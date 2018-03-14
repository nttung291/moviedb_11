package com.framgia.moviedb.untils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nttung PC on 10/17/2017.
 */

public class Utils {
    private static SimpleDateFormat mSimpleDateFormat;

    public static void openFragment(FragmentManager fragmentManager, int layoutID, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutID, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static String dateFormat(String date) {
        String stringDate = date;
        try {
            mSimpleDateFormat = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD);
            Date mDate = mSimpleDateFormat.parse(stringDate);
            mSimpleDateFormat = new SimpleDateFormat(Constant.DATE_FORMAT_DD_MM_YYYY);
            stringDate = mSimpleDateFormat.format(mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stringDate;
    }

    public static void showMessageGetDataFailed(final Activity activity, final Context context) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(context, Constant.MESSAGE_GET_DATA_FAILED, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showMessageGetVideoFailed(final Activity activity, final Context context) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(context, Constant.MESSAGE_GET_VIDEO_FAILED, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showToast(final Activity activity, final Context context, final String message) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
