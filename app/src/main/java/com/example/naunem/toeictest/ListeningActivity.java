package com.example.naunem.toeictest;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HCD-Fresher036 on 1/10/2017.
 */

public class ListeningActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    ListenningItemAdapter adapter;
    Toolbar toolbar;
    ImageView img;
    ArrayList<QuestionListening> list;
    ArrayList<QuestionListening> arr;
    TextView time;
    TextView count;
    Button submit;
    MediaPlayer mPlayer = null;
    DataQuestion data;
    private int[] vitoidayeuem = new int[]{R.drawable.part11, R.drawable.part12};
    //private int[] khongcoyeuduockhong = new int[]{R.raw.part11, R.raw.part12};

    public void mapped() {
        recyclerView = (RecyclerView) findViewById(R.id.scroll);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        count = (TextView) toolbar.findViewById(R.id.question);
        time = (TextView) toolbar.findViewById(R.id.time);
        submit = (Button) toolbar.findViewById(R.id.submit);
        submit.setOnClickListener(this);
        data = new DataQuestion(this);
        arr = new ArrayList<>();
        try {
            list = data.getDataListening();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter = new ListenningItemAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }

    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listening_activity);

        mapped();
        new CountTimeThread(time, 2700).start();

        try {
            readFileMp3();
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
                        mPlayer.stop();
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

    public void readFileMp3() throws IOException {
//        img.setImageResource(vitoidayeuem[number - 1]);
        mPlayer = MediaPlayer.create(this, R.raw.audio);
        mPlayer.start();
    }

    @Override
    public void onClick(View view) {
        int numberCorrect = 0;
        int point = 5;
        Intent intent = new Intent(this, FinishActivity.class);
        try {
            ArrayList<Question> data = new MockDataAnswer(this).getDataListening();

            for (int i = 0; i < list.size(); i++) {
                if (null == list.get(i).getAnswer()) ;
                else if (data.get(i).getCorrect().equals(list.get(i).getAnswer())) {
                    numberCorrect++;
                }
            }
            if (numberCorrect < 7) ;
            else if (numberCorrect <= 92) {
                Log.d("cccl", "ccccl" + numberCorrect);
                point += 5 * (numberCorrect - 6);
            } else point = 495;
            intent.putExtra("Point", point);
            startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayer.stop();
        startActivity(intent);
    }
}
