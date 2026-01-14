package be.euromoon.reizen;

import java.io.Serializable;

/**
 * Deze klasse representeert een traject. Wordt gebruikt om een punt A en B te koppelen aan een reis.
 */
public class Traject implements Serializable {

    private final String startPunt, eindPunt;

    /**
     * Maak een object Traject aan.
     * @param startPunt een String met een startpunt
     * @param eindPunt een String met een eindpunt
     */
    public Traject(String startPunt, String eindPunt) {
        this.startPunt = startPunt;
        this.eindPunt = eindPunt;
    }

    public String getStartPunt() {
        return startPunt;
    }



    public String getEindPunt() {
        return eindPunt;
    }



    @Override
    public String toString() {
        return
                "startPunt: " + startPunt +
                ", eindPunt: " + eindPunt;
    }
}

