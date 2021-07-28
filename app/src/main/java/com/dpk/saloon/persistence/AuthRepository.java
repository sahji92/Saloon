package com.dpk.saloon.persistence;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dpk.saloon.model.StoreUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthRepository {
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = rootRef.collection("/storeusers");
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private static final String TAG="Authrepository";


    public LiveData<StoreUser> createUserInFirestoreIfNotExists(StoreUser authenticatedStoreUser) {
        MutableLiveData<StoreUser> newUserMutableLiveData = new MutableLiveData<>();
        Log.d(TAG,"inside createuserinfirebase()");
        DocumentReference uidRef = usersRef.document(authenticatedStoreUser.getUid());
        uidRef.get().addOnCompleteListener(uidTask -> {
            if (uidTask.isSuccessful()) {
                Log.d(TAG,"inside uidtask.isSuccessful, createuserinfirebase()");
                DocumentSnapshot document = uidTask.getResult();
                if (!document.exists()) {
                    Log.d(TAG,"inside !document.exists, createuserinfirebase()");
                    uidRef.set(authenticatedStoreUser).addOnCompleteListener(userCreationTask -> {
                        if (userCreationTask.isSuccessful()) {
                            authenticatedStoreUser.setCreated(true);
                            newUserMutableLiveData.setValue(authenticatedStoreUser);
                            Log.d(TAG,"inside usercreationtask.isSuccessful, createuserinfirebase()");
                        } else {
                            Log.d(TAG,"inside usercreationtask.isNotSuccessful, createuserinfirebase()");
                            Log.e(TAG,userCreationTask.getException().getMessage());
                        }
                    });
                } else {
                    Log.d(TAG,"inside document exist, createuserinfirebase()");
                    newUserMutableLiveData.setValue(authenticatedStoreUser);
                }
            } else {
                Log.d(TAG,"inside !uidtask.isSuccessful, createuserinfirebase()");
                Log.e(TAG,uidTask.getException().getMessage());
            }
        });
        return newUserMutableLiveData;
    }
}
