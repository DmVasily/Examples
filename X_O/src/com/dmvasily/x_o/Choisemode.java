package com.dmvasily.x_o;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Choisemode extends Activity implements OnClickListener{
    Intent intent;
	Button alone,host,client;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choisemode);
		
		alone= (Button) findViewById(R.id.button1);
		alone.setOnClickListener(this);
		host= (Button) findViewById(R.id.button2);
		host.setOnClickListener(this);
		client= (Button) findViewById(R.id.button3);
		client.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {
	    switch (v.getId()) {
	    case R.id.button1:
	    intent = new Intent(this,X_O_startscreen.class);
	    startActivity(intent);
	    break;
	    }
	}
}