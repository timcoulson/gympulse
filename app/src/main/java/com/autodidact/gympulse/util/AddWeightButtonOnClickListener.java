package com.autodidact.gympulse.util;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.content.DialogInterface;
import android.widget.Toast;

import com.autodidact.gympulse.SessionActivity;
import com.autodidact.gympulse.entity.Exercise;

/**
 * Created by timcoulson on 05/12/14.
 */
public class AddWeightButtonOnClickListener implements View.OnClickListener {

    private final Dialog dialog;
    Exercise exercise;
    Editable input;
    Button btn;

    public AddWeightButtonOnClickListener(Dialog dialog, Exercise exercise, Editable input, Button btn) {
        this.dialog = dialog;
        this.exercise = exercise;
        this.input = input;
        this.btn = btn;
    }
    @Override
    public void onClick(View view) {
        // put your code here
            if(validate(input.toString())){
                exercise.setWeight(Float.parseFloat(input.toString()));
                btn.setText(String.valueOf(exercise.getWeight()));
                dialog.dismiss();
            } else{
                Context context = view.getContext();
                Toast.makeText(context, "Please enter a number divisible by 2.5kg", Toast.LENGTH_SHORT).show();
            }
    }

    private static boolean validate(String weight)
    {
        try {
            float parsedWeight = Float.parseFloat(weight);
            if (parsedWeight%2.5!=0 || parsedWeight < 0){
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


