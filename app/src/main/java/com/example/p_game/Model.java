package com.example.p_game;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Model {

    private AudioRecorder recorder;
    private String path;

    public Model() {
        recorder = new AudioRecorder("voices");
    }

    public void saveUserNameToFile(String userName, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("userName.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(userName);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public String readUserNameFromFile(Context context) {
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput("userName.txt");
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    protected void recordStart(){
        try {
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void recordStop(){
        try {
            recorder.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}