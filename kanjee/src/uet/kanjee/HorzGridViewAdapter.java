package uet.kanjee;

import java.util.ArrayList;
import java.util.List;

import com.jess.ui.TwoWayAdapterView;
import com.jess.ui.TwoWayAdapterView.OnItemClickListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.CursorWrapper;
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
import android.widget.RelativeLayout;
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
		size =(int)(MainActivity.screenHeight*0.92)/rows;
		RadicalsFragment.horzGridView.setNumRows(rows);
		
//		RadicalsFragment.horzGridView.setRowHeight(size);
//		RadicalsFragment.horzGridView
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


	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//Get the data for the given position in the array
		KRadical thisData = data.get(position);
		ViewHandler handler;
		//Use a viewHandler to improve performance
		
		//If reusing a view get the handler info; if view is null, create it
		if(convertView == null){
			
			//Only get the inflater when it's needed, then release it-which isn't frequently
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.model_layout , parent, false);
			handler = new ViewHandler();
			handler.rl = (RelativeLayout) convertView.findViewById(R.id.layout);
			handler.tv = (TextView) convertView.findViewById(R.id.content);
			convertView.setTag(handler);
			
		}else{
			handler = (ViewHandler) convertView.getTag();
		}
		RelativeLayout relativeLayout = (RelativeLayout)convertView.findViewById(R.id.layout);
		relativeLayout.getLayoutParams().width = size;
		handler.tv.setTypeface(MainActivity.font,Typeface.BOLD);
		handler.tv.setText(thisData.getText());
		handler.tv.setTextSize(size/4);
		
		if(thisData.isOnFocus()){
			if(thisData.isOnSelect()){
				handler.rl.setBackgroundColor(Color.argb(255,0xfd,0x9a,0x00)); //cam 
			}
			else{
				handler.rl.setBackgroundColor(Color.argb(255,0, 255, 0)); //xanh
			}
		}else{
			handler.rl.setBackgroundColor(Color.argb(50,0, 255, 0)); //xanh 50/255
		}
		
		if(thisData.isFilled()){
			handler.tv.setVisibility(View.INVISIBLE);
			
		}else{
			handler.tv.setVisibility(View.VISIBLE);
		}
		if(thisData.isHeader()){
			handler.tv.setText(String.valueOf(thisData.getNumStrokes()));
		}
//		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(columnWidth, rowHeight);// convertView.getLayoutParams();
//		handler.tv.setLayoutParams(lp);
		return convertView;
	}
	
	public class ViewHandler{
		RelativeLayout rl;
		TextView tv;
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
