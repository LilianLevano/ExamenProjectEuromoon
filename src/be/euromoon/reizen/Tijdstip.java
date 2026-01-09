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

    public void setAankomstPuntA(LocalDateTime aankomstPuntA) {
        this.aankomstPuntA = aankomstPuntA;
    }

    public LocalDateTime getAankomstPuntB() {
        return aankomstPuntB;
    }

    public void setAankomstPuntB(LocalDateTime aankomstPuntB) {
        this.aankomstPuntB = aankomstPuntB;
    }
}
