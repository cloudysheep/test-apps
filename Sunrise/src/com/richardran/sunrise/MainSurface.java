package com.richardran.sunrise;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.Random;


public class MainSurface extends SurfaceView implements Runnable{
   Thread thread = null;
   SurfaceHolder surfaceHolder;
   volatile boolean running = false;
   private Paint paintsun = new Paint(Paint.ANTI_ALIAS_FLAG);
   private Paint star = new Paint(Paint.ANTI_ALIAS_FLAG);

   Random random;

   int timer = 0;
   int canvas_w = 0;
   int canvas_h = 0;
   
   Sunrise sunrise = new Sunrise();
   
   volatile boolean touched = false;
   volatile float touched_x, touched_y;
     
   public MainSurface(Context context) {
	   super(context);
	   // TODO Auto-generated constructor stub
	   surfaceHolder = getHolder();
	   random = new Random();
   }
    
   public void onResumeMySurfaceView(){
	   // water	color
	   paintsun.setStyle(Paint.Style.FILL);
	   int color = Color.rgb(255, 160, 59);
	   paintsun.setColor(color);
	   
	   // star color
	   color = Color.rgb(200, 200, 255);
	   star.setColor(color);
	
	   //ran2d.onCreate(1000, 3000, 4000);
	   
	   running = true;
	   thread = new Thread(this);
	   thread.start();
   }
  
   public void onPauseMySurfaceView(){
	   boolean retry = true;
	   running = false;
	   while(retry){
		   try {
			   thread.join();
			   retry = false;
		   } catch (InterruptedException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
	   }
   }

   @Override
   public void run() {
	   // TODO Auto-generated method stub
	   while(running){
		   //if(true) return;
		   
		   if(surfaceHolder.getSurface().isValid()){
			   Canvas canvas = surfaceHolder.lockCanvas();
			   
			   if(!sunrise.isCreated) {
				   canvas_w = canvas.getWidth();
				   canvas_h = canvas.getHeight();
				   sunrise.cs(canvas_w, canvas_h);
			   }
			   
			   //drawing background
			   //canvas.drawColor(Color.BLUE);
			   //if(timer%4==0) canvas.drawPath(sky.sky, star);
			   //else canvas.drawPath(sky.sky1, star);
			   
			   sunrise.draw(timer, canvas, paintsun, canvas_w, canvas_h);
			   
			   if(touched){  }

			   surfaceHolder.unlockCanvasAndPost(canvas);
			   timer=timer+4;
			   
			   try {
				   Thread.sleep(30);
			   } catch (InterruptedException e) {
				   // TODO Auto-generated catch block
				   e.printStackTrace();
			   }
			   
		   }
		   
	   }
   }
    

   @Override
   public boolean onTouchEvent(MotionEvent event) {
	   // TODO Auto-generated method stub
  
	   touched_x = event.getX();
	   touched_y = event.getY();
  
	   int action = event.getAction();
 
	   switch(action){
	   case MotionEvent.ACTION_DOWN:
		   touched = true;
		   break;
	   case MotionEvent.ACTION_MOVE:
		   touched = true;
		   break;
	   case MotionEvent.ACTION_UP:
		   touched = false;
		   break;
	   case MotionEvent.ACTION_CANCEL:
		   touched = false;
		   break;
	   case MotionEvent.ACTION_OUTSIDE:
		   touched = false;
		   break;
	   default:
	   }
	
	   return true; //processed
   }
   
}
