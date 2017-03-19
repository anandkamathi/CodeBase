package org.app.anand.gitinfobase.api;

import org.app.anand.gitinfobase.mvp.model.User;

import java.security.cert.CertificateFactory;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by anand on 2/19/17.
 */

public interface GitApiService {
    //Restful API Endpoints
    @GET("/users/{username}/followers")
    Call<List<User>> getFollowers(@Path("username") String uname);

    @GET("/users/{username}")
    Observable<User> getUserDetails(@Path("username") String uname);

}
