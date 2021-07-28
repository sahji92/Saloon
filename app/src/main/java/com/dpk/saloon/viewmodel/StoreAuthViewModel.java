package com.dpk.saloon.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.dpk.saloon.R;
import com.dpk.saloon.model.StoreUser;
import com.dpk.saloon.persistence.AuthRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class StoreAuthViewModel extends AndroidViewModel {
    AuthRepository authRepository;
    public LiveData<StoreUser> authenticatedStoreUserLivedata;
    public LiveData<StoreUser> createdStoreUserLiveData;

    public StoreAuthViewModel(@NotNull Application application) {
        super(application);
        authRepository = new AuthRepository();
    }

    public boolean validateInfo(Context context, LinearLayout storeSignupmainLayout, TextView storeName, TextView storeId, TextView location, TextView pwd, TextView rpwd) {
        String sname = storeName.getText().toString();
        String sid = storeId.getText().toString();
        String loc = location.getText().toString();
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
        } else if (!sid.matches("[a-zA-Z]+[0-9]+[@]+[s]+[a]+[l]+[o]+[o]+[n]")) {
            storeId.requestFocus();
            showErrorSnackbar(storeSignupmainLayout,context,"Id format must be like deepak23@saloon");
            return false;
        }
        else if (sid.length()>20) {
            storeId.requestFocus();
            showErrorSnackbar(storeSignupmainLayout,context,"Field cannot be more than 20 characters long");
            return false;
        }


        if (loc.length() == 0) {
            location.requestFocus();
            showErrorSnackbar(storeSignupmainLayout,context,"Field cannot be empty");
            return false;
        }
        else if (loc.length()>60) {
            location.requestFocus();
            showErrorSnackbar(storeSignupmainLayout,context,"Field cannot be more than 60 characters long");
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

    private void showErrorSnackbar(LinearLayout storeSignupmainLayout, Context context, String msg) {
        Snackbar.make(storeSignupmainLayout,msg,Snackbar.LENGTH_LONG).setTextColor(context.getResources().getColor(R.color.red,Resources.getSystem().newTheme()))
                .setAction("close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setActionTextColor(context.getResources().getColor(R.color.teal_700, Resources.getSystem().newTheme()))
                .setBackgroundTint(context.getResources().getColor(R.color.light_blue, Resources.getSystem().newTheme()))
                .show();
    }

    public void closeKeyboardFromFragment(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        // hide the keyboard
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void goToStoreSignupPage(View v) {
        NavController controller = Navigation.findNavController(v);
        controller.navigate(R.id.action_vendorLoginFragment_to_vendorSignupFragment);
    }

    public void goBackToVendorLoginFragment(View v) {
        NavController controller = Navigation.findNavController(v);
        controller.navigate(R.id.action_vendorSignupFragment_to_LoginFragment);
    }
}
