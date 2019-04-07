package com.example.androidui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.*;
import android.widget.*;
import android.view.*;

public class MyListView extends AppCompatActivity {

    private String[] names = new String[]{"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
    private int[] image=new int[]{R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        List<Map<String,Object>> ListItems=new ArrayList<Map<String, Object>>();
        for (int i=0;i<names.length;i++){
            Map<String,Object> listItem=new HashMap<String,Object>();
            listItem.put("content",names[i]);
            listItem.put("image",image[i]);
            //加入list集合
            ListItems.add(listItem);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,ListItems,R.layout.listview_item,new String[]{"content","image"},new int[]{R.id.content,R.id.image});
        final ListView list=(ListView)findViewById(R.id.mylistview);
        list.setAdapter(simpleAdapter);
        //对应点击事件
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                int flag=0;
                System.out.println(names[position]+position+"被单击");
                //点击则改变状态，改变颜色
                switch (flag){
                    case 0:
                        //view.setBackgroundColor(color1);
                        //此处对应上面布局文件的点击函数
                        view.setSelected(true);
                        CharSequence text=names[position];
                        //定义一个Toast表示哪一个图片所在item被点击
                        int duration= Toast.LENGTH_SHORT;
                        Toast toast=Toast.makeText(MyListView.this,text,duration);
                        toast.show();
                        flag=1;
                        break;
                    case 1:
                        //view.setBackgroundColor(0x0000FF);
                        view.setSelected(false);
                        CharSequence text1=names[position];
                        int duration1= Toast.LENGTH_SHORT;
                        Toast toast1=Toast .makeText(MyListView.this,text1,duration1);
                        toast1.show();
                        flag=0;
                        break;
                }
            }
        });
        //选中函数
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                System.out.println(names[position]+"选中");
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
    }
}
