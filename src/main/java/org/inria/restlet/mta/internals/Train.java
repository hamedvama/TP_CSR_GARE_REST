package org.inria.restlet.mta.internals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 18004032 on 27/11/17.
 */
public class Train extends Thread {

    private EspaceQuai espaceQuai;
    private int capacite = 3;
    private String TrainName;
    private int vitesse;
    private List<Voyageur> listVoyDuTrain = new ArrayList<Voyageur>();
    private String StateTrain;

    /**
     * creer un train avec son nom, sa capacité que nous avons fixé pour tous les trains
     *
     * sa vitesse aleatoire entre 50 et 300 et un etat d'origine
     *
     * @param nom
     */
    public Train(String nom) {
        this.TrainName = nom;
        this.capacite = capacite;
        this.StateTrain = Constante.ETAT1TRAIN;
        this.vitesse = (int) (50 + (Math.random() * (300 - 50)));
        System.out.println(this.getTrainName() + "  +++++++++  " + this.getStateTrain());
    }

    /**
     * accede a une voie et repare
     */
    public void run() {

        try {
                /*acceder a une voie*/
            espaceQuai.OQPVoie(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();

        try {
                /*quite la voie*/
            espaceQuai.LibereVoie(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * retourne vrai si le voyageur est monté dans un train
     *
     * @param voyageur
     * @return boolean
     */
    public boolean prendreVoyageur(Voyageur voyageur) {
        boolean monte = false;
        if (this.capacite > 0) {
            /*reduire la capacité du train */
            this.capacite--;
            /*change l'etat du voyageur*/
            voyageur.setStateV(Constante.ETAT3VOYAGEUR);
            /*ajoute le voyageur a la liste des voyageurs du train*/
            listVoyDuTrain.add(voyageur);
            /*affiche le voyageur, le train dans lequel il est et son etat*/
            System.out.println(">>>> le voyageur : " + voyageur.getNom() + " est dans le train "
                    + this.getTrainName() + " Son etat actuel est : " + voyageur.getStateV());
            /*retire le voyageur de la liste des voyageurs sur le quai*/
            espaceQuai.getListVoyAQaui().remove(voyageur);
            System.out.println();
            monte = true;
        } else {

        }
        return monte;
    }

    /* les getters egt setters des attributs de la classe train*/
    public EspaceQuai getEspaceQuai() {
        return espaceQuai;
    }

    public void setEspaceQuai(EspaceQuai espaceQuai) {
        this.espaceQuai = espaceQuai;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getTrainName() {
        return TrainName;
    }

    public void setTrainName(String trainName) {
        TrainName = trainName;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public List<Voyageur> getListVoyDuTrain() {
        return listVoyDuTrain;
    }

    public void setListVoyDuTrain(List<Voyageur> listVoyDuTrain) {
        this.listVoyDuTrain = listVoyDuTrain;
    }

    public String getStateTrain() {
        return StateTrain;
    }

    public void setStateTrain(String stateTrain) {
        StateTrain = stateTrain;
    }
}
