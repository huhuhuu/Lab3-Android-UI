package com.example.androidui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_toLinearLayout=(Button)findViewById(R.id.btn_toLinearLayout);
        btn_toLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MyListView.class);
                startActivity(intent);
            }
        });
        Button btn_toMyDialog=(Button)findViewById(R.id.btn_toMyDialog);
        btn_toMyDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, my_dialog.class);
                startActivity(intent);
            }
        });
        Button btn_show_menu=(Button)findViewById(R.id.btn_show_menu);
        btn_show_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TestMenu.class);
                startActivity(intent);
            }
        });
        Button btn_action_mode=(Button)findViewById(R.id.btn_action_mode);
        btn_action_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TestMenu.class);
                startActivity(intent);
            }
        });
    }
}
