package be.euromoon.reizen;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deze klasse representeert een tijdstip.
 * Wordt gebruikt om een tijdstip aan een reis te koppelen.
 */
public class Tijdstip implements Serializable {

    private final LocalDateTime aankomstPuntA, aankomstPuntB ;

    /**
     * Maakt een Tijdstip object aan.
     * @param aankomstPuntA een LocalDateTime van de startuur
     * @param aankomstPuntB een LocalDateTime van einduur
     */
    public Tijdstip(LocalDateTime aankomstPuntA, LocalDateTime aankomstPuntB) {
        this.aankomstPuntA = aankomstPuntA;
        this.aankomstPuntB = aankomstPuntB;
    }

    /**
     * Deze getter wordt gebruikt om een formaat te krijgen die een file kan aannemen als een bestandsnaam.
     * @return een String met een geformatteerde LocalDateTime in formaat: "yyyy-MM-dd_HH-mm-ss"
     */
    public String getProperAankomstPuntA() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return this.aankomstPuntA.format(fmt);
    }

    /**
     * Deze getter wordt gebruikt om een formaat te krijgen die een file kan aannemen als een bestandsnaam.
     * @return een String met een geformatteerde LocalDateTime in formaat: "yyyy-MM-dd_HH-mm-ss"
     */
    public String getProperAankomstPuntB() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return this.aankomstPuntB.format(fmt);
    }



    @Override
    public String toString() {
        return
                "vertrek uur: " + aankomstPuntA +
                ", aankomstuur:  " + aankomstPuntB;

    }


}
