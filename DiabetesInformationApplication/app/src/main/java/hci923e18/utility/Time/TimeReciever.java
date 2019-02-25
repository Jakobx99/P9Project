package hci923e18.utility.Time;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import hci923e18.utility.NotificationUtil;

public class TimeReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //TODO Check recieved alarm and call the appropriate notification

        Integer identifier = 0;
        Bundle result = intent.getExtras();
        if (result != null) {
            identifier = result.getInt("Identifier");
        }
        //Morning
        if (identifier == 0){
            NotificationUtil n = new NotificationUtil(context, 0);
            n.CreateNotification("God morgen", "Du kan frit benytte appen som du vil");
        }


        //Afternoon
        //Evening



    }
}

//    NotificationUtil n = new NotificationUtil(getActivity(), 0-2);
//                n.CreateNotification("DIAbetes information application", "Benyt appen frit som du vil");
