package org.inria.restlet.mta.internals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 18004032 on 27/11/17.
 */
public class Voyageur extends Thread {
    private EspaceVente espaceVente;
    private EspaceQuai espaceQuai;
    private List<Train> listtrain = new ArrayList<Train>();
    private String StateVoy;
    private String nom;

    /**
     * creer un voyageur avec un nom et un etat d'origine
     *
     * @param nom
     */
    public Voyageur(String nom) {
        this.nom = nom;
        this.StateVoy = Constante.ETAT1VOYAGEUR;
        System.out.println(this.getNom() + "  ********  " + this.getStateV());
    }

    /**
     * cette methode permet au voyageur de d'essayer de payer un billet
     *
     * ensuite d'essayer de monter dans un train sur le quai
     */
    public void run() {

        Boolean payer;
        /* payer un billet*/
        payer = espaceVente.achatbillet(this);
        if (payer == true) {
            boolean monte = false;
            fin1:
            while (!monte) {
                /*monter dans un train*/
                monte = espaceQuai.VoyageurMonteTrain(this);
            }
        }
    }

    /*Getter et Setters des attributs de la classe voyageurs*/

    public EspaceVente getEspaceVente() {
        return espaceVente;
    }

    public void setEspaceVente(EspaceVente espaceVente) {
        this.espaceVente = espaceVente;
    }

    public EspaceQuai getEspaceQuai() {
        return espaceQuai;
    }

    public void setEspaceQuai(EspaceQuai espaceQuai) {
        this.espaceQuai = espaceQuai;
    }

    public List<Train> getListtrain() {
        return listtrain;
    }

    public void setListtrain(List<Train> listtrain) {
        this.listtrain = listtrain;
    }

    public String getStateV() {
        return StateVoy;
    }

    public void setStateV(String state) {
        StateVoy = state;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
