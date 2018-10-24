package hci923e18.diabetesinformationapplication;

import android.app.AlertDialog;
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
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import hci923e18.database.Food;


public class FrontPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView navigation;
    //Setup of burger menu
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

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
                    fragmentTransaction.replace(R.id.framelayout, new HomeFragment()).commit();
                    return true;
                case R.id.navigation_dashboard:
                    fragmentTransaction.replace(R.id.framelayout, new CalculatorFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
                    fragmentTransaction.replace(R.id.framelayout, new MealPlanFragment()).commit();
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
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, new HomeFragment()).commit();

        List<Food> _food = Food.listAll(Food.class);
        if (_food.size() == 0)
        {
            populateDB();
        }
        else{
            //Do nothing
        }

    }

    /**
     * Method to change navigation bar and active fragment to home
     */
    public void changeToHome(){
        navigation.setSelectedItemId(R.id.navigation_home);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, new HomeFragment()).commit();
    }

    /**
     * Method to change navigation bar and active fragment to Data
     */
    public void changeToData(){
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, new CalculatorFragment()).commit();
    }

    /**
     * Method to change navigation bar and active fragment to Information
     */
    public void changeToMealPlan(){
        navigation.setSelectedItemId(R.id.navigation_notifications);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, new MealPlanFragment()).commit();
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
            case R.id.menumyevent:
                intent = new Intent(FrontPageActivity.this, SettingsActivity.class);
                drawerLayout.closeDrawers();
                break;
            case R.id.aboutUs:

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
