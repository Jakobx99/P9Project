package hci923e18.diabetesinformationapplication.Notes;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import hci923e18.database.NoteObject;
import hci923e18.diabetesinformationapplication.R;
import hci923e18.diabetesinformationapplication.Tabs.FrontPageActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoteListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoteListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteListFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    Button newNote;
    List<NoteObject> noteObjects = new ArrayList<>();
    ListView noteListView;
    NoteListAdapter localAdapter;
    View view;

    /**
     * Default constructor
     */
    public NoteListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoteListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoteListFragment newInstance(String param1, String param2) {
        NoteListFragment fragment = new NoteListFragment();
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

        view = inflater.inflate(R.layout.fragment_note_list, container, false);

        try {
            noteObjects = NoteObject.listAll(NoteObject.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        noteListView = view.findViewById(R.id.listview_NoteList);
        newNote = view.findViewById(R.id.buttonNewNote);

        localAdapter = new NoteListAdapter(view.getContext(),0, noteObjects, NoteListFragment.this);
        noteListView.setAdapter(localAdapter);

        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FrontPageActivity)getActivity()).changeToNewNote();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
     * Method used to open a specific note from the list of notes, by replacing the fragment with a new one
     * @param noteObject the specific note object
     */
    public void openSpecificNote(NoteObject noteObject){
        NewNoteFragment myFragment = new NewNoteFragment();
        myFragment.passData(view.getContext(), noteObject);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.framelayoutFrontPage, myFragment).addToBackStack("SpecificNote").commit();
    }

    /**
     * Used to remove a note object from the list and the database
     * @param pos position of the note to delete in the list of notes
     */
    public void removeItemFromList(int pos){
        noteObjects.remove(pos);
        localAdapter.notifyDataSetChanged();
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
