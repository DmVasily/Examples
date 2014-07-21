package com.example.p0171_dynamiclayout2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityTwo extends Activity {
	  EditText editSpeed;
	  Button Save;
	  Button Scores;
	  Button ResetScore;
	  String Reset="false";
	  SharedPreferences sPref;
	  String myName;
	  EditText editName;
	  int highScore=0;
	  String name;
	  ActivityThree three;
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.two);
		    Intent intent = getIntent();
		    
		   // String fName = intent.getStringExtra("fname");
		    loadName();
		    editSpeed = (EditText) findViewById(R.id.editSpeed);
		    int delayold = intent.getIntExtra("delaystart", 0);
		    highScore=intent.getIntExtra("highScore", 0);
	       // Toast.makeText(getApplicationContext(), Integer.toString(highScore), Toast.LENGTH_SHORT).show();

		    editSpeed.setText(Integer.toString(delayold));
		    
		    editName = (EditText) findViewById(R.id.editName);
		    editName.setText(myName);

	        name = editName.getText().toString();

		    Save = (Button) findViewById(R.id.Save);
		    Save.setOnClickListener(new View.OnClickListener() {
		    	
	        @Override
	        public void onClick(View v) {
	        	//three=new ActivityThree();
	        	//three.open();
	        	//int oldh=three.gethighscore(name);
	 	        //Toast.makeText(getApplicationContext(), Integer.toString(oldh), Toast.LENGTH_SHORT).show();
	    	    Intent intent = new Intent();
			    intent.putExtra("delaynew", editSpeed.getText().toString());
			    intent.putExtra("reset", Reset);
			  //  if (oldh>highScore) {
				//intent.putExtra("highscore", oldh);
			//    }
			//    else {
			//	intent.putExtra("highscore", highScore);
			//    }
			    setResult(RESULT_OK, intent);
			    saveName();
			    finish();  
			    
	        }
	    });
		    ResetScore = (Button) findViewById(R.id.ResetScore);
		    ResetScore.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        Reset="true";

	        }
	    });
		    Scores = (Button) findViewById(R.id.Scores);
		    Scores.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        	saveName();
	            Intent intent = new Intent(getApplicationContext(), ActivityThree.class);
	            intent.putExtra("name", name);
	            intent.putExtra("highScore", highScore);
	            startActivity(intent);

	        	
	        	
	        }
	    });
		    
		    
		    
		    
		    
		  }
	
	void saveName() {
	    sPref = getPreferences(MODE_PRIVATE);
	    Editor ed = sPref.edit();
	    ed.putString("myName", editName.getText().toString());
	    ed.commit();
	  }
	  
	  void loadName() {
	    sPref = getPreferences(MODE_PRIVATE);
	    myName = sPref.getString("myName", "");
	  }
	  
	  
	}
