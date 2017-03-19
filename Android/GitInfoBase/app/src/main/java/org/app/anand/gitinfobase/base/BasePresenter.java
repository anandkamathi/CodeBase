package org.app.anand.gitinfobase.base;

import org.app.anand.gitinfobase.mvp.model.User;
import org.app.anand.gitinfobase.mvp.view.BaseView;
import org.app.anand.gitinfobase.mvp.view.HomeView;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by anand on 2/20/17.
 */

public class BasePresenter<View extends BaseView> {

    protected View mView;

    public BasePresenter(View v) {
        mView=v;
    }

    protected View getView() {
        return mView;
    }


}
