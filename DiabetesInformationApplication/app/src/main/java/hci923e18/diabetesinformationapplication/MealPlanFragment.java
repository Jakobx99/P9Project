package hci923e18.diabetesinformationapplication;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import hci923e18.database.Food;
import hci923e18.database.MealObject;
import hci923e18.utility.Calculator;


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
    AlertDialog alertDialog;
    Calculator calculator = new Calculator();
    String mealType;

    /**
     * Default constructor
     */
    public MealPlanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment MealPlanFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        View view = inflater.inflate(R.layout.fragment_mealplan, container, false);

        mealPlanBloodSugar = view.findViewById(R.id.editText_mealplan_bloodsugar);
        mealPlanButton = view.findViewById(R.id.button_mealplan);
        mealPlanResult = view.findViewById(R.id.textView_mealplan_result);
        mealPlanSpinner = view.findViewById(R.id.spinner_mealplan);
        mealPlanLayout = view.findViewById(R.id.mealplan_listview);
        mealPlanAddFood = view.findViewById(R.id.textView_addfood);

        createDatabaseFoodList();

        String [] values =
                {"Morgenmad","Middagsmad","Aftensmad"};
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
                calculate();
            }
        });

        mAdapter = new MealPlanAdapter(view.getContext(),0, foods, MealPlanFragment.this);
        mealPlanLayout.setAdapter(mAdapter);


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
        // TODO: Update argument type and name
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
     * @param f
     */
    public void addItemToList(Pair<Food, Double> f){

        Pair<Food,Double> p = new Pair<>(calculator.calculateNutritinalValuesDependingOnWeight(f), f.second);
        foods.add(p);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Creates the PopUp menu with a list of all Food items
     * @param context The context of the activity
     */
    public void createPopup(Context context){
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.popupwindow, null);


        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(view);


        ListView listViewPopUp = view.findViewById(R.id.listViewPopUp);
        final PopUpAdapter localAdapter;
        localAdapter = new PopUpAdapter(view.getContext(),0, databaseFoods, MealPlanFragment.this);
        listViewPopUp.setAdapter(localAdapter);

        final EditText editTextSearch = view.findViewById(R.id.edittextSearch);
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
        databaseFoods = Food.listAll(Food.class);
    }

    /**
     * Method called when a users presses the calculate button. The insulin is calculated and the meal plan is saved in the database
     */
    private void calculate(){

        Double bloodsugar =Double.parseDouble(mealPlanBloodSugar.getText().toString());
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
        mealPlanResult.setText(result.toString());
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

        try {
            MealObject m = MealObject.find(MealObject.class,"_mealtype = ? and _timestamp = ?", meal.get_mealtype(), meal.get_timestamp()).get(0);
            Long id = m.getId();
            m = meal;
            m.setId(id);
            m.save();
        } catch (Exception e) {
            meal.save();
        }


    }
}


//Navn:                     Kulhydrater,  Protein,   Sukker,  Fiber