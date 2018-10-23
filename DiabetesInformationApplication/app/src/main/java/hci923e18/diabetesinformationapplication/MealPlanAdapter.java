package hci923e18.diabetesinformationapplication;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import hci923e18.database.Food;

public class MealPlanAdapter extends ArrayAdapter<Food> {

    private Context mContext;
    private List<Food> mFood;
    private MealPlanFragment mMealPlanFragment;

    public MealPlanAdapter(@NonNull Context context, int resource, @NonNull List<Food> objects, MealPlanFragment mealPlanFragment) {
        super(context, resource, objects);
        mContext = context;
        mFood = objects;
        mMealPlanFragment = mealPlanFragment;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.mealplanlistlayout,parent,false);

        Food currentFood = mFood.get(position);

        ImageView image = listItem.findViewById(R.id.imageView_mealPlanList);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMealPlanFragment.removeItemFromList(position);

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
}
