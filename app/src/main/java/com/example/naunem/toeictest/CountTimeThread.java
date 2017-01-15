package com.example.naunem.toeictest;

import android.widget.TextView;

/**
 * Created by HCD-Fresher036 on 1/11/2017.
 */

public class CountTimeThread extends Thread {
    TextView tv;
    int time;

    public CountTimeThread(TextView tv, int time) {
        this.tv = tv;
        this.time = time;
    }

    @Override
    public void run() {
        super.run();
        for (int i = time; i > 0  ; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final int finalI = i;
            tv.post(new Runnable() {
                @Override
                public void run() {
                    if (finalI % 60 < 10){
                        tv.setText(finalI / 60 + ":0" + finalI % 60);
                    }else{
                        tv.setText(finalI / 60 + ":" + finalI % 60);
                    }
                }
            });
        }
    }
}
