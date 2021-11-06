package com.example.weekviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.WeekView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private CalendarEvent.WeekViewSimpleAdapter viewSimpleAdapter;
    private WeekView weekView;
    private List<CalendarEvent> displaylist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weekView=findViewById(R.id.weekView);

        initWeekView();
        viewSimpleAdapter = new CalendarEvent.WeekViewSimpleAdapter();
        weekView.setAdapter(viewSimpleAdapter);
        String start="2021-11-06 12:00:00";
        String end="2021-11-06 14:00:00";
        //Time set in WeekViewEntity onCreateEntity
        CalendarEvent calendarEvent=new CalendarEvent(1,"Eric",start,end,"Test","#37C8A5");
        displaylist.add(calendarEvent);
        viewSimpleAdapter.submitList(displaylist);

        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        weekView.scrollToDateTime(calendar);
    }

    private void initWeekView(){
        final String[] weekLabels={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        weekView.setShowNowLine(true);

        weekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public void onSetNumberOfDays(int i) {

            }

            @NotNull
            @Override
            public String interpretDate(@NotNull Calendar calendar) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.ENGLISH);
                String weekday = weekdayNameFormat.format(calendar.getTime());
                switch (weekday){
                    case "Sun":
                        weekday=weekLabels[0];
                        break;
                    case "Mon":
                        weekday=weekLabels[1];
                        break;
                    case "Tue":
                        weekday=weekLabels[2];
                        break;
                    case "Wed":
                        weekday=weekLabels[3];
                        break;
                    case "Thu":
                        weekday=weekLabels[4];
                        break;
                    case "Fri":
                        weekday=weekLabels[5];
                        break;
                    case "Sat":
                        weekday=weekLabels[6];
                        break;
                }
                SimpleDateFormat format = new SimpleDateFormat("d", Locale.ENGLISH);
                return format.format(calendar.getTime())+"\n"+weekday;
            }

            @NotNull
            @Override
            public String interpretTime(int hour) {
                return String.format("%02d:00", hour);
            }
        });
      }
}