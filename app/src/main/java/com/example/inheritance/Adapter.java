package com.example.inheritance;


import android.app.AlertDialog;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import static com.example.inheritance.MainActivity.sharedPreferences;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Post> postList;
    private Context context;
    private String committee, adminCred;

    public Adapter(Context mcontext, List<Post> list) {
        context = mcontext;
        postList = list;
    }

    public Adapter(Context mContext, List<Post> postList, String committee, String adminCred) {
        context = mContext;
        this.postList = postList;
        this.committee = committee;
        this.adminCred = adminCred;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.feed_posts, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Post postCurrent = postList.get(getItemCount() - position - 1);
        holder.postTitle.setText(postCurrent.getTitle());
        holder.postDescription.setText(postCurrent.getDescription());
        holder.postDate.setText(postCurrent.getDate());
        holder.postId = postCurrent.getId();
        if(!TextUtils.isEmpty(postCurrent.getImage())){
            Picasso.get()
                    .load(postCurrent.getImage())
                    .into(holder.postImage);
        }

        sharedPreferences = context.getSharedPreferences("userCred", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("logged", false) && sharedPreferences.getString("login_id", null).equals(adminCred)) {
            holder.overflow.setVisibility(View.VISIBLE);
            holder.overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    PopupMenu popup = new PopupMenu(context, holder.overflow);
                    popup.inflate(R.menu.overflow);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.edit_post:
                                    Intent editIntent = new Intent(v.getContext(), EditThisPost.class);
                                    editIntent.putExtra("committee", committee);
                                    editIntent.putExtra("postID", holder.postId);
                                    v.getContext().startActivity(editIntent);
                                    break;

                                case R.id.delete_post:
                                    //Intent deleteIntent = new Intent(v.getContext(), DeleteActivity.class);
                                    //deleteIntent.putExtra("committee", committee);
                                    //deleteIntent.putExtra("postID", holder.postId);
                                    //v.getContext().startActivity(deleteIntent);
                                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                                    alertDialog.setTitle("Confirm delete...");
                                    alertDialog.setMessage("Are you sure you want to delete this?");

                                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            DatabaseReference db = FirebaseDatabase.getInstance().getReference(committee).child(holder.postId);
                                            db.removeValue();
                                            Toast.makeText(context, "Post deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    alertDialog.setNegativeButton("Cancel", null);
                                    alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                                    alertDialog.show();

                                    break;
                                default:
                                    break;
                            }

                            return false;
                        }
                    });
                    popup.show();
                }
            });


        }


    }


    @Override
    public int getItemCount() {
        return postList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView postTitle, postDescription, postDate;
        ImageView postImage;
        ImageButton overflow = (ImageButton) itemView.findViewById(R.id.overflow_menu);
        String postId;
        Button viewPost;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            postTitle = itemView.findViewById(R.id.post_title);
            postDescription = itemView.findViewById(R.id.post_description);
            postDate = itemView.findViewById(R.id.post_date);
            postImage = itemView.findViewById(R.id.ivPost);
            viewPost = (Button) itemView.findViewById(R.id.view_post);

            viewPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ViewPost.class);
                    intent.putExtra("committee", committee);
                    intent.putExtra("postID", postId);
                    itemView.getContext().startActivity(intent);
                }
            });

            overflow.setVisibility(View.INVISIBLE);


        }
    }
}
/*
    class OverflowItemClickListener implements PopupMenu.OnMenuItemClickListener {
        private int position;
        private Context context;
        public OverflowItemClickListener(int position, Context context) {
            this.position = position;
            this.context = context;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch(menuItem.getItemId()) {
                case R.id.edit_post:
                    Intent intent = new Intent(context, EditPost.class);
                    intent.putExtra("committee", committee);
                    intent.putExtra("postID", postId)
            }
        }
    }

}
*/

/*
public void showOverflowMenu(View view, int position) {
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.overflow, popup.getMenu());
                popup.setOnMenuItemClickListener(new OverflowItemClickListener(position, view.getContext()));
                popup.show();
            }

 */

