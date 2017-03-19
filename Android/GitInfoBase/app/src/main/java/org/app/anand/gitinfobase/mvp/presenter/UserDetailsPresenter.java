package org.app.anand.gitinfobase.mvp.presenter;

import android.util.Log;

import org.app.anand.gitinfobase.api.GitApiService;
import org.app.anand.gitinfobase.base.BasePresenter;
import org.app.anand.gitinfobase.mvp.model.User;
import org.app.anand.gitinfobase.mvp.view.UserDetailsView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by anand on 2/20/17.
 */

public class UserDetailsPresenter extends BasePresenter<UserDetailsView> implements Observer<User>{

    private static final String TAG = "UserDetailsPresenterTAG_";

    public UserDetailsPresenter(UserDetailsView userDetailsView) {
        super(userDetailsView);
    }

    //Communicates with UserActivity as Presenter component with RxJava driven validation
    // and response for individual user detail
    public void getUserDetails(String username,GitApiService gitApiService) {
        getView().onShowDialog("Loading details for "+username+"...");
        Observable<User> currentUser = gitApiService.getUserDetails(username);

        Subscription subscription = currentUser.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);


    }

    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("User details loaded successfully");
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Error : User details loading failed");
    }

    @Override
    public void onNext(User user) {
        if(user.getFollowers()==null)
            user.setFollowers(0);
        if(user.getFollowing()==null)
            user.setFollowing(0);
        if(user.getPublicRepos()==null)
            user.setPublicRepos(0);
        if(user.getEmail()==null)
            user.setEmail("NA");
        if(user.getLocation()==null)
            user.setLocation("NA");
        getView().onUserDetailsLoaded(user);
    }
}
