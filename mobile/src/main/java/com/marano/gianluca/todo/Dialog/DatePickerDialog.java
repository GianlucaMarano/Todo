package com.marano.gianluca.todo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.marano.gianluca.todo.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.marano.gianluca.todo.activities.AddActivity.getDateFromDatePicker;

/**
 * Created by Gianluca Marano on 24/02/2017.
 */

public class DatePickerDialog extends Dialog implements View.OnClickListener {

    public String dataScadenza;
    TextView dataScadenzaTv;
    Button ok;
    DatePicker date;

    public DatePickerDialog(Context context, TextView dataScadenzaTv) {
        super(context);
        this.dataScadenzaTv = dataScadenzaTv;
    }

    @Override
    public void show() {
        super.show();
        ok = (Button) findViewById(R.id.dialog_ok_btn);
        date = (DatePicker) findViewById(R.id.date_picker);
        ok.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        DateFormat data = new SimpleDateFormat("dd MMMM YYYY");
        Date d = getDateFromDatePicker(date);
        dataScadenza = data.format(d);
        dataScadenzaTv.setText(dataScadenza);
        this.cancel();
    }
}
