package com.example.amine.learn2sign;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.IpSecManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.facebook.stetho.Stetho;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.HttpClient;

import static com.example.amine.learn2sign.LoginActivity.INTENT_SERVER_ADDRESS;
import static com.example.amine.learn2sign.LoginActivity.INTENT_TIME_WATCHED;
import static com.example.amine.learn2sign.LoginActivity.INTENT_TIME_WATCHED_VIDEO;
import static com.example.amine.learn2sign.LoginActivity.INTENT_URI;
import static com.example.amine.learn2sign.LoginActivity.INTENT_WORD;

public class PracticeActivity extends AppCompatActivity {

    @BindView(R.id.togglePractice)
    RadioGroup togglePractice;

    @BindView(R.id.vd_learn)
    RadioButton vd_learn;

    @BindView(R.id.vd_practice)
    RadioButton vd_practice;

    @BindView(R.id.practiceVideo)
    Button recordPrac;

    @BindView(R.id.cancelPractice)
    Button cancelPrac;

    @BindView(R.id.uploadPractice)
    Button uploadPrac;

    @BindView(R.id.pt_video_learn)
    VideoView learnVideo;

    @BindView(R.id.pt_recorded_video)
    VideoView recordVideo;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    
    @BindView(R.id.sp_states)
    Spinner states;
    
    @BindView(R.id.sp_ip_address2)
    Spinner ip_address;

    SharedPreferences sharedPreferences;

    long timeStarted = 0;
    long timeStartedReturn = 0;
    String returnedURI;
    private String oldState;

