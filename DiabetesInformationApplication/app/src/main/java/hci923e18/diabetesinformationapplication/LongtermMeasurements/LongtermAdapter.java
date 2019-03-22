package hci923e18.diabetesinformationapplication.LongtermMeasurements;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import hci923e18.database.LongTermBloodGlucose;
import hci923e18.diabetesinformationapplication.R;

public class LongtermAdapter extends ArrayAdapter<LongTermBloodGlucose> {

    private Context mContext;
    private List<LongTermBloodGlucose> longTermBloodGlucoseList;
    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM 'Kl.' HH:mm");
    DecimalFormat formater = new DecimalFormat("#.##");

    /**
     * Constructor
     * @param context Context of the application
     * @param resource Resource number
     * @param objects List of Food objects
     */
    public LongtermAdapter(@NonNull Context context, int resource, @NonNull List<LongTermBloodGlucose> objects) {
        super(context, resource, objects);
        mContext = context;
        longTermBloodGlucoseList = objects;
    }

    /**
     * The getview method. Called when the adapter is used to render the view. Containts the click events for the items in the view
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
            listItem = LayoutInflater.from(mContext).inflate(R.layout.longtermadapterlayout,parent,false);

        LongTermBloodGlucose longTermBloodGlucose = longTermBloodGlucoseList.get(position);

        TextView measurement = listItem.findViewById(R.id.textView_longtermadaptermeasurement);
        measurement.setText(formater.format(longTermBloodGlucose.get_value()));

        TextView from = listItem.findViewById(R.id.textView_longtermadapterfrom);
        from.setText(sdf.format(longTermBloodGlucose.getStart().getTime()));

        TextView to = listItem.findViewById(R.id.textView_longtermadapterTo);
        to.setText(sdf.format(longTermBloodGlucose.getEnd().getTime()));

        return listItem;
    }
}
