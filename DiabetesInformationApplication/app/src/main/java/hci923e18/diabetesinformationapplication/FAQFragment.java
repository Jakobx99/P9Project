package hci923e18.diabetesinformationapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import hci923e18.database.FrequentlyAskedQuestions;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FAQFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FAQFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FAQFragment extends Fragment {

    Spinner typeSpinner;
    Spinner categorySpinner;
    String type;
    String category;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<FrequentlyAskedQuestions> FAQList = new ArrayList<>();
    List<String> listDataHeader;
    HashMap<String, String> listDataChild;

    private OnFragmentInteractionListener mListener;

    /**
     * Default constructor
     */
    public FAQFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FAQFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FAQFragment newInstance(String param1, String param2) {
        FAQFragment fragment = new FAQFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq, container, false);

        typeSpinner = view.findViewById(R.id.spinner_FAQType);
        categorySpinner = view.findViewById(R.id.spinner_FAQCategory);

        // get the listview
        expListView = view.findViewById(R.id.expandableListView_FAQ);
        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(this.getActivity(), listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        //typeSpinner
        String [] valuesTypes =
                {"Alle","Type 1","Type 2"};
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, valuesTypes);
        adapterType.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        typeSpinner.setAdapter(adapterType);

        //categorySpinner
        String [] valuesCategory =
                {"Alle","Mad","Livet med diabetes"};
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, valuesCategory);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        categorySpinner.setAdapter(adapterCategory);

        // typeSpinner
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // categorySpinner
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
     * Default onAttach
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
     * Method for preparing the List of FAQs from the database
     */
    private void prepareListData() {

        //TODO Lave en FAQAdapter for at kunne lave et filter til reloed liste ud fra spinnerne.


        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, String>();
        String tempAnswer;

        try {
            FAQList = FrequentlyAskedQuestions.listAll(FrequentlyAskedQuestions.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < FAQList.size(); i++){
            listDataHeader.add(FAQList.get(i).get_title());
            tempAnswer = FAQList.get(i).get_answer();
            listDataChild.put(listDataHeader.get(i), tempAnswer);
        }
    }
}
