package be.euromoon.persoon;

import java.time.LocalDate;

public class Passagier extends Persoon{

    private static int passagierTeller = 0;

    public Passagier(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);
        passagierTeller++;
    }

    public static int getPassagierTeller() {
        return passagierTeller;
    }

    @Override
    public String toString() {
        return "Passagier{" + super.toString() + "}";
    }
}
