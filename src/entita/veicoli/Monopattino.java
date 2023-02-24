package entita.veicoli;

import entita.Sede;
import entita.Veicolo;

public class Monopattino extends Veicolo implements Alimentazione {
    private float serbatoio = 100f;

    public Monopattino(Sede sede) {
        super(sede);
    }

    @Override
    public String controlloAlimentazione() {
        return null;
    }
}
