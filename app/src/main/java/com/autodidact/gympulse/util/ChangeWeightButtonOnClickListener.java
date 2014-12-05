package com.autodidact.gympulse.util;

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
public class ChangeWeightButtonOnClickListener implements OnClickListener {

    Exercise exercise;

    public ChangeWeightButtonOnClickListener(Exercise exercise){
        this.exercise = exercise;
    }

    @Override
    public void onClick(View view){
        SessionActivity context = (SessionActivity) view.getContext();
        Button btn = (Button)context.findViewById(view.getId());

        final EditText input = new EditText(context);

        AlertDialog.Builder setWeight = new AlertDialog.Builder(context)
                .setTitle("Set Weight")
                .setView(input)
                .setPositiveButton("Ok", null);
        AlertDialog alertDialog = setWeight.create();
        alertDialog.show();
        Button okButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        okButton.setOnClickListener(new AddWeightButtonOnClickListener(alertDialog,exercise,input.getText(),btn));

                //
           //
           //     .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
           //         public void onClick(DialogInterface dialog, int whichButton) {
                        // Do nothing.
            //        }


    }

}



