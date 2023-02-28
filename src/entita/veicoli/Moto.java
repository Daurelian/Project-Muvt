package entita.veicoli;

import entita.Sede;
import entita.Veicolo;

public class Moto extends Veicolo implements Alimentazione {
    private String targa;
    private float serbatoio = 100f;

    public float getSerbatoio() {
        return serbatoio;
    }

    TipoAlimentazione tipo_alimentazione;

    public Moto(String targa, Sede sede, TipoAlimentazione tipo_alimentazione,float prezzo) {
        super(sede,prezzo);
        this.targa = targa;
        this.tipo_alimentazione = tipo_alimentazione;
    }

    public void brumBrum(){
        this.serbatoio*=0.80;
    }
    @Override
    public String controlloAlimentazione() {
        return null;
    }

    @Override
    public String toString() {
        String tipo = tipo_alimentazione == TipoAlimentazione.CARBURANTE ? "Serbatoio" : "Batterie";
        return super.toString()+ "\nTarga: " +targa+ "\n" +tipo+ " al " +serbatoio+ "%\nAlimentazione: " +tipo_alimentazione;
    }
}