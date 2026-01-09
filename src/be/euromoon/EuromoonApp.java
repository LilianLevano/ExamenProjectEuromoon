package be.euromoon;

import be.euromoon.persoon.Passagier;
import be.euromoon.reizen.Reis;
import be.euromoon.reizen.Tijdstip;
import be.euromoon.reizen.Traject;
import be.euromoon.trein.Trein;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class EuromoonApp {

    public static final String GROEN = "\u001B[32m";
    public static final String GEEL = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    Scanner sc = new Scanner(System.in);
    private ArrayList<Passagier> lijstPassagier = new ArrayList<>();
    private ArrayList<Trein> lijstTrein = new ArrayList<>();
    private ArrayList<Reis> lijstReis = new ArrayList<>();

    public void start(){
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

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:
                    toonLijsten();
                    break;
            }
        } while (keuzeMenu != 9);

    }

    public void passagierRegistreren(){

        try{

            System.out.print("Voornaam: ");
            String voornaam = sc.nextLine();
            System.out.print("Achternaam: ");
            String achternaam = sc.nextLine();
            System.out.print("Rijksregisternummer: ");
            String rijksregisternummer = sc.nextLine();
            System.out.println("Geboortedatum (Formaat: yyyy-MM-dd): ");
            LocalDate geboortedatum = LocalDate.parse(sc.nextLine());

            int grootteLijstVoorAanpassing = lijstPassagier.size();
            Passagier p = new Passagier(voornaam, achternaam, rijksregisternummer, geboortedatum );
            lijstPassagier.add(p);

            if (grootteLijstVoorAanpassing != lijstPassagier.size()){
                System.out.println(GROEN + "Passagier werd succesvol aangemaakt" + RESET);
            }else{
                System.err.println("Passagier werd niet aangemaakt.");
            }


        } catch (DateTimeParseException e) {
            System.err.println("Foutieve invoer: Je hebt een foute formaat ingegeven bij het geboortedatum. Passagier werd niet aangemaakt, probeer opnieuw.");
        }
    }

    public void maakReis(){

        try{

            System.out.print("Start punt van het traject: ");
            String startpunt = sc.nextLine();
            System.out.print("Eind punt van het traject: ");
            String eindpunt = sc.nextLine();
            Traject t = new Traject(startpunt, eindpunt);

            System.out.print("Startuur van het traject (Formaat: yyyy-MM-ddThh:mm:ss): ");
            LocalDateTime start = LocalDateTime.parse(sc.nextLine());
            System.out.print("Einduur van het traject (Formaat: yyyy-MM-ddThh:mm:ss): ");
            LocalDateTime eind = LocalDateTime.parse(sc.nextLine());
            Tijdstip ti = new Tijdstip(start, eind);


            Reis r = new Reis(t, ti);
            int grootteLijstVoorAanpassing = lijstReis.size();
            lijstReis.add(r);

            if (grootteLijstVoorAanpassing != lijstReis.size()){
                System.out.println(GROEN + "Reis werd succesvol aangemaakt" + RESET);
            }else{
                System.err.println("Reis werd niet aangemaakt.");
            }

        } catch (DateTimeParseException e) {
            System.err.println("Foutieve invoer: Je hebt een foute formaat ingegeven bij een van de tijdstip. Reis werd niet aangemaakt, probeer opnieuw.");
        }


    }

    public void toonLijsten(){
        System.out.println(lijstPassagier.toString());
        System.out.println(lijstTrein.toString());
        System.out.println(lijstReis.toString());
    }

    public void treinAanReisKoppelen(){

        try{

        }catch (Exception e){
            System.err.println("Fout");
        }

    }

}
