package hci923e18.diabetesinformationapplication.UCI;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import hci923e18.MongoDB.MongoDB;
import hci923e18.database.ErrorObject;
import hci923e18.database.Identifier;
import hci923e18.diabetesinformationapplication.R;

public class UCI extends AppCompatActivity {

    EditText UCIPage;
    EditText UCIDate;
    EditText UCITask;
    Spinner UCIType;
    Spinner UCIElement;
    Spinner UCIFrequency;
    Spinner UCICompleted;
    Spinner UCIError;
    Spinner UCIEffect;
    ErrorObject errorObject;
    Button UCISend;
    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uci);
        errorObject = new ErrorObject();

        UCIPage = findViewById(R.id.edittext_uci_page);
        UCIDate = findViewById(R.id.edittext_uci_date);
        UCITask = findViewById(R.id.edittext_uci_task);
        UCIType = findViewById(R.id.spinner_uci_type);
        UCIElement = findViewById(R.id.spinner_uci_element);
        UCIFrequency = findViewById(R.id.spinner_uci_task_frequency);
        UCICompleted = findViewById(R.id.spinner_uci_task_completed);
        UCIError = findViewById(R.id.spinner_uci_task_error);
        UCIEffect = findViewById(R.id.spinner_uci_task_effect);
        UCISend = findViewById(R.id.button_uci_send);

        errorObject.setDate(Calendar.getInstance().getTimeInMillis());
        errorObject.setReadableDate(sdf.format(Calendar.getInstance().getTimeInMillis()));
        UCIDate.setText(sdf.format(Calendar.getInstance().getTimeInMillis()));


        String pageName= getIntent().getStringExtra("PageName");
        errorObject.setPage(pageName);
        UCIPage.setText(pageName);

        //Type
        String [] types =
                {"Applikaitons fejl","Uforståelig side","Brugbarhed"};
        ArrayAdapter<String> adapterTypes = new ArrayAdapter<String>(UCI.this, android.R.layout.simple_spinner_item, types);
        adapterTypes.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        UCIType.setAdapter(adapterTypes);
        UCIType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                errorObject.setType(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Elements
        //TODO CREATE FOR EACH PAGE
        String [] elements =
                {"tekst felt","udregning","andet"};
        ArrayAdapter<String> adapterElements = new ArrayAdapter<String>(UCI.this, android.R.layout.simple_spinner_item, elements);
        adapterElements.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        UCIElement.setAdapter(adapterElements);
        UCIElement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                errorObject.setElement(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Frequency
        String [] frequency =
                {"Meget ofte", "ofte", "En gang i mellem", "Sjældent", "Første gang"};
        ArrayAdapter<String> adapterFrequency = new ArrayAdapter<String>(UCI.this, android.R.layout.simple_spinner_item, frequency);
        adapterFrequency.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        UCIFrequency.setAdapter(adapterFrequency);
        UCIFrequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                errorObject.setFrequency(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Completed
        String [] completed =
                {"Ikke muligt","Muligt men med store udfordringer", "Muligt men med mindre udfordringer", "Muligt med ubetydelige udfradringer", "Fejlen havde ingen effekt"};
        ArrayAdapter<String> adapterCompleted = new ArrayAdapter<String>(UCI.this, android.R.layout.simple_spinner_item, completed);
        adapterCompleted.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        UCICompleted.setAdapter(adapterCompleted);
        UCICompleted.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                errorObject.setCompleted(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Error
        String [] errors =
                {"Meget kraftig påvirkning", "Kraftig påvirkning", "Moderat påvirking", "Minimal påvirkning", "Ingen påvirkning"};
        ArrayAdapter<String> adapterError = new ArrayAdapter<String>(UCI.this, android.R.layout.simple_spinner_item, errors);
        adapterError.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        UCIError.setAdapter(adapterError);
        UCIError.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                errorObject.setError(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Effect
        String [] effect =
                {"Betydelig stor effect","stor effect", "mindre effect", "Ubetydelig effekt", "ingen effekt"};
        ArrayAdapter<String> adapterEffect = new ArrayAdapter<String>(UCI.this, android.R.layout.simple_spinner_item, effect);
        adapterEffect.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        UCIEffect.setAdapter(adapterEffect);
        UCIEffect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                errorObject.setEffect(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        UCISend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorObject.setTask(UCITask.getText().toString());
                sendToDatabase();

            }
        });
    }

    /**
     * Method inserting the errorobject in the remote MongoDB
     */
    private void sendToDatabase(){
        MongoDB m = new MongoDB();
        try {
            Identifier i = Identifier.listAll(Identifier.class).get(0);
            errorObject.setId(i.get_ID());
            m.execute(errorObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(UCI.this, "Rapport sendt", Toast.LENGTH_LONG).show();
        finish();
    }
}
