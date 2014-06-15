package uet.kanjee;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(view == null){
		view = inflater.inflate(R.layout.radicals_fragment, container,
				false);
		}
		mContext = getActivity().getApplicationContext();
		// Get handles to views that will be used
		horzGridView = (TwoWayGridView) view.findViewById(R.id.horz_gridview);
		horzGridView.setColumnWidth(MainActivity.screenHeight/8);
		// Create the data for use in the vert gridview-same data will be passed
		// to horz gridview
		horzData = (ArrayList<KRadical>) MainActivity.db.getAllRadicals();
		horzDataArranged = reArrange(horzData);

		// StringBuilder stringBuilder = new StringBuilder(temp);

		// tv.setText(temp);
		// horzData = reArrange(horzData);
		// Create the adapters for the gridviews
		horzGridViewAdapter = new HorzGridViewAdapter(mContext,
				R.layout.model_layout, horzDataArranged);

		// horzGridViewAdapter = new HorzGridViewAdapter(mContext, horzData);

		// Set the adapter for the gridviews
		horzGridView.setAdapter(horzGridViewAdapter);

		horzGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(TwoWayAdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(mContext, "clicked " + position,
//						Toast.LENGTH_SHORT).show();
				ImageView bg = (ImageView)view.findViewById(R.id.bg);
				if (horzDataArranged.get(position).isOnFocus()) {
					bg.setAlpha(100);
					horzDataArranged.get(position).setOnFocus(false);
				} else {
					bg.setAlpha(0);
					horzDataArranged.get(position).setOnFocus(true);
				}

				// ImageView image =
				// (ImageView)view.findViewById(R.id.horz_gv_iv);
				// image.setAlpha(50);

				// horzData.get(position).setOnSelect(true);
				// for(int i=0;i<horzData.size();i++){
				// if(!horzData.get(position).getRelatedRadicals().contains(horzData.get(i))){
				// // Log.e("",horzData.get(i).getId());
				// horzData.get(i).setOnFocus(false);
				// //
				// Log.e("",horzData.get(position).getRelatedRadicals().get(i).getId()+"");
				// }
				// }
				// horzData.get(position).setOnFocus(true);
				//
				// horzGridViewAdapter.notifyDataSetChanged();
			}
		});

		// ViewTreeObserver observer = horzGridView.getViewTreeObserver();
		//
		// observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
		//
		// @SuppressLint("NewApi")
		// @Override
		// public void onGlobalLayout() {
		// int newcolw = (int) ((horzGridView.getHeight()/8));
		// //
		// Log.e("",""+(mContext.getResources().getDimension(R.dimen.item_padding)));
		// int newrowh = newcolw;
		// horzGridView.setColumnWidth(newcolw);
		// horzGridViewAdapter.setColumnWidth(newcolw);
		// horzGridView.setRowHeight(newrowh);
		// horzGridViewAdapter.setRowHeight(newrowh);
		//
		// horzGridView.invalidateViews();
		//
		// horzGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
		// }
		// });

		return view;
	}

	private ArrayList<KRadical> reArrange(ArrayList<KRadical> horzData) {
		// TODO Auto-generated method stub
		ArrayList<KRadical> output = new ArrayList<KRadical>();
		int curNumStroke = 0;
		int num = 0;
		for (int i = 0; ; i++) {
			if(num>0){
				if(i<horzData.size()||(num)%8!=0){
					
				}else{ 
					return output;
				}
			}
			if(i<horzData.size()){
				if (curNumStroke != horzData.get(i).getNumStrokes() && num % 8 == 0) {
					output.add(new KRadical(horzData.get(i+1),true,false));
					
					curNumStroke++;
					num++;
				}
				if (num % 8 != 0 && horzData.get(i).getNumStrokes() != curNumStroke) {
					/*
					 * add empty Radical to fill column
					 */
					while (num % 8 != 0) {
						output.add(new KRadical(horzData.get(i),false,true));
						num++;
					}
				} else {
					output.add(horzData.get(i));
					num++;
				}
			}else{
				output.add(new KRadical(output.get(num-1),false,true));
				num++;
			}
			
		}
	}

	@Override
	public void onClick(View v) {

	}

	// private ArrayList<KRadical> generateGridViewObjects() {
	//
	// ArrayList<KRadical> allData = new ArrayList<KRadical>();
	//
	// String name;
	// int color;
	// int red;
	// int green;
	// int blue;
	// Random rn = new Random();
	//
	// for (int i = 0; i < 500; i++) {
	// // Get random data for use during testing
	//
	// red = rn.nextInt();
	// blue = rn.nextInt();
	// green = rn.nextInt();
	//
	// // Generate data from random info
	// color = Color.argb(255, red, green, blue);
	// name = ""+i;
	//
	// KRadical singleObject = new KRadical(name, color);
	// allData.add(singleObject);
	// }
	// return allData;
	// }
}
