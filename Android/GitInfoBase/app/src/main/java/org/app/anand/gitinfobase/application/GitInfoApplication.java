package org.app.anand.gitinfobase.application;

import android.app.Application;

import org.app.anand.gitinfobase.dagger.component.DaggerNetComponent;
import org.app.anand.gitinfobase.dagger.component.NetComponent;
import org.app.anand.gitinfobase.dagger.module.AppModule;
import org.app.anand.gitinfobase.dagger.module.NetModule;

/**
 * Created by anand on 2/19/17.
 */

public class GitInfoApplication extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.github.com"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
