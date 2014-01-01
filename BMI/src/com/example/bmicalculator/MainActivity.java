package com.example.bmicalculator;


import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void calculateClickHandler(View view) {
	     // make sure we handle the click of the calculator button
		
		final EditText txtWeight = (EditText) findViewById(R.id.weightText);
		final TextView lblBMI = (TextView) findViewById(R.id.resultHolder);
		final Date dt = new Date();

		
	     if (view.getId() == R.id.btnSaveEntry) {
	    	 Intent intent = new Intent(MainActivity.this, AddEditCountry.class);
	    	 intent.putExtra("cap", txtWeight.getText().toString());
	    	 intent.putExtra("code", lblBMI.getText().toString());
	    	 intent.putExtra("name", dt.toString());
	    	 intent.putExtra("forceSave", "Yes");
	    	 startActivity(intent);	
	     }
	     
	     if (view.getId() == R.id.btnFB) {
	    	 Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
		     shareIntent.setType("text/plain");
		     shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Content to share");
		     PackageManager pm = view.getContext().getPackageManager();
		     List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
		     for (final ResolveInfo app : activityList) {
		         if ((app.activityInfo.name).contains("facebook")) {
		             final ActivityInfo activity = app.activityInfo;
		             final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
		             shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		             shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		             shareIntent.setComponent(name);
		             view.getContext().startActivity(shareIntent);
		             break;
		        }
		     }
	     }
	     
	     if (view.getId() == R.id.btnShare) {
	    	 	String message = "Whoa, look at my BMI :o: "+lblBMI.getText().toString();
			    Intent share = new Intent(Intent.ACTION_SEND);
			    share.setType("text/plain");
			    share.putExtra(Intent.EXTRA_TEXT, message);
			    startActivity(Intent.createChooser(share, "Title of the dialog the system will open"));
	     }
	    		 
	     if (view.getId() == R.id.calculateButton) {

	      // get the references to the widgets
	      EditText weightText = (EditText)findViewById(R.id.weightText);
	      EditText heightText = (EditText)findViewById(R.id.heightText);
	      TextView resultText = (TextView)findViewById(R.id.resultLabel);
	      TextView resultHolder = (TextView)findViewById(R.id.resultHolder);
	      
	      // get the users values from the widget references

	      float weight = Float.parseFloat(weightText.getText().toString());
	      float height = Float.parseFloat(heightText.getText().toString());
	 
	      // calculate the bmi value

	      float bmiValue = calculateBMI(weight, height);
	 
	      // interpret the meaning of the bmi value
	      String bmiInterpretation = interpretBMI(bmiValue);
	 
	      // now set the value in the result text
	      resultHolder.setText(Float.toString(bmiValue));
	      resultText.setText(bmiValue+"=>" + bmiInterpretation);
	     }
	     

	    }
	 
	    // the formula to calculate the BMI index

	    // check for http://en.wikipedia.org/wiki/Body_mass_index
	    private float calculateBMI (float weight, float height) {

	     return (float) (weight * 4.88 / (height * height));
	    }

	 
	    // interpret what BMI means
	    private String interpretBMI(float bmiValue) {

	     if (bmiValue < 16) {
	      return "You are severely underweight";
	     } else if (bmiValue < 18.5) {

	      return "You are underweight";
	     } else if (bmiValue < 25) {

	      return "Congrats! You are normal";
	     } else if (bmiValue < 30) {

	      return "You are overweight";
	     } else {
	      return "You are obese";
	     }

	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;    
	}

}
