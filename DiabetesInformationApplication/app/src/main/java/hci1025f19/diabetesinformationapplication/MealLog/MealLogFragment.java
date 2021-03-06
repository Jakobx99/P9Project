package hci1025f19.diabetesinformationapplication.MealLog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import hci1025f19.database.MealObject;
import hci1025f19.diabetesinformationapplication.R;
import hci1025f19.diabetesinformationapplication.SpecificLog.SpecificLogFragment;
import hci1025f19.diabetesinformationapplication.UCI.UCI;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MealLogFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MealLogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealLogFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    List<MealObject> meals = new ArrayList<>();
    ListView mealLogListView;
    MealLogAdapter mAdapter;
    View view;
    private FloatingActionButton floatingActionButtonMealLog;

    /**
     * Default constructor
     */
    public MealLogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MealLogFragment.
     */
    public static MealLogFragment newInstance(String param1, String param2) {
        MealLogFragment fragment = new MealLogFragment();
        return fragment;
    }

    /**
     * Default On create method - called when the fragment is constructed
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
        getActivity().setTitle("DIAbetes - Måltidslog");
        view = inflater.inflate(R.layout.fragment_meal_log, container, false);
        mealLogListView = view.findViewById(R.id.listview_MealLog);
        try {
            meals = MealObject.listAll(MealObject.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mAdapter = new MealLogAdapter(view.getContext(),0, meals, MealLogFragment.this);
        mealLogListView.setAdapter(mAdapter);
        mealLogListView.setOnTouchListener(new ListView.OnTouchListener() {
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

        floatingActionButtonMealLog = view.findViewById(R.id.floatingActionButton_mealLog);
        floatingActionButtonMealLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UCI.class);
                intent.putExtra("PageName", "MealLogPage");
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    /**
     * Default onButtonPressed
     * @param uri update argument and hook method into UI event
     */
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    /**
     * Default onAttach method
     * @param context the context of the fragment
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
     * Default onDetach
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Open the fragment with the specific meal log
     * @param m The mealobject chosen
     */
    public void openSpecificLog(MealObject m){
        SpecificLogFragment myFragment = new SpecificLogFragment();
        myFragment.passData(view.getContext(),m);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.framelayoutFrontPage, myFragment).addToBackStack("Specificlog").commit();
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
}
