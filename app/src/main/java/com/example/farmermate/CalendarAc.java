package com.example.farmermate;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.CalendarContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.desai.vatsal.mydynamiccalendar.EventModel;
import com.desai.vatsal.mydynamiccalendar.GetEventListListener;
import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;
import com.desai.vatsal.mydynamiccalendar.OnDateClickListener;
import com.desai.vatsal.mydynamiccalendar.OnEventClickListener;
import com.desai.vatsal.mydynamiccalendar.OnWeekDayViewClickListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarAc extends AppCompatActivity {
    public static int Day1;
    public static String Step1,Detail,Reco,Warni;
    private Toolbar toolbar;
    MyDynamicCalendar myCalendar;
    ArrayList<Work> Work;
    List<Todo>Todo;
    ArrayList<String> getAllWork;
    TextView tv1;
    ArrayList<String> Work1;
    SQLiteDatabase mDB;

    DBHelper2 DBHelper2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calen);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        myCalendar = (MyDynamicCalendar) findViewById(R.id.myCalendar);
        setSupportActionBar(toolbar);
        myCalendar.showMonthViewWithBelowEvents();
        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
                long eventID = 208;
                myCalendar.getEventList(new GetEventListListener() {
                    @Override
                    public void eventList(ArrayList<EventModel> eventList) {

                        Log.e("tag", "eventList.size():-" + eventList.size());
                        for (int i = 0; i < eventList.size(); i++) {
                            Log.e("tag", "eventList.getStrName:-" + eventList.get(i).getStrName());
                            //tv1.setText(eventList.get(i).getStrName());
                        }

                    }
                });

            }
            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });
        myCalendar.setCalendarBackgroundColor("#87FFE7");
        myCalendar.setHeaderBackgroundColor("#87FFE7");
        myCalendar.setHeaderTextColor("#ffffff");
        myCalendar.setNextPreviousIndicatorColor("#FF03A9F4");
        myCalendar.setWeekDayLayoutBackgroundColor("#87FFE7");
        myCalendar.setWeekDayLayoutTextColor("#0045149E");
        myCalendar.setExtraDatesOfMonthBackgroundColor("#87FFE7");
        myCalendar.setExtraDatesOfMonthTextColor("#756325");
        myCalendar.setDatesOfMonthBackgroundColor("#87FFE7");
        myCalendar.setDatesOfMonthTextColor("#745632");
        myCalendar.setCurrentDateTextColor("#00e600");
        myCalendar.setEventCellBackgroundColor("#87FFE7");
        myCalendar.setEventCellTextColor("#425684");

        int Evtname = ToDoAdapter.getDay();







        myCalendar.addHoliday("");



        int b = 10;
        String a = b+"-";
        String start = "10-10-2020";
        String end = "20-10-2020";


        myCalendar.addEvent(ToDoAdapter.getDay()+"-10-2020", "8:00", "18:00", String.valueOf(getAllWork));

        myCalendar.getEventList(new GetEventListListener() {
            @Override
            public void eventList(ArrayList<EventModel> eventList) {

                Log.e("tag", "eventList.size():-" + eventList.size());
                for (int i = 0; i < eventList.size(); i++) {
                    Log.e("tag", "eventList.getStrName:-" + eventList.get(i).getStrName());
                }

            }
        });

//        myCalendar.updateEvent(0, "5-10-2016", "8:00", "8:15", "Today Event 111111");

//        myCalendar.deleteEvent(2);

//        myCalendar.deleteAllEvent();

        myCalendar.setBelowMonthEventTextColor("#425684");
//        myCalendar.setBelowMonthEventTextColor(R.color.black);

        myCalendar.setBelowMonthEventDividerColor("#0045149E");
//        myCalendar.setBelowMonthEventDividerColor(R.color.black);

        myCalendar.setHolidayCellBackgroundColor("#0045149E");
//        myCalendar.setHolidayCellBackgroundColor(R.color.black);

        myCalendar.setHolidayCellTextColor("#d590bb");
//        myCalendar.setHolidayCellTextColor(R.color.black);



//        myCalendar.setCalendarDate(5, 10, 2016);

    }

    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DBHelper.DBNAME);
            String outFileName = DBHelper.DBLOCATION + DBHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity", "DB copied");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_month:
                showMonthView();
                return true;
            case R.id.action_month_with_below_events:
                showMonthViewWithBelowEvents();
                return true;
            case R.id.action_week:
                showWeekView();
                return true;
            case R.id.action_day:
                showDayView();
                return true;
            case R.id.action_agenda:
                showAgendaView();
                return true;
            case R.id.action_today:
                myCalendar.goToCurrentDate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showMonthView() {

        myCalendar.showMonthView();

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

    }

    private void showMonthViewWithBelowEvents() {

        myCalendar.showMonthViewWithBelowEvents();

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

    }

    private void showWeekView() {

        myCalendar.showWeekView();

        myCalendar.setOnEventClickListener(new OnEventClickListener() {
            @Override
            public void onClick() {
                Log.e("showWeekView","from setOnEventClickListener onClick");
            }

            @Override
            public void onLongClick() {
                Log.e("showWeekView","from setOnEventClickListener onLongClick");

            }
        });

        myCalendar.setOnWeekDayViewClickListener(new OnWeekDayViewClickListener() {
            @Override
            public void onClick(String date, String time) {
                Log.e("showWeekView", "from setOnWeekDayViewClickListener onClick");
                Log.e("tag", "date:-" + date + " time:-" + time);

            }

            @Override
            public void onLongClick(String date, String time) {
                Log.e("showWeekView", "from setOnWeekDayViewClickListener onLongClick");
                Log.e("tag", "date:-" + date + " time:-" + time);

            }
        });


    }

    private void showDayView() {

        myCalendar.showDayView();

        myCalendar.setOnEventClickListener(new OnEventClickListener() {
            @Override
            public void onClick() {
                Log.e("showDayView", "from setOnEventClickListener onClick");

            }

            @Override
            public void onLongClick() {
                Log.e("showDayView", "from setOnEventClickListener onLongClick");

            }
        });

        myCalendar.setOnWeekDayViewClickListener(new OnWeekDayViewClickListener() {
            @Override
            public void onClick(String date, String time) {
                Log.e("showDayView", "from setOnWeekDayViewClickListener onClick");
                Log.e("tag", "date:-" + date + " time:-" + time);
            }

            @Override
            public void onLongClick(String date, String time) {
                Log.e("showDayView", "from setOnWeekDayViewClickListener onLongClick");
                Log.e("tag", "date:-" + date + " time:-" + time);
            }
        });

    }

    private void showAgendaView() {

        myCalendar.showAgendaView();

        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
            }

            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });

    }


}
