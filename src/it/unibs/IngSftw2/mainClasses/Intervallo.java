package it.unibs.IngSftw2.mainClasses;

public class Intervallo {

    private Orario[] ore = new Orario[2];

    public Intervallo(Orario[] _ore) {
        this.ore = _ore;
    }

    public Orario[] getOre() {
        return ore;
    }
}
