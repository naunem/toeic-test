package com.example.naunem.toeictest;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by HCD-Fresher036 on 1/13/2017.
 */

public class MockDataAnswer {
    Context mContext;
    String answer;
    ArrayList<Question> data = new ArrayList<>();
    public MockDataAnswer(Context context){
        mContext=context;
    }
    public ArrayList<Question> getDataReading() throws IOException {
        String str = "";
        InputStream is = mContext.getResources().openRawResource(R.raw.answerreading);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            int i = 1;
            while (!(str = reader.readLine()).trim().isEmpty()) {
                answer = str;
                data.add(new Question(i, answer));
                i++;
            }
        }
        reader.close();
        is.close();
        return data;
    }

    public ArrayList<Question> getDataListening() throws IOException {
        String str = "";
        InputStream is = mContext.getResources().openRawResource(R.raw.answerlistenning);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            int i = 1;
            while (!(str = reader.readLine()).trim().isEmpty()) {
                answer = str;
                data.add(new Question(i, answer));
                i++;
            }
        }
        reader.close();
        is.close();
        return data;
    }
}
