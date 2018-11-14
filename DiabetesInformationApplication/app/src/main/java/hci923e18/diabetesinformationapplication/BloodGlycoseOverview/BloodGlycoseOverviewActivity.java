package hci923e18.diabetesinformationapplication.BloodGlycoseOverview;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

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
    private int reds = 0;
    private int yellows = 0;
    private int greens = 0;
    private Profile profile;
    private int count;
    private GraphView graphView;
    private LineGraphSeries<DataPoint> mSeries;
    private PointsGraphSeries<DataPoint> redSeries;
    private PointsGraphSeries<DataPoint> yellowSeries;
    private PointsGraphSeries<DataPoint> greenSeries;
    private LineGraphSeries<DataPoint> lowestSeries;
    private LineGraphSeries<DataPoint> highestSeries;

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
        graphView = findViewById(R.id.graph_overview);


        try {
            bloodGlucoseMeasurement = fetchWeekMeasurements();
            profile = fetchProfile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Sets the size of the red/yellow/green display on startup
        count = bloodGlucoseMeasurement.size();
        calculateRatio();
        ViewTreeObserver vto = linearLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int localwidth  = linearLayout.getMeasuredWidth();
                if (count == 0){
                    redButton.setText("0");
                    yellowButton.setText("0");
                    greenButton.setText("0");
                } else {
                    if (reds == 0){
                        redButton.setText(String.valueOf(reds));
                        redButton.setWidth((localwidth/count));
                    }else{
                        redButton.setText(String.valueOf(reds));
                        redButton.setWidth((localwidth/count)*reds);
                    }
                    if (yellows == 0){
                        yellowButton.setText(String.valueOf(yellows));
                        yellowButton.setWidth((localwidth/count));
                    }else{
                        yellowButton.setText(String.valueOf(yellows));
                        yellowButton.setWidth((localwidth/count)*yellows);
                    }
                    if (greens == 0){
                        greenButton.setText(String.valueOf(greens));
                        greenButton.setWidth((localwidth/count));
                    }else {
                        greenButton.setText(String.valueOf(greens));
                        greenButton.setWidth((localwidth/count)*greens);
                    }
                }
            }
        });

        //Populate graph
        populateGraph(bloodGlucoseMeasurement);

        //Customize graph
        //graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
       // graphView.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space
        //graphView.getGridLabelRenderer().setHumanRounding(false);

        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(mSeries.getLowestValueX());
        graphView.getViewport().setMaxX(mSeries.getHighestValueX());
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(mSeries.getLowestValueY());
        graphView.getViewport().setMaxY(mSeries.getHighestValueY());
        graphView.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graphView.getGridLabelRenderer().setVerticalLabelsVisible(false);


//        // enable scaling and scrolling
//        graphView.getViewport().setScalable(true);
//        graphView.getViewport().setScalableY(true);




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

    private void populateGraph(List<BloodGlucoseMeasurements> bloodList){
        int i = 0;
        int count = bloodList.size();
        DataPoint[] values = new DataPoint[count];
        for (BloodGlucoseMeasurements b:bloodList) {
            DataPoint temp = new DataPoint(b.getDate().getTime(),b.get_glucoseLevel());
            values[i] = temp;
            i++;
        }

        //create graph
        mSeries = new LineGraphSeries<>(values);
        mSeries.setColor(Color.BLUE);

        DataPoint[] lowest = new DataPoint[2];
        lowest[0] = new DataPoint(mSeries.getLowestValueX(), profile.get_lowerBloodGlucoseLevel());
        lowest[1] = new DataPoint(mSeries.getHighestValueX(), profile.get_lowerBloodGlucoseLevel());
        lowestSeries = new LineGraphSeries<>(lowest);
        lowestSeries.setColor(Color.RED);
        graphView.addSeries(lowestSeries);

        DataPoint[] highest = new DataPoint[2];
        highest[0] = new DataPoint(mSeries.getLowestValueX(), profile.get_upperBloodGlucoseLevel());
        highest[1] = new DataPoint(mSeries.getHighestValueX(), profile.get_upperBloodGlucoseLevel());
        highestSeries = new LineGraphSeries<>(highest);
        highestSeries.setColor(Color.YELLOW);
        graphView.addSeries(highestSeries);

        graphView.addSeries(mSeries);

        //Create reg, yellow and green

        DataPoint[] redValues = new DataPoint[reds];
        DataPoint[] yellowValues = new DataPoint[yellows];
        DataPoint[] greenValues = new DataPoint[greens];
        int r = 0;
        int y = 0;
        int g = 0;

        for (BloodGlucoseMeasurements b:bloodList) {
            if (b.get_glucoseLevel() > profile.get_upperBloodGlucoseLevel()){
                DataPoint temp = new DataPoint(b.getDate().getTime(),b.get_glucoseLevel());
                yellowValues[y] = temp;
                y++;
            }else if(b.get_glucoseLevel() < profile.get_lowerBloodGlucoseLevel()){
                DataPoint temp = new DataPoint(b.getDate().getTime(),b.get_glucoseLevel());
                redValues[r] = temp;
                r++;
            }
            else{
                DataPoint temp = new DataPoint(b.getDate().getTime(),b.get_glucoseLevel());
                greenValues[g] = temp;
                g++;
            }
        }

        redSeries = new PointsGraphSeries<>(redValues);
        redSeries.setShape(PointsGraphSeries.Shape.POINT);
        redSeries.setColor(Color.RED);
        graphView.addSeries(redSeries);
        yellowSeries = new PointsGraphSeries<>(yellowValues);
        yellowSeries.setShape(PointsGraphSeries.Shape.POINT);
        yellowSeries.setColor(Color.YELLOW);
        graphView.addSeries(yellowSeries);
        greenSeries = new PointsGraphSeries<>(greenValues);
        greenSeries.setShape(PointsGraphSeries.Shape.POINT);
        greenSeries.setColor(Color.GREEN);
        graphView.addSeries(greenSeries);

    }

}
