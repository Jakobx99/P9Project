package hci923e18.diabetesinformationapplication;

import android.app.Dialog;
import android.app.TimePickerDialog;
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

        upperLimitBS = 16.0;
        lowerLimitBS = 4.0;

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
                    Toast.makeText(getApplicationContext(),
                            String.valueOf(buttonView.isChecked()), Toast.LENGTH_SHORT).show();
                }
                else {
                    beforeMeal.setClickable(true);
                    afterMeal.setClickable(true);
                    Toast.makeText(getApplicationContext(),
                            String.valueOf(buttonView.isChecked()), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getApplicationContext(),
                            String.valueOf(buttonView.isChecked()), Toast.LENGTH_SHORT).show();
                } else {
                    noMark.setClickable(true);
                    afterMeal.setClickable(true);
                    Toast.makeText(getApplicationContext(),
                            String.valueOf(buttonView.isChecked()), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getApplicationContext(),
                            String.valueOf(buttonView.isChecked()), Toast.LENGTH_SHORT).show();
                } else {
                    noMark.setClickable(true);
                    beforeMeal.setClickable(true);
                    Toast.makeText(getApplicationContext(),
                            String.valueOf(buttonView.isChecked()), Toast.LENGTH_SHORT).show();
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

                }

            }
        });


    }
    @Override
    public void OnTimeSet(String time) {
        enteredTime.setText(time);
    }
}
