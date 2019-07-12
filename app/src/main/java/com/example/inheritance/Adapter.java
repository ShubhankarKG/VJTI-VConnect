package com.example.inheritance;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Post> postList;
    private Context context;
    private String committee;

    public Adapter(List<Post> postList, String committee) {
        this.postList = postList;
        this.committee = committee;
    }

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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Post post = postList.get(i);
        viewHolder.postTitle.setText(post.getTitle());
        viewHolder.postDescription.setText((post.getDescription()));
        viewHolder.postDate.setText(post.getDate());
        viewHolder.postId = post.getId();
//        final String postID = viewHolder.postId;
//        viewHolder.viewPost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(),ViewPost.class);
//                intent.putExtra("committee",committee);
//                intent.putExtra("postID",postID);
//                viewHolder.itemView.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView postTitle, postDescription, postDate;
        Button viewPost;
        ImageView postImage;
        String postId;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            postTitle = (TextView) itemView.findViewById(R.id.post_title);
            postDescription = (TextView) itemView.findViewById(R.id.post_description);
            postDate = (TextView) itemView.findViewById(R.id.post_date);
            viewPost = (Button) itemView.findViewById(R.id.view_post);
//            postImage = (ImageView)itemView.findViewById(R.id.ivPost);
            viewPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),ViewPost.class);
                    intent.putExtra("committee",committee);
                    intent.putExtra("postID",postId);
                    itemView.getContext().startActivity(intent);

                }
            });


        }


    }
}