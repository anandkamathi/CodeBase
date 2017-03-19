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

    String BASE_URL="https://api.github.com";

    @GET("/users/{username}/followers")
    Call<List<User>> getFollowers(@Path("username") String uname);

    @GET("/users/{username}")
    Observable<User> getUserDetails(@Path("username") String uname);

    class GitFactory{
        private static GitApiService gitApi;

        public static GitApiService getInstance(){
            if (gitApi == null){
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();

                gitApi = retrofit.create(GitApiService.class);
                return gitApi;
            }else {
                return gitApi;
            }

        }
    }
}
