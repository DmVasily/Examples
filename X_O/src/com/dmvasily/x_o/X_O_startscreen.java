package com.dmvasily.x_o;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class X_O_startscreen extends Activity implements OnClickListener {

	ImageButton three,six,nine;
	int W=3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_x__o_startscreen);
		
		three= (ImageButton) findViewById(R.id.three);
		three.setOnClickListener(this);

		six= (ImageButton) findViewById(R.id.six);
		six.setOnClickListener(this);

		nine= (ImageButton) findViewById(R.id.nine);
		nine.setOnClickListener(this);

	}
	  public void onClick(View v) {
	    switch (v.getId()) {
	    case R.id.three:
	    	W=3;
	    	break;
	    case R.id.six:
	    	W=6;
	    	break;
	    case R.id.nine:
	    	W=9;
	    	break;

	    }
	    Intent intent = new Intent(this,MainActivity.class);
	    intent.putExtra("W", W);
	    startActivityForResult(intent, 1);
	    }
	  
	  
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		  if (data == null) {		  //finish();
		  }
		 // finish();
		}

	  
}