package com.kukroid.newscorp.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

/**
 * Created by kukresa on 12/26/2018.
 */

public class Util {
    static  String TAG = "NEWSCORPS";

    public static void log(String msg){
        Log.d(TAG,msg);
    }

    public static boolean  isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static void showSnackBar(View view, int message){

        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
