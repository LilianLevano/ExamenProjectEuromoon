package be.euromoon.persoon.typePersoneel;

import be.euromoon.persoon.Personeelslid;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import static be.euromoon.EuromoonApp.GEEL;
import static be.euromoon.EuromoonApp.RESET;

public class Bestuurder extends Personeelslid implements Serializable {
    public Bestuurder(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);
    }

    public static void toonBestuurder(ArrayList<Personeelslid> bestuurder) {

        for (Personeelslid b : bestuurder) {
            System.out.println(
                    GEEL + "\t\tBestuurder" + bestuurder.indexOf(b) + ":" + RESET +
                            "\n\t\t\tVoornaam bestuurder: " + b.getVoornaam() +
                            "\n\t\t\tAchternaam bestuurder: " + b.getAchternaam() +
                            "\n\t\t\tRijksregisternummer van de bestuurder: " + b.getRijksregisternummer() +
                            "\n\t\t\tGeboortedatum van de bestuurder: " + b.getGeboortedatum() +
                            "\n\t\t\tCertificaties: " + b.getLijstCertificaties());

        }
    }

}
