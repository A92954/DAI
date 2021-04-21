package com.example.dai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private Context context;
    private List<CalendarModel> list;

    public CalendarAdapter(Context context, List<CalendarModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.calendar_acti, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CalendarModel calendar = list.get(position);

        holder.textDay.setText(calendar.getDay());
        holder.textActi.setText(calendar.getActi_name());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textDay, textActi;

        public ViewHolder(View itemView) {
            super(itemView);

            textDay = itemView.findViewById(R.id.dayID);
            textActi = itemView.findViewById(R.id.actiNameID);
        }
    }

}
