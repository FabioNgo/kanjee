package uet.kanjee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Toast;

import com.jess.ui.TwoWayAdapterView;
import com.jess.ui.TwoWayAdapterView.OnItemClickListener;
import com.jess.ui.TwoWayGridView;

public class RadicalsFragment extends Fragment implements OnClickListener {

	public HorzGridViewAdapter horzGridViewAdapter;
	private Context mContext;

	public static TwoWayGridView horzGridView ;

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
		final List<KRadical> horzData = generateGridViewObjects();

		// Create the adapters for the gridviews
		horzGridViewAdapter = new HorzGridViewAdapter(mContext, horzData);

		// Set the adapter for the gridviews
		horzGridView.setAdapter(horzGridViewAdapter);

		horzGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(TwoWayAdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(mContext, "clicked " + position,
						Toast.LENGTH_SHORT).show();
				for(int i=0;i<horzData.get(position).cacchulienquan.size();i++){
					horzData.get(position).cacchulienquan.get(i).setACTIVE(false);
					Log.e("",horzData.get(position).cacchulienquan.get(i).getName()+"");
				}
				
				
				horzGridView.invalidateViews();
				
				
//				Menu1FragmentActivity.getInstance().replace1();
			}
		});
		
		ViewTreeObserver observer = horzGridView.getViewTreeObserver();

	        observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

	            @SuppressLint("NewApi")
				@Override
	            public void onGlobalLayout() {
	            	Log.e("","  "+horzGridViewAdapter.getItemPadding()+" " +horzGridViewAdapter.getColumnWidth()+" "+horzGridViewAdapter.getRowHeight() + " "+ horzGridView.getHeight());

	            	
	            	
	            	int newcolw = (int) ((horzGridView.getHeight()/8));
	            	Log.e("",""+(mContext.getResources().getDimension(R.dimen.item_padding)));
	            	int newrowh = newcolw;
	            	horzGridView.setColumnWidth(newcolw);
	            	horzGridViewAdapter.setColumnWidth(newcolw);
	            	horzGridView.setRowHeight(newrowh);
	            	horzGridViewAdapter.setRowHeight(newrowh);
	            	
	            	Log.e("","  "+horzGridViewAdapter.getItemPadding()+" " +horzGridViewAdapter.getColumnWidth()+" "+horzGridViewAdapter.getRowHeight() + " "+ horzGridView.getHeight());

	            	horzGridView.invalidateViews();
	                horzGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
	            }
	        });
		
		
		return view;
	}

	@Override
	public void onClick(View v) {

	}

	private List<KRadical> generateGridViewObjects() {

		List<KRadical> allData = new ArrayList<KRadical>();

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

		allData.get(0).setResourceImageId(R.drawable.a1);
		allData.get(1).setResourceImageId(R.drawable.a11);
		allData.get(2).setResourceImageId(R.drawable.a12);
		allData.get(3).setResourceImageId(R.drawable.a13);
		allData.get(4).setResourceImageId(R.drawable.a14);
		allData.get(5).setResourceImageId(R.drawable.a15);
		allData.get(6).setResourceImageId(R.drawable.a16);
		allData.get(8).setResourceImageId(R.drawable.a2);
		allData.get(9).setResourceImageId(R.drawable.a21);
		allData.get(10).setResourceImageId(R.drawable.a22);
		allData.get(11).setResourceImageId(R.drawable.a23);
		allData.get(12).setResourceImageId(R.drawable.a24);
		allData.get(13).setResourceImageId(R.drawable.a25);
		allData.get(14).setResourceImageId(R.drawable.a26);
		allData.get(16).setResourceImageId(R.drawable.a3);
		allData.get(24).setResourceImageId(R.drawable.a4);
		allData.get(25).setResourceImageId(R.drawable.a41);
		allData.get(32).setResourceImageId(R.drawable.a5);
		allData.get(33).setResourceImageId(R.drawable.a51);
		allData.get(34).setResourceImageId(R.drawable.a52);
		allData.get(33).cacchulienquan.add(allData.get(4));
		allData.get(33).cacchulienquan.add(allData.get(10));
		allData.get(34).setACTIVE(false);
		return allData;
	}
}
