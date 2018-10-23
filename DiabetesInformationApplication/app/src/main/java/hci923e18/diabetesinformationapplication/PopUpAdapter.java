package hci923e18.diabetesinformationapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import hci923e18.database.Food;

public class PopUpAdapter extends ArrayAdapter<Food> {

    private Context mContext;
    private List<Food> mFood = null;
    private MealPlanFragment mMealPlanFragment;
    private ArrayList<Food> arraylist = null;

    /**
     * Constructor
     * @param context Context of the application
     * @param resource Resource number
     * @param objects List of Food objects
     * @param mealPlanFragment The instance of the fragment the adapter is used with
     */
    public PopUpAdapter(@NonNull Context context, int resource, @NonNull List<Food> objects, MealPlanFragment mealPlanFragment) {
        super(context, resource, objects);
        mContext = context;
        this.mFood = objects;
        mMealPlanFragment = mealPlanFragment;
        this.arraylist = new ArrayList<Food>();
        this.arraylist.addAll(mFood);

    }

    /**
     * Count method of the number of objects in the list
     * @return
     */
    @Override
    public int getCount() {
        return mFood.size();
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
            listItem = LayoutInflater.from(mContext).inflate(R.layout.popuplistlayout,parent,false);

        Food currentFood = mFood.get(position);

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMealPlanFragment.addItemToList(mFood.get(position));
                mMealPlanFragment.alertDialog.cancel();
            }
        });


        TextView name = listItem.findViewById(R.id.textView_mealPlanListname);
        name.setText(currentFood.get_name());

        TextView carbohydrate = listItem.findViewById(R.id.textView_carbo);
        carbohydrate.setText(currentFood.get_carbohydrate().toString() + " g");

        TextView protein = listItem.findViewById(R.id.textView_protein);
        protein.setText(currentFood.get_protein().toString() + " g");

        TextView sugar = listItem.findViewById(R.id.textView_sugar);
        sugar.setText(currentFood.get_sugar().toString() + " g");

        TextView fiber = listItem.findViewById(R.id.textView_fiber);
        fiber.setText(currentFood.get_fiber().toString() + " g");



        return listItem;
    }

    /**
     * Filter method used when searching
     * @param charText The search string
     */
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mFood.clear();
        if (charText.length() == 0) {
            mFood.addAll(arraylist);
        }
        else
        {
            for (Food food : arraylist) {
                if (food.get_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mFood.add(food);
                }
            }
        }

    }
}