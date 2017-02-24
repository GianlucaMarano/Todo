package com.marano.gianluca.todo.model;

/**
 * Created by Gianluca Marano on 20/02/2017.
 */
public enum Stato {
    COMPLETATO,
    DACOMPLETARE;

   public static Stato getValue(String string ){
       if(string.equals("Da completare"))
       {
           return DACOMPLETARE;
       }
       else if (string.equals("Completato")){
           return COMPLETATO;
       }
       return DACOMPLETARE;
   }

    @Override
    public String toString() {
        switch (this){
            case COMPLETATO: return "Completato";
            case DACOMPLETARE:return "Da completare";
        }
        return null;
    }
}
