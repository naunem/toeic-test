package com.example.naunem.toeictest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HCD-Fresher036 on 1/6/2017.
 */

public class ReadingFragment extends Fragment implements View.OnClickListener {
    public int number;
    TextView question;
    private boolean flag;
    FloatingActionButton next, previnus;
    Answer ans;
    ImageView img;
    RadioGroup rGroup;
    RadioButton rA;
    RadioButton rB;
    RadioButton rC;
    RadioButton rD;
    ArrayList<Question> mang;
    DataQuestion data;
    ArrayList<QuestionListening> arrayList;
    TextView countQuestion;

    public void setAns(Answer ans) {
        this.ans = ans;
    }

    public void mapped(View view) {
        question = (TextView) view.findViewById(R.id.question);
        img = (ImageView) view.findViewById(R.id.image);
        rGroup = (RadioGroup) view.findViewById(R.id.group);
        Toolbar too = (Toolbar) getActivity().findViewById(R.id.toolbar);
        countQuestion = (TextView) too.findViewById(R.id.question);
        rA = (RadioButton) rGroup.findViewById(R.id.a);
        rB = (RadioButton) rGroup.findViewById(R.id.b);
        rC = (RadioButton) rGroup.findViewById(R.id.c);
        rD = (RadioButton) rGroup.findViewById(R.id.d);
        next = (FloatingActionButton) view.findViewById(R.id.next);
        previnus = (FloatingActionButton) view.findViewById(R.id.previous);
        next.setOnClickListener(this);
        previnus.setOnClickListener(this);
        flag = false;
        data = new DataQuestion(getActivity());

        try {
            arrayList = data.getDataReading();
            question.setText(arrayList.get(number).getQuestion());
            rA.setText(arrayList.get(number).getA());
            rB.setText(arrayList.get(number).getB());
            rC.setText(arrayList.get(number).getC());
            rD.setText(arrayList.get(number).getD());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_reading, container, false);

        mang = getArguments().getParcelableArrayList("mang");
        number = getArguments().getInt("number");
        mapped(view);
        if (mang.get(number).getCorrect() != null) {
            upDateChoice();
        }
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = (RadioButton) rGroup.findViewById(i);

                // This puts the value (true/false) into the variable
                boolean isChecked = false;
                if (null != checkedRadioButton) {
                    isChecked = checkedRadioButton.isChecked();
                }
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked) {

                    switch (i) {
                        case R.id.a:
                            mang.get(number).setCorrect("A");
                            DataAnswer.setMang(mang);
                            break;
                        case R.id.b:
                            mang.get(number).setCorrect("B");
                            DataAnswer.setMang(mang);
                            break;
                        case R.id.c:
                            mang.get(number).setCorrect("C");
                            DataAnswer.setMang(mang);
                            break;
                        case R.id.d:
                            mang.get(number).setCorrect("D");
                            DataAnswer.setMang(mang);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        try {
            data.getDataReading();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                number++;
                countQuestion.setText(String.valueOf(number + 1) + "/100");
                question.setText(arrayList.get(number).getQuestion());
                rA.setText(arrayList.get(number).getA());
                rB.setText(arrayList.get(number).getB());
                rC.setText(arrayList.get(number).getC());
                rD.setText(arrayList.get(number).getD());
                if (!upDateChoice()) {
                    changeStatus();
                }

                break;
            case R.id.previous:
                if (number == 0) break;
                else {
                    --number;
                    countQuestion.setText(String.valueOf(number + 1) + "/100");
                    question.setText(arrayList.get(number).getQuestion());
                    rA.setText(arrayList.get(number).getA());
                    rB.setText(arrayList.get(number).getB());
                    rC.setText(arrayList.get(number).getC());
                    rD.setText(arrayList.get(number).getD());
                    upDateChoice();
                    break;
                }
        }
    }

    private boolean upDateChoice() {
        String getChoice = mang.get(number).getCorrect();
        if (null == getChoice) {
            return false;
        }
        switch (getChoice.toString()) {
            case "A":
                rA.setChecked(true);
                return true;
            case "B":
                rB.setChecked(true);
                return true;
            case "C":
                rC.setChecked(true);
                return true;
            case "D":
                rD.setChecked(true);
                return true;
            default:
                return false;
        }
    }

    private void changeStatus() {

        rGroup.clearCheck();
        rA.setChecked(false);
        rB.setChecked(false);
        rC.setChecked(false);
        rD.setChecked(false);

    }

    public void returnQuestion(int number) {
        this.number = number;
//        try {
//            PlayWithRawFiles();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public interface Answer {
        void getaAnswer(int number);
    }
}
