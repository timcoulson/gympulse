package com.autodidact.gympulse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.autodidact.gympulse.button.onclicklistener.ExerciseNameOnClickListener;
import com.autodidact.gympulse.button.onclicklistener.RepsOnClickListener;
import com.autodidact.gympulse.button.onclicklistener.RestOnClickListener;
import com.autodidact.gympulse.button.onclicklistener.SetsOnClickListener;
import com.autodidact.gympulse.button.onclicklistener.WeightDesignOnClickListener;
import com.autodidact.gympulse.entity.Exercise;
import com.autodidact.gympulse.entity.Session;

public class EditSessionActivity extends Activity {

    TableLayout tl;
    int exerciseNumber = 0;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_session);

        final Intent myIntent = getIntent(); // gets the previously created intent

        String sessionName = myIntent.getStringExtra("session");

        getActionBar().setTitle("Edit Session");

        session = GymPulseModel.getPlan().getSessionFromName(sessionName);

        // Populate table with exercises
        this.tl=(TableLayout)findViewById(R.id.sessionTable);

        for(Exercise e : session.getExercises()) {
           addExerciseRow(e);
        }

    }

    public void addExercise(View view){
        Exercise e = session.addExercise();
        addExerciseRow(e);
        GymPulseModel.persistDB(this);
    }

    public void deleteExercise(View view){
        TableRow tr = (TableRow) tl.findViewWithTag("row"+exerciseNumber);
        tl.removeView(tr);
        exerciseNumber--;
        session.deleteLastExercise();
        GymPulseModel.persistDB(this);
    }


    public void addExerciseRow(Exercise e){
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
        TextView textview = new TextView(this);
        textview.setText(e.getName());

        Button changeNameBtn = new Button(this);
        changeNameBtn.setText(String.valueOf(e.getName()));
        changeNameBtn.setOnClickListener(new ExerciseNameOnClickListener(e, changeNameBtn));
        tr.addView(changeNameBtn);
        android.view.ViewGroup.LayoutParams params = changeNameBtn.getLayoutParams();
        params.height = 80;
        params.width = 160;
        changeNameBtn.setTextSize(10);
        changeNameBtn.setLayoutParams(params);

        Button changeRepsBtn = new Button(this);
        changeRepsBtn.setText(String.valueOf(e.getReps()));
        changeRepsBtn.setOnClickListener(new RepsOnClickListener(e, changeRepsBtn));
        tr.addView(changeRepsBtn);
        android.view.ViewGroup.LayoutParams params2 = changeRepsBtn.getLayoutParams();
        //TODO why is this causing my text to reposition after click?
        params2.height = 80;
        params2.width = 80;
        changeRepsBtn.setTextSize(10);
        changeRepsBtn.setLayoutParams(params2);

        Button changeSetsBtn = new Button(this);
        changeSetsBtn.setText(String.valueOf(e.getSets()));
        changeSetsBtn.setOnClickListener(new SetsOnClickListener(e, changeSetsBtn));
        tr.addView(changeSetsBtn);
        android.view.ViewGroup.LayoutParams params3 = changeSetsBtn.getLayoutParams();
        //TODO why is this causing my text to reposition after click?
        params3.height = 80;
        params3.width = 80;
        changeSetsBtn.setTextSize(10);
        changeSetsBtn.setLayoutParams(params3);

        Button changeWeightBtn = new Button(this);
        changeWeightBtn.setText(String.valueOf(e.getWeight()));
        changeWeightBtn.setOnClickListener(new WeightDesignOnClickListener(e, changeWeightBtn));
        tr.addView(changeWeightBtn);
        android.view.ViewGroup.LayoutParams params4= changeWeightBtn.getLayoutParams();
        //TODO why is this causing my text to reposition after click?
        params4.height = 80;
        params4.width = 160;
        changeWeightBtn.setTextSize(10);
        changeWeightBtn.setLayoutParams(params4);

        Button changeRestBtn = new Button(this);
        changeRestBtn.setText(String.valueOf(e.getRest()));
        changeRestBtn.setOnClickListener(new RestOnClickListener(e, changeRestBtn));
        tr.addView(changeRestBtn);
        android.view.ViewGroup.LayoutParams params5= changeRestBtn.getLayoutParams();
        //TODO why is this causing my text to reposition after click?
        params5.height = 80;
        params5.width = 80;
        changeRestBtn.setTextSize(10);
        changeRestBtn.setLayoutParams(params5);


        exerciseNumber++;
        tr.setTag("row"+exerciseNumber);
        tl.addView(tr, new TableLayout.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));

    }



}
