package com.example.mvvm.opengl2_0.comm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.opengl2_0.R;

import java.util.List;

/**
 * author : 90589
 * date   : 2020/6/17
 * desc   : 描述
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    private static final String TAG = "TolyRvAdapter";
    private List<String> mData;
    private Context mContext;
    public RvAdapter(List<String> data) {
        mData = data;
    }

    @NonNull
    @Override//将item布局文件与ViewHolder结合
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_text, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mItemTV.setText(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnClick.onClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private ItemOnClick itemOnClick;

    public void setItemOnClick(ItemOnClick itemOnClick){
        this.itemOnClick=itemOnClick;
    };


    /**
     * ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mItemTV;
        public MyViewHolder(View itemView) {
            super(itemView);
            mItemTV = itemView.findViewById(R.id.id_tv_qq_name);
        }
    }

    public interface ItemOnClick {
        void onClick(View view ,int position);
    }

}
