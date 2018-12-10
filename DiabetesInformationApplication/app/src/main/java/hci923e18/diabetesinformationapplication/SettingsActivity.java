package hci923e18.diabetesinformationapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.TooltipCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import hci923e18.database.Profile;
import hci923e18.utility.KeyBoard;

public class SettingsActivity extends AppCompatActivity {

    public Button saveButton;
    public EditText idealBloodGlucoseLevel;
    public EditText insulinDuration;
    public EditText totalDailyInsulinConsumption;
    public EditText upperBloodGlucoseLevel;
    public EditText lowerBloodGlucoseLevel;
    public EditText beforeBloodGlucoseLevel;
    public EditText afterBloodGlucoseLevel;
    public TextView titleUpper;
    public TextView titleLower;
    public TextView titleBefore;
    public TextView titleAfter;
    public ImageButton infoBloodGlucose;
    public ImageButton infoDuration;
    public ImageButton infoInsulinUsage;
    public ImageButton infoUpperLimit;
    public ImageButton infoLowerLimit;
    public ImageButton infoBefore;
    public ImageButton infoAfter;
    Switch switchButton;
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

        switchButton = findViewById(R.id.switch_settingLimits);
        saveButton = findViewById(R.id.button_save);
        idealBloodGlucoseLevel = findViewById(R.id.textView_idealBloodGlucoseLevel);
        insulinDuration = findViewById(R.id.textView_insulinDuration);
        totalDailyInsulinConsumption = findViewById(R.id.textView_totalDailyInsulinConsumption);
        infoBloodGlucose = findViewById(R.id.imageButtonBloodGlucose);
        infoDuration = findViewById(R.id.imageButtonInsulinDuratation);
        infoInsulinUsage = findViewById(R.id.imageButtonInsulinUsage);
        upperBloodGlucoseLevel = findViewById(R.id.textView_UpperBloodGlucoseLevel);
        titleUpper = findViewById(R.id.textView_titleUpperBloodGlucoseLevel);
        infoUpperLimit = findViewById(R.id.imageButton_UpperBloodGlucoseLevel);
        lowerBloodGlucoseLevel = findViewById(R.id.textView_LowerBloodGlucoseLevel);
        titleLower = findViewById(R.id.textView_titleLowerBloodGlucoseLevel);
        infoLowerLimit = findViewById(R.id.imageButton_LowerBloodGlucoseLevel);
        beforeBloodGlucoseLevel = findViewById(R.id.textView_BeforeBloodGlucoseLevel);
        titleBefore = findViewById(R.id.textView_titleBeforeBloodGlucoseLevel);
        infoBefore = findViewById(R.id.imageButton_BeforeBloodGlucoseLevel);
        afterBloodGlucoseLevel = findViewById(R.id.textView_AfterBloodGlucoseLevel);
        titleAfter = findViewById(R.id.textView_titleAfterBloodGlucoseLevel);
        infoAfter = findViewById(R.id.imageButton_AfterBloodGlucoseLevel);

