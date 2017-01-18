package com.example.naunem.toeictest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by HCD-Fresher036 on 1/12/2017.
 */
public class FinishActivity extends AppCompatActivity implements View.OnClickListener {
    Button finish;
    TextView point;

    public void mapped() {
        finish = (Button) findViewById(R.id.back);
        point = (TextView) findViewById(R.id.point);
        finish.setOnClickListener(this);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_exam);
        int score=getIntent().getExtras().getInt("Point");
        mapped();
        point.setText(String.valueOf(score) + "/495");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ChoiceActivity.class);
        startActivity(intent);
        finish();
    }
}
