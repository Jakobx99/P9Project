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
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.orm.SugarRecord;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hci923e18.database.BloodGlucoseMeasurements;
import hci923e18.database.Food;
import hci923e18.database.LongTermBloodGlucose;
import hci923e18.diabetesinformationapplication.BloodGlycoseOverview.BloodGlycoseOverviewActivity;
import hci923e18.diabetesinformationapplication.MealLog.MealLogFragment;
import hci923e18.diabetesinformationapplication.MealPlan.MealPlanFragment;
import hci923e18.diabetesinformationapplication.Notes.NewNoteFragment;
import hci923e18.diabetesinformationapplication.Notes.NoteListFragment;
import hci923e18.diabetesinformationapplication.R;
import hci923e18.diabetesinformationapplication.SettingsActivity;
import hci923e18.diabetesinformationapplication.newBloodGlucoseLevelActivity;


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

        List<Food> _food = Food.listAll(Food.class);
        if (_food.size() == 0)
        {
            populateDB();
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
     * Method to populate Database with default Food items first time the app is launched
     */
    private void populateDB(){

        //http://maddata.dk/
        // Food(String name, Double carbohydrate, Double protein, Double fiber, Double sugar)
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Cola", 10.6, 0.1, 0.0, 10.6));
        foods.add(new Food("Cola light", 0.0, 0.0,0.0,0.0));
        foods.add(new Food("Arla Letmælk", 4.7, 3.5, 0.0,4.7));
        foods.add(new Food("Ris", 28.0, 2.7,0.4, 0.1));
        foods.add(new Food("Kartofler", 17.0, 2.0, 2.2, 0.8));
        foods.add(new Food("Pasta", 75.0, 13.0, 3.2, 2.7));
        foods.add(new Food("Kyllingebryst", 0.0, 21.5, 0.0, 0.0));
        foods.add(new Food("Hakket oksekød", 0.0, 19.3, 0.0, 0.0));
        foods.add(new Food("Hakket Svinekød", 0.0, 21.2, 0.0, 0.0));
        foods.add(new Food("Mørkt rugbrød", 47.4, 5.7, 8.6, 0.0));

        //Add to database
        SugarRecord.saveInTx(foods);

        //Calendar date, Double glucoseLevel, Integer type, Integer category, Integer beforeAfter
        Calendar c = Calendar.getInstance();

        List<BloodGlucoseMeasurements> bloodGlucoseMeasurements = new ArrayList<>();
        c.set(2018, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE) -2);
        c.set(Calendar.HOUR, 8);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 1.0, 1,1,1));
        c.set(Calendar.HOUR, 9);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 4.0, 1,1,1));
        c.set(Calendar.HOUR, 10);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 14.0, 1,1,1));
        c.set(Calendar.HOUR, 11);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 15.0, 1,1,1));
        c = Calendar.getInstance();
        c.set(2018, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE) -1);
        c.set(Calendar.HOUR, 8);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 1.0, 1,1,1));
        c.set(Calendar.HOUR, 9);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 4.0, 1,1,1));
        c.set(Calendar.HOUR, 10);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 14.0, 1,1,1));
        c.set(Calendar.HOUR, 11);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 15.0, 1,1,1));
        c = Calendar.getInstance();
        c.set(Calendar.HOUR, 8);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 8.0, 1,1,1));
        c.set(Calendar.HOUR, 9);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 2.0, 1,1,1));
        c.set(Calendar.HOUR, 10);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 6.0, 1,1,1));

        SugarRecord.saveInTx(bloodGlucoseMeasurements);

        //Calendar start, Calendar end, Double value
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(Calendar.MONTH, 8);
        c2.set(Calendar.MONTH, 11);

        LongTermBloodGlucose longTermBloodGlucose = new LongTermBloodGlucose(c1, c2, 6.2);
        longTermBloodGlucose.save();


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

            case R.id.newbloodglucoselevel:
                intent = new Intent(FrontPageActivity.this, newBloodGlucoseLevelActivity.class);
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
