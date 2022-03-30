package it.unibs.IngSftw2.mainClasses;

public class Intervallo {

    private Orario[] ore = new Orario[2];

    public Intervallo(Orario[] _ore) {
        this.ore = _ore;
    }

    public Orario[] getOre() {
        return ore;
    }

    public boolean intervalloValido(){
        boolean valido=false;
        if(this.ore[1].getOra()==this.ore[1].getOra()){
            if(this.ore[0].getMinuti()==0 && this.ore[1].getMinuti()==30){
                valido=true;
            }
        }
        if(this.ore[0].getOra() <this.ore[1].getOra())
            valido=true;
        return valido;
    }
}
