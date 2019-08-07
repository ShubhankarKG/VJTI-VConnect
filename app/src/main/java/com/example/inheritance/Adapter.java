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
import android.util.Log;
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

import static com.example.inheritance.Home.sharedPreferences;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Post> postList;
    private Context context;
    private String committee, adminCred;
    private String program, year, branch;
    private StorageReference sr;

    public Adapter(Context mContext, List<Post> noticesList, String program, String branch, String year) {
        context = mContext;
        postList = noticesList;
        this.program = program;
        this.branch = branch;
        this.year = year;
    }

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
        final Post postCurrent = postList.get(getItemCount() - position - 1);
        holder.postTitle.setText(postCurrent.getTitle());
        holder.postDescription.setText(postCurrent.getDescription());
        holder.postDate.setText(postCurrent.getDate());
        holder.postId = postCurrent.getId();
        if (!TextUtils.isEmpty(postCurrent.getImageUrl())) {
            Picasso.get()
                    .load(postCurrent.getImageUrl())
                    .into(holder.postImage);
        }

        sharedPreferences = context.getSharedPreferences("userCred", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("logged", false) && Objects.equals(sharedPreferences.getString("login_id", null), adminCred)) {
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
                                    if (committee != null) {
                                        editIntent.putExtra("committee", committee);
                                    } else {
                                        editIntent.putExtra("program", program);
                                        editIntent.putExtra("branch", branch);
                                        editIntent.putExtra("year", year);
                                    }
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
                                            if (postCurrent.getImageUrl() != null) {
                                                FirebaseStorage storage = FirebaseStorage.getInstance();
                                                sr = storage.getReferenceFromUrl(postCurrent.getImageUrl());
                                                sr.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        DatabaseReference db = FirebaseDatabase.getInstance().getReference(committee).child(holder.postId);
                                                        db.removeValue();
                                                        Toast.makeText(context, "Post deleted", Toast.LENGTH_SHORT).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(context, "Oops! An error occurred", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            } else {
                                                DatabaseReference db = FirebaseDatabase.getInstance().getReference(committee).child(holder.postId);
                                                db.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(context, "Post deleted ", Toast.LENGTH_SHORT).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.e("error", Objects.requireNonNull(e.getMessage()));
                                                    }
                                                });
                                                ;

                                            }
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


        } else if (sharedPreferences.getBoolean("cr_logged", false)) {
            holder.overflow.setVisibility(View.VISIBLE);
            holder.overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    PopupMenu popup = new PopupMenu(context, holder.overflow);
                    popup.inflate(R.menu.overflow);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.edit_post:
                                    Intent editIntent = new Intent(view.getContext(), EditThisPost.class);
                                    editIntent.putExtra("program", program);
                                    editIntent.putExtra("branch", branch);
                                    editIntent.putExtra("year", year);
                                    editIntent.putExtra("postID", holder.postId);
                                    view.getContext().startActivity(editIntent);
                                    break;

                                case R.id.delete_post:
                                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                                    alertDialog.setTitle("Confirm delete...");
                                    alertDialog.setMessage("Are you sure you want to delete this?");

                                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int which) {
                                            if (postCurrent.getImageUrl() != null) {
                                                FirebaseStorage storage = FirebaseStorage.getInstance();
                                                sr = storage.getReferenceFromUrl(postCurrent.getImageUrl());
                                                sr.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        DatabaseReference db;
                                                        if (!program.equals("MCA")) {
                                                            db = FirebaseDatabase.getInstance().getReference(program).child(branch).child(year).child(holder.postId);
                                                        } else {
                                                            db = FirebaseDatabase.getInstance().getReference(program).child(year).child(holder.postId);
                                                        }
                                                        db.removeValue();
                                                        Toast.makeText(context, "Notice deleted", Toast.LENGTH_SHORT).show();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.e("error", Objects.requireNonNull(e.getMessage()));
                                                        Toast.makeText(context, "Oops! An error occured", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            } else {
                                                DatabaseReference db;
                                                if (!program.equals("MCA")) {
                                                    db = FirebaseDatabase.getInstance().getReference(program).child(branch).child(year).child(holder.postId);
                                                } else {
                                                    db = FirebaseDatabase.getInstance().getReference(program).child(year).child(holder.postId);
                                                }
                                                db.removeValue();
                                                Toast.makeText(context, "Notice deleted", Toast.LENGTH_SHORT).show();
                                            }

                                        }


                                    });
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
        ImageButton overflow = itemView.findViewById(R.id.overflow_menu);
        String postId;
        Button viewPost;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            postTitle = itemView.findViewById(R.id.post_title);
            postDescription = itemView.findViewById(R.id.post_description);
            postDate = itemView.findViewById(R.id.post_date);
            postImage = itemView.findViewById(R.id.ivPost);
            viewPost = itemView.findViewById(R.id.view_post);

            viewPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ViewPost.class);
                    if (committee != null) {
                        intent.putExtra("purpose", "student_activity");
                        intent.putExtra("committee", committee);
                    } else {
                        intent.putExtra("purpose", "notice");
                        intent.putExtra("program", program);
                        intent.putExtra("branch", branch);
                        intent.putExtra("year", year);
                    }
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

