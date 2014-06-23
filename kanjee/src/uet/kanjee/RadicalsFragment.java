package uet.kanjee;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SlidingDrawer;
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
	ArrayList<Integer> radicalsSelected = new ArrayList<Integer>(); // vi tri
																	// (int) cua
																	// cac chu
																	// dang dc
																	// chon
	ArrayList<ArrayList<KRadical>> arrayOfRelatedRadicals = new ArrayList<ArrayList<KRadical>>();
	ArrayList<KRadical> relatedRadicals = new ArrayList<KRadical>();
//	SlidingDrawer slidingDrawer;
//	Button slidingButton;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.radicals_fragment, container,
					false);
		}
		mContext = getActivity().getApplicationContext();
		// Get handles to views that will be used
		horzGridView = (TwoWayGridView) view.findViewById(R.id.horz_gridview);
//		slidingDrawer = Menu1FragmentActivity.getInstance().slidingDrawer;
//		slidingButton = Menu1FragmentActivity.getInstance().slidingButton;
//		slidingButton.setText("sssss");
		
		horzGridView.setColumnWidth(MainActivity.screenHeight / 8);
		// Create the data for use in the vert gridview-same data will be passed
		// to horz gridview
		horzData = (ArrayList<KRadical>) MainActivity.db.getAllRadicals();
		horzDataArranged = reArrange(horzData);

		horzGridViewAdapter = new HorzGridViewAdapter(mContext,
				R.layout.model_layout, horzDataArranged);

		// Set the adapter for the gridviews
		horzGridView.setAdapter(horzGridViewAdapter);

		horzGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(TwoWayAdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(mContext, "clicked " + position,
						Toast.LENGTH_SHORT).show();
				doOnClick(view, position);
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

	public void doOnClick(View view, int position) {
		if (!(horzDataArranged.get(position).isFilled() || horzDataArranged
				.get(position).isHeader())) {
			
//			= getRelatedRadical(horzDataArranged
//					.get(position));
			if (!horzDataArranged.get(position).isOnSelect()) {
				radicalsSelected.add((Integer) position);
				horzDataArranged.get(position).setOnSelect(true);
				
			} else {
				radicalsSelected.remove((Integer) position);
				horzDataArranged.get(position).setOnSelect(false);
				
			}
			if (radicalsSelected.isEmpty()) {
				for (int i = 0; i < horzDataArranged.size(); i++) {
					horzDataArranged.get(i).setOnFocus(true);
					horzDataArranged.get(i).setOnSelect(false);
				}
			} else {
				arrayOfRelatedRadicals.clear();
				for(int i=0;i<radicalsSelected.size();i++){
					relatedRadicals = getRelatedRadical(horzDataArranged.get(radicalsSelected.get(i)));
					arrayOfRelatedRadicals.add(relatedRadicals);
				}
//				Log.e("",radicalsSelected.size()+ " "+arrayOfRelatedRadicals.size());
				for (int i = 0; i < horzDataArranged.size(); i++) {
					for(int j=0;j<radicalsSelected.size();j++){
						if(!conTains(arrayOfRelatedRadicals.get(j), horzDataArranged.get(i))){
							horzDataArranged.get(i).setOnFocus(false);
						}
					}
					
//					if (!conTains(relatedRadicals, horzDataArranged.get(i))) {
//						horzDataArranged.get(i).setOnFocus(false);
//					}
				}
			}
			for(int i=0;i<radicalsSelected.size();i++){
				horzDataArranged.get(radicalsSelected.get(i)).setOnFocus(true);
			}
//			abc(relatedRadicals);
		}
		horzGridViewAdapter.notifyDataSetChanged();
	}

	boolean conTains(ArrayList<KRadical> arr, KRadical kra) {
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).getText().equals(kra.getText())) {
				return true;
			}
		}
		return false;
	}
	//ham in ra atribute cua cac radical
	void abc(ArrayList<KRadical> c) {
		String b = "";
		for (int i = 0; i < c.size(); i++) {
			b += c.get(i).getText() + " ";
		}
		Log.e("", b);
		String a = "";
		for (int i = 0; i < 20; i++) {
			if (!(horzDataArranged.get(i).isFilled() || horzDataArranged.get(i)
					.isHeader())) {
				a += horzDataArranged.get(i).getText();
				if (horzDataArranged.get(i).isOnFocus())
					a += "[F]";
				if (horzDataArranged.get(i).isOnSelect())
					a += "[S]";
				a += " ";
			}
		}
		Log.e("", a);
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

	}
}
