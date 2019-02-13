package hci923e18.diabetesinformationapplication.LongtermMeasurements;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.DoubleDateAndTimePickerDialog;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hci923e18.database.LongTermBloodGlucose;
import hci923e18.database.Profile;
import hci923e18.diabetesinformationapplication.BloodGlycoseOverview.GraphActivity;
import hci923e18.diabetesinformationapplication.R;
import hci923e18.diabetesinformationapplication.UCI.UCI;

public class LongtermMeasurement extends AppCompatActivity {

    public EditText editTextFrom;
    public EditText editTextTo;
    public Button saveButton;
    public EditText enteredMeasurement;
    Profile p;
    Double upperLimitBS;
    Double lowerLimitBS;
    private Calendar startDate;
    private Calendar endDate;
    private FloatingActionButton floatingActionButtonLongtermMeasurement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longterm_measurement);

        editTextFrom = findViewById(R.id.edittext_langtid_fradato);
        editTextTo = findViewById(R.id.editText_langtid_til);
        saveButton = findViewById(R.id.button_langtid_gem);
        enteredMeasurement = findViewById(R.id.editText_langtid_newbloodglucoselevelEnteredBS);
        p = fetchProfile();

        upperLimitBS = p.get_upperBloodGlucoseLevel();
        lowerLimitBS = p.get_lowerBloodGlucoseLevel();


        enteredMeasurement.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(count > 0) {
                    // change that shit border color when high or low
                    double bloodGlucoseLevelInput = Double.parseDouble(0 + s.toString());

                    if (bloodGlucoseLevelInput <= lowerLimitBS) {
                        enteredMeasurement.setBackgroundResource(R.drawable.rounded_edittext_red);
                    } else if (bloodGlucoseLevelInput >= upperLimitBS) {
                        enteredMeasurement.setBackgroundResource(R.drawable.rounded_edittext_yellow);
                    } else {
                        enteredMeasurement.setBackgroundResource(R.drawable.rounded_edittext_green);
                    }
                }
                else{
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker(0);
            }
        });

        editTextTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker(1);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LongTermBloodGlucose longTermBloodGlucose = new LongTermBloodGlucose(startDate, endDate, Double.parseDouble(0 + enteredMeasurement.getText().toString()));

                try {
                    longTermBloodGlucose.save();
                    Toast.makeText(LongtermMeasurement.this, "Måling gemt", Toast.LENGTH_LONG).show();
                    finish();
                } catch (Exception e) {
                    
                }

            }
        });

        floatingActionButtonLongtermMeasurement = findViewById(R.id.floatingActionButton_home);
        floatingActionButtonLongtermMeasurement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LongtermMeasurement.this, UCI.class);
                intent.putExtra("PageName", "LongtermMeasurementPage");
                startActivity(intent);
            }
        });

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
     * Creates the date and time picker
     */
    public void showDateTimePicker(final int indentifier) {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        Integer screenSize = Math.round(displayMetrics.ydpi);
        new SingleDateAndTimePickerDialog.Builder(LongtermMeasurement.this)
                //.bottomSheet()
                //.curved()
                //.minutesStep(15)
                //.displayHours(false)
                //.displayMinutes(false)
                //.todayText("aujourd'hui")
                .displayListener(new SingleDateAndTimePickerDialog.DisplayListener() {
                    @Override
                    public void onDisplayed(SingleDateAndTimePicker picker) {

                    }
                })

                .title("Vælg dato")
                .listener(new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        if (indentifier == 0){
                            startDate = Calendar.getInstance();
                            startDate.setTime(date);
                            updateFrom();
                        } else {
                            endDate = Calendar.getInstance();
                            endDate.setTime(date);
                            updateTo();
                        }

                    }
                }).display();
    }

    private void updateFrom(){
        editTextFrom.setText(startDate.getTime().toString());
    }
    private void updateTo(){
        editTextTo.setText(endDate.getTime().toString());
    }

}
