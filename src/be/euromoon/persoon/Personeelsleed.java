package be.euromoon.persoon;

import java.time.LocalDate;
import java.util.ArrayList;

public class Personeelsleed extends Persoon{

  private ArrayList<String> lijstCertificaties = new ArrayList<>();
  private TypePersoneel vasteRol;
  private String andereRol;

    public Personeelsleed(String voornaam, String achternaam, String rijksregisternummer, LocalDate geboortedatum, TypePersoneel vasteRol) {
        super(voornaam, achternaam, rijksregisternummer, geboortedatum);
        this.vasteRol = vasteRol;
    }



    public void voegCertificaties(String certificatie){
        lijstCertificaties.add(certificatie);
    }

    public String getVasteRol() {
        return vasteRol.getRol();
    }
    public void setVasteRol(TypePersoneel vasteRol) {
        this.vasteRol = vasteRol;
    }
    public String getAndereRol() {
        return andereRol;
    }
    public void setAndereRol(String andereRol) {
        this.andereRol = andereRol;
    }


}
