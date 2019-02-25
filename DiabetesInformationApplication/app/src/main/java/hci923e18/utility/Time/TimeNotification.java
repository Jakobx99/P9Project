package hci923e18.utility.Time;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import hci923e18.utility.NotificationUtil;

public class TimeNotification {
    //Initialize the interval for the alarm
    long intervalMillis = 0;
    //If the alarm is for every day of the week
    //Alarm once a day
    public void setAlarm(Context ctx){

        //Initialize alarmManager with the context
        AlarmManager am = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);


        intervalMillis = 24 * 3600 * 1000;
        long intervalmin = 1000 *60*1;

        //TODO Setup alarm for morning
        //Get instance of the calendar
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);

        Intent morningIntent = new Intent(ctx, TimeReciever.class);
        morningIntent.putExtra("Identifier", 0);
        PendingIntent sender = PendingIntent.getBroadcast(ctx, 0, morningIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intervalmin, sender);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        Log.i("Alarm", "Alarm added at: " + sdf.format(new Date()) + "With time:´" + sdf.format(calendar.getTimeInMillis()));



    //TODO Setup alarm for midday
    //TODO Setup alarm for evening

    }
}


