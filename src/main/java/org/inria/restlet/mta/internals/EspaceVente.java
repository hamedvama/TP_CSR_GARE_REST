package org.inria.restlet.mta.internals;

import java.util.ArrayList;

/**
 * Created by 18004032 on 27/11/17.
 */
public class EspaceVente {

    /*le nombre de billet disponible dans l'espace vente*/
    private int nbrebillet = 100;
    /* une liste de voyageurs ayant un billet*/
    private ArrayList<Voyageur> listVoyEspacVent = new ArrayList<Voyageur>();
    /*l'espace quai */
    private EspaceQuai espaceQuai;

    /**
     * Retourne vrai si le billet a pu etre acheter
     * @param voyageur
     * @return boolean
     */
    public synchronized boolean achatbillet(Voyageur voyageur) {
        boolean achat = false;
        int manque = 0;
        if (this.nbrebillet > 0) {
            nbrebillet--;
            achat = true;

            /*ajoute le voyageur a la liste des voyageur ayant un billet*/
            listVoyEspacVent.add(voyageur);

            /*change l'etat du voyageur*/
            voyageur.setStateV(Constante.ETAT2VOYAGEUR);

            /*affiche le voyageur et son etat dans la console*/
            System.out.println(" Etat du voyageur : " + voyageur.getNom() + " est : " + voyageur.getStateV());
            System.out.println();

            /*donner a l'espace quai la liste des voyagers ayant un billet*/
            espaceQuai.getListVoyAQaui().add(voyageur);
        } else {
            System.out.println(" ++++ il n'y a plus de billets, veiller patientez ... ");
        }
        return achat;
    }
    /* getters et setters des attributs de la classe Espacevente*/

    public int getNbrebillet() {
        return nbrebillet;
    }

    public void setNbrebillet(int nbrebillet) {
        this.nbrebillet = nbrebillet;
    }

    public ArrayList<Voyageur> getListVoyEspacVent() {
        return listVoyEspacVent;
    }

    public void setListVoyEspacVent(ArrayList<Voyageur> listVoyEspacVent) {
        this.listVoyEspacVent = listVoyEspacVent;
    }

    public EspaceQuai getEspaceQuai() {
        return espaceQuai;
    }

    public void setEspaceQuai(EspaceQuai espaceQuai) {
        this.espaceQuai = espaceQuai;
    }
}