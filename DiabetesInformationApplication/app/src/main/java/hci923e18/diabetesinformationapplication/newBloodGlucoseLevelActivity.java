package hci923e18.diabetesinformationapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Objects;

import hci923e18.database.BloodGlucoseMeasurements;
import hci923e18.database.Profile;
import hci923e18.utility.KeyBoard;
import hci923e18.utility.TimePickerFragment;
import hci923e18.utility.TimepickerInterface;

import static java.lang.Double.parseDouble;

public class newBloodGlucoseLevelActivity extends AppCompatActivity implements TimepickerInterface {

    EditText enteredTime;
    EditText enteredBloodGlucoseLevel;
    Spinner typeOfMeal;
    ToggleButton noMark;
    ToggleButton beforeMeal;
    ToggleButton afterMeal;
    Button saveBloodGlucoseLevel;
    String mealType;
    Profile p;

    Double upperLimitBS;
    Double lowerLimitBS;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bloodglucoselevel);
        KeyBoard.setHideKeyboardOnTouch(this, findViewById(R.id.container));

        enteredTime = findViewById(R.id.editText_newbloodglucoselevelinputtime);
        enteredBloodGlucoseLevel = findViewById(R.id.editText_newbloodglucoselevelEnteredBS);
        typeOfMeal = findViewById(R.id.spinner_newbloodglucoselevel);
        noMark = findViewById(R.id.toggleButton_newbloodglucoselevelnomark);
        beforeMeal = findViewById(R.id.toggleButton_newglucoselevelbeforefood);
        afterMeal = findViewById(R.id.toggleButton_newglucoselevelafterfood);
        saveBloodGlucoseLevel = findViewById(R.id.button_newbloodglucoselevelsave);

        p = fetchProfile();

        upperLimitBS = p.get_upperBloodGlucoseLevel();
        lowerLimitBS = p.get_lowerBloodGlucoseLevel();

        String [] values =
                {"Morgenmad","Middagsmad","Aftensmad"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        typeOfMeal.setAdapter(adapter);

        //Spinner
        typeOfMeal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mealType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        enteredTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(),"Timepicker");
            }
        });



        enteredBloodGlucoseLevel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(count > 0) {
                    // change that shit border color when high or low
                    double bloodGlucoseLevelInput = Double.parseDouble(s.toString());

                    if (bloodGlucoseLevelInput <= lowerLimitBS) {
                        enteredBloodGlucoseLevel.setBackgroundResource(R.drawable.rounded_edittext_red);
                    } else if (bloodGlucoseLevelInput >= upperLimitBS) {
                        enteredBloodGlucoseLevel.setBackgroundResource(R.drawable.rounded_edittext_yellow);
                    } else {
                        enteredBloodGlucoseLevel.setBackgroundResource(R.drawable.rounded_edittext_green);
                    }
                }
                else{

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        noMark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    beforeMeal.setChecked(false);
                    afterMeal.setChecked(false);
                    beforeMeal.setClickable(false);
                    afterMeal.setClickable(false);

                }
                else {
                    beforeMeal.setClickable(true);
                    afterMeal.setClickable(true);

                }
            }

        });



        beforeMeal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    noMark.setChecked(false);
                    afterMeal.setChecked(false);
                    noMark.setClickable(false);
                    afterMeal.setClickable(false);

                } else {
                    noMark.setClickable(true);
                    afterMeal.setClickable(true);

                }
            }
        });

        afterMeal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    noMark.setChecked(false);
                    beforeMeal.setChecked(false);
                    noMark.setClickable(false);
                    beforeMeal.setClickable(false);

                } else {
                    noMark.setClickable(true);
                    beforeMeal.setClickable(true);

                }
            }
        });


        saveBloodGlucoseLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(enteredBloodGlucoseLevel.getText().toString().isEmpty())
                {
                    Toast.makeText(newBloodGlucoseLevelActivity.this, "Du mangler at indtaste et blodsukker", Toast.LENGTH_SHORT).show();
                }
                else if(!noMark.isChecked() && !afterMeal.isChecked() && !beforeMeal.isChecked())
                {
                    Toast.makeText(newBloodGlucoseLevelActivity.this, "Du mangler at vælge en markering", Toast.LENGTH_SHORT).show();
                }
                else if(enteredTime.getText().toString().isEmpty())
                {
                    Toast.makeText(newBloodGlucoseLevelActivity.this, "Du mangler at indtaste tid", Toast.LENGTH_SHORT).show();
                }
                else{
                    BloodGlucoseMeasurements newBloodGlucoseLevel = new BloodGlucoseMeasurements();
                    Calendar newTime = Calendar.getInstance();
                    String timeHour = enteredTime.getText().toString();
                    String[] parts = timeHour.split(":");
                    String hour = parts[0]; // Hours
                    String minut = parts[1]; // Minuts

                    newTime.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hour));
                    newTime.set(Calendar.MINUTE,Integer.parseInt((minut)));
                    newBloodGlucoseLevel.setDate(newTime);

                    newBloodGlucoseLevel.set_glucoseLevel(Double.parseDouble(enteredBloodGlucoseLevel.getText().toString()));

                    if (noMark.isChecked())
                    {
                        newBloodGlucoseLevel.set_beforeAfter(0);  //ingen markering
                    }
                    else if (beforeMeal.isChecked())
                    {
                        newBloodGlucoseLevel.set_beforeAfter(1);  // før måltid
                    }
                    else if(afterMeal.isChecked())
                    {
                        newBloodGlucoseLevel.set_beforeAfter(2);   // efter måltid
                    }
                    if(Objects.equals(mealType,"Morgenmad"))
                    {
                        newBloodGlucoseLevel.set_category(0);  // morgenmad
                    }
                    else if(Objects.equals(mealType,"Middagsmad"))
                    {
                        newBloodGlucoseLevel.set_category(1);   // middagsmad
                    }
                    else if(Objects.equals(mealType,"Aftensmad"))
                    {
                        newBloodGlucoseLevel.set_category(2);   //aftensmad
                    }


                    newBloodGlucoseLevel.save();

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",1);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();

                }


            }
        });


    }
    @Override
    public void OnTimeSet(String time) {
        enteredTime.setText(time);
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
