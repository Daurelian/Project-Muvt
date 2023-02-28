package gestione;

import entita.Patente;
import entita.Sede;
import entita.Utente;
import entita.Veicolo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;

public class Database {
    private static HashMap<Integer, Veicolo> veicoli = new HashMap<>(0);
    private static HashMap<Integer, Utente> utenti = new HashMap<>(0);
    private static HashSet<Sede> sedi = new HashSet<>(0);


    /**
     * Questo metodo legge da database (file.csv) gli utenti e ne fa una copia cache nell'HashMap attributo della classe
     */
    protected void inizializzaDatabase() {
        leggiUtenti();
        //leggiVeicoli();
        //leggiSedi();
    }

    /**
     * Questo metodo si occupa di leggere gli utente dal file csv
     */
    public static void leggiUtenti() {
        //Percorso relativo del file csv
        Path path = Paths.get("files", "CSV", "users.csv");

        //Se il file non esiste viene creato
        try {
            if (!Files.exists(path))
                Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //try-with-resources per leggere da file
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;        //Variabile per memorizzare il contenuto di una riga del csv
            String temp;        //Variabile temporanea per memorizzare una singola patente senza i caratteri ("[", "]", " ")
            String[] info;      //In ogni cella contiene l'ID, nome, cognome, .... tutti i vari campi dell'utente
            Patente[] patenti;  //Contiene le patenti dell'utente

            while ((line = reader.readLine()) != null) {
                info = line.split(",");     //Memorizzo tutti i campi dell'utente in ogni cella dell'array "info"
                patenti = new Patente[3];         //Inizializzo questa variabile che memorizzerà le patenti (le quali si trovano da info[7] a info[9])

                //Ogni patente (A, B, B1 o eventualmente "null") la memorizzo in "temp" e, se non è null, la aggiungo al vettore "patenti"
                for (int i=0; i<patenti.length; i++) {
                    temp = info[7+i].replace("[","").replace("]","").replace(" ","");

                    if (!temp.equals("null")) {
                        Patente valore = Patente.valueOf(temp);
                        patenti[i] = valore;
                    }
                }
                //Campi dell'utente = (nome, cognome, cod_fisc, data_nascita, saldo, casco, new Patente[]{Patente.A, Patente.B, null});
                utenti.put(Integer.valueOf(info[0]), new Utente(info[1], info[2], info[3], info[4], Float.parseFloat(info[5]), Boolean.parseBoolean(info[6]), patenti));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Questo metodo permette di aggiungere l'utente al database (file csv) e al database interno usato come cache (classe Database)
     * @param utente utente da aggiungere al database
     */
    protected static void addUtente(Utente utente) {
        utenti.put(utente.getID(), utente);
        updateUsersCsv();
    }

    protected static void addSede(Sede sede){
        sedi.add(sede);
    }
    protected static void addVeicolo(Veicolo veicolo){
        veicoli.put(veicolo.getID(), veicolo);
    }
    protected static void removeUtente(Utente utente) {
        utenti.remove(utente.getID());
    }
    protected static void removeVeicolo(Veicolo veicolo) {
        veicoli.remove(veicolo.getID());
    }
    protected static void removeSede(Sede sede) {
        sedi.remove(sede);
    }
    public static HashMap<Integer, Veicolo> getVeicoli() {
        return veicoli;
    }
    public static HashMap<Integer, Utente> getUtenti() {
        return utenti;
    }
    public static HashSet<Sede> getSedi() {
        return sedi;
    }

    /**
     * Aggiorna il file csv con le informazioni memorizzate nella classe Database (quindi le info presenti nella copia "cache" del database)
     * @return vero o falso a seconda se l'operazione è andata o meno a buon fine
     */
    public static boolean updateUsersCsv() {
        Path path = Paths.get("files", "CSV", "users.csv");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Utente user : utenti.values()) {
                writer.write(user.writeAsCsv());
                writer.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}