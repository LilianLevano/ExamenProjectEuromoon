package be.euromoon.persoon.typePersoneel;

import be.euromoon.persoon.Personeelslid;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import static be.euromoon.EuromoonApp.GEEL;
import static be.euromoon.EuromoonApp.RESET;

/**
 * Deze klasse representeert een Steward.
 * Het stamt af van Personeelslid en Persoon, wordt enkel gebruikt in EuromoonApp en dient om een makkelijk onderscheiding te maken tussen alle types personeelsleden.
 */
public class Steward extends Personeelslid implements Serializable {


    /**
     * Maakt een Steward object aan
     * @param voornaam een String met de voornaam
     * @param achternaam een String met de achternaam
     * @param rijksregisternummer een String met rijksregisternummer
     * @param geboortedatum een LocalDate met de geboortedatum
     */
    public Steward(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);
    }


    /**
     * Functie om alle stewards in een Arraylist af te printen.
     * Gebruikt de getters van de klasse Persoon om zijn gegevens af te printen
     * @param lijstSteward Een ArrayList die Personeelslid objecten accepteert
     */
    public static void toonSteward(ArrayList<Personeelslid> lijstSteward) {

        for (Personeelslid s : lijstSteward) {
            System.out.println(
                    GEEL + "\t\tSteward" + lijstSteward.indexOf(s) + ":" + RESET +
                            "\n\t\t\tVoornaam steward: " + s.getVoornaam() +
                            "\tAchternaam steward: " + s.getAchternaam() +
                            "\n\t\t\tRijksregisternummer van de steward: " + s.getRijksregisternummer() +
                            "\n\t\t\tGeboortedatum van de steward: " + s.getGeboortedatum() +
                            "\n\t\t\tCertificaties: " + s.getLijstCertificaties())
            ;
        }
    }
}
