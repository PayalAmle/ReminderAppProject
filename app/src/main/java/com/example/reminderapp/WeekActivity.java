package com.example.reminderapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.ParcelUuid;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.reminderapp.Utils.LetterImageView;


public class WeekActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;
    public static SharedPreferences sharedPreferences;
    public static String SEL_DAY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        setUIViews();
        initToolbar();
        setUpListView();

    }
    private void setUIViews(){
        toolbar=(Toolbar)findViewById(R.id.ToolbarWeek);
        listView=(ListView)findViewById(R.id.lvWeek1);
        sharedPreferences=getSharedPreferences("MY_DAY",MODE_PRIVATE);

    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Week");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setUpListView(){
        String[] week=getResources().getStringArray(R.array.Week);
        WeekAdapter adapter=new WeekAdapter(this,R.layout.week_singleitem,week);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0: {
                        startActivity(new Intent(WeekActivity.this, DayDetails.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Monday").apply();
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(WeekActivity.this, DayDetails.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Tuesday").apply();
                        break;
                    }
                    case 2: {
                        startActivity(new Intent(WeekActivity.this, DayDetails.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Wednesday").apply();
                        break;
                    }
                    case 3: {
                        startActivity(new Intent(WeekActivity.this, DayDetails.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Thursday").apply();
                        break;
                    }

                    case 4: {
                        startActivity(new Intent(WeekActivity.this, DayDetails.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Friday").apply();
                        break;
                    }
                    case 5: {
                        startActivity(new Intent(WeekActivity.this, DayDetails.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Saturday").apply();
                        break;
                    }
                    case 6: {
                        startActivity(new Intent(WeekActivity.this, DayDetails.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Sunday").apply();
                        break;
                    }
                    default:
                        break;


                }
            }
        });

    }
    public class WeekAdapter extends ArrayAdapter{
        private int resource;
        private LayoutInflater lf;
        private String[] week=new String[]{};

        public WeekAdapter(@NonNull Context context, int resource,String[] objects) {
            super(context, resource,objects);
            this.resource=resource;
            this.week=objects;
            lf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                holder=new ViewHolder();
                convertView=lf.inflate(resource,null);
                holder.ivLogo=(LetterImageView)convertView.findViewById(R.id.ivLetter);
                holder.tvWeek=(TextView)convertView.findViewById(R.id.tvWeek);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }
            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(week[position].charAt(0));
            holder.tvWeek.setText(week[position]);
            return convertView;
        }
        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvWeek;
        }


            public boolean onOptionsItemSelected(MenuItem item){
            switch (item.getItemId()){
                case android.R.id.home:{
                    onBackPressed();
                    return true;
                }
            }
            return false;
        }


    }
}
