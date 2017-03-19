package org.app.anand.gitinfobase.gitinfobase.mvp.presenter;

/**
 * Created by anand on 3/19/17.
 */

import android.os.Looper;

import org.app.anand.gitinfobase.api.GitApiService;
import org.app.anand.gitinfobase.mvp.model.User;
import org.app.anand.gitinfobase.mvp.presenter.UserDetailsPresenter;
import org.app.anand.gitinfobase.mvp.view.UserDetailsView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;


@RunWith(PowerMockRunner.class)
@PrepareForTest({Observable.class, AndroidSchedulers.class, Looper.class, User.class})
public class UserDetailsPresenterTest {

    @InjectMocks
    private UserDetailsPresenter presenter;
    @Mock private GitApiService gitApiService;
    @Mock private UserDetailsView userView;
    @Mock private Observable<User> userResponseObservable;

    @Captor
    private ArgumentCaptor<Subscriber<User>> captor;

    private final RxJavaSchedulersHook mRxJavaSchedulersHook = new RxJavaSchedulersHook() {
        @Override
        public Scheduler getIOScheduler() {
            return Schedulers.immediate();
        }

        @Override
        public Scheduler getNewThreadScheduler() {
            return Schedulers.immediate();
        }
    };

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getUserDetails() throws Exception {
        String username = "JakeWharton";
        PowerMockito.mockStatic(Looper.class);
        when(AndroidSchedulers.mainThread()).thenReturn(mRxJavaSchedulersHook.getComputationScheduler());

        when(gitApiService.getUserDetails(username)).thenReturn(userResponseObservable);

        presenter.getUserDetails(username, gitApiService);
        verify(userView, atLeastOnce()).onShowDialog("Loading details for "+username+"...");
    }

    @Test
    public void onCompleted() throws Exception {
        presenter.onCompleted();
        verify(userView, times(1)).onHideDialog();
        verify(userView, times(1)).onShowToast("User details loaded successfully");
    }

    @Test
    public void onError() throws Exception {
        presenter.onError(new Throwable());
        verify(userView, times(1)).onHideDialog();
        verify(userView, times(1)).onShowToast("Error : User details loading failed");
    }

    @Test
    public void onNext() throws Exception {
        User response = mock(User.class);
        presenter.onNext(response);
        verify(userView, times(1)).onUserDetailsLoaded(response);
    }

}
