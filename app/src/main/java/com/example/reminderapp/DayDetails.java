package com.example.reminderapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.reminderapp.Utils.LetterImageView;

public class DayDetails extends AppCompatActivity {
    private ListView listView;
    private Toolbar toolbar;
    public static String[] Monday;
    public static String[] Tuesday;
    public static String[] Wednesday;
    public static String[] Thursday;
    public static String[] Friday;
    public static String[] Saturday;
    public static String[] Sunday;
    public static String[] Time1;
    public static String[] Time2;
    public static String[] Time3;
    public static String[] Time4;
    public static String[] Time5;
    public static String[] Time6;
    public static String[] Time7;
    private String[] PrefferedDay;
    private String[] PrefferedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_details);
        setupUIViews();
        initToolbar();
        setListView();
    }
    private void setupUIViews(){
        listView=(ListView)findViewById(R.id.lvDayDetail);
        toolbar=(Toolbar)findViewById(R.id.ToolbarDayDetail);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY,null));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setListView() {
        Monday = getResources().getStringArray(R.array.Monday);
        Tuesday = getResources().getStringArray(R.array.Tuesday);
        Wednesday = getResources().getStringArray(R.array.Wednesday);
        Thursday = getResources().getStringArray(R.array.Thursday);
        Friday = getResources().getStringArray(R.array.Friday);
        Saturday = getResources().getStringArray(R.array.Saturday);
        Sunday = getResources().getStringArray(R.array.Sunday);

        Time1 = getResources().getStringArray(R.array.time1);
        Time2 = getResources().getStringArray(R.array.time2);
        Time3 = getResources().getStringArray(R.array.time3);
        Time4 = getResources().getStringArray(R.array.time4);
        Time5 = getResources().getStringArray(R.array.time5);
        Time6 = getResources().getStringArray(R.array.time6);
        Time7=getResources().getStringArray(R.array.time7);

        String selected_day = WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY, null);

        if (selected_day.equalsIgnoreCase("Monday")) {
            PrefferedDay = Monday;
            PrefferedTime = Time1;
        }
        else if (selected_day.equalsIgnoreCase("Tuesday")) {
            PrefferedDay = Tuesday;
            PrefferedTime = Time2;
        }
        else if (selected_day.equalsIgnoreCase("Wednesday")) {
            PrefferedDay = Wednesday;
            PrefferedTime = Time3;
        }
        else if (selected_day.equalsIgnoreCase("Thursday")) {
            PrefferedDay = Thursday;
            PrefferedTime = Time4;
        }
        else if (selected_day.equalsIgnoreCase("Friday")) {
            PrefferedDay = Friday;
            PrefferedTime = Time5;
        }
        else if (selected_day.equalsIgnoreCase("Saturday")) {
            PrefferedDay = Saturday;
            PrefferedTime = Time6;
        }
        else {
            PrefferedDay=Sunday;
            PrefferedTime = Time7;
        }
        SimpleAdapter sadap=new SimpleAdapter(this,PrefferedDay,PrefferedTime);
        listView.setAdapter(sadap);
    }
    public class SimpleAdapter extends BaseAdapter {
        private Context mcon;
        private LayoutInflater lf;
        private TextView subject, time;
        private String[] subjectArray;
        private String[] timeArray;
        private LetterImageView ltrimgv;

        public SimpleAdapter(Context context, String[] subjectArray, String[] timeArray) {
            mcon = context;
           this.subjectArray = subjectArray;
            this.timeArray= timeArray;
            lf = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return subjectArray.length;
        }

        @Override
        public Object getItem(int i) {
            return subjectArray[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = lf.inflate(R.layout.day_details_single_item, null);
            }
            subject = (TextView) view.findViewById(R.id.tvSubDayDetails);
            time = (TextView) view.findViewById(R.id.tvTimeDayDetails);
            ltrimgv = (LetterImageView) view.findViewById(R.id.ivDayDetails);
            subject.setText(subjectArray[i]);
            time.setText(timeArray[i]);
            ltrimgv.setOval(true);
            ltrimgv.setLetter(subjectArray[i].charAt(0));

            return view;
        }
        }

    }
