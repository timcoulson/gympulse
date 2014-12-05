package com.autodidact.gympulse.util;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;

import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.widget.Button;

import com.autodidact.gympulse.R;
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
    boolean timerRunning = false;

    public RepsButtonOnClickListener(int exerciseNumber, int setNumber, int rest){
        this.exerciseNumber = exerciseNumber;
        this.setNumber = setNumber;
        this.rest = rest;



    }

    @Override
    public void onClick(View view){
        SessionActivity context = (SessionActivity) view.getContext();
        Button btn = (Button)context.findViewById(view.getId());
        btn.setText(String.valueOf(context.decrementRep(exerciseNumber, setNumber)));
        context.countDownTimer(rest);
    }

}



