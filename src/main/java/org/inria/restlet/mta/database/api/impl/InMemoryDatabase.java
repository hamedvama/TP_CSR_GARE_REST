package org.inria.restlet.mta.database.api.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.inria.restlet.mta.database.api.Database;
import org.inria.restlet.mta.internals.*;

/**
 *
 * In-memory database 
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class InMemoryDatabase implements Database
{

    /** User count (next id to give).*/
    private int trainCount_;
    private int voyageurCount_;

  EspaceQuai espaceQuai ;
  EspaceVente espaceVente;


    /**Train Hashmap.*/
    Map<Integer, Train> trains_;

    /**Voyageur Hashmap.*/
    Map<Integer, Voyageur> voyageurs_;


    public InMemoryDatabase(EspaceQuai espaceQuai, EspaceVente espaceVente)
    {
        trains_ = new HashMap<Integer, Train>();
        voyageurs_ = new HashMap<Integer, Voyageur>();
        this.espaceQuai = espaceQuai;
        this.espaceVente = espaceVente;
    }


    /**
     * Synchronized Train creation
     * @param name
     * @return
     */
    @Override
    public synchronized Train createTrain(String name){
        Train train = new Train(name);
        trains_.put(trainCount_, train);
        trainCount_++;
        train.setEspaceQuai(this.espaceQuai);
        train.start();
        return train;

    }

    /**
     * Synchronized Voyageur creation
     * @param name
     * @return
     */
    @Override
    public Voyageur createVoyageur(String name) {
        Voyageur voyageur = new Voyageur(name);
        voyageurs_.put(voyageurCount_,voyageur);
        voyageurCount_++;
        voyageur.setEspaceVente(this.espaceVente);
        voyageur.setEspaceQuai(this.espaceQuai);
        voyageur.start();
        return voyageur;
    }

    @Override
    public Collection<Train> getTrains() {
        return trains_.values();
    }

    @Override
    public Collection<Voyageur> getVoyageurs() {
        return voyageurs_.values();
    }


    @Override
    public Train getTrain(int id) {
        return trains_.get(id);
    }

    @Override
    public Voyageur getVoyageur(int id) {
        return voyageurs_.get(id);
    }

    @Override
    public void addTrain(Train train) {

    }

    @Override
    public void addVoyageur(Voyageur voyageur) {

    }

}