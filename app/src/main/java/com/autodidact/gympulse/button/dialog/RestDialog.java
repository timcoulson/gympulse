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
public class RestDialog implements View.OnClickListener {

    private final Dialog dialog;
    Exercise exercise;
    Editable input;
    View view;
    Button btn;

    public RestDialog(Dialog dialog, Exercise exercise, Editable input, View view, Button btn) {
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
                exercise.setRest(Integer.parseInt(input.toString()));
                dialog.dismiss();
                btn.setText(String.valueOf(exercise.getRest()));
            } else{
                Context context = view.getContext();
                Toast.makeText(context, "Enter rest in seconds", Toast.LENGTH_SHORT).show();
            }
    }

    private static boolean validate(String rest)
    {
        try {
            float parsedRest = Integer.parseInt(rest);
            if (parsedRest < 0){
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



