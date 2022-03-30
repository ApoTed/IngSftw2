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

    public Orario[] getOre() {
        return ore;
    }

    public int getScadenza() {
        return scadenza;
    }

    public String toStringParametri(){
        StringBuffer stb=new StringBuffer();
        stb.append("Piazza: "+this.piazza);
        stb.append("\nLuoghi: ");
        for(int i=0;i<luoghi.size();i++){
            stb.append(luoghi.get(i));
            if(i!=luoghi.size()-1){
                stb.append(",");
            }
        }
        stb.append(".");
        stb.append("\nGiorni: ");
        for(int i=0;i<giorni.size();i++){
            stb.append(giorni.get(i).toString());
            if(i!=giorni.size()-1){
                stb.append(",");
            }
        }
        stb.append(".");
        stb.append("\nOrario: " + ore[0].toStringOrario() + "-" + ore[1].toStringOrario());
        //stb.append("\nScadenza: " + scadenza);
        return stb.toString();
    }
}
