package hci923e18.diabetesinformationapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import hci923e18.database.Profile;
import hci923e18.utility.KeyBoard;

public class ParentalControlActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_SEND_SMS = 1;
    public Boolean smsPermission = false;
    public Button saveButton;
    public TextInputLayout mobileNumberLayout;
    public EditText mobileNumber;
    public TextView whenSMSTitle;
    public Boolean ParentalControl;
    public Boolean BloodGlucoseMeasurement;
    public Boolean Calc;
    Switch switchButtonParentalControl;
    Switch switchButtonBloodGlucose;
    Switch switchButtonCalc;
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
        mobileNumberLayout = findViewById(R.id.TextInputLayout_parental_control);
        mobileNumber = findViewById(R.id.textInput_mobile_parental_control);
        whenSMSTitle = findViewById(R.id.textView_parental_control_sendtitle);
        switchButtonParentalControl = findViewById(R.id.switch_parental_control_onoff);
        switchButtonBloodGlucose = findViewById(R.id.switch_parental_control_mesurment);
        switchButtonCalc = findViewById(R.id.switch_parental_control_calc);
        mobileNumber.setSelected(false);

        fetchProfile();
        getSMSPermission();

        mobileNumber.setText(profile.get_phoneNumber());
        switchButtonParentalControl.setChecked(ParentalControl);
        switchButtonBloodGlucose.setChecked(BloodGlucoseMeasurement);
        switchButtonCalc.setChecked(Calc);

        if (ParentalControl == true){
            mobileNumberLayout.setVisibility(View.VISIBLE);
            mobileNumber.setVisibility(View.VISIBLE);
            whenSMSTitle.setVisibility(View.VISIBLE);
            switchButtonBloodGlucose.setVisibility(View.VISIBLE);
            switchButtonCalc.setVisibility(View.VISIBLE);
        }

        switchButtonParentalControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mobileNumberLayout.setVisibility(View.VISIBLE);
                mobileNumber.setVisibility(View.VISIBLE);
                whenSMSTitle.setVisibility(View.VISIBLE);
                switchButtonBloodGlucose.setVisibility(View.VISIBLE);
                switchButtonCalc.setVisibility(View.VISIBLE);
                if (!isChecked)
                {
                    mobileNumberLayout.setVisibility(View.GONE);
                    mobileNumber.setVisibility(View.GONE);
                    whenSMSTitle.setVisibility(View.GONE);
                    switchButtonBloodGlucose.setVisibility(View.GONE);
                    switchButtonCalc.setVisibility(View.GONE);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(updateProfile()){
                    Toast.makeText(getApplicationContext(), "Forældrekontrol indstillingerne blev gemt",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    public void fetchProfile(){
        try {
            profile = Profile.find(Profile.class, "ID = ?", "1").get(0);
            if (profile.get_parentalControl() == 1){
                ParentalControl = true;
            }
            else{ParentalControl = false;}

            if (profile.get_bloodGlucoseMeasurement() == 1){
                BloodGlucoseMeasurement = true;
            }
            else{BloodGlucoseMeasurement = false;}

            if (profile.get_insulinCalc() == 1){
                Calc = true;
            }
            else{Calc = false;}
        }catch (Exception e) {
        }
    }

    public Boolean updateProfile(){
        int parentalControlSwitch = switchButtonParentalControl.isChecked() ? 1 : 0;
        int bloodGlucoseSwitch = switchButtonBloodGlucose.isChecked() ? 1 : 0;
        int insulinCalcSwitch = switchButtonCalc.isChecked() ? 1 : 0;

        profile.set_parentalControl(parentalControlSwitch);
        profile.set_bloodGlucoseMeasurement(bloodGlucoseSwitch);
        profile.set_insulinCalc(insulinCalcSwitch);
        profile.set_phoneNumber(mobileNumber.getText().toString());

        try {
            profile.save();
            return true;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Der skete en fejl med databasen", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void getSMSPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            smsPermission = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSIONS_REQUEST_SEND_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        smsPermission = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_SEND_SMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    smsPermission = true;
                } else {
                    Intent resultIntent = new Intent();
                    setResult(Activity.RESULT_CANCELED, resultIntent);
                    finish();
                }
            }
        }
    }

}
