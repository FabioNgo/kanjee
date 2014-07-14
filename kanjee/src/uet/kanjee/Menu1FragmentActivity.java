package uet.kanjee;

import java.util.ArrayList;

import android.graphics.drawable.ScaleDrawable;
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
import android.support.v4.app.*;
public class Menu1FragmentActivity extends FragmentActivity {
	public static FragmentActivity activity;
	ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	ArrayList<Button> buttons = new ArrayList<Button>();
	
	Fragment menu1Fragment1;
	Fragment detailCharacterFragment;
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
		getSupportFragmentManager().beginTransaction().replace(R.id.framelayout1, menu1Fragment1).commit();
		TextView back = (TextView)findViewById(R.id.bt_back);
		back.getLayoutParams().height =  (int) (MainActivity.screenHeight*0.1);
		back.setTextSize((float) (MainActivity.screenHeight*0.01));
		back.setGravity(Gravity.CENTER);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Menu1FragmentActivity.activity.finish();
			}
		});
		TextView reset = (TextView)findViewById(R.id.bt_reset);
		reset.getLayoutParams().height =  (int) (MainActivity.screenHeight*0.1);
		reset.setTextSize((float) (MainActivity.screenHeight*0.01));
		reset.setGravity(Gravity.CENTER);
//		bt_back = (Button) findViewById(R.id.bt_back);
//		bt_reset = (Button) findViewById(R.id.bt_reset);
//		bt_back.setOnClickListener(this);
		
		slidingDrawer= (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		
		slidingButton = (Button) findViewById(R.id.handle);
		//slidingGV = (GridView)findViewById(R.id.gridView1);
	}

	@Override
	public void onBackPressed() {
		this.finish();
	}
	
	

}
