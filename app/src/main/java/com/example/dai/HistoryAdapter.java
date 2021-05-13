package com.example.dai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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

        Picasso.get()
                .load(history
                        .getImageURL())
                .resize(100, 100)
                .centerInside()
                .into(holder.imageId);

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
        public ImageView dayIcon, localIcon, imageId;


        public ViewHolder(View itemView) {
            super(itemView);
            imageId = itemView.findViewById(R.id.imageHistory);

            dayIcon = itemView.findViewById(R.id.imageView30);
            dayIcon.setImageResource(R.drawable.location);

            localIcon = itemView.findViewById(R.id.imageView29);
            localIcon.setImageResource(R.drawable.calendar);

            historyDayID = itemView.findViewById(R.id.dataHist);
            historyLocalID = itemView.findViewById(R.id.locationHist);
            historyNameID = itemView.findViewById(R.id.activname);
        }
    }

}