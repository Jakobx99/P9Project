package hci1025f19.diabetesinformationapplication.UCI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hci1025f19.database.Identifier;
import hci1025f19.database.UCIAdvancedObject;
import hci1025f19.diabetesinformationapplication.R;

public class UCIAdvanced extends AppCompatActivity {

    Button buttonAdvanced;
    EditText editTextDiary;
    UCIAdvancedObject uciAdvancedObject;

    /**
     * The onCreate method
     * @param savedInstanceState The saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uciadvanced);

        buttonAdvanced = findViewById(R.id.button_advanced_diary);
        editTextDiary = findViewById(R.id.editText_advanced);
        uciAdvancedObject = new UCIAdvancedObject();

        buttonAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(editTextDiary)){
                    Toast.makeText(UCIAdvanced.this, "Beskriv venligst dine oplevelser", Toast.LENGTH_LONG).show();
                } else {

                    Identifier i = null;
                    try {
                        i = Identifier.listAll(Identifier.class).get(0);
                    } catch (Exception e) {
                        Toast.makeText(UCIAdvanced.this, "Der skete en fejl med databasen prøv igen senere", Toast.LENGTH_LONG).show();
                    }

                    uciAdvancedObject.setId(i.get_ID());
                    uciAdvancedObject.setDiary(editTextDiary.getText().toString());


                    Intent intent = new Intent(UCIAdvanced.this, UCIAdvancedGoodBad.class);
                    intent.putExtra("myObj", uciAdvancedObject);
                    startActivity(intent);


                    finish();
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
