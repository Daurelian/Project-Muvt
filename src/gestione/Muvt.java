package gestione;


import entita.Sede;
import entita.Utente;
import entita.Veicolo;
import gestione.osservatori.Observable;
import gestione.osservatori.Observer;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class Muvt implements Observable {
    private static Muvt internalInstance;
    private ArrayList<Veicolo> obs = new ArrayList<>();


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
    public void affittaVeicolo(Utente utente, Veicolo veicolo, long minutes /*Duration*/ ){
        if(!veicolo.isDisponibile()){
            System.out.println("Veicolo selezionato non è più dsponibile");
            return;
        }
        veicolo.setDisponibile(false);
        veicolo.setOccupanteID(utente.getID());
        veicolo.setEndtime(LocalDateTime.now().plusMinutes(minutes));
        addObserver(veicolo);
        //// TODO calcolaprezzo
    }
    public void rinnovaVeicolo(Utente utente,Veicolo veicolo,long minutes){
        veicolo.setEndtime(LocalDateTime.now().plusMinutes(minutes));
    }

    @Override
    public void addObserver(Veicolo observer){
        obs.add(observer);
    }
    @Override
    public void removeObserver(Veicolo observer){
        obs.remove(observer);
    }

    public void notifyEndtime(LocalDateTime blabla){
        for(Veicolo ob :obs)
            if(ob.notifyMe(blabla))
                rinnovaVeicolo(Database.getUtenti().get(ob.getOccupanteID()),ob,1);
    }
}