package hci923e18.diabetesinformationapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.singledateandtimepicker.dialog.DoubleDateAndTimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import hci923e18.database.BloodGlucoseMeasurements;
import hci923e18.database.Profile;
import hci923e18.utility.KeyBoard;

public class BloodGlucoseListActivity extends AppCompatActivity {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Calendar startDate = Calendar.getInstance();
    private Calendar endDate = Calendar.getInstance();
    TextView textStartDate;
    TextView textEndDate;
    ExpandableListObjectAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listHead = new ArrayList<>();
    HashMap<String, List<BloodGlucoseMeasurements>> listChild = null;

    /**
     * OnCreate method called when activity is initiated.
     * Bindings are performed and setOnClickListener for button is made
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_glucose_list);
        KeyBoard.setHideKeyboardOnTouch(this, findViewById(R.id.container));

        textStartDate = findViewById(R.id.textInput_FromDate);
        textEndDate = findViewById(R.id.textInput_ToDate);
        expListView = findViewById(R.id.expandableListView_BloodGlycoseOverviewList);

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
        //calendar.add(Calendar.DATE, +1); //For american phones
        try {
            // preparing list data
            fetchData(calendar, Calendar.getInstance());
            listAdapter = new ExpandableListObjectAdapter(this,listHead,listChild,fetchProfile());
            // setting list adapter
            expListView.setAdapter(listAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "Du har ingen tidligere blodsukker målinger for denne uge", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        textStartDate.setText(sdf.format(calendar.getTime()));
        textEndDate.setText(sdf.format(Calendar.getInstance().getTime()));

        textStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker();
            }
        });
        textEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker();
            }
        });
    }

    /**
     * Creates the date and time picker
     */
    public void showDateTimePicker() {
        new DoubleDateAndTimePickerDialog.Builder(BloodGlucoseListActivity.this)
                .title("Indstil mellem 2 forskellige datoer")
                .tab0Text("Start")
                .tab1Text("Slut")
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
                            fetchData(startDate, endDate);
                            listAdapter = new ExpandableListObjectAdapter(BloodGlucoseListActivity.this,listHead,listChild, fetchProfile());
                            // setting list adapter
                            expListView.setAdapter(listAdapter);
                            textStartDate.setText(sdf.format(startDate.getTime()));
                            textEndDate.setText(sdf.format(endDate.getTime()));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }).display();
    }

    /**
     * Fetch data from database method using start and endDate
     * @param startDate date object used to query the database
     * @param endDate date object used to query the database
     */
    public void fetchData(Calendar startDate, Calendar endDate){
        //Fetch all measurements from/to date
        List<BloodGlucoseMeasurements> l = new ArrayList<>();
        listHead = new ArrayList<>();
        listChild = new HashMap<>();
        List<BloodGlucoseMeasurements> childBloodGlucoseMeasurements = new ArrayList<>();

        l.addAll(BloodGlucoseMeasurements.find(BloodGlucoseMeasurements.class, "_date >= ? and _date <= ? ORDER BY _date", Long.toString(startDate.getTimeInMillis()), Long.toString(endDate.getTimeInMillis())));

        Calendar currentCalendar = l.get(0).getDate();
        listHead.add(sdf.format(l.get(0).getDate().getTime()));

        for (BloodGlucoseMeasurements b:l) {
            if (!sdf.format(currentCalendar.getTimeInMillis()).equals(sdf.format(b.getDate().getTimeInMillis()))){
                listChild.put(sdf.format(currentCalendar.getTimeInMillis()), childBloodGlucoseMeasurements);
                currentCalendar = b.getDate();
                listHead.add(sdf.format(currentCalendar.getTimeInMillis()));
                childBloodGlucoseMeasurements = new ArrayList<>();
                childBloodGlucoseMeasurements.add(b);
            } else {
                childBloodGlucoseMeasurements.add(b);
            }
        }
        listChild.put(sdf.format(currentCalendar.getTimeInMillis()), childBloodGlucoseMeasurements);
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
}
