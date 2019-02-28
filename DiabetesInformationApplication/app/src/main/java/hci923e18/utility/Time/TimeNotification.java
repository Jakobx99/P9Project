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

import hci923e18.database.Identifier;
import hci923e18.utility.NotificationUtil;

public class TimeNotification {
    //Initialize the interval for the alarm
    long intervalMillis = 0;

    /**
     * Initiates the alarmmanager with 3 alarms each day
     * @param ctx The context of the calling activity
     */
    public void setAlarm(Context ctx){

        Identifier i = Identifier.listAll(Identifier.class).get(0);

        //Initialize alarmManager with the context
        AlarmManager am = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);


        intervalMillis = 24 * 3600 * 1000;
        long intervalmin = 1000 *60*2;

        //Morning alarm
        //Get instance of the calendar
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 00);

        Intent morningIntent = new Intent(ctx, TimeReciever.class);
        morningIntent.putExtra("Identifier", 0);
        morningIntent.putExtra("advanced", i.get_advanced());
        PendingIntent morningSender = PendingIntent.getBroadcast(ctx, 0, morningIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intervalMillis, morningSender);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        Log.i("Alarm", "Alarm added at: " + sdf.format(new Date()) + "With time:´" + sdf.format(calendar.getTimeInMillis()));

        //Afternoon alarm
        //Get instance of the calendar
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 00);

        Intent afternoonIntent = new Intent(ctx, TimeReciever.class);
        afternoonIntent.putExtra("Identifier", 1);
        afternoonIntent.putExtra("advanced", i.get_advanced());
        PendingIntent afternoonsender = PendingIntent.getBroadcast(ctx, 1, afternoonIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intervalMillis, afternoonsender);
        Log.i("Alarm", "Alarm added at: " + sdf.format(new Date()) + "With time:´" + sdf.format(calendar.getTimeInMillis()));

        //Evening alarm
        //Get instance of the calendar
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 00);

        Intent eveningIntent = new Intent(ctx, TimeReciever.class);
        morningIntent.putExtra("Identifier", 2);
        morningIntent.putExtra("advanced", i.get_advanced());
        PendingIntent sender = PendingIntent.getBroadcast(ctx, 2, eveningIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intervalMillis, sender);
        Log.i("Alarm", "Alarm added at: " + sdf.format(new Date()) + "With time:´" + sdf.format(calendar.getTimeInMillis()));

    }
}


