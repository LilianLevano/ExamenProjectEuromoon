package be.euromoon.reizen;

import be.euromoon.persoon.Personeelsleed;
import be.euromoon.trein.Trein;

import java.util.ArrayList;

public class Reis {

    public static final String GROEN = "\u001B[32m";
    public static final String GEEL = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    private Traject traject;
    private Tijdstip tijdstip;
    private Trein trein;
    private final ArrayList<Personeelsleed> lijstPersoneelsleden = new ArrayList<>();
    private final ArrayList<Personeelsleed> lijstBestuurder = new ArrayList<>();
    private final ArrayList<Personeelsleed> lijstSteward = new ArrayList<>();
    private static int reisTeller = 0;
    private int ticketTeller = 0;


    public Reis(Traject traject, Tijdstip tijdstip) {
        this.traject = traject;
        this.tijdstip = tijdstip;
        reisTeller++;
    }

    public static String getReisTeller() {
        return "Reis" + reisTeller;
    }


    public ArrayList<Personeelsleed> getLijstSteward() {
        return lijstSteward;
    }

    public ArrayList<Personeelsleed> getLijstPersoneelsleden() {
        return lijstPersoneelsleden;
    }

    public ArrayList<Personeelsleed> getLijstBestuurder() {
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

    public void voegPersoneelToe(Personeelsleed personeelsleden) {
        lijstPersoneelsleden.add(personeelsleden);
    }

    public void voegBestuurderToe(Personeelsleed bestuurder) {
        lijstBestuurder.add(bestuurder);
    }

    public void voegStewardToe(Personeelsleed steward) {
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
