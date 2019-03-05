package com.example.reminderapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class Entrypge extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrypge);
        setupUIViews();
        initToolbar();
        setUpListView();
    }

    private void setupUIViews() {
        toolbar = (Toolbar) findViewById(R.id.ToolbarMain);
        listView = (ListView) findViewById(R.id.lvmain1);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reminder App");
    }
    private void setUpListView(){
        String[] title=getResources().getStringArray(R.array.Main);
        String[] description=getResources().getStringArray(R.array.Description);
        SimpleAdapter sp=new SimpleAdapter(this,title,description);
        listView.setAdapter(sp);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0: {
                        Intent intent=new Intent(Entrypge.this,WeekActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:{break;}
                    default:{break;}
                }
            }
        });
    }
    public class SimpleAdapter extends BaseAdapter {
        private Context mcon;
        private LayoutInflater lf;
        private TextView title, description;
        private String[] titleArray;
        private String[] descArray;
        private ImageView imgv;

        public SimpleAdapter(Context context, String[] title, String[] description) {
            mcon = context;
            titleArray = title;
            descArray = description;
            lf = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int i) {
            return titleArray[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = lf.inflate(R.layout.entrypge_singleitem, null);
            }
            title = (TextView) view.findViewById(R.id.tvMain);
            description = (TextView) view.findViewById(R.id.tvDescription);
            imgv = (ImageView) view.findViewById(R.id.ivMain);
            title.setText(titleArray[i]);
            description.setText(descArray[i]);
            if (titleArray[i].equalsIgnoreCase("Timetable")) {
                imgv.setImageResource(R.drawable.tt1);
            } else if (titleArray[i].equalsIgnoreCase("Event")) {
                imgv.setImageResource(R.drawable.event1);
            }
            return view;
        }

    }


}
