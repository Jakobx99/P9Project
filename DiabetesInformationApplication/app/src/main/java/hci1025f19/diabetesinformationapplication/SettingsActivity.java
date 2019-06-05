package hci1025f19.diabetesinformationapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.TooltipCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import hci1025f19.database.Profile;
import hci1025f19.diabetesinformationapplication.UCI.UCI;
import hci1025f19.utility.KeyBoard;

public class SettingsActivity extends AppCompatActivity {

    public Button saveButton;
    public EditText idealBloodGlucoseLevel;
    public EditText totalDailyInsulinConsumption;
    public EditText upperBloodGlucoseLevel;
    public EditText lowerBloodGlucoseLevel;
    public TextView titleUpper;
    public TextView titleLower;
    public ImageButton infoBloodGlucose;
    public ImageButton infoInsulinUsage;
    public ImageButton infoUpperLimit;
    public ImageButton infoLowerLimit;
    public EditText carboRatio;
    public EditText sensitive;
    public Double daily = 30.0;
    public Double ratio;
    public Double sensitiveDouble;

    private FloatingActionButton floatingActionButtonSettings;

    Profile p;

    /**
     * OnCreate method called when activity is initiated.
     * Bindings are performed and setOnClickListener for button is made
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        KeyBoard.setHideKeyboardOnTouch(this, findViewById(R.id.container));


        saveButton = findViewById(R.id.button_save);
        idealBloodGlucoseLevel = findViewById(R.id.textView_idealBloodGlucoseLevel);

        totalDailyInsulinConsumption = findViewById(R.id.textView_totalDailyInsulinConsumption);
        infoBloodGlucose = findViewById(R.id.imageButtonBloodGlucose);

        infoInsulinUsage = findViewById(R.id.imageButtonInsulinUsage);
        upperBloodGlucoseLevel = findViewById(R.id.textView_UpperBloodGlucoseLevel);
        titleUpper = findViewById(R.id.textView_titleUpperBloodGlucoseLevel);
        infoUpperLimit = findViewById(R.id.imageButton_UpperBloodGlucoseLevel);
        lowerBloodGlucoseLevel = findViewById(R.id.textView_LowerBloodGlucoseLevel);
        titleLower = findViewById(R.id.textView_titleLowerBloodGlucoseLevel);
        infoLowerLimit = findViewById(R.id.imageButton_LowerBloodGlucoseLevel);
        carboRatio = findViewById(R.id.textView_carboratio);
        sensitive = findViewById(R.id.textView_insulinSensitive);

        fetchdata();
        idealBloodGlucoseLevel.setText(p.get_idealBloodGlucoseLevel().toString());

        totalDailyInsulinConsumption.setText(p.get_totalDailyInsulinConsumption().toString());
        dailyToRatio();
        dailyToSensitive();
        carboRatio.setText(String.format("%.2f", ratio).replace(",", "."));
        sensitive.setText(String.format("%.2f", sensitiveDouble).replace(",", "."));
        upperBloodGlucoseLevel.setText(p.get_upperBloodGlucoseLevel().toString());
        lowerBloodGlucoseLevel.setText(p.get_lowerBloodGlucoseLevel().toString());



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(save()){
                    Toast.makeText(getApplicationContext(), "Profil indstillingerne blev gemt",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        infoBloodGlucose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TooltipCompat.setTooltipText(v,"Idealt blodsukker bliver brugt til at korrigere blodsukkeret udenfor normal værdien");
                Toast.makeText(v.getContext(), "Idealt blodsukker bliver brugt til at korrigere blodsukkeret udenfor normal værdien", Toast.LENGTH_LONG).show();
            }
        });

        infoInsulinUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TooltipCompat.setTooltipText(v,"Bruges til at udregne hvor mange enheder insulin per gram kulhydrat");
                Toast.makeText(v.getContext(), "Bruges til at udregne hvor mange enheder insulin per gram kulhydrat", Toast.LENGTH_LONG).show();
            }
        });

        infoUpperLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TooltipCompat.setTooltipText(v,"Bruges til at farvekode dine blodsukker målinger over en øvre grænse");
                Toast.makeText(v.getContext(), "Bruges til at farvekode dine blodsukker målinger over en øvre grænse", Toast.LENGTH_LONG).show();
            }
        });

        infoLowerLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TooltipCompat.setTooltipText(v,"Bruges til at farvekode dine blodsukker målinger under en nedre grænse");
                Toast.makeText(v.getContext(), "Bruges til at farvekode dine blodsukker målinger under en nedre grænse", Toast.LENGTH_LONG).show();
            }
        });


        floatingActionButtonSettings = findViewById(R.id.floatingActionButton_settings);
        floatingActionButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, UCI.class);
                intent.putExtra("PageName", "SettingsPage");
                startActivity(intent);
            }
        });
        totalDailyInsulinConsumption.addTextChangedListener(dailyTextWatcher);
        carboRatio.addTextChangedListener(ratioTextWatcher);
        sensitive.addTextChangedListener(sensitivityTextWatcher);

    }

    /**
     * Fetch data from database
     * If no data exist create default object
     */
    private void fetchdata(){
        try {
            p = Profile.find(Profile.class, "ID = ?", "1").get(0);
            Toast.makeText(getApplicationContext(), "Hentet object", Toast.LENGTH_SHORT);
        } catch (Exception e) {
            p = new Profile();
            p.set_idealBloodGlucoseLevel(5.5);
            p.set_totalDailyInsulinConsumption(30.0);
            p.set_lowerBloodGlucoseLevel(2.8);
            p.set_upperBloodGlucoseLevel(13.0);
            p.set_parentalControl(0);
            p.set_bloodGlucoseMeasurement(0);
            p.set_insulinCalc(0);
            p.set_phoneNumber("");

            //Save default to DB
            p.save();
        }
    }

