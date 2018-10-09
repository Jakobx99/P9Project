package hci923e18.diabetesinformationapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import hci923e18.database.Person;

public class FrontPageActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);


                    //Eksemple på at gemme et person object
                    Person person = new Person("Jhon", "Johnson", 30);
                    person.save();
                    Toast.makeText(getApplicationContext(), "SAVING", Toast.LENGTH_LONG).show();
                    /////


                    return true;
                case R.id.navigation_notifications:

                    //Eksemple på at hente et person object
                    Person local;
                    local = Person.find(Person.class, "age = ?", "30").get(0);
                    //////


                    mTextMessage.setText(local.firstname + " " + local.lastname + " " + local.age);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
