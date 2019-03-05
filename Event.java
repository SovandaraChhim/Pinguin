package com.example.sovandara.create_event;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import static com.example.sovandara.create_event.R.layout.creation;

/**
 * Created by Sovandara on 18/02/2019.
 */

public class Event extends AppCompatActivity {

    private TextView mDate;
    private DatePickerDialog.OnDateSetListener mDateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(creation);

        mDate = (TextView) findViewById(R.id.txtDate);
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Event.this, mDateListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicket, int year, int month, int day) {
                month = month + 1;
                Log.d("Event","onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDate.setText(date);
            }
        };
    }

    public void send (View view) {
        EditText edText = (EditText) findViewById(R.id.editTitle);
        TextView tvDate = (TextView) findViewById(R.id.txtDate);
        EditText edCity = (EditText) findViewById(R.id.editCity);
        EditText edAddress = (EditText) findViewById(R.id.editAddress);
        EditText edBegin = (EditText) findViewById(R.id.editBegin);
        EditText edEnd = (EditText) findViewById(R.id.editEnd);

        String title = edText.getText().toString();
        String date = tvDate.getText().toString();
        String city = edCity.getText().toString();
        String address = edAddress.getText().toString();
        String begin = edBegin.getText().toString();
        String end = edEnd.getText().toString();

        Intent intent = new Intent(Event.this, Display.class);
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        bundle.putString("date",date);
        bundle.putString("city",city);
        bundle.putString("address",address);
        bundle.putString("begin",begin);
        bundle.putString("end",end);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}