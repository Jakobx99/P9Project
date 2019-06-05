package hci1025f19.diabetesinformationapplication.OldCalculations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hci1025f19.database.InsulinCalculation;
import hci1025f19.diabetesinformationapplication.R;

public class OldCalculationsActivity extends AppCompatActivity {

    ListView oldCalcListview;
    List<InsulinCalculation> insulinCalculationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_calculations);

        oldCalcListview = findViewById(R.id.oldCalc_listview);

        insulinCalculationList = InsulinCalculation.listAll(InsulinCalculation.class);

        OldCalculationsAdapter oldCalculationsAdapter = new OldCalculationsAdapter(this, 1, insulinCalculationList);

        oldCalcListview.setAdapter(oldCalculationsAdapter);
        oldCalcListview.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
    }

}
