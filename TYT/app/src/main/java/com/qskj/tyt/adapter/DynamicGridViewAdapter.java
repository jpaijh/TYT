package com.qskj.tyt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qskj.tyt.R;
import com.qskj.tyt.view.dynamicgridview.BaseDynamicGridAdapter;

import java.util.List;

/**
 * 首页：DynamicGridView的数据适配器
 * Created by 赵 鑫 on 2015/8/25.
 */
public class DynamicGridViewAdapter extends BaseDynamicGridAdapter {
    public DynamicGridViewAdapter(Context context, List<ItemHomeGridView> items, int columnCount) {
        super(context, items, columnCount);
    }

    public static class ItemHomeGridView {
        private int textRes; // 标题资源
        private int imgRes; // 图片资源

        public ItemHomeGridView(int textRes, int imgRes) {
            this.imgRes = imgRes;
            this.textRes = textRes;
        }

        public int getImgRes() {
            return imgRes;
        }

        public void setImgRes(int imgRes) {
            this.imgRes = imgRes;
        }

        public int getTextRes() {
            return textRes;
        }

        public void setTextRes(int textRes) {
            this.textRes = textRes;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_home_gridview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ItemHomeGridView item = (ItemHomeGridView) getItem(position);
        holder.build(item.getTextRes(), item.getImgRes());
        return convertView;
    }

    private class ViewHolder {
        private TextView titleText;
        private ImageView image;

        private ViewHolder(View view) {
            titleText = (TextView) view.findViewById(R.id.item_title);
            image = (ImageView) view.findViewById(R.id.item_img);
        }

        void build(int textRes, int imgRes) {
            titleText.setText(textRes);
            image.setImageResource(imgRes);
        }
    }
}
