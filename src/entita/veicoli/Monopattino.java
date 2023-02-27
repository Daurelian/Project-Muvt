package entita.veicoli;

import entita.Sede;
import entita.Veicolo;

public class Monopattino extends Veicolo implements Alimentazione {
    private float serbatoio = 100f;
    TipoAlimentazione tipo_alimentazione = TipoAlimentazione.ELETTRICO;

    public Monopattino(Sede sede) {
        super(sede,0.15f);
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