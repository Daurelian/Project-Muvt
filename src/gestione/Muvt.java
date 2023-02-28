package gestione;

import entita.Patente;
import entita.Sede;
import entita.Utente;
import entita.Veicolo;
import entita.veicoli.*;
import gestione.osservatori.Observable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Muvt implements Observable {
    private static Muvt internalInstance;               //Unica istanza ammissibile della classe Muvt (Singleton)
    private ArrayList<Veicolo> obs = new ArrayList<>(); //Array di Observer (veicoli). L'array di Veicoli invece è presente nel Database


    /**
     * Questo costruttore è privato perchè questa classe è un Singleton, ovvero ammette una sola istanza. Il costruttore privato è
     * richiamato nel metodo getInstance() solo una volta (la prima), tutte le volte successive verrà restituito l'attributo internalInstance
     */
    private Muvt() {}

    /**
     * Questo metodo si occupa di istanziare un oggetto di tipo Muvt qualora non esistesse già, altrimenti restituisce l'istanza già creata
     * @return istanza (oggetto) di tipo Muvt
     */
    public static Muvt getInstance() {
        if (internalInstance != null)   //Se l'istanza è già creata, la restituisce
            return internalInstance;

        internalInstance= new Muvt();   //Altrimenti la crea
        return internalInstance;
    }

    public void aggiungiUtente(Utente utente) {
        Database.addUtente(utente);
    }

    public void aggiungiVeicolo(Veicolo veicolo) {
        Database.addVeicolo(veicolo);
    }

    public void aggiungiSede(Sede sede) {
    Database.addSede(sede);
    }

    public void stampaUtenti() {
        System.out.println(Database.getUtenti().values());
    }

    public void stampaVeicoli() {
        System.out.println(Database.getVeicoli().values());
    }

    public void stampaSedi() {
        System.out.println(Database.getSedi());
    }

    /**
     * Questo metodo permette di affittare un veicolo
     * @param ID identificativo dell'utente che vuole affittare il veicolo
     * @param veicolo veicolo che si vuole affittare
     * @param minutes tempo per il quale l'utente vuole affittare il veicolo
     */
    public void affittaVeicolo(int ID, Veicolo veicolo, long minutes) {
        //Non è ammesso un tempo di prenotazione inferiore ai 5 min
        if (minutes < 5) {
            System.out.println("Il tempo di prenotazione è troppo breve, selezionare un tempo più lungo");
            return;
        }
        //Viene preventivamente controllato se l'utente ha abbastanza credito per pagare il veicolo per il tempo dichiarato
        if (cercaUtente(ID).getSaldo() < (float)minutes * veicolo.getPrezzoMinuto()) {
            System.out.println("Credito insufficiente");
            return;
        }
        //Viene controllato se il veicolo non è già stato affittato da qualcun altro
        if (!veicolo.isDisponibile()){
            System.out.println("Veicolo selezionato non dsponibile");
            return;
        }
        //Controllo se il veicolo appartiene ad uno dei veicoli che richiedono il caso per poter essere affittati
        if (veicolo.getClass().equals(Moto.class) || veicolo.getClass().equals(Monopattino.class) || veicolo.getClass().equals(Bicicletta.class)) {
            //Controllo se l'utente possiede il casco
            if (!cercaUtente(ID).isCasco()) {
                System.out.println("L'utente non ha il casco e non può prenotare il veicolo");
                return;
            }
            //Per guidare la moto è necessaria la patente A, quindi viene controllato se l'utente la possiede
            if (!(veicolo.getClass().equals(Moto.class) && cercaUtente(ID).getPatente(0) == Patente.A)){
                System.out.println("L'utente non ha la patente adatta per poter prenotare il veicolo");
                return;
            }
            //Per guidare gli altri veicoli (tranne monopattino e bici) è necessaria la patente B
        } else if (veicolo.getClass().equals(Automobile.class) || veicolo.getClass().equals(Furgoncino.class)) {
            if (!(cercaUtente(ID).getPatente(1) == Patente.B)) {
                System.out.println("L'utente non ha la patente adatta per poter prenotare il veicolo");
                return;
            }
        }

        /* Se i controlli precedenti passano:
         * la disponibilità del veicolo viene impostata su "occupato"
         * il credito dell'utente viene scalato
         * l'observer associato al veicolo eliminato
         */
        veicolo.setDisponibile(false);
        veicolo.setOccupanteID(ID);
        veicolo.setEndtime(LocalDateTime.now().plusMinutes(minutes));
        addObserver(veicolo);
        cercaUtente(ID).updateSaldo((float)minutes * veicolo.getPrezzoMinuto(), false);
        System.out.println("\nIl veicolo è stato prenotato correttamente");
        // updateUtentiCsv(); //aggiornare il csv con il nuovo credito
    }

    /**
     * Questo metodo permette di prolungare il possesso del veicolo da parte dell'utente
     * @param ID identificativo dell'utente che vuole estendere la prenotazione
     * @param veicolo veicolo interessato dalla prenotazione
     * @param minutes minuti per i quali si vuole prolungare la detenzione del veicolo
     */
    public void rinnovaPrenotazioneVeicolo(int ID, Veicolo veicolo, long minutes){
        //Vengono sommati "minutes" al tempo di fine prenotazione
        veicolo.setEndtime(LocalDateTime.now().plusMinutes(minutes));
        System.out.println("La prenotazione del veicolo " +veicolo.getID()+ " è stata prolungata di " +minutes+ " minuti");

        //Vengono scalati i soldi all'utente relativi ai nuovi minuti di utilizzo del veicolo
        cercaUtente(ID).updateSaldo((float)minutes * veicolo.getPrezzoMinuto(), false);
        System.out.println("Scalati (totsoldi) dal saldo dell'utente con ID \"" +veicolo.getOccupanteID()+ "\"");
        //updateUtentiCsv(); //aggiornare il csv con il nuovo credito
    }

    public Utente cercaUtente(int ID) {
        return Database.getUtenti().get(ID);
    }

    public void updateCredito(int ID, float money) {
        Utente utente = cercaUtente(ID);
        utente.setSaldo(utente.getSaldo() + money);
        Database.updateUsersCsv();
    }

    /**
     * Questo metodo permette di lasciare il veicolo, quindi di impostare la sua disponibilità su "true"
     * @param sede sede alla quale si vuole lasciare il veicolo
     * @param veicolo veicolo da lasciare
     */
    public void lasciaVeicolo(Sede sede, Veicolo veicolo) {
        //Viene controllato se il veicolo è utilizzato (altrimenti non avrebbe senso)
        if (!veicolo.isDisponibile()) {
            //Il veicolo deve trovarsi già nella sede destinazione (si imposta nel main manualmente)
            if (veicolo.getPosizione_veicolo() == sede.getPosizioneSede()) {

                veicolo.setDisponibile(true);   //Il veicolo viene impostato su "disponibile"
                veicolo.setOccupanteID(-1);     //L'occupante viene rimosso
                removeObserver(veicolo);        //L'observer viene rimosso
                veicolo.setEndtime(null);       //Il tempo di fine prenotazione viene rimosso
                System.out.println("Veicolo consegnato correttamente");

            } else
                System.out.println("Il veicolo non è nella sede selezionata per poterlo lasciare");
        } else
            System.out.println("Il Veicolo non è in strada");
    }

    /**
     * Questo metodo permette di stampare il livello (in percentuale) di carburante (o batteria) del veicolo
     * @param veicolo veicolo di cui si vuole conoscere la percentuale di carburante/elettricità rimanente
     */
    public void stampaLivelloCarburante(Veicolo veicolo) {
        if(veicolo.getClass().equals(Moto.class))
            System.out.println("Livello carburante: " +((Moto)veicolo).getSerbatoio()+ "%");
        else if (veicolo.getClass().equals(Automobile.class))
            System.out.println("Livello carburante: " +((Automobile)veicolo).getSerbatoio()+ "%");
        else if(veicolo.getClass().equals(Furgoncino.class))
            System.out.println("Livello carburante: " +((Furgoncino)veicolo).getSerbatoio()+ "%");
        else if (veicolo.getClass().equals(Monopattino.class))
            System.out.println("Livello carburante: " +((Monopattino)veicolo).getSerbatoio()+ "%");
    }


    @Override
    public void addObserver(Veicolo observer) {
        obs.add(observer);
    }
    @Override
    public void removeObserver(Veicolo observer) {
        obs.remove(observer);
    }

    /**
     * Questa funzione manda una notifica a tutti gli osservatori passando come messaggio LocalDateTime riferito all'istante attuale
     * @param tempo_attuale gli passo il tempo attuale (LocalDateTime.now())
     */
    public void notifyEndtime(LocalDateTime tempo_attuale) {
        for (Veicolo ob : obs)
            if (ob.notifyMe(tempo_attuale))                                         //Condizione di verità di avvenuto messaggio
                rinnovaPrenotazioneVeicolo(ob.getOccupanteID(), ob, 1);     //Il sistema rinnova automaticamente la prenotazione di un tempo prestabilito
    }
}