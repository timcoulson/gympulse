package com.autodidact.gympulse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.autodidact.gympulse.entity.Exercise;
import com.autodidact.gympulse.entity.Session;
import com.autodidact.gympulse.util.ChangeWeightButtonOnClickListener;
import com.autodidact.gympulse.util.IncrementWeightButtonOnClickListener;
import com.autodidact.gympulse.util.RepsButtonOnClickListener;


public class ViewSessionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_session);

        Intent myIntent = getIntent(); // gets the previously created intent
        Session session = (Session) myIntent.getSerializableExtra("currentSession");

        // Populate table with exercises
        int exerciseNumber = 0;
        TableLayout tl=(TableLayout)findViewById(R.id.sessionTable);

        for(Exercise e : session.getExercises()) {

            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
            TextView textview = new TextView(this);
            textview.setText(e.getName());
            tr.addView(textview);
            int setNumber = 0;
            while (setNumber < e.getSets()) {
                TextView reps = new TextView(this);
                reps.setText(String.valueOf(e.getLoggedReps(setNumber)));
                tr.addView(reps);
                setNumber++;
            }

            TextView weight = new TextView(this);
            textview.setText(String.valueOf(e.getWeight()));
            tr.addView(weight);

            exerciseNumber++;
            tl.addView(tr, new TableLayout.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_session, menu);
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
