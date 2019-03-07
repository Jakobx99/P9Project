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

import java.io.ObjectStreamException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

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
    EditText UCIExpectation;

    /**
     * The onCreate method
     * @param savedInstanceState The saved instance state
     */
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
        UCIExpectation = findViewById(R.id.edittext_uci_expectation);

        errorObject.setDate(Calendar.getInstance().getTimeInMillis());
        errorObject.setReadableDate(sdf.format(Calendar.getInstance().getTimeInMillis()));
        UCIDate.setText(sdf.format(Calendar.getInstance().getTimeInMillis()));


        String pageName= getIntent().getStringExtra("PageName");
        errorObject.setPage(pageName);
        UCIPage.setText(pageName);

        //Type
        String [] types =
                {"Applikationsfejl", "Brugbarhedsproblem"};
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
        String [] elements = generateStringArray(pageName);
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
                {"Ikke muligt","Muligt men med store udfordringer", "Muligt men med mindre udfordringer", "Muligt med ubetydelige udfordringer", "Fejlen havde ingen effekt"};
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
                if (isEmpty(UCITask)){
                    Toast.makeText(UCI.this, "Beskriv venligst hændelsen", Toast.LENGTH_LONG).show();
                } else if(isEmpty(UCIExpectation)){
                    Toast.makeText(UCI.this, "Beskriv venligst hvad du forventede", Toast.LENGTH_LONG).show();
                } else {
                    errorObject.setTask(UCITask.getText().toString());
                    errorObject.setExpectation(UCIExpectation.getText().toString());
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
     * Method inserting the errorobject in the remote MongoDB
     */
    private void sendToDatabase(){
        //MongoDB m = new MongoDB();
        try {
            Identifier i = Identifier.listAll(Identifier.class).get(0);
            errorObject.setId(i.get_ID());
            errorObject.setAdvanced(i.get_advanced());
            errorObject.setHotfixes(i.get_hotfix());
            new MongoDB().execute(errorObject);
        } catch (Exception e) {
            Toast.makeText(this, "Der skete en fejl da rapporten blev sendt, Tjek internet forbindelsen og prøv igen senere", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(UCI.this, "Rapport sendt", Toast.LENGTH_LONG).show();
        finish();
    }

    /**
     * Method to generate the relevant string array for the dropdown menu regarding the selected page
     * @param page The calling page
     * @return A string array with the elements of the calling page
     */
    private String [] generateStringArray(String page){

        String[] def = {"tekst felt","udregning","andet"};

        if (Objects.equals(page, "HomePage")){
            return new String[]{"Knapper", "Navigations menu", "Menu i siden","Hele siden", "Andet"};
        } else if(Objects.equals(page, "CalculatorPage")){
            return new String[]{"Indtastning af kulhydrat", "Indtastning af blodsukker", "Indtastning af fiber", "Beregningen","Hele siden", "Andet"};
        } else if(Objects.equals(page, "MealPlan")){
            return new String[]{"Valg af måltids type", "Udvælgning af mad varer", "Indtastning af blodsukker", "Beregningen","Hele siden", "Andet"};
        } else if(Objects.equals(page, "BloodGlycoseOverviewPage")){
            return new String[]{"Graf i topppen af side", "Visning af sidste måling", "Tilføj ny måling knap", "Illustration af målinger i denne uge", "Link til liste over gamle målinger","Hele siden", "Andet"};
        } else if(Objects.equals(page, "GraphPage")){
            return new String[]{"Grafen", "Element på grafen", "Valg af dato interval","Hele siden", "Andet"};
        } else if(Objects.equals(page, "LongtermMeasurementPage")){
            return new String[]{"Valg af fra dato", "Valg af til dato", "Indtastning af blodsukker", "Gem knap","Hele siden", "Andet"};
        } else if (Objects.equals(page, "MealLogPage")){
            return new String[]{"Liste over tidligere beregnede måltider","Valg af måltid","Hele siden", "Andet"};
        } else if (Objects.equals(page, "NoteListPage")){
            return new String[]{"Liste over noter", "Ny note knap","Hele siden", "Andet"};
        } else if (Objects.equals(page, "NewNotePage")) {
            return new String[]{"Title felt", "Note felt", "Gem knap","Hele siden", "Andet"};
        } else if (Objects.equals(page, "SpecificLogPage")){
            return new String[]{"Måltids type felt", "Liste af måltider", "Blodsukker felt", "Beregningen","Hele siden", "Andet"};
        } else if (Objects.equals(page, "AboutUsPage")){
            return new String[]{"Logo","Om os tekst","Hele siden", "Andet"};
        } else if (Objects.equals(page, "OldBloodMeasurementsPage")){
            return new String[]{"Valg af fra dato", "Valg af til dato", "List over målinger","Hele siden", "Andet"};
        } else if (Objects.equals(page, "FAQPage")){
            return new String[]{"Valg af type", "Valg af kategori", "Søge funktion", "list over Spørgsmål","Hele siden", "Andet"};
        } else if (Objects.equals(page, "GeneratePDFPage")){
            return new String[]{"Download til telefon knap","Email felt", "Gem og send til email knap","Hele siden", "Andet"};
        } else if (Objects.equals(page, "newBloodGlucoseLevelPage")){
            return new String[]{"Valg af tid", "Indtastning af blodsukker", "Valg af type af måltid", "Valg af markering", "Gem knap","Hele siden", "Andet"};
        } else if (Objects.equals(page, "ParentalControlPage")){
            return new String[]{"Tilladelses forspørgelse", "Indtastning af mobil nummer", "Til/fra valg af funktioner", "Gem knap","Hele siden", "Andet"};
        } else if (Objects.equals(page, "SettingsPage")){
            return new String[]{"Indtastning af mål blodsukker", "Indtastning af insulin virkningstid", "Indtastning af daglig insulin forbrug", "Indtastning af grænseværdier for graf", "Gem knap","Hele siden", "Andet"};
        } else {
            return def;
        }
    }
}
