package com.dpk.saloon.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.dpk.saloon.R;

public class CustomLottieDialog extends Dialog {

    public CustomLottieDialog(@NonNull Context context) {
        super(context);
        WindowManager.LayoutParams params= getWindow().getAttributes();
        params.gravity= Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(params);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        View view= LayoutInflater.from(context).inflate(R.layout.dialog_lottie_loader,null);
        setContentView(view);
    }
}
