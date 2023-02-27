package gestione;


import entita.Sede;
import entita.Utente;
import entita.Veicolo;

public class Muvt {
    private static Muvt internalInstance;

     private Muvt(){
    }
     public static Muvt getInstance(){
        if(internalInstance!=null)
            return internalInstance;
        internalInstance= new Muvt();
        return internalInstance;
    }
     public void aggiungiUtente(Utente utente){
         Database.addUtente(utente);
     }
     public void aggiungiVeicolo(Veicolo veicolo){
         Database.addVeicolo(veicolo);
     }
     public void aggiungiSede(Sede sede){
         Database.addSede(sede);
     }

     public void stampaUtenti(){
         System.out.println(Database.getUtenti().values());
     }
     public void stampaVeicoli(){
         System.out.println(Database.getVeicoli().values());
     }
     public void stampaSedi(){
         System.out.println(Database.getSedi());
     }



}