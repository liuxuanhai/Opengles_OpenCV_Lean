package com.example.mvvm.selfdrawview.viewgroup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvvm.selfdrawview.R;

import java.util.List;

/**
 * author : 90589
 * date   : 2020/6/18
 * desc   : 描述
 */
public class FlowerAdapter extends BaseAdapter {
    private List<Petal> mPetals;

    public FlowerAdapter(List<Petal> petals) {
        mPetals = petals;
    }

    @Override
    public int getCount() {
        return mPetals.size();
    }

    @Override
    public Object getItem(int position) {
        return mPetals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.flower_item, parent, false);
        ImageView iv = view.findViewById(R.id.id_pic);
        iv.setImageResource(mPetals.get(position).resId);
        TextView tv = view.findViewById(R.id.id_info);
        tv.setText(mPetals.get(position).info);
        return view;
    }
}


