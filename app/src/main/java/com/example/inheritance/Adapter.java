package com.example.inheritance;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Post> postList;
    private Context context;

    public Adapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.feed_posts, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Post post = postList.get(i);
        viewHolder.postTitle.setText(post.getTitle());
        viewHolder.postDescription.setText((post.getDescription()));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView postTitle, postDescription;
        ImageView postImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postTitle = (TextView) itemView.findViewById(R.id.post_title);
            postDescription = (TextView) itemView.findViewById(R.id.post_description);
            postImage = (ImageView)itemView.findViewById(R.id.ivPost);
        }
    }
}
