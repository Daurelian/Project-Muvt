package gestione;

import entita.Patente;
import entita.Sede;
import entita.Utente;
import entita.Veicolo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;

public class Database {
    private static HashMap<Integer, Veicolo> veicoli = new HashMap<>(0);
    private static HashMap<Integer, Utente> utenti = new HashMap<>(0);
    private static HashSet<Sede> sedi = new HashSet<>(0);
    //hashmap key<id veicolo>, Affito (date e utenti)


    protected void inizializzaDatabase(){
        leggiUtenti();
        //leggiVeicoli();
        //leggiSedi();
    }
    public static void leggiUtenti(){
        try {
            if (!Files.exists(Paths.get("files","CSV","users.csv")))
                Files.createFile(Paths.get("files","CSV","users.csv"));
        } catch (IOException e) { e.printStackTrace(); }

        try (BufferedReader br = Files.newBufferedReader(Paths.get("files","CSV","users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] info=line.split(",");
                //Utente("Michele", "Damone", "mcldmn", "06/06/94", 13, new Patente[]{Patente.A, Patente.B, null});
                Patente[] patente = new Patente[3];
                int i=0;
                for (i=0;i<patente.length;i++){
                    String trad=info[7+i].replace("[","").replace("]","").replace(" ","");
                    if(!trad.equals("null")){
                        Patente valore=Patente.valueOf(trad);
                        patente[i]=valore;
                    }
                }
                utenti.put(Integer.valueOf(info[0]),new Utente(info[1],info[2],info[3],info[4],Float.valueOf(info[5]),Boolean.parseBoolean(info[6]),patente));
            }
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    protected static void addUtente(Utente utente){
        utenti.put(utente.getID(), utente);
        updateUsersCsv();
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
    public static boolean updateUsersCsv() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("files","CSV","users.csv"))) {
            for (Utente user : utenti.values()) {
                bw.write(user.writeAsCsv());
                bw.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}