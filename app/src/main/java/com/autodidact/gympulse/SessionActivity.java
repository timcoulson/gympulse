package com.autodidact.gympulse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.AbsListView.LayoutParams;
import android.widget.TextView;

import com.autodidact.gympulse.button.onclicklistener.WeightOnClickListener;
import com.autodidact.gympulse.entity.*;
import com.autodidact.gympulse.button.onclicklistener.IncrementWeightButtonOnClickListener;
import com.autodidact.gympulse.button.onclicklistener.RepsButtonOnClickListener;

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
                btn.setOnClickListener(new RepsButtonOnClickListener(exerciseNumber, setNumber, e.getRest(),btn, e));
                tr.addView(btn);
                android.view.ViewGroup.LayoutParams params = btn.getLayoutParams();
                params.height = 40;
                params.width = 40;
                btn.setLayoutParams(params);

                setNumber++;
            }
            //
            Button changeWeightBtn = new Button(this);
            changeWeightBtn.setText(String.valueOf(e.getWeight()));
            changeWeightBtn.setOnClickListener(new WeightOnClickListener(e, changeWeightBtn));
            tr.addView(changeWeightBtn);
            android.view.ViewGroup.LayoutParams params = changeWeightBtn.getLayoutParams();
            //TODO why is this causing my text to reposition after click?
            params.height = 80;
            params.width = 60;
            changeWeightBtn.setTextSize(10);
            changeWeightBtn.setLayoutParams(params);

            Button incrementWeightBtn = new Button(this);
            incrementWeightBtn.setText("+");
            incrementWeightBtn.setOnClickListener(new IncrementWeightButtonOnClickListener(incrementWeightBtn, e));
            tr.addView(incrementWeightBtn);
            android.view.ViewGroup.LayoutParams params2 = incrementWeightBtn.getLayoutParams();
            params2.height = 40;
            params2.width = 40;
            incrementWeightBtn.setLayoutParams(params);


            exerciseNumber++;
            tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        }


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

    public void finishSession(View view){
        GymPulse.saveSession(this.session);
        this.session = GymPulse.getPlan().nextSession();
        GymPulse.persistDB(this);
        Intent intent = new Intent(this, ChooseSessionActivity.class);
        startActivity(intent);
    }

    public void countDownTimer(int rest){
        final TextView timerView = (TextView)findViewById(R.id.timerText);

        //TODO how can I check if the timer is running
        try {
            timer.cancel();
        } catch (Exception e){
            // TODO add proper error handler
            System.out.println("Error");
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
