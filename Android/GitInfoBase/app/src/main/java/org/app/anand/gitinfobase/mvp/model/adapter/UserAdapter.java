package org.app.anand.gitinfobase.mvp.model.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.app.anand.gitinfobase.R;
import org.app.anand.gitinfobase.modules.details.UserActivity;
import org.app.anand.gitinfobase.mvp.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anand on 2/20/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private static final String TAG = "UserAdapterTAG_";

    LayoutInflater userLayoutInflater;
    List<User> userList = new ArrayList<>();

    public UserAdapter(LayoutInflater inflater) {
        userLayoutInflater = inflater;
    }

    public void addUsers(List<User> users) {

        Log.d(TAG, "addUsers: " + users);

        if (users != null) {
            userList.addAll(users);
            notifyDataSetChanged();
        }
    }

    public void clearUsers() {
        userList.clear();
        notifyDataSetChanged();
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = userLayoutInflater.inflate(R.layout.grid_layout_item, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.bind(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgUser;
        TextView txtUser;

        Context context;
        User user;

        public UserHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            context = itemView.getContext();
            imgUser = (ImageView) itemView.findViewById(R.id.userPic);
            txtUser = (TextView) itemView.findViewById(R.id.userName);
        }

        public void bind(User u) {
            try {
                user = u;
                Log.d("Username",user.getLogin());
                Log.d("Pic",user.getAvatarUrl());
                txtUser.setText(user.getLogin());
                Glide.with(context).load(user.getAvatarUrl()).placeholder(R.drawable.ic_launcher).fitCenter().into(imgUser);
            } catch (Exception e) {
                Log.e("Adapter", "bind: " + e.getMessage());
            }
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            User current = userList.get(pos);


            Intent detailsIntent = new Intent(context, UserActivity.class);
            detailsIntent.putExtra("User",current.getLogin());
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, view, "cakeImageAnimation");
                context.startActivity(detailsIntent, options.toBundle());
            } else {
                context.startActivity(detailsIntent);
            }

        }
    }
}
