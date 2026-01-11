package be.euromoon.persoon;

import java.time.LocalDate;
import java.util.ArrayList;

import static be.euromoon.EuromoonApp.GEEL;
import static be.euromoon.EuromoonApp.RESET;

public class Persoon {

    private final String voornaam;
    private final String achternaam;
    private final String rijksregisternummer;
    private final LocalDate geboortedatum;

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
