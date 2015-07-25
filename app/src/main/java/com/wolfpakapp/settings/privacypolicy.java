package com.wolfpakapp.settings;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


//ALL TEXT ON THE PRIVACY POLICY IS ON THE XML PAGE, AND IT IS UUUUUUGGGGLLLYYY, also eventually need to format it

public class privacypolicy extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacypolicy);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_privacypolicy, menu);
        return true;
    }

}