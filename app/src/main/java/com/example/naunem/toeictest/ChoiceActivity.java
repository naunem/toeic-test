package com.example.naunem.toeictest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by HCD-Fresher036 on 1/10/2017.
 */

public class ChoiceActivity extends AppCompatActivity implements View.OnClickListener {
    Button listenning;
    Button reading;

    public void mapped() {
        listenning = (Button) findViewById(R.id.listening);
        reading = (Button) findViewById(R.id.reading);
        listenning.setOnClickListener(this);
        reading.setOnClickListener(this);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
       mapped();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.listening :
                Intent intent = new Intent(this, ListeningActivity.class);
                startActivity(intent);
                break;
            case R.id.reading :
                Intent intent1 = new Intent(this, ReadingActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
