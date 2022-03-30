package it.unibs.IngSftw2.mainClasses;

public class Orario {
    private int ore;
    private int minuti;

    public Orario(int _ore, int _minuti){
        this.minuti=_minuti;
        this.ore=_ore;
    }

    public boolean orarioValido(){
        boolean valido=false;
        if(this.minuti==0 || this.minuti==30){
            if(this.ore>=0 && this.ore<=24){
                valido=true;
            }
        }
        return valido;
    }

    /**
     * metodo che applicato sull'orario di apertura controllache l'intervallo esista
     * @param end orario fine intervallo
     * @return true se è valido
     */
    public boolean intervalloValido(Orario end){
        boolean valido=false;
        if(this.ore==end.ore){
            if(this.minuti==0 && end.minuti==30){
                valido=true;
            }
        }
        if(this.ore<end.ore)
            valido=true;
        return valido;
    }
    public boolean isInsideIntervallo(Orario o1, Orario o2){
        boolean dentro=false;
        if(o1.ore==this.ore && this.ore==o2.ore){
            dentro=true;
        }
        if(o1.ore<this.ore && this.ore>o2.ore){
            dentro=true;
        }
        if(this.ore==o1.ore && this.ore<o2.ore){
            if(this.minuti>=o1.minuti){
                dentro=true;
            }
        }
        if(this.ore>o1.ore && this.ore==o2.ore){
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
        if(this.ore<10){
            str.append("0"+this.ore);
        }
        else{
            str.append(this.ore);
        }

        if(this.minuti==0){
            str.append(":00");
        }
        else{
            str.append(":30");
        }
        return str.toString();
    }
}
