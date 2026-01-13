package be.euromoon.trein;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Deze klasse representeert een trein.
 * Een trein bevat een waarde uit het enumeratie TypeLocomotief, die zijn aantal zitplaatsen vast stelt.
 */
public class Trein {

    private TypeLocomotief typeLocomotief;
    private int aantalZitplaatsen;
    private final ArrayList<Wagon> lijstWagons;

    /**
     * Maakt een object Trein aan.
     * Elk nieuw object Trein wordt aangevuld met een aantal wagons, afhankelijk van de type locomotief.
     * Aantal zitplaatsen worden ook daarvan berekend.
     * @param typeLocomotief een waarde uit de enumeratie TypeLocomotief
     */
    public Trein(TypeLocomotief typeLocomotief) {
        this.typeLocomotief = typeLocomotief;

        aantalZitplaatsen = typeLocomotief.getMaxAantalWagon() * 80;
        this.lijstWagons = new ArrayList<>(typeLocomotief.getMaxAantalWagon());

        for(int i = 0; i < typeLocomotief.getMaxAantalWagon(); i++) {
            lijstWagons.add(new Wagon());
        }
    }

    public ArrayList<Wagon> getLijstWagons() {
        return lijstWagons;
    }

    public TypeLocomotief getTypeLocomotief() {
        return typeLocomotief;
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
