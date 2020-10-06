package com.example.farmermate;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.json.JSONArray;
import org.json.JSONObject;
import android.text.format.DateFormat;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static java.util.Calendar.*;

/**
 * Created by raghu on 2/7/17.
 */

public class CalendarFragment extends Fragment implements OnMonthChangedListener {


    private MaterialCalendarView calendarView;
    private List<CalendarDay> calevents = new ArrayList<>();
    private List<Event> eventList = new ArrayList<>();
    private HashMap<Integer, List<Event>> map = new HashMap<>();
    private ListView listView;
    private MyAdapter adapter;
    private Calendar cal;
    private LocalDate LocalDate;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calendar, container, false);

        listView = (ListView)view.findViewById(R.id.listview);

        adapter = new MyAdapter(getActivity(),eventList);
        listView.setAdapter(adapter);

        calendarView =  view.findViewById(R.id.calendarView);
        calendarView.setDateTextAppearance(View.ACCESSIBILITY_LIVE_REGION_ASSERTIVE);
        final Calendar calendar = getInstance();
        calendarView.setSelectedDate(calendarView.getSelectedDate());
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                calendarView.setHeaderTextAppearance(R.style.AppTheme);
            }
        });



        calendarView.setOnMonthChangedListener(this);

        makeJsonObjectRequest();

        return view;
    }




    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void makeJsonObjectRequest() {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());

        String response = loadJSONFromAsset();
        try {
            JSONArray jArray = new JSONArray(response);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jsonObject = jArray.getJSONObject(i);
                String StartDate = jsonObject.getString("StartDate");
                Date date = simpleDateFormat.parse(StartDate);


                String title =  jsonObject.getString("Title");

                Log.d("Date ",""+ date);
                CalendarDay day = CalendarDay.from(org.threeten.bp.LocalDate.ofEpochDay(DAY_OF_MONTH));
                Event event = new Event(date,title);
                cal = getInstance();
                cal.setTime(date);
                int month = cal.get(MONTH);

                if(!map.containsKey(month))
                {
                    List<Event> events = new ArrayList<>();
                    events.add(event);
                    map.put(month,events);
                }else
                {
                    List<Event> events = map.get(month);
                    events.add(event);
                    map.put(month,events);

                }

                calevents.add(day);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // after parsing
        cal = getInstance();
        int month = cal.get(MONTH);
        List<Event> event =  map.get(month);
        if(event!=null && event.size()>0)
        adapter.addItems(event);
        EventDecorator eventDecorator = new EventDecorator(Color.RED, calevents);
        calendarView.addDecorator(eventDecorator);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = Objects.requireNonNull(getActivity()).getAssets().open("testjson.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        Calendar cal = getInstance();
        cal.setTime(cal.getTime());
        int month = cal.get(MONTH);
        List<Event> event =  map.get(month);
        if(event!=null && event.size()>0)
        adapter.addItems(event);

    }
}
