package gestione;

import entita.Sede;
import entita.Utente;
import entita.Veicolo;

import java.util.HashMap;
import java.util.HashSet;

public class Database {
    private HashMap<Integer, Veicolo> veicoli = new HashMap<>(0);
    private HashMap<Integer, Utente> utenti = new HashMap<>(0);
    private HashSet<Sede> sedi = new HashSet<>(0);

    public void addUtente(Utente utente){
        utenti.put(utente.getID(), utente);
    }


}
