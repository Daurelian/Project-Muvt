import entita.Patente;
import entita.Sede;
import entita.Utente;
import entita.veicoli.*;
import gestione.Muvt;

public class Main {
    public static void main(String[] args) {

        //Creazione delle sedi
        Sede bari = new Sede("Bari", new int[]{1,1,1});
        Sede vittoria = new Sede("Vittoria", new int[]{2,2,2});
        Sede taranto = new Sede("Taranto", new int[]{3,3,3});
        Sede militello = new Sede("Militello in Val di Catania", new int[]{4,4,4});

        //Creazione dei veicoli
        Automobile auto1 = new Automobile("ab123cd", bari, TipoAlimentazione.ELETTRICO);
        Automobile auto2 = new Automobile("ef456gh", militello, TipoAlimentazione.CARBURANTE);
        Furgoncino furgoncino1 = new Furgoncino("il789iu", militello, TipoAlimentazione.CARBURANTE);
        Furgoncino furgoncino2 = new Furgoncino("kj896oi", taranto, TipoAlimentazione.CARBURANTE);
        Monopattino monopattino1 = new Monopattino(taranto);
        Monopattino monopattino2 = new Monopattino(vittoria);
        Moto moto1 = new Moto("xv895wj", bari, TipoAlimentazione.CARBURANTE);
        Moto moto2 = new Moto("ca220ne", taranto, TipoAlimentazione.ELETTRICO);
        Bicicletta bici = new Bicicletta(taranto);

        //Creazione degli utenti
        Utente michele = new Utente("Michele", "Damone", "mcldmn", "06/06/94", 13, new Patente[]{Patente.A, Patente.B, null});
        Utente davide = new Utente("Davide", "Campagna", "dvdcmp", "18/01/98", 1300, new Patente[]{null, Patente.B, null});
        Utente pippo = new Utente("Giuseppe", "Dambone", "gspdmb", "30/08/93", 500, new Patente[]{Patente.A, Patente.B, Patente.B1});
        Utente lucri = new Utente("Lucrezia", "Arestia", "lrzrst", "10/07/97", 20, new Patente[]{Patente.A, Patente.B, null});

        Muvt muvt=Muvt.getInstance();
        muvt.aggiungiUtente(michele);
        muvt.aggiungiUtente(davide);
        muvt.aggiungiUtente(pippo);
        muvt.aggiungiUtente(lucri);

        muvt.aggiungiSede(bari);
        muvt.aggiungiSede(vittoria);
        muvt.aggiungiSede(militello);
        muvt.aggiungiSede(taranto);

        muvt.aggiungiVeicolo(auto1);
        muvt.aggiungiVeicolo(auto2);
        muvt.aggiungiVeicolo(furgoncino1);
        muvt.aggiungiVeicolo(furgoncino2);
        muvt.aggiungiVeicolo(monopattino1);
        muvt.aggiungiVeicolo(monopattino2);
        muvt.aggiungiVeicolo(moto1);
        muvt.aggiungiVeicolo(moto2);
        muvt.aggiungiVeicolo(bici);

       // muvt.stampaVeicoli();
       // muvt.stampaSedi();
       // muvt.stampaUtenti();
    }
}