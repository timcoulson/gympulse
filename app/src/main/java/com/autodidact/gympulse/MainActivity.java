package com.autodidact.gympulse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setTitle("GymPulse");
        GymPulseModel.initDB(this);
    }

    public void chooseSession(View view){
        Intent intent = new Intent(this, ChooseSessionActivity.class);
        startActivity(intent);
    }

    public void design(View view){
        Intent intent = new Intent(this, ViewSessionsActivity.class);
        startActivity(intent);
    }

    public void sessionLog(View view){
        Intent intent = new Intent(this, ViewSessionLogActivity.class);
        startActivity(intent);
    }
}
