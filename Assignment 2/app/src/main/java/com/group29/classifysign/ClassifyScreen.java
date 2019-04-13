package com.group29.classifysign;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ClassifyScreen extends AppCompatActivity {

    @BindView(R.id.button2)
    Button chooseFile;

    @BindView(R.id.button)
    Button predictButton;

    @BindView(R.id.result_col)
    LinearLayout resultBox;

    @BindView(R.id.result_about)
    TextView about;

    @BindView(R.id.result_father)
    TextView father;

    @BindView(R.id.spinner)
    Spinner mSpinner;
    
    private double features[] = null;
    private Map <String,Uri> fileNames = new HashMap<String,Uri>();
    private ArrayAdapter<String> adapter = null;

    private static final int READ_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_screen);
        ButterKnife.bind(this);
        Log.d("TEST",chooseFile.toString());
        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        DecisionTree.getInstance();

        chooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                features = null;
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                Log.d("TEST","takka");
                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });

        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    readCsvData(fileNames.get(mSpinner.getSelectedItem()));
                    if (features != null) {
                        resultBox.setVisibility(View.VISIBLE);
                        if (!DecisionTree.rootTree(features)) {
                            about.setVisibility(View.VISIBLE);
                            father.setVisibility(View.GONE);
                        } else {
                            about.setVisibility(View.GONE);
                            father.setVisibility(View.VISIBLE);
                        }
                    }
                }catch(IOException iox){
                    iox.printStackTrace();
                }
            }
        });

    }

    public void readCsvData(Uri uri) throws IOException {
        CSVReader reader = new CSVReaderBuilder(new InputStreamReader(
                getContentResolver().openInputStream(uri))).withSkipLines(1).build();
        String[] nextLine;
        features = new double[22]; // 22 is hardcoded as we know the number of features in prior
        while ((nextLine = reader.readNext()) != null) {
            features[0] += Double.parseDouble(nextLine[3]);
            features[1] += Double.parseDouble(nextLine[4]);
            features[2] += Double.parseDouble(nextLine[6]);
            features[3] += Double.parseDouble(nextLine[7]);
            features[4] += Double.parseDouble(nextLine[9]);
            features[5] += Double.parseDouble(nextLine[10]);
            features[6] += Double.parseDouble(nextLine[12]);
            features[7] += Double.parseDouble(nextLine[13]);
            features[8] += Double.parseDouble(nextLine[15]);
            features[9] += Double.parseDouble(nextLine[16]);
            features[10] += Double.parseDouble(nextLine[18]);
            features[11] += Double.parseDouble(nextLine[19]);
            features[12] += Double.parseDouble(nextLine[21]);
            features[13] += Double.parseDouble(nextLine[22]);
            features[14] += Double.parseDouble(nextLine[24]);
            features[15] += Double.parseDouble(nextLine[25]);
            features[16] += Double.parseDouble(nextLine[27]);
            features[17] += Double.parseDouble(nextLine[28]);
            features[18] += Double.parseDouble(nextLine[30]);
            features[19] += Double.parseDouble(nextLine[31]);
            features[20] += Double.parseDouble(nextLine[33]);
            features[21] += Double.parseDouble(nextLine[34]);
        }
        for(int i = 0; i<22; i++){
            features[i] = features[i]/reader.getLinesRead();
            Log.d("PRASH",""+features[i]);
        }
    }

    private void reset(){
        resultBox.setVisibility(View.GONE);
    }

    private void updateDropDownItems(String name){
        adapter.add(name);
        adapter.setNotifyOnChange(true);
        mSpinner.setSelection(adapter.getCount());

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
                    Log.i("PRASH", "Uri: " + uri.toString());
                }
            }
        }catch(Exception ioe){
            ioe.printStackTrace();
        }
    }
}
