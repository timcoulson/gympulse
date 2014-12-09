package com.autodidact.gympulse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.autodidact.gympulse.entity.Exercise;
import com.autodidact.gympulse.entity.Session;
import com.autodidact.gympulse.util.ChangeExerciseNameButtonOnClickListener;
import com.autodidact.gympulse.util.ChangeRepsButtonOnClickListener;
import com.autodidact.gympulse.util.ChangeRestButtonOnClickListener;
import com.autodidact.gympulse.util.ChangeSetsButtonOnClickListener;
import com.autodidact.gympulse.util.ChangeWeightButtonDesignOnClickListener;
import com.autodidact.gympulse.util.ChangeWeightButtonOnClickListener;
import com.autodidact.gympulse.util.IncrementWeightButtonOnClickListener;
import com.autodidact.gympulse.util.RepsButtonOnClickListener;

import java.util.ArrayList;


public class DesignSessionActivity extends Activity {

    TableLayout tl;
    int exerciseNumber = 0;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_session);

        final Intent myIntent = getIntent(); // gets the previously created intent

        String sessionName = myIntent.getStringExtra("session");

        Toast.makeText(this, sessionName, Toast.LENGTH_LONG).show();

        session = GymPulse.getPlan().getSessionFromName(sessionName);

        // Populate table with exercises
        this.tl=(TableLayout)findViewById(R.id.sessionTable);

        for(Exercise e : session.getExercises()) {
           addExerciseRow(e);
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

    public void addExercise(View view){
        Exercise e = session.addExercise();
        addExerciseRow(e);
        GymPulse.persistDB(this);
    }

    public void deleteExercise(View view){
        TableRow tr = (TableRow) tl.findViewWithTag("row"+exerciseNumber);
        tl.removeView(tr);
        exerciseNumber--;
        session.deleteLastExercise();
        GymPulse.persistDB(this);
    }


    public void addExerciseRow(Exercise e){
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
        TextView textview = new TextView(this);
        textview.setText(e.getName());

        Button changeNameBtn = new Button(this);
        changeNameBtn.setText(String.valueOf(e.getName()));
        changeNameBtn.setOnClickListener(new ChangeExerciseNameButtonOnClickListener(e, changeNameBtn));
        tr.addView(changeNameBtn);
        android.view.ViewGroup.LayoutParams params = changeNameBtn.getLayoutParams();
        //TODO why is this causing my text to reposition after click?
        params.height = 40;
        params.width = 60;
        changeNameBtn.setTextSize(10);
        changeNameBtn.setLayoutParams(params);

        Button changeRepsBtn = new Button(this);
        changeRepsBtn.setText(String.valueOf(e.getReps()));
        changeRepsBtn.setOnClickListener(new ChangeRepsButtonOnClickListener(e, changeRepsBtn));
        tr.addView(changeRepsBtn);
        android.view.ViewGroup.LayoutParams params2 = changeRepsBtn.getLayoutParams();
        //TODO why is this causing my text to reposition after click?
        params2.height = 40;
        params2.width = 40;
        changeRepsBtn.setTextSize(10);
        changeRepsBtn.setLayoutParams(params2);

        Button changeSetsBtn = new Button(this);
        changeSetsBtn.setText(String.valueOf(e.getSets()));
        changeSetsBtn.setOnClickListener(new ChangeSetsButtonOnClickListener(e, changeSetsBtn));
        tr.addView(changeSetsBtn);
        android.view.ViewGroup.LayoutParams params3 = changeSetsBtn.getLayoutParams();
        //TODO why is this causing my text to reposition after click?
        params3.height = 40;
        params3.width = 40;
        changeSetsBtn.setTextSize(10);
        changeSetsBtn.setLayoutParams(params3);

        Button changeWeightBtn = new Button(this);
        changeWeightBtn.setText(String.valueOf(e.getSets()));
        changeWeightBtn.setOnClickListener(new ChangeWeightButtonDesignOnClickListener(e, changeWeightBtn));
        tr.addView(changeWeightBtn);
        android.view.ViewGroup.LayoutParams params4= changeWeightBtn.getLayoutParams();
        //TODO why is this causing my text to reposition after click?
        params4.height = 40;
        params4.width = 40;
        changeWeightBtn.setTextSize(10);
        changeWeightBtn.setLayoutParams(params3);

        Button changeRestBtn = new Button(this);
        changeRestBtn.setText(String.valueOf(e.getSets()));
        changeRestBtn.setOnClickListener(new ChangeRestButtonOnClickListener(e, changeRestBtn));
        tr.addView(changeRestBtn);
        android.view.ViewGroup.LayoutParams params5= changeRestBtn.getLayoutParams();
        //TODO why is this causing my text to reposition after click?
        params5.height = 40;
        params5.width = 40;
        changeRestBtn.setTextSize(10);
        changeRestBtn.setLayoutParams(params3);


        exerciseNumber++;
        tr.setTag("row"+exerciseNumber);
        tl.addView(tr, new TableLayout.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));

    }



}
