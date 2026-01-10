package be.euromoon.persoon.typePersoneel;

import be.euromoon.persoon.Personeelslid;

import java.time.LocalDate;

public class BagagePersoneel extends Personeelslid {
    public BagagePersoneel(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);
    }
}
