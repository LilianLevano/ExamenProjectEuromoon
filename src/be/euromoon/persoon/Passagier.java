package be.euromoon.persoon;

import be.euromoon.tickets.Klasse;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import static be.euromoon.EuromoonApp.GEEL;
import static be.euromoon.EuromoonApp.RESET;

public class Passagier extends Persoon implements Serializable {


    private Klasse klasse;

    public Passagier(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);

    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }

    public String getKlasse() {
        return klasse.name().toLowerCase();
    }

    public static void toonPassagier(ArrayList<Passagier> passagier){

        for (Passagier p : passagier) {
            System.out.println(
                    GEEL + "\nPassagier" + passagier.indexOf(p) + ":" + RESET +
                            "\nVoornaam passagier: " + p.getVoornaam() +
                            "\nAchternaam passagier: " + p.getAchternaam() +
                            "\nRijksregisternummer van de passagier: " + p.getRijksregisternummer() +
                            "\nGeboortedatum van de passagier: " + p.getGeboortedatum() +
                            "\n");
        }
    }

    public String toonPassagierVoorTicket(int i){

        return "\nPassagier" + i + ":" +
                "\nVoornaam passagier: " + getVoornaam() +
                "\nAchternaam passagier: " + getAchternaam() +
                "\nRijksregisternummer van de passagier: " + getRijksregisternummer() +
                "\nGeboortedatum van de passagier: " + getGeboortedatum() +
                "\nKlasse van de passagier: " + getKlasse() +
                "\n";
    }



    @Override
    public String toString() {
        return "Passagier{" + super.toString() + "}";
    }
}
