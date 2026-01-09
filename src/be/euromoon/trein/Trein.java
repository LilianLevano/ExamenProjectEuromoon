package be.euromoon.trein;

public class Trein {

    private TypeLocomotief typeLocomotief;
    private int aantalZitplaatsen;
    private static int treinTeller = 0;

    public Trein(TypeLocomotief typeLocomotief) {
        this.typeLocomotief = typeLocomotief;

        if (typeLocomotief == TypeLocomotief.CLASS_373){
            aantalZitplaatsen = 80*typeLocomotief.getMaxAantalWagon();
        }else if(typeLocomotief == TypeLocomotief.CLASS_374){
            aantalZitplaatsen = 80*typeLocomotief.getMaxAantalWagon();
        }
        treinTeller++;

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
