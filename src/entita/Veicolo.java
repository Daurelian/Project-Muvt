package entita;

import java.util.Arrays;

public class Veicolo {
    private static int contatore = 1;       //Serve per incrementare l'ID ad ogni creazione di un veicolo
    private int ID;
    private boolean disponibile = true;     //Al momento della creazione il veicolo è sempre disponibile
    private int[] posizione_veicolo;        //Coordinate (x, y, z)

    protected Veicolo(Sede sede) {
        this.posizione_veicolo = sede.getPosizioneSede();
        ID = contatore++;
    }

    public int getID() {
        return ID;
    }

    public int[] getPosizione_veicolo() {
        return posizione_veicolo;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    public void setPosizioneVeicolo(int[] posizione_veicolo) {
        this.posizione_veicolo = posizione_veicolo;
    }

    public boolean isDisponibile() {
        return disponibile;
    }


    @Override
    public String toString() {
        return "ID: " +ID+ "\nDisponibile: " +disponibile+ "\nCoordinate: " +Arrays.toString(posizione_veicolo);
    }
}