package be.euromoon.trein;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class Trein {

    private TypeLocomotief typeLocomotief;
    private int aantalZitplaatsen;
    private static int treinTeller = 0;
    private final ArrayList<Wagon> lijstWagons;

    public Trein(TypeLocomotief typeLocomotief) {
        this.typeLocomotief = typeLocomotief;


        aantalZitplaatsen = typeLocomotief.getMaxAantalWagon() * 80;
        this.lijstWagons = new ArrayList<>(typeLocomotief.getMaxAantalWagon());

        for(int i = 0; i < typeLocomotief.getMaxAantalWagon(); i++) {
            lijstWagons.add(new Wagon());
        }
        treinTeller++;



    }

    public ArrayList<Wagon> getLijstWagons() {
        return lijstWagons;
    }

    public TypeLocomotief getTypeLocomotief() {
        return typeLocomotief;
    }

    public void setTypeLocomotief(TypeLocomotief typeLocomotief) {
        this.typeLocomotief = typeLocomotief;
        aantalZitplaatsen = typeLocomotief.getMaxAantalWagon() * 80;

    }

    public int getAantalZitplaatsen() {
        return aantalZitplaatsen;
    }

    @Override
    public String toString() {
        return "Trein{" +
                "typeLocomotief = " + typeLocomotief +
                ", aantalZitplaatsen = " + aantalZitplaatsen +
                '}';
    }
}
