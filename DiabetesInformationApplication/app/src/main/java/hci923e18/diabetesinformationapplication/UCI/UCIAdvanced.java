package hci923e18.diabetesinformationapplication.UCI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import hci923e18.database.ErrorObject;
import hci923e18.database.Identifier;
import hci923e18.diabetesinformationapplication.R;

public class UCIAdvanced extends AppCompatActivity {

    Button buttonAdvanced;
    EditText editTextDiary;
    ErrorObject errorObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uciadvanced);

        buttonAdvanced = findViewById(R.id.button_advanced_diary);
        editTextDiary = findViewById(R.id.editText_advanced);

        buttonAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(editTextDiary)){
                    Toast.makeText(UCIAdvanced.this, "Beskriv venligst dine oplevelser", Toast.LENGTH_LONG).show();
                } else {

                    Identifier i =Identifier.listAll(Identifier.class).get(0);

                    errorObject.setId(i.get_ID());
                    errorObject.setAdvanced(true);
                    errorObject.setDiary(editTextDiary.getText().toString());

                    //TODO Enten upload til db eller redirect til mere


//                    Intent intent = new Intent(UCIAdvanced.this, UCI.class);
//                    intent.putExtra("myObj", errorObject);
//                    startActivity(intent);
                    finish();
                }
            }
        });


    }


    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

}
