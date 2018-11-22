package hci923e18.diabetesinformationapplication;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, String> _listDataChild;

    /**
     * Constructor
     * @param context Context of the application
     * @param listDataHeader A list of string used to generate the headers of the ExpandableList
     * @param listChildData A list of string used to generate the child of the ExpandableList
     */
    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, String> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    /**
     * getChild method used to return the child based on the position of the list Header
     * @param groupPosition Position of the list Header
     * @param childPosititon Position of the child
     * @return Return the Child object at a given position
     */
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition));
    }

    /**
     * getChildID method used to return the child's ID based on the position of the list child
     * @param groupPosition Position of the list Header
     * @param childPosition Position of the child
     * @return return the ID of the child(position)
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * getChildView used generated the List on the view
     * @param groupPosition Position of the list Header
     * @param childPosition Position of the child
     * @param isLastChild Boolean used to check if it is the last child
     * @param convertView The view who called it
     * @param parent The ViewGroup of the parent
     * @return Return the View
     */
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.faqlistlayoutitems, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.expandableListView_FAQItems);

        txtListChild.setText(childText);
        return convertView;
    }

    /**
     * getChildrenCount used for FAQ list only, hence the return of 1
     * @param groupPosition Position of the list Header
     * @return 1 returns 1 as there is always only one answer to the FAQ
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    /**
     * getGroup method used to return the header object based on the Header in the list
     * @param groupPosition Position of the Header in the list
     * @return return the object containing a list of headers
     */
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    /**
     * getGroupCount method used to return the size of the header list
     * @return returns and int representing the count of headers
     */
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    /**
     * getGroupID method used to return the ID of the header based on position
     * @param groupPosition Position of the Header in the list
     * @return returns a Long representing the ID of the header (position)
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * getChildView method used generated the List on the view
     * @param groupPosition Position of the Header in the list
     * @param isExpanded Boolean used to determine if the list header is expanded
     * @param convertView The view who called it
     * @param parent The ViewGroup of the parent
     * @return Return the View
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.faqlistlayoutheader, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.expandableListView_FAQHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    /**
     * Default method of hasStableIds
     * @return false
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * isChildSelectable method used to determine if a child is selectable
     * @param groupPosition Position of the Header in the list
     * @param childPosition Position of the child
     * @return true
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    //Guide til at bruge expandable List View
    //setOnGroupExpandListener
    //https://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
}