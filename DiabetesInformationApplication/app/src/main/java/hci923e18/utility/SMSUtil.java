package hci923e18.utility;

import android.support.annotation.NonNull;
import android.telephony.SmsManager;

import java.text.SimpleDateFormat;

import hci923e18.database.BloodGlucoseMeasurements;

public class SMSUtil {

    public static void sendSMS(BloodGlucoseMeasurements bloodGlucoseMeasurements){

        //TODO Fetch parent number from profile page?
        //TODO maybe include latest 3 readings ?
        String formattedMessage = formatSMSMessageBloodGlucose(bloodGlucoseMeasurements);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+4524636086", null, formattedMessage, null, null);
    }

    public static void sendSMS(String carbohydrate, String bloodGlucoseLevel, String units){

        String formattedMessage = formatSMSMessageInculinCalc(carbohydrate, bloodGlucoseLevel, units);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+4524636086", null, formattedMessage, null, null); //4560453035
    }

    public static String formatSMSMessageBloodGlucose(@NonNull BloodGlucoseMeasurements bloodGlucoseMeasurements){

        final SimpleDateFormat sdf = new SimpleDateFormat("'Kl.'HH:mm 'den' dd/MM");
        String formattedSMS;
        String bloodGlucoseLevel = bloodGlucoseMeasurements.get_glucoseLevel().toString();
        String bloodGlucoseTimestamp = sdf.format(bloodGlucoseMeasurements.getDate().getTime());
        String bloodGlucoseBeforeAfter;
        Integer bloodGlucoseBeforeAfterInt = bloodGlucoseMeasurements.get_beforeAfter();  // 0 = ingen markering, 1 = før mad, 2 = efter mad

        if (bloodGlucoseBeforeAfterInt == 0){
            bloodGlucoseBeforeAfter = "fastend. ";
        }else if(bloodGlucoseBeforeAfterInt == 1){
            bloodGlucoseBeforeAfter = "lige inden jeg skal spise. ";
        }else{
            bloodGlucoseBeforeAfter = "lige efter jeg har spist. ";
        }

        formattedSMS = "Jeg har lige taget en blodsukkermåling på: " + bloodGlucoseLevel + " mmol/L som jeg har taget "
                + bloodGlucoseBeforeAfter + "Målingen blivet taget: " + bloodGlucoseTimestamp;

        return formattedSMS;
    }

    public static String formatSMSMessageInculinCalc(String carbohydrate, String bloodGlucoseLevel, String units){

        String formattedSMS;
        formattedSMS = "Jeg har lige udregnet at jeg skal tage " + units + " enheder insulin, da jeg har spist "
                + carbohydrate + " gram kulhydrater og har et blodsukker på: " + bloodGlucoseLevel;

        return formattedSMS;
    }

}
