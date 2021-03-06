package com.dpk.saloon.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dpk.saloon.databinding.FragmentVendorLoginBinding;
import com.dpk.saloon.util.AuthUtil;
import com.dpk.saloon.viewmodel.StoreSigninViewModel;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VendorLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VendorLoginFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int REQUEST_CODE = 101;
    CustomLottieDialog customLottieDialog;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentVendorLoginBinding fragmentVendorLoginBinding;
    private StoreSigninViewModel storeSigninViewModel;

    public VendorLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VendorLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VendorLoginFragment newInstance(String param1, String param2) {
        VendorLoginFragment fragment = new VendorLoginFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        storeSigninViewModel = new ViewModelProvider(this).get(StoreSigninViewModel.class);
        fragmentVendorLoginBinding = FragmentVendorLoginBinding.inflate(getLayoutInflater());
        return fragmentVendorLoginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentVendorLoginBinding.storeLoginMainLayout.setOnClickListener(this);
        fragmentVendorLoginBinding.goToSignupPageButton.setOnClickListener(this);
        fragmentVendorLoginBinding.signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == fragmentVendorLoginBinding.storeLoginMainLayout.getId())
            AuthUtil.closeKeyboardFromFragment(v);
        if (v.getId() == fragmentVendorLoginBinding.goToSignupPageButton.getId()) {
            storeSigninViewModel.goToStoreSignupPage(v);
        }
        if (v.getId() == fragmentVendorLoginBinding.signInButton.getId()) {
            boolean res = AuthUtil.validateSigninInfo(fragmentVendorLoginBinding.loginStoreId, fragmentVendorLoginBinding.loginPassword, getContext(), fragmentVendorLoginBinding.storeLoginMainLayout);
            if (res) {
                customLottieDialog = new CustomLottieDialog(getContext());
                customLottieDialog.show();
                storeSigninViewModel.signin(fragmentVendorLoginBinding.loginStoreId.getText().toString(), fragmentVendorLoginBinding.loginPassword.getText().toString(), getContext(), customLottieDialog, getView());
            }
        }
    }

}
