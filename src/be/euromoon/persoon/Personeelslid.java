package be.euromoon.persoon;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Deze klasse representeert een Personeelslid.
 * Het stamt af van Persoon, wordt gebruikt als basis voor alle andere types personeelsleden en om ze certificaties toe te voegen.
 */
public class Personeelslid extends Persoon implements Serializable {

    private final ArrayList<String> lijstCertificaties = new ArrayList<>();


    /**
     * Maakt een Personeelslid object aan
     * @param voornaam een String met de voornaam
     * @param achternaam een String met de achternaam
     * @param rijksregisternummer een String met rijksregisternummer
     * @param geboortedatum een LocalDate met de geboortedatum
     */
    public Personeelslid(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);
    }

    public ArrayList<String> getLijstCertificaties() {
        return lijstCertificaties;
    }

    /**
     * Functie om een certificatie (String) toe te voegen aan een lijst van certificaties.
     * Werkt in combinatie met een do while buiten deze functie in EuromoonApp
     *
     * @param certificatie een String met een certificatie
     */
    public void voegCertificatie(String certificatie){
        lijstCertificaties.add(certificatie);
    }


    @Override
    public String toString() {
        return "Personeelsleed{" + super.toString() +

                ", lijstCertificaties = " + lijstCertificaties +
                "} ";
    }
}