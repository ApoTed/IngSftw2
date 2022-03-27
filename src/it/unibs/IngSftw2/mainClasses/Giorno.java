package it.unibs.IngSftw2.mainClasses;

public enum Giorno {
    LUNEDI,
    MARTEDI,
    MERCOLEDI,
    GIOVEDI,
    VENERDI,
    SABATO,
    DOMENICA;

    public String toString(){
        String day=null;
        switch(this){
            case LUNEDI :
                day="lunedì";
                break;
            case MARTEDI:
                day="martedi";
                break;
            case MERCOLEDI:
                day="mercoledì";
                break;
            case GIOVEDI:
                day="giovedì";
                break;
            case VENERDI:
                day="venerdì";
                break;
            case SABATO:
                day="sabato";
                break;
            case DOMENICA:
                day="domenica";
                break;

        }
        return day;
    }
}
