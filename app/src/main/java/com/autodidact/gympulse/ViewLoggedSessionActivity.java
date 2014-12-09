package com.autodidact.gympulse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.autodidact.gympulse.entity.Exercise;
import com.autodidact.gympulse.entity.Session;

public class ViewLoggedSessionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_session);
        Intent myIntent = getIntent();
        Session session = (Session) myIntent.getSerializableExtra("currentSession");
        TableLayout table=(TableLayout)findViewById(R.id.sessionTable);

        for(Exercise exercise : session.getExercises()) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
            TextView exerciseName = new TextView(this);
            exerciseName.setText(exercise.getName());
            tableRow.addView(exerciseName);
            TextView sets = new TextView(this);
            sets.setText(exercise.getSets());
            tableRow.addView(sets);
            int setNumber = 0;
            while (setNumber < exercise.getSets()) {
                TextView reps = new TextView(this);
                reps.setText(String.valueOf(exercise.getLoggedReps(setNumber)));
                tableRow.addView(reps);
                setNumber++;
            }
            TextView weight = new TextView(this);
            weight.setText(String.valueOf(exercise.getWeight()));
            tableRow.addView(weight);
            table.addView(tableRow, new TableLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
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
