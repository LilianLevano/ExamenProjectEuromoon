package be.euromoon.tickets;

import be.euromoon.persoon.Passagier;
import be.euromoon.reizen.Reis;

public class Ticket {

    private Passagier passagier;
    private Reis reis;
    private Klasse klasse;
    private static int ticketTeller = 0;

    public Ticket(Passagier passagier, Reis reis, Klasse klasse) {
        this.passagier = passagier;
        this.reis = reis;
        this.klasse = klasse;
        ticketTeller++;
    }

    public Passagier getPassagier() {
        return passagier;
    }
    public void setPassagier(Passagier passagier) {
        this.passagier = passagier;
    }
    public Reis getReizen() {
        return reis;
    }
    public void setReizen(Reis reis) {
        this.reis = reis;
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
                ", klasse = " + klasse +
                '}';
    }
}
