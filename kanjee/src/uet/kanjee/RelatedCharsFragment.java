package uet.kanjee;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SlidingDrawer;



@SuppressLint("ValidFragment")
public class RelatedCharsFragment extends Fragment implements OnClickListener{
	ArrayList<KCharacter> chars;
	SlidingDrawer slidingGV;
	public RelatedCharsFragment(){
		super();
		chars = new ArrayList<KCharacter>();
	}
	public RelatedCharsFragment(ArrayList<KCharacter> chars){
		super();
		this.chars = chars;
		slidingGV=Menu1FragmentActivity.slidingDrawer;
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = null;
		if (view == null) {
			view = inflater.inflate(R.layout.sliding, null,
					false);
		}
		GridView gridView = (GridView)view.findViewById(R.id.charsGridView);
		gridView.setNumColumns(Menu1FragmentActivity.numCol);
		//gridView.
		MyGridViewAdapter adapter = new MyGridViewAdapter(getActivity(), R.layout.model_layout, chars);
		gridView.setAdapter(adapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> parent, View v,
	                    int position, long id) {
	            	DetailCharacterFragment f = new DetailCharacterFragment(chars.get(position));
	            	
					getActivity().getSupportFragmentManager().beginTransaction()
							.replace(R.id.framelayout1, f).addToBackStack(null).commit();
					slidingGV.close();
					slidingGV.setVisibility(View.GONE);
	            }
	        });
		return view;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
