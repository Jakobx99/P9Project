package hci923e18.diabetesinformationapplication.BloodGlycoseOverview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import hci923e18.database.BloodGlucoseMeasurements;
import hci923e18.database.Profile;
import hci923e18.diabetesinformationapplication.BloodGlucoseListActivity;
import hci923e18.diabetesinformationapplication.R;
import hci923e18.diabetesinformationapplication.UCI.UCI;
import hci923e18.diabetesinformationapplication.newBloodGlucoseLevelActivity;

public class BloodGlycoseOverviewActivity extends AppCompatActivity {

    private EditText lastMeasurementEdittext;
    private Button newPageButton;
    private Button redButton;
    private Button yellowButton;
    private Button greenButton;
    private TextView oldMeasurementList;
    private LinearLayout linearLayout;
    private List<BloodGlucoseMeasurements> bloodGlucoseMeasurement = new ArrayList<>();
    private int reds = 0;
    private int yellows = 0;
    private int greens = 0;
    private Profile profile;
    private int count = 0;
    private GraphView graphView;
    private LineGraphSeries<DataPoint> mSeries;
    private PointsGraphSeries<DataPoint> redSeries;
    private PointsGraphSeries<DataPoint> yellowSeries;
    private PointsGraphSeries<DataPoint> greenSeries;
    private LineGraphSeries<DataPoint> lowestSeries;
    private LineGraphSeries<DataPoint> highestSeries;
    private TextView textViewSeneste;
    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM \n'Kl.' HH:mm");
    private FloatingActionButton floatingActionButtonBloodOverview;

    /**
     * OnCreate method for this activity
     * @param savedInstanceState The saved instance of this activity
     */
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
        textViewSeneste = findViewById(R.id.textView_overview_seneste);

        newPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BloodGlycoseOverviewActivity.this, newBloodGlucoseLevelActivity.class);
                BloodGlycoseOverviewActivity.this.startActivityForResult(i, 1);
            }
        });

        oldMeasurementList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BloodGlycoseOverviewActivity.this, BloodGlucoseListActivity.class);
                BloodGlycoseOverviewActivity.this.startActivityForResult(i, 1);
            }
        });

        floatingActionButtonBloodOverview = findViewById(R.id.floatingActionButton_blood_glycose_overview);
        floatingActionButtonBloodOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BloodGlycoseOverviewActivity.this, UCI.class);
                intent.putExtra("PageName", "BloodGlycoseOverviewPage");
                startActivity(intent);
            }
        });

        setUp();
    }

    /**
     * Method used to initiate the graph and other elements of the page
     */
    private void setUp(){
        bloodGlucoseMeasurement = new ArrayList<>();
        graphView.removeAllSeries();
        try {
            bloodGlucoseMeasurement = fetchWeekMeasurements();
            profile = fetchProfile();
        } catch (Exception e) {

        }

//        Collections.sort(bloodGlucoseMeasurement, new Comparator<BloodGlucoseMeasurements>() {
//            @Override
//            public int compare(BloodGlucoseMeasurements bloodGlucoseMeasurements, BloodGlucoseMeasurements t1) {
//                return Long.compare(bloodGlucoseMeasurements.getDate().getTimeInMillis(), t1.getDate().getTimeInMillis());
//            }
//        });

                //Sets the size of the red/yellow/green display on startup
        if (bloodGlucoseMeasurement.size() != 0){
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
            graphView.getViewport().setXAxisBoundsManual(true);
            graphView.getViewport().setMinX(mSeries.getLowestValueX());
            graphView.getViewport().setMaxX(mSeries.getHighestValueX());
            graphView.getViewport().setYAxisBoundsManual(true);
            graphView.getViewport().setMinY(mSeries.getLowestValueY());
            graphView.getViewport().setMaxY(mSeries.getHighestValueY());
            graphView.getGridLabelRenderer().setHorizontalLabelsVisible(true);
            graphView.getGridLabelRenderer().setVerticalLabelsVisible(true);

            // set date label formatter

            graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                @Override
                public String formatLabel(double value, boolean isValueX) {
                    if (isValueX) {
                        // show normal x values
                        return "";
                    } else {
                        // show currency for y values
                        return super.formatLabel(value, isValueX) + " mmol/L";
                    }
                }
            });

            //graphView.getGridLabelRenderer().setHorizontalLabelsAngle(90);
            //graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(GraphActivity.this));
            graphView.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space

            graphView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent graph = new Intent(BloodGlycoseOverviewActivity.this, GraphActivity.class);
                    Bundle extra = new Bundle();
                    extra.putSerializable("graphData", (Serializable) bloodGlucoseMeasurement);
                    graph.putExtra("extra", extra);
                    startActivity(graph);

                }
            });

            BloodGlucoseMeasurements bloodGlucoseMeasurements = null;
            try {
                List<BloodGlucoseMeasurements> b = new ArrayList<>();
                b.addAll(BloodGlucoseMeasurements.listAll(BloodGlucoseMeasurements.class));
                int last = b.size() - 1;

                bloodGlucoseMeasurements = b.get(last);
                b.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }

            textViewSeneste.setText("Seneste måling: " + sdf.format(bloodGlucoseMeasurements.getDate().getTime()));
            lastMeasurementEdittext.setText(bloodGlucoseMeasurements.get_glucoseLevel().toString());
            lastMeasurementEdittext.setEnabled(false);

            if (bloodGlucoseMeasurements.get_glucoseLevel() <= profile.get_lowerBloodGlucoseLevel()) {
                lastMeasurementEdittext.setBackgroundResource(R.drawable.rounded_edittext_red);
            } else if (bloodGlucoseMeasurements.get_glucoseLevel() >= profile.get_upperBloodGlucoseLevel()) {
                lastMeasurementEdittext.setBackgroundResource(R.drawable.rounded_edittext_yellow);
            } else {
                lastMeasurementEdittext.setBackgroundResource(R.drawable.rounded_edittext_green);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                setUp();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

    /**
     * Method used to fetch the measurements for the current week
     * @return A list of blood glucose measurements for the current week
     */
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
        //calendar.add(Calendar.DATE, +1); - For american telephones
        long firstDayOfWeekTimestamp = calendar.getTimeInMillis();

        l.addAll(BloodGlucoseMeasurements.find(BloodGlucoseMeasurements.class, "_date >= ? ORDER BY _date", Long.toString(firstDayOfWeekTimestamp)));
        return l;
    }

    /**
     * Fetches the profile from the database
     * @return The profile object
     */
    private Profile fetchProfile(){
        Profile p;
        try {
            p = Profile.find(Profile.class, "ID = ?", "1").get(0);
        } catch (Exception e) {
            p = new Profile();
            p.set_idealBloodGlucoseLevel(5.5);
            p.set_insulinDuration(3.5);
            p.set_totalDailyInsulinConsumption(30.0);
            p.set_upperBloodGlucoseLevel(15.0);
            p.set_lowerBloodGlucoseLevel(3.0);
            p.set_beforeBloodGlucoseLevel(8.0);
            p.set_afterBloodGlucoseLevel(8.0);

            //Save default to DB
            p.save();
        }
        return p;
    }

    /**
     * Calculates the ratio between low, normal and high measurements
     */
    private void calculateRatio(){
        yellows = 0;
        reds = 0;
        greens = 0;

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

    /**
     * Creates the different series that are displayed on the graph
     * @param bloodList
     */
    private void populateGraph(List<BloodGlucoseMeasurements> bloodList){
        redSeries = null;
        yellowSeries = null;
        greenSeries = null;


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
        redSeries.setShape(PointsGraphSeries.Shape.RECTANGLE);
        redSeries.setColor(Color.RED);
        graphView.addSeries(redSeries);
        yellowSeries = new PointsGraphSeries<>(yellowValues);
        yellowSeries.setShape(PointsGraphSeries.Shape.TRIANGLE);
        yellowSeries.setColor(Color.YELLOW);
        graphView.addSeries(yellowSeries);
        greenSeries = new PointsGraphSeries<>(greenValues);
        greenSeries.setShape(PointsGraphSeries.Shape.POINT);
        greenSeries.setColor(Color.GREEN);
        graphView.addSeries(greenSeries);

    }

}
