package be.euromoon.trein;

import java.io.Serializable;

/**
 * Deze enumeratie representeert de beschikbare types locomotieven die een trein kan aannemen.
 * Elke enumeratie moet een int bevatten dat het aantal wagons bepaald.
 */
public enum TypeLocomotief implements Serializable {

    CLASS_373(12), CLASS_374(14);

    private final int maxAantalWagon;


    /**
     * Maakt een type locomotief aan.
     * @param maxAantalWagon een int die uit elke enumeratie moet komen
     */
    TypeLocomotief(int maxAantalWagon) {
        this.maxAantalWagon = maxAantalWagon;
    }

    public int getMaxAantalWagon() {
        return maxAantalWagon;
    }
}
