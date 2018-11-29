package hci923e18.diabetesinformationapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.List;

import hci923e18.database.Food;
import hci923e18.database.Profile;
import hci923e18.diabetesinformationapplication.Tabs.FrontPageActivity;
import hci923e18.utility.PopulateDatabase;

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

        List<Food> _food = Food.listAll(Food.class);
        if (_food.size() == 0)
        {
            PopulateDatabase.populateDB();
        }
        else {//Do nothing
        }
    }
}