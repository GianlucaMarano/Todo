package com.marano.gianluca.todo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.marano.gianluca.todo.R;
import com.marano.gianluca.todo.dialog.DatePickerDialog;

import java.util.Calendar;

/**
 * Created by Gianluca Marano on 20/02/2017.
 */
public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText titleEt, textEt;
    TextView dataScadenzaTv;
    ImageView calendarImg;
    String dataScadenza;
    CheckBox specialeCb;
    DatePickerDialog d;

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_title_add);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);
        getSupportActionBar().setHomeButtonEnabled(true);
        titleEt = (EditText) findViewById(R.id.add_titolo_et);
        textEt = (EditText) findViewById(R.id.add_text_et);
        dataScadenzaTv = (TextView) findViewById(R.id.add_data_scadenza_tv);
        calendarImg = (ImageView) findViewById(R.id.add_calendar_img);
        specialeCb = (CheckBox) findViewById(R.id.add_seciale_cb);
        dataScadenzaTv.setOnClickListener(this);
        calendarImg.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_confirm:
                String titolo = titleEt.getText().toString();
                String testo = textEt.getText().toString();
                Intent i = new Intent();
                i.putExtra(MainActivity.NOTE_KEY_TITOLO, titolo);
                i.putExtra(MainActivity.NOTE_KEY_TESTO, testo);
                i.putExtra(MainActivity.NOTE_KEY_SPECIALE, specialeCb.isChecked());
                i.putExtra(MainActivity.NOTE_KEY_DATA_SCADENZA, d.dataScadenza);
                setResult(RESULT_OK, i);
                finish();
                return true;
            case android.R.id.home:
                onBackPressed();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        d = new DatePickerDialog(this, dataScadenzaTv);
        d.setContentView(R.layout.dialog_datapicker);
        d.show();

    }
}
