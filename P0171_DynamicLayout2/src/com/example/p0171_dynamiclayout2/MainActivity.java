package com.example.p0171_dynamiclayout2;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
public class MainActivity extends Activity implements OnClickListener {
  private static final int ACTION_DOWN = 0;
Button btnCreate;
  ImageButton btnStart;
  ImageButton btnRestart;
  ImageButton btnOptions;
  TextView Score;
  int ide=0;
  int wrapContent = RelativeLayout.LayoutParams.WRAP_CONTENT;
  int width;
  int height;
  int xbelow=0;
  int ybelow=0;
  boolean food=true;
  int tx,ty;
  int fx,fy;
  boolean getfood=false;
  boolean stop=true;
  boolean check=true;
  boolean dead=false;
  RelativeLayout llMain;
  final RelativeLayout.LayoutParams lParams0 = new RelativeLayout.LayoutParams( 5, 5);
  int delaystart=100;
  int delaynew=delaystart;
  int delayms=delaystart;
  int highScore=0;
  String napravl="Up";
  SharedPreferences sPref;

  @SuppressLint("NewApi")
@Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
    llMain = (RelativeLayout) findViewById(R.id.llMain);
    
    btnCreate = (Button) findViewById(R.id.btnCreate);
    btnCreate.setOnClickListener(this);
    btnCreate.setVisibility(View.GONE);

    btnStart = (ImageButton) findViewById(R.id.Start);
    btnStart.setOnClickListener(this);
    
    btnRestart = (ImageButton) findViewById(R.id.Restart);
    btnRestart.setOnClickListener(this);
    
    btnOptions = (ImageButton) findViewById(R.id.Options);
    btnOptions.setOnClickListener(this);

    Score = (TextView) findViewById(R.id.Score);
    loadScore();
    Score.setText(Integer.toString(0)+"/"+Integer.toString(highScore));

    
    Display display = getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    width = size.x;
    height =size.y; 
    
    borders();    
    start();
    havchik ();
    //saveScore();
  }

  
  @Override
  public void onClick(View v) {
	  RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams( 5, 5);
    switch (v.getId()) {
    case R.id.btnCreate:
      Button btnNew = new Button(this);
      btnNew.setId(ide);
      btnNew.setBackgroundColor (0xFF00FF00 );
      if(ide==0) {
      lParams.topMargin=ty=ocrugl(height/2);
      lParams.leftMargin=tx=ocrugl(width/2);
      lParams0.topMargin=ocrugl(height/2);
      lParams0.leftMargin=ocrugl(width/2);
      btnNew.setBackgroundColor (0xffff0000);
      }
      else {
      lParams.topMargin=ybelow+5;
      lParams.leftMargin=xbelow;
      }
      llMain.addView(btnNew, lParams);
      xbelow=lParams.leftMargin;
      ybelow=lParams.topMargin;
      ide++;
      break;

    case R.id.Start:
    	if(check==true && dead==false) {
    	check=false;
    	stop=false;
    	Thread t = new Thread(new Runnable() {
        public void run() {
    	while(tx>0 && tx<width-5 && ty>0 &&ty<height-80&&stop==false &&dead==false) {
    	ha.sendEmptyMessage(1);
    	SystemClock.sleep(delayms);
    	}    	
        }
    	});
    	t.start();
    	}
    	break;
    
    case R.id.Restart:
        llMain.removeAllViews();
    	if ((ide-3)>highScore) {
    		saveScore(ide-3);
    }
        ide=0;
        food=true;
        xbelow=0;
        ybelow=0;
        food=true;
        getfood=false;
        stop=true;
        check=true;
        dead=false;
        napravl="Up";
        delaystart=delaynew;
        delayms=delaystart;
        loadScore();
        borders();
   		havchik ();
        start();
        Score.setText(Integer.toString(0)+"/"+Integer.toString(highScore));
        break;
        
    case R.id.Options:
    	if ((ide-3)>highScore) {
    		saveScore(ide-3);
    }
    	stop=true;
    	check=true;
        loadScore();
        Intent intent = new Intent(this, ActivityTwo.class);
        intent.putExtra("delaystart", delaystart);
        intent.putExtra("highScore", highScore);
        startActivityForResult(intent, 1);
        break;
    }
  }
  
  int location[] = new int[2];
  int xlast = 0,ylast=0;
  RelativeLayout.LayoutParams lParamsi = new RelativeLayout.LayoutParams( 5, 5);
	 int xxbelow;
	 int yybelow;
	  int location2[] = new int[2];

  Handler ha =new Handler()  {
	  public void handleMessage(android.os.Message msg) {

			 findViewById(0).getLocationOnScreen(location);
			 xxbelow=location[0];
			 yybelow=location[1];
		 
		  switch (napravl) {
		  case "Up":
		     lParams0.topMargin-=5;
		     ty-=5;
		     break;
		     
		  case "Right":
		     lParams0.leftMargin+=5;
			  tx+=5;
			  break;
		  case "Left":
		     lParams0.leftMargin-=5;
		     tx-=5;
		     break;

		  case "Down":
		     lParams0.topMargin+=5;
		     ty+=5;
		     break;
		     
			 }
		     findViewById(0).setLayoutParams(lParams0);
		  
		   for (int i=1;i<ide;i++) {
				 findViewById(i).getLocationOnScreen(location2);
				 lParamsi=(LayoutParams) findViewById(i).getLayoutParams();
				 if (ide-i==1) {
				 xlast=lParamsi.leftMargin;
		    	 ylast=lParamsi.topMargin;
				 }
		    	 if(location[0]==location2[0] &&location[1]==location2[1])
		    	 {
		    		dead=true;
		    	 }
			   	if (location2[0]<xxbelow) {
				     lParamsi.leftMargin+=5;
			   	}
			   	if (location2[0]>xxbelow) {
			 	 lParamsi.leftMargin-=5;
			   	}
			   	if (location2[1]<yybelow) {
				     lParamsi.topMargin+=5;
			   	}
			   	if (location2[1]>yybelow) {
				     lParamsi.topMargin-=5;
			   	}
			     findViewById(i).setLayoutParams(lParamsi);
				 xxbelow=location2[0];
				 yybelow=location2[1];
		   }
		   
		   if (getfood==true) {
			   getfood=false;
			   RelativeLayout.LayoutParams lParamsn = new RelativeLayout.LayoutParams( 5, 5);
		      Button btnNew = new Button(getApplicationContext());
		      btnNew.setId(ide);
		      btnNew.setBackgroundColor (0xFF00FF00);
		      lParamsn.topMargin=ylast;
		      lParamsn.leftMargin=xlast;
		      llMain.addView(btnNew, lParamsn);
              Score.setText(Integer.toString(ide-2)+"/"+Integer.toString(highScore));
              if(ide%2==0 && delayms>0.2*delaystart) {
            	  delayms-=1;
                            }
              ide++;
		   }
		      		   
			 if(tx==fx && ty==fy) {
			 getfood=true;
		      llMain.removeView(findViewById(999));
		      havchik();
			 }
	  };
	  };
  
  
      Random randomno = new Random();
	  RelativeLayout.LayoutParams lParamsh = new RelativeLayout.LayoutParams( 5, 5);

  void havchik () {
	  fx=lParamsh.leftMargin=5+ocrugl(randomno.nextInt( width-10));
	  fy=lParamsh.topMargin=5+ocrugl(randomno.nextInt( height-90));
      Button btnNew = new Button(this);
      btnNew.setId(999);
      btnNew.setBackgroundColor (0xff0000ff );
      llMain.addView(btnNew, lParamsh);
  }
  
  int ocrugl (int x) {
	  int z=x/5;
	  Math.floor(z);
	  return z*5;
  }
  
  public boolean onTouchEvent(MotionEvent event) {
	  if(event.getActionMasked()==ACTION_DOWN) {
		  int x = (int)event.getX();
		    int y = (int)event.getY();
			  int location[] = new int[2];
			 findViewById(0).getLocationOnScreen(location);
			 switch(napravl) {
			 case "Up":
				 if (x>location[0]) {
					napravl="Right";
					break;
				 }
				 if (x<location[0]) {
					napravl="Left";
					break;
				 }			 
			 case "Down":
				 if (x>location[0]) {
					napravl="Right";
					break;
				 }
				 if (x<location[0]) {
					napravl="Left";
					break;
				 }			 
				 
			 case "Left":
				 if (y>location[1]) {
					napravl="Down";
					break;
				 }			 
				 if (y<location[1]) {
					napravl="Up";
					break;
				 }			 
			 case "Right":
				 if (y>location[1]) {
					napravl="Down";
					break;
				 }				
				 if (y<location[1]) {
						napravl="Up";
						break;
					 }			 
			 }
	  }
		return false;
	}
  