    /**
     * Save the Profile object
     * @return Boolean value depending on success of database operation
     */
    private Boolean save(){
        if(idealBloodGlucoseLevel.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Du mangler at angive dit mål blodsukker",Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            p.set_idealBloodGlucoseLevel(Double.parseDouble(0 + idealBloodGlucoseLevel.getText().toString()));
        }


        if(totalDailyInsulinConsumption.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Du mangler at angive dit daglige insulinforbrug",Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            p.set_totalDailyInsulinConsumption(Double.parseDouble(0 + totalDailyInsulinConsumption.getText().toString()));
        }

        if(upperBloodGlucoseLevel.getText().toString().isEmpty()) {
            Toast.makeText(this, "Du mangler at angive en øvre grænseværdi", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            p.set_upperBloodGlucoseLevel(Double.parseDouble(0 + upperBloodGlucoseLevel.getText().toString()));
        }

        if(lowerBloodGlucoseLevel.getText().toString().isEmpty()) {
            Toast.makeText(this, "Du mangler at angive en nedre grænseværdi", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            p.set_lowerBloodGlucoseLevel(Double.parseDouble(0 + lowerBloodGlucoseLevel.getText().toString()));
        }

        try {
            p.save();
            return true;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Der skete en fejl med databasen", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void dailyToRatio(){
        ratio = 500/daily;
    }
    private void dailyToSensitive(){
        sensitiveDouble = 100/daily;
    }
    private void ratioToDaily(){
        daily = 500/ratio;
    }
    private void sensitiveToDaily(){
        daily = 100/sensitiveDouble;
    }

    /**
     * A method to check if an edittext is empty
     * @param s The string
     * @return Boolean representing if the field was empty
     */
    private boolean isEmpty(String s) {
        if (s.trim().length() > 0)
            return false;
        return true;
    }

    protected TextWatcher dailyTextWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            carboRatio.addTextChangedListener(ratioTextWatcher);
            sensitive.addTextChangedListener(sensitivityTextWatcher);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // your logic here
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            carboRatio.removeTextChangedListener(ratioTextWatcher);
            sensitive.removeTextChangedListener(sensitivityTextWatcher);
            if (!isEmpty(s.toString())){
                daily = Double.parseDouble(0 + totalDailyInsulinConsumption.getText().toString().replace(",", "."));
                dailyToRatio();
                dailyToSensitive();
                carboRatio.setText(String.format("%.2f", ratio).replace(",", "."));
                sensitive.setText(String.format("%.2f", sensitiveDouble).replace(",", "."));
            }else {
                carboRatio.setText("0.0");
                sensitive.setText("0.0");
            }
        }
    };

    protected TextWatcher ratioTextWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            totalDailyInsulinConsumption.addTextChangedListener(dailyTextWatcher);
            sensitive.addTextChangedListener(sensitivityTextWatcher);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            totalDailyInsulinConsumption.removeTextChangedListener(dailyTextWatcher);
            sensitive.removeTextChangedListener(sensitivityTextWatcher);
            if (!isEmpty(s.toString())){
                ratio = Double.parseDouble(0 + carboRatio.getText().toString().replace(",", "."));
                ratioToDaily();
                dailyToSensitive();
                totalDailyInsulinConsumption.setText(String.format("%.2f", daily).replace(",", "."));
                sensitive.setText(String.format("%.2f", sensitiveDouble).replace(",", "."));
            }else {
                totalDailyInsulinConsumption.setText("0.0");
                sensitive.setText("0.0");
            }
        }
    };

    protected TextWatcher sensitivityTextWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            totalDailyInsulinConsumption.addTextChangedListener(dailyTextWatcher);
            carboRatio.addTextChangedListener(ratioTextWatcher);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            totalDailyInsulinConsumption.removeTextChangedListener(dailyTextWatcher);
            carboRatio.removeTextChangedListener(ratioTextWatcher);
            if (!isEmpty(s.toString())){
                sensitiveDouble = Double.parseDouble(0 + sensitive.getText().toString().replace(",", "."));
                sensitiveToDaily();
                dailyToRatio();
                totalDailyInsulinConsumption.setText(String.format("%.2f", daily).replace(",", "."));
                carboRatio.setText(String.format("%.2f", ratio).replace(",", "."));
            }else {
                totalDailyInsulinConsumption.setText("0.0");
                carboRatio.setText("0.0");
            }
        }
    };

}
