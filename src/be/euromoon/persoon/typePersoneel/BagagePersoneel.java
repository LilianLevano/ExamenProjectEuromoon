package be.euromoon.persoon.typePersoneel;

import be.euromoon.persoon.Personeelslid;

import java.time.LocalDate;
import java.util.ArrayList;

import static be.euromoon.EuromoonApp.GEEL;
import static be.euromoon.EuromoonApp.RESET;

public class BagagePersoneel extends Personeelslid {
    public BagagePersoneel(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);
    }



    public static void toonBagagePersoneel(ArrayList<Personeelslid> bagagePersoneel) {

        if (!bagagePersoneel.isEmpty()) {
            System.out.println("\n\tLijst Bagage Personeel: ");

            for (Personeelslid pl : bagagePersoneel) {
                System.out.println(
                        GEEL + "\t\tBagage personeel" + bagagePersoneel.indexOf(pl) + ":" + RESET +
                                "\n\t\t\tVoornaam bagage personeel: " + pl.getVoornaam() +
                                "\n\t\t\tAchternaam bagage personeel: " + pl.getAchternaam() +
                                "\n\t\t\tRijksregisternummer van de bagage personeel: " + pl.getRijksregisternummer() +
                                "\n\t\t\tGeboortedatum van de bagage personeel: " + pl.getGeboortedatum() +
                                "\n\t\t\tCertificaties: " + pl.getLijstCertificaties()
                );
            }
        }


    }

}
