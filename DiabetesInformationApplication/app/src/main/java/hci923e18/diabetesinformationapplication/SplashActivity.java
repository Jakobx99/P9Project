package hci923e18.diabetesinformationapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import hci923e18.diabetesinformationapplication.Tabs.FrontPageActivity;

public class SplashActivity extends AppCompatActivity {
    private ImageView logo;
    private static int splashTimeOut=6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



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
    }
