package entita.veicoli;

public interface Alimentazione {
     TipoAlimentazione tipo_alimentazione = null;

     //Restituisce la percentuale di serbatoio rimanente
     public String controlloAlimentazione();
}
