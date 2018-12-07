package hci923e18.diabetesinformationapplication.Tabs;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.orm.SugarRecord;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hci923e18.database.BloodGlucoseMeasurements;
import hci923e18.database.Food;
import hci923e18.database.FrequentlyAskedQuestions;
import hci923e18.database.Profile;
import hci923e18.diabetesinformationapplication.AboutUsFragment;
import hci923e18.diabetesinformationapplication.FAQFragment;
import hci923e18.database.LongTermBloodGlucose;
import hci923e18.diabetesinformationapplication.BloodGlycoseOverview.BloodGlycoseOverviewActivity;
import hci923e18.diabetesinformationapplication.MealLog.MealLogFragment;
import hci923e18.diabetesinformationapplication.MealPlan.MealPlanFragment;
import hci923e18.diabetesinformationapplication.Notes.NewNoteFragment;
import hci923e18.diabetesinformationapplication.Notes.NoteListFragment;
import hci923e18.diabetesinformationapplication.ParentalControlActivity;
import hci923e18.diabetesinformationapplication.R;
import hci923e18.diabetesinformationapplication.SettingsActivity;
import hci923e18.diabetesinformationapplication.newBloodGlucoseLevelActivity;
import hci923e18.utility.PopulateDatabase;


public class FrontPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView navigation;
    //Setup of burger menu
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FrameLayout frameLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        /**
         * Selector for navigation bar
         * @param item
         * @return The selected fragment
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                        fragmentManager.popBackStack();
                    }
                    fragmentTransaction.replace(R.id.framelayoutFrontPage, new HomeFragment()).commit();
                    return true;
                case R.id.navigation_dashboard:
                    for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                        fragmentManager.popBackStack();
                    }
                    fragmentTransaction.replace(R.id.framelayoutFrontPage, new CalculatorFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
                    for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                        fragmentManager.popBackStack();
                    }
                    fragmentTransaction.replace(R.id.framelayoutFrontPage, new MealPlanFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    /**
     * OnCreate method for the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        frameLayout = findViewById(R.id.framelayoutFrontPage);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //--------------------------Burger menu-------------------------------------
        drawerLayout = findViewById(R.id.homeactivity);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nv = findViewById(R.id.burgermenu);
        nv.setNavigationItemSelectedListener(this);
        //--------------------------Burger menu-------------------------------------

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayoutFrontPage, new HomeFragment()).commit();

        List<Profile> _profile = Profile.listAll(Profile.class);
        if (_profile.size() == 0)
        {
            Dialog dialog = null;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Dette er første gang appen startes. Vil du gerne lave dine indstillinger")
                    .setCancelable(false)
                    .setPositiveButton("Nej", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            }
                    })
                    .setNegativeButton("Ja", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(FrontPageActivity.this, SettingsActivity.class);
                            startActivity(intent);
                            }
                    });
            AlertDialog alert = builder.create();
            dialog = alert;
            dialog.show();
        }
        else{
            //Do nothing
        }
    }

    /**
     * Method to disable back button on fragments
     * By not calling super, we disable back button
     */
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
        }
    }

    /**
     * Method to change navigation bar and active fragment to Home
     */
    public void changeToHome(){
        frameLayout.removeAllViews();
        navigation.setSelectedItemId(R.id.navigation_home);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayoutFrontPage, new HomeFragment()).commit();
    }

    /**
     * Method to change navigation bar and active fragment to Data
     */
    public void changeToData(){
        frameLayout.removeAllViews();
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayoutFrontPage, new CalculatorFragment()).commit();
    }

    /**
     * Method to change navigation bar and active fragment to MealPlan
     */
    public void changeToMealPlan(){
        frameLayout.removeAllViews();
        navigation.setSelectedItemId(R.id.navigation_notifications);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayoutFrontPage, new MealPlanFragment()).commit();
    }

    /**
     * Method to change navigation bar and active fragment to MealLog
     */
    public void changeToMealLog(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayoutFrontPage, new MealLogFragment()).addToBackStack("Meallog").commit();
    }

    /**
     * Method to change navigation bar and active fragment to NewNote
     */
    public void changeToNewNote(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayoutFrontPage, new NewNoteFragment()).addToBackStack("noter").commit();
    }

    /**
     * Method to change navigation bar and active fragment to NoteList
     */
    public void changeToNoteList(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayoutFrontPage, new NoteListFragment()).addToBackStack("noteList").commit();
    }


    /**
     * Method to change navigation bar and active fragment to FAQ
     */
    public void changeToFAQ(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayoutFrontPage, new FAQFragment()).addToBackStack("FAQ").commit();
    }

    public void changeToAboutUs(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayoutFrontPage, new AboutUsFragment()).addToBackStack("AboutUs").commit();
    }

    //--------------------------Burger menu-------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;
        switch (id){
            case R.id.profile:
                intent = new Intent(FrontPageActivity.this, SettingsActivity.class);
                drawerLayout.closeDrawers();
                break;
            case R.id.mealLog:
                changeToMealLog();
                drawerLayout.closeDrawers();
                break;
            case R.id.navigation_notes:
                changeToNoteList();
                drawerLayout.closeDrawers();
                break;

            case R.id.blood_glycose_overview:
                intent = new Intent(FrontPageActivity.this, BloodGlycoseOverviewActivity.class);
                drawerLayout.closeDrawers();
                break;
            case R.id.navigation_FAQ:
                changeToFAQ();
                drawerLayout.closeDrawers();
                break;
            case R.id.newbloodglucoselevel:
                intent = new Intent(FrontPageActivity.this, newBloodGlucoseLevelActivity.class);
                drawerLayout.closeDrawers();
                break;
            case R.id.navigation_AboutUS:
                changeToAboutUs();
                drawerLayout.closeDrawers();
                break;
            case R.id.navigation_Parent_control:
                intent = new Intent(FrontPageActivity.this, ParentalControlActivity.class);
                drawerLayout.closeDrawers();
                break;
        }
        if (intent == null){
            return false;
        }
        startActivity(intent);
        return false;
    }
    //--------------------------Burger menu-------------------------------------
}
