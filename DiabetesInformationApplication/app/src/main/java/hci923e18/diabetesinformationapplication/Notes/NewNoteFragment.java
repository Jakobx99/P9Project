package hci923e18.diabetesinformationapplication.Notes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import hci923e18.database.NoteObject;
import hci923e18.diabetesinformationapplication.R;
import hci923e18.diabetesinformationapplication.UCI.UCI;
import hci923e18.utility.KeyBoard;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewNoteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewNoteFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    Button saveNote;
    EditText title;
    EditText noteContext;
    Context mContext;
    NoteObject noteObject = new NoteObject();
    Boolean data = false;
    DecimalFormat formater = new DecimalFormat("#.##");
    private FloatingActionButton floatingActionButtonNewNote;

    /**
     * Default constructor
     */
    public NewNoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewNoteFragment.
     */
    public static NewNoteFragment newInstance(String param1, String param2) {
        NewNoteFragment fragment = new NewNoteFragment();
        return fragment;
    }

    /**
     * Default On create method - called when the fragment is constructed
     * @param savedInstanceState The saved instance can be used if the application is reopened
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {  super.onCreate(savedInstanceState); }

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
        final View view = inflater.inflate(R.layout.fragment_new_note, container, false);
        getActivity().setTitle("DIAbetes - Ny note");
        KeyBoard.setHideKeyboardOnTouch(view.getContext(), view.findViewById(R.id.NewNoteConstraintLayout));

        saveNote = view.findViewById(R.id.buttonSave);
        title = view.findViewById(R.id.textInputNoteTitle);
        noteContext = view.findViewById(R.id.textInputNoteContext);

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(save(view)){
                    Toast.makeText(view.getContext(), "Note blev gemt",Toast.LENGTH_LONG);
                    //NoteListFragment noteListFragment = new NoteListFragment();
                    //noteListFragment.dataChanged();
                    getActivity().getFragmentManager().popBackStack();
                    FragmentManager fragmentManager = getActivity().getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayoutFrontPage, new NoteListFragment()).addToBackStack("noteList").commit();
                }
            }
        });

        if(data){
            setup();
        }

        floatingActionButtonNewNote = view.findViewById(R.id.floatingActionButton_newNote);
        floatingActionButtonNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UCI.class);
                intent.putExtra("PageName", "NewNotePage");
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    /**
     * Default onButtonPressed method
     * @param uri uri element to hook on the fragment
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
     * Save Method to save noteObject to database
     * @return true if success or false if fails
     */
    private Boolean save(View view){

        if(noteContext.getText().toString().isEmpty() || title.getText().toString().isEmpty()){
            Toast.makeText(view.getContext(),"Der er et tomt felt", Toast.LENGTH_SHORT ).show();
            return false;
        }
        else {
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            noteObject.set_title(title.getText().toString());
            noteObject.set_context(noteContext.getText().toString());
            noteObject.set_timestamp(date);
            try {
                noteObject.save();
            } catch (Exception e) {
                return false;
            }
            return true;
            }
    }

    /**
     * Used to pass date when saving a note object
     * @param context the context of the note
     * @param m the note object itself
     */
    public void passData(Context context, NoteObject m) {
        mContext = context;
        noteObject = m;
        data = true;
    }

    /**
     * Setup method used to get a noteObject title and context
     */
    public void setup(){
        title.setText(noteObject.get_title());
        noteContext.setText(noteObject.get_context());
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
