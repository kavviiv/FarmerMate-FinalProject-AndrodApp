package com.example.farmermate;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import com.desai.vatsal.mydynamiccalendar.EventModel;
import com.desai.vatsal.mydynamiccalendar.GetEventListListener;
import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;
import com.desai.vatsal.mydynamiccalendar.OnDateClickListener;
import com.desai.vatsal.mydynamiccalendar.OnEventClickListener;
import com.desai.vatsal.mydynamiccalendar.OnWeekDayViewClickListener;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarAc1 extends AppCompatActivity {
    public static int Day1;
    public static String Step1,Detail,Reco,Warni;
    public String Step;
    private Toolbar toolbar;
    MyDynamicCalendar myCalendar2;
    TextView tv1;
    ArrayList<String> Work1;
    SQLiteDatabase mDB;
    WorkAdapter wa;
    ToDoAdapter TDa;
    DBHelper2 DBHelper2;
 List<Work>ww;
    List<Todo> ToDoList;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calen1);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        myCalendar2 = (MyDynamicCalendar) findViewById(R.id.myCalendar);
        setSupportActionBar(toolbar);

        Button wl = (Button) findViewById(R.id.wl);
        wl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CalendarAc1.this, WorkList2.class);
                startActivity(intent);
            }
        });
        myCalendar2.showMonthViewWithBelowEvents();
        myCalendar2.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Log.e("date", String.valueOf(date));
                long eventID = 208;
                myCalendar2.getEventList(new GetEventListListener() {
                    @Override
                    public void eventList(ArrayList<EventModel> eventList) {
                        Log.e("tag", "eventList.size():-" + eventList.size());
                        for (int i = 0; i < eventList.size(); i++) {
                            Log.e("tag", "eventList.getStrName:-" + eventList.get(i).getStrName());
                        }
                    }
                });
            }
            @Override
            public void onLongClick(Date date) {
                Log.e("date", String.valueOf(date));
            }
        });
        myCalendar2.setCalendarBackgroundColor("#87FFE7");
        myCalendar2.setHeaderBackgroundColor("#87FFE7");
        myCalendar2.setHeaderTextColor("#ffffff");
        myCalendar2.setNextPreviousIndicatorColor("#FF03A9F4");
        myCalendar2.setWeekDayLayoutBackgroundColor("#87FFE7");
        myCalendar2.setWeekDayLayoutTextColor("#0045149E");
        myCalendar2.setExtraDatesOfMonthBackgroundColor("#87FFE7");
        myCalendar2.setExtraDatesOfMonthTextColor("#756325");
        myCalendar2.setDatesOfMonthBackgroundColor("#87FFE7");
        myCalendar2.setDatesOfMonthTextColor("#745632");
        myCalendar2.setCurrentDateTextColor("#00e600");
        myCalendar2.setEventCellBackgroundColor("#FF00BCD4");
        myCalendar2.setEventCellTextColor("#425684");

        String std = getIntent().getStringExtra("Date");
        String stm = getIntent().getStringExtra("Month");
        String sty = getIntent().getStringExtra("Year");
        int i = Integer.parseInt(std);
        myCalendar2.addEvent( std+"-"+stm+"-"+sty ,"20:54","23:00", "เตรียมหน้าดินครั้งที่ 1 โดยการไถดะ");
        myCalendar2.addEvent( (i+14)+"-"+stm+"-"+sty,"08:00","18:00", "เตรียมหน้าดินครั้งที่ 2 โดยการไถแปร");
        myCalendar2.addEvent((i+14)+"-"+stm+"-"+sty,"08:00","18:00", "เตรียมเมล็ดพันธุ์โดยการนำไปแช่น้ำ เพื่อให้เกิดการงอกของราก");
        myCalendar2.addEvent((i+28)+"-"+stm+"-"+sty,"08:00","18:00", "เตรียมหน้าดินครั้งที่ 3 โดยการไถคราด");
        myCalendar2.addEvent((i+30)+"-"+stm+"-"+sty,"08:00","18:00", "นำน้ำเข้านา");
        myCalendar2.addEvent((i+51)+"-"+stm+"-"+sty,"08:00","18:00", "ใสปุ่๋ยครั้งที่ 1 \n ใส่ปุ๋ยสูตร 16-16-8 หรือปุ๋ยที่มีว่สนผสมของแอมโมเนียมฟอสเฟตสูตรต่างๆ");
        myCalendar2.addEvent((i+30)+"-"+stm+"-"+sty,"08:00","18:00", "นำน้าออกจากนา");
        myCalendar2.addEvent((i+30)+"-"+stm+"-"+sty,"08:00","18:00", "ตรวจสอบโรคข้าวและศัตรูพืช \n ถ้าพบให้เลือกใช้ยาที่เหมากับโรคและศัตรูพืชที่เจอ");
        myCalendar2.addEvent((i+30)+"-"+stm+"-"+sty,"08:00","18:00", "นำน้ำเข้านา");
        myCalendar2.addEvent((i+30)+"-"+stm+"-"+sty,"08:00","18:00", "ใสปุ่๋ยครั้งที่ 2");
        myCalendar2.addEvent((i+30)+"-"+stm+"-"+sty,"08:00","18:00", "ตรวจสอบโรคข้าวและศัตรูพืช \n ถ้าพบให้เลือกใช้ยาที่เหมากับโรคและศัตรูพืชที่เจอ");
        myCalendar2.addEvent((i+30)+"-"+stm+"-"+sty,"08:00","18:00", "นำน้าออกจากนา");
        myCalendar2.addEvent((i+30)+"-"+stm+"-"+sty,"08:00","18:00", "เก็บเกี่ยว");
        myCalendar2.setBelowMonthEventTextColor("#425684");