    private final static String APP_NAME = "Group29";
    private final String VIDEO_RES_PATH_PREFIX = "android.resource://";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);

        cancelPrac.setVisibility(View.GONE);
        uploadPrac.setVisibility(View.GONE);
        recordVideo.setVisibility(View.GONE);
        ratingBar.setVisibility(View.GONE);

        LoggerEntity.modifyClassName(PracticeActivity.class.getName());

        String statesArray[] = getResources().getStringArray(R.array.spinner_words);

        states.setSelection(new Random().nextInt(statesArray.length));

        togglePractice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==vd_learn.getId()) {
                    LoggerEntity.logActivity("CLICK_INFO","User clicked on Learn mode.");
                    finish();
                } else if ( checkedId==vd_practice.getId()) {
                    Toast.makeText(getApplicationContext(),"Practice Mode ON",Toast.LENGTH_SHORT).show();
                    LoggerEntity.logActivity("CLICK_INFO","User clicked on Practice mode again.");
                }
            }
        });

        states.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String state = states.getSelectedItem().toString();
                LoggerEntity.logActivity("CLICK_INFO","User clicked on a state to practice : "+state);
                String path="";
                try{
                    if(oldState != state){
                        timeStarted = System.currentTimeMillis();
                        oldState = state;
                        path=VIDEO_RES_PATH_PREFIX + getPackageName()+"/"+getResources().getIdentifier(state.toLowerCase(),"raw",getPackageName());
                        Uri uri = Uri.parse(path);
                        learnVideo.setVideoURI(uri);
                        learnVideo.start();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(mediaPlayer!=null)
                {
                    mediaPlayer.start();

                }

            }
        };

        learnVideo.setOnCompletionListener(onCompletionListener);
        recordVideo.setOnCompletionListener(onCompletionListener);



    }


    @OnClick(R.id.practiceVideo)
    public void recordVideo() {
        if( ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ) {

            // Permission is not granted
            // Should we show an explanation?

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        101);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


        if ( ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ) {

            // Permission is not granted
            // Should we show an explanation?


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        } else {
            // Permission has already been granted
            File f = new File(Environment.getExternalStorageDirectory(), "Learn2Sign");

            if (!f.exists()) {
                f.mkdirs();
            }

            LoggerEntity.logActivity("CLICK_INFO","To record to practise for word : "+states.getSelectedItem());

            timeStarted = System.currentTimeMillis() - timeStarted;

            Intent t = new Intent(this,VideoActivity.class);
            t.putExtra(INTENT_WORD,states.getSelectedItem().toString());
            t.putExtra(INTENT_TIME_WATCHED, timeStarted);
            startActivityForResult(t,9999);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==9999 && resultCode == 8888){
            if(data.hasExtra(INTENT_URI) && data.hasExtra(INTENT_TIME_WATCHED_VIDEO)) {
                returnedURI = data.getStringExtra(INTENT_URI);
                timeStartedReturn = data.getLongExtra(INTENT_TIME_WATCHED_VIDEO,0);

                recordVideo.setVisibility(View.VISIBLE);
                recordPrac.setVisibility(View.GONE);
                uploadPrac.setVisibility(View.VISIBLE);
                cancelPrac.setVisibility(View.VISIBLE);
                states.setEnabled(false);
                //rb_practice.setEnabled(false);
                recordVideo.setVideoURI(Uri.parse(returnedURI));
                recordVideo.start();
                learnVideo.start();
                ratingBar.setVisibility(View.VISIBLE);
            }
        } else if (requestCode==9999 && resultCode == 7777){

            returnNormal();

        }
    }

    private void returnNormal(){
        cancelPrac.setVisibility(View.GONE);
        uploadPrac.setVisibility(View.GONE);
        recordVideo.setVisibility(View.GONE);
        ratingBar.setVisibility(View.GONE);
        recordPrac.setVisibility(View.VISIBLE);
        learnVideo.start();
        states.setEnabled(true);
    }

    @OnClick(R.id.cancelPractice)
    public void deleteRecordedVideo(){

        LoggerEntity.logActivity("CLICK_INFO","User clicks on Re-Record button for word : "+states.getSelectedItem());
        AlertDialog.Builder cancelDialogBuilder = new AlertDialog.Builder(this);
        cancelDialogBuilder.setTitle("Record Again");
        cancelDialogBuilder.setMessage("This is will delete the Current Recorded Video. Are you sure?");
        cancelDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoggerEntity.logActivity("CLICK_INFO","User decided to delete the recorded video for word : "+states.getSelectedItem());
                        new File(returnedURI).delete();
                        recordVideo();
                    }
                }
        );

        cancelDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        cancelDialogBuilder.create().show();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LoggerEntity.logActivity("CLICK_INFO","User presses back button on Practice Screen");
        finish();
    }

    @OnClick(R.id.uploadPractice)
    public void uploadPracticeVideo() {
        try {
            final File srcFile = new File(returnedURI);
            File practiceVideo = new File(srcFile.getParent()+"/"+APP_NAME + "_" + states.getSelectedItem() + "_" + Math.round(ratingBar.getRating() * 2) + ".mp4");
            LoggerEntity.logActivity("CLICK_INFO","User decided to upload the Recorded Video.");
            RequestParams params = new RequestParams();
            AsyncHttpClient client = new AsyncHttpClient();
            FileUtils.copyFile(srcFile, practiceVideo);

            params.put("uploaded_file", practiceVideo);
            client.post("http://"+ip_address.getSelectedItem()+"/upload_video_performance.php", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200) {
                        srcFile.delete();
                        Toast.makeText(PracticeActivity.this, "Practice Video Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        LoggerEntity.logActivity("SUCCESS","The Practice video uploaded successfully");
                        returnNormal();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }catch(IOException io){
            Log.e("IOException",io.getMessage());
            io.printStackTrace();
        }


    }


    //Menu Item for logging out
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.practice_logout:
                final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("ALERT");
                alertDialog.setMessage("Logging out will delete all the data!");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                getApplication().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE).edit().clear().apply();
                                File f = new File(Environment.getExternalStorageDirectory(), "Learn2Sign");
                                if (f.isDirectory())
                                {
                                    String[] children = f.list();
                                    for (int i = 0; i < children.length; i++)
                                    {
                                        new File(f, children[i]).delete();
                                    }
                                }

                                f = new File(Environment.getExternalStorageDirectory(), "Learn2Sign_logs");
                                if (f.isDirectory())
                                {
                                    String[] children = f.list();
                                    for (int i = 0; i < children.length; i++)
                                    {
                                        new File(f, children[i]).delete();
                                    }
                                }
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                finish();

                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
