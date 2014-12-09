package com.autodidact.gympulse;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.autodidact.gympulse.util.TouchInterceptor;

public class ViewSessionsActivity extends ListActivity {
    private Object[] sArray =  GymPulseModel.getSessionList();
    private TouchInterceptor mList;
    private TouchInterceptor.DropListener mDropListener = new TouchInterceptor.DropListener() {
        public void drop(int from, int to) {
        //Assuming that item is moved up the list
        int direction = -1;
        int loop_start = from;
        int loop_end = to;
        //For instance where the item is dragged down the list
        if (from < to) {
            direction = 1;
        }
        Object target = sArray[from];
        for (int i = loop_start; i != loop_end; i = i + direction) {
            sArray[i] = sArray[i + direction];
        }
        sArray[to] = target;
        ((BaseAdapter) mList.getAdapter()).notifyDataSetChanged();
    }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        GymPulseModel.persistDB(this);
        ArrayAdapter adp = new ArrayAdapter(this, R.layout.listrow, sArray);
        setListAdapter(adp);
        mList = (TouchInterceptor) getListView();
        mList.setDropListener(mDropListener);
        registerForContextMenu(mList);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String selection = sArray[position].toString();
        ViewSessionsActivity context = (ViewSessionsActivity) v.getContext();
        Intent intent = new Intent(context, EditSessionActivity.class);
        intent.putExtra("session", selection);
        startActivity(intent);
    }
}