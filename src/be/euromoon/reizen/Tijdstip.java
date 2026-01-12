package be.euromoon.reizen;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tijdstip implements Serializable {

    private LocalDateTime aankomstPuntA, aankomstPuntB;

    public Tijdstip(LocalDateTime aankomstPuntA, LocalDateTime aankomstPuntB) {
        this.aankomstPuntA = aankomstPuntA;
        this.aankomstPuntB = aankomstPuntB;
    }

    public String getProperAankomstPuntA() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");


        return this.aankomstPuntA.format(fmt);
    }

    public void getProperAankomstPuntB() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

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
