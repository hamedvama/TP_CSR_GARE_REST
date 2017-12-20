package org.inria.restlet.mta.internals;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by 18004032 on 27/11/17.
 */
public class EspaceQuai {

    private int nbvoie = 1 ;
    boolean OQP = false;
    private ArrayList <Train> listTrainEspQuai = new ArrayList<Train>();
    private ArrayList<Voyageur> listVoyAQaui = new ArrayList<Voyageur>();

    /**
     * retourne true (oui) si le train a pu se garé sur le quai
     * @param train
     * @return boolean
     * @throws InterruptedException
     */
    public synchronized Boolean OQPVoie(Train train) throws InterruptedException {

        while(nbvoie==0) {
            /*change l'etat du train (en attente de voie libre) */
            train.setStateTrain(Constante.ETAT2TRAIN);
            System.out.println(train.getTrainName() + " ----  "+ train.getStateTrain());
            /*faire patienter le train*/
            wait();
        }
        /*diminue le nombre de voie disponible sur le quai*/
        nbvoie--;
        /*change l'etat du train*/
        train.setStateTrain(Constante.ETAT3TRAIN);
        /*ajoute le train a la liste des trains sur le quai*/
        listTrainEspQuai.add(train);
        /*affiche le train et son etat dans la console*/
        System.out.println(train.getTrainName() + " ----  "+ train.getStateTrain());
        /*faire patienter le train sur le quai*/
        train.sleep(5000);
        OQP = true;
        return OQP;
    }

    /**
     * Libere la voie au depart d'un train
     * @param train
     * @throws InterruptedException
     */
    public synchronized void LibereVoie(Train train) throws InterruptedException {
        /*augmente de le nombre de voie disonible*/
        nbvoie++;
        /*change l'etat du train*/
        train.setStateTrain(Constante.ETAT4TRAIN);
        /*affiche l'etat du train*/
        System.out.println(train.getTrainName() + " ----  "+ train.getStateTrain());
        System.out.println();
        /*retire le train de la liste de train sur le quai*/
        listTrainEspQuai.remove(train);
        /*reveiler un train qui patiente */
        notify();
        OQP = false;
    }

    /**
     * retourne true si le voyageur a pu monter dans un train
     * @param voyageur
     * @return boolean
     */
    public synchronized boolean VoyageurMonteTrain(Voyageur voyageur){
        /*déclarer un iterator sur la liste des trains*/
        Iterator<Train> iterator = listTrainEspQuai.iterator();
        Boolean monte = false;
        fin:
        while (iterator.hasNext()) {
            Train train = iterator.next();
            /*faire monter le voyageur dans le train*/
            monte = train.prendreVoyageur(voyageur);
            /*sortir si le voyageur a pu monter dans un train*/
            if (monte) {
                break fin;
            }
        }
        return monte;
    }

    /*Getters et setter de la classe EspaceQuai*/
    public ArrayList<Train> getListTrainEspQuai() {
        return listTrainEspQuai;
    }

    public void setListTrainEspQuai(ArrayList<Train> listTrainEspQuai) {
        this.listTrainEspQuai = listTrainEspQuai;
    }

    public ArrayList<Voyageur> getListVoyAQaui() {
        return listVoyAQaui;
    }

    public void setListVoyAQaui(ArrayList<Voyageur> listVoyAQaui) {
        this.listVoyAQaui = listVoyAQaui;
    }

   }
