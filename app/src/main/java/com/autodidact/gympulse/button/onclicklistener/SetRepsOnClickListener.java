package com.autodidact.gympulse.button.onclicklistener;

import android.view.View;

import android.view.View.OnClickListener;
import android.widget.Button;

import com.autodidact.gympulse.ExecuteSessionActivity;
import com.autodidact.gympulse.entity.Exercise;

/**
 * Created by timcoulson on 05/12/14.
 */
public class SetRepsOnClickListener implements OnClickListener {

    int exerciseNumber;
    int setNumber;
    int rest;
    Button btn;
    boolean timerRunning = false;
    Exercise exercise;

    public SetRepsOnClickListener(int exerciseNumber, int setNumber, int rest, Button btn, Exercise exercise){
        this.exerciseNumber = exerciseNumber;
        this.setNumber = setNumber;
        this.rest = rest;
        this.btn = btn;
        this.exercise = exercise;
    }

    @Override
    public void onClick(View view){
        btn.setText(String.valueOf(exercise.decrementRep(setNumber)));
        ExecuteSessionActivity context = (ExecuteSessionActivity)view.getContext();
        context.startCountDownTimer(rest);
    }

}



