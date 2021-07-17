package com.dpk.saloon.ui.main;

import android.view.View;

import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.dpk.saloon.R;

public class MainViewModel extends ViewModel {
   View v;
    // TODO: Implement the ViewModel
    public void goToVendorLoginPage(){
        NavController controller= Navigation.findNavController(v);
        controller.navigate(R.id.action_mainFragment_to_vendorLoginFragment);
    }

    public void setView(View view) {
        this.v=view;
    }
}