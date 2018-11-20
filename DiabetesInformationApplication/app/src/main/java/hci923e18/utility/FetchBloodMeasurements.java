package hci923e18.utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import hci923e18.database.BloodGlucoseMeasurements;

public class FetchBloodMeasurements {

    public void fetchData(Calendar startDate, Calendar endDate){

        //TODO Skal sætter som global variabler
        List<String> listHead = new ArrayList<>();
        HashMap<String, BloodGlucoseMeasurements> listChild;

        //Fetch all measurements from/to date
        List<BloodGlucoseMeasurements> l = new ArrayList<>();

        l.addAll(BloodGlucoseMeasurements.find(BloodGlucoseMeasurements.class, "_date >= ? and _date <= ?", Long.toString(startDate.getTimeInMillis()), Long.toString(endDate.getTimeInMillis())));

        for (BloodGlucoseMeasurements b:l) {
            
        }


    }
}
