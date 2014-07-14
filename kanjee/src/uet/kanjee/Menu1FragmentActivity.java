package uet.kanjee;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
public class Menu1FragmentActivity extends FragmentActivity implements OnClickListener {
	public static FragmentActivity activity;
	
	Fragment menu1Fragment1;
	Fragment detailCharacterFragment;
	@SuppressWarnings("deprecation")
	public static SlidingDrawer slidingDrawer;
	public static Button slidingButton;
	public static GridView slidingGV;
	
	ViewPager viewPager1;
	Button bt_back;
	Button bt_reset;

	private static Menu1FragmentActivity instance;
	public static int numCol = 6;
	public static Menu1FragmentActivity getInstance(){
		if (instance == null) {
			instance = new Menu1FragmentActivity();
		}
		return instance;
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		activity = this;
		setContentView(R.layout.menu1_fragmentactivity);
		RelativeLayout relativeLayout2 = (RelativeLayout)findViewById(R.id.RelativeLayout2);
		relativeLayout2.getLayoutParams().height = (int) (MainActivity.screenHeight*0.08);
		FrameLayout frameLayout = (FrameLayout)findViewById(R.id.framelayout1);
		frameLayout.getLayoutParams().height = (int) (MainActivity.screenHeight*0.92);
		menu1Fragment1 = new RadicalsFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.framelayout1, menu1Fragment1).addToBackStack(null).commit();
		TextView back = (TextView)findViewById(R.id.bt_back);
		back.getLayoutParams().height =  (int) (MainActivity.screenHeight*0.1);
		back.setTextSize((float) (MainActivity.screenHeight*0.015));
		back.setGravity(Gravity.CENTER);
		back.setOnClickListener(this);
		TextView reset = (TextView)findViewById(R.id.bt_reset);
		reset.getLayoutParams().height =  (int) (MainActivity.screenHeight*0.1);
		reset.setTextSize((float) (MainActivity.screenHeight*0.015));
		reset.setGravity(Gravity.CENTER);
		reset.setOnClickListener(this);
		slidingDrawer= (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		
		slidingButton = (Button) findViewById(R.id.handle);
	}

	@Override
	public void onBackPressed() {
		if(getSupportFragmentManager().findFragmentById(R.layout.detailcharacter_layout) != null){
			getSupportFragmentManager().executePendingTransactions();
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_back:
			onBackPressed();
			
			break;
		case R.id.bt_reset:
			break;
		default:
			break;
		}
	}
	
	

}
