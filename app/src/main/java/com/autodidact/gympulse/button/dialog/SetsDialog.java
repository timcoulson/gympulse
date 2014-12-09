package com.autodidact.gympulse.button.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.autodidact.gympulse.entity.Exercise;

/**
 * Created by timcoulson on 05/12/14.
 */
public class SetsDialog implements View.OnClickListener {

    private final Dialog dialog;
    Exercise exercise;
    Editable input;
    View view;
    Button btn;

    public SetsDialog(Dialog dialog, Exercise exercise, Editable input, View view, Button btn) {
        this.dialog = dialog;
        this.exercise = exercise;
        this.input = input;
        this.view = view;
        this.btn = btn;
    }
    @Override
    public void onClick(View view) {
        // put your code here
            if(validate(input.toString())){
                exercise.setSets(Integer.parseInt(input.toString()));
                dialog.dismiss();
                btn.setText(String.valueOf(exercise.getSets()));
            } else{
                Context context = view.getContext();
                Toast.makeText(context, "Enter number of sets", Toast.LENGTH_SHORT).show();
            }
    }

    private static boolean validate(String reps)
    {
        try {
            float parsedReps = Integer.parseInt(reps);
            if (parsedReps < 0){
                return false;
            }
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }


}



