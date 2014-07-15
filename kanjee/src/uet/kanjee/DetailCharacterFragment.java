package uet.kanjee;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailCharacterFragment extends Fragment{
	
	private KCharacter thischar;
	private TextView characterTV;
	private TextView meaningTV;
	private TextView hanNhatTV;
	private TextView amNhatTV;
	private TextView trinhDoTV;
	private ImageView cachVietIV;
	
	
	public DetailCharacterFragment(){
		
	}
	public DetailCharacterFragment(KCharacter tchar) {
		this.thischar = tchar;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.detailcharacter_layout, null,false);
		Menu1FragmentActivity.STATE=3;
		characterTV = (TextView) v.findViewById(R.id.characterTV);
		meaningTV =  (TextView) v.findViewById(R.id.meaningTV);
		amNhatTV = (TextView)v.findViewById(R.id.amNhatTV);
		hanNhatTV = (TextView)v.findViewById(R.id.hanNhatTV);
		
		characterTV.setText(thischar.getText());
		meaningTV.setText(thischar.getMeaning());
		amNhatTV.setTypeface(MainActivity.font,Typeface.BOLD);
		amNhatTV.setText(thischar.getAmNhat());
		hanNhatTV.setTypeface(MainActivity.font,Typeface.BOLD);
		hanNhatTV.setText(thischar.getHanNhat());
		
		return v;
	}
	
	@Override
	public void onDestroy() {
		Menu1FragmentActivity.slidingDrawer.setVisibility(View.VISIBLE);
		Menu1FragmentActivity.STATE=2;
		super.onDestroy();
	}
}
