package com.marano.gianluca.todo.Model;

/**
 * Created by Gianluca Marano on 20/02/2017.
 */

public class Nota {
    private String titolo;
    private String dataCreazione;
    private String dataUltimaModifica;
    private String corpo;
    private String dataScadenza;
    private Stato stato;

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(String dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String getDataUltimaModifica() {
        return dataUltimaModifica;
    }

    public void setDataUltimaModifica(String dataUltimaModifica) {
        this.dataUltimaModifica = dataUltimaModifica;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }
}
