package org.app.anand.gitinfobase.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import org.app.anand.gitinfobase.Application.GitInfoApplication;
import org.app.anand.gitinfobase.api.GitApiService;
import org.app.anand.gitinfobase.dagger.component.NetComponent;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by anand on 2/20/17.
 */

public abstract class BaseActivity extends AppCompatActivity{

    ProgressDialog mProgressDialog;
    @Inject
    Retrofit retrofit;
    public GitApiService gitApiService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        getNetComponent().inject(this);
        gitApiService = retrofit.create(GitApiService.class);
        onViewReady(savedInstanceState, getIntent());

    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {

    }


    protected NetComponent getNetComponent() {
        return ((GitInfoApplication) getApplication()).getNetComponent();
    }

    protected void showBackArrow() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
    }

    protected void showDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected abstract int getContentView();
}
