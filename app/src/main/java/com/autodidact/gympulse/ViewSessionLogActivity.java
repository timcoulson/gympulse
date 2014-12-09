package com.autodidact.gympulse;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.List;
import java.util.HashMap;
import com.autodidact.gympulse.entity.Session;

public class ViewSessionLogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_session_log);
        getActionBar().setTitle("Training Log");
        final ListView listview = (ListView) findViewById(R.id.listView);
        final String[] fromMapKey = new String[] {"sessionName", "timestamp"};
        final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
        ListAdapter adapter = new SimpleAdapter(this, GymPulseModel.getSessionLogList(),
                android.R.layout.simple_list_item_2,
                fromMapKey, toLayoutId);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                ViewSessionLogActivity context = (ViewSessionLogActivity) view.getContext();
                Intent intent = new Intent(context, ViewLoggedSessionActivity.class);
                Session session = GymPulseModel.getSession(position);
                intent.putExtra("currentSession", session);
                startActivity(intent);
            }

        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
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