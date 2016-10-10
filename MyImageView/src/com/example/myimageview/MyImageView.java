package com.example.myimageview;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.gesture.Gesture;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Handler;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public class MyImageView extends ImageView {
	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public MyImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	
	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}


	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//		layoutItems(left, top, right, bottom);
	};
	
	@Override
	public void layout(int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.layout(l, t, r, b);
	}
	
	

	private PointF startPoint = new PointF();
	private Matrix matrix = new Matrix();
	private Matrix currentMatrix = new Matrix();
	private int lastDownX;
	private int lastDownY;
	
	private int lastMoveX;
	private int lastMoveY;
//	private long firstTime;
	
	public ImageView getImage(){
		return this;
	}

	private int upX;
	private int upY;
	private int right;
	private int bottom;
	private int rotation =0;
	private boolean isBaidongEnd=true;
//	private VelocityTracker mVelocityTracker;
	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		System.out.println(event.getAction());
		Log.e("msg", event.getAction()+"");
		startLeft2 = this.getLeft();
		startTop2 = this.getTop();
		right=this.getRight();
		bottom=this.getBottom();
//		if (mVelocityTracker == null) { 
//            mVelocityTracker = VelocityTracker.obtain();//获得VelocityTracker类实例 
//    } 
//		
//		mVelocityTracker.addMovement(event);
		
		switch (event.getAction()&MotionEvent.ACTION_MASK) {
		
		
	
		

		case MotionEvent.ACTION_DOWN:
			leftDown = getLeft();
			topDown = getTop();
			i=0;

		isUp=false;
			
			 Log.v(TAG, "onDWON");
			
			lastDownX = (int) event.getRawX(); 
            lastDownY = (int) event.getRawY();

            
            
             downTime = event.getEventTime();
//             lastTime=downTime;
//             firstTime=downTime;
            Log.e("downTime", downTime+"");
			
			System.out.println(lastDownX+"---++++++++--"+lastDownY);
			
			 int x1=lastDownX,x2=(int) getLeft()+getWidth()/2; //点1坐标;
	        int y1=lastDownY,y2=(int) getTop()+getHeight()/2; //点2坐标
	        int x=Math.abs(x1-x2);
	        int y=Math.abs(y1-y2);
	        disz = Math.sqrt(x*x+y*y);
	        int jiaodu=Math.round((float)(Math.asin(x/disz)/Math.PI*180));//最终角度
	        //Math.round  是四舍五入       Math.asin 返回的是  x/disz 的反正弦值        返回的值是 -PI/2 到 PI/2 之间的弧度值          //圆是 360  三角形是180
	        
	        
		deg=jiaodu;
		startRotion=0;
		
		
//	      ObjectAnimator	animator = ObjectAnimator.ofFloat(this, "rotation",0, 180).setDuration(800);
//      	setPivotX(lastDownX-getLeft());
////      	setPivotX(xSelf);
////      	setPivotY(ySelf+upY);
//      	setPivotY(lastDownY-getTop());
//      	animator.setRepeatCount(3);
//      	animator.setInterpolator(new AccelerateDecelerateInterpolator());
//      	animator.start();
		fitstX=lastDownX;
			break;

			
		case MotionEvent.ACTION_MOVE:
			 Log.v(TAG, "onMove");
			lastMoveX=(int) event.getRawX();
			lastMoveY=(int) event.getRawY();
			
		
//			float disX= event.getRawX()-lastTimeX;
//			float disY=event.getRawY()-lastTimeY;
//			
//			
//			
//			float  lasteventTime = event.getEventTime()-lastTime;
			
			int dx = (int) (event.getRawX() - lastDownX);
			int dy = (int) (event.getRawY() - lastDownY);
			System.out.println(dx+"-----"+dy);
			
			
//			setRotation(dy);
			
			this.setTranslationX(dx);
			this.setTranslationY(dy);
			// 改变Canvas  piant 位置  不改变  layout属性 
			
			
			
//			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)this.getLayoutParams(); 
//			params.leftMargin = 300+dx; params.topMargin = 780+dy;
//			setLayoutParams(params);
			
			
			


	

	
//			 Log.i("test","velocityTraker"+mVelocityTracker.getXVelocity());   
			
//			 mVelocityTracker.computeCurrentVelocity(1000); 
			
//			 Log.i("test","velocityTraker"+"------"+mVelocityTracker.getYVelocity());
			 
			
			 
			 
//			 if (mVelocityTracker.getYVelocity()==0) {
				 if (isBaidongEnd) {
					 startRotion=getRotation();
					 baidong(event.getRawX(),event.getRawY());
				
				 }
				
				

			
//				 if (event.getEventTime()-firstTime>=500&&Math.abs(event.getRawY() - lastDownY)>200) {
//					 firstTime=event.getEventTime();
//
//			  
//						
//					}
			
//			
//			int dx222 = (int) (event.getRawX() - lastMoveX);
//			int dy222 = (int) (event.getRawY() - lastMoveY);
//			System.out.println(dx+"-----"+dy);
//			int dis=Math.abs(dx222)>Math.abs(dy222)?Math.abs(dx222):Math.abs(dy222);

			
			
//			float speed= (float)dis/lasteventTime;
//			Log.e("speed", speed+"________");
			

			
////			lastTime=event.getEventTime();
////			lastMoveX=(int) event.getRawX();
////			lastMoveY=(int) event.getRawY();
////			if (event.getEventTime()-downTime>500&&Math.abs(event.getRawX()-fitstX)>300&&isBaidongEnd) {
//				if (event.getEventTime()-downTime>500&&Math.abs(event.getRawX()-fitstX)>300&&isBaidongEnd) {
//					boolean isLastEnd=true;
//				downTime=event.getEventTime();
//				fitstX=event.getRawX();
//				isUp=true;
//				clearAnimation();
//				invalidate();
//			      ObjectAnimator	animator = ObjectAnimator.ofFloat(this, "rotation",0, 360).setDuration(1000);
//			      animator.setRepeatCount(3);
//	      	setPivotX(lastDownX-getLeft());
////	      	setPivotX(xSelf);
////	      	setPivotY(ySelf+upY);
//	      	setPivotY(lastDownY-getTop());
//	      	animator.setInterpolator(new AccelerateDecelerateInterpolator());
//	      	animator.addUpdateListener(new AnimatorUpdateListener() {
//				
//				@Override
//				public void onAnimationUpdate(ValueAnimator animation) {
//					// TODO Auto-generated method stub
////					if (isUp) {
////						animation.cancel();
////					}
//					
//				}
//			});
//	      	animator.addListener(new AnimatorListener() {
//				
//				@Override
//				public void onAnimationStart(Animator animation) {
//					// TODO Auto-generated method stub
//					isBaidongEnd=false;
//				}
//				
//				@Override
//				public void onAnimationRepeat(Animator animation) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void onAnimationEnd(Animator animation) {
//					// TODO Auto-generated method stub
//					isUp=false;
//					i=0;
//					 startRotion=getRotation();
//					baidong(lastMoveX,lastMoveY);
//				}
//				
//				@Override
//				public void onAnimationCancel(Animator animation) {
//					// TODO Auto-generated method stub
//					
//				}
//			});
//	      	animator.start();
//				
//			}else {
//				
//			}
			
			
			
			
			
			break;
	
			
		case MotionEvent.ACTION_UP:
			
			isUp=true;
			  Log.v(TAG, "onUP");
//			  handler.sendEmptyMessage(2);
			 upX=(int) event.getRawX();
			 upY=(int) event.getRawY();
//			 baidong(event.getRawX(),event.getRawY());
			int dupx =(int) (event.getRawX()-lastDownX);
			int dupy =(int) (event.getRawY()-lastDownY);
//			paraboleAnimation(this,dupx,dupy);
			
//			baidongByAnima(event.getRawX(),event.getRawY());
		
			
			int disTra= Math.abs(dupx)>Math.abs(dupy)?Math.abs(dupx):Math.abs(dupy);
			long timeTra=event.getEventTime()-downTime;
			
			float speedTra=(float)disTra/timeTra;
			long speedTra2=disTra%timeTra;
			Log.e("speedTra", speedTra+"__"+speedTra2);
			Log.e("eventTime", eventTime+"");
			
			
			
			
			
			
			
			
			
			
			int translteDis = Math.abs(dupx)>Math.abs(dupy)?Math.abs(dupx):Math.abs(dupy);
			if (speedTra>0.7) {
//				if (speedTra>0.7||mVelocityTracker.getXVelocity()>100||mVelocityTracker.getYVelocity()>100) {
				
			
			
if (translteDis<=300&&Math.abs(dupy)>=Math.abs(dupx)&&dupy<0) {
	paowuxian2(this,dupx,dupy);
	
			}else {
				
				if (dupx>=0) {
					TransletAndRorate2(event.getRawX(),event.getRawY(),dupx, dupy,false);
				}else {
					TransletAndRorate2(event.getRawX(),event.getRawY(),dupx, dupy,true);
				}
				
				
				
			}


				
				
}else {
	doReRotate2(dupx,dupy,this);	
			}
			
			break;

		default:
			break;
		}
		
		return detector.onTouchEvent(event);
