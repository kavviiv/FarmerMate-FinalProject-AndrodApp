package com.example.farmermate;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.CaseMap;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.desai.vatsal.mydynamiccalendar.EventListAdapter;
import com.desai.vatsal.mydynamiccalendar.EventModel;
import com.desai.vatsal.mydynamiccalendar.GetEventListListener;
import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;
import com.desai.vatsal.mydynamiccalendar.OnDateClickListener;
import com.desai.vatsal.mydynamiccalendar.OnEventClickListener;
import com.desai.vatsal.mydynamiccalendar.OnWeekDayViewClickListener;
import com.google.type.DateTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.LocalDate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.example.farmermate.ToDoAdapter.getStep;
import static com.example.farmermate.Todo.*;
import static java.util.Calendar.*;

public class CalendarAc extends AppCompatActivity {

    public static int Day1;
    public static String Step1,Detail,Reco,Warni;
    public String Step;
    private Toolbar toolbar;
    MyDynamicCalendar myCalendar;
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
        setContentView(R.layout.activity_calen);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        myCalendar = (MyDynamicCalendar) findViewById(R.id.myCalendar);
        Button wl = (Button) findViewById(R.id.wl);
        wl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarAc.this, WorkList.class);
                startActivity(intent);
            }
        });
        setSupportActionBar(toolbar);
        myCalendar.showMonthViewWithBelowEvents();
        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {
                Notification notification =
                        new NotificationCompat.Builder(CalendarAc.this) // this is context
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("DevAhoy News")
                                .setContentText("สวัสดีครับ ยินดีต้อนรับเข้าสู่บทความ Android Notification :)")
                                .setAutoCancel(true)
                                .build();
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1000, notification);
                Log.e("date", String.valueOf(date));
                long eventID = 208;
                myCalendar.getEventList(new GetEventListListener() {
                    @Override
                    public void eventList(ArrayList<EventModel> eventList) {
                        Log.e("tag", "eventList.size():-" + eventList.size());
                        for (int i = 0; i < eventList.size(); i++) {
                            Log.e("tag", "eventList.getStrName:-" + eventList.get(i).getStrName());
                        }
                    }
                });
            }

            private void showNotification() {
                Notification notification =
                        new NotificationCompat.Builder(CalendarAc.this) // this is context
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("DevAhoy News")
                                .setContentText("สวัสดีครับ ยินดีต้อนรับเข้าสู่บทความ Android Notification :)")
                                .setAutoCancel(true)
                                .build();
            }

            @Override
            public void onLongClick(Date date) {
                Notification notification =
                        new NotificationCompat.Builder(CalendarAc.this) // this is context
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("DevAhoy News")
                                .setContentText("สวัสดีครับ ยินดีต้อนรับเข้าสู่บทความ Android Notification :)")
                                .setAutoCancel(true)
                                .build();

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1000, notification);
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
        myCalendar.setEventCellTextColor("#FFF44336");


        String std = getIntent().getStringExtra("Date");
        String stm = getIntent().getStringExtra("Month");
        String sty = getIntent().getStringExtra("Year");




        TextView start = (TextView)findViewById(R.id.std);
        start.setText(sty + "-" + stm + "-" + std);

