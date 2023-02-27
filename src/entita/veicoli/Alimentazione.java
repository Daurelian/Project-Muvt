package entita.veicoli;

public interface Alimentazione {
     /**
      * Questo metodo controlla la percentuale di carburante o elettricità del veicolo e la restituisce in output
      * @return percentuale di carburante/batteria rimanente sottoforma di una stringa (es. "47%")
      */
     String controlloAlimentazione();
}