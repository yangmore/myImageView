package com.example.myimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;



public class ImageDraw {

	public static final int IMAGE = 0;
	public static final int TEXT = 1;

	public String pack = "";
	public String folder = "";
	public String filename = "";
	public boolean locked = false;
	public Bitmap padlock = null;
	protected Point mPosition = new Point();
	protected float mRotation = 0.0f;
	protected float mScale = 1.0f;
	protected boolean mSelected = false;
	protected boolean InBack = true;
	protected boolean flipVertical = false;
	protected boolean flipHorizontal = false;
	protected final int resizeBoxSize = 32;
	public static final int maxImageWidth = 640;
	public static  final int maxImageHeight = 500;
	static boolean resizeMode = false; 
	/** 鏍囪鏄浘鐗囪繕鏄枃瀛�*/
	private int type = 0;
	protected Bitmap content = null;

	static void setResizeMode(boolean rm) {
		resizeMode = rm;
	}

	static boolean interactiveMode = false;

	static void setInteractiveMode(boolean rm) {
		interactiveMode = rm;
	}

	public void recycle() {
		Log.w("RAGE", "RECYCLE ImageObject at" + mPosition.toString());
		if (!content.isRecycled()) {
			content.recycle();
		}
	}

	public int getType() {
		return type;
	}

	public boolean isInBack() {
		return InBack;
	}

	public Bitmap getContentBitmap() {
		return content;
	}

	public void setInBack(boolean inBack) {
		InBack = inBack;
	}

	private int mDrawableId = -1;


	public ImageDraw(int type) {
		this.type = type;
	}

	public ImageDraw() {
		this(IMAGE);
	}

	public ImageDraw(Bitmap target, int posX, int posY, float rot,
			float scale, int drawableId, String pac, String foldr, String fil) {
		this(IMAGE);
		content = target;
		imageSizeCheck();
		mPosition.x = posX;
		mPosition.y = posY;
		mRotation = rot;
		mScale = scale;
		mDrawableId = drawableId;
		filename = fil;
		pack = pac;
		folder = foldr;
		Log.w("RAGE", "Initialized ImageObject at" + mPosition.toString());
	}

	private void imageSizeCheck() {
		if (content.getWidth() > maxImageWidth) {
			int newWidth = maxImageWidth;
			int newHeight = (content.getHeight() * newWidth)
					/ content.getWidth();
			float scaleWidth = ((float) newWidth) / content.getWidth();
			float scaleHeight = ((float) newHeight) / content.getHeight();
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			content = Bitmap.createScaledBitmap(content, newWidth, newHeight,
					true);
		}
		if (content.getHeight() > maxImageHeight) {
			int newHeight = maxImageHeight;
			int newWidth = (content.getWidth() * newHeight)
					/ content.getHeight();
			float scaleWidth = ((float) newWidth) / content.getWidth();
			float scaleHeight = ((float) newHeight) / content.getHeight();
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			content = Bitmap.createScaledBitmap(content, newWidth, newHeight,
					true);
		}
	}

	public int getWidth() {
		if (content != null)
			return content.getWidth();
		else
			return 0;
	}

	public int getHeight() {
		if (content != null)
			return content.getHeight();
		else
			return 0;
	}

	public void moveBy(int x, int y) {
		mPosition.x += x;
		mPosition.y += y;
	}

	Paint paint = new Paint();

