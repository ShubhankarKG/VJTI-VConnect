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

    public Adapter(Context mcontext, List<Post> list){
        context = mcontext;
        postList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.feed_posts, parent, false);
        return new ViewHolder (v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post postCurrent = postList.get(getItemCount() - position - 1);
        holder.postTitle.setText(postCurrent.getTitle());
        holder.postDescription.setText(postCurrent.getDescription());
        holder.postDate.setText(postCurrent.getDate());
        Picasso.get()
                .load(postCurrent.getImage())
                .error(R.drawable.vjti_logo)
                .into(holder.postImage);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView postTitle, postDescription, postDate;
        ImageView postImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postTitle =  itemView.findViewById(R.id.post_title);
            postDescription =  itemView.findViewById(R.id.post_description);
            postDate =  itemView.findViewById(R.id.post_date);
            postImage = itemView.findViewById(R.id.ivPost);


        }
    }
}
