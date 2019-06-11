package com.group29.bcardp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import org.apache.commons.math3.stat.StatUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BradyActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;

    @BindView(R.id.browse)
    Button browse;

    @BindView(R.id.detectButton)
    Button detect;

    @BindView(R.id.predictButton)
    Button predict;

    @BindView(R.id.spinner)
    Spinner mSpinner;

    @BindView(R.id.graph)
    GraphView mGraphView;

    private double dataSet[] = null;
    private Map<String,Uri> fileNames = new HashMap<String,Uri>();
    private ArrayAdapter<String> adapter = null;
    private AlertDialog mAlertDialog, predictAlert, numberPickDialog;
    private double smoothedData[] = null;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brady);
        ButterKnife.bind(this);
        scrolls();
        createNumberPickerDialog();
        ClassifyTree.getInstance();

        mAlertDialog = new AlertDialog.Builder(BradyActivity.this).create();
        predictAlert = new AlertDialog.Builder(BradyActivity.this).create();
        mAlertDialog.setTitle("Detect Activity");
        predictAlert.setTitle("Predict Activity");
        mAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        predictAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });

        detect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startTime = System.currentTimeMillis();
                    readCsvData(fileNames.get(mSpinner.getSelectedItem()));
                    plotForGraph();
                }catch(Exception io){
                    io.printStackTrace();
                }
            }
        });
        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPickDialog.show();
            }
        });
    }

    private void  smoothedData() {
        smoothedData  =  new double[30];
        int i;
        Double sum;
        for (i=0;i<smoothedData.length - 2;i++){
            sum = dataSet[i] + dataSet[i+1] + dataSet[i+2];
            smoothedData[i] = sum/3;
        }
        smoothedData[i] = smoothedData[i+1] = smoothedData[i-1];
    }
    private void scrolls(){
        mGraphView.getViewport().setScalable(true);
        mGraphView.getViewport().setScrollable(true);
        mGraphView.getViewport().setScalableY(true);
        mGraphView.getViewport().setScrollableY(true);
        mGraphView.setTitle("Heart Rate Variations");
        mGraphView.getGridLabelRenderer().setHorizontalAxisTitle("Time in Minutes");
        mGraphView.getGridLabelRenderer().setVerticalAxisTitle("Heart beats per minute");
    }


    private boolean calculatePredictionResult(int index){
        double[] feature = new double[2];
        feature[0] = StatUtils.mean(dataSet,index-1,4);
        feature[1] = StatUtils.variance(dataSet,feature[0],index-1,4);
        Log.d("ASD", "Mean : "+feature[0]+"\nVariance: "+feature[1]);
        return ClassifyTree.rootTree(feature);
    }

    private void createNumberPickerDialog(){
        final NumberPicker pickNum= new NumberPicker(BradyActivity.this);
        pickNum.setMinValue(1);
        pickNum.setMaxValue(20);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(BradyActivity.this);
        dialogBuilder.setTitle("Pick the Starting Minute for Prediction");
        dialogBuilder.setMessage("Pick a number to serve as the starting index for the 4-minute range.");
        dialogBuilder.setPositiveButton("Predict", new DialogInterface.OnClickListener() {
            long totalTime = 0;
            String message;
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startTime = System.currentTimeMillis();
                if(calculatePredictionResult(pickNum.getValue())){
                    message = "Bradycardia likely to occur";
                } else {
                    message = "Bradycardia will not occur";
                }
                message += "\nExecution Time : "+ (System.currentTimeMillis() - startTime) + " ms";
                predictAlert.setMessage(message);
                predictAlert.show();
            }
        });
        dialogBuilder.setView(pickNum);
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        numberPickDialog = dialogBuilder.create();
    }

    private void plotForGraph(){
        String message;
        long totalTime = 0;
        int  i,count=0;
        double fp=0,fn=0;
        smoothedData();
        LineGraphSeries<DataPoint> graphSeries = new LineGraphSeries<DataPoint>();
        PointsGraphSeries<DataPoint> pointGraphs = new PointsGraphSeries<>();
        for(i=1;i<=smoothedData.length ;i++){
            graphSeries.appendData(new DataPoint(i, smoothedData[i-1]),true,30,false);
            if(smoothedData[i-1] <= 60){
                pointGraphs.appendData(new DataPoint(i,smoothedData[i-1]),true,30,false);
                count++;
            }

            if(smoothedData[i-1] > 60 && dataSet[i-1] <= 60 ){
                fp++;
            }
            if(smoothedData[i-1] <= 60 && dataSet[i-1] > 60 ){
                fn++;
            }
        }
        pointGraphs.setColor(Color.RED);
        mGraphView.removeAllSeries();
        mGraphView.setScrollContainer(true);
        mGraphView.addSeries(graphSeries);
        mGraphView.addSeries(pointGraphs);
        mGraphView.getViewport().setMaxX(31);

        totalTime = (System.currentTimeMillis() - startTime);
        // Displaying Results in Dialog
        if(count != 0) {
            message = "BradyCardia Points Detected : " + count;
            message += "\nFalse Positives Rate: " + df2.format(fp / 30);
            message += "\nFalse Negatives Rate: " + df2.format(fn / 30);
        }
        else {
            message = "No Bradycardia Detected.";
        }
        message += "\nExecution Time: " + totalTime + " ms";
        mAlertDialog.setMessage(message);
        mAlertDialog.show();
    }

    public void readCsvData(Uri uri) throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(
                getContentResolver().openInputStream(uri)));
        String[] nextLine;
        dataSet = new double[30];
        int i = 0;
        while ((nextLine = reader.readNext()) != null) {
             dataSet[i] = Double.parseDouble(nextLine[1]);
             i++;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData)  {

        String currentFileName;

        try {

            if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                // The document selected by the user won't be returned in the intent.
                // Instead, a URI to that document will be contained in the return intent
                // provided to this method as a parameter.
                // Pull that URI using resultData.getData().
                Uri uri = null;
                if (resultData != null) {
                    uri = resultData.getData();
                    currentFileName = new File(uri.getPath()).getName();
                    fileNames.put(currentFileName,uri);
                    updateDropDownItems(currentFileName);
                }
            }

        }   catch(Exception iox){
                iox.printStackTrace();
        }
    }


    private void updateDropDownItems(String name){
        adapter.add(name);
        adapter.setNotifyOnChange(true);
        mSpinner.setSelection(adapter.getCount());
    }



}
