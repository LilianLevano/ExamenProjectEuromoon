package be.euromoon.persoon;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Personeelslid extends Persoon implements Serializable {

    private final ArrayList<String> lijstCertificaties = new ArrayList<>();






    public Personeelslid(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);


    }



    public ArrayList<String> getLijstCertificaties() {
        return lijstCertificaties;
    }

    public void voegCertificatie(String certificatie){
        lijstCertificaties.add(certificatie);
    }

//    public void verwijderCertificatie(String certificatie){
//        lijstCertificaties.remove(certificatie);
//    }





    @Override
    public String toString() {
        return "Personeelsleed{" + super.toString() +

                ", lijstCertificaties = " + lijstCertificaties +
                "} ";
    }
}