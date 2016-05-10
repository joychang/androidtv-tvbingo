package cn.tvbingo.tv.launcher.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainLayout extends LinearLayout {

	public MainLayout(Context context) {
		super(context, null, 0);
		init(context);
	}

	public MainLayout(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		init(context);
	}

	public MainLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		setClipChildren(false);
		setClipToPadding(false);
	}

}
