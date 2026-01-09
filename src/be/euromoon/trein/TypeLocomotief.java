package be.euromoon.trein;

public enum TypeLocomotief {

    CLASS_373(12), CLASS_374(14);

    private final int maxAantalWagon;

    TypeLocomotief(int maxAantalWagon) {
        this.maxAantalWagon = maxAantalWagon;
    }

    public int getMaxAantalWagon() {
        return maxAantalWagon;
    }
}
