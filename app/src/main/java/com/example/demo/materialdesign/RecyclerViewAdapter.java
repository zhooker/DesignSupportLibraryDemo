package com.example.demo.materialdesign;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.R;

import java.util.List;

/**
 * Created by zhooker on 2016/10/22.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<String> dataList;
    private Activity mActivity;
    //标志避免java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid item position…
    private boolean defaultIndex = false;

    RecyclerViewAdapter(Activity activity, List<String> dataList) {
        this.dataList = dataList;
        this.mActivity = activity;
    }

    public void addAll(List<String> list) {
        if(defaultIndex && dataList.size() > 0)
            dataList.clear();
        dataList.addAll(list);
        defaultIndex = false;
        notifyDataSetChanged();
    }

    public void clear(){
        //dataList.clear();
        defaultIndex = true;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_material_content_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).split(",")[0]);
        holder.content.setText(dataList.get(position).split(",")[1]);
        if (position % 2 == 0) {
            holder.showImage.setBackgroundResource(R.mipmap.show_img1);
        } else {
            holder.showImage.setBackgroundResource(R.mipmap.show_img2);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        ImageView showImage;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            showImage = (ImageView) itemView.findViewById(R.id.showImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getLayoutPosition() % 2 == 0)
                        DetailActivity.startActivity(mActivity, getLayoutPosition(), showImage);
                    else
                        DetailActivity2.startActivity(mActivity, getLayoutPosition(), showImage);
                }
            });
        }
    }
}
