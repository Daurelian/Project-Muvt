package gestione;

import entita.Sede;
import entita.Utente;
import entita.Veicolo;
import java.util.HashMap;
import java.util.HashSet;

public class Database {
    private static HashMap<Integer, Veicolo> veicoli = new HashMap<>(0);
    private static HashMap<Integer, Utente> utenti = new HashMap<>(0);
    private static HashSet<Sede> sedi = new HashSet<>(0);
    //hashmap key<id veicolo>, Affito (date e utenti)

    protected static void addUtente(Utente utente){
        utenti.put(utente.getID(), utente);
    }
    protected static void addSede(Sede sede){
        sedi.add(sede);
    }
    protected static void addVeicolo(Veicolo veicolo){
        veicoli.put(veicolo.getID(), veicolo);
    }
    protected static void removeUtente(Utente utente) { utenti.remove(utente.getID());}
    protected static void removeVeicolo(Veicolo veicolo) {veicoli.remove(veicolo.getID());}
    protected static void removeSede(Sede sede) {sedi.remove(sede);}
    public static HashMap<Integer, Veicolo> getVeicoli() {
        return veicoli;
    }
    public static HashMap<Integer, Utente> getUtenti() {
        return utenti;
    }
    public static HashSet<Sede> getSedi() {
        return sedi;
    }
}