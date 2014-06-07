package uet.kanjee;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class HorzGridViewAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<Model> data;	
	
	//HorzGridView stuff
	private int columns;//Used to set childSize in TwoWayGridView
	private int rows;//used with TwoWayGridView
	private int itemPadding;
	private int columnWidth;
	private int rowHeight;

	public HorzGridViewAdapter(Context context,List<Model> data){
		this.mContext = context;
		this.data = data;

		rows = 8;
		columns = 1;
		rowHeight = 100;
		columnWidth=100;
		RadicalsFragment.horzGridView.setNumRows(rows);
		RadicalsFragment.horzGridView.setRowHeight(rowHeight);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//Get the data for the given position in the array
		Model thisData = data.get(position);
		
		//Use a viewHandler to improve performance
		ViewHandler handler;
		
		//If reusing a view get the handler info; if view is null, create it
		if(convertView == null){
			
			//Only get the inflater when it's needed, then release it-which isn't frequently
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.model_layout , parent, false);
			
			//User findViewById only when first creating the child view
			handler = new ViewHandler();
			handler.iv = (ImageView) convertView.findViewById(R.id.horz_gv_iv);
			handler.tv = (TextView) convertView.findViewById(R.id.horz_gv_tv);
			convertView.setTag(handler);
			
		}else{
			handler = (ViewHandler) convertView.getTag();
		}
		
		//Set the data outside once the handler and view are instantiated
//		handler.iv.setBackgroundColor(thisData.getColor());
		
		
		handler.iv.setImageResource(thisData.getResourceImageId());
		handler.iv.setBackgroundColor(Color.BLACK);
		handler.tv.setText(thisData.getName());
		if(!thisData.isACTIVE()){
			handler.iv.getBackground().setAlpha(128);
		}
		FrameLayout.LayoutParams lp 
			= new FrameLayout.LayoutParams(columnWidth, rowHeight);// convertView.getLayoutParams();
		handler.iv.setLayoutParams(lp);

		return convertView;
	}
	
	public class ViewHandler{
		ImageView iv;
		TextView tv;
	}
	

	@Override
	public int getCount() {
		
		return data.size();
	}

	@Override
	public Model getItem(int position) {
		
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
