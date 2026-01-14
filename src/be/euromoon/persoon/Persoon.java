package be.euromoon.persoon;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * Deze klasse representeert een Persoon.
 * Het stemt af van Personeelslid en Persoon, wordt enkel gebruikt in EuromoonApp en dient om een makkelijk onderscheiding te maken tussen alle types personeelsleden.
 */
public class Persoon implements Serializable {

    private final String voornaam;
    private final String achternaam;
    private final String rijksregisternummer;
    private final LocalDate geboortedatum;

    /**
     * Maakt een Persoon object aan
     * @param voornaam een String met de voornaam
     * @param achternaam een String met de achternaam
     * @param rijksregisternummer een String met rijksregisternummer
     * @param geboortedatum een LocalDate met de geboortedatum
     */
    public Persoon(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.rijksregisternummer = rijksregisternummer;
        this.geboortedatum = geboortedatum;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getRijksregisternummer() {
        return rijksregisternummer;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }



    @Override
    public String toString() {
        return
                "voornaam = " + voornaam +
                ", achternaam = " + achternaam +
                ", rijksregisternummer = " + rijksregisternummer +
                ", geboortedatum = " + geboortedatum +
                '}';
    }
}
