package be.euromoon.persoon;

public enum TypePersoneel{
    CONDUCTEURS("Conducteur"), STEWARDS("Steward"), BAGAGEPERSONEEL("Bagage Personeel");

    private String rol;

    TypePersoneel(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return this.rol;
    }
}