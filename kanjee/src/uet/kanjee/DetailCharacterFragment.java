package uet.kanjee;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
		View v = inflater.inflate(R.layout.detailcharacter_layout, container,false);
		characterTV = (TextView) v.findViewById(R.id.characterTV);
		meaningTV =  (TextView) v.findViewById(R.id.meaningTV);
		
		
		
		characterTV.setText(thischar.getText());
		meaningTV.setText(thischar.getMeaning());
		
		
		return v;
	}
}