//        myCalendar.setBelowMonthEventTextColor(R.color.black);
        myCalendar2.setBelowMonthEventDividerColor("#0045149E");
//        myCalendar.setBelowMonthEventDividerColor(R.color.black);
        myCalendar2.setHolidayCellBackgroundColor("#0045149E");
//        myCalendar.setHolidayCellBackgroundColor(R.color.black);
        myCalendar2.setHolidayCellTextColor("#d590bb");
//        myCalendar.setHolidayCellTextColor(R.color.black);
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
        getMenuInflater().inflate(R.menu.main_menu2, menu);
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
                myCalendar2.goToCurrentDate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showMonthView() {

        myCalendar2.showMonthView();

        myCalendar2.setOnDateClickListener(new OnDateClickListener() {
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

        myCalendar2.showMonthViewWithBelowEvents();

        myCalendar2.setOnDateClickListener(new OnDateClickListener() {
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

        myCalendar2.showWeekView();

        myCalendar2.setOnEventClickListener(new OnEventClickListener() {
            @Override
            public void onClick() {
                Log.e("showWeekView","from setOnEventClickListener onClick");
            }

            @Override
            public void onLongClick() {
                Log.e("showWeekView","from setOnEventClickListener onLongClick");

            }
        });

        myCalendar2.setOnWeekDayViewClickListener(new OnWeekDayViewClickListener() {
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

        myCalendar2.showDayView();

        myCalendar2.setOnEventClickListener(new OnEventClickListener() {
            @Override
            public void onClick() {
                Log.e("showDayView", "from setOnEventClickListener onClick");

            }

            @Override
            public void onLongClick() {
                Log.e("showDayView", "from setOnEventClickListener onLongClick");

            }
        });

        myCalendar2.setOnWeekDayViewClickListener(new OnWeekDayViewClickListener() {
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

        myCalendar2.showAgendaView();

        myCalendar2.setOnDateClickListener(new OnDateClickListener() {
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.onDestroy();
    }


    public class WorkAdapter extends BaseAdapter {
        private Context aContext;
        private List<Todo> Work;

        public WorkAdapter(Context aContext, List<Todo> Work) {
            this.aContext = aContext;
            this.Work = Work;
        }

        public int getDay1(){
            return Day1;
        }
        public String getStep(){
            return Step;
        }
        public String getDetail(){
            return Detail;
        }
        public String getReco(){
            return Reco;
        }
        public String getWarni(){
            return Warni;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }



}
