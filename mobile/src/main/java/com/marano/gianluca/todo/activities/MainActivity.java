package com.marano.gianluca.todo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.marano.gianluca.todo.R;
import com.marano.gianluca.todo.adapters.NoteAdapter;
import com.marano.gianluca.todo.controllers.Databasehandler;
import com.marano.gianluca.todo.model.Nota;

/**
 * Created by Gianluca Marano on 20/02/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_ADD = 10;
    public static final String NOTE_KEY_TITOLO = "titolo";
    public static final String NOTE_KEY_TESTO = "testo";
    public static final String NOTE_KEY_SPECIALE = "speciale";
    public static final String NOTE_KEY_DATA_SCADENZA = "data_scadenza";
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    NoteAdapter noteAdapter;
    FloatingActionButton floatingActionButton;
    Databasehandler db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.titolo_main);
        db = new Databasehandler(this);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.main_float_fab);
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        noteAdapter = new NoteAdapter(db.getAllNotas());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(noteAdapter);
        registerForContextMenu(recyclerView);
        Log.d("register", "lo faccio ");
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteAdapter.notifyDataSetChanged();
        Log.d("note", db.getAllNotas().toString());
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, AddActivity.class);
        startActivityForResult(i, REQUEST_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_ADD == requestCode && resultCode == RESULT_OK) {
            Nota n = new Nota(data.getExtras().getString(NOTE_KEY_TITOLO),
                    data.getExtras().getString(NOTE_KEY_TESTO),
                    data.getExtras().getString(NOTE_KEY_DATA_SCADENZA),
                    data.getExtras().getBoolean(NOTE_KEY_SPECIALE));
            Log.d("data", data.getExtras().getString(NOTE_KEY_DATA_SCADENZA));
            db.addNota(n);
            noteAdapter.addNota(n);
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.elimina:
                if (db.deletNota(noteAdapter.getNota(noteAdapter.getPosizione())) > 0) {
                    noteAdapter.removeNota(noteAdapter.getPosizione());
                } else {
                    Log.d("main", "non eliminato ");
                }
                break;
        }
        return super.onContextItemSelected(item);
    }
}
