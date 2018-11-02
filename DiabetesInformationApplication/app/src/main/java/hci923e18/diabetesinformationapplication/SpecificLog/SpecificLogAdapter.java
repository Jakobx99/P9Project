package hci923e18.diabetesinformationapplication.SpecificLog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.List;
import hci923e18.database.Food;
import hci923e18.diabetesinformationapplication.R;

public class SpecificLogAdapter extends ArrayAdapter<Pair<Food, Double>> {

    private Context mContext;
    private List<Pair<Food, Double>> mFood;
    DecimalFormat formater = new DecimalFormat("#.##");

    /**
     * Constructor
     * @param context Context of the application
     * @param resource Resource number
     * @param objects List of Food object
     */
    public SpecificLogAdapter(@NonNull Context context, int resource, @NonNull List<Pair<Food, Double>> objects) {
        super(context, resource, objects);
        mContext = context;
        mFood = objects;
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
            listItem = LayoutInflater.from(mContext).inflate(R.layout.specificloglist,parent,false);

        Pair<Food, Double> currentFood = mFood.get(position);

        TextView name = listItem.findViewById(R.id.specificlog_ListLayoutName);
        name.setText(currentFood.first.get_name());

        TextView carbohydrate = listItem.findViewById(R.id.specificlog_ListLayouCarbs);
        carbohydrate.setText(formater.format(currentFood.first.get_carbohydrate()) + " g");

        TextView fiber = listItem.findViewById(R.id.specificlog_ListLayoutFiber);
        fiber.setText(formater.format(currentFood.first.get_fiber()) + " g");

        TextView weight = listItem.findViewById(R.id.specificlog_ListLayoutWeight);
        weight.setText(formater.format(currentFood.second) + " g");

        return listItem;
    }
}
