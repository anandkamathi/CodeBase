package org.app.anand.gitinfobase.mvp.view;

import org.app.anand.gitinfobase.mvp.model.User;
import org.app.anand.gitinfobase.mvp.view.BaseView;

import java.util.List;

/**
 * Created by anand on 2/19/17.
 */

public interface HomeView extends BaseView {
    void onUserLoaded(List<User> user);

    void onClearItems();
}
