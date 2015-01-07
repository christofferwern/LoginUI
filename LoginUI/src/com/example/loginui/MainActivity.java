package com.example.loginui;

import java.util.ArrayList;
import java.util.List;

import com.example.loginui.PasswordComponent.SecurityType;
import com.example.loginui.RegistrationComponent.Type;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity {
	RegistrationComponent RC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        RC = (RegistrationComponent) findViewById(R.id.registration);
        
        RC.setFieldBackgroundColor(Color.GREEN);
        RC.setHeaderColor(Color.RED);
        RC.setTextColor(Color.BLUE);
        RC.addField("Username", Type.DEFAULT);
        RC.addField("Password", Type.PASSWORD);
        RC.setPasswordLevel(SecurityType.HARD);
        RC.addField("Customfield", Type.DEFAULT, false);
        

    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
