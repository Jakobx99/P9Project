package hci923e18.diabetesinformationapplication.SpecificLog;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import hci923e18.database.MealObject;
import hci923e18.diabetesinformationapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SpecificLogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SpecificLogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpecificLogFragment extends Fragment {
    private SpecificLogFragment.OnFragmentInteractionListener mListener;

    EditText mealLogSpinner;
    EditText mealLogBloodSugar;
    TextView mealLogResult;
    ListView mealLogLayout;
    SpecificLogAdapter mAdapter;
    Context mContext;
    MealObject _MealObject;

    /**
     * Default constructor
     */
    public SpecificLogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SpecificLogFragment.
     */
    public static SpecificLogFragment newInstance(String param1, String param2) {
        SpecificLogFragment fragment = new SpecificLogFragment();

        return fragment;
    }

    /**
     * On create method called when fragment is created
     * @param savedInstanceState The saved instance state used if the application is reopened
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
        View view = inflater.inflate(R.layout.fragment_specific_log, container, false);

        mealLogSpinner = view.findViewById(R.id.spinner_specificLog);
        mealLogBloodSugar = view.findViewById(R.id.editText_specificLog_bloodsugar);
        mealLogResult = view.findViewById(R.id.textView_specificLog_result);
        mealLogLayout = view.findViewById(R.id.specificLog_listview);

        mealLogResult.setText(_MealObject.get_insulinResult().toString());
        mealLogBloodSugar.setText(_MealObject.get_bloodGlucoseLevel().toString());
        mealLogSpinner.setText(_MealObject.get_mealtype());

        mealLogBloodSugar.setInputType(0);
        mealLogSpinner.setInputType(0);

        mAdapter = new SpecificLogAdapter(view.getContext(),0, _MealObject.get_meals());
        mealLogLayout.setAdapter(mAdapter);

        // Inflate the layout for this fragment
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
     * Method used when passing data into the fragment
     * @param context The context of the activity that calls the fragment
     * @param m A Meal object used in the fragment
     */
    public void passData(Context context, MealObject m) {
        mContext = context;
        _MealObject = m;
    }
}
