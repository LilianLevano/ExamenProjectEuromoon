package be.euromoon.trein;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class Trein {

    private TypeLocomotief typeLocomotief;
    private int aantalZitplaatsen;
    private static int treinTeller = 0;
    private ArrayList<Wagon> lijstWagons;

    public Trein(TypeLocomotief typeLocomotief) {
        this.typeLocomotief = typeLocomotief;

        if (typeLocomotief == TypeLocomotief.CLASS_373){
            aantalZitplaatsen = 80*typeLocomotief.getMaxAantalWagon();
            this.lijstWagons = new ArrayList<>(12);

            for(int i = 0; i < 12; i++){
                lijstWagons.add(new Wagon());
            }
        }else if(typeLocomotief == TypeLocomotief.CLASS_374){
            aantalZitplaatsen = 80*typeLocomotief.getMaxAantalWagon();
            this.lijstWagons = new ArrayList<>(14);
            for(int i = 0; i < 14; i++){
                lijstWagons.add(new Wagon());
            }
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

        if (typeLocomotief == TypeLocomotief.CLASS_373){
            aantalZitplaatsen = 80*typeLocomotief.getMaxAantalWagon();
        }else if(typeLocomotief == TypeLocomotief.CLASS_374){
            aantalZitplaatsen = 80*typeLocomotief.getMaxAantalWagon();
        }
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
