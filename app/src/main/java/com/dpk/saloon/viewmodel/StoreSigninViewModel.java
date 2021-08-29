package com.dpk.saloon.viewmodel;

import android.app.Application;
import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.dpk.saloon.R;
import com.dpk.saloon.persistence.StoreSigninRepository;
import com.dpk.saloon.ui.CustomLottieDialog;
import com.google.firebase.FirebaseApp;

public class StoreSigninViewModel extends AndroidViewModel {
    StoreSigninRepository storeSigninRepository;
    public StoreSigninViewModel(@NonNull Application application) {
        super(application);
        storeSigninRepository = new StoreSigninRepository();
    }

    public void signin(String storeId, String pswd, Context context, CustomLottieDialog customLottieDialog, View view) {
        storeSigninRepository.signin(storeId,pswd,context,customLottieDialog,view);
    }

    public void goToStoreSignupPage(View v) {
        NavController controller = Navigation.findNavController(v);
        controller.navigate(R.id.action_vendorLoginFragment_to_vendorSignupFragment);
    }
}