        fetchdata();
        idealBloodGlucoseLevel.setText(p.get_idealBloodGlucoseLevel().toString());
        insulinDuration.setText(p.get_insulinDuration().toString());
        totalDailyInsulinConsumption.setText(p.get_totalDailyInsulinConsumption().toString());
        upperBloodGlucoseLevel.setText(p.get_upperBloodGlucoseLevel().toString());
        lowerBloodGlucoseLevel.setText(p.get_lowerBloodGlucoseLevel().toString());
        beforeBloodGlucoseLevel.setText(p.get_beforeBloodGlucoseLevel().toString());
        afterBloodGlucoseLevel.setText(p.get_afterBloodGlucoseLevel().toString());

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                upperBloodGlucoseLevel.setVisibility(View.VISIBLE);
                titleUpper.setVisibility(View.VISIBLE);
                infoUpperLimit.setVisibility(View.VISIBLE);
                lowerBloodGlucoseLevel.setVisibility(View.VISIBLE);
                titleLower.setVisibility(View.VISIBLE);
                infoLowerLimit.setVisibility(View.VISIBLE);
                beforeBloodGlucoseLevel.setVisibility(View.VISIBLE);
                titleBefore.setVisibility(View.VISIBLE);
                infoBefore.setVisibility(View.VISIBLE);
                afterBloodGlucoseLevel.setVisibility(View.VISIBLE);
                titleAfter.setVisibility(View.VISIBLE);
                infoAfter.setVisibility(View.VISIBLE);
                if (!isChecked)
                {
                    upperBloodGlucoseLevel.setVisibility(View.GONE);
                    titleUpper.setVisibility(View.GONE);
                    infoUpperLimit.setVisibility(View.GONE);
                    lowerBloodGlucoseLevel.setVisibility(View.GONE);
                    titleLower.setVisibility(View.GONE);
                    infoLowerLimit.setVisibility(View.GONE);
                    beforeBloodGlucoseLevel.setVisibility(View.GONE);
                    titleBefore.setVisibility(View.GONE);
                    infoBefore.setVisibility(View.GONE);
                    afterBloodGlucoseLevel.setVisibility(View.GONE);
                    titleAfter.setVisibility(View.GONE);
                    infoAfter.setVisibility(View.GONE);
                }
            }
        });

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

        infoDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TooltipCompat.setTooltipText(v,"Tiltænkt til fremtidige features");
                Toast.makeText(v.getContext(), "Tiltænkt til fremtidige features", Toast.LENGTH_LONG).show();
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

        infoBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TooltipCompat.setTooltipText(v,"Bruges til at farvekode dine blodsukker målinger ved målinger taget før et måltid");
                Toast.makeText(v.getContext(), "Bruges til at farvekode dine blodsukker målinger ved målinger taget før et måltid", Toast.LENGTH_LONG).show();
            }
        });

        infoAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TooltipCompat.setTooltipText(v,"Bruges til at farvekode dine blodsukker målinger ved målinger taget efter et måltid");
                Toast.makeText(v.getContext(), "Bruges til at farvekode dine blodsukker målinger ved målinger taget efter et måltid", Toast.LENGTH_LONG).show();
            }
        });
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
            p.set_insulinDuration(3.5);
            p.set_totalDailyInsulinConsumption(30.0);
            p.set_afterBloodGlucoseLevel(9.0);
            p.set_beforeBloodGlucoseLevel(6.0);
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
            p.set_idealBloodGlucoseLevel(Double.parseDouble(idealBloodGlucoseLevel.getText().toString()));
        }

        if(insulinDuration.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Du mangler at angive hvor længe insulinen er aktiv, efter du har taget det",Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            p.set_insulinDuration(Double.parseDouble(insulinDuration.getText().toString()));
        }

        if(totalDailyInsulinConsumption.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Du mangler at angive dit daglige insulinforbrug",Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            p.set_totalDailyInsulinConsumption(Double.parseDouble(totalDailyInsulinConsumption.getText().toString()));
        }

        if(upperBloodGlucoseLevel.getText().toString().isEmpty()) {
            Toast.makeText(this, "Du mangler at angive en øvre grænseværdi", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            p.set_upperBloodGlucoseLevel(Double.parseDouble(upperBloodGlucoseLevel.getText().toString()));
        }

        if(lowerBloodGlucoseLevel.getText().toString().isEmpty()) {
            Toast.makeText(this, "Du mangler at angive en nedre grænseværdi", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            p.set_lowerBloodGlucoseLevel(Double.parseDouble(lowerBloodGlucoseLevel.getText().toString()));
        }

        if(beforeBloodGlucoseLevel.getText().toString().isEmpty()) {
            Toast.makeText(this, "Du mangler at angive en grænseværdi for dit blod sukker før måltider", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            p.set_beforeBloodGlucoseLevel(Double.parseDouble(beforeBloodGlucoseLevel.getText().toString()));
        }
        if(afterBloodGlucoseLevel.getText().toString().isEmpty()) {
            Toast.makeText(this, "Du mangler at angive en grænseværdi for dit blod sukker efter måltider", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            p.set_afterBloodGlucoseLevel(Double.parseDouble(afterBloodGlucoseLevel.getText().toString()));
        }

        try {
            p.save();
            return true;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Der skete en fejl med databasen", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
