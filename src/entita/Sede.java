package entita;

public class Sede {
    private String nome;
    private int[] posizione_sede;

    public Sede(String nome, int[] posizione_sede) {
        this.nome = nome;
        this.posizione_sede = posizione_sede;
    }

    public String getNome() {
        return nome;
    }

    public int[] getPosizione_sede() {
        return posizione_sede;
    }
}