//        long yourNumber = Integer.parseInt(std)+Integer.parseInt(stm)+Integer.parseInt(sty);
//        DateTime dt = new DateTime(yourNumber);
        LocalDate EndDate = LocalDate.of(Integer.parseInt(sty), Integer.parseInt(stm), Integer.parseInt(std)).plusDays(146);


        int i = Integer.parseInt(std);

        TextView end = (TextView)findViewById(R.id.end);
        end.setText(EndDate.toString());

        //TextView end = (TextView)findViewById(R.id.end);
        //end.setText();
        myCalendar.addEvent( std+"-"+stm+"-"+sty ,"04:52","04:53", "เตรียมหน้าดินครั้งที่ 1 โดยการไถดะ");
        myCalendar.addEvent( (i+14)+"-"+stm+"-"+sty,"08:00","18:00", "เตรียมหน้าดินครั้งที่ 2 โดยการไถแปร");
        myCalendar.addEvent((i+14)+"-"+stm+"-"+sty,"08:00","18:00", "เตรียมเมล็ดพันธุ์โดยการนำไปแช่น้ำ เพื่อให้เกิดการงอกของราก");
        myCalendar.addEvent((i+28)+"-"+stm+"-"+sty,"08:00","18:00", "เตรียมหน้าดินครั้งที่ 3 โดยการไถคราด");
        myCalendar.addEvent((i+30)+"-"+stm+"-"+sty,"08:00","18:00", "นำน้ำเข้านา");
        myCalendar.addEvent((i+31)+"-"+stm+"-"+sty,"08:00","18:00", "หว่านข้าว");
        myCalendar.addEvent((i+51)+"-"+stm+"-"+sty,"08:00","18:00", "ใสปุ่๋ยครั้งที่ 1 \nใส่ปุ๋ยสูตร 16-16-8 หรือปุ๋ยที่มีส่วนนผสมของแอมโมเนียมฟอสเฟตสูตรต่างๆ ");
        myCalendar.addEvent((i+61)+"-"+stm+"-"+sty,"08:00","18:00", "ตรวจสอบโรคข้าวและศัตรูพืช \nถ้าพบให้เลือกใช้ยาที่เหมากับโรคและศัตรูพืชที่เจอ" +
                "\nโรคที่มักจะพบในช่วงนี้:โรคใบสีส้ม โรคขอบใบแห้ง \nศัตรูพืช:แมลง เพลี้ยกระโดดสีน้ำตาล เพลี้ยจักจั่นสีเขียว และหนอนกอ"
        );
        myCalendar.addEvent((i+71)+"-"+stm+"-"+sty,"08:00","18:00", "ใสปุ่๋ยครั้งที่ 2 \nใส่ปุ๋ยยูเรีย 46-0-0 หรือปุ๋ยแอมโมเนียมซัลเฟต 21-0-0");
        myCalendar.addEvent((i+86)+"-"+stm+"-"+sty,"08:00","18:00", "นำน้าออกจากนา");
        myCalendar.addEvent((i+91)+"-"+stm+"-"+sty,"08:00","18:00", "ใสปุ่๋ยครั้งที่ 3 \nใส่ปุ๋ยยูเรีย 46-0-0 หรือปุ๋ยแอมโมเนียมซัลเฟต 21-0-0");
        myCalendar.addEvent((i+91)+"-"+stm+"-"+sty,"08:00","18:00", "ตรวจสอบโรคข้าวและศัตรูพืช \nถ้าพบให้เลือกใช้ยาที่เหมากับโรคและศัตรูพืชที่เจอ" +
                "\nโรคที่มักจะพบในช่วงนี้:โรคไหม้ และโรคใบหงิก \nศัตรูพืช:เพลี้ยกระโดดสีน้ำตาล  และหนอนกอ");
        myCalendar.addEvent((i+101)+"-"+stm+"-"+sty,"08:00","18:00", "นำน้ำเข้านา");
        myCalendar.addEvent((i+121)+"-"+stm+"-"+sty,"08:00","18:00", "ตรวจสอบโรคข้าวและศัตรูพืช \nถ้าพบให้เลือกใช้ยาที่เหมากับโรคและศัตรูพืชที่เจอ");
        myCalendar.addEvent((i+141)+"-"+stm+"-"+sty,"08:00","18:00", "นำน้าออกจากนา");
        myCalendar.addEvent((i+146)+"-"+stm+"-"+sty,"08:00","18:00", "เก็บเกี่ยว");

//        Notification notification =
//                new NotificationCompat.Builder(this) // this is context
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setContentTitle("DevAhoy News")
//                        .setContentText("สวัสดีครับ ยินดีต้อนรับเข้าสู่บทความ Android Notification :)")
//                        .setAutoCancel(true)
//                        .build();
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.notify(1000, notification);


        myCalendar.setBelowMonthEventTextColor("#425684");
//        myCalendar.setBelowMonthEventTextColor(R.color.black);

        myCalendar.setBelowMonthEventDividerColor("#0045149E");
//        myCalendar.setBelowMonthEventDividerColor(R.color.black);

        myCalendar.setHolidayCellBackgroundColor("#0045149E");
//        myCalendar.setHolidayCellBackgroundColor(R.color.black);

        myCalendar.setHolidayCellTextColor("#d590bb");
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
                                Notification notification =
                        new NotificationCompat.Builder(CalendarAc.this) // this is context
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("DevAhoy News")
                                .setContentText("สวัสดีครับ ยินดีต้อนรับเข้าสู่บทความ Android Notification :)")
                                .setAutoCancel(true)
                                .build();

                NotificationManager notificationManager =
                        (NotificationManager) CalendarAc.this.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1000, notification);
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
