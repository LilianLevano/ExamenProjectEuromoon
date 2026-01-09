package be.euromoon.reizen;

import be.euromoon.persoon.Personeelsleed;
import be.euromoon.trein.Trein;

import java.util.ArrayList;

public class Reizen {

    private Traject traject;
    private Tijdstip tijdstip;
    private Trein trein;
    private ArrayList<Personeelsleed> lijstPersoneelsleden = new ArrayList<>();
    private ArrayList<Personeelsleed> lijstBestuurder = new ArrayList<>();
    private ArrayList<Personeelsleed> lijstSteward = new ArrayList<>();


    public Reizen(Traject traject, Tijdstip tijdstip, Trein trein) {
        this.traject = traject;
        this.tijdstip = tijdstip;
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
        return "Reizen{" +
                "traject = " + traject +
                ", tijdstip = " + tijdstip +
                ", trein = " + trein +
                ", lijstPersoneelsleden = " + lijstPersoneelsleden +
                ", lijstBestuurder = " + lijstBestuurder +
                ", lijstSteward = " + lijstSteward +
                '}';
    }
}
