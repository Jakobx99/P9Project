package hci923e18.diabetesinformationapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import hci923e18.database.Food;
import hci923e18.diabetesinformationapplication.Tabs.FrontPageActivity;
import hci923e18.utility.PopulateDatabase;
import hci923e18.utility.Time.TimeNotification;
import hci923e18.utility.Time.TimeService;

public class SplashActivity extends AppCompatActivity {
    private ImageView logo;
    private static int splashTimeOut=6000;

    /**
     * OnCreate method called when activity is initiated.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if (s.getBoolean("firstStart", true)){
            PopulateDatabase.populateDB(getApplicationContext());

            //Starts the time based notification service
            Intent in = new Intent(SplashActivity.this, TimeService.class);
            startService(in);
            TimeNotification timeNotification = new TimeNotification();
            timeNotification.setAlarm(getApplicationContext());
        }
//        if (_food.size() == 0)
//        {
//            PopulateDatabase.populateDB();
//
//        }
        else {
            Log.d("DATABASE", "THERE IS A DATABASE");
        }




            logo=(ImageView)findViewById(R.id.logo);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this,FrontPageActivity.class);
                    startActivity(i);
                    finish();
                }
            },splashTimeOut);

            Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mysplashanimation);
            logo.startAnimation(myanim);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
