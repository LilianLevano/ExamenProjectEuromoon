package be.euromoon.persoon;

import java.time.LocalDate;
import java.util.ArrayList;

public class Personeelsleed extends Persoon{

  private ArrayList<String> lijstCertificaties = new ArrayList<>();
  private ArrayList<String> lijstVasteRol =  new ArrayList<>();
  private static int personeelsleedTeller = 0;


  private String rol;

    public Personeelsleed(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum, String rol) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);
        this.rol = rol;

        lijstVasteRol.add("Conducteur");
        lijstVasteRol.add("Steward");
        lijstVasteRol.add("Bagage Personeel");
        personeelsleedTeller++;

    }



    public void voegCertificatie(String certificatie){
        lijstCertificaties.add(certificatie);
    }

    public void verwijderCertificatie(String certificatie){
        lijstCertificaties.remove(certificatie);
    }

    public ArrayList<String> getLijstVasteRol() {
        return lijstVasteRol;
    }

    public String getRol() {
        return rol;
    }

    @Override
    public String toString() {
        return "Personeelsleed{" + super.toString() +
                ", rol = " + rol +
                ", lijstCertificaties = " + lijstCertificaties +
                "} ";
    }
}
