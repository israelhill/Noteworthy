package com.noteworthy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class Classes extends ListActivity {

	private SimpleAdapter adapter;
	private ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

	private ArrayList<String> list1 = new ArrayList<String>();
	private ArrayList<String> list2 = new ArrayList<String>();
	private ArrayList<String> list3 = new ArrayList<String>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_classes);
        setTitle("My Classes");
        
        android.app.ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099FF")));
        ListView listview = (ListView) findViewById(android.R.id.list);
        
  
			try {
				populateList();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			// TODO Auto-generated catch block
        //Log.v("TGDB", String.valueOf(list.size()));
        

    }
    /**
    private void populateList() throws ParseException {
		list.clear();
		
		for(int i = 0; i < 9; i++) {
			HashMap<String,String> temp = new HashMap<String,String>();
			
			temp.put("class", "Fuck");
			temp.put("prof", "This");
			temp.put("school", "Shit" );
			list.add(temp);
		}
	}
    */
    
	private void populateList() throws ParseException {
		list.clear();
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Courses");
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> courses, ParseException e) {

				if (e == null) {
					for(ParseObject course : courses){
						Log.v("AppWoo", course.getString("courseName"));
						Log.v("AppWoo", course.getString("profName"));
						Log.v("AppWoo", course.getString("schoolName"));
//						list1.add(course.getString("courseName"));
//						list2.add(course.getString("profName"));
//						list3.add(course.getString("schoolName"));
						fluff(course);
					}
                } 
				else {
                    Log.v("AppWS", "Error: " + e.getMessage());
                }
			}
		});
		
		
		
		Log.v("asdf", String.valueOf(this.list1.size()));
		}
	
	public void fluff(ParseObject course) {
		Log.v("asdfasdfasdfas", course.getString("courseName"));
		list1.add(course.getString("courseName"));
		list2.add(course.getString("profName"));
		list3.add(course.getString("schoolName"));

		Log.v("Size1", String.valueOf(list1.size()));
		Log.v("Size2", String.valueOf(list2.size()));
		Log.v("Size3", String.valueOf(list3.size()));
		
		if(list1.size() == 3){
			for(int i = 0; i < list1.size(); i++) {
				HashMap<String,String> temp = new HashMap<String,String>();

				temp.put("class", list1.get(i));
				temp.put("prof", list2.get(i));
				temp.put("school", list3.get(i));
				
				
				list.add(temp);
			}
			
			Log.v("Size11111", String.valueOf(list1.size()));
			Log.v("Size22222", String.valueOf(list2.size()));
			Log.v("Size33333", String.valueOf(list3.size()));
			
			for(int j = 0; j < list.size(); j++){
				Log.v("Class", list.get(j).get("class").toString());
				Log.v("Teacher", list.get(j).get("prof").toString());
				Log.v("School", list.get(j).get("school").toString());
			}
			adapter = new SimpleAdapter(this, list, R.layout.custom_row_view, new String[]
					{"class","prof", "school"}, new int[] {R.id.header, R.id.text1, R.id.text2});
	      setListAdapter(adapter);
			
		}
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