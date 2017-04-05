package com.qskj.tyt.view.CalenderView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.qskj.tyt.R;
import com.qskj.tyt.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarVPopwindow extends BasePopupWindow {
    private Context context;
    private String date;
    private Date startDate;
    private Date endDate;
    private int number;
    private GetDaySelectListener mdaylistener;
    private CalendarView mcalendarview;
    private ImageButton calendarLeft;
    private TextView calendarCenter;
    private ImageButton calendarRight;
    private SimpleDateFormat format;

    public CalendarVPopwindow(Context context, int width, int height, Date startDate, Date endDate, int number,
                              GetDaySelectListener getdayselecylistener) {
        super(LayoutInflater.from(context).inflate(R.layout.popwindow_calendar, null), width, height);
        this.context = context;
        mdaylistener = getdayselecylistener;
        this.startDate = startDate;
        this.endDate = endDate;
        this.number = number;
    }

    @Override
    public void initViews() {
        mcalendarview = (CalendarView) findViewById(R.id.calendar);
        calendarLeft = (ImageButton) findViewById(R.id.calendarLeft);
        calendarCenter = (TextView) findViewById(R.id.calendarCenter);
        calendarRight = (ImageButton) findViewById(R.id.calendarRight);
        format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse("2015-01-01");

            mcalendarview.setCalendarData(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //获取日历中年月 ya[0]为年，ya[1]为月
        String[] ya = mcalendarview.getYearAndmonth().split("-");
        calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
    }

    @Override
    public void initEvents() {

        calendarLeft.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //点击上一月 同样返回年月
                String leftYearAndmonth = mcalendarview.clickLeftMonth(startDate);
                if (leftYearAndmonth != null) {
                    String[] ya = leftYearAndmonth.split("-");
                    calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
                } else {
                    ToastUtil.showToast(context, "结束时间不能早于开始时间");
                }

            }
        });

        calendarRight.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //点击下一月
                String rightYearAndmonth = mcalendarview.clickRightMonth(endDate);
                if (rightYearAndmonth != null) {
                    String[] ya = rightYearAndmonth.split("-");
                    calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
                } else {
                    ToastUtil.showToast(context, "开始时间不能晚于结束时间");
                }

            }
        });

        //设置控件监听，可以监听到点击的每一天
        mcalendarview.setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void OnItemClick(Date selectedStartDate, Date selectedEndDate, Date downDate) {
                if (!mcalendarview.isSelectMore()) {
                    //Toast.makeText(getApplicationContext(), format.format(selectedStartDate)+"到"+format.format(selectedEndDate), Toast.LENGTH_SHORT).show();
                    switch (number) {
                        case 1:
                            mdaylistener.getstartDay(downDate);
                            dismiss();
                            break;
                        case 2:
                            mdaylistener.getendDay(downDate);
                            dismiss();
                            break;
                        default:
                            break;
                    }
                    //Toast.makeText(getApplicationContext(), format.format(downDate), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void init() {

    }

    public interface GetDaySelectListener {
        void getstartDay(Date date);

        void getendDay(Date date);
    }

}
