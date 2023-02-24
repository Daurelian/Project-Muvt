package entita;

public class Veicolo {
    private static int contatore = 1;
    private int ID;
    private boolean disponibile = true;
    private int[] posizione_veicolo = new int[3];

    protected Veicolo(Sede sede) {
        this.posizione_veicolo = sede.getPosizione_sede();
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

    public void setPosizione_veicolo(int[] posizione_veicolo) {
        this.posizione_veicolo = posizione_veicolo;
    }

    public boolean isDisponibile() {
        return disponibile;
    }
}
