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
        getActionBar().setTitle(session.getName());

        TableLayout table=(TableLayout)findViewById(R.id.sessionTable);

        for(Exercise exercise : session.getExercises()) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
            TextView exerciseName = new TextView(this);
            exerciseName.setText(exercise.getName());
            tableRow.addView(exerciseName);
            TextView sets = new TextView(this);
            sets.setText(String.valueOf(exercise.getSets()));
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
}
