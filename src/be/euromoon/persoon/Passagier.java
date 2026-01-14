package be.euromoon.persoon;

import be.euromoon.tickets.Klasse;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import static be.euromoon.EuromoonApp.GEEL;
import static be.euromoon.EuromoonApp.RESET;

/**
 * Deze klasse representeert een Passagier.
 * Het stamt af van Personeelslid en Persoon, wordt enkel gebruikt in EuromoonApp en dient om een makkelijk onderscheiding te maken tussen alle types personeelsleden.
 * Aan een passagier kan een (trein) klasse toegevoegd worden om de klasse van een object te krijgen tijdens het drukken van een boarding lijst.
 */
public class Passagier extends Persoon implements Serializable {


    private Klasse klasse;

    /**
     * Maakt een Passagier object aan
     * @param voornaam een String met de voornaam
     * @param achternaam een String met de achternaam
     * @param rijksregisternummer een String met rijksregisternummer
     * @param geboortedatum een LocalDate met de geboortedatum
     */
    public Passagier(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);

    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }

    /**

     * @return Waarde van de enum Klasse in lowercase
     */
    public String getKlasse() {
        return klasse.name().toLowerCase();
    }

    /**
     * Deze functie print alle objecten Passagier in een ArrayList af.
     * Gebruikt de getters van de klasse Persoon om zijn gegevens af te printen
     *
     * @param passagier Een ArrayList die enkel Passagier objecten accepteert is vereist om deze functie te gebruiken.
     *
     */
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

    /**
     * Deze functie print print voor 1 passagier zijn gegevens af.
     * Het werd anders geformatteerd om het proper te krijgen bij het wegschrijven naar een extern bestand.
     * Werkt in combinatie van een for each buiten deze functie in EuromoonApp.
     * @param i om voor elk passagier een nummer te krijgen om orde te maken in de boarding lijst.
     * @return een String met alle nodige gegevens van een passagier op een reis
     */
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
