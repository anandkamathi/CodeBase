package org.app.anand.gitinfobase.modules.details;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.app.anand.gitinfobase.R;
import org.app.anand.gitinfobase.utilities.NetworkUtils;
import org.app.anand.gitinfobase.base.BaseActivity;
import org.app.anand.gitinfobase.handler.imagehandler.ImageLoader;
import org.app.anand.gitinfobase.mvp.model.User;
import org.app.anand.gitinfobase.mvp.presenter.UserDetailsPresenter;
import org.app.anand.gitinfobase.mvp.view.UserDetailsView;
import org.app.anand.gitinfobase.mvp.view.custom.CircularImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by anand on 2/20/17.
 */

public class UserActivity extends BaseActivity implements UserDetailsView{

    UserDetailsPresenter userDetailsPresenter;
    @Bind(R.id.userIcon) CircularImageView iconUser;
    @Bind(R.id.uName) TextView txtUserDetails;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        ButterKnife.bind(this);
        String user = intent.getExtras().getString("User");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iconUser.setTransitionName("cakeImageAnimation");
        }

        showBackArrow();

        if(user!=null) {
            if (NetworkUtils.isNetworkAvailable(this)) {

                getUserDetails(user);
            }else
                Toast.makeText(this, "No data connection!", Toast.LENGTH_LONG).show();
        }
    }

    private void getUserDetails(String u) {
        userDetailsPresenter = new UserDetailsPresenter(this);
        userDetailsPresenter.getUserDetails(u,gitApiService);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_user;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {

    }

    @Override
    public void onUserDetailsLoaded(User user) {

        //Glide.with(UserActivity.this).load(user.getAvatarUrl()).into(iconUser);
        new ImageLoader(this).DisplayImage(user.getAvatarUrl(),iconUser);
        String publicRepository="NA", following="NA", followers="NA", email="NA", location="NA";
        if(user.getFollowers()!=null)
            followers = user.getFollowers().toString();
        if(user.getFollowing()!=null)
            following = user.getFollowing().toString();
        if(user.getPublicRepos()!=null)
            publicRepository = user.getPublicRepos().toString();
        if(user.getEmail()!=null)
            email = user.getEmail().toString();
        if(user.getLocation()!=null)
            location = user.getLocation().toString();

        String details = " Followers: " + followers +
                "\n Following: " + following +
                "\n Repositeries: " + publicRepository +
                "\n Email: " + email +
                "\n Location: " + location
                ;
        txtUserDetails.setText(details);

        Toast.makeText(UserActivity.this,"User "+user.getLogin()+ " details fetched",Toast.LENGTH_LONG).show();;
    }
}

