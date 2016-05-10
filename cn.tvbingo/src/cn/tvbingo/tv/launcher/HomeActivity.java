package cn.tvbingo.tv.launcher;

import cn.tvbingo.tv.launcher.widget.FlyBorderView;
import cn.tvbingo.tv.launcher.widget.MainLayout;
import cn.tvbingo.tv.launcher.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
/**
 * 
 * @Description 主页面
 * @author Joychang E-mail: changwanjob@163.com
 * @version 创建时间：2016年4月29日 下午12:34:02
 */
public class HomeActivity extends Activity implements OnClickListener,
		OnFocusChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initView();
	}

	/**
	 * 初始化view
	 * @note
	 * @author Joychang
	 * @date 创建时间：2016年5月3日 下午2:17:58
	 */
	private void initView() {
		loadViewLayout();
		findViewById();
		setListener();
	}

	
	protected void loadViewLayout() {
		ticketbgs = new ImageView[9];
		ivs = new Integer[9];
	}

	protected void findViewById() {
		fl_main = (FrameLayout) findViewById(R.id.fl_main);
		ll_home = (MainLayout) findViewById(R.id.ll_home);
		ivs = new Integer[] { R.drawable.iv_home_0, R.drawable.iv_home_1,
				R.drawable.iv_home_2, R.drawable.iv_home_4,
				R.drawable.iv_home_3, R.drawable.iv_home_5,
				R.drawable.iv_home_6, R.drawable.iv_home_7,
				R.drawable.iv_home_8 };
		ticketbgs[0] = (ImageView) findViewById(R.id.iv_home_0);
		ticketbgs[1] = (ImageView) findViewById(R.id.iv_home_1);
		ticketbgs[2] = (ImageView) findViewById(R.id.iv_home_2);
		ticketbgs[3] = (ImageView) findViewById(R.id.iv_home_3);
		ticketbgs[4] = (ImageView) findViewById(R.id.iv_home_4);
		ticketbgs[5] = (ImageView) findViewById(R.id.iv_home_5);
		ticketbgs[6] = (ImageView) findViewById(R.id.iv_home_6);
		ticketbgs[7] = (ImageView) findViewById(R.id.iv_home_7);
		ticketbgs[8] = (ImageView) findViewById(R.id.iv_home_8);

		for (int i = 0; i < ticketbgs.length; i++) {
			ticketbgs[i].setOnFocusChangeListener(this);
			ticketbgs[i].setOnClickListener(this);
			int imageId = ivs[i];
			ticketbgs[i].setImageResource(imageId);
		}
		initwhiteBorder();
	}

	protected void setListener() {
		ll_home.getViewTreeObserver().addOnGlobalFocusChangeListener(
				new OnGlobalFocusChangeListener() {
					@Override
					public void onGlobalFocusChanged(View oldFocus,
							View newFocus) {
						whiteBorder.startTranslateAnimation(newFocus, 1.0f,
								1.0f);
					}
				});
	}

	/**
	 * 初始化飞框
	 * @note
	 * @author Joychang
	 * @date 创建时间：2016年5月3日 下午2:18:31
	 */
	public void initwhiteBorder() {
		this.whiteBorder = new FlyBorderView(this);
		FrameLayout.LayoutParams layoutparams = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		whiteBorder.setLayoutParams(layoutparams);
		//设置飞框大小
		whiteBorder.setBorderPadding(
				getResources().getDimensionPixelOffset(R.dimen.sm_230),
				getResources().getDimensionPixelOffset(R.dimen.sm_100),
				getResources().getDimensionPixelOffset(R.dimen.sm_230),
				getResources().getDimensionPixelOffset(R.dimen.sm_100));
		fl_main.addView(whiteBorder);
		whiteBorder.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_home_0:
			Toast.makeText(HomeActivity.this, "iv_home_0 被点击了", 0).show();
			whiteBorder.startTranslateAnimation(v, 1.0f,
					1.0f);
			break;
		case R.id.iv_home_1:
			Toast.makeText(HomeActivity.this, "iv_home_1 被点击了", 1).show();
			whiteBorder.startTranslateAnimation(v, 1.0f,
					1.0f);
			break;
		case R.id.iv_home_2:
			Toast.makeText(HomeActivity.this, "iv_home_2被点击了", 2).show();
			whiteBorder.startTranslateAnimation(v, 1.0f,
					1.0f);
			break;
		case R.id.iv_home_3:
			Toast.makeText(HomeActivity.this, "iv_home_3 被点击了", 3).show();
			whiteBorder.startTranslateAnimation(v, 1.0f,
					1.0f);
			break;
		case R.id.iv_home_4:
			
			break;
		case R.id.iv_home_5:
			
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private static final String TAG = HomeActivity.class.getSimpleName();
	private ImageView[] ticketbgs;
	private Integer[] ivs;
	private FlyBorderView whiteBorder;
	private MainLayout ll_home;
	private FrameLayout fl_main;
}
