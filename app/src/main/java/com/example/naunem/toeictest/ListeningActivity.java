package com.example.naunem.toeictest;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class ListeningActivity extends AppCompatActivity implements View.OnClickListener ,ListenningItemAdapter.updatechoi{
    RecyclerView recyclerView;
    ListenningItemAdapter adapter;
    Toolbar toolbar;
    ArrayList<QuestionItem> list;
    TextView time;
    TextView count;
    Button submit;
    MediaPlayer mPlayer = null;
    DataQuestion data;
    private int[] data_image = new int[]{R.drawable.question1, R.drawable.question2, R.drawable.question3, R.drawable.question4, R.drawable.question5, R.drawable.question6,
            R.drawable.question7, R.drawable.question8, R.drawable.question9, R.drawable.question10};

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
        try {
            list = data.getDataListening();
            for (int i=0;i<10;i++){
                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), data_image[i]);
                list.get(i).setBitmap(largeIcon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter = new ListenningItemAdapter(list, this);
        adapter.setCheck(this);
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

        try {
            MockDataAnswer moi = new MockDataAnswer(ListeningActivity.this);
            ArrayList<Question> data = moi.getDataListening();
            for (int i = 0; i < list.size(); i++) {
                if (null == list.get(i).getAnswer()) ;
                else if (data.get(i).getCorrect().equals(list.get(i).getAnswer())) {
                    numberCorrect++;
                }
            }
            if (numberCorrect < 7) ;
            else if (numberCorrect <= 92) {
                point += 5 * (numberCorrect - 6);
            } else point = 495;
            Intent intent = new Intent(this, FinishActivity.class);
            intent.putExtra("Point", point);
            startActivity(intent);
            mPlayer.stop();
            startActivity(intent);
            finish();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void check(int number) {
        count.setText(String.valueOf(number) + "/100");
    }
}
