package com.doublewillow.okidea.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doublewillow.okidea.R;
import com.doublewillow.okidea.module.Person;

import java.util.List;

/**
 * @author zhouyou
 * @version 1.0
 * @desc
 * @date 2017/7/19 17:17
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewHolder> {

    private List<Person> mListPerson;

    public MyAdapter(List<Person> mListPerson) {
        this.mListPerson = mListPerson;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        viewHolder viewHolder = new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        holder.mtvName.setText(mListPerson.get(position).getName());
        holder.mtvPhone.setText(mListPerson.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return mListPerson.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        TextView mtvName;
        TextView mtvPhone;

        public viewHolder(View itemView) {
            super(itemView);
            mtvName = (TextView) itemView.findViewById(R.id.tv_name);
            mtvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
        }
    }
}
