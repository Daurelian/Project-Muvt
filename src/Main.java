import entita.Patente;
import entita.Sede;
import entita.Utente;
import entita.Veicolo;
import entita.veicoli.*;

public class Main {
    public static void main(String[] args) {

        //creiamo le sedi
        Sede bari = new Sede("Bari", new int[]{1,1,1});
        Sede vittoria = new Sede("Vittoria", new int[]{2,2,2});
        Sede taranto = new Sede("Taranto", new int[]{3,3,3});
        Sede militello = new Sede("Militello in Val di Catania", new int[]{4,4,4});

        //creiamo i veicoli
        Automobile a1 = new Automobile("ab123cd", bari);
        Automobile a2 = new Automobile("ef456gh", militello);
        Furgoncino f1 = new Furgoncino("il789iu", militello);
        Furgoncino f2 = new Furgoncino("kj896oi", taranto);
        Monopattino mn1 = new Monopattino(taranto);
        Monopattino mn2 = new Monopattino(vittoria);
        Moto m1 = new Moto("xv895wj", bari);
        Bicicletta b1 = new Bicicletta(taranto);

        //creiamo gli utenti
        Utente michele = new Utente("Michele", "Damone", "mcldmn", "06/06/94", 13, new Patente[]{Patente.A, Patente.B, null});
        Utente davide = new Utente("Davide", "Campagna", "dvdcmp", "18/01/98", 1300, new Patente[]{null, Patente.B, null});
        Utente pippo = new Utente("Giuseppe", "Dambone", "gspdmb", "30/08/93", 500, new Patente[]{Patente.A, Patente.B, Patente.B1});
        Utente lucri = new Utente("Lucrezia", "Arestia", "lrzrst", "10/07/97", 20, new Patente[]{Patente.A, Patente.B, null});


    }
}