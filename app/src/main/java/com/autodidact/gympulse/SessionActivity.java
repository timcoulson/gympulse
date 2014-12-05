package com.autodidact.gympulse;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.AbsListView.LayoutParams;
import android.widget.TextView;
import android.widget.NumberPicker;
import android.app.AlertDialog;
import java.util.ArrayList;
import com.autodidact.gympulse.entity.*;
import com.autodidact.gympulse.util.AddWeightButtonOnClickListener;
import com.autodidact.gympulse.util.ChangeWeightButtonOnClickListener;
import com.autodidact.gympulse.util.RepsButtonOnClickListener;

import android.view.View.OnClickListener;
import android.widget.Button;
public class SessionActivity extends Activity {

    private Plan plan = GymPulse.getPlan();
    private Session session = GymPulse.getPlan().getCurrentSession();
    private boolean timerRunning = false;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        // Populate table with exercises
        int btnId = 0;
        int exerciseNumber = 0;
        TableLayout tl=(TableLayout)findViewById(R.id.sessionTable);

        for(Exercise e : session.getExercises()) {

            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new LayoutParams( LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            TextView textview = new TextView(this);
            textview.setText(e.getName());
            tr.addView(textview);
            int setNumber = 0;
            while( setNumber < e.getSets() ) {
                Button btn = new Button(this);
                if (e.getLoggedReps(setNumber)<0) {
                    btn.setText("-");
                }
                else {
                    btn.setText(String.valueOf(e.getLoggedReps(setNumber)));
                }
                btn.setId(btnId);
                btn.setOnClickListener(new RepsButtonOnClickListener(exerciseNumber, setNumber, e.getRest()));
                tr.addView(btn);
                android.view.ViewGroup.LayoutParams params = btn.getLayoutParams();
                params.height = 40;
                params.width = 40;
                btn.setLayoutParams(params);

                btnId++;
                setNumber++;
            }

            Button changeWeightBtn = new Button(this);
            android.view.ViewGroup.LayoutParams params = changeWeightBtn.getLayoutParams();
            changeWeightBtn.setText(String.valueOf(e.getWeight()));
            changeWeightBtn.setId(btnId);
            changeWeightBtn.setOnClickListener(new ChangeWeightButtonOnClickListener(e));
            tr.addView(changeWeightBtn);
            params = changeWeightBtn.getLayoutParams();
            params.height = 40;
            params.width = 80;
            changeWeightBtn.setLayoutParams(params);
            btnId++;

            exerciseNumber++;
            tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        }


    }

    public boolean RepsButtonOnClickListener(int exerciseNumber, int setNumber){
        plan.skipSession();
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_session, menu);
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

    public int decrementRep(int exerciseNumber, int setNumber){
            return session.getExercises().get(exerciseNumber).decrementRep(setNumber);
    }

    public float incrementWeight(int exerciseNumber){
        return session.getExercises().get(exerciseNumber).incrementWeight();
    }


    public void finishSession(View view){
        GymPulse.saveSession(this.session);
        this.session = GymPulse.getPlan().skipSession();
        Intent intent = new Intent(this, ChooseSessionActivity.class);
        startActivity(intent);
    }

    public void countDownTimer(int rest){
        final TextView timerView = (TextView)findViewById(R.id.timerText);

        //TODO how can I check if the timer is running
        try {
            timer.cancel();
        } catch (Exception e){

}

        timer = new CountDownTimer(rest*1000,1000) {
public void onTick(long millisUntilFinished) {
        timerView.setText("" + millisUntilFinished / 1000);
        }

public void onFinish() {
        timerView.setText("");
        timerRunning = false;
        }
        }.start();

        }
}
