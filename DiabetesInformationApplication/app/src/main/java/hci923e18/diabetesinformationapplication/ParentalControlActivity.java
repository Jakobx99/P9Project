package hci923e18.diabetesinformationapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import hci923e18.database.Profile;
import hci923e18.utility.KeyBoard;

public class ParentalControlActivity extends AppCompatActivity {

    public Button saveButton;
    public EditText mobileNumber;
    public TextView whenSMSTitle;
    Switch switchButtonOnOff;
    Switch switchButtonBloodGlucose;
    Switch switchButtonOnCalc;
    Profile profile;
    /**
     * OnCreate method called when activity is initiated.
     * Bindings are performed and setOnClickListener for button is made
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parental_control);
        KeyBoard.setHideKeyboardOnTouch(this, findViewById(R.id.constraintLayout_parent_control));

        saveButton = findViewById(R.id.button_save_parental_control);
        mobileNumber = findViewById(R.id.textInput_mobile_parental_control);
        whenSMSTitle = findViewById(R.id.textView_parental_control_sendtitle);
        switchButtonOnOff = findViewById(R.id.switch_parental_control_onoff);
        switchButtonBloodGlucose = findViewById(R.id.switch_parental_control_mesurment);
        switchButtonOnCalc = findViewById(R.id.switch_parental_control_calc);

        switchButtonOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveButton.setVisibility(View.VISIBLE);
                mobileNumber.setVisibility(View.VISIBLE);
                whenSMSTitle.setVisibility(View.VISIBLE);
                switchButtonBloodGlucose.setVisibility(View.VISIBLE);
                switchButtonOnCalc.setVisibility(View.VISIBLE);
                if (!isChecked)
                {
                    saveButton.setVisibility(View.GONE);
                    mobileNumber.setVisibility(View.GONE);
                    whenSMSTitle.setVisibility(View.GONE);
                    switchButtonBloodGlucose.setVisibility(View.GONE);
                    switchButtonOnCalc.setVisibility(View.GONE);
                }
            }
        });

    }
}
