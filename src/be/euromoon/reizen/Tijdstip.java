package be.euromoon.reizen;

import java.time.LocalDateTime;

public class Tijdstip {

    private LocalDateTime aankomstPuntA, aankomstPuntB;

    public Tijdstip(LocalDateTime aankomstPuntA, LocalDateTime aankomstPuntB) {
        this.aankomstPuntA = aankomstPuntA;
        this.aankomstPuntB = aankomstPuntB;
    }

    @Override
    public String toString() {
        return
                "vertrek uur: " + aankomstPuntA +
                ", aankomstuur:  " + aankomstPuntB;

    }

    public LocalDateTime getAankomstPuntA() {
        return aankomstPuntA;
    }



    public LocalDateTime getAankomstPuntB() {
        return aankomstPuntB;
    }


}
