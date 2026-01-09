package be.euromoon.persoon;

import java.time.LocalDate;

public class Persoon {

    private String voornaam, achternaam, rijksregisternummer;
    private LocalDate geboortedatum;

    public Persoon(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.rijksregisternummer = rijksregisternummer;
        this.geboortedatum = geboortedatum;
    }

    public String getVoornaam() {
        return voornaam;
    }
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }
    public String getAchternaam() {
        return achternaam;
    }
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }
    public String getRijksregisternummer() {
        return rijksregisternummer;
    }
    public void setRijksregisternummer(String rijksregisternummer) {
        this.rijksregisternummer = rijksregisternummer;
    }
    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }
    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
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
