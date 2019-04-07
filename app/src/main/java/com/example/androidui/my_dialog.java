package com.example.androidui;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.Window;
import android.widget.Button;
import android.app.Dialog;
import android.content.Context;

public class my_dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialog);

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View newPlanDialog = layoutInflater.inflate(R.layout.my_dialog,
                (ViewGroup)findViewById(R.id.mydialog));

        AlertDialog.Builder builder = new AlertDialog.Builder(my_dialog.this)
                .setView(newPlanDialog);
        AlertDialog ad = builder.create();
        ad.show();
    }
}
