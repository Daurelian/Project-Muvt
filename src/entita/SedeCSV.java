package entita;

import gestione.Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class SedeCSV implements  SedeDAO{
    @Override
    public void updateSedes() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("files","CSV","sede.csv"))) {
            for (Sede sede : Database.getSedi()) {
                bw.write(writeAsCsv(sede));
                bw.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String writeAsCsv(Sede sede){
        return String.join(",", sede.getNome(), Arrays.toString(sede.getPosizioneSede()));
    }

    @Override
    public void removeSede(Sede sede) {
        Database.getSedi().remove(sede);
        updateSedes();
    }
    public void addSede(Sede sede) {
        Database.getSedi().add(sede);
        updateSedes();
    }

    @Override
    public void stampa(){
        try (BufferedReader br = Files.newBufferedReader(Paths.get("files","CSV","sede.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] info=line.split(",");
                System.out.println("Sede: "+info[0]+"\nPosizione: "+info[1]+", "+info[2]+", "+info[3]);
                }
            } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
