package com.autodidact.gympulse.button.onclicklistener;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;

import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.widget.Button;

import com.autodidact.gympulse.R;
import com.autodidact.gympulse.entity.Exercise;
import com.autodidact.gympulse.entity.Session;
import com.autodidact.gympulse.entity.Plan;

import android.app.Activity;
import android.widget.TextView;

import com.autodidact.gympulse.SessionActivity;

/**
 * Created by timcoulson on 05/12/14.
 */
public class RepsButtonOnClickListener implements OnClickListener {

    int exerciseNumber;
    int setNumber;
    int rest;
    Button btn;
    boolean timerRunning = false;
    Exercise exercise;

    public RepsButtonOnClickListener(int exerciseNumber, int setNumber, int rest, Button btn, Exercise exercise){
        this.exerciseNumber = exerciseNumber;
        this.setNumber = setNumber;
        this.rest = rest;
        this.btn = btn;
        this.exercise = exercise;
    }

    @Override
    public void onClick(View view){
        btn.setText(String.valueOf(exercise.decrementRep(setNumber)));
        SessionActivity context = (SessionActivity)view.getContext();
        context.countDownTimer(rest);
    }

}



