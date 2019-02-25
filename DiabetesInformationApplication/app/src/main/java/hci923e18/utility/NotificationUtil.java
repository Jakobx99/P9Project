package hci923e18.utility;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

import hci923e18.diabetesinformationapplication.R;
import hci923e18.diabetesinformationapplication.Tabs.FrontPageActivity;
import hci923e18.diabetesinformationapplication.UCI.UCI;


public class NotificationUtil extends ContextWrapper {
    public static final String ANDROID_CHANNEL_ID = "hci923e18.ANDROID";
    public static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";
    //For SDK 22
    private NotificationManager mNotifyManager;
    private Notification mNotify;
    //For SDK 26
    private NotificationManager mManager;
    private Intent notifyIntent;
    private PendingIntent notifyPendingIntent;

    /**
     * Creation of class, sets the context of the class
     *
     * @param base context of caller
     */
    public NotificationUtil(Context base, Integer id) {
        super(base);
        creationOfIntent(id);
    }

    /**
     * Creates notification intent
     */
    private void creationOfIntent(Integer id) {
        if (id == 2){
            //TODO If needed fix so it is a diffenrent report page
            notifyIntent = new Intent(this, UCI.class);
        } else {
            notifyIntent = new Intent(this, FrontPageActivity.class);
        }

        // Set the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Create the PendingIntent
        notifyPendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
    }

    /**
     * Creates the android notification for home page
     *
     * @param title   title of notification
     * @param content content of the notification
     */
    public void CreateNotification(String title, String content) {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            Notification.Builder mNotificationBuilder = new Notification.Builder(this);
            mNotificationBuilder.setSmallIcon(R.drawable.logo)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setContentIntent(notifyPendingIntent);
            mNotify = mNotificationBuilder.build();
            ShowNotify();
        } else {
            createChannels();
            Notification.Builder mNotificationBuilder = new Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(R.drawable.logo)
                    .setContentIntent(notifyPendingIntent);
            mManager.notify(101, mNotificationBuilder.build());
        }
    }



    /**
     * Create notification for sub SDK 26
     */
    //SDK < 26
    private void ShowNotify() {
        mNotifyManager.notify(0, mNotify);
    }

    /**
     * Create notification for post SDK 26
     */
    //SDK >= 26
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannels() {
        // create android channel
        NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID, ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        // Sets whether notifications posted to this channel should display notification lights
        androidChannel.enableLights(true);
        // Sets whether notification posted to this channel should vibrate.
        androidChannel.enableVibration(true);
        androidChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
        // Sets the notification light color for notifications posted to this channel
        androidChannel.setLightColor(Color.RED);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(androidChannel);
    }

    /**
     * Receives notification manager
     *
     * @return notification manager instance
     */
    private NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
}
