package hci1025f19.utility;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import hci1025f19.diabetesinformationapplication.newBloodGlucoseLevelActivity;

/**
 * The TimePickerFragment1 class
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    static private int startHour;
    static private int startMin;
    static private int endHour;
    static private int endMin;
    static private EditText AtTime = null;
    private TimepickerInterface mCallback;
    newBloodGlucoseLevelActivity saveTime = new newBloodGlucoseLevelActivity();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);



        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        startHour = hourOfDay;
        startMin = minute;

        if (minute < 10) {
            String tempminute;
            tempminute = "0" + String.valueOf(minute);
            String time = String.valueOf(hourOfDay) + ":" + tempminute;
            mCallback.OnTimeSet(time);

        } else {
            String time = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
            mCallback.OnTimeSet(time);
        }
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (TimepickerInterface) activity;
        }
        catch (ClassCastException e) {
            Log.d("MyDialog", "Activity doesn't implement the ISelectedData interface");
        }
    }
}