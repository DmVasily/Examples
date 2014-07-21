package com.example.p0171_dynamiclayout2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;



public class ActivityThree extends Activity {
	
	String name;
	int highScore;
	  SQLiteDatabase db;
      boolean was=false;
      int[] colors = new int[2];

	DBHelper dbHelper;
	 /* int highScore=0;
      //Toast.makeText(this, name + Integer.toString(highScore), Toast.LENGTH_SHORT).show();
	    db.delete("highScores", null, null);
        dbHelper.close();
	*/
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.three);
		DBHelper dbHelper =  new DBHelper(this);
	    
	    Intent intent = getIntent();
	    highScore = intent.getIntExtra("highScore", 0);
		name = intent.getStringExtra("name");
		
	      db = dbHelper.getWritableDatabase();
	      ContentValues cv = new ContentValues();
	      
	      //проверяем, есть ли имя в базе, обновляем highscore
	    	Cursor c = db.query("highScores", null, null, null, null, null, null);
	    	if (c.moveToFirst()) {
	        int idColIndex = c.getColumnIndex("id");
	        int nameColIndex = c.getColumnIndex("name");
	        int scoreColIndex = c.getColumnIndex("score");
	        int scoresearch;
	        int idsearch;
	        String namesearch;
	        do {
	        	idsearch=c.getInt(idColIndex);
		        namesearch=c.getString(nameColIndex);
		        scoresearch=c.getInt(scoreColIndex);
		    if (namesearch.equals(name) && scoresearch<highScore) {
			      cv.put("name", name);
			      cv.put("score", highScore);
		        db.update("highScores", cv, "id = "+ idsearch, null);
		        cv.clear();
		    }
		    if (namesearch.equals(name)){
		    	was=true;
		    }
	        //Toast.makeText(getApplicationContext(), c.getInt(idColIndex) + c.getString(nameColIndex)+c.getInt(scoreColIndex), Toast.LENGTH_SHORT).show();

	        } while (c.moveToNext());

	        }
	    
	    /*else {
		      cv.put("name", name);
		      cv.put("score", highScore);
		      long rowID = db.insert("highScores", null, cv);

	    }  */
	    	//добавляем запись, если имени нет
	    	if (was==false && name.equals("")==false && highScore!=0) {
			      cv.put("name", name);
			      cv.put("score", highScore);
			      long rowID = db.insert("highScores", null, cv);
	    	}
	      
	      //список
	        c = db.query("highScores", null, null, null, null, null, "score");

	    LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
	    LayoutInflater ltInflater = getLayoutInflater();
	    colors[0] = Color.parseColor("#559966CC");
	    colors[1] = Color.parseColor("#55336699");

    	if (c.moveToLast()) {
    		int count=10;
	        int idColIndex = c.getColumnIndex("id");
	        int nameColIndex = c.getColumnIndex("name");
	        int scoreColIndex = c.getColumnIndex("score");
	        int scoresearch;
	        int idsearch;
	        String namesearch;
	        do {
	        	idsearch=c.getInt(idColIndex);
		        namesearch=c.getString(nameColIndex);
		        scoresearch=c.getInt(scoreColIndex);

			    View item = ltInflater.inflate(R.layout.my_list_item, linLayout, false);
			      TextView textView11 = (TextView) item.findViewById(R.id.textView11);
			      textView11.setText(namesearch);
			      TextView textView22 = (TextView) item.findViewById(R.id.textView22);
			      textView22.setText(Integer.toString(scoresearch));
			      item.setBackgroundColor(colors[count % 2]);
			      linLayout.addView(item);
		        //Toast.makeText(getApplicationContext(), c.getInt(idColIndex) + c.getString(nameColIndex)+c.getInt(scoreColIndex), Toast.LENGTH_SHORT).show();
			      count--;
	        } while (c.moveToPrevious()&&count>0);

	        }

	    
	/*   //чтение последнего
	   c.moveToLast();
       int idColIndex = c.getColumnIndex("id");
       int nameColIndex = c.getColumnIndex("name");
       int scoreColIndex = c.getColumnIndex("score");
       Toast.makeText(getApplicationContext(), c.getInt(idColIndex) + c.getString(nameColIndex)+c.getInt(scoreColIndex), Toast.LENGTH_SHORT).show();

*/
		    c.close(); 
		    dbHelper.close();
	  }

	  class DBHelper extends SQLiteOpenHelper {

		    public DBHelper(Context context) {
		      // конструктор суперкласса
		      super(context, "highScores", null, 1);
		    }

		    @Override
		    public void onCreate(SQLiteDatabase db) {
		      // создаем таблицу с полями
		      db.execSQL("create table highScores ("
		          + "id integer primary key autoincrement," 
		          + "name text,"
		          + "score integer" + ");");
		    }

		    @Override
		    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		    } 
		    


	  
	  
	  
	

	  
	  }
	    public int gethighscore(String name) {
		      int h=0;
	    	//DBHelper dbHelper =  new DBHelper(this);

		  //   db = dbHelper.getWritableDatabase();
		   /*  //ContentValues cv = new ContentValues();
		    	Cursor c = db.query("highScores", null, null, null, null, null, null);
		    	if (c.moveToFirst()) {
		        int idColIndex = c.getColumnIndex("id");
		        int nameColIndex = c.getColumnIndex("name");
		        int scoreColIndex = c.getColumnIndex("score");
		        String namesearch;
		        do {
			        namesearch=c.getString(nameColIndex);
			    if (namesearch.equals(name)) {
			    	h=c.getInt(scoreColIndex);
			    	break;
			    }
		        //Toast.makeText(getApplicationContext(), c.getInt(idColIndex) + c.getString(nameColIndex)+c.getInt(scoreColIndex), Toast.LENGTH_SHORT).show();
		        } while (c.moveToNext());
		        }
		    	return h; */
		    			    //c.close(); 
		  //  dbHelper.close();

	    	return h;
	    }
	    //private final Context mCtx;
	    
	    
	  public ActivityThree()
	  {
		 // mCtx=ctx;
	  }
	  public void open() {
		  dbHelper = new DBHelper(this);
		    db = dbHelper.getWritableDatabase();
		  }

	  
	  
}
