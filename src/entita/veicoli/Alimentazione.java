package entita.veicoli;

public interface Alimentazione {
     TipoAlimentazione tipo_alimentazione = null;      //TODO questo attributo sembra non servire a niente nell'interfaccia. L'IDE non ci ha obbligati a metterlo nelle varie classi

     /**
      * Questo metodo controlla la percentuale di carburante o elettricità del veicolo e la restituisce in output
      * @return percentuale di carburante/batteria rimanente sottoforma di una stringa (es. "47%")
      */
     String controlloAlimentazione();
}