package hci923e18.diabetesinformationapplication.UCI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import hci923e18.MongoDB.MongoDB;
import hci923e18.MongoDB.MongoDBAdvanced;
import hci923e18.database.Identifier;
import hci923e18.database.UCIAdvancedObject;
import hci923e18.diabetesinformationapplication.R;

public class UCIAdvancedGoodBad extends AppCompatActivity {

    Button buttonConfirm;
    EditText editTextGood;
    EditText editTextBad;
    UCIAdvancedObject uciAdvancedObject;
    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");

    /**
     * OnCreate method
     * @param savedInstanceState The saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uciadvanced_good_bad);

        uciAdvancedObject = (UCIAdvancedObject)getIntent().getSerializableExtra("myObj");

        uciAdvancedObject.setDate(Calendar.getInstance().getTimeInMillis());
        uciAdvancedObject.setReadableDate(sdf.format(Calendar.getInstance().getTimeInMillis()));

        buttonConfirm = findViewById(R.id.button_goodbadsend);
        editTextBad = findViewById(R.id.editText_advanced_bad);
        editTextGood = findViewById(R.id.editText_advanced_good);
        
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(editTextGood)){
                    Toast.makeText(UCIAdvancedGoodBad.this, "Beskriv venligst noget godt du oplevede angående appen i dag", Toast.LENGTH_LONG).show();
                } else if (isEmpty(editTextBad)){
                    Toast.makeText(UCIAdvancedGoodBad.this, "Beskriv venligst noget dårligt du oplevede angående appen i dag", Toast.LENGTH_LONG).show();
                } else {
                    uciAdvancedObject.setGood(editTextGood.getText().toString());
                    uciAdvancedObject.setBad(editTextBad.getText().toString());

                    sendToDatabase();
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

    /**
     * Method used to send the UCIAdvancedObject to the Mongo database
     */
    private void sendToDatabase(){
        try {
            Identifier i = Identifier.listAll(Identifier.class).get(0);
            uciAdvancedObject.setHotfixes(i.get_hotfix());
            new MongoDBAdvanced().execute(uciAdvancedObject);
        } catch (Exception e) {
            Toast.makeText(this, "Der skete en fejl da rapporten blev sendt, Tjek internet forbindelsen og prøv igen senere", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(UCIAdvancedGoodBad.this, "Dagbogs indtastningen er hermed gemt og sendt :-)", Toast.LENGTH_LONG).show();
        finish();
    }

}
