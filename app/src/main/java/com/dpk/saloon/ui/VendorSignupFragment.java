package com.dpk.saloon.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dpk.saloon.BuildConfig;
import com.dpk.saloon.R;
import com.dpk.saloon.databinding.FragmentVendorSignupBinding;
import com.dpk.saloon.util.AuthUtil;
import com.dpk.saloon.viewmodel.StoreSignupViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VendorSignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VendorSignupFragment extends Fragment implements View.OnClickListener {
    FragmentVendorSignupBinding fragmentVendorSignupBinding;
    StoreSignupViewModel storeSignupViewModel;
    CustomLottieDialog customLottieDialog;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VendorSignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VendorSignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VendorSignupFragment newInstance(String param1, String param2) {
        VendorSignupFragment fragment = new VendorSignupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        storeSignupViewModel = new ViewModelProvider(this).get(StoreSignupViewModel.class);
        fragmentVendorSignupBinding = FragmentVendorSignupBinding.inflate(getLayoutInflater());
        return fragmentVendorSignupBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentVendorSignupBinding.storeSignupmainLayout.setOnClickListener(this);
        fragmentVendorSignupBinding.backToStoreSigninPageButton.setOnClickListener(this);
        fragmentVendorSignupBinding.storeSignupButton.setOnClickListener(this);
        fragmentVendorSignupBinding.passwordCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // show password
                    fragmentVendorSignupBinding.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    fragmentVendorSignupBinding.confirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password
                    fragmentVendorSignupBinding.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    fragmentVendorSignupBinding.confirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == fragmentVendorSignupBinding.storeSignupmainLayout.getId()) {
            AuthUtil.closeKeyboardFromFragment(v);
        } else if (v.getId() == fragmentVendorSignupBinding.backToStoreSigninPageButton.getId()) {
            storeSignupViewModel.goBackToVendorLoginFragment(v);
        } else if (v.getId() == fragmentVendorSignupBinding.storeSignupButton.getId()) {
            TextView storeName = fragmentVendorSignupBinding.saloonName;
            TextView storeId = fragmentVendorSignupBinding.saloonId;
            TextView pwd = fragmentVendorSignupBinding.password;
            TextView rpwd = fragmentVendorSignupBinding.confirmPassword;
            boolean validationResult= AuthUtil.validateSignupInfo(getContext(),fragmentVendorSignupBinding.storeSignupmainLayout,storeName, storeId, pwd, rpwd);
        if(validationResult){
             customLottieDialog=new CustomLottieDialog(getContext());
             customLottieDialog.show();
             storeSignupViewModel.signup(storeName.getText().toString(),storeId.getText().toString(),pwd.getText().toString(),getContext(), fragmentVendorSignupBinding.storeSignupmainLayout,customLottieDialog,getView());
        }
        }
    }
}
