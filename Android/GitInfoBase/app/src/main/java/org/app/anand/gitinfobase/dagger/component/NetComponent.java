package org.app.anand.gitinfobase.dagger.component;


import android.content.Context;

import org.app.anand.gitinfobase.base.BaseActivity;
import org.app.anand.gitinfobase.dagger.module.AppModule;
import org.app.anand.gitinfobase.dagger.module.NetModule;
import org.app.anand.gitinfobase.modules.details.UserActivity;
import org.app.anand.gitinfobase.modules.home.HomeActivity;

import javax.inject.Singleton;
import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by anand on 3/18/17.
 */

//NetComponent provides Retrofit singleton object for Restful API consumption
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(BaseActivity activity);
}