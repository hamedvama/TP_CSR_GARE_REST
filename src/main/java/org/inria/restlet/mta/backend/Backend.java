package org.inria.restlet.mta.backend;

import org.inria.restlet.mta.database.api.Database;
import org.inria.restlet.mta.database.api.impl.InMemoryDatabase;
import org.inria.restlet.mta.internals.*;

import java.util.ArrayList;

/**
 *
 * Backend for all resources.
 * 
 * @author ctedeschi
 * @author msimonin
 *
 */
public class Backend
{
    /*l'espace quai qui sera partagé par les trains et les voyageurs*/
    static EspaceQuai espaceQuai = new EspaceQuai();
    /*l'espace vente qui sera partagé par les voyageurs*/
    static EspaceVente espaceVente = new EspaceVente();

    /** Database.*/
    private Database database_;

    public Backend()
    {
        /* donner l'espace quai au train*/
        this.espaceVente.setEspaceQuai(espaceQuai);
        /*creation de la base de données*/
        database_ = new InMemoryDatabase(espaceQuai, espaceVente);
    }

    public Database getDatabase()
    {
        return database_;
    }

    /**
     * Cette Methode permet de lancer une simulation par default avec des trains
     * et des voyageurs au lancement de l'application
     */
    public void lancer(){

        /* initialisation de l'environnement de simulation */
        Train train1 = getDatabase().createTrain("RER A");
        Train train2 = getDatabase().createTrain("RER B");
        Train train3 = getDatabase().createTrain("RER C");

        System.out.println();

        Voyageur voyageur1 = getDatabase().createVoyageur("Alpha");
        Voyageur voyageur2 = getDatabase().createVoyageur("Beta");
        Voyageur voyageur3 = getDatabase().createVoyageur("Gama");
        Voyageur voyageur4 = getDatabase().createVoyageur("Lamda");
        Voyageur voyageur5 = getDatabase().createVoyageur("Sigma");
        Voyageur voyageur6 = getDatabase().createVoyageur("Hocta");
        Voyageur voyageur7 = getDatabase().createVoyageur("Silva");
        Voyageur voyageur8 = getDatabase().createVoyageur("Diakite");
        Voyageur voyageur9 = getDatabase().createVoyageur("Khabaz");
        System.out.println();

    }

}