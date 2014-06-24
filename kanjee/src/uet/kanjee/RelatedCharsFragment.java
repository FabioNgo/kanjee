package uet.kanjee;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.os.Build;
import android.os.Bundle;




@SuppressLint("ValidFragment")
public class RelatedCharsFragment extends Fragment {
	ArrayList<KCharacter> chars;
	public RelatedCharsFragment(){
		super();
		chars = new ArrayList<KCharacter>();
	}
	public RelatedCharsFragment(ArrayList<KCharacter> chars){
		super();
		this.chars = chars;
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = null;
		if (view == null) {
			view = inflater.inflate(R.layout.sliding, container,
					false);
		}
		GridView gridView = (GridView)view.findViewById(R.id.charsGriddView);
		MyGridViewAdapter adapter = new MyGridViewAdapter(getActivity(), R.layout.model_layout, chars);
		gridView.setAdapter(adapter);
		return view;
	}
}
