# Lab3-Android-UI
## Description: 
The use of SimpleAdapter、AlertDialog、menu、ActionMode.
## Core code:
```
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
        
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View newPlanDialog = layoutInflater.inflate(R.layout.my_dialog,
                (ViewGroup)findViewById(R.id.mydialog));

        AlertDialog.Builder builder = new AlertDialog.Builder(my_dialog.this)
                .setView(newPlanDialog);
        AlertDialog ad = builder.create();
        ad.show();
        
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
    
    private void downloadContactsFromServer() {
        if (contacts == null) {
            contacts = new ArrayList<Contact>();
        }

        for (int i = 0; i < userNames.length; i++) {
            contacts.add(new Contact(userNames[i], phoneNumbers[i]));
        }
    }

    private class ContactsDownloadTask extends AsyncTask<Void, Integer, Void> {

        private int currentlyProgressValue;

        @Override
        protected void onPreExecute() {
            mDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void  doInBackground(Void... params) {

            while (currentlyProgressValue < 100) {
                publishProgress(++currentlyProgressValue);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // download data from server
            downloadContactsFromServer();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            mAdpater = new MyContactAdapter(ContextMenuActionModeActivity.this, contacts);
            contact_list_view.setAdapter(mAdpater);

            mDialog.dismiss();
        }

    }
    
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_delete:
                mAdpater.deleteSeletedContacts();
                mAdpater.notifyDataSetChanged();
                mode.invalidate();
                mode.finish();
                break;

            default:
                break;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mAdpater.setItemMultiCheckable(false);
        mAdpater.clearSeletedContacts();
        mAdpater.notifyDataSetChanged();

    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        if (checked) {
            mAdpater.addSelectedContact(position);

        } else {
            mAdpater.cancelSeletedContact(position);
        }

        mAdpater.notifyDataSetChanged();

        updateSeletedCount();
        mode.invalidate();

    }

    public void updateSeletedCount() {
        mSelectedCount.setText(Integer.toString(contact_list_view.getCheckedItemCount()) + "selected");
    }

 <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:background="#ffffff"
        android:orientation="vertical">

        <RelativeLayout
            android:id="@+id/ll_content"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:gravity="center"
            >
            <ImageView
                android:id="@+id/img_header"
                android:layout_width="match_parent"
                android:layout_height="80dp"
                android:src="@drawable/header_logo"
                android:layout_gravity="center"
                android:adjustViewBounds="true"
                android:scaleType="fitXY"
                />
            <EditText
                android:id="@+id/edittext_username"
                android:layout_width="match_parent"
                android:layout_height="40dp"
                android:layout_below="@id/img_header"
                android:text="Username"
                android:background="@null"
                android:textCursorDrawable="@color/border"
                android:paddingLeft="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginRight="10dp"
                android:layout_marginBottom="0dp"
                android:layout_marginTop="20dp"
                />
            <View
                android:layout_width="match_parent"
                android:layout_height="1dp"
                android:layout_marginTop="0dp"
                android:background="#c0c0c0"
                android:layout_below="@id/edittext_username"
                android:layout_marginBottom="30dp"
                android:layout_marginLeft="10dp"
                android:layout_marginRight="10dp"
                />
            <EditText
                android:id="@+id/editview_password"
                android:layout_width="match_parent"
                android:layout_height="40dp"
                android:text="Password"
                android:background="@null"
                android:paddingLeft="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginBottom="0dp"
                android:layout_marginTop="10dp"
                android:layout_below="@id/edittext_username"
                />
            <View
                android:layout_width="match_parent"
                android:layout_marginLeft="10dp"
                android:layout_marginRight="10dp"
                android:layout_height="1dp"
                android:layout_below="@id/editview_password"
                android:layout_marginTop="0dp"
                android:background="#c0c0c0"
                android:layout_marginBottom="30dp"
                />
        </RelativeLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="55dp"
            android:layout_gravity="bottom"
            android:background="#FFFFFF"
            android:gravity="center"
            android:orientation="horizontal"
            android:padding="0dp"
            >
            <Button
                android:layout_margin="0dp"
                android:id="@+id/btn_cancel"
                android:layout_width="0dp"
                android:layout_weight="1"
                android:layout_height="match_parent"
                android:layout_marginLeft="20dp"
                android:textAllCaps="false"
                android:background="@drawable/shape"
                android:gravity="center"
                android:text="Cancel"
                android:textColor="#000000"
                android:textSize="15sp" />
            <Button
                android:id="@+id/btn_signin"
                android:layout_width="0dp"
                android:layout_margin="0dp"
                android:layout_weight="1"
                android:layout_height="match_parent"
                android:layout_marginLeft="20dp"
                android:gravity="center"
                android:text="Sign in"
                android:textColor="#000000"
                android:textSize="15sp"
                android:textAllCaps="false"
                android:background="@drawable/shape"
                />
        </LinearLayout>
    </LinearLayout>
    
    <?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/fontsize"
        android:title="字体大小"
        >
        <menu>
            <group>
                <item
                    android:id="@+id/fontsize_small"
                    android:title="@string/fontsize_small"></item>
                <item
                    android:id="@+id/fontsize_middle"
                    android:title="@string/fontsize_middle"></item>
                <item
                    android:id="@+id/fontsize_big"
                    android:title="@string/fontsize_big"></item>
            </group>
        </menu>
    </item>
    <item
        android:id="@+id/common_menu"
        android:title="@string/common_menu"
        >
    </item>
    <item
        android:id="@+id/fontcolor"
        android:title="@string/fontcolor"
        >
        <menu>
            <group>
                <item
                    android:id="@+id/fontcolor_red"
                    android:title="@string/fontcolor_red"
                    ></item>
                <item
                    android:id="@+id/fontcolor_black"
                    android:title="@string/fontcolor_black"
                    ></item>
            </group>
        </menu>
    </item>
</menu>

 <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="windowNoTitle">true</item>
    </style>
    <style name="AppThemeWithTitle" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="windowActionBar">true</item>
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="tooltipForegroundColor">@color/textColor</item>
    </style>
```
## Screenshots:<br>
![This picture is stored in the folder of this images.](https://github.com/huhuhuu/Lab3-Android-UI/blob/master/images/main.PNG)<br>
![This picture is stored in the folder of this images.](https://github.com/huhuhuu/Lab3-Android-UI/blob/master/images/listview.PNG)<br>
![This picture is stored in the folder of this images.](https://github.com/huhuhuu/Lab3-Android-UI/blob/master/images/dialog.PNG)<br>
![This picture is stored in the folder of this images.](https://github.com/huhuhuu/Lab3-Android-UI/blob/master/images/testmenu.PNG)<br>
![This picture is stored in the folder of this images.](https://github.com/huhuhuu/Lab3-Android-UI/blob/master/images/testmenu_smallfont.PNG)<br>
![This picture is stored in the folder of this images.](https://github.com/huhuhuu/Lab3-Android-UI/blob/master/images/testmenu_fontcolor.PNG)<br>
![This picture is stored in the folder of this images.](https://github.com/huhuhuu/Lab3-Android-UI/blob/master/images/testmenu_commonmenu.PNG)<br>
![This picture is stored in the folder of this images.](https://github.com/huhuhuu/Lab3-Android-UI/blob/master/images/actionmenu.PNG)<br>


