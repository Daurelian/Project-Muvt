package entita;

public class Utente {
    private int ID;
    private String nome;
    private String cognome;
    private String codice_fiscale;
    private String data_di_nascita;
    private float saldo;
    private Patente[] patenti;
    private int[] posizione_utente = new int[3];

    public Utente(String nome, String cognome, String codice_fiscale, String data_di_nascita, float saldo, Patente[] patenti) {
        this.nome = nome;
        this.cognome = cognome;
        this.codice_fiscale = codice_fiscale;
        this.data_di_nascita = data_di_nascita;
        this.saldo = saldo;
        this.patenti = patenti;
        ID = codice_fiscale.hashCode();
    }

    public int getID() {
        return ID;
    }

    public float getSaldo() {
        return saldo;
    }

    public Patente getPatenti(int i) {
        return patenti[i]; //TODO da rivedere
    }

    public int[] getPosizione_utente() {
        return posizione_utente;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void setPosizione_utente(int[] posizione_utente) {
        this.posizione_utente = posizione_utente;
    }
}
