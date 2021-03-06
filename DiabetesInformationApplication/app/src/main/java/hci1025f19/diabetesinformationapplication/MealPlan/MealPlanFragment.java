package hci1025f19.diabetesinformationapplication.MealPlan;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import hci1025f19.database.BloodGlucoseMeasurements;
import hci1025f19.database.Food;
import hci1025f19.database.MealObject;
import hci1025f19.database.Profile;
import hci1025f19.diabetesinformationapplication.R;
import hci1025f19.diabetesinformationapplication.UCI.UCI;
import hci1025f19.utility.Calculator;
import hci1025f19.utility.KeyBoard;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MealPlanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MealPlanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealPlanFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    Spinner mealPlanSpinner;
    Button mealPlanButton;
    EditText mealPlanBloodSugar;
    TextView mealPlanResult;
    ListView mealPlanLayout;
    List<Pair<Food, Double>> foods = new ArrayList<>();
    MealPlanAdapter mAdapter;
    TextView mealPlanAddFood;
    List<Food> databaseFoods = new ArrayList<>();
    List<Food> backupFood = new ArrayList<>();
    AlertDialog alertDialog;
    Calculator calculator = new Calculator();
    String mealType;
    DecimalFormat formater = new DecimalFormat("#.##");
    Profile user = new Profile();
    private FloatingActionButton floatingActionButtonMealPlan;

    /**
     * Default constructor
     */
    public MealPlanFragment() {
        // Required empty public constructor
        try {
            user = Profile.listAll(Profile.class).get(0);
        } catch (Exception e) {
            user.set_idealBloodGlucoseLevel(6.0);
            user.set_totalDailyInsulinConsumption(41.6);
            user.set_lowerBloodGlucoseLevel(2.8);
            user.set_upperBloodGlucoseLevel(13.0);
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment MealPlanFragment.
     */
    public static MealPlanFragment newInstance(String param1, String param2) {
        MealPlanFragment fragment = new MealPlanFragment();
        return fragment;
    }

    /**
     * On create method called when the fragment is constructed
     * @param savedInstanceState The saved instance can be used if the application is reopened
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Method to create the view that is rendered. Binds the different controls and sets up the onclick events
     * @param inflater Layout inflater
     * @param container Layout container
     * @param savedInstanceState The saved instance state used if the application is reopened
     * @return The View object
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_mealplan, container, false);
        getActivity().setTitle("DIAbetes - Måltidsplanlægger");
        KeyBoard.setHideKeyboardOnTouch(view.getContext(), view.findViewById(R.id.mealplankeyboardlayout));

        mealPlanBloodSugar = view.findViewById(R.id.editText_mealplan_bloodsugar);
        mealPlanButton = view.findViewById(R.id.button_mealplan);
        mealPlanResult = view.findViewById(R.id.textView_mealplan_result);
        mealPlanSpinner = view.findViewById(R.id.spinner_mealplan);
        mealPlanLayout = view.findViewById(R.id.mealplan_listview);
        mealPlanAddFood = view.findViewById(R.id.textView_addfood);



        String [] values =
                {"Morgenmad","Middagsmad","Aftensmad", "Mellemmåltid"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mealPlanSpinner.setAdapter(adapter);

        //Spinner
        mealPlanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mealType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Add new food object
        mealPlanAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopup(getActivity());
            }
        });

        //Calculate button
        mealPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate(view);
            }

        });

        mAdapter = new MealPlanAdapter(view.getContext(),0, foods, MealPlanFragment.this);
        mealPlanLayout.setAdapter(mAdapter);
        mealPlanLayout.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        try {
            BloodGlucoseMeasurements b = fetchlastBloodMeasurement();
            mealPlanBloodSugar.setText(b.get_glucoseLevel().toString());
        } catch (Exception e) {

        }

        floatingActionButtonMealPlan = view.findViewById(R.id.floatingActionButton_MealPlan);
        floatingActionButtonMealPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UCI.class);
                intent.putExtra("PageName", "MealPlan");
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    /**
     *Method called when the fragment is attached to the fragment manager
     * @param context The context of the activity that the fragment is attached to
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    /**
     *Detach method
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    /**
     *Removes a Food object from the list of food objects
     * @param pos
     */
    public void removeItemFromList(int pos){
        foods.remove(pos);
        mAdapter.notifyDataSetChanged();
    }

    /**
     *Adds a Food object from the list of food objects
     * @param f A Pair consisting of a Food object and a double describing the weight of the food object
     */
    public void addItemToList(Pair<Food, Double> f){
        Pair<Food,Double> p = new Pair<>(calculator.calculateNutritinalValuesDependingOnWeight(f), f.second);
        foods.add(p);
        mAdapter.notifyDataSetChanged();
        try {
            createDatabaseFoodList();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Der skete en fejl med databasen prøv at genstarte appen", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Creates the PopUp menu with a list of all Food items
     * @param context The context of the activity
     */
    public void createPopup(Context context){
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.popupwindow, null);
        createDatabaseFoodList();

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(view);

        ListView listViewPopUp = view.findViewById(R.id.listViewPopUp);
        final PopUpAdapter localAdapter;
        localAdapter = new PopUpAdapter(view.getContext(),0, databaseFoods, MealPlanFragment.this);
        listViewPopUp.setAdapter(localAdapter);

        Button newFoodButton = view.findViewById(R.id.button_newfooditem);
        newFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddNewFoodActivity.class);
                getActivity().startActivity(intent);
                alertDialog.cancel();
            }
        });

        final EditText editTextSearch = view.findViewById(R.id.edittext_PopUpSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                localAdapter.getFilter().filter(s);

                String text = editTextSearch.getText().toString().toLowerCase(Locale.getDefault());
                localAdapter.filter(text);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);//
    }

    /**
     * Creates a list of all Food items in the database
     */
    private void createDatabaseFoodList(){
        List<Food> temp = Food.listAll(Food.class);
        Collections.sort(temp, new Comparator<Food>() {
            @Override
            public int compare(Food food, Food t1) {
                return food.get_name().compareTo(t1.get_name());
            }
        });
        databaseFoods = new ArrayList<>();
        databaseFoods = temp;
        backupFood = new ArrayList<>();
        backupFood = databaseFoods;
    }

    /**
     * Method called when a users presses the calculate button. The insulin is calculated and the meal plan is saved in the database
     */
    private void calculate(View context){

        Double bloodsugar;

        if (mealPlanBloodSugar.getText().toString().isEmpty() || Double.parseDouble(0 + mealPlanBloodSugar.getText().toString())<=0)
        {
            bloodsugar = user.get_idealBloodGlucoseLevel();
            Toast.makeText(context.getContext(),"Da du ikke har skrevet et blodsukker ind, bruges din standard værdi: " + bloodsugar, Toast.LENGTH_LONG).show();
        }
        else
        {
            bloodsugar = Double.parseDouble(0 + mealPlanBloodSugar.getText().toString());
        }

        Double carbs = 0.0;
        Double fiber = 0.0;
        Double weight = 0.0;

        for (Pair<Food, Double> f:foods) {
            carbs = carbs + f.first.get_carbohydrate();
            fiber = fiber + f.first.get_fiber();
            weight = weight + f.second;
        }
        //Calculate result
        Double result = calculator.insulinCalculator(carbs, bloodsugar);
        //Display result
        mealPlanResult.setText(formater.format(result));
        //Save in db or override

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        MealObject meal = new MealObject();
        meal.set_bloodGlucoseLevel(bloodsugar);
        meal.set_insulinResult(result);
        meal.set_meals(foods);
        meal.set_mealtype(mealType);
        meal.set_timestamp(date);
        meal.set_totalCarbs(carbs);
        meal.set_totalFiber(fiber);
        meal.set_totalWeight(weight);

        if(foods.isEmpty())
        {
        }
        else {
            try {
                if (mealType == "Mellemmåltid"){
                    meal.save();
                    Toast.makeText(getActivity(), "Måltid beregnet og gem", Toast.LENGTH_LONG).show();
                }else {
                    MealObject m = MealObject.find(MealObject.class, "_mealtype = ? and _timestamp = ?", meal.get_mealtype(), meal.get_timestamp()).get(0);
                    Long id = m.getId();
                    m = meal;
                    m.setId(id);
                    m.save();
                    Toast.makeText(getActivity(), "Måltid beregnet og gem", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                meal.save();
            }
        }
    }

    /**
     * Fetch the latest blood glycose measurement
     * @return A bloodGlucoseMeasurement object representing the latest measurement
     */
    private BloodGlucoseMeasurements fetchlastBloodMeasurement(){
        BloodGlucoseMeasurements bloodGlucoseMeasurements = null;
        try {
            List<BloodGlucoseMeasurements> b = new ArrayList<>();
            b.addAll(BloodGlucoseMeasurements.listAll(BloodGlucoseMeasurements.class));
            int last = b.size() - 1;

            bloodGlucoseMeasurements = b.get(last);
            b.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bloodGlucoseMeasurements;
    }


}