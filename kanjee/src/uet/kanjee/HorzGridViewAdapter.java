package uet.kanjee;

import java.util.ArrayList;
import java.util.List;

import com.jess.ui.TwoWayAdapterView;
import com.jess.ui.TwoWayAdapterView.OnItemClickListener;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import uet.kanjee.RadicalsFragment;
public class HorzGridViewAdapter extends ArrayAdapter<KRadical>  {
	
	public HorzGridViewAdapter(Context context, int resource, ArrayList<KRadical> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.data = objects;

		rows = 8;
		columns = 1;
//		rowHeight = 100;
//		columnWidth=100;
		size =(int)(MainActivity.screenHeight*0.92)/rows;
		RadicalsFragment.horzGridView.setNumRows(rows);
		//RadicalsFragment.horzGridView.setRowHeight(size);
		//RadicalsFragment.horzGridView.setColumnWidth(size);
	}


	private Context mContext;
	private ArrayList<KRadical> data;	
	
	//HorzGridView stuff
	private int columns;//Used to set childSize in TwoWayGridView
	private int rows;//used with TwoWayGridView
	private int itemPadding;
	private int columnWidth;
	private int rowHeight;
	private int size;

//	public HorzGridViewAdapter(Context context,ArrayList<KRadical> data){
//		this.mContext = context;
//		this.data = data;
//
//		rows = 8;
//		columns = 1;
//		rowHeight = 100;
//		columnWidth=100;
//		RadicalsFragment.horzGridView.setNumRows(rows);
//		RadicalsFragment.horzGridView.setRowHeight(rowHeight);
//	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//Get the data for the given position in the array
		KRadical thisData = data.get(position);
		TextView header = new TextView(mContext);
		TextView content = new TextView(mContext);
		//Use a viewHandler to improve performance
		
		//If reusing a view get the handler info; if view is null, create it
		if(convertView == null){
			
			//Only get the inflater when it's needed, then release it-which isn't frequently
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.model_layout , parent, false);
			header = (TextView)convertView.findViewById(R.id.header);
			content = (TextView) convertView.findViewById(R.id.content);
			//User findViewById only when first creating the child view
			//handler = new ViewHandler();
			//FrameLayout frameLayout = (FrameLayout)convertView.findViewById(R.id.layout);
			//frameLayout.getLayoutParams().height = RadicalsFragment.height/8;
			//handler.iv = (ImageView) convertView.findViewById(R.id.horz_gv_iv);
			
			
			//convertView.setTag(handler);
			
		}else{
		}
		
		//Set the data outside once the handler and view are instantiated
//		handler.iv.setBackgroundColor(thisData.getColor());
		
		//handler.iv.setImageBitmap(thisData.getImage());
		//handler.iv.setImageResource(thisData.getImagePath());
		///TextView id = (TextView) convertView.findViewById(R.id.horz_gv_tv);
		KRadical curRadical = data.get(position);
		
		
		content.setTypeface(MainActivity.font);
		content.setText(thisData.getText());
		content.setTextSize(size/4);
		if(curRadical.isFilled()){
			content.setVisibility(View.INVISIBLE);
			header.setVisibility(View.INVISIBLE);
			
		}
		if(curRadical.isHeader()){
			content.setVisibility(View.INVISIBLE);
			header.setVisibility(View.VISIBLE);
			header.setText(String.valueOf(curRadical.getNumStrokes()));
		}
		//id.setText(thisData.getId());
//		if(!thisData.isOnFocus()){
//			content.setBackgroundColor(Color.argb(90, 255, 255, 255));
//			//handler.iv.setAlpha(90);
//		}else{
//			content.setBackgroundColor(Color.argb(0, 255, 255, 255));
//		}
//		if(thisData.isOnSelect()){
			//handler.iv.setColorFilter(Color.rgb(254, 255, 113),PorterDuff.Mode.MULTIPLY);
//		}
//		FrameLayout.LayoutParams lp 
//			= new FrameLayout.LayoutParams(columnWidth, rowHeight);// convertView.getLayoutParams();
//		handler.iv.setLayoutParams(lp);

		return convertView;
	}
	

	@Override
	public int getCount() {
		
		return data.size();
	}

	@Override
	public KRadical getItem(int position) {
		
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}


	public int getItemPadding() {
		return itemPadding;
	}


	public void setItemPadding(int itemPadding) {
		this.itemPadding = itemPadding;
	}


	public int getColumnWidth() {
		return columnWidth;
	}


	public void setColumnWidth(int columnWidth) {
		this.columnWidth = columnWidth;
	}


	public int getRowHeight() {
		return rowHeight;
	}


	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}


	public int getColumns() {
		return columns;
	}


	public void setColumns(int columns) {
		this.columns = columns;
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}

}
