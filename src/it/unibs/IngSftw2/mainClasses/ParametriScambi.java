package it.unibs.IngSftw2.mainClasses;

import java.util.ArrayList;

/**
 * calsse per la gestione dei parametri
 */
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

        stb.append("\nScadenza: " + scadenza);
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

    public void togliIntervallo(){
        Intervallo toRemove=ParametriScambi.addIntervallo();
        boolean presente=false;
        int countI=0;
        for(Intervallo x: this.intervalli){
            if(!presente){
                countI++;
            }
            if(x.compareIntervalllo(toRemove)){
                presente=true;
            }

        }
        if (presente) {
            this.intervalli.remove(countI-1);
            if(this.intervalli.size()==0){
                System.out.println("ci deve essere almeno un intervallo quindi aggiungi un intervallo");
                this.intervalli.add(ParametriScambi.addIntervallo());
            }
        }
        else
            System.out.println("l'intervallo inserito non è presente");
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

    public void togliGionro(){
        String nomeGiorno=Utilita.leggiStringaNonVuota("Inserisci il nome del giorno da rimuovere: ");
        Giorno toRemove=Giorno.getGiornoFromString(nomeGiorno);
        boolean gironoEsiste=false;
        if(toRemove!=null ){
            if(this.giorni.contains(toRemove)){
               giorni.remove(toRemove);
               gironoEsiste=true;
            }
        }
        if(!gironoEsiste)
            System.out.println("il giorno inserito non è presente o il dato inserito non è corretto");
        if(this.giorni.size()==0){
            System.out.println("deve esserci almeno un giorno quindi inserisci un giorno");
            this.addGiorno();
        }
    }

    public void addScadenza(){
        int scad=Utilita.leggiIntero("inserisci la nuova scadenza: ",1,999999);
        this.scadenza=scad;
    }
    public void addLuogo(){
        String luogo=Utilita.leggiStringaNonVuota("Inserisci il nuovo luogo: ");
        this.luoghi.add(luogo);
    }

    public void togliLuogo(){
        String luogoToRemove=Utilita.leggiStringaNonVuota("inserisci il nome del luogo da togliere");
        if(this.luoghi.contains(luogoToRemove)){
            this.luoghi.remove(luogoToRemove);
        }
        else{
            System.out.println("questo luogo non è tra i luoghi presenti");
        }
        if(this.luoghi.size()==0){
            String luogoMinimo=Utilita.leggiStringaNonVuota("Deve esserci sempre almeno un luogo quindi aggiungi un luogo: ");
            this.luoghi.add(luogoMinimo);
        }
    }

    public void modificaParamentri(){
        System.out.println("Questi sono i parametri:\n"+this.toStringParametri()+"\n");
        int choise=Utilita.leggiIntero("Inserisci\n1 per modificare i luoghi\n" +
                "2 per modificare gli intervalli\n" +
                "3 per modificare i giorni\n" +
                "4 per cambiare la scadenza: ",1,4);

        switch(choise){
            case 1:
                int choiceL=Utilita.leggiIntero("Inserisci 1 se vuoi aggiungere 2 se vuoi togliere: ",1,2);
                if(choiceL==1)
                    this.addLuogo();
                else
                    this.togliLuogo();
                break;
            case 2:
                int choiceI=Utilita.leggiIntero("Inserisci 1 se vuoi aggiungere 2 se vuoi togliere: ",1,2);
                if(choiceI==1)
                    this.intervalli.add(ParametriScambi.addIntervallo());
                else
                    this.togliIntervallo();
                break;
            case 3:
                int choiceG=Utilita.leggiIntero("Inserisci 1 se vuoi aggiungere 2 se vuoi togliere: ",1,2);
                if(choiceG==1)
                    this.addGiorno();
                else
                    this.togliGionro();
                break;
            case 4:
                this.addScadenza();
                break;
        }
    }
}
