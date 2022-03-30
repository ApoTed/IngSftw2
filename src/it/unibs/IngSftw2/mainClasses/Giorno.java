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
                day="martedì";
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

    public static Giorno getGiornoFromString(String gg){

        switch(gg){
            case "lunedì":
                return LUNEDI;
            case "martedì":
                return MARTEDI;

            case "mercoledì":
                return MERCOLEDI;

            case "giovedì":
                return GIOVEDI;

            case "venerdì":
                return VENERDI;

            case "sabato":
                return SABATO;

            case "domenica":
                return DOMENICA;

        }
        return null;
    }
}
