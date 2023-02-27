package entita;

import gestione.osservatori.Observer;

import java.time.LocalDateTime;
import java.util.Arrays;

public abstract class Veicolo implements Observer {
    private static int contatore = 1;       //Serve per incrementare l'ID ad ogni creazione di un veicolo
    private int ID;
    private boolean disponibile = true;     //Al momento della creazione il veicolo è sempre disponibile
    private int[] posizione_veicolo;        //Coordinate (x, y, z)
    LocalDateTime endtime;
    private int occupanteID;
    private double prezzoOrario;

    protected Veicolo(Sede sede, double prezzo) {
        this.posizione_veicolo = sede.getPosizioneSede();
        this.prezzoOrario=prezzo;
        ID = contatore++;
    }

    public int getID() {
        return ID;
    }

    public int[] getPosizione_veicolo() {
        return posizione_veicolo;
    }

    public int getOccupanteID() {
        return occupanteID;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    public void setPosizioneVeicolo(int[] posizione_veicolo) {
        this.posizione_veicolo = posizione_veicolo;
    }

    public void setEndtime(LocalDateTime endtime) {
        this.endtime = endtime;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setOccupanteID(int occupanteID) {
        this.occupanteID = occupanteID;
    }

    @Override
    public String toString() {
        return "ID: " +ID+ "\nDisponibile: " +disponibile+ "\nCoordinate: " +Arrays.toString(posizione_veicolo);
    }

    @Override
    public boolean notifyMe(LocalDateTime ora){
        if(endtime.isBefore(ora)|| endtime.isEqual(ora)){
            System.out.println("HAI SFORATO");
            return true;}
        return false;
    }
}