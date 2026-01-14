package be.euromoon.tickets;

import be.euromoon.persoon.Passagier;
import be.euromoon.reizen.Reis;

import java.io.Serializable;



/**
 * Deze klasse representeert een ticket. Een ticket bevat een reis, een klasse en een passagier. Wordt gebruikt om een boarding lijst af te drukken.
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


    @Override
    public String toString() {
        return "Tickets{" +
                passagier +
                ", "+ reis +
                ", " + klasse +
                '}';
    }
}
