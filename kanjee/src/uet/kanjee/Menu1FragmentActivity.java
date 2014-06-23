package uet.kanjee;

import java.util.ArrayList;

import android.os.Bundle;
import android.provider.CalendarContract.Instances;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;

public class Menu1FragmentActivity extends FragmentActivity implements
		OnClickListener {

	ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	ArrayList<Button> buttons = new ArrayList<Button>();
	
	Fragment menu1Fragment1;
	Fragment detailCharacterFragment;
	public SlidingDrawer slidingDrawer;
	public Button slidingButton;
//	public static getSD(){
//		if(SlidingDrawer==null){
//			slidingDrawer = new SlidingDrawer(getInstance(), attrs)
//		}
//	}
	ViewPager viewPager1;
	Button bt_back;
	Button bt_reset;

	private static Menu1FragmentActivity instance;
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
		
		setContentView(R.layout.menu1_fragmentactivity);
		RelativeLayout relativeLayout2 = (RelativeLayout)findViewById(R.id.RelativeLayout2);
		relativeLayout2.getLayoutParams().height = (int) (MainActivity.screenHeight*0.08);
		FrameLayout frameLayout = (FrameLayout)findViewById(R.id.framelayout1);
		frameLayout.getLayoutParams().height = (int) (MainActivity.screenHeight*0.92);
		menu1Fragment1 = new RadicalsFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.framelayout1, menu1Fragment1).commit();
		
		bt_back = (Button) findViewById(R.id.bt_back);
		bt_reset = (Button) findViewById(R.id.bt_reset);
		bt_back.setOnClickListener(this);
		
//		slidingDrawer= (SlidingDrawer) findViewById(R.id.slidingDrawer1);
//		slidingButton = (Button) findViewById(R.id.handle);
	}

	@Override
	public void onBackPressed() {
		this.finish();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_back:
			this.finish();
			break;
		default:
			break;
		}
	}

}
