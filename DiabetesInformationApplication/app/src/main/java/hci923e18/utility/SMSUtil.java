package hci923e18.utility;

import android.support.annotation.NonNull;
import android.telephony.SmsManager;

import java.text.SimpleDateFormat;

import hci923e18.database.BloodGlucoseMeasurements;
import hci923e18.database.Profile;

public class SMSUtil {

    /**
     * sendSMS method used to preparer and send SMS with input arguments
     * @param bloodGlucoseMeasurements bloodGlucoseMeasurements object used to format the message
     */
    public static void sendSMS(BloodGlucoseMeasurements bloodGlucoseMeasurements){
        String mobileNumber = fetchProfileParentPhoneNumber();
        String formattedMessage = formatSMSMessageBloodGlucose(bloodGlucoseMeasurements);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+45" + mobileNumber, null, formattedMessage, null, null);
    }

    /**
     * sendSMS method used to preparer and send SMS with input arguments
     * @param carbohydrate string containing the amount carbs
     * @param bloodGlucoseLevel string containing the blood glucose level
     * @param units string containing the amount of insulin units
     */
    public static void sendSMS(String carbohydrate, String bloodGlucoseLevel, String units){
        String mobileNumber = fetchProfileParentPhoneNumber();
        String formattedMessage = formatSMSMessageInculinCalc(carbohydrate, bloodGlucoseLevel, units);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+45" + mobileNumber, null, formattedMessage, null, null); //4560453035
    }

    /**
     * formatSMSMessageBloodGlucose used to format the message for blood glucose content
     * @param bloodGlucoseMeasurements bloodGlucoseMeasurements object used to format the message
     * @return string containing the formatted message
     */
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

    /**
     *  formatSMSMessageBloodGlucose used to format the message for insulin calculations content
     * @param carbohydrate string containing the amount carbs
     * @param bloodGlucoseLevel string containing the blood glucose level
     * @param units string containing the amount of insulin units
     * @return string containing the formatted message
     */
    public static String formatSMSMessageInculinCalc(String carbohydrate, String bloodGlucoseLevel, String units){

        String formattedSMS;
        formattedSMS = "Jeg har lige udregnet at jeg skal tage " + units + " enheder insulin, da jeg har spist "
                + carbohydrate + " gram kulhydrater og har et blodsukker på: " + bloodGlucoseLevel;

        return formattedSMS;
    }
    public static String fetchProfileParentPhoneNumber(){
        Profile profile = Profile.find(Profile.class, "ID = ?", "1").get(0);
        return profile.get_phoneNumber();
    }
}
