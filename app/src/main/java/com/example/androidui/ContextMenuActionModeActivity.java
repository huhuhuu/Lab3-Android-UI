package com.example.androidui;

import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.MultiChoiceModeListener;
import java.util.ArrayList;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ContextMenuActionModeActivity extends AppCompatActivity {

    private ListView contact_list_view;
    private ProgressDialog mDialog;

    private MyContactAdapter mAdpater;
    private MultiModeCallback mCallback;

    // 妯℃嫙鏁版嵁
    private ArrayList<Contact> contacts;
    private String[] userNames = new String[] { "One", "Two", "Three", "Four", "Five", "Six" };
    private String[] phoneNumbers = new String[] { "", "", "", "",
            "", "" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_menu_action_mode);

        initView();

        new ContactsDownloadTask().execute();

    }

    private void initView() {
        contact_list_view = (ListView) this.findViewById(R.id.context_menu_listView);

        mDialog = new ProgressDialog(this);



        mCallback = new MultiModeCallback();
        contact_list_view.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        contact_list_view.setMultiChoiceModeListener(mCallback);
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
    //MultiChoiceModeListener锛屽閫夊鐞?    private class MultiModeCallback implements MultiChoiceModeListener {
    private View mMultiSelectActionBarView;
    private TextView mSelectedCount;

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        //鍦ˋctivity绫讳腑鏈変竴涓猤etMenuInflater()鐨勫嚱鏁扮敤鏉ヨ繑鍥炶繖涓狝ctivity鐨凪enuInflater锛?            // 骞堕€氳繃MenuInflater瀵硅薄鏉ヨ缃甿enu XML閲岀殑menu浣滀负璇ctivity鐨勮彍鍗?            //inflate灏辩浉褰撲簬灏嗕竴涓獂ml涓畾涔夌殑甯冨眬鎵惧嚭鏉?
        mode.getMenuInflater().inflate(R.menu.multi_acitonmode_menu, menu);

        mAdpater.setItemMultiCheckable(true);
        //notifyDataSetChanged鏂规硶閫氳繃涓€涓閮ㄧ殑鏂规硶鎺у埗濡傛灉閫傞厤鍣ㄧ殑鍐呭鏀瑰彉鏃堕渶瑕佸己鍒惰皟鐢╣etView鏉ュ埛鏂版瘡涓狪tem鐨勫唴瀹?
        mAdpater.notifyDataSetChanged();

        if (mMultiSelectActionBarView == null) {
            //閫氳繃LayoutInflater鐨刬nflate鏂规硶鏄犲皠涓€涓嚜宸卞畾涔夌殑Layout甯冨眬xml鍔犺浇鎴栦粠xxxView涓垱寤?
            mMultiSelectActionBarView = LayoutInflater.from(ContextMenuActionModeActivity.this)
                    .inflate(R.layout.list_multi_select_actionbar, null);
            mSelectedCount = (TextView) mMultiSelectActionBarView.findViewById(R.id.selected_conv_count);
        }
        mode.setCustomView(mMultiSelectActionBarView);



        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
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

}