//		return true;
	}
	
	
	 private GestureDetector.OnGestureListener listener = 
	            new GestureDetector.OnGestureListener() {
	         
	        @Override
	        public boolean onSingleTapUp(MotionEvent e) {
	            // TODO Auto-generated method stub
	            Log.v(TAG, "onSingleTapUp");
	            return true;
	        }
	         
	        @Override
	        public void onShowPress(MotionEvent e) {
	            // TODO Auto-generated method stub
	            Log.v(TAG, "onShowPress");
	        }
	         
	        @Override
	        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
	                float distanceY) {
	            // TODO Auto-generated method stub
	        	
	            Log.v("Sroll", "onSroll"+distanceX+distanceY);
	            if (Math.abs(distanceX)<=0&&Math.abs(distanceY)<=0) {
	            	 Log.v(TAG, "stop");
				}
//	            Log.v(TAG, e1+"---"+e2);
	            return true;
	        }
	         
	        @Override
	        public void onLongPress(MotionEvent e) {
	            // TODO Auto-generated method stub
	            Log.v(TAG, "onLongPress");
	        }
	         
	        @Override
	        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
	                float velocityY) {
	            // TODO Auto-generated method stub
//	        	invalidate();
//	        	clearAnimation();
//	        	TransletAndRorate2(e2.getX(),e2.getY(),e2.getX()-e1.getX(),e2.getY()-e1.getY(),false);
	        	
	            Log.v(TAG, "onFling");
	            return true;
	        }

			@Override
			public boolean onDown(MotionEvent arg0) {
				// TODO Auto-generated method stub
				  Log.v(TAG, "onDown");
				return true;
			}
	         
	 
	        
	    };
