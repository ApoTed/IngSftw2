package it.unibs.IngSftw2.mainClasses;

public class Orario {
    private int ora;
    private int minuti;

    public Orario(int _ora, int _minuti){
        this.minuti=_minuti;
        this.ora =_ora;
    }

    public boolean orarioValido(){
        boolean valido=false;
        if(this.minuti==0 || this.minuti==30){
            if(this.ora >=0 && this.ora <=24){
                valido=true;
            }
        }
        return valido;
    }

    /**
     * metodo che applicato sull'orario di apertura controlla che l'intervallo esista
     * @param end orario fine intervallo
     * @return true se è valido
     */

    public boolean isInsideIntervallo(Orario o1, Orario o2){
        boolean dentro=false;
        if(o1.ora ==this.ora && this.ora ==o2.ora){
            dentro=true;
        }
        if(o1.ora <this.ora && this.ora >o2.ora){
            dentro=true;
        }
        if(this.ora ==o1.ora && this.ora <o2.ora){
            if(this.minuti>=o1.minuti){
                dentro=true;
            }
        }
        if(this.ora >o1.ora && this.ora ==o2.ora){
            if(this.minuti<=o2.minuti){
                dentro=true;
            }
        }
        return dentro;
    }

    public static Orario getOrarioFromString(String ora){
        StringBuffer sb=new StringBuffer();
        sb.append(ora.charAt(0));
        sb.append(ora.charAt(1));
        int o=Integer.valueOf(sb.toString());
        StringBuffer sbm=new StringBuffer();
        sbm.append(ora.charAt(3));
        sbm.append(ora.charAt(4));
        int m=Integer.valueOf(sbm.toString());
        Orario or=new Orario(o,m);
        return or;
    }

    public String toStringOrario(){
        StringBuilder str = new StringBuilder();
        if(this.ora <10){
            str.append("0"+this.ora);
        }
        else{
            str.append(this.ora);
        }

        if(this.minuti==0){
            str.append(":00");
        }
        else{
            str.append(":30");
        }

        return str.toString();
    }

    public int getOra() {
        return ora;
    }

    public int getMinuti() {
        return minuti;
    }

}