	public void draw(Canvas canvas) {
		paint.setAntiAlias(true);
		int sc = canvas.save();
		try {
			canvas.translate(mPosition.x, mPosition.y);
			canvas.scale((float) mScale, (float) mScale);
			int sc2 = canvas.save();
			canvas.rotate((float) mRotation);
			canvas.scale((flipHorizontal ? -1 : 1), (flipVertical ? -1 : 1));
			// dr.draw(canvas);
			canvas.drawBitmap(content, -getWidth() / 2, -getHeight() / 2,
					new Paint());
			canvas.restoreToCount(sc2);
			Rect imgrect = new Rect(-getWidth() / 2, -getHeight() / 2,
					getWidth() / 2, getHeight() / 2);
			if (mSelected && interactiveMode) {
				paint.setARGB(128, 128, 128, 128);
				canvas.drawRect(imgrect, paint);

				// 鐢绘斁澶ф寜閽�
				if (type == IMAGE) {
					Rect resizerect = new Rect();
					Bitmap imageScale = BitmapFactory
							.decodeResource(
									Constans.mContext.getResources(),
									R.drawable.image_scale,
									new BitmapFactory.Options());
					resizerect.set(imgrect.right
							- (int) (resizeBoxSize * (1.0 / mScale)),
							imgrect.bottom
									- (int) (resizeBoxSize * (1.0 / mScale)),
							imgrect.right, imgrect.bottom);
					Rect scaleRect = new Rect(0, 0, imageScale.getWidth(),
							imageScale.getHeight());
					canvas.drawBitmap(imageScale, scaleRect, resizerect, paint);

					paint.setARGB(255, 0, 0, 0);
					paint.setStyle(Style.FILL);

					paint.setStrokeWidth(2.0f);
					// if (!locked)
					// canvas.drawRect(resizerect, paint);
					// 鐢昏彍鍗�
					Bitmap menuImg = BitmapFactory.decodeResource(
							Constans.mContext.getResources(),
							R.drawable.image_menu, new BitmapFactory.Options());

					resizerect.set(imgrect.left, imgrect.top, imgrect.left
							+ (int) (resizeBoxSize * (1.0 / mScale)),
							imgrect.top
									+ (int) (resizeBoxSize * (1.0 / mScale)));
					Rect rsrc = new Rect(0, 0, menuImg.getWidth(),
							menuImg.getHeight());
					canvas.drawBitmap(menuImg, rsrc, resizerect, paint);
				}
				// 鐢籸emove鎸夐挳

				Bitmap closeImg = BitmapFactory.decodeResource(
						Constans.mContext.getResources(),
						R.drawable.image_remove, new BitmapFactory.Options());

				Rect dst = new Rect();
				dst.set(imgrect.right - (int) (resizeBoxSize * (1.0 / mScale)),
						imgrect.top, imgrect.right, imgrect.top
								+ (int) (resizeBoxSize * (1.0 / mScale)));
				Rect src = new Rect(0, 0, closeImg.getWidth(),
						closeImg.getHeight());
				canvas.drawBitmap(closeImg, src, dst, paint);
			}
			// 鐢婚攣
			if (locked && padlock != null && !padlock.isRecycled()
					&& interactiveMode) {
				Rect dst = new Rect();
				dst.set(imgrect.left, imgrect.bottom
						- (int) (resizeBoxSize * (1.0 / mScale)), imgrect.left
						+ (int) (resizeBoxSize * (1.0 / mScale)),

				imgrect.bottom);
				Rect src = new Rect(0, 0, padlock.getWidth(),
						padlock.getHeight());
				canvas.drawBitmap(padlock, src, dst, paint);
			}

		} catch (Exception e) {
			Log.d("RAGE", e.toString());
		}
		canvas.restoreToCount(sc);
	}

	public boolean pointIn(int x, int y) {
		int wp2 = (int) (((float) getWidth() / 2.0) * mScale);
		int hp2 = (int) ((getHeight() / 2.0) * mScale);
		return (x >= mPosition.x - wp2) && (x <= mPosition.x + wp2)
				&& (y >= mPosition.y - hp2) && (y <= mPosition.y + hp2);
	}

	public boolean pointInResize(int x, int y) {
		int wp2 = (int) (((float) getWidth() / 2.0) * mScale);
		int hp2 = (int) ((getHeight() / 2.0) * mScale);
		return (x >= mPosition.x + wp2 - resizeBoxSize)
				&& (x <= mPosition.x + wp2)
				&& (y >= mPosition.y + hp2 - resizeBoxSize)
				&& (y <= mPosition.y + hp2);
	}

	public boolean pointInMenu(int x, int y) {
		// getWidth: 寰楀埌閫変腑鍥剧墖鐨勫
		int wp2 = (int) (((float) getWidth() / 2.0) * mScale);
		int hp2 = (int) ((getHeight() / 2.0) * mScale);
		return (x >= mPosition.x - wp2)
				&& (x <= mPosition.x - wp2 + resizeBoxSize)
				&& (y >= mPosition.y - hp2)
				&& (y <= mPosition.y - hp2 + resizeBoxSize);
	}

	public boolean pointIsRemove(int x, int y) {
		// getWidth: 寰楀埌閫変腑鍥剧墖鐨勫
		int wp2 = (int) (((float) getWidth() / 2.0) * mScale);
		int hp2 = (int) ((getHeight() / 2.0) * mScale);

		return (x <= mPosition.x + wp2)
				&& (x >= mPosition.x + wp2 - resizeBoxSize)
				&& (y >= mPosition.y - hp2)
				&& (y <= mPosition.y - hp2 + resizeBoxSize);
	}

	public Point getPosition() {
		return mPosition;
	}

	public void setPosition(Point Position) {
		this.mPosition = Position;
	}

	public float getRotation() {
		return mRotation;
	}

	public void setRotation(float Rotation) {
		this.mRotation = Rotation;
	}

	public float getScale() {
		return mScale;
	}

	public void setScale(float Scale) {
		if (getWidth() * Scale >= resizeBoxSize / 2
				&& getHeight() * Scale >= resizeBoxSize / 2)
			this.mScale = Scale;
	}

	public boolean isSelected() {
		if (mSelected) {
		}
		return mSelected;
	}

	public void setSelected(boolean Selected) {
		this.mSelected = Selected;
	}

	public int getDrawableId() {
		return mDrawableId;
	}

	public boolean isFlipVertical() {
		return flipVertical;
	}

	public void setFlipVertical(boolean flipVertical) {
		this.flipVertical = flipVertical;
	}

	public boolean isFlipHorizontal() {
		return flipHorizontal;
	}

	public void setFlipHorizontal(boolean flipHorizontal) {
		this.flipHorizontal = flipHorizontal;
	}

}
