package be.euromoon.reizen;

public class Traject {

    private String startPunt, eindPunt;

    public Traject(String startPunt, String eindPunt) {
        this.startPunt = startPunt;
        this.eindPunt = eindPunt;
    }

    public String getStartPunt() {
        return startPunt;
    }

    public void setStartPunt(String startPunt) {
        this.startPunt = startPunt;
    }

    public String getEindPunt() {
        return eindPunt;
    }

    public void setEindPunt(String eindPunt) {
        this.eindPunt = eindPunt;
    }

    @Override
    public String toString() {
        return "Traject{" +
                "startPunt = " + startPunt +
                ", eindPunt = " + eindPunt +
                '}';
    }
}

