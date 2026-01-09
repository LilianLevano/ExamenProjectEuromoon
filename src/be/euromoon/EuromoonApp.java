package be.euromoon;

import be.euromoon.persoon.Passagier;
import be.euromoon.persoon.Personeelsleed;
import be.euromoon.reizen.Reis;
import be.euromoon.reizen.Tijdstip;
import be.euromoon.reizen.Traject;
import be.euromoon.tickets.Klasse;
import be.euromoon.tickets.Ticket;
import be.euromoon.trein.Trein;
import be.euromoon.trein.TypeLocomotief;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class EuromoonApp {

    public static final String GROEN = "\u001B[32m";
    public static final String GEEL = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    Scanner sc = new Scanner(System.in);
    private final ArrayList<Passagier> lijstPassagier = new ArrayList<>();

    private final ArrayList<Reis> lijstReis = new ArrayList<>();
    private final ArrayList<Ticket> lijstTicket = new ArrayList<>();

    public void start() {
        System.out.println(GEEL + "Welkom op de Euromoon Manager App." + RESET);

        int keuzeMenu;

        do {
            System.out.println("""
                    Maak een keuze: \
                    
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

                    break;
                case 8:
                    toonLijsten();
                    break;
            }
        } while (keuzeMenu != 9);

    }

    private void passagierRegistreren() {

        try {

            System.out.print("Voornaam: ");
            String voornaam = sc.nextLine();
            System.out.print("Achternaam: ");
            String achternaam = sc.nextLine();
            System.out.print("Rijksregisternummer: ");
            String rijksregisternummer = sc.nextLine();
            System.out.println("Geboortedatum (Formaat: yyyy-MM-dd): ");
            LocalDate geboortedatum = LocalDate.parse(sc.nextLine());

            int grootteLijstVoorAanpassing = lijstPassagier.size();
            Passagier p = new Passagier(voornaam, achternaam, rijksregisternummer, geboortedatum);
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

    private Personeelsleed maakBestuurder() {
        System.out.println(GEEL + "Een reis heeft minstens 1 bestuurder nodig." + RESET);
        System.out.print("Voornaam van de bestuurder: ");
        String voornaam = sc.nextLine();
        System.out.print("Achternaam van de bestuurder: ");
        String achternaam = sc.nextLine();
        System.out.print("Rijksregisternummer van de bestuurder: ");
        String rijksregisternummer = sc.nextLine();
        System.out.print("Geboortedatum (Formaat: yyyy-MM-dd): ");
        LocalDate geboortedatum = LocalDate.parse(sc.nextLine());

        Personeelsleed bestuurder = new Personeelsleed(voornaam, achternaam, rijksregisternummer, geboortedatum, "Bestuurder");

        String certificatieBestuurder;
        System.out.println("Wat zijn de certificaties van de bestuurder ? Typ 'STOP' om te stoppen.");
        do {
            System.out.print("Certificatie van de bestuurder: ");
            certificatieBestuurder = sc.nextLine();

            if (!Objects.equals(certificatieBestuurder, "STOP")) {
                bestuurder.voegCertificatie(certificatieBestuurder);
            }
        } while (!Objects.equals(certificatieBestuurder, "STOP"));

        return bestuurder;
    }

    private Personeelsleed maakSteward() {


        System.out.print("Voornaam van de steward: ");
        String voornaamSteward = sc.nextLine();
        System.out.print("Achternaam van de steward: ");
        String achternaamSteward = sc.nextLine();
        System.out.print("Rijksregisternummer van de steward: ");
        String rijksregisternummerSteward = sc.nextLine();
        System.out.print("Geboortedatum (Formaat: yyyy-MM-dd): ");
        LocalDate geboortedatumSteward = LocalDate.parse(sc.nextLine());

        Personeelsleed steward = new Personeelsleed(voornaamSteward, achternaamSteward, rijksregisternummerSteward, geboortedatumSteward, "Steward");

        String certificatieSteward;

        System.out.println("Wat zijn de certificaties van de steward ? Typ 'STOP' om te stoppen.");
        do {
            System.out.print("Certificatie van de bestuurder: ");
            certificatieSteward = sc.nextLine();

            if (!Objects.equals(certificatieSteward, "STOP")) {
                steward.voegCertificatie(certificatieSteward);
            }

        } while (!Objects.equals(certificatieSteward, "STOP"));


        return steward;
    }


    private void maakReis() {
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
            Personeelsleed bestuurder = maakBestuurder();
            r.voegBestuurderToe(bestuurder);


            // Maak drie stewards aan, vereiste voor een reis

            System.out.println(GEEL + "Een reis heeft ook een minimum van drie stewards nodig." + RESET);
            int i = 3;
            do {
                System.out.println(GEEL + "Maak een steward aan, nog " + i + " te maken." + RESET);
                Personeelsleed steward = maakSteward();
                r.voegStewardToe(steward);
                i--;
            } while (r.sizeLijstSteward() != 3);


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

    private void toonLijsten() {
        System.out.println(lijstPassagier);

        System.out.println(lijstReis);
        System.out.println(lijstTicket);
    }

    private void treinAanReisKoppelen() {

        try {
            System.out.print("Welke type trein wil je aan deze reis koppelen? Je hebt de keuze tussen: 1. CLASS_373 2. CLASS_374. (Gebruik de nummer van de keuze): ");
            int keuzeTypeTrein = Integer.parseInt(sc.nextLine());
            TypeLocomotief typeLocomotief =
                    switch (keuzeTypeTrein) {
                        case 1 -> TypeLocomotief.CLASS_373;
                        case 2 -> TypeLocomotief.CLASS_374;
                        default -> null;
                    };

            Trein trein = new Trein(typeLocomotief);

            System.out.println("Hier zijn alle mogelijke reizen: " + lijstReis);
            System.out.print("Aan welke reis wil je deze trein koppelen? Gebruik de index van de reis.)");
            int keuzeReis = Integer.parseInt(sc.nextLine());

            Reis reisKoppelenAanTrein = lijstReis.get(keuzeReis);

            reisKoppelenAanTrein.koppelTreinAanReis(trein);

            System.out.println(GROEN + "Trein werd succesvol gekoppeld aan deze reis." + RESET);

        } catch (Exception e) {
            System.err.println("Fout");
        }
    }

    private void verkoopTicketAanPassagier() {


        System.out.println("Aan een ticket moet je een reis koppelen, hier zijn de mogelijke reizen: " + lijstReis);
        System.out.print("Kies een reis. Gebruik de nummers om een reis te kiezen: ");
        int keuzeReis = Integer.parseInt(sc.nextLine());
        Reis reisKoppelenAanReis = lijstReis.get(keuzeReis);

        if ((reisKoppelenAanReis.getTicketTeller() + 1) > (reisKoppelenAanReis.getTrein().getAantalZitplaatsen())) {
            System.err.println("Deze reis zit al vol, sorry.");
        } else {
            System.out.println("Mogelijke passagiers aan wie je een ticket kan verkopen: " + lijstPassagier);
            System.out.print("Kies een passagier. Gebruik de nummers om een passagier te kiezen: ");
            int keuzePassagier = Integer.parseInt(sc.nextLine());
            Passagier ticketAanPassagierVerkopen = lijstPassagier.get(keuzePassagier);

            System.out.println("In welke klasse wil je zitten ? Je hebt keuze tussen: 1. Eerste Klasse 2. Tweede Klasse (Gebruik de nummers om een klasse te selecteren)");
            System.out.print("-->");
            int keuzeKlasse = Integer.parseInt(sc.nextLine());

            Klasse klasse = switch (keuzeKlasse) {
                case 1 -> Klasse.EERSTEKLASSE;
                case 2 -> Klasse.TWEEDEKLASSE;
                default -> null;
            };

            Ticket ticket = new Ticket(ticketAanPassagierVerkopen, reisKoppelenAanReis, klasse);
            lijstTicket.add(ticket);

        }
    }
}

