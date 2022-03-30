package it.unibs.IngSftw2.mainClasses;

import java.util.ArrayList;

public class ParametriScambi {
    private String piazza;
    private ArrayList <String> luoghi=new ArrayList<>();
    private ArrayList <Giorno> giorni=new ArrayList<>();
    private ArrayList <Intervallo> intervalli=new ArrayList<>();
    private int scadenza;

    public ParametriScambi(String _piazza, ArrayList <String> _luoghi, ArrayList <Giorno> _giorni, ArrayList<Intervallo> _intervalli, int _scadenza){
        this.giorni=_giorni;
        this.intervalli=_intervalli;
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

    public ArrayList<Intervallo> getIntervalli() {
        return intervalli;
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
        for(Intervallo x : this.intervalli){
            stb.append("\nOrario: " + x.getOre()[0].toStringOrario() + "-" + x.getOre()[1].toStringOrario());
        }

        //stb.append("\nScadenza: " + scadenza);
        return stb.toString();
    }
}
