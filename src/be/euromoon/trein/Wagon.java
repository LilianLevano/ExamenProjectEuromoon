package be.euromoon.trein;

import be.euromoon.persoon.Passagier;

import java.io.Serializable;
import java.util.ArrayList;

import static be.euromoon.EuromoonApp.GEEL;
import static be.euromoon.EuromoonApp.RESET;

/**
 * Deze klasse representeert een wagon. Elke wagon kan een aantal (max. 80) passagieren houden.
 */
public class Wagon implements Serializable {


    private final ArrayList<Passagier> lijstPassagier = new ArrayList<>();

    /**
     * Maakt een object Wagon aan. Neemt geen parameters mee in het constructor
     */
    public Wagon() {
    }


    /**
     * Functie om een Passagier object toe te voegen in een wagon.
     * @param p een object Passagier
     */
    public void voegPassagierToeAanWagon(Passagier p){
        lijstPassagier.add(p);
    }

    public ArrayList<Passagier> getLijstPassagier() {
        return lijstPassagier;
    }

    /**
     * Functie om alle passagieren in een wagon af te printen.
     */
    public void toonPassagierInWagon(){

        for ( Passagier p : lijstPassagier){
            System.out.println(
                    GEEL + "\tPassagier" + lijstPassagier.indexOf(p) + ":" + RESET +
                            "\n\t\tVoornaam passagier: " + p.getVoornaam() +
                            "\n\t\tAchternaam passagier: " + p.getAchternaam() +
                            "\n\t\tRijksregisternummer van de passagier: " + p.getRijksregisternummer() +
                            "\n\t\tGeboortedatum van de passagier: " + p.getGeboortedatum() +
                            "\n");
        }
    }
}
