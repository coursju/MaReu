package com.example.mareu.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mareu.DI.DI;
import com.example.mareu.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddReunionActivity extends AppCompatActivity {
    @BindView(R.id.room_selection)
    Spinner roomSelection;
    @BindView(R.id.hour_selection)
    Spinner hourSelection;
    @BindView(R.id.minute_selection)
    Spinner minuteSelection;
    @BindView(R.id.reunion_time_selection)
    Spinner reunionTimeSelection;
    @BindView(R.id.date_button)
    Button dateButton;
    @BindView(R.id.save_reunion)
    Button saveReunion;
    @BindView(R.id.enter_name)
    EditText enterName;
    @BindView(R.id.enter_participants)
    EditText enterParticipants;

    private String date;
    private String room;
    private String hour;
    private String reunionTime;
    private String nomReunion = "";
    private String participants = "";

    private String h;
    private String min;

    private Calendar calendar;
    private DatePickerDialog calendarDialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reunion);
        ButterKnife.bind(this);
        setTitle("Add Reunion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createSpinners(R.array.rooms_array, roomSelection);
        createSpinners(R.array.hour_array, hourSelection);
        createSpinners(R.array.minute_array, minuteSelection);
        createSpinners(R.array.time_array, reunionTimeSelection);
        date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        dateButton.setText(date);
        spinnersListenersSetup();
        textListenersSetup();
        buttonsListenersSetup();
    }

    private void createSpinners(int ressource, Spinner spinner){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                ressource, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void calendarSetup(){
        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        calendarDialog = new DatePickerDialog(AddReunionActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String mYear = String.valueOf(year);
                String mMonth = String.valueOf(month+1);
                String mDayOfMonth = String.valueOf(dayOfMonth);
                if (dayOfMonth<10) mDayOfMonth = "0"+mDayOfMonth;
                if (month<9) mMonth = "0"+mMonth;
                String str = mDayOfMonth+"-"+mMonth+"-"+mYear;
                date = str;
                dateButton.setText(str);
            }
        },year, month, day);
        calendarDialog.show();
    }

    private void spinnersListenersSetup(){
        roomSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                room = (String)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),room,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        hourSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                h = (String)parent.getItemAtPosition(position);
                hour = h+":"+min;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        minuteSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                min = (String)parent.getItemAtPosition(position);
                hour = h+":"+min;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        reunionTimeSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reunionTime = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void buttonsListenersSetup(){
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarSetup();
            }
        });
        saveReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DI.service.addReunion(date, room, hour, reunionTime, nomReunion, participants);
//                String str = date+" "+room+" "+hour+" "+reunionTime+" "+nomReunion+" "+participants+" ";
//                Toast.makeText(getApplicationContext(),str+": Ajouter",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"Bouton ajouter cliqué!",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void textListenersSetup(){
        enterName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                nomReunion = s.toString();
            }
        });
        enterParticipants.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                    participants = s.toString();
                    Toast.makeText(getApplicationContext(),R.string.only_email, Toast.LENGTH_LONG).show();
            }
        });
    }
}
