package com.autodidact.gympulse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.autodidact.gympulse.entity.Plan;

public class ChooseSessionActivity extends Activity {

    private Plan plan = GymPulseModel.getPlan();

    public void skipSession(View view){
        TextView tv = (TextView)findViewById(R.id.sessionName);
        tv.setText(GymPulseModel.skipSession(this));
    }

    public void beginWorkout(View view){
        Intent intent = new Intent(this, ExecuteSessionActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_session);
        TextView tv = (TextView)findViewById(R.id.sessionName);
        tv.setText(plan.getCurrentSession().getName());
        getActionBar().setTitle("GymPulse");
    }
}