public void borders() {
	  RelativeLayout.LayoutParams lParamu = new RelativeLayout.LayoutParams( 5, 5);
    Button upBorder = new Button(this);
    upBorder.setId(1000);
    upBorder.setBackgroundColor (0xff808080);
    lParamu.topMargin=0;
    lParamu.leftMargin=0;    
    lParamu.width=width;
    llMain.addView(upBorder, lParamu);
    
	  RelativeLayout.LayoutParams lParaml = new RelativeLayout.LayoutParams( 5, 5);
    Button leftBorder = new Button(this);
    leftBorder.setId(1001);
    leftBorder.setBackgroundColor (0xff808080);
    lParaml.topMargin=0;
    lParaml.leftMargin=0;
    lParaml.height=height;
    llMain.addView(leftBorder, lParaml);
    
	  RelativeLayout.LayoutParams lParamr = new RelativeLayout.LayoutParams( 5, 5);
	    Button rightBorder = new Button(this);
	    rightBorder.setId(1002);
	    rightBorder.setBackgroundColor (0xff808080);
	    lParamr.topMargin=0;
	    lParamr.leftMargin=width-5;
	    lParamr.height=height;
	    llMain.addView(rightBorder, lParamr);

		  RelativeLayout.LayoutParams lParamb = new RelativeLayout.LayoutParams( 5, 5);
		    Button bottomBorder = new Button(this);
		    bottomBorder.setId(1003);
		    bottomBorder.setBackgroundColor (0xff808080);
		    lParamb.topMargin=height-80;
		    lParamb.leftMargin=0;
		    lParamb.width=width;
		    llMain.addView(bottomBorder, lParamb);
}

public void start() {
    for(int i=0;i<3;i++) {
    onClick((btnCreate));
    }
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	stop=false;
  if (data == null) {return;}
  String del = data.getStringExtra("delaynew");
  delaynew=Integer.parseInt(del);
  String t=data.getStringExtra("reset");
 // int oldh=data.getIntExtra("highscore", highScore);
  if(t.equals("true"))
  {
	  saveScore(0);
	  loadScore();
      Score.setText(Integer.toString(ide-3)+"/"+Integer.toString(highScore));
  }
 // else {
//	  saveScore(oldh);
//	  loadScore();
 //     Score.setText(Integer.toString(ide-3)+"/"+Integer.toString(highScore));
//  }
}

void saveScore(int scor) {
    sPref = getPreferences(MODE_PRIVATE);
    Editor ed = sPref.edit();
    ed.putInt("highScore", scor);
    ed.commit();
  }
  
  void loadScore() {
    sPref = getPreferences(MODE_PRIVATE);
    highScore = sPref.getInt("highScore", 0);
  }



}