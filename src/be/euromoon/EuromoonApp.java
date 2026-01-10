package be.euromoon;

import be.euromoon.persoon.Passagier;
import be.euromoon.persoon.Personeelslid;
import be.euromoon.persoon.Persoon;
import be.euromoon.persoon.TypePersoon;
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
    private final ArrayList<Reis> lijstReisMetTrein = new ArrayList<>();

    public void start() {
        System.out.println(GEEL + "Welkom op de Euromoon Manager App." + RESET + "\n");

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


            Passagier p = (Passagier) maakPersoon(TypePersoon.PASSAGIER, null);
            int grootteLijstVoorAanpassing = lijstPassagier.size();

            lijstPassagier.add(p);

            if (grootteLijstVoorAanpassing != lijstPassagier.size()) {
                System.out.println(GROEN + "Passagier werd succesvol aangemaakt" + RESET + "\n");
            } else {
                System.err.println("Passagier werd niet aangemaakt." + "\n");
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

            Personeelslid bestuurderVerplicht = maakBestuurder();
            r.voegBestuurderToe(bestuurderVerplicht);

            String invoerBestuurder;

            do{
                System.out.print("Wil je een andere bestuurder registreren? (Ja/Nee): ");
                invoerBestuurder = sc.nextLine();

                if (invoerBestuurder.equalsIgnoreCase("Ja")) {
                    Personeelslid bestuurder = maakBestuurder();
                    r.voegBestuurderToe(bestuurder);
                }


            }while(invoerBestuurder.equalsIgnoreCase("Ja"));



            // Maak drie stewards aan, vereiste voor een reis

            System.out.println( "\n"+ GEEL + "Een reis heeft ook een minimum van drie stewards nodig." + RESET);
            int i = 3;
            do {
                System.out.println(GEEL + "Maak een steward aan, nog " + i + " te maken." + RESET );
                Personeelslid steward = maakSteward();
                r.voegStewardToe(steward);
                i--;
            } while (r.sizeLijstSteward() != 3);


            // Maak andere persoonsleden als nodig

            System.out.println(GEEL + "Een reis kan andere personeelsleden bevatten" + RESET);

            String invoer;

            do{
                System.out.print("\nWil je een andere personeelslid toevoegen? (Ja/Nee): ");
                invoer = sc.nextLine();

                if (invoer.equalsIgnoreCase("ja")){

                    System.out.print("Rol / beroep van dat persoon: ");
                    String rol = sc.nextLine();

                    Personeelslid anderePersoneelslid = anderePersoneelslid(rol);
                    r.voegPersoneelToe(anderePersoneelslid);
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

        try {
            System.out.print("\nWelke type trein wil je aan een reis koppelen? Je hebt de keuze tussen: \n1. CLASS_373 (12 plaatsen per wagon) \n2. CLASS_374 (14 plaatsen per wagon). \n(Gebruik de index van de type locomotief om deze te selecteren)\n--> ");
            int keuzeTypeTrein = Integer.parseInt(sc.nextLine());
            TypeLocomotief typeLocomotief =
                    switch (keuzeTypeTrein) {
                        case 1 -> TypeLocomotief.CLASS_373;
                        case 2 -> TypeLocomotief.CLASS_374;
                        default -> null;
                    };

            Trein trein = new Trein(typeLocomotief);

            System.out.println("Hier zijn alle mogelijke reizen: ");


            toonReis(lijstReis, false);


            System.out.print("Aan welke reis wil je deze trein koppelen? (Gebruik de index van de reis.): ");
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


        System.out.println(GROEN + "\nAan een ticket moet je een reis koppelen, hier zijn de mogelijke reizen: " + RESET);

        toonReis(lijstReisMetTrein, true);

            System.out.print("Kies een reis. Gebruik de nummers om een reis te kiezen: ");
            int keuzeReis = Integer.parseInt(sc.nextLine());
            Reis reisKoppelenAanReis = lijstReis.get(keuzeReis);

            if ((reisKoppelenAanReis.getTicketTeller() + 1) > (reisKoppelenAanReis.getTrein().getAantalZitplaatsen())) {
                System.err.println("Deze reis zit al vol, sorry.");
            } else {

                System.out.println("Mogelijke passagiers aan wie je een ticket kan verkopen: ");

                toonPersoon(lijstPassagier);

                System.out.print("Kies een passagier. Gebruik de nummer van de gewenste passagier om deze te selecteren: ");
                int keuzePassagier = Integer.parseInt(sc.nextLine());
                Passagier ticketAanPassagierVerkopen = lijstPassagier.get(keuzePassagier);

                System.out.println("In welke klasse wil je zitten ? Je hebt keuze tussen: \n1. Eerste klasse \n2. Tweede klasse \n(Gebruik de nummer van de klasse die je wilt selecteren)");
                System.out.print("-->");
                int keuzeKlasse = Integer.parseInt(sc.nextLine());

                Klasse klasse = switch (keuzeKlasse) {
                    case 1 -> Klasse.EERSTEKLASSE;
                    case 2 -> Klasse.TWEEDEKLASSE;
                    default -> null;
                };

                Ticket ticket = new Ticket(ticketAanPassagierVerkopen, reisKoppelenAanReis, klasse);
                lijstTicket.add(ticket);
                reisKoppelenAanReis.ticketGemaakt();

            }
    }

    private void toonLijsten() {
        System.out.println(lijstPassagier);

        System.out.println(lijstReis);
        System.out.println(lijstTicket);
    }

    // -----------------FUNCTIES VOOR KEUZE MENU ----------------- //




    private void toonPersoon(ArrayList<Passagier> lijstPassagier) {

        for (Passagier p : lijstPassagier) {
            System.out.println(
                    GEEL + "Passagier" + lijstPassagier.indexOf(p) + ":" + RESET +
                            "\nVoornaam passagier: " + p.getVoornaam() +
                            "\nAchternaam passagier: " + p.getAchternaam() +
                            "\nRijksregisternummer van de passagier: " + p.getRijksregisternummer() +
                            "\nGeboortedatum van de passagier: " + p.getGeboortedatum() +
                            "\n");
        }

    }

    private void toonReis(ArrayList<Reis> lijstReis, boolean moetTreinBevatten){
        for (Reis r : lijstReis) {
            ArrayList<Personeelslid> lijstBestuurder = r.getLijstBestuurder();

            System.out.println("[" + GROEN + "Reis"+lijstReis.indexOf(r) + ":" +  RESET +
                    "\n\tTraject: " + r.getTraject() +
                    "\n\tTijdstip:" + r.getTijdstip() +
                    "\n\tLijst bestuurders: ");

            for (Personeelslid b : lijstBestuurder) {
                System.out.println(
                        GEEL + "\t\tBestuurder" + lijstBestuurder.indexOf(b) + ":" + RESET +
                                "\n\t\t\tVoornaam bestuurder: " + b.getVoornaam() +
                                "\n\t\t\tAchternaam bestuurder: " + b.getAchternaam() +
                                "\n\t\t\tRijksregisternummer van de bestuurder: " + b.getRijksregisternummer() +
                                "\n\t\t\tGeboortedatum van de bestuurder: " + b.getGeboortedatum() +
                                "\n\t\t\tCertificaties: " + b.getLijstCertificaties());

            }
            System.out.println("\n\tLijst stewards: ");
            ArrayList<Personeelslid> lijstSteward = r.getLijstSteward();
            for (Personeelslid s : lijstSteward) {
                System.out.println(
                        GEEL + "\t\tSteward" + lijstSteward.indexOf(s) + ":" + RESET +
                                "\n\t\t\tVoornaam steward: " + s.getVoornaam() +
                                "\n\t\t\tAchternaam steward: " + s.getAchternaam() +
                                "\n\t\t\tRijksregisternummer van de steward: " + s.getRijksregisternummer() +
                                "\n\t\t\tGeboortedatum van de steward: " + s.getGeboortedatum() +
                                "\n\t\t\tCertificaties: " + s.getLijstCertificaties())
                ;
            }

            System.out.println("\n\tLijst algemene personeelsleden: ");
            ArrayList<Personeelslid> lijstPersoneel = r.getLijstPersoneelsleden();
            for (Personeelslid pl : lijstPersoneel) {
                System.out.println(
                        GEEL + "\t\tPersoneelsleed" + lijstPersoneel.indexOf(pl) + ":" + RESET +
                                "\n\t\t\tVoornaam personeelsleed: " + pl.getVoornaam() +
                                "\n\t\t\tAchternaam personeelsleed: " + pl.getAchternaam() +
                                "\n\t\t\tRijksregisternummer van de personeelsleed: " + pl.getRijksregisternummer() +
                                "\n\t\t\tGeboortedatum van de personeelsleed: " + pl.getGeboortedatum() +
                                "\n\t\t\tRol van de personeelsleed: " + pl.getRol() +
                                "\n\t\t\tCertificaties: " + pl.getLijstCertificaties()
                );
            }

            System.out.println("]");

            if (moetTreinBevatten) {
                System.out.println("Type trein dat deze reis zal gebruiken: " + r.getTrein().getTypeLocomotief() + ", met: " + r.getTrein().getAantalZitplaatsen() + " aantal zitplaatsen.");
            }
            System.out.println("----------------------------------------------");

        }
    }


    private Persoon maakPersoon(TypePersoon typePersoon, String rol) {

        if (typePersoon == TypePersoon.PASSAGIER) {
            System.out.print("Voornaam: ");
            String voornaam = sc.nextLine();
            System.out.print("Achternaam: ");
            String achternaam = sc.nextLine();
            System.out.print("Rijksregisternummer: ");
            String rijksregisternummer = sc.nextLine();
            System.out.println("Geboortedatum (Formaat: yyyy-MM-dd): ");
            LocalDate geboortedatum = LocalDate.parse(sc.nextLine());

            return new Passagier(voornaam, achternaam, rijksregisternummer, geboortedatum);
        }else if (typePersoon == TypePersoon.PERSONEELSLID) {

            System.out.print("Voornaam van de " + rol + ": ");
            String voornaam = sc.nextLine();
            System.out.print("Achternaam van de " + rol + ": ");
            String achternaam = sc.nextLine();
            System.out.print("Rijksregisternummer van de " + rol + ": ");
            String rijksregisternummer = sc.nextLine();
            System.out.print("Geboortedatum van de " + rol + " (Formaat: yyyy-MM-dd): ");
            LocalDate geboortedatum = LocalDate.parse(sc.nextLine());

            return new Personeelslid(voornaam, achternaam, rijksregisternummer, geboortedatum, rol);
        }

        return null;
    }

    private Personeelslid maakBestuurder() {



        Personeelslid bestuurder = (Personeelslid) maakPersoon(TypePersoon.PERSONEELSLID, "Bestuurder");

        String certificatieBestuurder;
        System.out.println("\nWat zijn de certificaties van de bestuurder ? Typ 'STOP' om te stoppen.");
        do {
            System.out.print("Certificatie van de bestuurder: ");
            certificatieBestuurder = sc.nextLine();

            if (!Objects.equals(certificatieBestuurder, "STOP")) {

                assert bestuurder != null; // checkt dat "bestuurder" niet nul is --> als het null is dan gebeurt er niets
                bestuurder.voegCertificatie(certificatieBestuurder);
            }
        } while (!Objects.equals(certificatieBestuurder, "STOP"));

        return bestuurder;
    }

    private Personeelslid maakSteward() {


        Personeelslid steward = (Personeelslid) maakPersoon(TypePersoon.PERSONEELSLID, "Steward");

        String certificatieSteward;

        System.out.println("\nWat zijn de certificaties van de steward ? Typ 'STOP' om te stoppen.");
        do {
            System.out.print("Certificatie van de steward: ");
            certificatieSteward = sc.nextLine();

            if (!Objects.equals(certificatieSteward, "STOP")) {

                assert steward != null;
                steward.voegCertificatie(certificatieSteward);
            }

        } while (!Objects.equals(certificatieSteward, "STOP"));


        return steward;
    }

    private Personeelslid anderePersoneelslid(String rol){

        Personeelslid anderePersoneelslid = (Personeelslid) maakPersoon(TypePersoon.PERSONEELSLID, rol);

        String certificatieSteward;

        System.out.println("\nWat zijn de certificaties van de " + rol + " ? Typ 'STOP' om te stoppen.");
        do {
            System.out.print("Certificatie van de " + rol +": ");
            certificatieSteward = sc.nextLine();

            if (!Objects.equals(certificatieSteward, "STOP")) {

                assert anderePersoneelslid != null;
                anderePersoneelslid.voegCertificatie(certificatieSteward);
            }

        } while (!Objects.equals(certificatieSteward, "STOP"));


        return anderePersoneelslid;

    }


}

