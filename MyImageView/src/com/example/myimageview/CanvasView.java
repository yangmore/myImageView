package com.example.myimageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView  extends View {

	public CanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
//	public CanvasView(Context context, AttributeSet attrs,int) {
//		super(context, attrs);
//		// TODO Auto-generated constructor stub
//	}

//	public CanvasView(Context context) {
//		super(context);
//		// TODO Auto-generated constructor stub
//	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		float x1 = event.getX(0);
		float x2 = event.getX(1);
		float y1 = event.getY(0);
		float y2 = event.getY(1);
		float a = (x2 - x1);
		float b = (y2 - y1);
		
		
		float diff = (float) Math.sqrt((a * a + b * b));
		float q = (b / a);
		float rot = (float) Math.toDegrees(Math.atan(q));
		
		
		
		if (event.getPointerCount()==1) {
			
			mStartDistance = 0.0f;
			
//			if (event.getAction()==MotionEvent.ACTION_SCROLL) {
//				
//			}
			boolean x3= a>0?true:false;
			boolean y3= b>0?true:false;
			float dis=a>b?a:b;
		long time=	event.getEventTime();
		
	         long  disLon=   Long.parseLong(dis+"");
	         
	         long speed =disLon/time;
			
			if (Math.abs(a)>Math.abs(b)) {
//				doImageTrasle(x3,);
				
			}else {
				
				
			}
			
		long eventTime=	event.getEventTime();
			
			
			
			
		}else {
			
			

			
		}
		
		
		return super.onTouchEvent(event);
	}
	private Point mCanvasOffset = new Point(0, 0);
	private float mStartDistance;
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		int sc =canvas.save();
		
//		canvas.scale(mCanvasScale, mCanvasScale);
		canvas.translate(mCanvasOffset.x, mCanvasOffset.y);
//		canvas.clipRect(mCanvasLimits);
		canvas.drawColor(Color.WHITE);
		
		drawImages(canvas, true);
		
		
		super.onDraw(canvas);
	}
	
	
	private void drawImages(Canvas canvas, boolean back) {
//		ImageDraw.setResizeMode(resizeObjectMode);
//		for (ImageDraw ad : currentState.mDrawables) {
//			if (ad != null && (ad.isInBack() == back)) {
//				if (ad.padlock == null)
//					ad.padlock = padlock;
//				ad.draw(canvas);
//			}
		
		ImageDraw im =new ImageDraw();
		im.draw(canvas);
		
		
		}
	

	
}
