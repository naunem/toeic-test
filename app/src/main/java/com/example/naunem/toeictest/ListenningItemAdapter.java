package com.example.naunem.toeictest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by HCD-Fresher036 on 1/10/2017.
 */

public class ListenningItemAdapter extends RecyclerView.Adapter<ListenningItemAdapter.ItemView> {

    ArrayList<QuestionListening> list;
    ArrayList<Question> arrList;
    ArrayList<Question> data;
    private Context context;

    public ListenningItemAdapter(ArrayList<QuestionListening> list, Context context) {
        this.list = list;
        this.context = context;
        try {
            data = new MockDataAnswer(context).getDataListening();
        } catch (IOException e) {
            e.printStackTrace();
        }
        arrList = DataAnswer.getMang();
    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_listening, parent, false);
        return new ItemView(v);
    }

    @Override
    public void onBindViewHolder(final ItemView holder, final int position) {
        QuestionListening data = list.get(position);
        holder.tv.setText(data.getQuestion());
        holder.image.setImageBitmap(data.getBitmap());
        holder.a.setText(data.getA());
        holder.b.setText(data.getB());
        holder.c.setText(data.getC());
        holder.d.setText(data.getD());
        /*if(!data.getD().toString().trim().isEmpty()){
            holder.d.setVisibility(View.VISIBLE);
            holder.d.setText(data.getD());
            }
        else {
            holder.d.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }*/
        if (list.get(position).getAnswer() == null) {
            holder.rgroup.clearCheck();
            holder.a.setChecked(false);
            holder.b.setChecked(false);
            holder.c.setChecked(false);
            holder.d.setChecked(false);
        } else {
            if (list.get(position).getAnswer().equals("A")) holder.a.setChecked(true);
            if (list.get(position).getAnswer().equals("B")) holder.b.setChecked(true);
            if (list.get(position).getAnswer().equals("C")) holder.c.setChecked(true);
            if (list.get(position).getAnswer().equals("D")) holder.d.setChecked(true);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemView extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView image;
        RadioButton a, b, c, d;
        View line;
        RadioGroup rgroup;

        public ItemView(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.question);
            image = (ImageView) itemView.findViewById(R.id.image);
            a = (RadioButton) itemView.findViewById(R.id.a);
            b = (RadioButton) itemView.findViewById(R.id.b);
            c = (RadioButton) itemView.findViewById(R.id.c);
            d = (RadioButton) itemView.findViewById(R.id.d);
            rgroup = (RadioGroup) itemView.findViewById(R.id.group);
            line = (View) itemView.findViewById(R.id.line);
            rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    RadioButton checkedRadioButton = (RadioButton) rgroup.findViewById(i);
                    // This puts the value (true/false) into the variable
                    boolean isChecked = false;
                    if (null != checkedRadioButton) {
                        isChecked = checkedRadioButton.isChecked();
                    }
                    // If the radiobutton that has changed in check state is now checked...
                    if (isChecked) {
                        switch (i) {
                            case R.id.a:
                                list.get(getLayoutPosition()).setAnswer("A");
                                break;
                            case R.id.b:
                                list.get(getLayoutPosition()).setAnswer("B");
                                break;
                            case R.id.c:
                                list.get(getLayoutPosition()).setAnswer("C");
                                break;
                            case R.id.d:
                                list.get(getLayoutPosition()).setAnswer("D");
                                break;
                            default:
                                break;
                        }
                    }
                }
            });
        }
    }
}
