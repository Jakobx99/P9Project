package hci923e18.diabetesinformationapplication.BloodGlycoseOverview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hci923e18.database.BloodGlucoseMeasurements;
import hci923e18.database.Profile;
import hci923e18.diabetesinformationapplication.R;

public class BloodGlycoseOverviewActivity extends AppCompatActivity {

    private EditText lastMeasurementEdittext;
    private Button newPageButton;
    private Button redButton;
    private Button yellowButton;
    private Button greenButton;
    private TextView oldMeasurementList;
    private LinearLayout linearLayout;
    private List<BloodGlucoseMeasurements> bloodGlucoseMeasurement;
    private int reds;
    private int yellows;
    private int greens;
    private Profile profile;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_glycose_overview);

        lastMeasurementEdittext = findViewById(R.id.textView_bloodoverview_lastmeasurement);
        newPageButton = findViewById(R.id.button_bloodoverview_new);
        redButton = findViewById(R.id.button_bloodoverview_buttonred);
        yellowButton = findViewById(R.id.button_bloodoverview_buttonyellow);
        greenButton = findViewById(R.id.button_bloodoverview_buttongreen);
        oldMeasurementList = findViewById(R.id.textView_bloodoverview_old);
        linearLayout = findViewById(R.id.ratingBar_bloodoverview);

        try {
            bloodGlucoseMeasurement = fetchWeekMeasurements();
            profile = fetchProfile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        count = bloodGlucoseMeasurement.size();
        calculateRatio();
        //Sets the size of the red/yellow/green display on startup
        ViewTreeObserver vto = linearLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int localwidth  = linearLayout.getMeasuredWidth();
                redButton.setText("2");
                redButton.setWidth((localwidth/count)*reds);
                yellowButton.setText("3");
                yellowButton.setWidth((localwidth/count)*yellows);
                greenButton.setText("5");
                greenButton.setWidth((localwidth/count)*greens);
            }
        });


    }

    public List<BloodGlucoseMeasurements> fetchWeekMeasurements(){

        List<BloodGlucoseMeasurements> l = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
            calendar.add(Calendar.DATE, -1); // Substract 1 day until first day of week.
        }
        calendar.add(Calendar.DATE, +1);
        long firstDayOfWeekTimestamp = calendar.getTimeInMillis();

        l.addAll(BloodGlucoseMeasurements.find(BloodGlucoseMeasurements.class, "_date >= ?", Long.toString(firstDayOfWeekTimestamp)));
        return l;
    }

    private Profile fetchProfile(){
        Profile p;
        try {
            p = Profile.find(Profile.class, "ID = ?", "1").get(0);
        } catch (Exception e) {
            p = new Profile();
            p.set_idealBloodGlucoseLevel(5.5);
            p.set_insulinDuration(3.5);
            p.set_totalDailyInsulinConsumption(30.0);

            //Save default to DB
            p.save();
        }
        return p;
    }

//Calculates the number of reds, yellows and green measurements
    private void calculateRatio(){

        for (BloodGlucoseMeasurements b: bloodGlucoseMeasurement) {
            if (b.get_glucoseLevel() > profile.get_upperBloodGlucoseLevel()){
                yellows++;
            }else if(b.get_glucoseLevel() < profile.get_lowerBloodGlucoseLevel()){
                reds++;
            }
            else{
                greens++;
            }
        }

    }

}
