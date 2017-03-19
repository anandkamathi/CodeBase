package org.app.anand.gitinfobase.mvp.view;

import android.view.View;

import org.app.anand.gitinfobase.mvp.model.User;

import java.util.List;

/**
 * Created by anand on 2/19/17.
 */

public interface BaseView {
    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);
}
