package hci923e18.diabetesinformationapplication.MealPlan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import java.text.DecimalFormat;

import hci923e18.database.Food;
import hci923e18.diabetesinformationapplication.R;

public class MealPlanAdapter extends ArrayAdapter<Pair<Food, Double>> {

    private Context mContext;
    private List<Pair<Food, Double>> mFood;
    private MealPlanFragment mMealPlanFragment;
    DecimalFormat formater = new DecimalFormat("#.##");

    /**
     * Constructor
     * @param context Context of the application
     * @param resource Resource number
     * @param objects List of Food objects
     * @param mealPlanFragment The instance of the fragment the adapter is used with
     */
    public MealPlanAdapter(@NonNull Context context, int resource, @NonNull List<Pair<Food, Double>> objects, MealPlanFragment mealPlanFragment) {
        super(context, resource, objects);
        mContext = context;
        mFood = objects;
        mMealPlanFragment = mealPlanFragment;
    }

    /**
     * The getview method. Called when the adapter is used to render the view. Containts the click events for the items in the view
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.mealplanlistlayout,parent,false);

        Pair<Food, Double> currentFood = mFood.get(position);

        ImageView image = listItem.findViewById(R.id.imageView_mealPlanList);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMealPlanFragment.removeItemFromList(position);

            }
        });

        TextView name = listItem.findViewById(R.id.textView_mealPlanListname);
        name.setText(currentFood.first.get_name());

        TextView carbohydrate = listItem.findViewById(R.id.textView_carbo);
        carbohydrate.setText(formater.format(currentFood.first.get_carbohydrate()) + " g");


        TextView fiber = listItem.findViewById(R.id.textView_fiber);
        fiber.setText(formater.format(currentFood.first.get_fiber()) + " g");

        TextView weight = listItem.findViewById(R.id.textView_gram);
        weight.setText(formater.format(currentFood.second) + " g");



        return listItem;
    }
}
