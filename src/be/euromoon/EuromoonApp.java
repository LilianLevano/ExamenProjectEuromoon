package be.euromoon;

import be.euromoon.persoon.Passagier;
import be.euromoon.persoon.Personeelslid;
import be.euromoon.persoon.Persoon;
import be.euromoon.persoon.TypePersoon;
import be.euromoon.persoon.typePersoneel.BagagePersoneel;
import be.euromoon.persoon.typePersoneel.Bestuurder;
import be.euromoon.persoon.typePersoneel.Steward;
import be.euromoon.reizen.Reis;
import be.euromoon.reizen.Tijdstip;
import be.euromoon.reizen.Traject;
import be.euromoon.tickets.Klasse;
import be.euromoon.tickets.Ticket;
import be.euromoon.trein.Trein;
import be.euromoon.trein.TypeLocomotief;
import be.euromoon.trein.Wagon;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static be.euromoon.persoon.Passagier.toonPassagier;
import static be.euromoon.persoon.typePersoneel.BagagePersoneel.toonBagagePersoneel;
import static be.euromoon.persoon.typePersoneel.Bestuurder.toonBestuurder;
import static be.euromoon.persoon.typePersoneel.Steward.toonSteward;

/**
 * EuromoonApp is de klasse die de hele applicatie bevat. Daarvan worden alle acties uitgevoerd die nodig zijn voor Euromoon.
 */
public class EuromoonApp {

    public static final String GROEN = "\u001B[32m";
    public static final String GEEL = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    Scanner sc = new Scanner(System.in);
    public static final ArrayList<Passagier> lijstPassagier = new ArrayList<>();
    private final ArrayList<Reis> lijstReis = new ArrayList<>();
    private final ArrayList<Ticket> lijstTicket = new ArrayList<>();
    private final ArrayList<Reis> lijstReisMetTrein = new ArrayList<>();
    private final ArrayList<Reis> lijstGeldigeReizenVoorWegschrijven = new ArrayList<>();



    /**
     * Start de applicatie met een keuzemenu die aparte functies oproept om de applicatie te gebruiken.
     */
    public void start() {
        System.out.println(GEEL + zetStreepjes(15) + "Welkom op de Euromoon Manager App" + zetStreepjes(15)+ RESET );

        System.out.println("Om deze app efficient te gebruiken, wordt het aangeraden om het console venster groot te maken. \nKritische informatie over het gebruiken van de verschillende opties worden altijd bovenaan getoond.\n");


        int keuzeMenu;

        do {
            System.out.println("""
                    \nMaak een keuze: \
                    
                    1. Registreer een nieuwe passagier \
                    
                    2. Maak een nieuwe reis \
                    
                    3. Koppel een trein aan een bestaande reis \
                    
                    4. Verkoop een ticket aan een passagier \
                    
                    5. Druk de boardinglijst van een specifieke reis af \
                    
                    9. Stop de applicatie.""");
            System.out.print("--> ");
            keuzeMenu = Integer.parseInt(sc.nextLine());

            switch (keuzeMenu) {
                case 1:
                    passagierRegistreren();
                    break;
                case 2:
                    maakReis();
                    break;
                case 3:
                    treinAanReisKoppelen();
                    break;
                case 4:
                    verkoopTicketAanPassagier();
                    break;
                case 5:
                    ticketWegschrijvenInBestand();
                    break;

            }
        } while (keuzeMenu != 9);

    }



    // -----------------FUNCTIES VOOR KEUZE MENU ----------------- //


