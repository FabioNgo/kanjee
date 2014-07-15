package uet.kanjee;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyGridViewAdapter extends ArrayAdapter<KCharacter>{
	private Context mContext;
	private ArrayList<KCharacter> data;	
	int size;
	
	public MyGridViewAdapter(Context context, int resource, ArrayList<KCharacter> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.data = objects;
		size =(int)(MainActivity.screenWidth)/Menu1FragmentActivity.numCol;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		KCharacter thisData = data.get(position);
		ViewHandler handler;
		if(convertView == null){
			
			//Only get the inflater when it's needed, then release it-which isn't frequently
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.model_layout , parent, false);
			handler = new ViewHandler();
			handler.rl = (RelativeLayout) convertView.findViewById(R.id.layout);
			handler.rl.getLayoutParams().height = MainActivity.screenWidth/Menu1FragmentActivity.numCol;
			handler.tv = (TextView) convertView.findViewById(R.id.content);
			
			handler.tv.setTypeface(MainActivity.font,Typeface.BOLD);
			convertView.setTag(handler);
			
		}else{
			handler = (ViewHandler) convertView.getTag();
		}
		
		
		handler.tv.setText(thisData.getText());
		handler.tv.setTextSize(size/4);
		
		
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
	public KCharacter getItem(int position) {
		
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}
}
