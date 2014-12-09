package com.autodidact.gympulse.util;

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
public class ChangeExerciseNameDialog implements View.OnClickListener {

    private final Dialog dialog;
    Exercise exercise;
    Editable input;
    View view;
    Button btn;

    public ChangeExerciseNameDialog(Dialog dialog, Exercise exercise, Editable input, View view, Button btn) {
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
                exercise.setName(input.toString());
                dialog.dismiss();
                btn.setText(String.valueOf(exercise.getName()));
            } else{
                Context context = view.getContext();
                Toast.makeText(context, "Please enter some text", Toast.LENGTH_SHORT).show();
            }
    }

    private static boolean validate(String weight)
    {
        try {
            if (weight.isEmpty()){
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



