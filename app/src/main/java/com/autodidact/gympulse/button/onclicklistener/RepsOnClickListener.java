package com.autodidact.gympulse.button.onclicklistener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.autodidact.gympulse.DesignSessionActivity;
import com.autodidact.gympulse.button.dialog.RepsDialog;
import com.autodidact.gympulse.entity.Exercise;

/**
 * Created by timcoulson on 05/12/14.
 */
public class RepsOnClickListener implements OnClickListener {

    Exercise exercise;
    Button btn;

    public RepsOnClickListener(Exercise exercise, Button btn){
        this.exercise = exercise;
        this.btn = btn;
    }

    @Override
    public void onClick(View view){
        DesignSessionActivity context = (DesignSessionActivity) view.getContext();

        final EditText input = new EditText(context);

        AlertDialog.Builder setReps = new AlertDialog.Builder(context)
                .setTitle("Enter number of reps")
                .setView(input)
                .setPositiveButton("Ok", null);
        AlertDialog alertDialog = setReps.create();
        alertDialog.show();
        Button okButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        okButton.setOnClickListener(new RepsDialog(alertDialog,exercise,input.getText(),view, btn));
    }

}



