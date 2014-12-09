package com.autodidact.gympulse;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;

import com.autodidact.gympulse.entity.Plan;
import com.autodidact.gympulse.entity.Session;

import java.util.ArrayList;

import static com.autodidact.gympulse.util.InternalStorage.readObject;
import static com.autodidact.gympulse.util.InternalStorage.writeObject;


public class MainActivity extends Activity {

    public void main(String[] args){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GymPulse.initDB(this);
        GymPulse.persistDB(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void chooseSession(View view){
        Intent intent = new Intent(this, ChooseSessionActivity.class);
        startActivity(intent);

    }

    public void design(View view){
        Intent intent = new Intent(this, DesignActivity.class);
        startActivity(intent);
    }

    public void sessionLog(View view){
        Intent intent = new Intent(this, SessionLogActivity.class);
        startActivity(intent);
    }

}
