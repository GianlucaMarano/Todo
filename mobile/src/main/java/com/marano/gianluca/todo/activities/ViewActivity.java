package com.marano.gianluca.todo.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.marano.gianluca.todo.R;
import com.marano.gianluca.todo.adapters.ViewAdapter;
import com.marano.gianluca.todo.controllers.Databasehandler;
import com.marano.gianluca.todo.model.Nota;

/**
 * Created by Gianluca Marano on 24/02/2017.
 */

public class ViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ViewAdapter viewAdapter;
    Nota nota;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_left);
        linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        int id = getIntent().getExtras().getInt("id");

        Databasehandler db = new Databasehandler(this);
        if (getIntent().getExtras().getInt(MainActivity.REQUEST) == MainActivity.ELENCO_SEMPLICE) {
            nota = db.getAllNotas().get(id);
        } else {
            nota = db.getSpecialNotas().get(id);
        }

        getSupportActionBar().setTitle(nota.getTitolo());
        viewAdapter = new ViewAdapter(nota);
        recyclerView = (RecyclerView) findViewById(R.id.view_recycler);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(viewAdapter);
        viewAdapter.notifyDataSetChanged();


    }
}
