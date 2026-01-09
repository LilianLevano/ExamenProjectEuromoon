package be.euromoon.reizen;

import be.euromoon.persoon.Personeelsleed;
import be.euromoon.trein.Trein;

import java.util.ArrayList;

public class Reis {

    private Traject traject;
    private Tijdstip tijdstip;
    private Trein trein;
    private ArrayList<Personeelsleed> lijstPersoneelsleden = new ArrayList<>();
    private ArrayList<Personeelsleed> lijstBestuurder = new ArrayList<>();
    private ArrayList<Personeelsleed> lijstSteward = new ArrayList<>();
    private static int reisTeller = 0;


    public Reis(Traject traject, Tijdstip tijdstip) {
        this.traject = traject;
        this.tijdstip = tijdstip;
        reisTeller++;
    }

    public void koppelTreinAanReis(Trein trein) {
        this.trein = trein;
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
