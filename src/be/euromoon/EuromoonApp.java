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

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import java.util.Objects;
import java.util.Scanner;

import static be.euromoon.persoon.Passagier.toonPassagier;

import static be.euromoon.persoon.typePersoneel.BagagePersoneel.toonBagagePersoneel;
import static be.euromoon.persoon.typePersoneel.Bestuurder.toonBestuurder;
import static be.euromoon.persoon.typePersoneel.Steward.toonSteward;
import static be.euromoon.tickets.Ticket.toonTicket;

public class EuromoonApp {

    public static final String GROEN = "\u001B[32m";
    public static final String GEEL = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    Scanner sc = new Scanner(System.in);

    public static final ArrayList<Passagier> lijstPassagier = new ArrayList<>();
    private final ArrayList<Reis> lijstReis = new ArrayList<>();
    private final ArrayList<Ticket> lijstTicket = new ArrayList<>();
    private final ArrayList<Reis> lijstReisMetTrein = new ArrayList<>();
    private final ArrayList<Passagier> lijstPassagierMetTicket = new ArrayList<>();
    private final ArrayList<Reis> lijstReisMetTreinMetTicket = new ArrayList<>();

    public void start() {
        System.out.println(GEEL + "Welkom op de Euromoon Manager App." + RESET );

        int keuzeMenu;

        do {
            System.out.println("""
                    \nMaak een keuze: \
                    
                    1. Registreer een passagier \
                    
                    2. Maak een reis \
                    
                    3. Trein aan een reis koppelen \
                    
                    4. Ticket verkopen aan een passagier \
                    
                    5. Druk boardinglijst af \
                    
                    6. \
                    
                    7. \
                    
                    8. Toon lijsten \
                    
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

                case 8:
                    toonLijsten();
                    break;
            }
        } while (keuzeMenu != 9);

    }



    // -----------------FUNCTIES VOOR KEUZE MENU ----------------- //

    private void passagierRegistreren() {
        System.out.println(GROEN + "Registreer een passagier." + RESET + "\n");
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
        }
    }

    private void maakReis() {

        System.out.println(GROEN + "Maak een reis." + RESET + "\n");
        try {

            /*Maak een traject, onderdeel om een object Reis te bouwen  */
            System.out.print("Start punt van het traject: ");
            String startpunt = sc.nextLine();
            System.out.print("Eind punt van het traject: ");
            String eindpunt = sc.nextLine();
            Traject t = new Traject(startpunt, eindpunt);

            /*Maak een Tijdstip, onderdeel om een object Reis te bouwen  */
            System.out.print("Startuur van het traject (Formaat: yyyy-MM-ddThh:mm:ss): ");
            LocalDateTime start = LocalDateTime.parse(sc.nextLine());
            System.out.print("Einduur van het traject (Formaat: yyyy-MM-ddThh:mm:ss): ");
            LocalDateTime eind = LocalDateTime.parse(sc.nextLine());
            Tijdstip ti = new Tijdstip(start, eind);

            Reis r = new Reis(t, ti);

            /*Maak een Bestuurder, er moet er minstens 1 zijn per reis  */
            System.out.println("\n" + GEEL + "Een reis heeft minstens 1 bestuurder nodig." + RESET + "\n");

            Personeelslid verplichteBestuurder = maakBestuurder();
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

            System.out.println(GEEL + "\nEen reis heeft ook een minimum van drie stewards nodig." + RESET);
            int i = 3;
            do {
                System.out.println(GEEL + "Maak een steward aan, nog " + i + " te maken." + RESET);
                Personeelslid steward = maakSteward();
                r.voegStewardToe(steward);
                i--;
            } while (r.sizeLijstSteward() != 3);

            // Maak andere persoonsleden als nodig

            System.out.println(GEEL + "\nEen reis kan bagage personeel bevatten" + RESET);

            String invoer;

            do{
                System.out.print("Wil je een bagage personeel toevoegen? (Ja/Nee): ");
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

        } catch (DateTimeParseException e) {
            System.err.println("Foutieve invoer: Je hebt een foute formaat ingegeven bij een van de tijdstip. Reis werd niet aangemaakt, probeer opnieuw.");
        }

    }

    private void treinAanReisKoppelen() {

        System.out.println(GROEN + "\nKoppel een trein aan een reis" + RESET);

        try {
            System.out.print("\nWelke type trein wil je aan een reis koppelen? Je hebt de keuze tussen: \n1. CLASS_373 (Kan tot 12 wagons trekken) \n2. CLASS_374 (Kan tot 14 wagons trekken). \n(Gebruik de index van de keuze om deze te selecteren)\n--> ");
            int keuzeTypeTrein = Integer.parseInt(sc.nextLine());
            TypeLocomotief typeLocomotief =
                    switch (keuzeTypeTrein) {
                        case 1 -> TypeLocomotief.CLASS_373;
                        case 2 -> TypeLocomotief.CLASS_374;
                        default -> null;
                    };

            Trein trein = new Trein(typeLocomotief);

            System.out.println("\nHier zijn alle mogelijke reizen waaraan je deze trein kan koppelen: ");


            toonReis(lijstReis, false, true);


            System.out.print("Aan welke reis wil je deze trein koppelen? \n(Gebruik de index van de reis.)\n--> ");
            int keuzeReis = Integer.parseInt(sc.nextLine());

            Reis reisKoppelenAanTrein = lijstReis.get(keuzeReis);

            reisKoppelenAanTrein.koppelTreinAanReis(trein);
            lijstReisMetTrein.add(reisKoppelenAanTrein);

            System.out.println(GROEN + "Trein werd succesvol gekoppeld aan deze reis." + RESET);

        } catch (Exception e) {
            System.err.println("Fout");
        }
    }



    private void verkoopTicketAanPassagier() {


        System.out.println(GROEN + "\nVerkoop een ticket aan een passagier" + RESET);
        System.out.println(GEEL + "Aan een ticket moet je een reis koppelen, hier zijn de mogelijke reizen: " + RESET);

        toonReis(lijstReisMetTrein, true, false);

        System.out.print("Kies een reis. \n(Gebruik de nummers om een reis te kiezen)\n--> ");
        int keuzeReis = Integer.parseInt(sc.nextLine());
        Reis reisKoppelenAanTicket = lijstReis.get(keuzeReis);

        if ((reisKoppelenAanTicket.getTicketTeller() + 1) > (reisKoppelenAanTicket.getTrein().getAantalZitplaatsen())) {
            System.err.println("Deze reis zit al vol, sorry.");
        } else {

            System.out.println("\nMogelijke passagiers aan wie je een ticket kan verkopen: ");

            toonPersoon(lijstPassagier);

            System.out.print("Kies een passagier. Gebruik de nummer van de gewenste passagier om deze te selecteren: ");
            int keuzePassagier = Integer.parseInt(sc.nextLine());
            Passagier ticketAanPassagierVerkopen = lijstPassagier.get(keuzePassagier);

            System.out.println("In welke klasse wilt" + ticketAanPassagierVerkopen.getVoornaam() + " " + ticketAanPassagierVerkopen.getAchternaam() + " zitten ? Er is keuze tussen: \n1. Eerste klasse \n2. Tweede klasse \n(Gebruik de nummers om een klasse te selecteren)\n-->");

            int keuzeKlasse = Integer.parseInt(sc.nextLine());

            Klasse klasse = switch (keuzeKlasse) {
                case 1 -> Klasse.EERSTEKLASSE;
                case 2 -> Klasse.TWEEDEKLASSE;
                default -> null;
            };

            System.out.println("In welke wagon wilt deze persoon zitten?\nHier zijn alle mogelijke wagons:\n");
            ArrayList<Wagon> lijstWagon = reisKoppelenAanTicket.getTrein().getLijstWagons();

            for (Wagon w : lijstWagon) {
                System.out.println(GROEN +"Wagon"+ lijstWagon.indexOf(w) + ":" + RESET + "\n");
                w.toonPassagierInWagon();
            }

            boolean fouteKeuzeWagon = true;

            do{
                System.out.print("\nGebruik de nummer van het wagon die je wilt selecteren\n-->");
                int keuzeWagon = Integer.parseInt(sc.nextLine());
                Wagon passagierAanWagonToevoegen = lijstWagon.get(keuzeWagon);

                if ((passagierAanWagonToevoegen.getLijstPassagier().size() + 1) > 80)  {
                    System.err.println("Deze wagon zit al vol. Kies een andere wagon.\n");
                }else{
                    passagierAanWagonToevoegen.voegPassagierToeAanWagon(ticketAanPassagierVerkopen);

                    Ticket ticket = new Ticket(ticketAanPassagierVerkopen, reisKoppelenAanTicket, klasse);
                    int grootteLijstTicketVoorAanpassing = lijstTicket.size();
                    lijstTicket.add(ticket);
                    lijstPassagierMetTicket.add(ticketAanPassagierVerkopen);


                    if (grootteLijstTicketVoorAanpassing != lijstTicket.size()) {
                        System.out.println(GROEN + "Ticket werd succesvol verkoopt aan " + ticketAanPassagierVerkopen.getVoornaam() + " " + ticketAanPassagierVerkopen.getAchternaam() + RESET);
                        reisKoppelenAanTicket.ticketGemaakt();
                    }

                    fouteKeuzeWagon = false;
                }
            }while (fouteKeuzeWagon);







        }
    }

    private void toonLijsten() {
        System.out.println(lijstPassagier);

        System.out.println(lijstReis);
        System.out.println(lijstTicket);
    }

    // -----------------FUNCTIES VOOR KEUZE MENU ----------------- //




    private void toonPersoon(ArrayList<Passagier> lijstPassagier) {
           toonPassagier(lijstPassagier);
    }

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
            System.out.println("]\n");



            if (moetTreinBevatten) {
                System.out.println("Type trein dat deze reis zal gebruiken: " + r.getTrein().getTypeLocomotief() + ", met: " + r.getTrein().getAantalZitplaatsen() + " aantal zitplaatsen.\n");

                if (moetWagonBevatten){
                    System.out.println("Lijst wagons:\n");
                    ArrayList<Wagon> lijstWagon = r.getTrein().getLijstWagons();

                    for (Wagon w : lijstWagon) {
                        System.out.println(GROEN +"\tWagon"+ lijstWagon.indexOf(w) + ":" + RESET + "\n");
                        w.toonPassagierInWagon();

                    }
                }


            }

        }
    }


    private Persoon maakPersoon(TypePersoon typePersoon) {


        System.out.print("Voornaam van de " + typePersoon.name().toLowerCase() + ": ");
        String voornaam = sc.nextLine();
        System.out.print("Achternaam van de " + typePersoon.name().toLowerCase() + ": ");
        String achternaam = sc.nextLine();
        System.out.print("Rijksregisternummer van de " + typePersoon.name().toLowerCase() + ": ");
        String rijksregisternummer = sc.nextLine();
        System.out.print("Geboortedatum  van de " + typePersoon.name().toLowerCase() + " (Formaat: yyyy-MM-dd): ");
        LocalDate geboortedatum = LocalDate.parse(sc.nextLine());

        switch (typePersoon) {
            case PASSAGIER -> {return new Passagier(voornaam, achternaam, rijksregisternummer, geboortedatum);}
            case BESTUURDER -> {return new Bestuurder(voornaam, achternaam, rijksregisternummer, geboortedatum);}
            case BAGAGEPERSONEEL -> {return new BagagePersoneel(voornaam, achternaam, rijksregisternummer, geboortedatum);}
            case STEWARD -> {return new Steward(voornaam, achternaam, rijksregisternummer, geboortedatum);}

        }

        return null;
    }

    private Personeelslid maakBestuurder() {

        Personeelslid p = (Personeelslid) maakPersoon(TypePersoon.BESTUURDER);
        Bestuurder bestuurder = (Bestuurder) p;

        voegCertificatiesToeAanPersoneelslid(bestuurder);

        return bestuurder;
    }

    private Personeelslid maakSteward() {

        Personeelslid p = (Personeelslid) maakPersoon(TypePersoon.STEWARD);
        Steward steward = (Steward) p;
        voegCertificatiesToeAanPersoneelslid(steward);

        return steward;
    }

    private Personeelslid maakBagagePersoneel() {

        Personeelslid p = (Personeelslid) maakPersoon(TypePersoon.BAGAGEPERSONEEL);
        BagagePersoneel bagagePersoneel = (BagagePersoneel) p;

        voegCertificatiesToeAanPersoneelslid(bagagePersoneel);


        return bagagePersoneel;
    }

    private void voegCertificatiesToeAanPersoneelslid(Personeelslid p) {

        String certificaties;

        System.out.println("\nWat zijn de certificaties van deze personeelslid ? Typ 'STOP' om te stoppen.");
        do {
            System.out.print("Certificatie van de personeelslid: ");
            certificaties = sc.nextLine();

            if (!certificaties.equalsIgnoreCase("STOP")) {

                assert p != null;
                p.voegCertificatie(certificaties);
            }

        } while (!certificaties.equalsIgnoreCase("STOP"));
    }


    private void ticketWegschrijvenInBestand(){

        System.out.println("Hier zijn de mogelijke reizen waarvan je een ticket kan printen: ");
        toonReis(lijstReis, true, true);
        System.out.print("Kies een reis: ");
        int keuzeReis = Integer.parseInt(sc.nextLine());
        Reis gekozeReis = lijstReis.get(keuzeReis);
        String bestandsNaam = gekozeReis.getTraject().getStartPunt() + "_" + gekozeReis.getTraject().getEindPunt() +"_"+gekozeReis.getTijdstip().getProperAankomstPuntA() + ".txt";



        try (FileWriter writer = new FileWriter("ticket/" + bestandsNaam)) {

            int i = 0;
            for (Passagier p : lijstPassagierMetTicket) {

               writer.append(p.toonPassagierVoorTicket(i));
               i++;
            }


        } catch (IOException e) {
            System.out.println("Schrijven mislukt");
        }



































    }



    }


