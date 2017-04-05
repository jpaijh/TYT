package com.qskj.tyt.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qskj.tyt.R;
import com.qskj.tyt.entity.T_OrderFormDetailsAttachmentsEntity;

import java.util.List;


/**
 * 订单详情-附件 数据适配器
 * Created by 赵 鑫 on 2015/10/29.
 */
public class T_OrderFormDetailsAttachmentsAdapter extends RecyclerView.Adapter<T_OrderFormDetailsAttachmentsAdapter.ViewHolder> {

    private List<T_OrderFormDetailsAttachmentsEntity> list;

    public T_OrderFormDetailsAttachmentsAdapter(List<T_OrderFormDetailsAttachmentsEntity> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.t_item_orderform_details_attachments, viewGroup, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_count;
        public TextView tv_attachmentName;
        public TextView tv_fileName;

        public ViewHolder(final View itemView) {
            super(itemView);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
            tv_attachmentName = (TextView) itemView.findViewById(R.id.tv_attachmentName);
            tv_fileName = (TextView) itemView.findViewById(R.id.tv_fileName);

            // 整个条目点击事件 TODO 打开文件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        T_OrderFormDetailsAttachmentsEntity entity = list.get(position);

        viewHolder.tv_count.setText("（" + (position + 1) + "）");
        viewHolder.tv_attachmentName.setText(entity.getAttachmentName());
        viewHolder.tv_fileName.setText(entity.getFileName());
    }
}

