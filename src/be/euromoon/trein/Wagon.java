package be.euromoon.trein;

import be.euromoon.persoon.Passagier;

import java.util.ArrayList;

import static be.euromoon.EuromoonApp.GEEL;
import static be.euromoon.EuromoonApp.RESET;

public class Wagon {

    private static int wagonTeller;
    private ArrayList<Passagier> lijstPassagier = new ArrayList<>();
    private int maxAantalPassagierPerWagon;

    public Wagon() {
        wagonTeller++;
        this.maxAantalPassagierPerWagon = 80;
    }

    public static int getWagonTeller() {
        return wagonTeller;
    }

    public void voegPassagierToeAanWagon(Passagier p){
        lijstPassagier.add(p);
    }

    public ArrayList<Passagier> getLijstPassagier() {
        return lijstPassagier;
    }

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
