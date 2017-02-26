package com.marano.gianluca.todo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.marano.gianluca.todo.ElencoFragment;
import com.marano.gianluca.todo.R;
import com.marano.gianluca.todo.controllers.Databasehandler;
import com.marano.gianluca.todo.model.Nota;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gianluca Marano on 20/02/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_ADD = 10;
    public static final String NOTE_KEY_TITOLO = "titolo";
    public static final String NOTE_KEY_TESTO = "testo";
    public static final String NOTE_KEY_SPECIALE = "speciale";
    public static final String NOTE_KEY_DATA_SCADENZA = "data_scadenza";
    public static final int ELENCO_SEMPLICE = 10;
    public static final String REQUEST = "elenco";
    public static final int ELENCO_SPECIALE = 20;
    FloatingActionButton floatingActionButton;
    Databasehandler db;
    ElencoFragment speciali, elenco;
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.titolo_main);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.main_float_fab);
        floatingActionButton.setOnClickListener(this);
        db = new Databasehandler(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), MainActivity.this);
        elenco = new ElencoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(REQUEST, ELENCO_SEMPLICE);
        elenco.setArguments(bundle);
        pagerAdapter.addFragment(elenco, "Elenco");
        speciali = new ElencoFragment();
        Bundle sBundle = new Bundle();
        sBundle.putInt(REQUEST, ELENCO_SPECIALE);
        speciali.setArguments(sBundle);
        pagerAdapter.addFragment(speciali, "Importanti");
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onResume() {
        super.onResume();
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
            if (n.isSpeciale()) {
                speciali.getNoteAdapter().addNota(n);
            }
            elenco.getNoteAdapter().addNota(n);
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.elimina:
                Log.d("frag", String.valueOf(viewPager.getCurrentItem()));
                if (viewPager.getCurrentItem() == 1) {
                    Log.d("elencovisible", "entra");
                    db.deletNota(speciali.getNoteAdapter().getNota(speciali.getNoteAdapter().getPosizione()));
                    Nota n = speciali.getNoteAdapter().getNota(speciali.getNoteAdapter().getPosizione());
                    if (n.isSpeciale()) {
                        speciali.getNoteAdapter().removeNota(speciali.getNoteAdapter().getPosizione());
                    }
                    elenco.getNoteAdapter().removeNota(elenco.getNoteAdapter().getPosizione(n));
                } else {
                    db.deletNota(elenco.getNoteAdapter().getNota(elenco.getNoteAdapter().getPosizione()));
                    Nota n = elenco.getNoteAdapter().getNota(elenco.getNoteAdapter().getPosizione());
                    elenco.getNoteAdapter().removeNota(elenco.getNoteAdapter().getPosizione());
                    if (n.isSpeciale()) {
                        speciali.getNoteAdapter().removeNota(speciali.getNoteAdapter().getPosizione(n));
                    }
                }
                break;
        }
        return super.onContextItemSelected(item);
    }

    class PagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        Context context;

        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
        }


        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        public void addFragment(Fragment frag, String title) {
            fragmentList.add(frag);
            titleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }

}
