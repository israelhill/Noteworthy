package com.noteworthy;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class CalendarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    
        ArrayList<Contributor> contributorList = new ArrayList<Contributor>();
    	//contributorList.add(new Contributor("contributor1","Larry Page","Vector 101","2"))
    	ListView listView = (ListView) findViewById(R.id.contributorsList);
    	
    	
    	Contributor[] items = { 
                new Contributor(R.drawable.ic_launcher, "Larry Page","Vector 101", 2), 
                new Contributor(R.drawable.ic_launcher, "Dave Fontenot","Calculus", 4), 
                new Contributor(R.drawable.ic_launcher, "Alexis Ohanian","Data Structures", 5), 
                new Contributor(R.drawable.ic_launcher, "Almighty Gaben","Brewing Science", 3), 
                new Contributor(R.drawable.ic_launcher, "Buckwheat","Science Fiction", 2)};
    	
    	ArrayList<Contributor> cList = new ArrayList<Contributor>();
    	
    	for(int i = 0; i < items.length; i++)
    	{
    		cList.add(items[i]);
    	}
    	ContributorAdapter adapter = new ContributorAdapter(this,R.layout.calendarlist_item, cList);
    	//adapter.notifyDataSetChanged();
    	listView.setAdapter(adapter);
//    	adapter.notifyDataSetChanged();
	}
	
	
	
	
	    
}





