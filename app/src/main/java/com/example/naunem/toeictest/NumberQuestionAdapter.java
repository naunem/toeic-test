package com.example.naunem.toeictest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by HCD-Fresher036 on 1/6/2017.
 */

public class NumberQuestionAdapter extends RecyclerView.Adapter<NumberQuestionAdapter.ItemNumber> {

    private Context mContext;
    private int soluong;


    public NumberQuestionAdapter(Context context, int soluong) {
        this.mContext = context;
        this.soluong = soluong;
    }

    @Override
    public ItemNumber onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_number_question, parent, false);
        return new ItemNumber(view);
    }

    @Override
    public void onBindViewHolder(final ItemNumber holder, final int position) {
        holder.number.setText(position + 1 + "");
        holder.number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOnClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return soluong;
    }

    public class ItemNumber extends RecyclerView.ViewHolder {
        Button number;

        public ItemNumber(View itemView) {
            super(itemView);
            number = (Button) itemView.findViewById(R.id.number);
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myOnClickListener.onClick(getAdapterPosition());
                }
            });*/
        }
    }

    MyOnClickListener myOnClickListener;

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.myOnClickListener = myOnClickListener;
    }

    public interface MyOnClickListener {
        public void onClick(int position);
    }
}
