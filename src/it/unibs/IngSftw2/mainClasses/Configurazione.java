package it.unibs.IngSftw2.mainClasses;

public class Configurazione {
    private Sistema sis;
    private ParametriScambi parametri;

    public Configurazione(Sistema _sis,ParametriScambi _parametri){
        sis=_sis;
        parametri=_parametri;
    }

    public Sistema getSis() {
        return sis;
    }

    public ParametriScambi getParametri() {
        return parametri;
    }

}
