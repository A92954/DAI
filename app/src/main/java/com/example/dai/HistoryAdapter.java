package com.example.dai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private List<HistoryModel> list;

    public HistoryAdapter(Context context, List<HistoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.history_acti, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryModel history = list.get(position);

        holder.historyDayID.setText(history.getData());
        holder.historyNameID.setText(history.getActi_name());
        holder.historyLocalID.setText(history.getLocal());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView historyDayID, historyLocalID, historyNameID;


        public ViewHolder(View itemView) {
            super(itemView);
            historyDayID = itemView.findViewById(R.id.dataHist);
            historyLocalID = itemView.findViewById(R.id.locationHist);
            historyNameID = itemView.findViewById(R.id.activname);
        }
    }

}