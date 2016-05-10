package cn.tvbingo.tv.launcher.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 
 * @Description 倒影生成包装类
 * @author Joychang E-mail: changwanjob@163.com
 * @version 创建时间：2015年12月7日 下午12:07:34
 */
public class ReflectItemView extends FrameLayout {
	private static final String TAG = "ReflectItemView";
	private Paint mRefPaint = null;
	private Bitmap mReflectBitmap;
	private Canvas mReflectCanvas;
	private View mContentView;
	private static final int REFHEIGHT = 130;

	public ReflectItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ReflectItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ReflectItemView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		if (mRefPaint == null) {
			mRefPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			// 倒影渐变.
			mRefPaint
					.setShader(new LinearGradient(0, 0, 0, REFHEIGHT,
							new int[] { 0x77000000, 0x66AAAAAA, 0x0500000,
									0x00000000 }, new float[] { 0.0f, 0.1f,
									0.9f, 1.0f }, Shader.TileMode.CLAMP));
			mRefPaint.setXfermode(new PorterDuffXfermode(
					PorterDuff.Mode.MULTIPLY));
		}
		setClipChildren(false);
		setClipToPadding(false);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		if (getChildCount() > 0) {
			mContentView = getChildAt(0);
		}
	}

	@Override
	public void addView(View child) {
		mContentView = child;
		super.addView(child);
	}

	public View getContentView() {
		return mContentView;
	}

	@Override
	public boolean performClick() {
		if (mContentView != null) {
			return mContentView.performClick();
		} else {
			return super.performClick();
		}
	}

	private boolean mIsReflection = true;

	public void setReflection(boolean ref) {
		mIsReflection = ref;
		invalidate();
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (mIsReflection) {
			// 创建一个画布.
			if (mReflectBitmap == null && mContentView != null) {
				mReflectBitmap = Bitmap.createBitmap(mContentView.getWidth(),
						REFHEIGHT, Bitmap.Config.ARGB_8888);
				mReflectCanvas = new Canvas(mReflectBitmap);
			}
			// 绘制倒影.
			if (mContentView != null) {
				drawReflection(mReflectCanvas);
				canvas.save();
				int dy = mContentView.getBottom();
				int dx = mContentView.getLeft();
				canvas.translate(dx, dy);
				canvas.drawBitmap(mReflectBitmap, 0, 10, null);
				canvas.restore();
			}
		}
	}

	public Bitmap getReflectBitmap() {
		return mReflectBitmap;
	}

	/**
	 * 绘制倒影.
	 */
	public void drawReflection(Canvas canvas) {
		canvas.save();
		canvas.clipRect(0, 0, mContentView.getWidth(), REFHEIGHT);
		canvas.save();
		canvas.scale(1, -1);
		canvas.translate(0, -mContentView.getHeight());
		mContentView.draw(canvas);
		canvas.restore();
		canvas.drawRect(0, 0, mContentView.getWidth(), REFHEIGHT, mRefPaint);
		canvas.restore();
	}

	public boolean isReflection() {
		return this.mIsReflection;
	}
}
