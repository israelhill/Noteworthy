package com.noteworthy;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class Classes extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        setTitle("My Classes");
        
        android.app.ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099FF")));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.classes, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.add_button) {
        	createAddToast();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean createAddToast() {

        CharSequence colors[] = new CharSequence[] {"Class", "Notes"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("What do you want to add?");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	if (which == 0){
                	Intent myIntent = new Intent(Classes.this, AddClass.class);
                	//myIntent.putExtra("key", value); //Optional parameters
                	Classes.this.startActivity(myIntent);   
            	}
            }

        });
        builder.show();
        return true;
    }
}
