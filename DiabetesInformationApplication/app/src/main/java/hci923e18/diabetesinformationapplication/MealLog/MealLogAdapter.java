package hci923e18.diabetesinformationapplication.MealLog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import hci923e18.database.MealObject;
import hci923e18.diabetesinformationapplication.R;

public class MealLogAdapter extends ArrayAdapter<MealObject> {
    private Context mContext;
    private List<MealObject> mMeal;
    private MealLogFragment mMealLogFragment;

    public MealLogAdapter(Context context, int resource, @NonNull List<MealObject> objects, MealLogFragment mealLogFragment){
        super(context, resource, objects);
        mContext = context;
        mMeal = objects;
        mMealLogFragment = mealLogFragment;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.mealloglayout,parent,false);

        final MealObject currentMeal = mMeal.get(position);

        TextView Type = listItem.findViewById(R.id.textView_mealLogType);
        Type.setText(currentMeal.get_mealtype());

        TextView Date = listItem.findViewById(R.id.textView_meaLogDate);
        Date.setText(currentMeal.get_timestamp());

        TextView Carbs = listItem.findViewById(R.id.textView_mealLogCarbs);
        Carbs.setText(currentMeal.get_totalCarbs().toString() + " g");

        TextView Insulin = listItem.findViewById(R.id.textView_mealLogInsulin);
        Insulin.setText(currentMeal.get_insulinResult().toString() + " Enheder");

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMealLogFragment.openSpecificLog(currentMeal);
            }
        });

        return listItem;
    }
}
