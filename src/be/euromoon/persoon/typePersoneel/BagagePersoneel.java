package be.euromoon.persoon.typePersoneel;

import be.euromoon.persoon.Personeelslid;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


import static be.euromoon.EuromoonApp.GEEL;
import static be.euromoon.EuromoonApp.RESET;


/**
 * Deze klasse representeert een Bagage Personeel.
 * Het stamt af van Personeelslid en Persoon, wordt enkel gebruikt in EuromoonApp en dient om een makkelijk onderscheiding te maken tussen alle types personeelsleden.
 */

public class BagagePersoneel extends Personeelslid implements Serializable {


    /**
     * Maakt een BagagePersoneel object aan
     * @param voornaam een String met de voornaam
     * @param achternaam een String met de achternaam
     * @param rijksregisternummer een String met rijksregisternummer
     * @param geboortedatum een LocalDate met de geboortedatum
     */
    public BagagePersoneel(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);
    }


    /**
     * Deze functie print alle objecten BagagePersoneel in een ArrayList af.
     * Gebruikt de getters van de klasse Persoon om zijn gegevens af te printen
     *
     * @param bagagePersoneel Een ArrayList die enkel Personeelslid objecten accepteert is vereist om deze functie te gebruiken.
     *
     */
    public static void toonBagagePersoneel(ArrayList<Personeelslid> bagagePersoneel) {

        if (!bagagePersoneel.isEmpty()) {
            System.out.println("\n\tLijst Bagage Personeel: ");

            for (Personeelslid pl : bagagePersoneel) {
                System.out.println(
                        GEEL + "\t\tBagage personeel" + bagagePersoneel.indexOf(pl) + ":" + RESET +
                                "\n\t\t\tVoornaam bagage personeel: " + pl.getVoornaam() +
                                "\tAchternaam bagage personeel: " + pl.getAchternaam() +
                                "\n\t\t\tRijksregisternummer van de bagage personeel: " + pl.getRijksregisternummer() +
                                "\n\t\t\tGeboortedatum van de bagage personeel: " + pl.getGeboortedatum() +
                                "\n\t\t\tCertificaties: " + pl.getLijstCertificaties()
                );
            }
        }


    }

}
