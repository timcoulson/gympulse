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
public class WeightDialog implements View.OnClickListener {

    private final Dialog dialog;
    Exercise exercise;
    Editable input;
    View view;
    Button btn;

    public WeightDialog(Dialog dialog, Exercise exercise, Editable input, View view, Button btn) {
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
                exercise.setWeight(Float.parseFloat(input.toString()));
                dialog.dismiss();
                btn.setText(String.valueOf(exercise.getWeight()));
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



