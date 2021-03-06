package hci1025f19.diabetesinformationapplication.Tabs;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hci1025f19.database.Identifier;
import hci1025f19.diabetesinformationapplication.BloodGlycoseOverview.BloodGlycoseOverviewActivity;
import hci1025f19.diabetesinformationapplication.R;
import hci1025f19.diabetesinformationapplication.UCI.UCI;
import hci1025f19.diabetesinformationapplication.UCI.UCIAdvanced;
import hci1025f19.diabetesinformationapplication.newBloodGlucoseLevelActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    //Global variables
    Button createButton;
    Button displayButton;
    Button overviewButton;
    Button newbloodglucoselevelButton;
    Button diary;

    FloatingActionButton floatingActionButtonHome;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Default onCreate method
     * @param savedInstanceState The saved instance can be used if the application is reopened
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
     * Default onCreateView method
     * @param inflater The inflater for the view
     * @param container The container for the view
     * @param savedInstanceState The saved instance
     * @return
     */
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Binder components
        createButton = view.findViewById(R.id.button_homepageInsulinCalc);
        displayButton = view.findViewById(R.id.button_homepageMeal);
        overviewButton = view.findViewById(R.id.button_home_overview);
        newbloodglucoselevelButton = view.findViewById(R.id.button_homepageNewBloodGlucoseLevel);


        //Click events
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FrontPageActivity)getActivity()).changeToData();
            }
        });

        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FrontPageActivity)getActivity()).changeToMealPlan();
            }
        });

        overviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BloodGlycoseOverviewActivity.class);
                getActivity().startActivity(intent);
            }
        });

        newbloodglucoselevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), newBloodGlucoseLevelActivity.class);
                getActivity().startActivity(intent);
            }
        });


        floatingActionButtonHome = view.findViewById(R.id.floatingActionButton_home);
        floatingActionButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UCI.class);
                intent.putExtra("PageName", "HomePage");
                getActivity().startActivity(intent);
                            }
        });

        diary = view.findViewById(R.id.button_diary);
        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), UCIAdvanced.class);
                startActivity(i);
            }
        });

        try {
            Identifier i = Identifier.listAll(Identifier.class).get(0);
            if (!i.get_advanced()){
                diary.setVisibility(View.GONE);
                diary.setEnabled(false);
            }
        } catch (Exception e) {

        }

        // Inflate the layout for this fragment
        return view;
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
        void onFragmentInteraction(Uri uri);
    }
}

