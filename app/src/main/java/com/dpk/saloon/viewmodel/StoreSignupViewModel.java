package com.dpk.saloon.viewmodel;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import androidx.lifecycle.AndroidViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.dpk.saloon.R;
import com.dpk.saloon.persistence.StoreSignupRepository;
import com.dpk.saloon.ui.CustomLottieDialog;
import com.google.firebase.FirebaseApp;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StoreSignupViewModel extends AndroidViewModel {
    StoreSignupRepository storeSignupRepository;
    public StoreSignupViewModel(@NotNull Application application) {
        super(application);
        storeSignupRepository = new StoreSignupRepository();
    }

    public void goBackToVendorLoginFragment(View v) {
        NavController controller = Navigation.findNavController(v);
        controller.navigate(R.id.action_vendorSignupFragment_to_LoginFragment);
    }

    public void signup(String storeName, String storeId, String password, Context context, LinearLayout storeSignupmainLayout, CustomLottieDialog progressDialog, View view){
        storeSignupRepository.signup(storeName,storeId, password,context,storeSignupmainLayout,progressDialog,view);
    }

    public void saveStoreServices(ArrayList<String> storeServices, View view) {
        storeSignupRepository.saveStoreServices(storeServices,view);
    }
}
