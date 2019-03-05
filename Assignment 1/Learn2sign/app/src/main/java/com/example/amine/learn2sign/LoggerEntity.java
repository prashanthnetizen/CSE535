package com.example.amine.learn2sign;

import android.os.Environment;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerEntity {

    private static Logger logger;

    private static String classN;


    public static void loggerEntity(String className){
        Handler fileTarget = null;
        File logFile = null;
        try{
            File f = new File(Environment.getExternalStorageDirectory(), "Learn2Sign_Logs");
            if (!f.exists()) {
                f.mkdirs();
            }
/*            logFile = new File(Environment.getExternalStorageDirectory()+ "/Learn2Sign_Logs/",
                    "Group29_Application.log");
            if (!logFile.exists())
                logFile.createNewFile();*/

            Log.d("StrangerThings","Activity occccc");

            classN  = className;

            logger = Logger.getLogger(className);
            fileTarget = new FileHandler(Environment.getExternalStorageDirectory() + "/Learn2Sign_Logs/Group29_Application.log");
            logger.addHandler(fileTarget);
            fileTarget.setLevel(Level.ALL);
            fileTarget.setFormatter(new SimpleFormatter(){
                @Override
                public synchronized String format(LogRecord record) {
                    return "[ "+new Date(record.getMillis())+" ] "+ classN + " ["+record.getLevel()+"] - " +record.getMessage()+"\n";
                }
            });

        }catch(IOException io){
            io.printStackTrace();
        }

    }

    public static void logActivity(String tag, String message){
        logger.info(":: "+tag+" - "+message);
    }

    public static void modifyClassName(String className){
        classN = className;
    }


}
