package com.dpk.saloon.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.dpk.saloon.R;
import com.dpk.saloon.databinding.FragmentVendorSignupBinding;
import com.dpk.saloon.viewmodel.StoreAuthViewModel;
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
    private FirebaseAuth mAuth;
    StoreAuthViewModel storeAuthViewModel;
    private FirebaseUser firebaseUser;
    private ProgressDialog progressDialog;
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = rootRef.collection("/storeusers");
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VendorSignupFragment() {
        // Required empty public constructor
        mAuth = FirebaseAuth.getInstance();
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
        storeAuthViewModel = new ViewModelProvider(this).get(StoreAuthViewModel.class);
        initProgressDialog();
        fragmentVendorSignupBinding = FragmentVendorSignupBinding.inflate(getLayoutInflater());
        return fragmentVendorSignupBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentVendorSignupBinding.storeSignupmainLayout.setOnClickListener(this);
        fragmentVendorSignupBinding.backToStoreSignupPageButton.setOnClickListener(this);
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

    /*public void signup(){
            String saloonId=vendorSignupBinding.saloonId.getText().toString();
            String password=vendorSignupBinding.password.getText().toString();
            mAuth.createUserWithEmailAndPassword(saloonId,password)
                    .addOnCompleteListener(getActivity(), task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            firebaseUser= task.getResult().getUser();
                            String usrname = saloonId.substring(0, 3) +firebaseUser.getUid().substring(0, 2) + (int) (Math.random() * 200);
                            StoreUser storeUser = new StoreUser(firebaseUser.getUid(),saloonId, usrname);
                            usersRef.add(storeUser);
                            goToHomeFragment();
                            progressDialog.dismiss();
                        } else {
                            // If sign in fails, display a message to the user.
                            Snackbar.make(vendorSignupBinding.mainLayout,"user already exist",Snackbar.LENGTH_LONG).setTextColor(getResources().getColor(R.color.blue))
                                    .setAction("close", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                        }
                                    })
                                    .setActionTextColor(getResources().getColor(R.color.blue))
                                    .show();
                        }
                    });

        }*/
    @Override
    public void onClick(View v) {
        if (v.getId() == fragmentVendorSignupBinding.storeSignupmainLayout.getId()) {
            storeAuthViewModel.closeKeyboardFromFragment(v);
        } else if (v.getId() == fragmentVendorSignupBinding.backToStoreSignupPageButton.getId()) {
            storeAuthViewModel.goBackToVendorLoginFragment(v);
        } else if (v.getId() == fragmentVendorSignupBinding.storeSignupButton.getId()) {
            TextView storeName = fragmentVendorSignupBinding.saloonName;
            TextView storeId = fragmentVendorSignupBinding.saloonId;
            TextView location = fragmentVendorSignupBinding.location;
            TextView pwd = fragmentVendorSignupBinding.password;
            TextView rpwd = fragmentVendorSignupBinding.confirmPassword;
            storeAuthViewModel.validateInfo(getContext(),fragmentVendorSignupBinding.storeSignupmainLayout,storeName, storeId, location, pwd, rpwd);
        }
    }

    public void initProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("VocabularyBattle"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.setCancelable(false);
    }

    public void goToHomeFragment() {
        NavController controller = Navigation.findNavController(getView());
        controller.navigate(R.id.action_vendorSignupFragment_to_storeHomeFragment);
    }

}
