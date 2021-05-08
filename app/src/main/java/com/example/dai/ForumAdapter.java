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

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ViewHolder> {

    private Context context;
    private List<ForumModel> list;

    public ForumAdapter(Context context, List<ForumModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.forum_acti, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ForumModel forum = list.get(position);

        Picasso.with(context)
                .load(forum
                        .getImage_URL())
                .into(holder.imageID);

        holder.commentID.setText(forum.getComentario());
        holder.userNameID.setText(forum.getUsername());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView commentID, userNameID;
        public ImageView imageID;

        public ViewHolder(View itemView) {
            super(itemView);
            imageID = itemView.findViewById(R.id.imageForum);
            commentID = itemView.findViewById(R.id.descForumID);
            userNameID = itemView.findViewById(R.id.userNameID);
        }
    }

}
