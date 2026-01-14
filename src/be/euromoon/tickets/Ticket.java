package be.euromoon.tickets;

import be.euromoon.persoon.Passagier;
import be.euromoon.reizen.Reis;

import java.io.Serializable;



/**
 * Deze klasse representeert een ticket voor een reis. Een ticket bevat een reis, een klasse en een passagier.
 */
public class Ticket implements Serializable {

    private Passagier passagier;
    private final Reis reis;
    private Klasse klasse;



    /**
     * Maakt een object Ticket aan
     * @param passagier een object Passagier
     * @param reis een object Reis
     * @param klasse een waarde uit de enumeratie Klasse
     */
    public Ticket(Passagier passagier, Reis reis, Klasse klasse) {
        this.passagier = passagier;
        this.reis = reis;
        this.klasse = klasse;

    }

    public Passagier getPassagier() {
        return passagier;
    }
    public void setPassagier(Passagier passagier) {
        this.passagier = passagier;
    }
    public Klasse getKlasse() {
        return klasse;
    }
    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }

    /**
     * Deze functie print print voor 1 passagier zijn gegevens af.
     * Het werd anders geformatteerd om het proper te krijgen bij het wegschrijven naar een extern bestand.
     * Werkt in combinatie van een for each buiten deze functie in EuromoonApp.
     * @param i om voor elk passagier een nummer te krijgen om orde te maken in de boarding lijst.
     * @return een String met alle nodige gegevens van een passagier op een reis
     */
    public String toonPassagierVoorTicket(int i, Passagier passagier) {

        return "\nPassagier" + i + ":" +
                "\nVoornaam passagier: " + passagier.getVoornaam() +
                "\nAchternaam passagier: " +passagier.getAchternaam() +
                "\nRijksregisternummer van de passagier: " + passagier.getRijksregisternummer() +
                "\nGeboortedatum van de passagier: " + passagier.getGeboortedatum() +
                "\nKlasse van de passagier: " + getKlasse() +
                "\n";
    }


    @Override
    public String toString() {
        return "Tickets{" +
                passagier +
                ", "+ reis +
                ", " + klasse +
                '}';
    }
}
