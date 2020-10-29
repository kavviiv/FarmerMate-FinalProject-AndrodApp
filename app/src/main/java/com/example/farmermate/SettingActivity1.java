package com.example.farmermate;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;
import java.util.TimeZone;

public class SettingActivity1 extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String CHANNEL_ID = "rain_notif_channel";
    private static String PREF_KEY_TIME = "pref_key_time";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        long notifTime = sharedPref.getLong(PREF_KEY_TIME, 0);
        scheduleNotif(notifTime);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(PREF_KEY_TIME)) {
            long notifTime = sharedPreferences.getLong(key, 0);
            scheduleNotif(notifTime);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    public void scheduleNotif(long time) {



        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        //Build notification
        Intent tapNotifIntent = new Intent(this, WeatherPage.class);
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(tapNotifIntent);
        // Get the PendingIntent containing the entire back stack
        PendingIntent tapNotifPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("FarmerMate")
                .setContentText("มีโอกาศเกิดฝน" + "")
                .setSmallIcon(R.drawable.ic_weather_windy_white_18dp)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(tapNotifPendingIntent)
                .setAutoCancel(true);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

        Notification alarmNotif = mBuilder.build();
        Log.i("mBuilder.build()", "Notification has been built.");

        AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(this, AlarmReceiver1.class);
        alarmIntent.putExtra(AlarmReceiver1.RAIN_NOTIF_ID, 1)
                .putExtra(AlarmReceiver1.NOTIFICATION, alarmNotif)
                .putExtra(AlarmReceiver1.RECEIVER_COORDINATES, getIntent()
                        .getDoubleArrayExtra(AlarmReceiver1.RECEIVER_COORDINATES));
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, 0,
                alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, time,
                AlarmManager.INTERVAL_DAY, alarmPendingIntent);
        Log.i("from onCreate", "alarm set");
    }
}
