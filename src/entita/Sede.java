package entita;

import java.util.Arrays;

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

    public int[] getPosizioneSede() {
        return posizione_sede;
    }


    @Override
    public String toString() {
        return "Luogo: " +nome+ "\nPosizione: " +Arrays.toString(posizione_sede);
    }
}