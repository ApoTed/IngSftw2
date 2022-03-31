package it.unibs.IngSftw2.mainClasses;

import java.util.ArrayList;

/**
 * Classe per la gestione dei parametri degli scambi
 * @author Jacopo Tedeschi,Enrico Zambelli
 */
public class ParametriScambi {
    private String piazza;
    private ArrayList <String> luoghi=new ArrayList<>();
    private ArrayList <Giorno> giorni=new ArrayList<>();
    private ArrayList <Intervallo> intervalli=new ArrayList<>();
    private int scadenza;

    /**
     * Costruttore della classe ParametriScambi
     * @param _piazza la piazza in cui avvengono gli scambi
     * @param _luoghi i luoghi della piazza in cui avvengono gli scambi
     * @param _giorni i giorni in cui avvengono gli scambi
     * @param _intervalli gli intervalli di tempo in cui avvengono gli scambi
     * @param _scadenza il numero massimo di giorni entro cui un fruitore può accettare una proposta di scambio
     */
    public ParametriScambi(String _piazza, ArrayList <String> _luoghi, ArrayList <Giorno> _giorni, ArrayList<Intervallo> _intervalli, int _scadenza){
        this.giorni=_giorni;
        this.intervalli=_intervalli;
        this.piazza=_piazza;
        this.luoghi=_luoghi;
        this.scadenza=_scadenza;
    }

    /**
     * Metodo get per i luoghi di scambio
     * @return i luoghi di scambio
     */
    public ArrayList<String> getLuoghi() {
        return luoghi;
    }
    /**
     * Metodo get per la piazza di scambio
     * @return la piazza di scambio
     */
    public String getPiazza() {
        return piazza;
    }
    /**
     * Metodo get per i giorni di scambio
     * @return i giorni di scambio
     */
    public ArrayList<Giorno> getGiorni() {
        return giorni;
    }
    /**
     * Metodo get per gli intervalli di scambio
     * @return gli intervalli di scambio
     */
    public ArrayList<Intervallo> getIntervalli() {
        return intervalli;
    }
    /**
     * Metodo get per la scadenza di accettazione
     * @return la scadenza di accettazione
     */
    public int getScadenza() {
        return scadenza;
    }

    /**
     * Metodo che restituisce la stringa corrispondente alla descrizione dei parametri
     * @return la stringa di visualizzazione dei parametri
     */
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

    /**
     * Metodo statico per l'inserimento dei parametri del sistema per la prima volta
     * @return i parametri di scambio inseriti
     */
    public static ParametriScambi inserimentoParametri(){
        String piazza=Utilita.leggiStringaNonVuota("Inserisci il nome della città in cui avvengono gli scambi:");
        int scelta=1;
        ArrayList<String> luoghi=new ArrayList<>();
        while(scelta!=0){
            String luogo=Utilita.leggiStringaNonVuota("Inserisci il nome del luogo in cui vengono effettuati gli scambi:");
            luoghi.add(luogo);
            scelta=Utilita.leggiIntero("Inserisci 1 per aggiungere un altro luogo, 0 altrimenti:",Menu.ZERO,Menu.UNO);
        }
        ArrayList<Giorno> giorni=new ArrayList<>();
        scelta=1;
        Giorno g;
        boolean giornoCorretto=true;
        int count=0;
        while(scelta!=0){
            do {
                String giorno = Utilita.leggiStringaNonVuota("Inserisci il nome del giorno in cui vengono effettuati gli scambi:");
                g = Giorno.getGiornoFromString(giorno);
                if(g==null){
                    System.out.println("Il giorno inserito è inesistente");
                    giornoCorretto=false;
                }
                if(giorni.contains(g)){
                    System.out.println("Questo giorno è già stato inserito");
                    giornoCorretto=false;
                }
                if(g!=null && !giorni.contains(g))
                    count++;
                giornoCorretto=true;
            }while(count==0);
            if(giornoCorretto){
                giorni.add(g);
            }
            scelta=Utilita.leggiIntero("Inserisci 1 per aggiungere un altro giorno, 0 altrimenti:",Menu.ZERO,Menu.UNO);
        }

        ArrayList<Intervallo> intervalli=new ArrayList<>();
        scelta=1;
        while(scelta!=0){
            boolean intervalloValido=false;
            Orario inizio;
            Orario fine;
            do {
                Orario[] orari=new Orario[2];
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
                orari[0]=inizio;
                orari[1]=fine;
                Intervallo intervallo=Intervallo.creaintervallo(orari);
                if(!intervallo.intervalloValido()){
                    System.out.println("L'intervallo inserito non è valido");

                }
                else{
                    intervalli.add(intervallo);
                    intervalloValido=true;
                }


            }while(!intervalloValido);
            scelta=Utilita.leggiIntero("Inserisci 1 per introdurre un nuovo intervallo, 0 altrimenti:");
        }

        int scadenza=Utilita.leggiIntero("Inserisci il numero di giorni disponibili per accettare un'offerta:");

        return new ParametriScambi(piazza,luoghi,giorni,intervalli,scadenza);

    }

}
