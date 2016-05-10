package cn.tvbingo.tv.launcher.widget;

import cn.tvbingo.tv.launcher.R;
import cn.tvbingo.tv.launcher.utils.DensityUtil;
import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Description 飞框view
 * @author Joychang E-mail: changwanjob@163.com
 * @version 创建时间：2015年12月3日 下午3:14:36
 */
@SuppressLint("NewApi")
public class FlyBorderView extends View {
	private Drawable mDrawableFlyBorder;
	private Context mContext;
	private static final String TAG = FlyBorderView.class.getSimpleName();

	public FlyBorderView(Context context) {
		super(context);
		init(context);
	}

	public FlyBorderView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		init(context);
	}

	public FlyBorderView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	// 初始化焦点框
	private void init(Context context) {
		mContext = context;
		mDrawableFlyBorder = mContext.getResources().getDrawable(
				R.drawable.white_border);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// super.onDraw(canvas);
		onDrawUpRect(canvas);
		Log.d(TAG, "onDraw");
	}

	/**
	 * 绘制最上层的焦点框
	 * 
	 * @note
	 * @author Joychang
	 * @date 创建时间：2015年12月3日 下午3:25:23
	 */
	private void onDrawUpRect(Canvas canvas) {
		if (null != mDrawableFlyBorder) {
			int width = getWidth();
			int height = getHeight();
			Rect padding = new Rect();
			mDrawableFlyBorder.getPadding(padding);
			mDrawableFlyBorder.setBounds(-padding.left - left, -padding.top
					- top, width + padding.right + right, height
					+ padding.bottom + bottom);
			//mDrawableFlyBorder.setAlpha((int)(255*(scale-1)*10));
			mDrawableFlyBorder.draw(canvas);
		}
	}

	/**
	 * 设置焦点框的间距
	 * 
	 * @note
	 * @author Joychang
	 * @date 创建时间：2015年12月3日 下午4:19:13
	 */
	public void setBorderPadding(int left, int top, int right, int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	/**
	 * 执行动画
	 * 
	 * @note
	 * @param toView
	 * @param scaleX
	 * @param scaleY
	 * @author Joychang
	 * @date 创建时间：2015年12月3日 下午4:21:54
	 */
	public void startTranslateAnimation(View toView, float scaleX, float scaleY) {
		Rect fromRect = findLocationWithView(this);
		Rect toRect = findLocationWithView(toView);
		int x = toRect.left - fromRect.left;
		int y = toRect.top - fromRect.top;

		int deltaX = (toView.getWidth() - this.getWidth()) / 2;
		int deltaY = (toView.getHeight() - this.getHeight()) / 2;
		
		if (isTvScreen) {
			x = DensityUtil.dip2px(this.getContext(), x + deltaX);
			y = DensityUtil.dip2px(this.getContext(), y + deltaY);
		} else {
			x = x + deltaX;
			y = y + deltaY;
		}
		float toWidth = toView.getWidth() * scaleX;
		float toHeight = toView.getHeight() * scaleY;
		int width = (int) (toWidth);
		int height = (int) (toHeight);

		flyWhiteBorder(width, height, x, y);
	}

	private void flyWhiteBorder(int width, int height, float x, float y) {
		int mWidth = this.getWidth();
		int mHeight = this.getHeight();

		float scaleX = (float) width / (float) mWidth;
		float scaleY = (float) height / (float) mHeight;

		// animate().translationX(x).translationY(y).setDuration(TRAN_DUR_ANIM)
		// .scaleX(scaleX).scaleY(scaleY)
		// .setInterpolator(new DecelerateInterpolator())
		// .setListener(flyListener).start();
		animate().translationX(x).translationY(y).setDuration(ANIM_TIME)
				.scaleX(scaleX).scaleY(scaleY).setListener(flyListener).start();
		}
	

	public Rect findLocationWithView(View view) {
		ViewGroup root = (ViewGroup) this.getParent();
		Rect rect = new Rect();
		root.offsetDescendantRectToMyCoords(view, rect);
		return rect;
	}

	private Animator.AnimatorListener flyListener = new Animator.AnimatorListener() {

		@Override
		public void onAnimationCancel(Animator arg0) {
			//setVisibility(View.VISIBLE);
		}

		@Override
		public void onAnimationEnd(Animator arg0) {
			setVisibility(View.VISIBLE);
		}

		@Override
		public void onAnimationRepeat(Animator arg0) {
		}

		@Override
		public void onAnimationStart(Animator arg0) {
			//setInDraw(false);
		}

	};
	
	private int left = 2;
	private int top = 2;
	private int right = 2;
	private int bottom = 2;
	private boolean isTvScreen = false;
	private long ANIM_TIME = 200L;//动画时间

}
