package hci923e18.diabetesinformationapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TextInputLayout;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import hci923e18.database.Profile;
import hci923e18.utility.Calculator;
import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalculatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Profile user = new Profile();



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


    public CalculatorFragment() {
        // Required empty public constructor
        try {
            user = Profile.listAll(Profile.class).get(0);
        } catch (Exception e) {
            user.set_idealBloodGlucoseLevel(6.0);
            user.set_weight(80.0);
            user.set_insulinDuration(3.5);
            user.set_totalDailyInsulinConsumption(41.6);
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_calculator, container, false);

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
                if(fiberInput.getText() != null && !fiberInput.getText().toString().isEmpty())
                {
                    Double fiberPercentageResult = calculator.fiberPercentage(Double.parseDouble(carbohydrateInput.getText().toString()),Double.parseDouble(fiberInput.getText().toString()));

                    if(fiberPercentageResult >= 25.0)
                    {
                        guideText.setText("Fiberen udgør " + formater.format(fiberPercentageResult) + "% af kulhydraterne. \nVi anbefaler at du tager insulinen efter måltidet, da det optages langsommere i blodet.");
                    }
                    else
                        {
                            guideText.setText("Fiberen udgør " + formater.format(fiberPercentageResult) + "% af kulhydraterne, vi anbefaler at du tager insulin før måltidet");
                        }

                }

                Double bloodGlucoseAdjustment = calculator.bloodGlucoseGoalCalculation(Double.parseDouble(bloodGlucoseInput.getText().toString()));
                Double result = calculator.insulinCalculator(Double.parseDouble(carbohydrateInput.getText().toString()),Double.parseDouble(bloodGlucoseInput.getText().toString()));
                insulinResult.setText(formater.format(result).toString());

                if(Double.parseDouble(bloodGlucoseInput.getText().toString())>user.get_idealBloodGlucoseLevel())
                {
                    if(guideText.getText() != null && !guideText.getText().toString().isEmpty())
                    {
                        guideText.append("\nVær opmærksom på at på grund af det indtastede blodsukker, er " + formater.format(bloodGlucoseAdjustment) + " enheder lagt til den beregnet insulin.");
                    }
                    else
                        {
                            guideText.setText("Vær opmærksom på at på grund af det indtastede blodsukker, er " + formater.format(bloodGlucoseAdjustment) + " enheder lagt til den beregnet insulin.");
                        }

                }
                else if(Double.parseDouble(bloodGlucoseInput.getText().toString())==user.get_idealBloodGlucoseLevel()){}
                else{
                    if(guideText.getText() != null && !guideText.getText().toString().isEmpty())
                    {
                        guideText.append("\nVær opmærksom på at på grund af det indtastede blodsukker, er " + formater.format(bloodGlucoseAdjustment) + " enheder trukket fra den beregnet insulin.");
                    }
                    else
                    {
                        guideText.setText("Vær opmærksom på at på grund af det indtastede blodsukker, er " + formater.format(bloodGlucoseAdjustment) + " enheder trukket fra den beregnet insulin.");
                    }
                }


            }});

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

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








}
