package hci923e18.diabetesinformationapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hci923e18.database.BloodGlucoseMeasurements;
import hci923e18.database.Profile;

public class GeneratePDF extends AppCompatActivity {

    public Button download;
    public Button send;
    public EditText email;
    public List<BloodGlucoseMeasurements> bloodGlucoseMeasurements = new ArrayList<>();
    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM 'Kl.' HH:mm");
    final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM \n'Kl.' HH:mm");
    private int reds = 0;
    private int yellows = 0;
    private int greens = 0;
    private GraphView graphView;
    private LineGraphSeries<DataPoint> mSeries;
    private PointsGraphSeries<DataPoint> redSeries;
    private PointsGraphSeries<DataPoint> yellowSeries;
    private PointsGraphSeries<DataPoint> greenSeries;
    private LineGraphSeries<DataPoint> lowestSeries;
    private LineGraphSeries<DataPoint> highestSeries;
    private Profile profile;
    private Bitmap bitmap;
    private Uri documentUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_pdf);

        download = findViewById(R.id.generate_download);
        send = findViewById(R.id.generate_send);
        email = findViewById(R.id.generate_email);
        graphView = findViewById(R.id.graph_generate);

        bloodGlucoseMeasurements = fetchMonthMeasurements();
        profile = fetchProfile();
        calculateRatio();
        populateGraph(bloodGlucoseMeasurements);
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(mSeries.getLowestValueX());
        graphView.getViewport().setMaxX(mSeries.getHighestValueX());
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(mSeries.getLowestValueY());
        graphView.getViewport().setMaxY(mSeries.getHighestValueY());
        graphView.getGridLabelRenderer().setHorizontalLabelsVisible(true);
        graphView.getGridLabelRenderer().setVerticalLabelsVisible(true);

        // set date label formatter
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return "\n" + sdf1.format(value);
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + " mmol/L";
                }
            }
        });
        graphView.getGridLabelRenderer().setNumHorizontalLabels(4); // only 3 because of the space

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap = graphView.takeSnapshot();
                createPDF();
                Toast.makeText(GeneratePDF.this, "PDF lavet", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = null;
                try {
                    mail = email.getText().toString();
                } catch (Exception e) {

                }
                if (mail != null && !mail.isEmpty() ){
                    bitmap = graphView.takeSnapshot();
                    createPDF();
                    sendEmail(mail);
                    Toast.makeText(GeneratePDF.this, "PDF lavet og sendt til: " + mail, Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }

    /**
     * Creates the pdf file
     */
    private void createPDF() {
        PdfDocument document = new PdfDocument();
        int pageNumber = 1;
        int y = 25;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, pageNumber).create();

        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(12);

        for (BloodGlucoseMeasurements b: bloodGlucoseMeasurements) {
            if (y < 800){
                canvas.drawText(constructString(b), 10, y, paint);
                y = y + 15;
            } else {
                document.finishPage(page);
                y = 25;
                pageNumber = pageNumber + 1;
                pageInfo = new PdfDocument.PageInfo.Builder(595, 842, pageNumber).create();
                page = document.startPage(pageInfo);
                canvas = page.getCanvas();
                paint = new Paint();
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawPaint(paint);
                paint.setColor(Color.BLACK);
                paint.setTextSize(12);
                canvas.drawText(constructString(b), 10, y, paint);
                y = y + 15;
            }

        }
        document.finishPage(page);

        //Create image
        pageNumber = pageNumber + 1;
        pageInfo = new PdfDocument.PageInfo.Builder(595, 842, pageNumber).create();

        page = document.startPage(pageInfo);
        canvas = page.getCanvas();
        paint = new Paint();
        paint.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawPaint(paint);
        bitmap = RotateBitmap(bitmap, 90);
        bitmap = Bitmap.createScaledBitmap(bitmap, 595, 842,true);
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(bitmap, 0,0,null);
        document.finishPage(page);
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        String pdfName = "Blodsukker_malinger_"
                + sdf.format(Calendar.getInstance().getTime()) + ".pdf";
        File outputFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), pdfName);
        outputFile.setReadable(true);
        outputFile.setWritable(true);

        try {
            outputFile.createNewFile();
            OutputStream out = new FileOutputStream(outputFile);
            document.writeTo(out);
            document.close();
            out.close();
            documentUri = Uri.fromFile(outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rotates a bitmap
     * @param source The bitmap
     * @param angle The angle to rotate
     * @return A rotated bitmap
     */
    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    /**
     * Construct a string for printing
     * @param bloodGlucoseMeasurement A BloodMeasurement object
     * @return The formatted string
     */
    private String constructString(BloodGlucoseMeasurements bloodGlucoseMeasurement){
        String s = "";
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Måling fortaget d. ");
        stringBuilder.append(sdf.format(bloodGlucoseMeasurement.getDate().getTime()));
        stringBuilder.append(" Måling: ");
        stringBuilder.append(bloodGlucoseMeasurement.get_glucoseLevel());
        stringBuilder.append(" Type: ");
        stringBuilder.append(bloodGlucoseMeasurement.get_type());
        stringBuilder.append(" Måltids type: ");
        switch (bloodGlucoseMeasurement.get_category()){
            case 0: stringBuilder.append("morgenmad ");
            break;
            case 1: stringBuilder.append("middagsmad ");
            break;
            case 2: stringBuilder.append("aftensmad ");
            break;
        }
        stringBuilder.append(" Markering: ");
        switch (bloodGlucoseMeasurement.get_beforeAfter()){
            case 0: stringBuilder.append("ingen markering");
            break;
            case 1: stringBuilder.append("før mad");
            break;
            case 2: stringBuilder.append("efter mad");
            break;
        }

        s = stringBuilder.toString();
        return s;
    }

    /**
     * Method used to fetch the measurements for the current week
     * @return A list of blood glucose measurements for the current week
     */
    public List<BloodGlucoseMeasurements> fetchMonthMeasurements(){

        List<BloodGlucoseMeasurements> l = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        calendar.add(Calendar.MONTH, -1);
        long firstDayOfWeekTimestamp = calendar.getTimeInMillis();
        l.addAll(BloodGlucoseMeasurements.find(BloodGlucoseMeasurements.class, "_date >= ? ORDER BY _date", Long.toString(firstDayOfWeekTimestamp)));
        return l;
    }

    /**
     * Fetches the profile from the database
     * @return The profile object
     */
    private Profile fetchProfile(){
        Profile p;
        try {
            p = Profile.find(Profile.class, "ID = ?", "1").get(0);
        } catch (Exception e) {
            p = new Profile();
            p.set_idealBloodGlucoseLevel(5.5);
            p.set_insulinDuration(3.5);
            p.set_totalDailyInsulinConsumption(30.0);
            p.set_upperBloodGlucoseLevel(15.0);
            p.set_lowerBloodGlucoseLevel(3.0);
            p.set_beforeBloodGlucoseLevel(8.0);
            p.set_afterBloodGlucoseLevel(8.0);

            //Save default to DB
            p.save();
        }
        return p;
    }

    /**
     * Calculates the ratio between low, normal and high measurements
     */
    private void calculateRatio(){
        yellows = 0;
        reds = 0;
        greens = 0;

        for (BloodGlucoseMeasurements b: bloodGlucoseMeasurements) {
            if (b.get_glucoseLevel() > profile.get_upperBloodGlucoseLevel()){
                yellows++;
            }else if(b.get_glucoseLevel() < profile.get_lowerBloodGlucoseLevel()){
                reds++;
            }
            else{
                greens++;
            }
        }


    }

    /**
     * Creates the different series that are displayed on the graph
     * @param bloodList A list of BloodMeasurements
     */
    private void populateGraph(List<BloodGlucoseMeasurements> bloodList){
        redSeries = null;
        yellowSeries = null;
        greenSeries = null;


        int i = 0;
        int count = bloodList.size();
        DataPoint[] values = new DataPoint[count];
        for (BloodGlucoseMeasurements b:bloodList) {
            DataPoint temp = new DataPoint(b.getDate().getTime(),b.get_glucoseLevel());
            values[i] = temp;
            i++;
        }

        //create graph
        mSeries = new LineGraphSeries<>(values);
        mSeries.setColor(Color.BLUE);

        DataPoint[] lowest = new DataPoint[2];
        lowest[0] = new DataPoint(mSeries.getLowestValueX(), profile.get_lowerBloodGlucoseLevel());
        lowest[1] = new DataPoint(mSeries.getHighestValueX(), profile.get_lowerBloodGlucoseLevel());
        lowestSeries = new LineGraphSeries<>(lowest);
        lowestSeries.setColor(Color.RED);
        graphView.addSeries(lowestSeries);

        DataPoint[] highest = new DataPoint[2];
        highest[0] = new DataPoint(mSeries.getLowestValueX(), profile.get_upperBloodGlucoseLevel());
        highest[1] = new DataPoint(mSeries.getHighestValueX(), profile.get_upperBloodGlucoseLevel());
        highestSeries = new LineGraphSeries<>(highest);
        highestSeries.setColor(Color.YELLOW);
        graphView.addSeries(highestSeries);

        graphView.addSeries(mSeries);

        DataPoint[] redValues = new DataPoint[reds];
        DataPoint[] yellowValues = new DataPoint[yellows];
        DataPoint[] greenValues = new DataPoint[greens];
        int r = 0;
        int y = 0;
        int g = 0;

        for (BloodGlucoseMeasurements b:bloodList) {
            if (b.get_glucoseLevel() > profile.get_upperBloodGlucoseLevel()){
                DataPoint temp = new DataPoint(b.getDate().getTime(),b.get_glucoseLevel());
                yellowValues[y] = temp;
                y++;
            }else if(b.get_glucoseLevel() < profile.get_lowerBloodGlucoseLevel()){
                DataPoint temp = new DataPoint(b.getDate().getTime(),b.get_glucoseLevel());
                redValues[r] = temp;
                r++;
            }
            else{
                DataPoint temp = new DataPoint(b.getDate().getTime(),b.get_glucoseLevel());
                greenValues[g] = temp;
                g++;
            }
        }

        redSeries = new PointsGraphSeries<>(redValues);
        redSeries.setShape(PointsGraphSeries.Shape.RECTANGLE);
        redSeries.setColor(Color.RED);
        graphView.addSeries(redSeries);
        yellowSeries = new PointsGraphSeries<>(yellowValues);
        yellowSeries.setShape(PointsGraphSeries.Shape.TRIANGLE);
        yellowSeries.setColor(Color.YELLOW);
        graphView.addSeries(yellowSeries);
        greenSeries = new PointsGraphSeries<>(greenValues);
        greenSeries.setShape(PointsGraphSeries.Shape.POINT);
        greenSeries.setColor(Color.GREEN);
        graphView.addSeries(greenSeries);
    }

    /**
     * Send an email
     * @param email The string used for the message
     */
    protected void sendEmail(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("vnd.android.cursor.dir/email");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email} );
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "DIAbetesInformationApplication - Blodsukker målinger");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Vedhæftet er pdf med blodsukker målinger");
        emailIntent.putExtra(Intent.EXTRA_STREAM, documentUri);
        // FOLLOWING STATEMENT CHECKS WHETHER THERE IS ANY APP THAT CAN HANDLE OUR EMAIL INTENT
        startActivity(Intent.createChooser(emailIntent,
                "Send Email Using: "));
    }
}
