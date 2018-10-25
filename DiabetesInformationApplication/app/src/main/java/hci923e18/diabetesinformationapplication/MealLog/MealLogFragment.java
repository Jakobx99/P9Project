package hci923e18.diabetesinformationapplication.MealLog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import hci923e18.database.MealObject;
import hci923e18.diabetesinformationapplication.R;
import hci923e18.diabetesinformationapplication.SpecificLog.SpecificLogFragment;

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

    /**
     * Default constructor
     */
    public MealLogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MealLogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MealLogFragment newInstance(String param1, String param2) {
        MealLogFragment fragment = new MealLogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_meal_log, container, false);
        mealLogListView = view.findViewById(R.id.listview_MealLog);
        try {
            meals = MealObject.listAll(MealObject.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mAdapter = new MealLogAdapter(view.getContext(),0, meals, MealLogFragment.this);
        mealLogListView.setAdapter(mAdapter);
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

    public void openSpecificLog(MealObject m){

        SpecificLogFragment myFragment = new SpecificLogFragment();
        myFragment.passData(view.getContext(),m);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.framelayoutFrontPage, myFragment);
        ft.commit();
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
