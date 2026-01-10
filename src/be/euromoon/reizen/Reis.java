package be.euromoon.reizen;

import be.euromoon.persoon.Personeelslid;
import be.euromoon.trein.Trein;

import java.util.ArrayList;

public class Reis {


    private final Traject traject;
    private final Tijdstip tijdstip;
    private Trein trein;
    private final ArrayList<Personeelslid> lijstPersoneelsleden = new ArrayList<>();
    private final ArrayList<Personeelslid> lijstBestuurder = new ArrayList<>();
    private final ArrayList<Personeelslid> lijstSteward = new ArrayList<>();

    private int ticketTeller = 0;


    public Reis(Traject traject, Tijdstip tijdstip) {
        this.traject = traject;
        this.tijdstip = tijdstip;

    }




    public ArrayList<Personeelslid> getLijstSteward() {
        return lijstSteward;
    }

    public ArrayList<Personeelslid> getLijstPersoneelsleden() {
        return lijstPersoneelsleden;
    }

    public ArrayList<Personeelslid> getLijstBestuurder() {
        return lijstBestuurder;
    }

    public Traject getTraject() {
        return traject;
    }

    public Tijdstip getTijdstip() {
        return tijdstip;
    }

    public void koppelTreinAanReis(Trein trein) {
        this.trein = trein;
    }

    public int sizeLijstSteward() {
        return lijstSteward.size();
    }

    public void voegPersoneelToe(Personeelslid personeelsleden) {
        lijstPersoneelsleden.add(personeelsleden);
    }

    public void voegBestuurderToe(Personeelslid bestuurder) {
        lijstBestuurder.add(bestuurder);
    }

    public void voegStewardToe(Personeelslid steward) {
        lijstSteward.add(steward);
    }

    public void ticketGemaakt(){
        this.ticketTeller++;
    }

    public int getTicketTeller() {
        return ticketTeller;
    }

    public Trein getTrein() {
        return trein;
    }

    @Override
    public String toString() {
        return "Reis{" +
                traject +
                ", " + tijdstip +
                ", " + trein +
                ", lijstPersoneelsleden = " + lijstPersoneelsleden +
                ", lijstBestuurder = " + lijstBestuurder +
                ", lijstSteward = " + lijstSteward +
                '}';
    }
}