package com.richardran.ricefields;
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

import com.richardran.ricefields.R;


public class MainSurface extends SurfaceView implements Runnable{
   Thread thread = null;
   SurfaceHolder surfaceHolder;
   volatile boolean running = false;
   private Paint paintsky = new Paint(Paint.ANTI_ALIAS_FLAG);
   private Paint paintgrass = new Paint(Paint.ANTI_ALIAS_FLAG);
   
   Random random;
   
   RiceFields ricefields = new RiceFields();
   
   int timer = 0;
   int canvas_w = 0;
   int canvas_h = 0;
   
   
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
	   paintsky.setStyle(Paint.Style.FILL);
	   paintgrass.setStyle(Paint.Style.FILL);
	   
	   int color = Color.rgb(0, 0, 70);
	   paintsky.setColor(color);
	   color = Color.rgb(100, 185, 100);
	   paintgrass.setColor(color);
	   
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
		   if(ricefields==null) return;
		   
		   if(surfaceHolder.getSurface().isValid()){
			   Canvas canvas = surfaceHolder.lockCanvas();
			   
			   if(!ricefields.isCreated){
				   canvas_w = canvas.getWidth();
				   canvas_h = canvas.getHeight();
				   ricefields.create_fields();
				   canvas.drawColor(Color.WHITE);
			   }
			   

			   
			   int skyh = (int)(0.314*canvas_h);
			   
			   if(timer < 8) {
				   canvas.drawColor(Color.WHITE);
				   for(int i = 0;i<=skyh;i++) {
					   double light = 35/(double)(skyh);
					   int badj = (int)(light * i);
					   int radj = (int)(light * i* 7);
					   int color = Color.rgb(radj, radj, 220+badj);
					   paintsky.setColor(color);
					   canvas.drawRect(0, i, canvas_w, (i+1), paintsky);
				   }
			   }
			   
			   ricefields.render_fields_op(timer, canvas, canvas_h, canvas_w);
			   //grass.persRender(canvas, 0, canvas_h, 0.25);
			   //ricefields.render_ray(canvas);
			   
			   if(touched){  }
			   
			   surfaceHolder.unlockCanvasAndPost(canvas);
			   timer=timer+2;
			   
			   try {
				   Thread.sleep(40);
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
