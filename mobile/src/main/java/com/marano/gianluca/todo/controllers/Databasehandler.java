package com.marano.gianluca.todo.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.marano.gianluca.todo.model.Nota;
import com.marano.gianluca.todo.model.Stato;

import java.util.ArrayList;

/**
 * Created by Gianluca Marano on 22/02/2017.
 */

public class Databasehandler extends SQLiteOpenHelper {

    // Notas Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_BODY = "body";
    private static final String KEY_DATA_CREAZIONE = "data_creazione";
    private static final String KEY_DATA_ULTIMA_MODIFICA = "data_ultima_modifica";
    private static final String KEY_DATA_SCADENZA = "data_scadenza";
    private static final String KEY_STATO = "stato";
    private static final String KEY_SPECIALE = "speciale";

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Note";

    // Contacts table name
    private static final String TABLE_NotaS = "nota";

    public Databasehandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_Nota_TABLE = " CREATE TABLE " + TABLE_NotaS + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_BODY + " TEXT, " + KEY_DATA_CREAZIONE + " TEXT, " + KEY_DATA_ULTIMA_MODIFICA + " TEXT, "
                + KEY_DATA_SCADENZA + " TEXT, " + KEY_STATO + " TEXT, " + KEY_SPECIALE + " BOOLEAN " + ")";
        db.execSQL(CREATE_Nota_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NotaS);
        // Create tables again
        onCreate(db);

    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */


    public void addNota(Nota nota) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, nota.getTitolo());
        values.put(KEY_BODY, nota.getCorpo());
        values.put(KEY_DATA_CREAZIONE, nota.getDataCreazione());
        values.put(KEY_DATA_ULTIMA_MODIFICA, nota.getDataUltimaModifica());
        values.put(KEY_DATA_SCADENZA, nota.getDataScadenza());
        values.put(KEY_STATO, nota.getStato().toString());
        values.put(KEY_SPECIALE, nota.isSpeciale());
        // Inserting Row
        long id = db.insert(TABLE_NotaS, null, values);
        nota.setId((int) id);
        db.close(); // Closing database connection
    }

    // Getting All Notas
    public ArrayList<Nota> getAllNotas() {
        ArrayList<Nota> NotasList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NotaS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            boolean speciale = false;
            do {
                if (cursor.getString(7).equals("1")) speciale = true;
                Nota Nota = new Nota(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5),
                        Stato.getValue(cursor.getString(6)), speciale);
                // Adding Nota to list
                NotasList.add(Nota);
            } while (cursor.moveToNext());
        }

        // return Notas list
        return NotasList;
    }

    public Nota getNota(int id) {
        boolean speciale = false;
        String selectQuery = "SELECT  * FROM " + TABLE_NotaS + " where " + KEY_ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getString(7).equals("1")) speciale = true;
        Nota nota = new Nota(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5),
                Stato.getValue(cursor.getString(6)), speciale);
        return nota;
    }

    // Updating single Nota
    public int updateNota(Nota Nota) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, Nota.getTitolo());
        values.put(KEY_BODY, Nota.getCorpo());
        values.put(KEY_DATA_CREAZIONE, Nota.getDataCreazione());
        values.put(KEY_DATA_ULTIMA_MODIFICA, Nota.getDataUltimaModifica());
        values.put(KEY_DATA_SCADENZA, Nota.getDataScadenza());
        values.put(KEY_STATO, Nota.getStato().toString());
        values.put(KEY_SPECIALE, Nota.isSpeciale());

        // updating row
        return db.update(TABLE_NotaS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(Nota.getId())});
    }

    // Deleting single Nota
    public int deletNota(Nota Nota) {
        SQLiteDatabase db = this.getWritableDatabase();
        int risultato = db.delete(TABLE_NotaS, KEY_ID + " = " + String.valueOf(Nota.getId()), null);
        db.close();
        return risultato;
    }


}