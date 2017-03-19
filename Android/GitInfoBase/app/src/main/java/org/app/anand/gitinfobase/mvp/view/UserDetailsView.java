package org.app.anand.gitinfobase.mvp.view;

import org.app.anand.gitinfobase.mvp.model.User;

import java.util.List;

/**
 * Created by anand on 2/20/17.
 */

public interface UserDetailsView extends BaseView {
    void onUserDetailsLoaded(User user);
}
