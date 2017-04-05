package com.qskj.tyt.view.TimeLine;

import android.content.Context;

/**
 * 时间轴-流程名 实体类
 * Created by zhaoxin
 * on 15/10/29.
 */
public class TimeLineEntity {
    private String name; // 节点名称
    private String date; // 节点时间
    private int nodeStatus; // 节点状态：亮 或者 不亮
    private Context context;

    public TimeLineEntity(String name, int nodeStatus, Context context) {
        this.name = name;
        this.nodeStatus = nodeStatus;
        this.context = context;
    }

    public TimeLineEntity(String name, String date, int nodeStatus, Context context) {
        this.name = name;
        this.nodeStatus = nodeStatus;
        this.context = context;
        this.date = date;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(int nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
