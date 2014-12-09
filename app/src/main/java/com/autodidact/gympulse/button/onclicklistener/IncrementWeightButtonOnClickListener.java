package com.autodidact.gympulse.button.onclicklistener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.autodidact.gympulse.SessionActivity;
import com.autodidact.gympulse.entity.Exercise;

/**
 * Created by timcoulson on 05/12/14.
 */
public class IncrementWeightButtonOnClickListener implements OnClickListener {

    private Button btn;
    private Exercise exercise;

    public IncrementWeightButtonOnClickListener(Button btn, Exercise exercise){
        this.btn = btn;
        this.exercise = exercise;
    }

    @Override
    public void onClick(View view){
        String label = (String) btn.getText().toString();

        if(label=="+"){
            btn.setText("=");
            exercise.keepSameWeight();
        } else if (label == "="){
            btn.setText("-");
            exercise.reduceWeight();
        } else {
            btn.setText("+");
            exercise.increaseWeight();
        }
    }

}



