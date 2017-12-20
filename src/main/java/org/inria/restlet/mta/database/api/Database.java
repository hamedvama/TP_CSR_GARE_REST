package org.inria.restlet.mta.database.api;

import java.util.Collection;
import java.util.List;

import org.inria.restlet.mta.internals.Train;
import org.inria.restlet.mta.internals.User;
import org.inria.restlet.mta.internals.Voyageur;

/**
 *
 * Interface to the database.
 *
 * @author diakite
 * @author khabaz
 *
 */
public interface Database
{

    /**
     * Create a new Train in the database.
     *
     * @param name      The name of the Train
     * @return  the new Train.
     */
    Train createTrain(String name);

    /**
     * Create a new voyageur in the database
     *
     * @param name
     * @return the new voyageur
     */
    Voyageur createVoyageur(String name);

    /**
     * Returns the list of trains.
     *
     * @return the list of trains
     */
    Collection<Train> getTrains();

    /**
     * Returns the list of voyageurs.
     *
     * @return the list of voyageurs
     */
    Collection<Voyageur> getVoyageurs();


    /**
     *  Returns the Train with a given id.
     *
     *  @return the Train
     */
    Train getTrain(int id);

    /**
     * Returns the voyageur with a given id
     *
     * @return the voyageur
     */
    Voyageur getVoyageur (int id);

    void addTrain(Train train);
    void addVoyageur(Voyageur voyageur);
}