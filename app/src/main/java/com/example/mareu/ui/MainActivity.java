package com.example.mareu.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.DI.DI;
import com.example.mareu.R;
import com.example.mareu.events.DeleteReunionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.reunion_recycler)
        RecyclerView recycler;

    private String todayDate;
    private String date;
    private ArrayList<ArrayList<String>> list;
    private String state = "date";
    private String mRoom;
    private String mHour;

    private Calendar mCalendar;
    private DatePickerDialog mCalendarDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.recycler.setLayoutManager(new LinearLayoutManager(this));
        this.recycler.addItemDecoration(new DividerItemDecoration( getApplicationContext(), DividerItemDecoration.VERTICAL));
        this.todayDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        this.date = todayDate;
        if (savedInstanceState != null){
            this.state = savedInstanceState.getString("STATE");
            this.mRoom = savedInstanceState.getString("ROOM");
            this.mHour = savedInstanceState.getString("HOUR");
        }
        checkState();
        initList();
    }

    @OnClick(R.id.add_reunion)
    void addReunion(){
        Intent intent = new Intent(getApplicationContext(), AddReunionActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_no_filter:
                date = todayDate;
                this.list = DI.service.getByDate(date);
                state = "date";
                initList();
                return true;
            case R.id.menu_date_filter:
                calendarSetup();
                state = "date";
                initList();
                return true;
            case R.id.menu_room_filter:
                dialogRoomConfig();
                state = "room";
                initList();
                return true;
            case R.id.menu_hour_filter:
                dialogHourConfig();
                state = "hour";
                initList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initList(){
        this.recycler.setAdapter(new ReunionRecyclerViewAdapter(list));
        setTitle("Mareu        "+date);
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteReunion(DeleteReunionEvent event) {
        DI.service.removeReunion(event.date,event.room,event.hour,event.name);
        checkState();
        initList();
    }

    private void calendarSetup(){
        mCalendar = Calendar.getInstance();
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        int month = mCalendar.get(Calendar.MONTH);
        int year = mCalendar.get(Calendar.YEAR);
        mCalendarDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String mYear = String.valueOf(year);
                String mMonth = String.valueOf(month+1);
                String mDayOfMonth = String.valueOf(dayOfMonth);
                if (dayOfMonth<10) mDayOfMonth = "0"+mDayOfMonth;
                if (month<9) mMonth = "0"+mMonth;
                String str = mDayOfMonth+"-"+mMonth+"-"+mYear;
                date = str;
                list = DI.service.getByDate(date);
                initList();
            }
        },year, month, day);
        mCalendarDialog.show();
    }

    private void dialogRoomConfig(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Sélectionner la salle").setItems(R.array.rooms_array, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<String> listRoom = Arrays.asList(getResources().getStringArray(R.array.rooms_array));
                Toast.makeText(getApplicationContext(),listRoom.get(which),Toast.LENGTH_LONG).show();
                mRoom = listRoom.get(which);
                list = DI.service.getByRoom(mRoom,date);
                initList();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void dialogHourConfig(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Selectionner l' heure").setItems(R.array.complet_hour_array, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<String> listRoom = Arrays.asList(getResources().getStringArray(R.array.complet_hour_array));
                Toast.makeText(getApplicationContext(),listRoom.get(which),Toast.LENGTH_LONG).show();
                mHour = listRoom.get(which);
                list = DI.service.getByHour(mHour,date);
                initList();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void checkState(){
        switch (state){
            case "date":
                this.list = DI.service.getByDate(date);
                break;
            case "room":
                this.list = DI.service.getByRoom(mRoom,date);
                break;
            case "hour":
                this.list = DI.service.getByHour(mHour,date);
                break;
            default:
                this.list = DI.service.getByDate(date);
                break;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("STATE", state);
        outState.putString("ROOM",mRoom);
        outState.putString("HOUR",mHour);
        super.onSaveInstanceState(outState);
    }
}