package com.dpk.saloon.persistence;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.dpk.saloon.R;
import com.dpk.saloon.model.StoreUser;
import com.dpk.saloon.ui.CustomLottieDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class StoreSignupRepository {
    private FirebaseAuth mAuth;
    private FirebaseFirestore rootRef;
    private CollectionReference usersRef;
    private static final String TAG="StoreSignupRepository";
    private StoreUser storeUser;

    public void saveUserInFirestoreIfNotExists(StoreUser authenticatedStoreUser) {
        Log.d(TAG,"inside createuserinfirebase()");
        rootRef = FirebaseFirestore.getInstance();
        usersRef = rootRef.collection("/storeusers");
        DocumentReference uidRef = usersRef.document(authenticatedStoreUser.getUid());
        uidRef.get().addOnCompleteListener(uidTask -> {
            if (uidTask.isSuccessful()) {
                Log.d(TAG,"inside uidtask.isSuccessful, createuserinfirebase()");
                DocumentSnapshot document = uidTask.getResult();
                if (!document.exists()) {
                    Log.d(TAG,"inside !document.exists, createuserinfirebase()");
                    uidRef.set(authenticatedStoreUser).addOnCompleteListener(userCreationTask -> {
                        if (userCreationTask.isSuccessful()) {
                            Log.d(TAG,"inside usercreationtask.isSuccessful, createuserinfirebase()");
                        } else {
                            Log.d(TAG,"inside usercreationtask.isNotSuccessful, createuserinfirebase()");
                            Log.e(TAG,userCreationTask.getException().getMessage());
                        }
                    });
                } else {
                    Log.d(TAG,"inside document exist, createuserinfirebase()");
                }
            } else {
                Log.d(TAG,"inside !uidtask.isSuccessful, createuserinfirebase()");
                Log.e(TAG,uidTask.getException().getMessage());
            }
        });
    }

    public void signup(String storeName, String storeId, String password, Context context, LinearLayout storeSignupmainLayout, CustomLottieDialog progressDialog, View view) {
        mAuth= FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(storeId,password)
                    .addOnCompleteListener((Activity)context, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           FirebaseUser firebaseUser= task.getResult().getUser();
                            String usrname = storeId.substring(0, 3) +firebaseUser.getUid().substring(0, 2) + (int) (Math.random() * 200)+"@saloon";
                             storeUser = new StoreUser(firebaseUser.getUid(),usrname,storeName);
                        saveUserInFirestoreIfNotExists(storeUser);
                            progressDialog.dismiss();
                            NavController controller = Navigation.findNavController(view);
                            controller.navigate(R.id.action_vendorSignupFragment_to_addServicesFragment);
                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Snackbar.make(storeSignupmainLayout,"user already exist",Snackbar.LENGTH_LONG).setTextColor(context.getResources().getColor(R.color.red, context.getTheme()))
                                    .setAction("close", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                        }
                                    })
                                    .setActionTextColor(context.getResources().getColor(R.color.blue, context.getTheme()))
                                    .show();
                        }
                    });

    }

    public void saveStoreServices(ArrayList<String> storeServices, View view) {
        rootRef = FirebaseFirestore.getInstance();
        usersRef = rootRef.collection("/storeusers");
        DocumentReference uidRef = usersRef.document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        Log.d(TAG,"inside savestrsrvcs"+uidRef);
        uidRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
             StoreUser storeUser=documentSnapshot.toObject(StoreUser.class);
                Log.d(TAG,"inside savestrsrvcsss "+storeUser);
                storeUser.setStoreServices(storeServices);
                uidRef.set(storeUser);
            }
        });
        NavController controller = Navigation.findNavController(view);
        controller.navigate(R.id.action_addServicesFragment_to_storeHomeFragment);
    }
}

