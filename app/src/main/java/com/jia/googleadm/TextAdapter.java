package com.jia.googleadm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TextAdapter extends BaseAdapter {
    private Context mContext;
    private List<TextModel> mList;

    public TextAdapter(List<TextModel> list, Context context){
        this.mList = list;
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_text, parent, false);

        ImageView image = view.findViewById(R.id.item_text_image);
        TextView tv = view.findViewById(R.id.item_text_tv);

        TextModel textModel = mList.get(position);
        tv.setText(textModel.getName());
        if(textModel.getUrl().equals("url")){
            image.setBackgroundResource(R.mipmap.ic_launcher);
        }else{
            GlideUtil.setImageView(mContext,textModel.getUrl(),image);
        }
        return view;
    }
}
