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

import java.util.ArrayList;

/**
 * Created by HCD-Fresher036 on 1/10/2017.
 */

public class ListenningItemAdapter extends RecyclerView.Adapter<ListenningItemAdapter.ItemView> {

    ArrayList<QuestionItem> list;
    ArrayList<Question> arrList;
    private Context context;
    updatechoi check;
    int thedo;

    public void setCheck(updatechoi check) {
        this.check = check;
    }

    public ListenningItemAdapter(ArrayList<QuestionItem> list, Context context) {
        this.list = list;
        this.context = context;
        arrList = DataAnswer.getMang();
    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_listening, parent, false);
        return new ItemView(v);
    }

    @Override
    public void onBindViewHolder(final ItemView holder, final int position) {
        QuestionItem data = list.get(position);
        holder.tv.setText(data.getQuestion());
        holder.image.setImageBitmap(data.getBitmap());
        holder.a.setText(data.getA());
        holder.b.setText(data.getB());
        holder.c.setText(data.getC());
        holder.d.setText(data.getD());

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
            thedo=0;
            for (int i=0;i<list.size();i++)
            if(null!=list.get(i).getAnswer()) {
                thedo++;
            }
            check.check(thedo);
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
                    int k=getLayoutPosition();
                    if (isChecked) {
                        if(null==list.get(k).getAnswer()) {
                            thedo++;
                            check.check(thedo);
                        }
                        switch (i) {
                            case R.id.a:
                                list.get(k).setAnswer("A");
                                break;
                            case R.id.b:
                                list.get(k).setAnswer("B");
                                break;
                            case R.id.c:
                                list.get(k).setAnswer("C");
                                break;
                            case R.id.d:
                                list.get(k).setAnswer("D");
                                break;
                            default:
                                break;
                        }
                    }
                }
            });
        }
    }
    public interface updatechoi{
        void check(int number);
    }
}
