import entita.Patente;
import entita.Sede;
import entita.SedeCSV;
import entita.Utente;
import entita.veicoli.*;
import gestione.Database;
import gestione.Muvt;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Muvt muvt=Muvt.getInstance();

        //Creazione delle sedi
        Sede bari = new Sede("Bari", new int[]{1,1,1});
        Sede vittoria = new Sede("Vittoria", new int[]{2,2,2});
        Sede taranto = new Sede("Taranto", new int[]{3,3,3});
        Sede militello = new Sede("Militello in Val di Catania", new int[]{4,4,4});

        //Creazione dei veicoli
        Automobile auto1 = new Automobile("ab123cd", bari, TipoAlimentazione.ELETTRICO,0.35f);
        Automobile auto2 = new Automobile("ef456gh", militello, TipoAlimentazione.CARBURANTE,0.25f);
        Furgoncino furgoncino1 = new Furgoncino("il789iu", militello, TipoAlimentazione.CARBURANTE,0.40f);
        Furgoncino furgoncino2 = new Furgoncino("kj896oi", taranto, TipoAlimentazione.CARBURANTE,0.30f);
        Monopattino monopattino1 = new Monopattino(taranto);
        Monopattino monopattino2 = new Monopattino(vittoria);
        Moto moto1 = new Moto("xv895wj", bari, TipoAlimentazione.CARBURANTE,0.20f);
        Moto moto2 = new Moto("ca220ne", taranto, TipoAlimentazione.ELETTRICO,0.32f);
        Bicicletta bici = new Bicicletta(taranto);

        /*
        //Creazione degli utenti
        Utente michele = new Utente("Michele", "Damone", "mcldmn", "06/06/94", 13, new Patente[]{Patente.A, Patente.B, null});
        Utente davide = new Utente("Davide", "Campagna", "dvdcmp", "18/01/98", 1300, new Patente[]{null, Patente.B, null});
        Utente pippo = new Utente("Giuseppe", "Dambone", "gspdmb", "30/08/93", 500, new Patente[]{Patente.A, Patente.B, Patente.B1});
        Utente lucri = new Utente("Lucrezia", "Arestia", "lrzrst", "10/07/97", 20, new Patente[]{Patente.A, Patente.B, null});

        muvt.aggiungiUtente(michele);
        muvt.aggiungiUtente(davide);
        muvt.aggiungiUtente(pippo);
        muvt.aggiungiUtente(lucri);
*/

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


        //Database.updateUsersCsv();
        Database.leggiUtenti();
//        muvt.stampaUtenti();
        muvt.affittaVeicolo(1079644241, auto1,10);
        muvt.notifyEndtime(LocalDateTime.now().plusMinutes(10));
//        muvt.stampaUtenti();
        auto1.brumBrum();
        muvt.checkCarburante(auto1);
        auto1.setPosizioneVeicolo(taranto.getPosizioneSede());
        muvt.lasciaVeicolo(taranto,auto1);

        //Zona DAO
        SedeCSV sedi= new SedeCSV();
        System.out.println("\nLista sedi");
        sedi.stampa();
        sedi.updateSedes();
        System.out.println("\nRimuovo una sede");
        sedi.removeSede(bari);
        sedi.stampa();
        System.out.println("\nAggiungo una sede");
        sedi.addSede(bari);
        sedi.stampa();

        /*
       // muvt.stampaVeicoli();
       // muvt.stampaSedi();
       //
        System.out.println(LocalDateTime.now());

        System.out.println(LocalDateTime.now().plusMinutes(1));
        */
    }
}