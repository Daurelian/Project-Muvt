package entita.veicoli;

import entita.Sede;
import entita.Veicolo;

public class Furgoncino extends Veicolo implements Alimentazione {
    private String targa;
    private float serbatoio = 100f;

    public Furgoncino(String targa, Sede sede) {
        super(sede);
        this.targa = targa;
    }

    @Override
    public String controlloAlimentazione() {
        return null;
    }
}
