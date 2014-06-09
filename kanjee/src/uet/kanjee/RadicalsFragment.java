package uet.kanjee;

import java.util.ArrayList;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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
import android.widget.Toast;

import com.jess.ui.TwoWayAdapterView;
import com.jess.ui.TwoWayAdapterView.OnItemClickListener;
import com.jess.ui.TwoWayGridView;

public class RadicalsFragment extends Fragment implements OnClickListener {

	public HorzGridViewAdapter horzGridViewAdapter;
	private Context mContext;
	public static TwoWayGridView horzGridView ;
	private ArrayList<KRadical> horzData;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.radicals_fragment, container, false);
		mContext = getActivity().getApplicationContext();
		// Get handles to views that will be used
		horzGridView = (TwoWayGridView) view.findViewById(R.id.horz_gridview);
		// Create the data for use in the vert gridview-same data will be passed
		// to horz gridview
		horzData = (ArrayList<KRadical>)MainActivity.db.getAllRadicals();
		horzData  = reArrange(horzData);
		// Create the adapters for the gridviews
		horzGridViewAdapter = new HorzGridViewAdapter(mContext, R.layout.model_layout, horzData);
				
		//horzGridViewAdapter = new HorzGridViewAdapter(mContext, horzData);

		// Set the adapter for the gridviews
		horzGridView.setAdapter(horzGridViewAdapter);

		horzGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(TwoWayAdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(mContext, "clicked " + position,
						Toast.LENGTH_SHORT).show();
				ImageView image = (ImageView)view.findViewById(R.id.horz_gv_iv);
				image.setAlpha(50);
				
				//				horzData.get(position).setOnSelect(true);
//				for(int i=0;i<horzData.size();i++){
//					if(!horzData.get(position).getRelatedRadicals().contains(horzData.get(i))){
////						Log.e("",horzData.get(i).getId());
//						horzData.get(i).setOnFocus(false);
////					Log.e("",horzData.get(position).getRelatedRadicals().get(i).getId()+"");
//					}
//				}
//				horzData.get(position).setOnFocus(true);
//				
//				horzGridViewAdapter.notifyDataSetChanged();
			}
		});
		
		ViewTreeObserver observer = horzGridView.getViewTreeObserver();

	        observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

	            @SuppressLint("NewApi")
				@Override
	            public void onGlobalLayout() {
	            	int newcolw = (int) ((horzGridView.getHeight()/8));
//	            	Log.e("",""+(mContext.getResources().getDimension(R.dimen.item_padding)));
	            	int newrowh = newcolw;
	            	horzGridView.setColumnWidth(newcolw);
	            	horzGridViewAdapter.setColumnWidth(newcolw);
	            	horzGridView.setRowHeight(newrowh);
	            	horzGridViewAdapter.setRowHeight(newrowh);

	            	horzGridView.invalidateViews();
	            	
	                horzGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
	            }
	        });
		
		
		return view;
	}

	private ArrayList<KRadical> reArrange(ArrayList<KRadical> horzData) {
		// TODO Auto-generated method stub
		ArrayList<KRadical> output = new ArrayList<KRadical>();
		int curNumStroke = 0;
		int num=0;
		for(int i=0;i<horzData.size();i++){
		
		}
		return horzData;
	}

	@Override
	public void onClick(View v) {

	}

	private ArrayList<KRadical> generateGridViewObjects() {

		ArrayList<KRadical> allData = new ArrayList<KRadical>();

		String name;
		int color;
		int red;
		int green;
		int blue;
		Random rn = new Random();

		for (int i = 0; i < 500; i++) {
			// Get random data for use during testing

			red = rn.nextInt();
			blue = rn.nextInt();
			green = rn.nextInt();

			// Generate data from random info
			color = Color.argb(255, red, green, blue);
			name =  ""+i;

			KRadical singleObject = new KRadical(name, color);
			allData.add(singleObject);
		}

		allData.get(0).setImagePath(R.drawable.a1);
		allData.get(1).setImagePath(R.drawable.a11);
		allData.get(2).setImagePath(R.drawable.a12);
		allData.get(3).setImagePath(R.drawable.a13);
		allData.get(4).setImagePath(R.drawable.a14);
		allData.get(5).setImagePath(R.drawable.a15);
		allData.get(6).setImagePath(R.drawable.a16);
		allData.get(8).setImagePath(R.drawable.a2);
		allData.get(9).setImagePath(R.drawable.a21);
		allData.get(10).setImagePath(R.drawable.a22);
		allData.get(11).setImagePath(R.drawable.a23);
		allData.get(12).setImagePath(R.drawable.a24);
		allData.get(13).setImagePath(R.drawable.a25);
		allData.get(14).setImagePath(R.drawable.a26);
		allData.get(16).setImagePath(R.drawable.a3);
		allData.get(24).setImagePath(R.drawable.a4);
		allData.get(25).setImagePath(R.drawable.a41);
		allData.get(32).setImagePath(R.drawable.a5);
		allData.get(33).setImagePath(R.drawable.a51);
		allData.get(34).setImagePath(R.drawable.a52);
		allData.get(33).getRelatedRadicals().add(allData.get(4));
		allData.get(33).getRelatedRadicals().add(allData.get(10));
		allData.get(33).getRelatedRadicals().add(allData.get(25));
		allData.get(33).getRelatedRadicals().add(allData.get(34));
		return allData;
	}
}
