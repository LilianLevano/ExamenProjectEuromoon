package be.euromoon.persoon.typePersoneel;

import be.euromoon.persoon.Personeelslid;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import static be.euromoon.EuromoonApp.GEEL;
import static be.euromoon.EuromoonApp.RESET;

/**
 * Deze klasse representeert een Bestuurder.
 * Het stamt af van Personeelslid en Persoon, wordt enkel gebruikt in EuromoonApp en dient om een makkelijk onderscheiding te maken tussen alle types personeelsleden.
 */
public class Bestuurder extends Personeelslid implements Serializable {

    /**
     * Maakt een Bestuurder object aan
     * @param voornaam een String met de voornaam
     * @param achternaam een String met de achternaam
     * @param rijksregisternummer een String met rijksregisternummer
     * @param geboortedatum een LocalDate met de geboortedatum
     */
    public Bestuurder(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);
    }


    /**
     * Functie om alle bestuurders in een Arraylist af te printen.
     * Gebruikt de getters van de klasse Persoon om zijn gegevens af te printen
     * @param bestuurder Een ArrayList die Personeelslid objecten accepteert
     */
    public static void toonBestuurder(ArrayList<Personeelslid> bestuurder) {

        for (Personeelslid b : bestuurder) {
            System.out.println(
                    GEEL + "\t\tBestuurder" + bestuurder.indexOf(b) + ":" + RESET +
                            "\n\t\t\tVoornaam bestuurder: " + b.getVoornaam() +
                            "\tAchternaam bestuurder: " + b.getAchternaam() +
                            "\n\t\t\tRijksregisternummer van de bestuurder: " + b.getRijksregisternummer() +
                            "\n\t\t\tGeboortedatum van de bestuurder: " + b.getGeboortedatum() +
                            "\n\t\t\tCertificaties: " + b.getLijstCertificaties());

        }
    }

}
