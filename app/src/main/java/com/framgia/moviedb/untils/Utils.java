package com.framgia.moviedb.untils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nttung PC on 10/17/2017.
 */

public class Utils {
    public static void openFragment(FragmentManager fragmentManager, int layoutID, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutID,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static String dateFormat(String date){
        String stringDate = date;
        try {
            Date mDate = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            stringDate = formatter.format(mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stringDate;
    }
}
