package hci923e18.utility.Time;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import hci923e18.utility.NotificationUtil;

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
        }
        //Afternoon
        if (identifier == 1){
            NotificationUtil n = new NotificationUtil(context, 1, advanced);
            n.CreateNotification("God middag", "Husk at lave de tilsendte opgaver :-)");
        }
        //Evening
        if (identifier == 2){
            NotificationUtil n = new NotificationUtil(context, 2, advanced);
            n.CreateNotification("God aften", "Husk at indrapportere dine oplevelser");
        }
    }
}