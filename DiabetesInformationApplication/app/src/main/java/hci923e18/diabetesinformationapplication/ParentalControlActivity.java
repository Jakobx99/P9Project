package hci923e18.diabetesinformationapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hci923e18.database.Profile;

public class ParentalControlActivity extends AppCompatActivity {

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
    }
}
