package com.richardran.sunrise;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Sunrise {
	int ss[] = new int[220];
	int sl[] = new int[220];
	int centerx = 0;
	int centery = 450;
	int p = 0;
	int shine = 0;
	boolean isCreated = false;
	Random random = new Random();
	
	int canvas_w = 1000;
	int canvas_h = 700;

    int sun_w = 300;
    int sun_h = 200; 

	public void cs(int c_w, int c_h){
	    for(int i = 0; i < 210; i+=1) {
	        ss[i] = -(random.nextInt(120));
	        sl[i] = random.nextInt(30);
	    }
	    
	    canvas_w = c_w;
	    canvas_h = c_h;
	    
	    centery = canvas_h+50;
	    
	    sun_w = Math.max(canvas_w,  canvas_h)/3;
	    sun_h = 2*sun_w/3;
	    
	    isCreated = true;
	}

	public void ray(double radian, Canvas canvas){
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStyle(Paint.Style.FILL);
	    boolean useCenter = true;
	    RectF oval = new RectF();
	    int centery_new = centery-(int)(190*Math.sin(0.5*radian-0.6*Math.PI));
	    double shine = 1.9 * Math.sin(0.5*radian+0.4*Math.PI);
	    
	    for(int i =200; i>0; i+=-1) {
	        int s = ss[i];
	        int l = sl[i];
	        
	        //fill(185-shine*i, 50-shine*i, 0);
	        int cr = Math.max(0,Math.min(255, (int)(185-shine*i)));
	        int cg = Math.max(0,Math.min(255, (int)(50-shine*i)));
	        
	 	   	int color = Color.rgb(cr, cg, 0);
	 	   	paint.setColor(color);
		   
	        int h = 20*i;
	        int w = 30*i;
	        
		    int left = centerx-w/2;
		    int top = centery_new - h/2;
		    oval.set(left, top, left+w, top+h);
	        canvas.drawArc(oval, s, l, useCenter, paint);
	        
	        //arc(centerx, centery-50*Math.sin(0.5*p+180), 1.5*w, w, s, s+l);
	    }
	}
    
	public void draw(int p, Canvas canvas, Paint paint, int canvas_w, int canvas_h) {
	    if ((p%360)==0){
	    	cs(canvas_w, canvas_h); 
	    }
	    
	    double radian = p/180.0;
	    int centery_new = centery-(int)(190*Math.sin(0.5*radian-0.6*Math.PI));
	    
	    ray(radian, canvas);
	    //fill(255, 160, 59);
	    RectF oval = new RectF();

	    oval.set(centerx-sun_w/2, centery_new - sun_h/2, centerx+sun_w/2, centery_new+sun_h/2);
	    canvas.drawOval(oval, paint);
	    //ellipse(centerx, centery-30*Math.sin(0.5*p+180), 300, 200);
	}
}
