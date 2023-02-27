package gestione.osservatori;

import entita.Veicolo;

public interface Observable {
    public void addObserver(Veicolo observer);
    public void removeObserver(Veicolo observer);
}
