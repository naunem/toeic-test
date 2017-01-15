package com.example.naunem.toeictest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HCD-Fresher036 on 1/6/2017.
 */

public class Question implements Parcelable{
    private int mNumber;
    private String mCorrect;

    protected Question(Parcel in) {
        mNumber = in.readInt();
        mCorrect = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        this.mNumber = number;
    }

    public String getCorrect() {
        return mCorrect;
    }

    public void setCorrect(String correct) {
        this.mCorrect = correct;
    }

    public Question(int number, String correct) {
        this.mNumber = number;
        this.mCorrect = correct;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mNumber);
        parcel.writeString(mCorrect);
    }
}
