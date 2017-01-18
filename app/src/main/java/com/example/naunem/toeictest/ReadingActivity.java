package com.example.naunem.toeictest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HCD-Fresher036 on 1/6/2017.
 */

public class ReadingActivity extends AppCompatActivity implements View.OnClickListener, NumberQuestionAdapter.MyOnClickListener {
    NumberQuestionAdapter adapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    TextView time;
    TextView question;
    Button submit;
    Context mContext;

    public void mapped() {
        recyclerView = (RecyclerView) findViewById(R.id.scroll);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        question = (TextView) toolbar.findViewById(R.id.question);
        time = (TextView) toolbar.findViewById(R.id.time);
        submit = (Button) toolbar.findViewById(R.id.submit);
        submit.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NumberQuestionAdapter(this, 100);
        adapter.setMyOnClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading_activity);

        mapped();

        CountTimeThread count = new CountTimeThread(time, 4500);
        count.start();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ReadingFragment fragment = new ReadingFragment();
        Bundle b = new Bundle();
        b.putInt("number", 0);
        b.putParcelableArrayList("mang", DataAnswer.createArray());
        fragment.setArguments(b);
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void returnQuestion(int number) {
        question.setText(String.valueOf(number) + "/100");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ReadingFragment fragment = new ReadingFragment();
        Bundle b = new Bundle();
        b.putInt("number", number);
        b.putParcelableArrayList("mang", DataAnswer.getMang());
        fragment.setArguments(b);
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        //submit
        int numberCorrect = 0;
        int point = 5;
        Intent intent = new Intent(this, FinishActivity.class);
        try {
            ArrayList<Question> data = new MockDataAnswer(this).getDataReading();
            ArrayList<Question> arrList = DataAnswer.getMang();
            for (int i = 0; i < 100; i++) {
                if (null == arrList.get(i).getCorrect()) ;
                else if (data.get(i).getCorrect().equals(arrList.get(i).getCorrect())) {
                    numberCorrect++;
                }
            }
            if (numberCorrect < 10) ;
            else if (numberCorrect <= 96) {
                Log.d("cccl", "ccccl" + numberCorrect);
                point += 5 * (numberCorrect - 9);
            } else point = 495;
            intent.putExtra("Point", point);
            startActivity(intent);
            finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Message");
        builder.setMessage("Do you want to exit ?")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO: handle the OK
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(int position) {
        returnQuestion(position);
    }
}
