package hci923e18.utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import hci923e18.database.BloodGlucoseMeasurements;

public class FetchBloodMeasurements {

    public void fetchData(Calendar startDate, Calendar endDate){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        //TODO Skal sætter som global variabler/////////////////////////////////
        List<String> listHead = new ArrayList<>();
        HashMap<String, BloodGlucoseMeasurements> listChild = null;
        ///////////////////////////////////////////////////////////////////////7

        //Fetch all measurements from/to date
        List<BloodGlucoseMeasurements> l = new ArrayList<>();

        l.addAll(BloodGlucoseMeasurements.find(BloodGlucoseMeasurements.class, "_date >= ? and _date <= ?", Long.toString(startDate.getTimeInMillis()), Long.toString(endDate.getTimeInMillis())));

        Calendar currentCalendar = startDate;
        listHead.add(sdf.format(currentCalendar));

        for (BloodGlucoseMeasurements b:l) {
            if (!sdf.format(currentCalendar.getTimeInMillis()).equals(sdf.format(b.getDate().getTimeInMillis()))){
                currentCalendar = b.getDate();
                listHead.add(sdf.format(currentCalendar.getTimeInMillis()));
                listChild.put(sdf.format(currentCalendar.getTimeInMillis()), b);
            } else {
                listChild.put(sdf.format(currentCalendar.getTimeInMillis()), b);
            }
        }
    }
}
