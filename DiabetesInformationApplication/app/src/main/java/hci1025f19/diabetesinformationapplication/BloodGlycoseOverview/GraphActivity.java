package hci1025f19.diabetesinformationapplication.BloodGlycoseOverview;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.github.florent37.singledateandtimepicker.dialog.DoubleDateAndTimePickerDialog;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import hci1025f19.database.BloodGlucoseMeasurements;
import hci1025f19.database.LongTermBloodGlucose;
import hci1025f19.database.Profile;
import hci1025f19.diabetesinformationapplication.R;
import hci1025f19.diabetesinformationapplication.UCI.UCI;

public class GraphActivity extends AppCompatActivity {

    private ArrayList<BloodGlucoseMeasurements> graphList = new ArrayList<>();
    private GraphView graphView;
    private LineGraphSeries<DataPoint> mSeries;
    private PointsGraphSeries<DataPoint> redSeries;
    private PointsGraphSeries<DataPoint> yellowSeries;
    private PointsGraphSeries<DataPoint> greenSeries;
    private LineGraphSeries<DataPoint> lowestSeries;
    private LineGraphSeries<DataPoint> highestSeries;
    private int reds = 0;
    private int yellows = 0;
    private int greens = 0;
    private Profile profile;
    private List<LongTermBloodGlucose> longTermBloodGlucoses;
    private LineGraphSeries<DataPoint> longTermSeries;
    public Calendar startDate;
    public Calendar endDate;
    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM \n'Kl.' HH:mm");
    private FloatingActionButton floatingActionButtonGraphPage;

    /**
     * OnCreate method for this activity
     * @param savedInstanceState The saved instance of this activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        graphView = findViewById(R.id.graph_specific);

        try {
            Bundle extra = getIntent().getBundleExtra("extra");
            graphList = (ArrayList<BloodGlucoseMeasurements>) extra.getSerializable("graphData");
        } catch (Exception e) {

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker();
            }
        });

        try {
            profile = fetchProfile();
            calculateRatio(graphList);
            populateGraph(graphList);
            getLongTermBloodGlucoseVisible();
            addSeries();
            adjustGraph();
        } catch (Exception e) {
            e.printStackTrace();

        }

//        mSeries.setOnDataPointTapListener(new OnDataPointTapListener() {
//            @Override
//            public void onTap(Series series, DataPointInterface dataPoint) {
//                Toast.makeText(GraphActivity.this, "Måling: " + dataPoint.getY() + " mmol/L" + "\n" + "Dato: " + sdf.format(dataPoint.getX()), Toast.LENGTH_SHORT).show();
//            }
//        });
        yellowSeries.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(GraphActivity.this, "Måling: " + dataPoint.getY() + " mmol/L" + "\n" + "Dato: " + sdf.format(dataPoint.getX()), Toast.LENGTH_SHORT).show();
            }
        });
        redSeries.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(GraphActivity.this, "Måling: " + dataPoint.getY() + " mmol/L" + "\n" + "Dato: " + sdf.format(dataPoint.getX()), Toast.LENGTH_SHORT).show();
            }
        });
        greenSeries.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(GraphActivity.this, "Måling: " + dataPoint.getY() + " mmol/L" + "\n" + "Dato: " + sdf.format(dataPoint.getX()), Toast.LENGTH_SHORT).show();
            }
        });

        floatingActionButtonGraphPage = findViewById(R.id.floatingActionButton_graphPage);
        floatingActionButtonGraphPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GraphActivity.this, UCI.class);
                intent.putExtra("PageName", "GraphPage");
                startActivity(intent);
            }
        });
    }

    /**
     * Creates the different series that are displayed on the graph
     * @param bloodList
     */
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

        DataPoint[] highest = new DataPoint[2];
        highest[0] = new DataPoint(mSeries.getLowestValueX(), profile.get_upperBloodGlucoseLevel());
        highest[1] = new DataPoint(mSeries.getHighestValueX(), profile.get_upperBloodGlucoseLevel());
        highestSeries = new LineGraphSeries<>(highest);
        highestSeries.setColor(Color.YELLOW);

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

        yellowSeries = new PointsGraphSeries<>(yellowValues);
        yellowSeries.setShape(PointsGraphSeries.Shape.TRIANGLE);
        yellowSeries.setColor(Color.YELLOW);

        greenSeries = new PointsGraphSeries<>(greenValues);
        greenSeries.setShape(PointsGraphSeries.Shape.POINT);
        greenSeries.setColor(Color.GREEN);
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
            p.set_totalDailyInsulinConsumption(30.0);

