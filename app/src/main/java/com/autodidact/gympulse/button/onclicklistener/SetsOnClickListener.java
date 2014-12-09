package com.autodidact.gympulse.button.onclicklistener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.autodidact.gympulse.EditSessionActivity;
import com.autodidact.gympulse.button.dialog.SetsDialog;
import com.autodidact.gympulse.entity.Exercise;

/**
 * Created by timcoulson on 05/12/14.
 */
public class SetsOnClickListener implements OnClickListener {

    Exercise exercise;
    Button btn;

    public SetsOnClickListener(Exercise exercise, Button btn){
        this.exercise = exercise;
        this.btn = btn;
    }

    @Override
    public void onClick(View view){
        EditSessionActivity context = (EditSessionActivity) view.getContext();

        final EditText input = new EditText(context);

        AlertDialog.Builder setSets = new AlertDialog.Builder(context)
                .setTitle("Enter number of sets")
                .setView(input)
                .setPositiveButton("Ok", null);
        AlertDialog alertDialog = setSets.create();
        alertDialog.show();
        Button okButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        okButton.setOnClickListener(new SetsDialog(alertDialog,exercise,input.getText(),view, btn));
    }

}



