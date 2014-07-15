package uet.kanjee;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.ui.TwoWayAdapterView;
import com.jess.ui.TwoWayAdapterView.OnItemClickListener;
import com.jess.ui.TwoWayGridView;

public class RadicalsFragment extends Fragment implements OnClickListener {
	View view;
	public HorzGridViewAdapter horzGridViewAdapter;
	private Context mContext;
	public static TwoWayGridView horzGridView;
	private ArrayList<KRadical> horzData;
	ArrayList<KRadical> horzDataArranged;
	ArrayList<Integer> posRadicalsSelected = new ArrayList<Integer>(); 
	ArrayList<ArrayList<KRadical>> arrayOfRelatedRadicals = new ArrayList<ArrayList<KRadical>>();
	ArrayList<KRadical> relatedRadicals = new ArrayList<KRadical>();

	SlidingDrawer slidingDrawer;
	Button slidingButton;
	ArrayList<KCharacter> relatedCharacters = new ArrayList<KCharacter>();
	MyGridViewAdapter myGridViewAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.radicals_fragment, null);
		}
		Menu1FragmentActivity.STATE=2;
		mContext = getActivity().getApplicationContext();
		horzGridView = (TwoWayGridView) view.findViewById(R.id.horz_gridview);
		slidingButton = Menu1FragmentActivity.slidingButton;
		slidingDrawer = Menu1FragmentActivity.slidingDrawer;
		
		horzGridView.setColumnWidth(MainActivity.screenHeight / 8);
		horzData = (ArrayList<KRadical>) MainActivity.db.getAllRadicals();
		horzDataArranged = reArrange(horzData);

		horzGridViewAdapter = new HorzGridViewAdapter(mContext,
				R.layout.model_layout, horzDataArranged);

		horzGridView.setAdapter(horzGridViewAdapter);

		horzGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(TwoWayAdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(mContext, "clicked " + position,
						Toast.LENGTH_SHORT).show();
				
				doOnClick(view, position);
				
				ArrayList<KRadical> selectedRadicals = new ArrayList<KRadical>();
				for(Integer pos : posRadicalsSelected){
					selectedRadicals.add(horzDataArranged.get(pos));
				}
				relatedCharacters = getRelatedChars(selectedRadicals);
				slidingButton.setText(relatedCharacters.size()+" characters found");
				RelatedCharsFragment f = new RelatedCharsFragment(relatedCharacters);
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, f).commit();
				
			}
		});
		return view;
	}

	public ArrayList<KRadical> getRelatedRadical(KRadical radical) {
		ArrayList<KRadical> relatedRadicals = new ArrayList<KRadical>();
		/**
		 * Get all chars from radical
		 */
		ArrayList<KCharacter> chars = MainActivity.db
				.getCharContainingRadical(radical);
		/**
		 * Get radicals from each char
		 */
		for (KCharacter character : chars) {
			ArrayList<KRadical> temp = MainActivity.db
					.getRelatedRadicals(character);
			relatedRadicals.addAll(temp);
		}
		return relatedRadicals;
	}

	public ArrayList<KCharacter> getRelatedChars(ArrayList<KRadical> radicals){
		ArrayList<KCharacter> relatedChars1 = new ArrayList<KCharacter>();
		ArrayList<KCharacter> relatedChars2	= new ArrayList<KCharacter>();
		boolean isFirstRadical = true;
		for(KRadical radical : radicals){
			ArrayList<KCharacter> chars = MainActivity.db.getCharContainingRadical(radical);
			if(isFirstRadical) {
				relatedChars2.addAll(chars);
				isFirstRadical = false;
			}else {
				for(KCharacter chararacter : relatedChars1) {
					if(chars.contains(chararacter)) {
						
					}else {
						relatedChars2.remove(chararacter);
					}
				}
			}
			relatedChars1 = (ArrayList<KCharacter>) relatedChars2.clone();
		}

		return relatedChars2;
	}

	public void doOnClick(View view, int position) {
		if (!(horzDataArranged.get(position).isFilled() || horzDataArranged
				.get(position).isHeader())) {
			if (!horzDataArranged.get(position).isOnSelect()) {
				posRadicalsSelected.add((Integer) position);
				horzDataArranged.get(position).setOnSelect(true);

			} else {
				posRadicalsSelected.remove((Integer) position);
				horzDataArranged.get(position).setOnSelect(false);

			}
			if (posRadicalsSelected.isEmpty()) {
				for (KRadical radical : horzDataArranged) {
					radical.setOnFocus(true);
					radical.setOnSelect(false);
				}
				horzGridViewAdapter.notifyDataSetChanged();
				horzGridView.invalidateViews();
			} else {
				arrayOfRelatedRadicals.clear();
				for(Integer pos : posRadicalsSelected) {
				
					relatedRadicals = getRelatedRadical(horzDataArranged.get(pos));
					arrayOfRelatedRadicals.add(relatedRadicals);
				}
				for (KRadical radical : horzDataArranged) {
					for(int j=0;j<posRadicalsSelected.size();j++){
						if(!(arrayOfRelatedRadicals.get(j).contains(radical))){
							radical.setOnFocus(false);
						}else{
							radical.setOnFocus(true);
						}
					}
					horzGridViewAdapter.notifyDataSetChanged();
				}
			}
			for(Integer pos : posRadicalsSelected){
				horzDataArranged.get(pos).setOnFocus(true);
			}
			horzGridViewAdapter.notifyDataSetChanged();
			horzGridView.invalidateViews();
		}
		horzGridViewAdapter.notifyDataSetChanged();
		horzGridView.invalidateViews();
	}

	private ArrayList<KRadical> reArrange(ArrayList<KRadical> horzData) {
		// TODO Auto-generated method stub
		ArrayList<KRadical> output = new ArrayList<KRadical>();
		int curNumStroke = 0;
		int num = 0;
		for (int i = 0;; i++) {
			if (num > 0) {
				if (i < horzData.size() || (num) % 8 != 0) {

				} else {
					return output;
				}
			}
			if (i < horzData.size()) {
				if (curNumStroke != horzData.get(i).getNumStrokes()
						&& num % 8 == 0) { // add header
					output.add(new KRadical(horzData.get(i), true, false));

					curNumStroke++;
					num++;
				}
				if (num % 8 != 0
						&& horzData.get(i).getNumStrokes() != curNumStroke) {
					/*
					 * add empty Radical to fill column
					 */
					while (num % 8 != 0) {
						output.add(new KRadical(horzData.get(i), false, true));
						num++;

					}
					i--;

				} else {
					output.add(horzData.get(i));
					num++;
				}
			} else {
				output.add(new KRadical(output.get(num - 1), false, true));
				num++;
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.handle:
//			if(Menu1FragmentActivity.STATE==1){
//				Menu1FragmentActivity.STATE=2;
//			}else{
//				
//			}
			break;

		default:
			break;
		}
	}
}
