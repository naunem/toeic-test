package com.example.naunem.toeictest;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HCD-Fresher036 on 1/10/2017.
 */

public class QuestionItem {
    String question;
    Bitmap bitmap;
    String a, b, c, d;
    String answer;

    public QuestionItem(String question, String a, String b, String c, String d) {
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public QuestionItem(String question, Bitmap bitmap, String a, String b, String c, String d, String answer) {
        this.question = question;
        this.bitmap = bitmap;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String tv) {
        this.question = tv;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
    public QuestionItem(){

    }
}
