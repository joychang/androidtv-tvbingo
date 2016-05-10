package cn.tvbingo.tv.launcher.utils;

import android.app.Activity;
import android.content.Context;

/**
 * 
 * @Description 分辨率转换类
 * @author Joychang E-mail: changwanjob@163.com
 * @version 创建时间：2015年12月3日 上午11:35:42
 */
public class DensityUtil {

	public static int getScreenHeight(Activity activity) {
		return activity.getWindowManager().getDefaultDisplay().getHeight();
	}

	public static int getScreenWidth(Activity activity) {
		return activity.getWindowManager().getDefaultDisplay().getWidth();
	}

	/**
	 * 根据手机的分辨率 dp 的单位 转成 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率 px(像素) 的单位 转成 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}