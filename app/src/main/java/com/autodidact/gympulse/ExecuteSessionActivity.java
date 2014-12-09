package com.autodidact.gympulse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.AbsListView.LayoutParams;
import android.widget.TextView;
import android.widget.Button;
import com.autodidact.gympulse.button.onclicklistener.SetRepsOnClickListener;
import com.autodidact.gympulse.button.onclicklistener.WeightOnClickListener;
import com.autodidact.gympulse.entity.*;
import com.autodidact.gympulse.button.onclicklistener.IncrementWeightButtonOnClickListener;
import java.util.ArrayList;

public class ExecuteSessionActivity extends Activity {

    private Plan plan = GymPulseModel.getPlan();
    private Session session = plan.getCurrentSession();
    private ArrayList<Exercise> exercises = session.getExercises();
    private boolean timerRunning = false;
    private static CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execute_session);
        TableLayout tl=(TableLayout)findViewById(R.id.sessionTable);

        getActionBar().setTitle(session.getName());

        int exerciseNumber = 0;
        for(Exercise exercise : exercises) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new LayoutParams( LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            TextView exerciseName = new TextView(this);
            exerciseName.setText(exercise.getName());
            tableRow.addView(exerciseName);
            int setNumber = 0;
            while( setNumber < exercise.getSets() ) {
                Button repsButton = new Button(this);
                if (exercise.getLoggedReps(setNumber)<0) {
                    repsButton.setText("-");
                }
                else {
                    repsButton.setText(String.valueOf(exercise.getLoggedReps(setNumber)));
                }
                repsButton.setOnClickListener(new SetRepsOnClickListener(exerciseNumber, setNumber, exercise.getRest(),repsButton, exercise));
                tableRow.addView(repsButton);
                android.view.ViewGroup.LayoutParams repParams = repsButton.getLayoutParams();
                repParams.height = 80;
                repParams.width = 80;
                repsButton.setLayoutParams(repParams);
                setNumber++;
            }
            Button weightButton = new Button(this);
            weightButton.setText(String.valueOf(exercise.getWeight()));
            weightButton.setOnClickListener(new WeightOnClickListener(exercise, weightButton));
            tableRow.addView(weightButton);
            ViewGroup.LayoutParams weightParams = weightButton.getLayoutParams();
            //TODO why is this causing my text to reposition after click?
            weightParams.height = 80;
            weightParams.width = 160;
            weightButton.setTextSize(10);
            weightButton.setLayoutParams(weightParams);
            Button incrementButton = new Button(this);
            incrementButton.setText("+");
            incrementButton.setOnClickListener(new IncrementWeightButtonOnClickListener(incrementButton, exercise));
            tableRow.addView(incrementButton);
            ViewGroup.LayoutParams incrementParams = incrementButton.getLayoutParams();
            incrementParams.height = 80;
            incrementParams.width = 80;
            incrementButton.setLayoutParams(incrementParams);
            exerciseNumber++;
            tl.addView(tableRow, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }
    }

    public void finishSession(View view){
        GymPulseModel.saveSession(session, this);
        Intent intent = new Intent(this, ChooseSessionActivity.class);
        startActivity(intent);
    }

    public void startCountDownTimer(int rest){
        final TextView timerView = (TextView)findViewById(R.id.timerText);
        //TODO how can I check if the timer is running
        try {
            timer.cancel();
        } catch (Exception e){
            // TODO add proper error handler
        }
        timer = new CountDownTimer(rest*1000,1000) {
            public void onTick(long millisUntilFinished) {
                timerView.setText((millisUntilFinished / 1000)+" seconds");
            }
            public void onFinish() {
                timerView.setText("");
                timerRunning = false;
            }
        }.start();

    }
}
