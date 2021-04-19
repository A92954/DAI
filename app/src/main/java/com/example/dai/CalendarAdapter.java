package com.example.dai;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class CalendarAdapter extends BaseAdapter{
    Context context;
    ArrayList<CalendarModel> calendarList;

    public CalendarAdapter(Context context, ArrayList<CalendarModel> calendarList){
        this.context = context;
        this.calendarList = calendarList;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.calendar_acti, parent, false);
        }
        TextView actiName, day;
        actiName = (TextView) convertView.findViewById(R.id.actiNameID);
        day = (TextView) convertView.findViewById(R.id.dayID);
        actiName.setText(calendarList.get(position).getActi_name());
        day.setText(calendarList.get(position).getDay());

        return convertView;
    }
    }

