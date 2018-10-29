package hci923e18.diabetesinformationapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hci923e18.database.Profile;

public class SettingsActivity extends AppCompatActivity {

    public Button saveButton;
    public EditText weight;
    public EditText idealBloodGlucoseLevel;
    public EditText insulinDuration;
    public EditText totalDailyInsulinConsumption;
    Profile p;

    /**
     * OnCreate method called when activity is initiated.
     * Bindings are performed and onclick listner for button is made
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        saveButton = findViewById(R.id.button_save);
        weight = findViewById(R.id.textView_weight);
        idealBloodGlucoseLevel = findViewById(R.id.textView_idealBloodGlucoseLevel);
        insulinDuration = findViewById(R.id.textView_insulinDuration);
        totalDailyInsulinConsumption = findViewById(R.id.textView_totalDailyInsulinConsumption);
        fetchdata();
        weight.setText(p.get_weight().toString());
        idealBloodGlucoseLevel.setText(p.get_idealBloodGlucoseLevel().toString());
        insulinDuration.setText(p.get_insulinDuration().toString());
        totalDailyInsulinConsumption.setText(p.get_totalDailyInsulinConsumption().toString());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(save()){
                    Toast.makeText(getApplicationContext(), "Profil indstillingerne blev gemt",Toast.LENGTH_LONG).show();
                    finish();
                }
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
            p.set_weight(80.0);
            p.set_idealBloodGlucoseLevel(5.5);
            p.set_insulinDuration(3.5);
            p.set_totalDailyInsulinConsumption(30.0);

            //Save default to DB
            p.save();
        }
    }

    /**
     * Save the Profile object
     * @return Boolean value depending on success of database operation
     */
    private Boolean save(){
        if(weight.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Du mangler at angive vægt",Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            p.set_weight(Double.parseDouble(weight.getText().toString()));
        }

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


        try {
            p.save();
            return true;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Der skete en fejl med databasen", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
