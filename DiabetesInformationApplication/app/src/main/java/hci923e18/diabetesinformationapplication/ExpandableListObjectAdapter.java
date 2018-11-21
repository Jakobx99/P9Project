package hci923e18.diabetesinformationapplication;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import hci923e18.database.BloodGlucoseMeasurements;
import hci923e18.database.Profile;

public class ExpandableListObjectAdapter extends BaseExpandableListAdapter {

    final SimpleDateFormat sdfChild = new SimpleDateFormat("HH:mm");
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<BloodGlucoseMeasurements>> _listDataChild;
    private Profile _profile;

    public ExpandableListObjectAdapter(Context context, List<String> listDataHeader,
                                       HashMap<String, List<BloodGlucoseMeasurements>> listChildData, Profile profile) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this._profile = profile;
    }


    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final BloodGlucoseMeasurements childText = (BloodGlucoseMeasurements) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.bloodglocoselistlayoutitems, null);
        }

        LinearLayout childLayout = (LinearLayout) convertView.findViewById(R.id.LinearLayoutBloodGlucoseOverviewList) ;
        TextView txtListChildTime = (TextView) convertView
                .findViewById(R.id.expandableListView_BloodGlucoseTime);

        TextView txtListChildUnit = (TextView) convertView
                .findViewById(R.id.expandableListView_BloodGlucoseUnits);

        txtListChildTime.setText(sdfChild.format(childText.getDate().getTime()));
        txtListChildUnit.setText(childText.get_glucoseLevel().toString() + " mmol/L");
        if (childText.get_glucoseLevel() > _profile.get_upperBloodGlucoseLevel()){
            childLayout.setBackgroundColor(Color.parseColor("#e7c945"));
        }else if(childText.get_glucoseLevel() < _profile.get_lowerBloodGlucoseLevel()){
            childLayout.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        else{
            childLayout.setBackgroundColor(Color.parseColor("#3bbc32"));
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

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

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    //Guide til at bruge expandable List View
    //setOnGroupExpandListener
    //https://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
}