package hci923e18.diabetesinformationapplication.MealPlan;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import hci923e18.database.Food;
import hci923e18.diabetesinformationapplication.MealPlan.MealPlanFragment;
import hci923e18.diabetesinformationapplication.R;

import static android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL;

public class PopUpAdapter extends ArrayAdapter<Food> {

    private Context mContext;
    private List<Food> mFood = null;
    private MealPlanFragment mMealPlanFragment;
    private ArrayList<Food> arraylist = null;
    private String m_Text="";

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

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Indtast mængde i gram");

// Set up the input
                final EditText input = new EditText(mContext);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        dialog.cancel();
                        Pair<Food, Double> chosenFood = new Pair<>(mFood.get(position), Double.parseDouble(m_Text));
                        mMealPlanFragment.addItemToList(chosenFood);
                        mMealPlanFragment.alertDialog.cancel();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();





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