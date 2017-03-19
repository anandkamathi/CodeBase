package org.app.anand.gitinfobase.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by anand on 2/20/17.
 */

public class NetworkUtils {

    //Method for determining if data connection is available or not at given time
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
