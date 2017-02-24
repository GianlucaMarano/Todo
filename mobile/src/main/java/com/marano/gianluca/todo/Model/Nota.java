package com.marano.gianluca.todo.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private boolean speciale;
    private int id;

    public Nota(String titolo, String corpo, String dataScadenza, boolean speciale) {
        DateFormat data = new SimpleDateFormat("dd MMMM YYYY");
        dataCreazione = data.format(new Date());
        dataUltimaModifica = dataCreazione;
        this.dataScadenza = dataScadenza;
        this.titolo = titolo;
        this.corpo = corpo;
        this.speciale = speciale;
        stato = Stato.DACOMPLETARE;
    }

    public Nota(int id, String titolo, String corpo, String dataCreazione, String dataUltimaModifica,  String dataScadenza, Stato stato, boolean speciale) {
        this.titolo = titolo;
        this.dataCreazione = dataCreazione;
        this.dataUltimaModifica = dataUltimaModifica;
        this.corpo = corpo;
        this.dataScadenza = dataScadenza;
        this.stato = stato;
        this.speciale = speciale;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSpeciale() {
        return speciale;
    }

    public void setSpeciale(boolean speciale) {
        this.speciale = speciale;
    }

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

    @Override
    public String toString() {
        return id + " " + titolo + " " + corpo + " "+ isSpeciale();
    }
}
