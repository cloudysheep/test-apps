package com.richardran.ricefields;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class RiceFields {
	public boolean isCreated = false;
	
	Random random = new Random();
		
	int fx[][] = new int[39][105];
	int fy[][] = new int[39][105];
	int fg[][] = new int[39][105];

	int tx[][] = new int[39][105];
	int ty[][] = new int[39][105];
	int cy[][] = new int[39][105];
	
	public void create_fields() {
	    for(int i = 0; i < 39;i+=1) {     
	        for(int j = 0; j <101;j+=1) {
	        	fx[i][j] = random.nextInt(30);
	        	
	            fy[i][j] = random.nextInt(30);
	            fg[i][j] = 90+random.nextInt(110);
	        }
	    }
	    
		isCreated = true;
	};

	public void render_fields_op(int p, Canvas canvas, int canvas_h, int canvas_w) {
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStyle(Paint.Style.FILL);
		
		double rad2pix = 1.27323954474* canvas_h; // 16 * 0.12/pi
		int horizon = (int)(0.25 * canvas_h);
		int shift = (int)(2.25 * canvas_h);
		int w = canvas_w/10;
		
		int i = 0;
	    for(i = 0; i < 31;i++) {
	        for(int j = 0; j <100;j++) {
	    	    int x = w*(j-1)+ fx[i][j];
	    	    int y = 70*(i-3) + fy[i][j]+p;
	    	    y = y%2100;
	    	    if(y<1) {
	    	    	tx[i][j] = -1;
	    	    	ty[i][j] = -1;
	    	    	continue;
	    	    }
	    	    
	    	    double atany = Math.atan(y/100.0);
	    	    int tyv = shift - (int)(rad2pix * atany);
	    	    double pr = horizon * atany/y;
	    	    int txv = (int)(x * pr);
    	    	tx[i][j] = txv;
    	    	ty[i][j] = tyv;
    	    	cy[i][j] = y/70;
	        }
	    }
	    
	    for(i = 0; i < 31;i++) {
	    	for(int j = 0; j <100;j++) {	
	    		int yij = ty[i][j];
	    		int yij1 = ty[i][j+1];
	    		int yi1j1 = ty[i+1][j+1];
	    		int yi1j = ty[i+1][j];
		        	
	    		if(yij<=0 || yij1<=0 || yi1j1<=0 || yi1j<=0) {
	    			continue;
		        }

	    		if(yi1j1>yij) {
	    			continue;
	    		}
		        	
	    		if(yi1j1>yij1) {
	    			continue;
	    		}
	            
	    		int yf = cy[i][j] *7;
	    		int yfg = cy[i][j]*5;
	            
	    		int g = fg[i][j];
	    		
	    		int cg = Math.min(255, 20+yfg+g);
	    		int color = Color.rgb(20+yf, cg, 20+yf);
	    		paint.setColor(color);
	    		drawQuad(canvas, paint, tx[i][j], yij, tx[i][j+1], yij1, tx[i+1][j+1], yi1j1, tx[i+1][j], yi1j);                        
	    	}
	    }
	}
	
	public void drawPoly(Canvas canvas, Paint paint, int[] x, int[] y) {
		Path wallpath = new Path();
		wallpath.reset(); // only needed when reusing this path for a new build
		wallpath.moveTo(x[0], y[0]); // used for first point
		wallpath.lineTo(x[1], y[1]);
		wallpath.lineTo(x[2], y[2]);
		wallpath.lineTo(x[3], y[3]);
		wallpath.lineTo(x[0], y[0]); // there is a setLastPoint action but i found it not to work as expected

		canvas.drawPath(wallpath, paint);
	}
	
	public void drawQuad(Canvas canvas, Paint paint, 
			int x0, int y0, int x1, int y1,
			int x2, int y2, int x3, int y3) {
		
		Path wallpath = new Path();
		//wallpath.reset(); // only needed when reusing this path for a new build
		wallpath.moveTo(x0, y0); // used for first point
		wallpath.lineTo(x1, y1);
		wallpath.lineTo(x2, y2);
		wallpath.lineTo(x3, y3);
		wallpath.lineTo(x0, y0); // there is a setLastPoint action but i found it not to work as expected

		canvas.drawPath(wallpath, paint);
	}
}