            //Save default to DB
            p.save();
        }
        return p;
    }

    /**
     * Fetches a list of long term blood glucose measurements from the database and constructs the series for this
     */
    private void getLongTermBloodGlucoseVisible(){
        longTermBloodGlucoses = new ArrayList<>();
        longTermBloodGlucoses = LongTermBloodGlucose.listAll(LongTermBloodGlucose.class);


        List<DataPoint> relevantLongTerm = new ArrayList<>();


        int i = 0;

        for (LongTermBloodGlucose l: longTermBloodGlucoses){
            if (l.getStart().getTimeInMillis() < mSeries.getLowestValueX() && l.getEnd().getTimeInMillis() < mSeries.getLowestValueX()){

            } else if(mSeries.getLowestValueX() >= l.getStart().getTimeInMillis() && mSeries.getHighestValueX() <= l.getEnd().getTimeInMillis()){
                DataPoint temp1 = new DataPoint(mSeries.getLowestValueX(), l.get_value());
                DataPoint temp2 = new DataPoint(mSeries.getHighestValueX(), l.get_value());
                relevantLongTerm.add(temp1);
                relevantLongTerm.add(temp2);

            } else if(mSeries.getLowestValueX() >= l.getStart().getTimeInMillis() && mSeries.getHighestValueX() > l.getEnd().getTimeInMillis()){
                DataPoint temp1 = new DataPoint(mSeries.getLowestValueX(), l.get_value());
                DataPoint temp2 = new DataPoint(l.getEnd().getTimeInMillis(), l.get_value());
                relevantLongTerm.add(temp1);
                relevantLongTerm.add(temp2);
            } else if(mSeries.getLowestValueX() < l.getStart().getTimeInMillis() && mSeries.getHighestValueX() <= l.getEnd().getTimeInMillis()){
                DataPoint temp1 = new DataPoint(l.getStart().getTimeInMillis(), l.get_value());
                DataPoint temp2 = new DataPoint(mSeries.getHighestValueX(), l.get_value());
                relevantLongTerm.add(temp1);
                relevantLongTerm.add(temp2);

            } else if(mSeries.getLowestValueX() < l.getStart().getTimeInMillis() && mSeries.getHighestValueX() > l.getEnd().getTimeInMillis()){
                DataPoint temp1 = new DataPoint(l.getStart().getTimeInMillis(), l.get_value());
                DataPoint temp2 = new DataPoint(l.getEnd().getTimeInMillis(), l.get_value());
                relevantLongTerm.add(temp1);
                relevantLongTerm.add(temp2);
            }
        }

        Collections.sort(relevantLongTerm, new Comparator<DataPoint>() {
            @Override
            public int compare(DataPoint dataPoint, DataPoint t1) {
                return Double.compare(dataPoint.getX(), t1.getX());
            }
        });


        int count = relevantLongTerm.size();
        DataPoint[] values = new DataPoint[count];
        for (DataPoint b:relevantLongTerm) {
            values[i] = b;
            i++;
        }


        longTermSeries = new LineGraphSeries<>(values);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        paint.setPathEffect(new DashPathEffect(new float[]{5,10,15,20}, 0));
        longTermSeries.setCustomPaint(paint);
    }

    /**
     * Calculates the ratio between low, normal and high measurements
     * @param bloodList A list of blood glucose measurements
     */
    private void calculateRatio(List<BloodGlucoseMeasurements> bloodList){
        yellows = 0;
        reds = 0;
        greens = 0;

        for (BloodGlucoseMeasurements b: bloodList) {
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
     * Method used to control the sequence all the different series are added to the graph
     */
    private void addSeries(){
        graphView.addSeries(mSeries);
        graphView.addSeries(lowestSeries);
        graphView.addSeries(highestSeries);
        graphView.addSeries(redSeries);
        graphView.addSeries(yellowSeries);
        graphView.addSeries(greenSeries);
        graphView.addSeries(longTermSeries);
    }

    /**
     * Adjusts the graphs settings
     */
    private void adjustGraph() {

        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(mSeries.getLowestValueX() - 500000);
        graphView.getViewport().setMaxX(mSeries.getHighestValueX() +500000);
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(mSeries.getHighestValueY() + 5);


        graphView.getGridLabelRenderer().setHorizontalLabelsVisible(true);
        graphView.getGridLabelRenderer().setVerticalLabelsVisible(true);

        // enable scaling and scrolling
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(false);

        // set date label formatter

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return "\n" + sdf.format(value);
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + " mmol/L";
                }
            }
        });

        //graphView.getGridLabelRenderer().setHorizontalLabelsAngle(90);
        //graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(GraphActivity.this));
        graphView.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space
    }

    /**
     * Creates the date and time picker
     */
    public void showDateTimePicker() {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        Integer screenSize = Math.round(displayMetrics.ydpi);
        new DoubleDateAndTimePickerDialog.Builder(GraphActivity.this)
                .title("Indstil mellem 2 forskellige datoer")
                .tab0Text("Start")
                .tab1Text("Slut")
                .bottomSheetHeight(screenSize)
                .mainColor(Color.RED)
                .titleTextColor(Color.WHITE)
                .listener(new DoubleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(List<Date> dates) {
                        startDate = Calendar.getInstance();
                        endDate = Calendar.getInstance();

                        startDate.setTime(dates.get(0));
                        endDate.setTime(dates.get(1));

                        try {
                            fetchNewAndRedraw();
                        } catch (Exception e) {

e.printStackTrace();
                        }
                    }

                }).display();
    }

    /**
     * Fetches new objects from the database and redraws the graph based on these
     */
    private void fetchNewAndRedraw(){
        List<BloodGlucoseMeasurements> l = new ArrayList<>();
        sortstartandendDate();

        l.addAll(BloodGlucoseMeasurements.find(BloodGlucoseMeasurements.class, "_date >= ? and _date <= ? ORDER BY _date", Long.toString(startDate.getTimeInMillis()), Long.toString(endDate.getTimeInMillis())));

        graphView.removeAllSeries();

        calculateRatio(l);
        populateGraph(l);
        //getLongTermBloodGlucoseVisible();
        addSeries();
        adjustGraph();
        mSeries.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(GraphActivity.this, "Måling: " + dataPoint.getY() + " mmol/L" + "\n" + "Dato: " + sdf.format(dataPoint.getX()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sortstartandendDate(){
        if (startDate.getTimeInMillis() > endDate.getTimeInMillis()){
            Calendar temp = startDate;
            startDate = endDate;
            endDate =temp;
        }
    }
}
