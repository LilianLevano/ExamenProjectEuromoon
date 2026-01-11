package be.euromoon.persoon;

import java.time.LocalDate;
import java.util.ArrayList;

import static be.euromoon.EuromoonApp.GEEL;
import static be.euromoon.EuromoonApp.RESET;

public class Passagier extends Persoon{



    public Passagier(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);

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



    @Override
    public String toString() {
        return "Passagier{" + super.toString() + "}";
    }
}
