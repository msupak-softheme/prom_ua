package com.example.eh.tryahnu.starinoy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

	public static boolean isOnline(Context ctx) {
	    ConnectivityManager cm =
	        (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
}
