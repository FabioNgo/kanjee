package uet.kanjee;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
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
	public static int STATE;
	RadicalsFragment radicalFragment;
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
		radicalFragment = new RadicalsFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.framelayout1, radicalFragment).addToBackStack(null).commit();
		
		
		
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

	public Fragment getVisibleFragment(){
		List<Fragment> fragments = getSupportFragmentManager().getFragments();
		for(Fragment fragment:fragments){
			if(fragment!=null && fragment.isVisible()){
				return fragment;
			}
		}
		return null;
	}
	@Override
	public void onBackPressed() {
		Log.e("",""+Menu1FragmentActivity.STATE);
		if(Menu1FragmentActivity.STATE==3){
			getSupportFragmentManager().beginTransaction().replace(R.id.framelayout1, new RadicalsFragment()).commit();
		}else if(Menu1FragmentActivity.STATE==2){
			this.finish();
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_back:
			onBackPressed();
			break;
		case R.id.bt_reset:
			radicalFragment.posRadicalsSelected.clear();
			for (KRadical radical : radicalFragment.horzDataArranged) {
				radical.setOnFocus(true);
				radical.setOnSelect(false);
			}
			radicalFragment.horzGridViewAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}
	
	

}
