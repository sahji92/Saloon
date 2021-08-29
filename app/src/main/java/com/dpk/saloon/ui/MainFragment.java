package com.dpk.saloon.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.dpk.saloon.R;
import com.dpk.saloon.databinding.MainFragmentBinding;
import com.google.firebase.FirebaseApp;

public class MainFragment extends Fragment implements View.OnClickListener {
    MainFragmentBinding mainFragmentBinding;
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainFragmentBinding=MainFragmentBinding.inflate(getLayoutInflater());
        return mainFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainFragmentBinding.storeSignupPageButton.setOnClickListener(this);
        FirebaseApp.initializeApp(getContext());
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.storeSignupPageButton){
            NavController controller= Navigation.findNavController(v);
            controller.navigate(R.id.action_mainFragment_to_vendorLoginFragment);
        }
    }
}