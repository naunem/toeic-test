package com.example.naunem.toeictest;

import java.util.ArrayList;

/**
 * Created by HCD-Fresher036 on 1/11/2017.
 */

public class DataAnswer {
    public static ArrayList<Question> mang;

    public static ArrayList<Question> createArray() {
        mang = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            mang.add(new Question(i, null));
        }
        return mang;
    }

    public static void setMang(ArrayList<Question> mang) {
        DataAnswer.mang = mang;
    }

    public static ArrayList<Question> getMang() {
        return mang;
    }
}
