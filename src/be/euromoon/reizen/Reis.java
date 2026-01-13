package be.euromoon.reizen;

import be.euromoon.persoon.Passagier;
import be.euromoon.persoon.Personeelslid;
import be.euromoon.trein.Trein;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Deze klasse representeert een reis. Een reis bevat een traject, een tijdstip, en lijsten met alle beschikbare types personen.
 * Wordt gebruikt om een boarding lijst af te printen in een extern bestand.
 */
public class Reis implements Serializable {


    private final Traject traject;
    private final Tijdstip tijdstip;
    private Trein trein;
    private final ArrayList<Personeelslid> lijstPersoneelsleden = new ArrayList<>();
    private final ArrayList<Personeelslid> lijstBestuurder = new ArrayList<>();
    private final ArrayList<Personeelslid> lijstSteward = new ArrayList<>();
    private final ArrayList<Passagier> lijstPassagierMetTicket = new ArrayList<>();

    private int ticketTeller = 0;


    /**
     * Maakt een Reis object aan
     * @param traject een object Traject met een aankomst- en eindpunt
     * @param tijdstip een object Tijdstip met een aankomst- en einduur
     */
    public Reis(Traject traject, Tijdstip tijdstip) {
        this.traject = traject;
        this.tijdstip = tijdstip;

    }




    public ArrayList<Passagier> getLijstPassagierMetTicket() {
        return lijstPassagierMetTicket;
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
    public Trein getTrein() {
        return trein;
    }
    public void koppelTreinAanReis(Trein trein) {
        this.trein = trein;
    }


    /**
     * Functie om de grootte van de lijst stewards te krijgen.
     * Gebruikt om de minimale aantal stewards te checken.
     * @return een int van de grootte van lijstSteward
     */
    public int sizeLijstSteward() {
        return lijstSteward.size();
    }


    /**
     * Functie om een personeelslid toe te voegen aan de lijst van personeelsleden.
     * @param personeelsleden een object Personeelslid
     */
    public void voegPersoneelToe(Personeelslid personeelsleden) {
        lijstPersoneelsleden.add(personeelsleden);
    }


    /**
     * Functie om een personeelslid toe te voegen aan de lijst van bestuurders.
     * @param bestuurder een object Personeelslid
     */
    public void voegBestuurderToe(Personeelslid bestuurder) {
        lijstBestuurder.add(bestuurder);
    }


    /**
     * Functie om een personeelslid toe te voegen aan de lijst van stewards.
     * @param steward een object Personeelslid
     */
    public void voegStewardToe(Personeelslid steward) {
        lijstSteward.add(steward);
    }


    /**
     * Functie om aan te duiden dat een ticket werd aangemaakt aan een reis.
     * Gebruikt zodat aantal tickets niet groter wordt dan het aantal zitplaatsen.
     */

    public void ticketGemaakt(){
        this.ticketTeller++;
    }

    /**
     * @return een int van het aantal ticket per reis
     */
    public int getTicketTeller() {
        return ticketTeller;
    }


    /**
     * Functie om een passagier toe te voegen aan de lijst van passagiers.
     * @param p een object Passagier
     */
    public void voegPersoonMetTicketToe(Passagier p){
        lijstPassagierMetTicket.add(p);
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