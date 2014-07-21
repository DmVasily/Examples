package com.dmvasily.x_o;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
int w=6;
int id=1;
int borderwidth=10;
int width,height,smallest;
RelativeLayout main;
RelativeLayout gameLayout;
int[][] massiv; 
boolean xmove=true;
TextView statusbar;
ImageButton restart;
SurfaceView xr;
SurfaceView yr;
SurfaceView won;
int color=Color.parseColor("#77c0c0c0");
int whitecolor=Color.parseColor("#00FFFFFF");
boolean stopwon=true;

/* private static ServerSocket serverSocket;
private static Socket clientSocket;
private static InputStreamReader inputStreamReader;
private static BufferedReader bufferedReader;
private static String message="hello";
private static String messsage;
private Socket client;
private PrintWriter printwriter; */


	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

	    Intent intent = getIntent();
	    w=intent.getIntExtra("W", 6);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		gameLayout = (RelativeLayout) findViewById(R.id.gameLayout);
		main = (RelativeLayout) findViewById(R.id.main);

		statusbar=(TextView) findViewById(R.id.statusbar);
		restart= (ImageButton) findViewById(R.id.restart);
		restart.setOnClickListener(this);
		xr=new MySurfaceView(this,R.drawable.x);
		yr=new MySurfaceView(this,R.drawable.o);
		won=new MySurfaceView(this,R.drawable.won);
		  RelativeLayout.LayoutParams lParamsx = new RelativeLayout.LayoutParams(50, 50);
		  RelativeLayout.LayoutParams lParamsy = new RelativeLayout.LayoutParams(50, 50);
		  RelativeLayout.LayoutParams lParamswon = new RelativeLayout.LayoutParams(130, 50);

		main.addView(xr,lParamsx);
		lParamsy.leftMargin=60;
		main.addView(yr,lParamsy);
		lParamswon.leftMargin=120;
		main.addView(won,lParamswon);
	    Display display = getWindowManager().getDefaultDisplay();
	    Point size = new Point();
	    display.getSize(size);
	    width = size.x;
	    height =size.y; 
	    if(width>height)
	    	smallest=height;
	    else
	    	smallest=width;
	    massiv=new int[w+2][w+2];
	    if (w>=3 && w<6) {
		borderwidth=6;	
		}
		if (w>=6 && w<9) {
		borderwidth=4;	
		}
		if (w>=9) {
		borderwidth=2;	
		}
		create(w);
		//((MySurfaceView) xr).TurnOnThread();
		//((MySurfaceView) yr).TurnOffThread();
	  //  Toast.makeText(getApplicationContext(), messsage, Toast.LENGTH_SHORT).show();
		//sendthread.start();
	/*	SimpleTextServer get = new SimpleTextServer ();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			get.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		else
			get.execute(); */

	}
	
	
	public void create(int q) {	
			int r=((smallest-(borderwidth*(q+1)))/q);
			Math.floor(r);
			for (int y=0;y<q;y++) {
			for (int x=0;x<q;x++) {
		  RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(r, r);
	      ImageButton btnNew = new ImageButton(this);
	      lParams.setMargins(borderwidth+r*x+borderwidth*x, borderwidth+r*y+borderwidth*y, 0, 0);
	      btnNew.setId(id);
	      btnNew.setBackgroundColor (color );
	      btnNew.setPadding(10,10,10,10);
	      gameLayout.addView(btnNew, lParams);
	      btnNew.setOnClickListener(this);
	      massiv[x][y]=0;
	      id++;

			}
			}


	}


	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		int ide=v.getId();
		if (ide<=id) {
		int x=((ide-1)%w);
		int y=(ide-1)/w;
		Math.floor(y);
		boolean win=false;
		
		
		////////////////
		
	/*	Sendinfo sendMessageTask = new Sendinfo();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			sendMessageTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		else
			sendMessageTask.execute();
*/
		
		
		
		
		
	//	Sendinfo sendMessageTask = new Sendinfo();
	//	sendMessageTask.execute();
		////////////////
		//SimpleTextServer get = new SimpleTextServer ();
		//get.execute();
		//startService();
		
		
    	//hsend.sendEmptyMessage(1);
		//Toast.makeText(getApplicationContext(), messsage, Toast.LENGTH_SHORT).show();

	    //Toast.makeText(getApplicationContext(), Integer.toString(y), Toast.LENGTH_SHORT).show();
		findViewById(ide).setEnabled(false);
		if (xmove) { 
		findViewById(ide).setBackgroundResource(R.drawable.x);
		xmove=false;
		massiv[x][y]=1;
		statusbar.setText("O MOVE");
		win=checkWin(1);
		if (win) {
		statusbar.setText("X WON");
		unactiv();
		stopwon=false;
		((MySurfaceView) won).TurnOnThread();
		}
		else 
			statusbar.setText("O MOVE");
		((MySurfaceView) xr).TurnOffThread();
		((MySurfaceView) yr).TurnOnThread();
		}
		else {
			findViewById(ide).setBackgroundResource(R.drawable.o);
			xmove=true;
			massiv[x][y]=2;
			statusbar.setText("X MOVE");
			win=checkWin(2);
			if (win) {
				statusbar.setText("O WON");
				unactiv();
				stopwon=false;
				((MySurfaceView) won).TurnOnThread();
				}
				else 
					statusbar.setText("X MOVE");
			((MySurfaceView) xr).TurnOnThread();
			((MySurfaceView) yr).TurnOffThread();
		}
		}
		if (ide==R.id.restart)
		{
			id=1;
			gameLayout.removeAllViews();
			create(w);
			((MySurfaceView) won).TurnOffThread();
		}
	}
	public boolean checkWin(int u) {
		for (int y=0;y<w;y++) {
		for (int x=0;x<w;x++) {
		if ((massiv[x][y]==u)&&w<=3) {
			if((massiv[x+1][y]==u)&&(massiv[x+2][y]==u)||(massiv[x][y+1]==u)&&(massiv[x][y+2]==u)||(massiv[x+1][y+1]==u)&&(massiv[x+2][y+2]==u)) {
				return true;
			}
			if ((y>=2)&&(massiv[x+1][y-1]==u)&&(massiv[x+2][y-2]==u))
				return true;
			if ((x>=2)&&(y>=2)&&(massiv[x-1][y-1]==u)&&(massiv[x-2][y-2]==u))
				return true;
			if ((x>=2)&&(massiv[x-1][y+1]==u)&&(massiv[x-2][y+2]==u))
				return true;

		}
		if ((massiv[x][y]==u)&&w<=6&&w>3) {
			if((massiv[x+1][y]==u)&&(massiv[x+2][y]==u)&&(massiv[x+3][y]==u)||(massiv[x][y+1]==u)&&(massiv[x][y+2]==u)&&(massiv[x][y+3]==u)||(massiv[x+1][y+1]==u)&&(massiv[x+2][y+2]==u)&&(massiv[x+3][y+3]==u)) {
				return true;
			}
			if ((y>=3)&&(massiv[x+1][y-1]==u)&&(massiv[x+2][y-2]==u)&&(massiv[x+3][y-3]==u))
				return true;
			if ((x>=3)&&(y>=3)&&(massiv[x-1][y-1]==u)&&(massiv[x-2][y-2]==u)&&(massiv[x-3][y-3]==u))
				return true;
			if ((x>=3)&&(massiv[x-1][y+1]==u)&&(massiv[x-2][y+2]==u)&&(massiv[x-3][y+3]==u))
				return true;

		}
		if ((massiv[x][y]==u)&&w>6) {
			if((massiv[x+1][y]==u)&&(massiv[x+2][y]==u)&&(massiv[x+3][y]==u)&&(massiv[x+4][y]==u)||(massiv[x][y+1]==u)&&(massiv[x][y+2]==u)&&(massiv[x][y+3]==u)&&(massiv[x][y+4]==u)||(massiv[x+1][y+1]==u)&&(massiv[x+2][y+2]==u)&&(massiv[x+3][y+3]==u)&&(massiv[x+4][y+4]==u)) {
				return true;
			}
			if ((y>=4)&&(massiv[x+1][y-1]==u)&&(massiv[x+2][y-2]==u)&&(massiv[x+3][y-3]==u)&&(massiv[x+4][y-4]==u))
				return true;
			if ((x>=4)&&(y>=4)&&(massiv[x-1][y-1]==u)&&(massiv[x-2][y-2]==u)&&(massiv[x-3][y-3]==u)&&(massiv[x-4][y-4]==u))
				return true;
			if ((x>=4)&&(massiv[x-1][y+1]==u)&&(massiv[x-2][y+2]==u)&&(massiv[x-3][y+3]==u)&&(massiv[x-4][y+4]==u))
				return true;

		}
		}
		}
		return false;
	}
	public void unactiv() {
		for (int i=1;i<id;i++) {
			findViewById(i).setEnabled(false);
		}
	}

	
	class DrawThread extends Thread{
	    private boolean runFlag = false;
	    private SurfaceHolder surfaceHolder;
	    private Bitmap picture;
	    private Matrix matrix;
	    private long prevTime;
	    private boolean paused;
	    int Png;
	    public DrawThread(SurfaceHolder surfaceHolder, Resources resources,int png){
	        this.surfaceHolder = surfaceHolder;	
	        this.Png=png;
	        // загружаем картинку, которую будем отрисовывать
	        picture = BitmapFactory.decodeResource(resources, png);
	        // формируем матрицу преобразований дл€ картинки
	        matrix = new Matrix();
	       matrix.postScale(0.8f, 0.8f);
	       // matrix.postTranslate(100.0f, 100.0f);

	        // сохран€ем текущее врем€
	        prevTime = System.currentTimeMillis();
	    }

	    public void setRunning(boolean run) {
	        runFlag = run;
	    }

	    @Override
	    public void run() {
	        Canvas canvas;
	        float x,y;
	        x=1;
	        y=1;
	        boolean t=true;
	        while (runFlag) {
	            // получаем текущее врем€ и вычисл€ем разницу с предыдущим 
	            // сохраненным моментом времени
	            long now = System.currentTimeMillis();
	            long elapsedTime = now - prevTime;
	            if (elapsedTime > 30){
	                // если прошло больше 30 миллисекунд - сохраним текущее врем€
	                // и повернем картинку на 2 градуса.
	                // точка вращени€ - центр картинки
	                prevTime = now;
	                if((paused==false) &&(Png==R.drawable.x||Png==R.drawable.o)) {
	                matrix.preRotate(2.0f, picture.getWidth() / 2, picture.getHeight() / 2); 
	                }
	               if((paused==false)&&(Png==R.drawable.won)&&stopwon==false) {
	               matrix.setScale(0.8f, 0.8f*x);
	                if(x>0.3&&t==true)
	                x-=0.05;
	                if(x<=0.35)
	                	t=false;
	                if(x<0.99&&t==false)
	                	x+=0.05;
	                if(x>=0.95)
	                	t=true;
	                 }
	            }
	            canvas = null;
	            try {
	                // получаем объект Canvas и выполн€ем отрисовку
	                canvas = surfaceHolder.lockCanvas(null);
	                synchronized (surfaceHolder) {
	                    canvas.drawColor(Color.WHITE);
	                    canvas.drawBitmap(picture, matrix, null);
	                }
	            } 
	            finally {
	                if (canvas != null) {
	                    // отрисовка выполнена. выводим результат на экран
	                    surfaceHolder.unlockCanvasAndPost(canvas);
	                }
	            }
	        }
	     

	    }
	    public void onPause(boolean p) {
	    paused=p;	
	    }

	}

	
	public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	    private DrawThread drawThread;
	    int Png;
	    public MySurfaceView(Context context,int png) {
	        super(context);
	        getHolder().addCallback(this);
	        Png=png;
	    }

	    @Override
	    public void surfaceChanged(SurfaceHolder holder, int format, int width,
	            int height) {	
	    }

	    @Override
	    public void surfaceCreated(SurfaceHolder holder) {
	        drawThread = new DrawThread(getHolder(), getResources(),Png);
	        drawThread.setRunning(true);
	        drawThread.start();
	    }

	    @Override
	    public void surfaceDestroyed(SurfaceHolder holder) {
	        boolean retry = true;
	        // завершаем работу потока
	        drawThread.setRunning(false);
	        while (retry) {
	            try {
	                drawThread.join();
	                retry = false;
	            } catch (InterruptedException e) {
	                // если не получилось, то будем пытатьс€ еще и еще
	            }
	        }
	    }
	    public void TurnOffThread ()
	    {
	    	drawThread.onPause(true);

	    }
	    public void TurnOnThread ()
	    {
	    	drawThread.onPause(false);
	    }
	    public void destroy() {
	        this.drawThread.setRunning(false);
	    }
	}

	@Override
	public void onPause(){
	    super.onDestroy();
	    ((MySurfaceView) xr).destroy();
	    ((MySurfaceView) yr).destroy();
	    ((MySurfaceView) won).destroy();
	    Intent intent = new Intent();
	    setResult(RESULT_OK, intent);
	    finish();
	}
	@Override
	  protected void onDestroy() {
	    super.onDestroy();
	    ((MySurfaceView) xr).destroy();
	    ((MySurfaceView) yr).destroy();
	    ((MySurfaceView) won).destroy();
	    Intent intent = new Intent();
	    setResult(RESULT_OK, intent);
	    finish();
	  }
	
	
	/*
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  if (data == null) {return;}
	  int buf=data.getIntExtra("W", 3);
	  Toast.makeText(getApplicationContext(), Integer.toString(buf), Toast.LENGTH_SHORT).show();
	  w=buf;
	} */

	
	
	
	
	
	
	
	
	
	/*
	Thread sendthread = new Thread() {
        public void run() {
        	hsend.sendEmptyMessage(1);

        	
        }

	};
	  int i=0;
	  @SuppressLint("HandlerLeak") */
	/*
	Handler hsend =new Handler()  {
		public void handleMessage(android.os.Message msg) {
			  
			  messsage="lo"+Integer.toString(i);
			  i++;
			try {
					
					client = new Socket("37.140.120.198", 4444); // connect to the server
					printwriter = new PrintWriter(client.getOutputStream(), true);
					printwriter.write(message); // write the message to output stream
	 
					printwriter.flush();
					printwriter.close();
					client.close(); // closing the connection
					messsage="lo1";
				} catch (UnknownHostException e) {
					e.printStackTrace();   				messsage="lo2";

				} catch (IOException e) {
					e.printStackTrace(); 				messsage="lo3";

				} 
		  };
	  };
	*/
	
	
	
	
	
	
	/*
	
	public class SimpleTextServer extends AsyncTask<Void, Void, Void>{

		
		protected Void doInBackground(Void... params) {
		try {
			serverSocket = new ServerSocket(4444); // Server socket
 
		} catch (IOException e) {
			System.out.println("Could not listen on port: 4444");
		}
 
		System.out.println("Server started. Listening to the port 4444");
 
		while (true) {
			try {
 
				clientSocket = serverSocket.accept(); // accept the client connection
				inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
				bufferedReader = new BufferedReader(inputStreamReader); // get the client message
				messsage = bufferedReader.readLine();
 
				System.out.println(message);
			    //Toast.makeText(getApplicationContext(), messsage, Toast.LENGTH_SHORT).show();

				inputStreamReader.close();
				clientSocket.close();
 
			} catch (IOException ex) {
				System.out.println("Problem in message reading");
			}
		}

	}
	}

	private class Sendinfo extends AsyncTask<Void, Void, Void> {
		 
		@Override
		protected Void doInBackground(Void... params) {
			try {
				//messsage="lo1o";
				client = new Socket("37.140.120.195", 4444); // connect to the server
				printwriter = new PrintWriter(client.getOutputStream(), true);
				printwriter.write(message); // write the message to output stream
 
				printwriter.flush();
				printwriter.close();
				client.close(); // closing the connection
			} catch (UnknownHostException e) {
				e.printStackTrace();   				//messsage="lo2";

			} catch (IOException e) {
				e.printStackTrace(); 				//messsage="lo3";

			}
			return null;
			
		}
 
	}

	
	
	
	/*
	
	
	
	
	
	
	
	
	
	private void startService() {


		try {
		openConnection();

		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		}

		// данный метод открыает соединение
		public void openConnection() throws InterruptedException
		{
		try {

		// WatchData - это класс, с помощью которого мы передадим параметры в 
		// создаваемый поток
		WatchData data = new WatchData();
		data.email = "HELLLO";
		data.ctx = this;

		// создаем новый поток дл€ сокет-соединени€
		new WatchSocket().execute(data);
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
		

	
			class WatchData 
			{
			String email;
			Context ctx;
			}

	
	
			
			
			
			
			
			
			
			
			
			
			
			
			class WatchSocket extends AsyncTask<WatchData , Integer, Integer>
			{
			Context mCtx;
			Socket mySock;

			protected void onProgressUpdate(Integer... progress)
			{ }

			protected void onPostExecute(Integer result)
			{
			// Ёто выполнитс€ после завершени€ работы потока

			}



			protected Integer doInBackground(WatchData... param)
			{
			InetAddress serverAddr;

			mCtx = param[0].ctx;
			String email = param[0].email;

			try {
			while(true)
			{
			serverAddr = InetAddress.getByName("37.140.125.40");
			mySock = new Socket(serverAddr, 4505);

			// открываем сокет-соединение 
			SocketData data = new SocketData();
			data.ctx = mCtx;
			data.sock = mySock;

			// ¬Ќ»ћјЌ»≈! ‘инт ушами - еще один поток =) 
			// »менно он будет принимать вход€щие сообщени€
			GetPacket pack = new GetPacket();
			AsyncTask<SocketData, Integer, Integer> running = pack.execute(data);

			String message = email;
			// ѕосылаем email на сервер
			try {
			PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(mySock.getOutputStream())),true);

			out.println(message);
			
			} catch(Exception e) {}

			// —ледим за потоком, принимающим сообщени€
			while(running.getStatus().equals(AsyncTask.Status.RUNNING))
			{

			}

			// ≈сли поток закончил принимать сообщени€ - это означает, 
			// что соединение разорвано (других причин нет). 
			// Ёто означает, что нужно закрыть сокет 
			// и открыть его оп€ть в бесконечном цикле (см. while(true) выше) 
			try
			{
			mySock.close();
			}
			catch(Exception e)
			{}
			}
			} catch (Exception e) {
			return -1;
			}
			}
			}

	
	
	
	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			class SocketData 
			{
			Socket sock;
			Context ctx;
			}

			class GetPacket extends AsyncTask<SocketData, Integer, Integer>
			{
			Context mCtx;
			char[] mData;
			Socket mySock;

			protected void onProgressUpdate(Integer... progress)
			{
			try
			{
			// ѕолучаем прин€тое от сервера сообщение
			String prop = String.valueOf(mData);
			// ƒелаем с сообщением, что хотим. я, например, пишу в базу
		    Toast.makeText(getApplicationContext(), prop, Toast.LENGTH_SHORT).show();
			}
			catch(Exception e)
			{
			Toast.makeText(mCtx, "Socket error: " + e.getMessage(), Toast.LENGTH_LONG).show();
			}
			}

			protected void onPostExecute(Integer result)
			{
			// Ёто выполнитс€ после завершени€ работы потока
			}


			protected Integer doInBackground(SocketData... param)
			{
			mySock = param[0].sock;
			mCtx = param[0].ctx;
			mData = new char[4096];

			try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(mySock.getInputStream()));
			int read = 0;

			// ѕринимаем сообщение от сервера
			// ƒанный цикл будет работать, пока соединение не оборветс€
			// или внешний поток не скажет данному cancel()
			while ((read = reader.read(mData)) >= 0 && !isCancelled())
			{
			// "¬ызываем" onProgressUpdate каждый раз, когда прин€то сообщение
			if(read > 0) publishProgress(read);
			}
			reader.close();
			} catch (IOException e) {
			return -1;
			}
			catch (Exception e) {
			return -1;
			}
			return 0;
			}
			}

	
	*/
	
	
	
	
}
