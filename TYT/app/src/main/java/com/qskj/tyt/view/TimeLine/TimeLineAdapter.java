package com.qskj.tyt.view.TimeLine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.qskj.tyt.R;

import java.util.List;

/**
 * Created by zhaoxin
 * on 15/10/29.
 */
public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ViewHolder> {
    private List<TimeLineEntity> mDataSet;

    public TimeLineAdapter(List<TimeLineEntity> models) {
        this.mDataSet = models;
    }

    @Override
    public int getItemViewType(int position) {
        final int size = mDataSet.size() - 1;
        if (size == 0)
            return ItemType.ATOM;
        else if (position == 0)
            return ItemType.START;
        else if (position == size)
            return ItemType.END;
        else return ItemType.NORMAL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_time_line, viewGroup, false);
        return new ViewHolder(v, viewType);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mDate;
        private TimeLineMarker mMarker;

        public ViewHolder(View itemView, int type) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.item_time_line_txt);
            mDate = (TextView) itemView.findViewById(R.id.item_time_line_date);
            mMarker = (TimeLineMarker) itemView.findViewById(R.id.item_time_line_mark);
            if (type == ItemType.ATOM) {
                mMarker.setBeginLine(null);
                mMarker.setEndLine(null);
            } else if (type == ItemType.START) {
                mMarker.setBeginLine(null);
            } else if (type == ItemType.END) {
                mMarker.setEndLine(null);
            }

        }

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mName.setText(mDataSet.get(i).getName());
        viewHolder.mDate.setText(mDataSet.get(i).getDate());

        int nodeStatus = mDataSet.get(i).getNodeStatus();
        Context context = mDataSet.get(i).getContext();
        if (nodeStatus != 0) { // 完成状态
            viewHolder.mMarker.setMarkerDrawable(context.getResources().getDrawable(R.drawable.shape_timeline_complete_marker));
            viewHolder.mName.setTextColor(context.getResources().getColor(R.color.green_a700));
            viewHolder.mDate.setTextColor(context.getResources().getColor(R.color.green_a700));
            int type = viewHolder.getItemViewType();
            if (type == ItemType.ATOM) {
                viewHolder.mMarker.setBeginLine(null);
                viewHolder.mMarker.setEndLine(null);
            } else if (type == ItemType.START) {
                viewHolder.mMarker.setBeginLine(null);
                viewHolder.mMarker.setEndLine(context.getResources().getDrawable(R.color.green_a700));
            } else if (type == ItemType.END) {
                viewHolder.mMarker.setBeginLine(context.getResources().getDrawable(R.color.green_a700));
                viewHolder.mMarker.setEndLine(null);
            } else if (type == ItemType.NORMAL) {
                viewHolder.mMarker.setBeginLine(context.getResources().getDrawable(R.color.green_a700));
                viewHolder.mMarker.setEndLine(context.getResources().getDrawable(R.color.green_a700));
            }
        }

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
