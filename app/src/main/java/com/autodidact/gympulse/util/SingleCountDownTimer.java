package com.autodidact.gympulse.util;

import android.app.Activity;
import android.widget.TextView;
import com.autodidact.gympulse.R;

public class SingleCountDownTimer {

    private Boolean timerRunning = false;
    private static android.os.CountDownTimer timer;

    public SingleCountDownTimer(int rest, Activity activity){

        final TextView timerView = (TextView)activity.findViewById(R.id.timerText);

        //TODO how can I check if the timer is running
        try {
            timer.cancel();
        } catch (Exception e){
            // TODO add proper error handler
        }

        timer = new android.os.CountDownTimer(rest*1000,1000) {
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
