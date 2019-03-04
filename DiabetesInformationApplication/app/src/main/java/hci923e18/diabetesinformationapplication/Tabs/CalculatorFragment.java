package hci923e18.diabetesinformationapplication.Tabs;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import hci923e18.database.BloodGlucoseMeasurements;
import hci923e18.database.Profile;
import hci923e18.diabetesinformationapplication.R;
import hci923e18.diabetesinformationapplication.UCI.UCI;
import hci923e18.utility.Calculator;
import hci923e18.utility.KeyBoard;
import hci923e18.utility.SMSUtil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalculatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Profile p = new Profile();
    private OnFragmentInteractionListener mListener;
    DecimalFormat formater = new DecimalFormat("#.##");
    Switch switchButton;
    Button calculateButton;
    TextView insulinResult;
    TextView guideText;
    EditText carbohydrateInput;
    EditText bloodGlucoseInput;
    EditText fiberInput;
    TextView fiberText;
    TextInputLayout fiberLayout;
    private FloatingActionButton floatingActionButtonCalculatorPage;

    /**
     * Default constructor
     */
    public CalculatorFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorFragment.
     */
    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Default onCreate method
     * @param savedInstanceState The saved state of the application
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * Default on create view method
     * @param inflater The inflater for the view
     * @param container The container for the view
     * @param savedInstanceState The saved state of the application
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        getActivity().setTitle("DIAbetes - Insulinberegner");
        KeyBoard.setHideKeyboardOnTouch(view.getContext(),view.findViewById(R.id.calculatorlayout));
        fetchProfile();

        //Binding of components
        switchButton = view.findViewById(R.id.switchAdvancedOptionsCalculator);
        calculateButton = view.findViewById(R.id.buttonCalculate);
        insulinResult = view.findViewById(R.id.textViewCalculatedInsulin);
        guideText = view.findViewById(R.id.editTextShowGuideText);
        carbohydrateInput = view.findViewById(R.id.textInputCarbohydrate);
        bloodGlucoseInput = view.findViewById(R.id.textInputBloodSugar);
        fiberInput = view.findViewById(R.id.textInputFiber);
        fiberText = view.findViewById(R.id.textViewFiber);
        fiberLayout = view.findViewById(R.id.textInputLayoutFiber);
        final Calculator calculator = new Calculator();


        try {
            BloodGlucoseMeasurements b = fetchlastBloodMeasurement();
            bloodGlucoseInput.setText(b.get_glucoseLevel().toString());
        } catch (Exception e) {

        }

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fiberText.setVisibility(View.VISIBLE);
                fiberLayout.setVisibility(View.VISIBLE);
                if (!isChecked)
                {
                    fiberText.setVisibility(View.INVISIBLE);
                    fiberLayout.setVisibility(View.GONE);
                }
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guideText.setText("");

                Double bloodGlucoseLevel = p.get_idealBloodGlucoseLevel();

                if(!carbohydrateInput.getText().toString().isEmpty() && Double.parseDouble(0 + carbohydrateInput.getText().toString())> 0) {

                    if (fiberInput.getText() != null && !fiberInput.getText().toString().isEmpty()) {
                        Double fiberPercentageResult = calculator.fiberPercentage(Double.parseDouble(0 + carbohydrateInput.getText().toString()), Double.parseDouble(0 + fiberInput.getText().toString()));

                        if (fiberPercentageResult >= 25.0) {
                            guideText.setText("Fiberen udgør " + formater.format(fiberPercentageResult) + "% af kulhydraterne. \nVi anbefaler at du tager insulinen efter måltidet, da det optages langsommere i blodet.");
                        } else {
                            guideText.setText("Fiberen udgør " + formater.format(fiberPercentageResult) + "% af kulhydraterne, vi anbefaler at du tager insulin før måltidet");
                        }
                    }
                    if (bloodGlucoseInput.getText().toString().isEmpty() || Math.round(Float.parseFloat(0 + bloodGlucoseInput.getText().toString()))<= 0) {
                        Double bloodGlucoseAdjustment = calculator.bloodGlucoseGoalCalculation(bloodGlucoseLevel);
                        Double result = calculator.insulinCalculator(Double.parseDouble(0 + carbohydrateInput.getText().toString()), bloodGlucoseLevel);
                        insulinResult.setText(formater.format(result).toString());

                        guideText.append("\nDa der ikke er indtastet et blodsukker, benyttes mål værdien " + bloodGlucoseLevel + " fra din profil.");
                    } else {
                        Double bloodGlucoseAdjustment = calculator.bloodGlucoseGoalCalculation(Double.parseDouble(0 + bloodGlucoseInput.getText().toString()));
                        Double result = calculator.insulinCalculator(Double.parseDouble(0 + carbohydrateInput.getText().toString()), Double.parseDouble(0 + bloodGlucoseInput.getText().toString()));
                        insulinResult.setText(formater.format(result).toString());

                        if (Double.parseDouble(0 + bloodGlucoseInput.getText().toString()) > p.get_idealBloodGlucoseLevel()) {
                            if (guideText.getText() != null && !guideText.getText().toString().isEmpty()) {
                                guideText.append("\nVær opmærksom på at på grund af det indtastede blodsukker, er " + formater.format(bloodGlucoseAdjustment) + " enheder lagt til den beregnet insulin.");
                            } else {
                                guideText.setText("Vær opmærksom på at på grund af det indtastede blodsukker, er " + formater.format(bloodGlucoseAdjustment) + " enheder lagt til den beregnet insulin.");
                            }

                        } else if (Double.parseDouble(0 + bloodGlucoseInput.getText().toString()) == p.get_idealBloodGlucoseLevel()) {
                        } else {
                            if (guideText.getText() != null && !guideText.getText().toString().isEmpty()) {
                                guideText.append("\nVær opmærksom på at på grund af det indtastede blodsukker, er " + formater.format(bloodGlucoseAdjustment) + " enheder trukket fra den beregnet insulin.");
                            } else {
                                guideText.setText("Vær opmærksom på at på grund af det indtastede blodsukker, er " + formater.format(bloodGlucoseAdjustment) + " enheder trukket fra den beregnet insulin.");
                            }
                        }
                    }
                    fetchProfile();
                    if (p.get_parentalControl() == 1 && p.get_insulinCalc() == 1) {
                        SMSUtil.sendSMS(carbohydrateInput.getText().toString(), bloodGlucoseInput.getText().toString(), insulinResult.getText().toString());
                    }
                }
                else
                {
                    guideText.setText("Du har ikke indtastet nogen værdi i kulhydrater.");
                }
            }
        });

        floatingActionButtonCalculatorPage = view.findViewById(R.id.floatingActionButton_calculatorPage);
        floatingActionButtonCalculatorPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UCI.class);
                intent.putExtra("PageName", "CalculatorPage");
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    /**
     * Default onAttach method
     * @param context The context of the application
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
     * Default onDetach method
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
     * Fetches the last blood glucose measurement
     * @return A bloodGlucoseMeasurement object
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

    /**
     * Fetches the profile
     */
    private void fetchProfile(){
        try {
            p = Profile.find(Profile.class, "ID = ?", "1").get(0);
        } catch (Exception e) {
            p = new Profile();
            p.set_idealBloodGlucoseLevel(5.5);
            p.set_totalDailyInsulinConsumption(30.0);
            p.set_upperBloodGlucoseLevel(15.0);
            p.set_lowerBloodGlucoseLevel(3.0);

            //Save default to DB
            p.save();
        }
    }
}