    /**
     * Optie om een passagier te registreren. Gebruikt de functie maakPersoon om een Persoon aan te maken die dan wordt gecast naar een object Passagier. Uiteindelijk komt het in de ArrayList lijstPassagier
     */
    private void passagierRegistreren() {
        System.out.println(GROEN +"\n"+  zetStreepjes(10) +  "Registreer een passagier" +  zetStreepjes(10) + RESET + "\n");
        try {


            Passagier p = (Passagier) maakPersoon(TypePersoon.PASSAGIER);
            int grootteLijstVoorAanpassing = lijstPassagier.size();

            lijstPassagier.add(p);

            if (grootteLijstVoorAanpassing != lijstPassagier.size()) {
                System.out.println(GROEN + "Passagier werd succesvol aangemaakt" + RESET);
            } else {
                System.err.println("Passagier werd niet aangemaakt.");
            }


        } catch (DateTimeParseException e) {
            System.err.println("Foutieve invoer: Je hebt een foute formaat ingegeven bij het geboortedatum. Passagier werd niet aangemaakt, probeer opnieuw.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        eindOptie();
    }

    /**
     * Optie om een reis te maken.
     * Een object Traject en Tijdstip worden aangemaakt om een reis te maken.
     * Dan worden bestuurders, stewards en bagage personelen toegevoegd aan de hand van functies die een object Personeelslid maken, die dan wordt gecast naar de nodige type personeel.
     * Uiteindelijk wordt het gemaakte reis toegevoegd aan de ArrayList lijstReis
     */
    private void maakReis() {

        System.out.println(GROEN + "\n" + zetStreepjes(15) + "Maak een reis" + zetStreepjes(15) + RESET + "\n");
        try {

            /*Maak een traject, onderdeel om een object Reis te bouwen  */
            System.out.print("Start punt van het traject: ");
            String startpunt = sc.nextLine();
            System.out.print("Eind punt van het traject: ");
            String eindpunt = sc.nextLine();
            Traject t = new Traject(startpunt, eindpunt);

            if (startpunt.isEmpty() || eindpunt.isEmpty()) {
                throw new Exception("Je moet 1 start- en eindpunt hebben om een reis te maken.");
            }

            Tijdstip ti;
            try{
                /*Maak een Tijdstip, onderdeel om een object Reis te bouwen  */
                System.out.print("Startuur van het traject (Formaat: yyyy-MM-ddThh:mm:ss): ");
                LocalDateTime start = LocalDateTime.parse(sc.nextLine());
                System.out.print("Einduur van het traject (Formaat: yyyy-MM-ddThh:mm:ss): ");
                LocalDateTime eind = LocalDateTime.parse(sc.nextLine());
                ti = new Tijdstip(start, eind);
            }catch(DateTimeParseException e){
                throw new Exception("Je hebt bij een van de tijdstip geen goeie formaat ingegeven.");
            }


            Reis r = new Reis(t, ti);


            /*Maak een Bestuurder, er moet er minstens 1 zijn per reis  */
            System.out.println("\n" + GEEL + "Een reis heeft minstens 1 bestuurder nodig." + RESET + "\n");

            Personeelslid verplichteBestuurder =  maakBestuurder();
            r.voegBestuurderToe(verplichteBestuurder);

            String invoerBestuurder;

            do{
                System.out.print("\nWil je een andere bestuurder registreren? (Ja/Nee): ");
                invoerBestuurder = sc.nextLine();

                if (invoerBestuurder.equalsIgnoreCase("Ja")) {
                    Personeelslid bestuurder = maakBestuurder();
                    r.voegBestuurderToe(bestuurder);
                }

            }while(invoerBestuurder.equalsIgnoreCase("Ja"));


            // Maak drie stewards aan, vereiste voor een reis

            System.out.println(GEEL + "\nEen reis heeft een minimum van drie stewards nodig." + RESET);
            int i = 3;
            do {
                System.out.println(GEEL + "\n" +  "Maak een steward aan, nog " + i + " te maken." + "\n" +  RESET);
                Personeelslid steward = maakSteward();
                r.voegStewardToe(steward);
                i--;
            } while (r.sizeLijstSteward() != 3);

            // Maak andere persoonsleden als nodig

            System.out.println(GEEL + "\nEen reis kan bagage personeel bevatten" + RESET);

            String invoer;

            do{
                System.out.print("\nWil je een bagage personeel toevoegen? (Ja/Nee): \n");
                invoer = sc.nextLine();

                if (invoer.equalsIgnoreCase("ja")){

                    Personeelslid bagagePersoneel = maakBagagePersoneel();
                    r.voegPersoneelToe(bagagePersoneel);

                }
            }while (!invoer.equalsIgnoreCase("NEE"));


            int grootteLijstVoorAanpassing = lijstReis.size();
            lijstReis.add(r);

            if (grootteLijstVoorAanpassing != lijstReis.size()) {
                System.out.println(GROEN + "Reis werd succesvol aangemaakt" + RESET);
            } else {
                System.err.println("Reis werd niet aangemaakt.");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage() + " Actie werd gesloten. Probeer opnieuw.");
        }

        eindOptie();

    }

    /**
     * Optie om een trein aan een reis te koppelen.
     * Eerst wordt een type locomotief gekozen om een object Trein aan te maken, trein die dan aan een reis kan toegevoegd worden.
     * Deze type locomotief bepaalt hoeveel plaatsen er per reis aanwezig zijn, en dus hoeveel tickets er per reis mogelijk zijn.
     */
    private void treinAanReisKoppelen() {


        if (!lijstReis.isEmpty()) {


            System.out.println(GROEN + "\n" + zetStreepjes(15) + "Koppel een trein aan een reis" + zetStreepjes(15) + RESET);

            try {
                System.out.println("\nWelke type trein wil je aan een reis koppelen? Je hebt de keuze tussen: ");

                ArrayList<TypeLocomotief> lijstLocomotieven = new ArrayList<>(List.of(TypeLocomotief.values()));

                for (TypeLocomotief t : lijstLocomotieven) {
                    System.out.println(lijstLocomotieven.indexOf(t) +". " + t.name() + ", kan maximaal: " + t.getMaxAantalWagon() + " wagons trekken.");
                }

                System.out.print("Gebruik de index van de gewenste type locomotief om deze te selecteren.\n--> ");

                TypeLocomotief typeLocomotief;
                do {

                    int keuzeTypeTrein;
                    do{
                        keuzeTypeTrein = Integer.parseInt(sc.nextLine());

                        if (keuzeTypeTrein < 0 || keuzeTypeTrein > lijstLocomotieven.size()) {
                            System.err.println("Je hebt geen geldige type locomotief gekozen. Probeer nu opnieuw:");

                        }
                    }while(keuzeTypeTrein < 0 || keuzeTypeTrein > lijstLocomotieven.size());


                    typeLocomotief = lijstLocomotieven.get(keuzeTypeTrein);


                    if (typeLocomotief == null) {
                        System.err.print("Je hebt geen geldige type locomotief gekozen. Probeer nu opnieuw:");
                    }

                } while (typeLocomotief == null);


                Trein trein = new Trein(typeLocomotief);

                System.out.println("\nHier zijn alle mogelijke reizen waaraan je deze trein kan koppelen: ");


                toonReis(lijstReis, true, false);


                System.out.print("Aan welke reis wil je deze trein koppelen? \n(Gebruik de index van de reis.)\n--> ");

                int keuzeReis = Integer.parseInt(sc.nextLine());

                if (keuzeReis < 0 || keuzeReis >= lijstReis.size()) {
                    throw new Exception("Je hebt geen geldige reis gekozen. Deze actie werd volledig gesloten. Probeer opnieuw.");
                }

                Reis reisKoppelenAanTrein = lijstReis.get(keuzeReis);


                reisKoppelenAanTrein.koppelTreinAanReis(trein);
                lijstReisMetTrein.add(reisKoppelenAanTrein);

                System.out.println(GROEN + "Trein werd succesvol gekoppeld aan deze reis." + RESET);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            eindOptie();
        }else{
            System.err.println("Er zijn momenteel geen reizen beschikbaar. Maak er eerst een.");
        }
    }

    /**
     * Optie om een ticket te verkopen aan een passagier.
     * Eerst wordt er een reis gekozen, dan wordt er een al geregistreerde passagier gekozen, uiteindelijk wordt een waarde uit de enumeratie Klasse gekozen om een object Ticket aan te maken.
     * Dit ticket wordt dan toegevoegd aan de lijst van tickets die een reis bevat.
     * Een klasse uit de enumeratie Klasse wordt ook gekozen om ze aan een passagier te koppelen.
     *
     */
    private void verkoopTicketAanPassagier() {

    if (!lijstReis.isEmpty()) {
        if(!lijstPassagier.isEmpty()) {


            System.out.println(GROEN + "\n" + zetStreepjes(15) + "Verkoop een ticket aan een passagier" + zetStreepjes(15) + "\n" + RESET);
            System.out.println(GEEL + "Aan een ticket moet je een reis koppelen, hier zijn de mogelijke reizen: " + RESET);

            toonReis(lijstReisMetTrein, true, false);

            System.out.print(GEEL + zetStreepjes(4) + "Kies een reis." + zetStreepjes(4) + RESET + "\n(Gebruik de nummer van de reis om deze te selecteren)\n--> ");

            try {


                int keuzeReis = Integer.parseInt(sc.nextLine());

                if (keuzeReis < 0 || keuzeReis >= lijstReisMetTrein.size()) {
                    throw new Exception("Je hebt geen geldige reis gekozen. Deze actie werd volledig gesloten. Probeer opnieuw.");
                }

                Reis reisKoppelenAanTicket = lijstReisMetTrein.get(keuzeReis);

                if ((reisKoppelenAanTicket.getTicketTeller() + 1) > (reisKoppelenAanTicket.getTrein().getAantalZitplaatsen())) {
                    System.err.println("Deze reis zit al vol, sorry.");
                } else {

                    System.out.println(GEEL + "\nMogelijke passagiers aan wie je een ticket kan verkopen: " + RESET);

                    toonPersoon();

                    System.out.print(GROEN + zetStreepjes(4) + "Kies een passagier." + zetStreepjes(4) + RESET + "\nGebruik de nummer van de gewenste passagier om deze te selecteren: ");
                    int keuzePassagier = Integer.parseInt(sc.nextLine());

                    if (keuzePassagier < 0 || keuzePassagier >= lijstPassagier.size()) {
                        throw new Exception("Je hebt geen geldige passagier gekozen. Deze actie werd volledig gesloten. Probeer opnieuw.");
                    }

                    Passagier ticketAanPassagierVerkopen = lijstPassagier.get(keuzePassagier);

                    System.out.println(GEEL + "\nIn welke klasse wilt " + ticketAanPassagierVerkopen.getVoornaam() + " " + ticketAanPassagierVerkopen.getAchternaam() + " zitten ?" + RESET +  "\nEr is keuze tussen:\n");

                    ArrayList<Klasse> lijstKlasse = new ArrayList<>(List.of(Klasse.values()));

                    for (Klasse klasse : lijstKlasse) {
                        System.out.println(lijstKlasse.indexOf(klasse) + "." + klasse.name() + "\n");
                    }

                    System.out.print("Gebruik de nummers om een klasse te selecteren\n-->");

                    Klasse klasse;
                    do {

                        int keuzeKlasse;
                        do {
                            keuzeKlasse = Integer.parseInt(sc.nextLine());
                            if (keuzeKlasse < 0 || keuzeKlasse >= lijstKlasse.size()){
                                System.err.println("Je hebt geen geldige klasse gekozen. Probeer nu opnieuw: ");
                            }
                        }while(keuzeKlasse < 0 || keuzeKlasse >= lijstKlasse.size());
                        klasse = lijstKlasse.get(keuzeKlasse);

                        if (klasse == null) {
                            System.err.print("Je hebt geen geldige klasse gekozen, probeer nu opnieuw: ");
                        } else {
                            ticketAanPassagierVerkopen.setKlasse(klasse);
                        }

                    } while (klasse == null);


                    System.out.println("In welke wagon wilt deze persoon zitten?\nHier zijn alle mogelijke wagons:\n");
                    ArrayList<Wagon> lijstWagon = reisKoppelenAanTicket.getTrein().getLijstWagons();

                    for (Wagon w : lijstWagon) {
                        System.out.println(GROEN + "Wagon" + lijstWagon.indexOf(w) + ":" + RESET + "\n");
                        w.toonPassagierInWagon();
                    }

                    boolean fouteKeuzeWagon = true;

                    do {
                        System.out.print("\nGebruik de nummer van het wagon die je wilt selecteren\n-->");
                        int keuzeWagon = Integer.parseInt(sc.nextLine());

                        if (keuzeWagon < 0 || keuzeWagon >= lijstWagon.size()) {
                            throw new Exception("Je hebt geen geldige wagon gekozen. Actie gesloten. Probeer opnieuw.");
                        }

                        Wagon passagierAanWagonToevoegen = lijstWagon.get(keuzeWagon);

                        if ((passagierAanWagonToevoegen.getLijstPassagier().size() + 1) > 80) {
                            System.err.println("Deze wagon zit al vol. Kies een andere wagon.\n");
                        } else {
                            passagierAanWagonToevoegen.voegPassagierToeAanWagon(ticketAanPassagierVerkopen);

                            Ticket ticket = new Ticket(ticketAanPassagierVerkopen, reisKoppelenAanTicket, klasse);
                            int grootteLijstTicketVoorAanpassing = lijstTicket.size();
                            lijstTicket.add(ticket);


                            if (grootteLijstTicketVoorAanpassing != lijstTicket.size()) {
                                System.out.println(GROEN + "Ticket werd succesvol verkoopt aan " + ticketAanPassagierVerkopen.getVoornaam() + " " + ticketAanPassagierVerkopen.getAchternaam() + RESET);
                                reisKoppelenAanTicket.ticketGemaakt();
                                reisKoppelenAanTicket.voegTicketToe(ticket);
                                reisKoppelenAanTicket.voegPersoonMetTicketToe(ticketAanPassagierVerkopen);
                                lijstGeldigeReizenVoorWegschrijven.add(reisKoppelenAanTicket);
                                fouteKeuzeWagon = false;
                            } else {
                                System.err.println("Ticket werd niet verkoopt.");
                            }


                        }
                    } while (fouteKeuzeWagon);

                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            eindOptie();
        }else{
            System.err.println("Er zijn momenteel geen beschikbare personen. Maak er eerst een aan.");
        }
    }else{
        System.err.println("Er zijn momenteel geen reizen beschikbaar. Maak er eerst een aan.");
    }
    }


    /**
     * Optie om een boardinglijst van een reis af te drukken in een extern bestand.
     * Het gebruikt de ArrayList lijstTicket om alle informatie van alle tickets af te printen
     * Een boardinglijst bevat alle nodige gegevens van alle passagier op het gekozen reis waarvan de boarding lijst afgeprint moet worden.
     */
    private void ticketWegschrijvenInBestand(){


        if (!lijstGeldigeReizenVoorWegschrijven.isEmpty()) {


            System.out.println(GROEN + "\n" + zetStreepjes(15) + "Ticket wegschrijven in bestand" + zetStreepjes(15) + "\n" + RESET);

            System.out.println(GEEL + zetStreepjes(4) + "Hier zijn de mogelijke reizen waarvan je een ticket kan printen: " + zetStreepjes(4) + "\n" + RESET);
            toonReis(lijstGeldigeReizenVoorWegschrijven, true, true);
            System.out.print("Kies een reis. \nGebruik de nummer van de reis die je wilt selecteren: ");

            try {
                int keuzeReis = Integer.parseInt(sc.nextLine());

                if (keuzeReis < 0 || keuzeReis >= lijstReis.size()) {
                    throw new Exception("Je hebt geen geldige reis gekozen. Actie volledig gesloten. Probeer opnieuw.");
                }

                Reis gekozeReis = lijstReis.get(keuzeReis);
                String bestandsNaam = gekozeReis.getTraject().getStartPunt() + "_" + gekozeReis.getTraject().getEindPunt() + "_" + gekozeReis.getTijdstip().getProperAankomstPuntA() + ".txt";

                ArrayList<Ticket> lijstTicket = gekozeReis.getLijstTicket();


                try (FileWriter writer = new FileWriter("ticket/" + bestandsNaam)) {

                    writer.write("Vertrekt punt: " + gekozeReis.getTraject().getStartPunt() + ", vertrekdatum: " + gekozeReis.getTijdstip().getProperAankomstPuntA() + "\nAankomstpunt: " + gekozeReis.getTraject().getEindPunt() + ", aankomstdatum: " + gekozeReis.getTijdstip().getProperAankomstPuntB() + "\n") ;

                    int i = 0;
                    for (Ticket t :  lijstTicket) {
                        Passagier p = t.getPassagier();
                        String klasse = p.getKlasse();
                        writer.append(t.toonPassagierVoorTicket(i, p)).append("Klasse: ").append(klasse).append("\n");
                    }





                } catch (IOException e) {
                    System.out.println("Schrijven mislukt");
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            System.out.println(GROEN + "Boardinglijst afgedrukt" + RESET);
            eindOptie();

        }else{
            System.err.println("Er zijn momenteel geen reizen beschikbaar. Maak er eerst een aan.");
        }

    }



    // -----------------FUNCTIES VOOR KEUZE MENU ----------------- //


    /**
     * Functie om alle passagieren in de ArrayList lijstPassagier af te printen.
     * Gebruikt de functie toonPassagier in de klasse Passagier.
     */
    private void toonPersoon() {
           toonPassagier(EuromoonApp.lijstPassagier);
    }

    /**
     * Functie om alle reizen in de ArrayList lijstReis af te printen, met alle bestuurders, stewards en bagagepersoneel.
     * Deze functie gebruikt verschillende functies uit de verschillende persoonsklasse om ze af te drukken.
     * Deze functie is flexibel, het kan het type locomotief en lijst van wagons verbergen als het niet nodig is.
     *
     * @param lijstReis een ArrayList met alle reizen die we willen printen
     * @param moetTreinBevatten een boolean om het type locomotief af te printen of niet
     * @param moetWagonBevatten een boolean om de lijst van wagons dat mogelijks passagieren bevat af te printen of niet
     */
    public static void toonReis(ArrayList<Reis> lijstReis, boolean moetTreinBevatten, boolean moetWagonBevatten){
        for (Reis r : lijstReis) {


            System.out.println("[" + GROEN + "Reis"+lijstReis.indexOf(r) + ":" +  RESET +
                    "\n\tTraject[ " + r.getTraject() + "]"+
                    "\n\tTijdstip[ " + r.getTijdstip() + "]"+
                    "\n\tLijst bestuurders: ");

            ArrayList<Personeelslid> lijstBestuurder = r.getLijstBestuurder();
            toonBestuurder(lijstBestuurder);


            System.out.println("\n\tLijst stewards: ");
            ArrayList<Personeelslid> lijstSteward = r.getLijstSteward();
            toonSteward(lijstSteward);


            ArrayList<Personeelslid> lijstPersoneel = r.getLijstPersoneelsleden();
            toonBagagePersoneel(lijstPersoneel);




            if (moetTreinBevatten) {
                System.out.println("\n De type trein dat deze reis zal gebruiken is de: " + r.getTrein().getTypeLocomotief() + ", met: " + r.getTrein().getAantalZitplaatsen() + " aantal zitplaatsen.\n");

                if (moetWagonBevatten){
                    System.out.println("Lijst wagons:\n");
                    ArrayList<Wagon> lijstWagon = r.getTrein().getLijstWagons();

                    for (Wagon w : lijstWagon) {
                        System.out.println(GROEN +"\tWagon"+ lijstWagon.indexOf(w) + ":" + RESET + "\n");
                        w.toonPassagierInWagon();

                    }
                }


            }
            System.out.println("]\n");

        }
    }

    /**
     * Deze functie maakt eerst een object Persoon aan.
     * Naargelang het typePersoon dat werd meegegeven zal het een andere object return met de attributen dat worden gegeven.
     *
     * @param typePersoon een waarde uit de enumeratie typePersoon
     * @return een verschillende constructor naargelang typePersoon
     */
    private Persoon maakPersoon(TypePersoon typePersoon) throws Exception {


        System.out.print("Voornaam van de " + typePersoon.name().toLowerCase() + ": ");
        String voornaam = sc.nextLine();
        System.out.print("Achternaam van de " + typePersoon.name().toLowerCase() + ": ");
        String achternaam = sc.nextLine();
        System.out.print("Rijksregisternummer van de " + typePersoon.name().toLowerCase() + ": ");
        String rijksregisternummer = sc.nextLine();

        if (voornaam.isEmpty() || achternaam.isEmpty() || rijksregisternummer.isEmpty()) {
            throw new Exception("Een persoon moet 1 voornaam, achternaam en rijksregisternummer bevatten.");
        }

        LocalDate geboortedatum;

        try {


            System.out.print("Geboortedatum van de " + typePersoon.name().toLowerCase() + " (Formaat: yyyy-MM-dd): ");
            geboortedatum = LocalDate.parse(sc.nextLine());
        }catch (DateTimeParseException e){
            throw new Exception("Je hebt een foute formaat ingegeven bij het geboortedatum. Persoon werd niet aangemaakt.");
        }
        switch (typePersoon) {
            case PASSAGIER -> {return new Passagier(voornaam, achternaam, rijksregisternummer, geboortedatum);}
            case BESTUURDER -> {return new Bestuurder(voornaam, achternaam, rijksregisternummer, geboortedatum);}
            case BAGAGEPERSONEEL -> {return new BagagePersoneel(voornaam, achternaam, rijksregisternummer, geboortedatum);}
            case STEWARD -> {return new Steward(voornaam, achternaam, rijksregisternummer, geboortedatum);}

        }

        return null;
    }

    /**
     * Functie om een object Bestuurder aan te maken.
     * De functie zal eerst maakPersoon gebruiken om een Persoon aan te maken, die het dan upcast naar een Personeelslid. Uiteindelijk wordt het nieuw object Personeelslid nog eens gecast naar een object Bestuurder.
     * De functie gebruikt ook de functie voegCertificatiesToeAanPersoneelslid om certificaties te voegen aan het nieuw gemaakte object.
     *
     * @return een object Bestuurder met een lijst van certificaties
     */
    private Personeelslid maakBestuurder() throws Exception {

        Personeelslid p = (Personeelslid) maakPersoon(TypePersoon.BESTUURDER);
        Bestuurder bestuurder = (Bestuurder) p;

        voegCertificatiesToeAanPersoneelslid(bestuurder);

        return bestuurder;
    }

    /**
     * Functie om een object Steward aan te maken.
     * De functie zal eerst maakPersoon gebruiken om een Persoon aan te maken, die het dan upcast naar een Personeelslid. Uiteindelijk wordt het nieuw object Personeelslid nog eens gecast naar een object Steward.
     * De functie gebruikt ook de functie voegCertificatiesToeAanPersoneelslid om certificaties te voegen aan het nieuw gemaakte object.
     *
     * @return een object Steward met een lijst van certificaties
     */
    private Personeelslid maakSteward() throws Exception {

        Personeelslid p = (Personeelslid) maakPersoon(TypePersoon.STEWARD);
        Steward steward = (Steward) p;
        voegCertificatiesToeAanPersoneelslid(steward);

        return steward;
    }

    /**
     * Functie om een object BagagePersoneel aan te maken.
     * De functie zal eerst maakPersoon gebruiken om een Persoon aan te maken, die het dan upcast naar een Personeelslid. Uiteindelijk wordt het nieuw object Personeelslid nog eens gecast naar een object BagagePersoneel.
     * De functie gebruikt ook de functie voegCertificatiesToeAanPersoneelslid om certificaties te voegen aan het nieuw gemaakte object.
     *
     * @return een object BagagePersoneel met een lijst van certificaties
     */
    private Personeelslid maakBagagePersoneel() throws Exception {

        Personeelslid p = (Personeelslid) maakPersoon(TypePersoon.BAGAGEPERSONEEL);
        BagagePersoneel bagagePersoneel = (BagagePersoneel) p;

        voegCertificatiesToeAanPersoneelslid(bagagePersoneel);


        return bagagePersoneel;
    }


    /**
     * Deze functie gebruikt een do while om certificaties (in String) toe te voegen aan een instantie van Personeelslid.
     *
     * @param p een instantie van een object Personeelslid
     */
    private void voegCertificatiesToeAanPersoneelslid(Personeelslid p) throws Exception {

        String certificaties;

        System.out.println("\nWat zijn de certificaties van deze personeelslid ? Typ 'STOP' om te stoppen.");
        do {
            System.out.print("Certificatie van de personeelslid: ");
            certificaties = sc.nextLine();

            if (!certificaties.equalsIgnoreCase("STOP")) {
                    p.voegCertificatie(certificaties);
            }

            if (p.getLijstCertificaties().isEmpty()) {
                throw new Exception("Een personeelslid moet minstens 1 certificatie hebben.");
            }

        } while (!certificaties.equalsIgnoreCase("STOP") && !p.getLijstCertificaties().isEmpty());
    }


    /**
     * Kleine functie om een gekozen aantal streepjes te voegen om een cleane print te bouwen.
     *
     * @param aantalStreepjes een int voor het aantal streepjes
     * @return een String met het gewenste aantal streepjes
     */
    private String zetStreepjes(int aantalStreepjes) {

        return "-".repeat(aantalStreepjes);
    }


    /**
     * Kleine functie om te tonen dat een optie gedaan is.
     * Het geeft een SOUT van 50 streepjes.
     */
    private void eindOptie(){
        System.out.println(GROEN + "-".repeat(50) + RESET);
    }



}


