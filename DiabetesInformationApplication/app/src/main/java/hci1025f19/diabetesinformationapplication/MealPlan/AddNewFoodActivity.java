package hci1025f19.diabetesinformationapplication.MealPlan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hci1025f19.database.Food;
import hci1025f19.diabetesinformationapplication.R;
import hci1025f19.utility.HideKeyboardUtil;

public class AddNewFoodActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextCarbo;
    EditText editTextProtein;
    EditText editTextFiber;
    EditText editTextSugar;
    EditText editTextCalories;
    EditText editTextFat;
    Button buttonAddNewFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_food);

        HideKeyboardUtil.setHideKeyboardOnTouch(this, findViewById(R.id.addnewfoodlayout));

        editTextName = findViewById(R.id.editText_newfood_name);
        editTextCarbo = findViewById(R.id.editText_newfood_carbo);
        editTextProtein = findViewById(R.id.editText_newfood_protein);
        editTextFiber = findViewById(R.id.editText_newfood_fiber);
        editTextSugar = findViewById(R.id.editText_newfood_sugar);
        editTextCalories = findViewById(R.id.editText__newfood_caloreis);
        editTextFat = findViewById(R.id.editText_newfood_fat);
        buttonAddNewFood = findViewById(R.id.button_newfood_button);

        buttonAddNewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(editTextName)){
                    Toast.makeText(AddNewFoodActivity.this, "Udfyld venligst navn", Toast.LENGTH_SHORT).show();
                } else if (isEmpty(editTextCarbo)){
                    Toast.makeText(AddNewFoodActivity.this, "Udfyld venligst kulhydrater", Toast.LENGTH_SHORT).show();
                } else if (isEmpty(editTextProtein)){
                    Toast.makeText(AddNewFoodActivity.this, "Udfyld venligst protein", Toast.LENGTH_SHORT).show();
                } else if (isEmpty(editTextFiber)){
                    Toast.makeText(AddNewFoodActivity.this, "Udfyld venligst fiber", Toast.LENGTH_SHORT).show();
                } else if (isEmpty(editTextSugar)){
                    Toast.makeText(AddNewFoodActivity.this, "Udfyld venligst sukker", Toast.LENGTH_SHORT).show();
                } else if (isEmpty(editTextCalories)){
                    Toast.makeText(AddNewFoodActivity.this, "Udfyld venligst kalorier", Toast.LENGTH_SHORT).show();
                } else if (isEmpty(editTextFat)){
                    Toast.makeText(AddNewFoodActivity.this, "Udfyld venligst fedt", Toast.LENGTH_SHORT).show();
                } else {
                    Food f = new Food(editTextName.getText().toString(), Double.parseDouble(0 + editTextCalories.getText().toString()),Double.parseDouble(0 + editTextProtein.getText().toString()), Double.parseDouble(0 + editTextCarbo.getText().toString()), Double.parseDouble(0 + editTextSugar.getText().toString()), Double.parseDouble(0 + editTextFiber.getText().toString()), Double.parseDouble(0 + editTextFat.getText().toString()));
                    try {
                        f.save();
                        Toast.makeText(AddNewFoodActivity.this, "Madvaren blev tilføjet", Toast.LENGTH_LONG).show();
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(AddNewFoodActivity.this, "Der skete en fejl prøv igen", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }

    /**
     * A method to check if an edittext is empty
     * @param etText The edittext field
     * @return Boolean representing if the field was empty
     */
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

}
