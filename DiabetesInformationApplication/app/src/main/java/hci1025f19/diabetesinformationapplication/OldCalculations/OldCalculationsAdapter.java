package hci1025f19.diabetesinformationapplication.OldCalculations;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import hci1025f19.database.InsulinCalculation;
import hci1025f19.diabetesinformationapplication.R;

public class OldCalculationsAdapter extends ArrayAdapter<InsulinCalculation> {

    private Context mContext;
    private List<InsulinCalculation> insulinCalculationList;
    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM 'Kl.' HH:mm");
    DecimalFormat formater = new DecimalFormat("#.##");

    /**
     * Constructor
     * @param context Context of the application
     * @param resource Resource number
     * @param objects List of Food objects
     */
    public OldCalculationsAdapter(@NonNull Context context, int resource, @NonNull List<InsulinCalculation> objects) {
        super(context, resource, objects);
        mContext = context;
        insulinCalculationList = objects;
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
            listItem = LayoutInflater.from(mContext).inflate(R.layout.oldcalcadapterlist,parent,false);

        final InsulinCalculation insulinCalculation = insulinCalculationList.get(position);

        TextView carbs = listItem.findViewById(R.id.column_oldcarbs);
        carbs.setText(formater.format(insulinCalculation.get_carbohydrates()));

        TextView fiber = listItem.findViewById(R.id.column_oldfiber);
        fiber.setText(formater.format(insulinCalculation.get_fiber()));

        TextView measurement = listItem.findViewById(R.id.column_oldmeasurement);
        measurement.setText(formater.format(insulinCalculation.get_bloodGlycoseMeasurement()));

        TextView result = listItem.findViewById(R.id.column_oldresult);
        result.setText(formater.format(insulinCalculation.get_result()));

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Beregning fortaget d. " + sdf.format(insulinCalculation.get_date()), Toast.LENGTH_LONG).show();
            }
        });

        return listItem;
    }
}