////	  private GestureDetector.OnGestureListener listener = 
////	            new GestureDetector.SimpleOnGestureListener(){
//		  
//		  
//	  };
	
	    private GestureDetector detector = new GestureDetector(listener);
	
	
	
	private int deg;
	private String TAG="TAG";
	
	  
	
	
	@SuppressLint("NewApi")
	private void doReRotate2(final int x,final int y,final ImageView img){
		float ro= getRotation();
		Log.e("rota", ro+"");
		invalidate();
		clearAnimation();
//		animatorObj.pause();
//		animatorObj.cancel();
//		animatorObj.end();
//		i=0;
		
		
//		isBaidongEnd=true;
setRotation(ro);
PropertyValuesHolder rota = PropertyValuesHolder.ofFloat("rotation", ro%360,  
        0.0f);  


PropertyValuesHolder taslX = PropertyValuesHolder.ofFloat("x", startLeft2+x,  
		startLeft2);  
PropertyValuesHolder taslY = PropertyValuesHolder.ofFloat("y", startTop2+y,  
		startTop2 );  
   ObjectAnimator obj=       ObjectAnimator.ofPropertyValuesHolder(img, rota, taslX,taslY).setDuration(1000);
   obj.addUpdateListener(new AnimatorUpdateListener() {
	
	@Override
	public void onAnimationUpdate(ValueAnimator arg0) {
		// TODO Auto-generated method stub
		img.invalidate();
		img.postInvalidate();
		Log.e("rotation", getRotation()+"'");
	}
});
   
   obj.addListener(new AnimatorListener() {
	
	@Override
	public void onAnimationStart(Animator arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onAnimationRepeat(Animator arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onAnimationEnd(Animator arg0) {
		// TODO Auto-generated method stub
		clearAnimation();
		setRotation(0);
		invalidate();
		Log.e("rotation", getRotation()+"'");
	}
	
	@Override
	public void onAnimationCancel(Animator arg0) {
		// TODO Auto-generated method stub
		
	}
});
   obj.start();
		
	}


	

		final float a = -1f / 75f;
		private long downTime;
		private long eventTime;
		private int startTop;
		private int startLeft; 

		private int startLeft2;
		private int startTop2;
		private RotateAnimation rota;
	
	
	
		@SuppressLint("NewApi")
		private void TransletAndRorate2(float upX,float upY,float x,float y,boolean leftOrRight){
			PropertyValuesHolder pvhX=null;
			if (leftOrRight) {
				pvhX = PropertyValuesHolder.ofFloat("rotation", 0,  
		                 -360); 
			}else {
				pvhX = PropertyValuesHolder.ofFloat("rotation", 0,  
		                 360); 
			}
			
			float xDou=Math.abs(Constans.screenWidth/x);
			float yDou=Math.abs(Constans.screenHeigt/y);
			
			float dou =xDou>yDou?xDou:yDou;
			
			
			PropertyValuesHolder	translateX = PropertyValuesHolder.ofFloat("x", getX() ,  
					x*dou); 
			PropertyValuesHolder	translateY = PropertyValuesHolder.ofFloat("y",getY() ,  
					y*dou); 
			
			 ObjectAnimator.ofPropertyValuesHolder(this, pvhX, translateX,translateY).setDuration(1000).start();
			 
		}
	

	






	

    
    
    @SuppressLint("NewApi")
	public void paowuxian2(View view,int disX,int disY)  
    {  
  

    	
    	 ObjectAnimator animator1 = ObjectAnimator.ofFloat(this, "translationX", disX*5, disX*10);//X轴平移旋转
    	 ObjectAnimator animator4 = ObjectAnimator.ofFloat(this, "translationX", 0F, disX*5);//X轴平移旋转
//    	 animator1.setInterpolator(new AccelerateDecelerateInterpolator());
    	 animator1.setInterpolator(new Interpolator() {
			
			@Override
			public float getInterpolation(float arg0) {
				// TODO Auto-generated method stub
				return (float)Math.pow(arg0, 0.2);
			}
		});
    	 
    	 animator4.setInterpolator(new Interpolator() {
 			
 			@Override
 			public float getInterpolation(float arg0) {
 				// TODO Auto-generated method stub
 				Log.e("nothing", arg0+"'");
 				return (float)Math.pow(arg0, 2.0);
 			}
 		});
    	 int beginH= Constans.screenHeigt-getTop()+disY*5;
         ObjectAnimator animator2 = ObjectAnimator.ofFloat(this, "translationY", disY*5,Constans.screenHeigt);//Y轴平移旋转
         ObjectAnimator animator5 = ObjectAnimator.ofFloat(this, "translationY", 0F, disY*5);//Y轴平移旋转
         
       
         
         ObjectAnimator animator3 = ObjectAnimator.ofFloat(this, "rotation", 0F, 90F);//360度旋转
         ObjectAnimator animator6 = ObjectAnimator.ofFloat(this, "rotation", 90, 180F);//360度旋转
         AnimatorSet set = new AnimatorSet();

        
         set.play(animator4).with(animator5);
         set.play(animator5).with(animator6);
         set.play(animator4).before(animator1);
         set.play(animator1).with(animator2);
         set.play(animator2).with(animator3);
       
       
  
         set.setDuration(500);
         set.start();
        

        
    } 
    

    
    
    

    
  private int i=0;
    
   
    
    private float startRotion=0;
    private float endRotion=0;
//	private float lastTimeX;
//	private float lastTimeY;
//	private long lastTime;
   
    
    private boolean isAnimationEnd =true;
    

    

	    private boolean isUp=false;
	    private Handler handler = new Handler(){
	    	@SuppressLint("HandlerLeak")
			public void handleMessage(android.os.Message msg) {
	    		switch (msg.what) {
		
					
				case 2:
					i++    	;
					if (i < 10) {

						baidongXx(i, 0, 0);

					}else {
						isBaidongEnd=true;
						i=0;
					}

	
					
					break;

				default:
					break;
				}
	    		
	    		
	    	};
	    };
		private double disz;
		private ObjectAnimator animatorObj;
		private int leftDown;
		private int topDown;
		private float fitstX;
    @SuppressLint("DrawAllocation")
	@Override
    protected void onDraw(Canvas canvas) {
    	// TODO Auto-generated method stub
    	canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG  
                | Paint.FILTER_BITMAP_FLAG));  
    	super.onDraw(canvas);
    }
        
    
    
    
    
    
    
    
    private void baidong(float rawX,float rawY){
    	baidongXx(i, rawX, rawY);
    
    }
    
    public void baidongXx(int x,final float rawX,final float rawY){
    	
        boolean isDou=	x%2==0?true:false;
        if (isDou) {
    		 
        	endRotion=9*(9-x)*((float)(10-x)/10)+deg;
    		
    	}else {
    		endRotion=9*(x-9)*((float)(10-x)/10)+deg;
    		
    	}
        	
        
        float xSelf=((float)(lastDownX-getLeft()))/getWidth();
        float ySelf=((float)(lastDownY-getTop()))/getHeight();

        clearAnimation();
        float xSelf2= (float)rawX/Constans.screenWidth;
        float ySelf2=(float) rawY/Constans.screenHeigt;
        Log.i("info", rawX+"--"+getWidth()+"--"+xSelf2+"--"+rawY+"--"+getHeight()+"--"+ySelf2+Constans.screenWidth+Constans.screenHeigt);
       
      float xdef=  (float)(2*rawX)/Constans.screenWidth;
      float ydef=  (float)(2*rawY)/Constans.screenHeigt;
        
        	animatorObj = ObjectAnimator.ofFloat(this, "rotation",startRotion, endRotion).setDuration(1500);
        	setPivotX(lastDownX-getLeft());
//        	setPivotX(xSelf);
//        	setPivotY(ySelf+upY);
        	setPivotY(lastDownY-getTop());
        	
        	animatorObj.setInterpolator(new AccelerateDecelerateInterpolator());
//        	animator1.setInterpolator(new Interpolator() {
//    			
//    			@Override
//    			public float getInterpolation(float arg0) {
//    				// TODO Auto-generated method stub
//
//    				return (float)(Math.cos((arg0 + 1) * Math.PI) / 2.0f) + 0.5f;
//    			}
//    		});
        	startRotion=endRotion;
        	Log.e("rotition", x+"--"+isDou+startRotion+"'"+endRotion);
        	animatorObj.addUpdateListener(new AnimatorUpdateListener() {
				
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					// TODO Auto-generated method stub
					if (isUp) {
						animation.cancel();
//						animation.end();
					}
				}
			});
        	animatorObj.addListener(new AnimatorListener() {
    			
    			@Override
    			public void onAnimationStart(Animator arg0) {
    				// TODO Auto-generated method stub
    				isBaidongEnd=false;
    			}
    			
    			@Override
    			public void onAnimationRepeat(Animator arg0) {
    				// TODO Auto-generated method stub
    				
    			}
    			
    			@Override
    			public void onAnimationEnd(Animator arg0) {
    				// TODO Auto-generated method stub
    				handler.sendEmptyMessage(2);
    			}
    			
    			@Override
    			public void onAnimationCancel(Animator arg0) {
    				// TODO Auto-generated method stub
    				
    			}
    		});
        	animatorObj.start();
        }
    

        
    } 
