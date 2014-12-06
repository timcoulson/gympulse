package com.autodidact.gympulse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ListActivity;
import android.view.View;
import android.widget.ListAdapter;
import java.util.Map;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.View.OnClickListener;

import com.autodidact.gympulse.entity.Session;
import com.autodidact.gympulse.util.IncrementWeightButtonOnClickListener;

public class SessionLogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_log);

        final ListView listview = (ListView) findViewById(R.id.listView);

        final String[] fromMapKey = new String[] {"sessionName", "timestamp"};
        final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};

        ListAdapter adapter = new SimpleAdapter(this, GymPulse.getSessionLogList(),
                android.R.layout.simple_list_item_2,
                fromMapKey, toLayoutId);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                SessionLogActivity context = (SessionLogActivity) view.getContext();
                Intent intent = new Intent(context, ViewSessionActivity.class);
                Session session = GymPulse.getSession(position);
                intent.putExtra("currentSession", session);
                startActivity(intent);

            }

        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}


/*
public class SessionLogActivity extends Activity {

    private ListAdapter createListAdapter(){
        final String[] fromMapKey = new String[] {"text1", "text2"};
        final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
        final List<Map<String,String>> list = GymPulse.getSessionLogList();


        return new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, fromMapKey, toLayoutId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_log);

        final ListView listview = (ListView) findViewById(R.id.listView);
        createListAdapter();

        final ListAdapter adapter = (ListAdapter) createListAdapter();
        listview.setAdapter(createListAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_session_log, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
*/