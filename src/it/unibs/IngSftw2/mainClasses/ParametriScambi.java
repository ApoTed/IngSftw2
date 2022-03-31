package it.unibs.IngSftw2.mainClasses;

import java.util.ArrayList;

public class ParametriScambi {
    private String piazza;
    private ArrayList<String> luoghi = new ArrayList<>();
    private ArrayList<Giorno> giorni = new ArrayList<>();
    private ArrayList<Intervallo> intervalli = new ArrayList<>();
    private int scadenza;

    public ParametriScambi(String _piazza, ArrayList<String> _luoghi, ArrayList<Giorno> _giorni, ArrayList<Intervallo> _intervalli, int _scadenza) {
        this.giorni = _giorni;
        this.intervalli = _intervalli;
        this.piazza = _piazza;
        this.luoghi = _luoghi;
        this.scadenza = _scadenza;
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

    public String toStringParametri() {
        StringBuffer stb = new StringBuffer();
        stb.append("Piazza: " + this.piazza);
        stb.append("\nLuoghi: ");
        for (int i = 0; i < luoghi.size(); i++) {
            stb.append(luoghi.get(i));
            if (i != luoghi.size() - 1) {
                stb.append(",");
            }
        }
        stb.append(".");
        stb.append("\nGiorni: ");
        for (int i = 0; i < giorni.size(); i++) {
            stb.append(giorni.get(i).toString());
            if (i != giorni.size() - 1) {
                stb.append(",");
            }
        }
        stb.append(".");
        for (Intervallo x : this.intervalli) {
            stb.append("\nOrario: " + x.getOre()[0].toStringOrario() + "-" + x.getOre()[1].toStringOrario());
        }

        //stb.append("\nScadenza: " + scadenza);
        return stb.toString();
    }

    public static ParametriScambi inserimentoParametri() {
        String piazza = Utilita.leggiStringaNonVuota("Inserisci il nome della città in cui avvengono gli scambi:");
        int scelta = 1;
        ArrayList<String> luoghi = new ArrayList<>();
        while (scelta != 0) {
            String luogo = Utilita.leggiStringaNonVuota("Inserisci il nome del luogo in cui vengono effettuati gli scambi:");
            luoghi.add(luogo);
            scelta = Utilita.leggiIntero("Inserisci 1 per aggiungere un altro luogo, 0 altrimenti:", Menu.ZERO, Menu.UNO);
        }
        ArrayList<Giorno> giorni = new ArrayList<>();
        scelta = 1;
        Giorno g;
        boolean giornoCorretto = true;
        int count = 0;
        while (scelta != 0) {
            do {
                String giorno = Utilita.leggiStringaNonVuota("Inserisci il nome del giorno in cui vengono effettuati gli scambi:");
                g = Giorno.getGiornoFromString(giorno);
                if (g == null) {
                    System.out.println("Il giorno inserito è inesistente");
                    giornoCorretto = false;
                }
                if (giorni.contains(g)) {
                    System.out.println("questo giorno è già stato inserito");
                    giornoCorretto = false;
                }
                if (g != null && !giorni.contains(g))
                    count++;
                giornoCorretto = true;
            } while (count == 0);
            if (giornoCorretto) {
                giorni.add(g);
            }
            scelta = Utilita.leggiIntero("Inserisci 1 per aggiungere un altro giorno, 0 altrimenti:", Menu.ZERO, Menu.UNO);
        }

        ArrayList<Intervallo> intervalli = new ArrayList<>();
        scelta = 1;
        while (scelta != 0) {
            intervalli.add(ParametriScambi.addIntervallo());
            scelta = Utilita.leggiIntero("Inserisci 1 per introdurre un nuovo intervallo, 0 altrimenti:");
        }

        int scadenza = Utilita.leggiIntero("Inserisci il numero di giorni disponibili per accettare un'offerta:");

        return new ParametriScambi(piazza, luoghi, giorni, intervalli, scadenza);

    }

    public void addLuogo(String s) {
        this.luoghi.add(s);
    }

    public static Intervallo addIntervallo() {
        boolean intervalloValido = false;
        Orario inizio;
        Orario fine;
        Intervallo intervallo = null;
        do {
            Orario[] orari = new Orario[2];
            boolean finito = false;
            do {
                int ora = Utilita.leggiIntero("Inserisci l'ora dell'inizio dell'intervallo(compresa tra 0 e 24):");
                int minuti = Utilita.leggiIntero("Inserisci i minuti dell'inizio dell'intervallo(0 oppure 30):");
                inizio = new Orario(ora, minuti);
                if (inizio.orarioValido()) {
                    finito = true;
                } else {
                    System.out.println("L'orario inserito non è valido");
                }
            } while (!finito);
            finito = false;
            do {
                int ora = Utilita.leggiIntero("Inserisci l'ora della fine dell'intervallo(compresa tra 0 e 24):");
                int minuti = Utilita.leggiIntero("Inserisci i minuti della fine dell'intervallo(0 oppure 30):");
                fine = new Orario(ora, minuti);
                if (fine.orarioValido()) {
                    finito = true;
                } else {
                    System.out.println("L'orario inserito non è valido");
                }
            } while (!finito);
            orari[0] = inizio;
            orari[1] = fine;
            intervallo = Intervallo.creaintervallo(orari);
            if (!intervallo.intervalloValido()) {
                System.out.println("L'intervallo inserito non è valido");

            } else {
                intervalloValido = true;
            }


        } while (!intervalloValido);
        return intervallo;

    }

    public void addGiorno() {
        boolean giornoCorretto = true;
        Giorno g = null;
        do {
            String giorno = Utilita.leggiStringaNonVuota("Inserisci il nome del giorno in cui vengono effettuati gli scambi:");
            g = Giorno.getGiornoFromString(giorno);
            if (g == null) {
                System.out.println("Il giorno inserito è inesistente");
                giornoCorretto = false;
            }
            if (giorni.contains(g)) {
                System.out.println("questo giorno è già stato inserito");
                giornoCorretto = false;
            }
            if (g != null && !giorni.contains(g))
                giornoCorretto = true;
        } while (!giornoCorretto);
        this.giorni.add(g);
    }

    public void addScadenza(){
        int scad=Utilita.leggiIntero("inserisci la nuova scadenza");
        this.scadenza=scad;
    }
    public void addLuogo(){
        String luogo=Utilita.leggiStringaNonVuota("Inserisci il nuovo luogo:");
        this.luoghi.add(luogo);
    }

    public void modificaParamentri(){
        int choise=Utilita.leggiIntero("inserisci 1 per aggiungere un luogo\n" +
                "2 per aggiungere un intervallo\n" +
                "3 per aggiungere un giorno\n" +
                "4 per cambiare la scadenza:",1,4);
        switch(choise){
            case 1:
                this.addLuogo();
                break;
            case 2:
                this.intervalli.add(ParametriScambi.addIntervallo());
                break;
            case 3:
                this.addGiorno();
                break;
            case 4:
                this.addScadenza();
                break;
        }
    }
}
