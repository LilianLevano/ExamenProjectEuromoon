package be.euromoon.reizen;

import java.io.Serializable;

public class Traject implements Serializable {

    private String startPunt, eindPunt;

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

