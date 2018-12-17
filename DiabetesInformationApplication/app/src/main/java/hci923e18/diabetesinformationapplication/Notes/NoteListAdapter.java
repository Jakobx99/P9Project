package hci923e18.diabetesinformationapplication.Notes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hci923e18.database.NoteObject;
import hci923e18.diabetesinformationapplication.R;

public class NoteListAdapter extends ArrayAdapter<NoteObject> {

    private Context mContext;
    private List<NoteObject> mNotes;
    private NoteListFragment mNoteListFragment;
    private ImageView mDelete;

    /**
     * Constructor
     * @param context Context of the application
     * @param resource Resource number
     * @param objects List of Note Object
     * @param noteListFragment The instance of the fragment the adapter is used with
     */
    public NoteListAdapter(Context context, int resource, @NonNull List<NoteObject> objects, NoteListFragment noteListFragment){
        super(context, resource, objects);
        mContext = context;
        mNotes = objects;
        mNoteListFragment = noteListFragment;
    }

    /**
     * The getView method. Called when the adapter is used to render the view. Containts the click events for the items in the view
     * @param position The position of the current item
     * @param convertView The view of the current item
     * @param parent The parent viewgroup in which the item is rendered
     * @return
     */
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.notelistlayout,parent,false);

        final NoteObject currentNote = mNotes.get(position);

        TextView Title = listItem.findViewById(R.id.column_NoteTitleListLayout);
        Title.setText(currentNote.get_title());

        TextView Date = listItem.findViewById(R.id.column_NoteDateListLayout);
        Date.setText(currentNote.get_timestamp());

        mDelete = listItem.findViewById(R.id.imageView_NoteListDelete);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoteListFragment.removeItemFromList(position);
                currentNote.delete();

            }
        });

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoteListFragment.openSpecificNote(currentNote);
            }
        });
        return listItem;
    }
}
