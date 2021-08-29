package com.dpk.saloon.persistence;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.dpk.saloon.R;
import com.dpk.saloon.model.StoreUser;
import com.dpk.saloon.ui.CustomLottieDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StoreSigninRepository {
    private FirebaseAuth mAuth;
    private static final String TAG = "StoreSigninRepository";

    public void signin(String storeId, String pswd, Context context, CustomLottieDialog customLottieDialog, View view) {
        mAuth= FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(storeId, pswd)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            StoreUser storeUser = new StoreUser(storeId, pswd);
                            NavController controller = Navigation.findNavController(view);
                            controller.navigate(R.id.action_vendorLoginFragment_to_storeHomeFragment);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            customLottieDialog.dismiss();
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "Wrong username/password",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }
}