package hci1025f19.utility.Time;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

import hci1025f19.utility.NotificationUtil;

public class TimeReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Reciever", "RECIEVER CALLED");

        Integer identifier = 0;
        Boolean advanced = false;
        Bundle result = intent.getExtras();
        if (result != null) {
            identifier = result.getInt("Identifier");
            advanced = result.getBoolean("advanced");
        }
        //Morning
        if (identifier == 0){
            NotificationUtil n = new NotificationUtil(context, 0, advanced);
            n.CreateNotification("God morgen", "Du kan frit benytte appen som du vil");

            //Initialize alarmManager with the context
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            //Morning alarm
            Calendar calendar1 = Calendar.getInstance();
            calendar1.add(Calendar.DATE, 1);
            calendar1.set(Calendar.HOUR_OF_DAY, 8);
            calendar1.set(Calendar.MINUTE, 00);
            calendar1.set(Calendar.SECOND, 00);
            Intent morningIntent = new Intent(context, TimeReciever.class);
            morningIntent.putExtra("Identifier", 0);
            morningIntent.putExtra("advanced", advanced);
            PendingIntent morningSender = PendingIntent.getBroadcast(context, 0, morningIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            am.setExact(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), morningSender);
        }
        //Afternoon
        if (identifier == 1){
            NotificationUtil n = new NotificationUtil(context, 1, advanced);
            n.CreateNotification("God middag", "Husk at lave de tilsendte opgaver :-)");

            //Afternoon alarm
            //Get instance of the calendar
            //Initialize alarmManager with the context
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            Calendar calendar2 = Calendar.getInstance();
            //Get instance of the calendar
            calendar2.add(Calendar.DATE, 1);
            calendar2.set(Calendar.HOUR_OF_DAY, 13);
            calendar2.set(Calendar.MINUTE, 00);
            calendar2.set(Calendar.SECOND, 00);
            Intent afternoonIntent = new Intent(context, TimeReciever.class);
            afternoonIntent.putExtra("Identifier", 1);
            afternoonIntent.putExtra("advanced", advanced);
            PendingIntent afternoonsender = PendingIntent.getBroadcast(context, 1, afternoonIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            am.setExact(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), afternoonsender);
        }
        //Evening
        if (identifier == 2){
            NotificationUtil n = new NotificationUtil(context, 2, advanced);
            n.CreateNotification("God aften", "Husk at indrapportere dine oplevelser");

            //Initialize alarmManager with the context
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            Calendar calendar3 = Calendar.getInstance();
            //Get instance of the calendar
            calendar3.add(Calendar.DATE, 1);
            calendar3.set(Calendar.HOUR_OF_DAY, 20);
            calendar3.set(Calendar.MINUTE, 00);
            calendar3.set(Calendar.SECOND, 00);
            Intent eveningIntent = new Intent(context, TimeReciever.class);
            eveningIntent.putExtra("Identifier", 2);
            eveningIntent.putExtra("advanced", advanced);
            PendingIntent sender = PendingIntent.getBroadcast(context, 2, eveningIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            am.setExact(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(), sender);
        }
    }
}