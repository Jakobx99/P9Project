package hci923e18.diabetesinformationapplication.MealPlan;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import hci923e18.database.Food;
import hci923e18.diabetesinformationapplication.R;

import static android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL;

public class PopUpAdapter extends ArrayAdapter<Food> {

    private Context mContext;
    public List<Food> mFood = null;
    private MealPlanFragment mMealPlanFragment;
    public ArrayList<Food> arraylist = null;
    public String m_Text="";

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
        this.arraylist.addAll(objects);
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
                        Pair<Food, Double> chosenFood = new Pair<>(mFood.get(position), Double.parseDouble(0 + m_Text));
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

        TextView name = listItem.findViewById(R.id.textView_PopUpListName);
        name.setText(currentFood.get_name());

        TextView carbohydrate = listItem.findViewById(R.id.textView_PopUpListLayoutCarbs);
        carbohydrate.setText(currentFood.get_carbohydrate().toString() + " g");

        TextView protein = listItem.findViewById(R.id.textView_PopUpListLayoutProtein);
        protein.setText(currentFood.get_protein().toString() + " g");

        TextView sugar = listItem.findViewById(R.id.textView_PopUpListLayoutSugar);
        sugar.setText(currentFood.get_sugar().toString() + " g");

        TextView fiber = listItem.findViewById(R.id.textView_PopUpListLayoutFiber);
        fiber.setText(currentFood.get_fiber().toString() + " g");

        return listItem;
    }

    /**
     * Filter method used when searching
     * @param charText The search string
     */
    public void filter(final String charText) {
        mFood.clear();
        if (charText.length() == 0) {
            mFood.addAll(arraylist);
        }
        else
        {
            for (Food food : arraylist) {
                if (food.get_name().toLowerCase(Locale.getDefault()).contains(charText.toLowerCase())) {
                    mFood.add(food);
                }
            }

            sorting(charText);
        }
    }


    public void sorting(final String s){
        Collections.sort(mFood, new Comparator<Food>() {
            @Override
            public int compare(Food food, Food t1) {

                if (similarity(s.toLowerCase(), food.get_name().toLowerCase()) > similarity(s.toLowerCase(), t1.get_name().toLowerCase())){
                    return -1;
                } else return 1;
            }
        });
    }

    /**
     * Calculates the similarity (a number within 0 and 1) between two strings.
     */
    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
    /* // If you have Apache Commons Text, you can use it to calculate the edit distance:
    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
    return (longerLength - levenshteinDistance.apply(longer, shorter)) / (double) longerLength; */
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    // Example implementation of the Levenshtein Edit Distance
    // See http://rosettacode.org/wiki/Levenshtein_distance#Java
    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }
}