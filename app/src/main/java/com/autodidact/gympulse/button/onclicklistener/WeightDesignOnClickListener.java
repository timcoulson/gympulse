package com.autodidact.gympulse.button.onclicklistener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.autodidact.gympulse.DesignSessionActivity;
import com.autodidact.gympulse.button.dialog.WeightDialog;
import com.autodidact.gympulse.entity.Exercise;

/**
 * Created by timcoulson on 05/12/14.
 */
public class WeightDesignOnClickListener implements OnClickListener {

    Exercise exercise;
    Button btn;

    public WeightDesignOnClickListener(Exercise exercise, Button btn){
        this.exercise = exercise;
        this.btn = btn;
    }

    @Override
    public void onClick(View view){
        DesignSessionActivity context = (DesignSessionActivity) view.getContext();

        final EditText input = new EditText(context);

        AlertDialog.Builder setWeight = new AlertDialog.Builder(context)
                .setTitle("Set Weight")
                .setView(input)
                .setPositiveButton("Ok", null);
        AlertDialog alertDialog = setWeight.create();
        alertDialog.show();
        Button okButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        okButton.setOnClickListener(new WeightDialog(alertDialog,exercise,input.getText(),view, btn));
    }

}


