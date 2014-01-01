package com.example.bmicalculator;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

public class WelcomeScreen extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_screen);
	}
	
	public void gotoClickHandler(View view) {
	     // make sure we handle the click of the calculator button

	     if (view.getId() == R.id.btnTblResult) {
	    	 Intent intent = new Intent(WelcomeScreen.this, TableResult.class);
	    	 startActivity(intent);	
	     }
	     
	     if (view.getId() == R.id.btnGraphResult) {
	    	 Toast toast=Toast.makeText(this, "Hello toast", 2000);
	    	      toast.setGravity(Gravity.TOP, -30, 50);
	    	      toast.show();
	     }
	     
	     if (view.getId() == R.id.btnGotoCalculator) {
	    	 Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
	    	 startActivity(intent);
	     }
	     
	     if (view.getId() == R.id.btnBMISrc) {
	    	 Intent intent = new Intent(WelcomeScreen.this, BMISrc.class);
	    	 startActivity(intent);
	     }
	     
	     
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	       super.onCreateOptionsMenu(menu);
	       MenuInflater inflater = getMenuInflater();
	       inflater.inflate(R.menu.country_menu, menu);
	       return true;
	}
}
