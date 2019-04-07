package com.example.androidui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Toast;

public class TestMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_menu);
        setTitle("TestMenu");
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = new MenuInflater(this);//菜单动态加载类
        inflater.inflate(R.menu.menu1,menu);//调用inflate方法解析菜单文件
        super.onCreateOptionsMenu(menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem mi){
        TextView textView = (TextView)findViewById(R.id.textv_menu);
        switch (mi.getItemId()){
            case R.id.fontsize_small:
                textView.setTextSize(20);
                break;
            case R.id.fontsize_middle:
                textView.setTextSize(32);
                break;
            case R.id.fontsize_big:
                textView.setTextSize(40);
                break;
            case R.id.fontcolor_red:
                textView.setTextColor(Color.RED);
                break;
            case R.id.fontcolor_black:
                textView.setTextColor(Color.BLACK);
                break;
            case R.id.common_menu:
                Toast toast =Toast.makeText(TestMenu.this,"这是普通菜单项",Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
        return true;
    }
}
