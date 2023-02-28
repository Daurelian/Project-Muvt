package entita.veicoli;

import entita.Sede;
import entita.Veicolo;

public class Monopattino extends Veicolo implements Alimentazione {
    private float serbatoio = 100f;

    public float getSerbatoio() {
        return serbatoio;
    }

    TipoAlimentazione tipo_alimentazione = TipoAlimentazione.ELETTRICO;

    public Monopattino(Sede sede) {
        super(sede,0.15f);
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
        return super.toString()+ "\nBatterie al " +serbatoio+ "%\nAlimentazione: " +tipo_alimentazione;
    }
}