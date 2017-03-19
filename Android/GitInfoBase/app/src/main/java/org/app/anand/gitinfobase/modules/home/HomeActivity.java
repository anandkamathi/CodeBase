package org.app.anand.gitinfobase.modules.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import org.app.anand.gitinfobase.R;
import org.app.anand.gitinfobase.utilities.NetworkUtils;
import org.app.anand.gitinfobase.base.BaseActivity;

import org.app.anand.gitinfobase.mvp.presenter.UserPresenter;
import org.app.anand.gitinfobase.mvp.model.User;
import org.app.anand.gitinfobase.mvp.model.adapter.UserAdapter;
import org.app.anand.gitinfobase.mvp.view.HomeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by anand on 2/20/17.
 */

public class HomeActivity extends BaseActivity implements HomeView{

    private static final String TAG = "HomeActivityTAG_";

    private UserAdapter userAdapter;
    private UserPresenter userPresenter;
    @Bind(R.id.userSrch) SearchView userSearch;
    @Bind(R.id.user_grid) RecyclerView usersList;
    @Bind(R.id.title) TextView titleTxt;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        ButterKnife.bind(this);
        initializeFollowersList();
    }

    private void loadFollowers(String uname) {
        if(NetworkUtils.isNetworkAvailable(this)) {
            userPresenter.getFollowers(uname,gitApiService);
        } else {
            Toast.makeText(this,"No data connection!",Toast.LENGTH_LONG).show();
        }
    }

    private void initializeFollowersList() {
        String hint = "GitHub username";
        userPresenter = new UserPresenter(this);


        userSearch.setQueryHint(hint);
        userSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                loadFollowers(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        usersList = (RecyclerView) findViewById(R.id.user_grid);
        usersList.setHasFixedSize(true);
        usersList.setLayoutManager(new GridLayoutManager(this,3));
        userAdapter = new UserAdapter(getLayoutInflater());
        //userAdapter.setCakeClickListener(mCakeClickListener);
        usersList.setAdapter(userAdapter);
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
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
        Toast.makeText(HomeActivity.this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserLoaded(List<User> users) {
        Log.d(TAG, "onUserLoaded: " + users);
        userAdapter.addUsers(users);
    }

    @Override
    public void onClearItems() {
        userAdapter.clearUsers();
    }
}
