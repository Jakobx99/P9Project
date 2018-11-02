package hci923e18.diabetesinformationapplication.MealLog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.List;
import hci923e18.database.MealObject;
import hci923e18.diabetesinformationapplication.R;

public class MealLogAdapter extends ArrayAdapter<MealObject> {

    private Context mContext;
    private List<MealObject> mMeal;
    private MealLogFragment mMealLogFragment;
    DecimalFormat formater = new DecimalFormat("#.##");

    /**
     *
     * Constructor
     * @param context Context of the application
     * @param resource Resource number
     * @param objects List of Food objects
     * @param mealLogFragment The instance of the fragment the adapter is used with
     */
    public MealLogAdapter(Context context, int resource, @NonNull List<MealObject> objects, MealLogFragment mealLogFragment){
        super(context, resource, objects);
        mContext = context;
        mMeal = objects;
        mMealLogFragment = mealLogFragment;
    }

    /**
     * The getView method. Called when the adapter is used to render the view and populate the MealLog list
     * @param position  Default parameter
     * @param convertView Default parameter
     * @param parent Default parameter
     * @return the views containing the populated list
     */
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.mealloglayout,parent,false);

        final MealObject currentMeal = mMeal.get(position);

        TextView Type = listItem.findViewById(R.id.textView_mealLogLayoutType);
        Type.setText(currentMeal.get_mealtype());

        TextView Date = listItem.findViewById(R.id.textView_mealLogLayoutDate);
        Date.setText(currentMeal.get_timestamp());

        TextView Carbs = listItem.findViewById(R.id.textView_mealLogLayoutCarbs);
        Carbs.setText(formater.format(currentMeal.get_totalCarbs()) + " g");

        TextView Insulin = listItem.findViewById(R.id.textView_mealLogLayoutInsulin);
        Insulin.setText(formater.format(currentMeal.get_insulinResult()) + " Enheder");

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMealLogFragment.openSpecificLog(currentMeal);
            }
        });

        return listItem;
    }
}
