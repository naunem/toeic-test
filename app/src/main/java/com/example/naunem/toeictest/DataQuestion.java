package com.example.naunem.toeictest;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by HCD-Fresher036 on 1/12/2017.
 */

public class DataQuestion {
    Context mContext;
    String question;
    String a, b, c, d;
    ArrayList<QuestionListening> data = new ArrayList<>();

    public DataQuestion(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<QuestionListening> getDataReading() throws IOException {
        String str = "";
        InputStream is = mContext.getResources().openRawResource(R.raw.questionreading);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            while (!(str = reader.readLine()).trim().isEmpty()) {
                question = str;
                a = reader.readLine();
                b = reader.readLine();
                c = reader.readLine();
                d = reader.readLine();
                data.add(new QuestionListening(question, a, b, c, d));
            }
        }
        reader.close();
        is.close();
        return data;
    }

    public ArrayList<QuestionListening> getDataListening() throws IOException {
        String str = "";
        InputStream is = mContext.getResources().openRawResource(R.raw.questionlistening);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            while (!(str = reader.readLine()).trim().isEmpty()) {
                question = str;
                a = reader.readLine();
                b = reader.readLine();
                c = reader.readLine();
                d = reader.readLine();
                data.add(new QuestionListening(question, a, b, c, d));
            }
        }
        reader.close();
        is.close();
        return data;
    }

    public QuestionListening getQuestionById(int number, ArrayList<QuestionListening> data) {
        return data.get(number);
    }
}
