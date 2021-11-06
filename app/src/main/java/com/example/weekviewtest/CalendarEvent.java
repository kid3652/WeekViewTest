package com.example.weekviewtest;

import android.graphics.Color;
import android.graphics.RectF;
import android.util.Log;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEntity;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarEvent {

    private long id;
    private String user_name;
    private String start;
    private String end;
    private String subject;
    private String group_color_code;

    public static final String FULL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public CalendarEvent(long id, String user_name,String start,String end, String subject, String group_color_code) {
        this.id = id;
        this.user_name = user_name;
        this.start = start;
        this.end = end;
        this.subject = subject;
        this.group_color_code = group_color_code;
    }

    public static class WeekViewSimpleAdapter extends WeekView.PagingAdapter<CalendarEvent>{

        public WeekViewSimpleAdapter() {

        }

        @NotNull
        @Override
        public WeekViewEntity onCreateEntity(CalendarEvent item) {

            WeekViewEntity.Style style = new WeekViewEntity.Style.Builder()
                    .setBackgroundColor(Color.parseColor(item.group_color_code))
                    .build();


            Calendar startTime = Calendar.getInstance();
            startTime.setTime(toDate(item.start));
            Calendar endTime = Calendar.getInstance();
            endTime.setTime(toDate(item.end));

            WeekViewEntity build= new WeekViewEntity.Event.Builder(item)
                    .setId(item.id)
                    .setTitle(item.user_name)
                    .setStartTime(startTime)
                    .setEndTime(endTime)
                    .setSubtitle(item.subject)
                    .setAllDay(false)
                    .setStyle(style)
                    .build();
            return build;
        }

        @Override
        public void onLoadMore(@NotNull Calendar startDate, @NotNull Calendar endDate) {
            super.onLoadMore(startDate, endDate);
        }

        @Override
        public void onEmptyViewClick(@NotNull Calendar time) {
            super.onEmptyViewClick(time);
        }

        @Override
        public void onEmptyViewLongClick(@NotNull Calendar time) {
            super.onEmptyViewLongClick(time);
        }

        @Override
        public void onEventClick(CalendarEvent data) {
            super.onEventClick(data);
        }

        @Override
        public void onEventClick(CalendarEvent data, @NotNull RectF bounds) {
            super.onEventClick(data, bounds);
        }

        @Override
        public void onEventLongClick(CalendarEvent data) {
            super.onEventLongClick(data);
        }

        @Override
        public void onEventLongClick(CalendarEvent data, @NotNull RectF bounds) {
            super.onEventLongClick(data, bounds);
        }

        @Override
        public void onRangeChanged(@NotNull Calendar firstVisibleDate, @NotNull Calendar lastVisibleDate) {
            super.onRangeChanged(firstVisibleDate, lastVisibleDate);
        }

        public Date toDate(String date) {
            SimpleDateFormat dateFormat;
            try {
                if(!date.contains(" ")) date=date+" 00:00:00";
                dateFormat = new SimpleDateFormat(FULL_DATE_TIME_FORMAT);
                dateFormat.setLenient(false);
                return dateFormat.parse(date);
            } catch (Exception e) {
                try {
                    dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    return dateFormat.parse(date);
                } catch (Exception e1) {
                    return null;
                }
            }
        }
    }

    public long getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

//    public CalendarDateModel getDate() {
//        return date;
//    }

    public String getSubject() {
        return subject;
    }

    public String getGroup_color_code() {
        return group_color_code;
    }


}