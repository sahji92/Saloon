package com.dpk.saloon.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dpk.saloon.R;
import com.google.android.material.snackbar.Snackbar;

public class AuthUtil {
    public static boolean validateSignupInfo(Context context, LinearLayout storeSignupmainLayout, TextView storeName, TextView storeId, TextView pwd,TextView rpwd) {
        String sname = storeName.getText().toString();
        String sid = storeId.getText().toString();
        String pswd = pwd.getText().toString();
        String rpswd = rpwd.getText().toString();
        if (sname.length() == 0) {
            storeName.requestFocus();
            showErrorSnackbar(storeSignupmainLayout,context,"Field cannot be empty");
            return false;
        } else if (!sname.matches("[a-zA-Z]+")) {
            storeName.requestFocus();
            showErrorSnackbar(storeSignupmainLayout,context,"Enter only alphabets");
            return false;
        }
        else if (sname.length()>20) {
            storeName.requestFocus();
            showErrorSnackbar(storeSignupmainLayout,context,"Field cannot be more than 20 characters long");
            return false;
        }

        if (sid.length() == 0) {
            storeId.requestFocus();
            showErrorSnackbar(storeSignupmainLayout,context,"Field cannot be empty");
            return false;
        } else if (!sid.matches("[a-zA-Z]+[0-9]+[@]+[s]+[a]+[l]+[o]+[o]+[n]+[.]+[c]+[o]+[m]")) {
            storeId.requestFocus();
            showErrorSnackbar(storeSignupmainLayout,context,"Id format must be like deepak23@saloon.com");
            return false;
        }
        else if (sid.length()>20) {
            storeId.requestFocus();
            showErrorSnackbar(storeSignupmainLayout,context,"Field cannot be more than 20 characters long");
            return false;
        }

        if (pswd.length()<=8||pswd.length()>=15) {
            pwd.requestFocus();
            showErrorSnackbar(storeSignupmainLayout,context,"Password must be more than 8 and less than 15 character long");
            return false;
        }
        if (rpswd.length()<=8||rpswd.length()>=15) {
            rpwd.requestFocus();
            showErrorSnackbar(storeSignupmainLayout,context,"Password must be more than 8 and less than 15 character long");
            return false;
        }
        if (!pswd.equals(rpswd)) {
            rpwd.requestFocus();
            showErrorSnackbar(storeSignupmainLayout,context,"Password do not match");
            return false;
        }
        return true;
    }
    public static boolean validateSigninInfo(TextView storeid, TextView pswd, Context context, LinearLayout storeSigninmainLayout){
        String sid = storeid.getText().toString();
        String pwd=pswd.getText().toString();
        if (sid.length() == 0) {
            storeid.requestFocus();
            showErrorSnackbar(storeSigninmainLayout, context, "Field cannot be empty");
            return false;
        }
        if(pwd.length()==0){
            pswd.requestFocus();
            showErrorSnackbar(storeSigninmainLayout, context, "Field cannot be empty");
            return false;
        }
        return true;
    }
    private static void showErrorSnackbar(LinearLayout mainLayout, Context context, String msg) {
        Snackbar.make(mainLayout,msg,Snackbar.LENGTH_LONG).setTextColor(context.getResources().getColor(R.color.red, Resources.getSystem().newTheme()))
                .setAction("close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setActionTextColor(context.getResources().getColor(R.color.teal_700, Resources.getSystem().newTheme()))
                .setBackgroundTint(context.getResources().getColor(R.color.light_blue, Resources.getSystem().newTheme()))
                .show();
    }

    public static void closeKeyboardFromFragment(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        // hide the keyboard
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
