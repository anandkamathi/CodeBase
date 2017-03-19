package org.app.anand.gitinfobase.mvp.presenter;

import android.util.Log;

import org.app.anand.gitinfobase.api.GitApiService;
import org.app.anand.gitinfobase.base.BasePresenter;
import org.app.anand.gitinfobase.mvp.model.User;
import org.app.anand.gitinfobase.mvp.view.HomeView;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
;


/**
 * Created by anand on 2/20/17.
 */

public class UserPresenter extends BasePresenter<HomeView>  {

    private static final String TAG = "UserPresenterTAG_";
    HomeView userView;


    public UserPresenter(HomeView v) {
        super(v);
        Log.d(TAG, "UserPresenter: " + v);
    }

    public void getFollowers(String username,GitApiService gitApiService) {
        getView().onShowDialog("Loading followers....");
        Call<List<User>> userResponseObservable = gitApiService.getFollowers(username);

        userResponseObservable.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                getView().onClearItems();
                getView().onHideDialog();
                if(response.body()!=null)
                {
                    getView().onUserLoaded(response.body());
                    getView().onShowToast("Total followers:"+response.body().size());
                } else {
                    getView().onShowToast("Error : No followers found");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

}
