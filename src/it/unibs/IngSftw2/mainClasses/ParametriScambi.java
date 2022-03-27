package it.unibs.IngSftw2.mainClasses;

import java.util.ArrayList;

public class ParametriScambi {
    private String piazza;
    private ArrayList <String> luoghi=new ArrayList<>();
    private ArrayList <Giorno> giorni=new ArrayList<>();
    private Orario[] ore = new Orario[2];
    private int scadenza;

    public ParametriScambi(String _piazza, ArrayList <String> _luoghi, ArrayList <Giorno> _giorni, Orario[] _ore, int _scadenza){
        this.giorni=_giorni;
        this.ore=_ore;
        this.piazza=_piazza;
        this.luoghi=_luoghi;
        this.scadenza=_scadenza;
    }

    public ArrayList<String> getLuoghi() {
        return luoghi;
    }

    public String getPiazza() {
        return piazza;
    }

    public ArrayList<Giorno> getGiorni() {
        return giorni;
    }
}
