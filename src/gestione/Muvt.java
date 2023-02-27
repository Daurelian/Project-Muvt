package gestione;


import entita.Sede;
import entita.Utente;
import entita.Veicolo;
import gestione.osservatori.Observable;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Muvt implements Observable {
    private static Muvt internalInstance;
    private ArrayList<Veicolo> obs = new ArrayList<>(); //questo è l'array di Observer (veicoli). L'array di Veicoli invece è presente nel Database.


    private Muvt(){
    }
    public static Muvt getInstance(){
    if(internalInstance!=null)
    return internalInstance;
    internalInstance= new Muvt();
    return internalInstance;
    } //qui è stato implementato il Design Pattern del Singleton
    public void aggiungiUtente(Utente utente){
    Database.addUtente(utente);
    }
    public void aggiungiVeicolo(Veicolo veicolo){
    Database.addVeicolo(veicolo);
    }
    public void aggiungiSede(Sede sede){
    Database.addSede(sede);
    }

    public void stampaUtenti(){
    System.out.println(Database.getUtenti().values());
    }
    public void stampaVeicoli(){
    System.out.println(Database.getVeicoli().values());
    }
    public void stampaSedi(){
    System.out.println(Database.getSedi());
    }
    public void affittaVeicolo(Utente utente, Veicolo veicolo, long minutes /*Duration*/ ){
        if(!veicolo.isDisponibile()){
            System.out.println("Veicolo selezionato non dsponibile");
            return;
        }
        veicolo.setDisponibile(false);
        veicolo.setOccupanteID(utente.getID());
        veicolo.setEndtime(LocalDateTime.now().plusMinutes(minutes));
        addObserver(veicolo);
        //// TODO calcolaprezzo
    }
    public void rinnovaVeicolo(Utente utente,Veicolo veicolo,long minutes){
        veicolo.setEndtime(LocalDateTime.now().plusMinutes(minutes));
        System.out.println("La preontazione del veicolo " + veicolo.getID()+ " è stata prolungata di "+minutes+" minuti");
        //calcolaprezzo
        System.out.println("Scalati (totsoldi) dal saldo dell'utente: " + veicolo.getOccupanteID());
    }


    //TODO checkcarburante(),lasciaveicolo(),checkpatente(),checkcasco(),checktempoprenotazione(minimo5minuti),aggiungiCredito()
    // modificareFLOWCHART(observer,singleton,attributivari),*opzionale fare i BUILDER

    @Override
    public void addObserver(Veicolo observer){
        obs.add(observer);
    }
    @Override
    public void removeObserver(Veicolo observer){
        obs.remove(observer);
    }

    public void notifyEndtime(LocalDateTime blabla){ //Questa funzione manda una notifica a tutti gli osservatori passando come messaggio LocalDateTime riferito all'istante attuale
        for(Veicolo ob :obs)
            if(ob.notifyMe(blabla))   //condizione di verità di avvenuto messaggio
                rinnovaVeicolo(Database.getUtenti().get(ob.getOccupanteID()),ob,1); //Il sistema rinnova automaticamente la prenotazione di un tempo prestabilito.
    }
}