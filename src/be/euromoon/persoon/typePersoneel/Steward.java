package be.euromoon.persoon.typePersoneel;

import be.euromoon.persoon.Personeelslid;

import java.time.LocalDate;
import java.util.ArrayList;

import static be.euromoon.EuromoonApp.GEEL;
import static be.euromoon.EuromoonApp.RESET;

public class Steward extends Personeelslid {
    public Steward(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);
    }

    public static void toonSteward(ArrayList<Personeelslid> lijstSteward) {

        for (Personeelslid s : lijstSteward) {
            System.out.println(
                    GEEL + "\t\tSteward" + lijstSteward.indexOf(s) + ":" + RESET +
                            "\n\t\t\tVoornaam steward: " + s.getVoornaam() +
                            "\n\t\t\tAchternaam steward: " + s.getAchternaam() +
                            "\n\t\t\tRijksregisternummer van de steward: " + s.getRijksregisternummer() +
                            "\n\t\t\tGeboortedatum van de steward: " + s.getGeboortedatum() +
                            "\n\t\t\tCertificaties: " + s.getLijstCertificaties())
            ;
        }
    }
}
