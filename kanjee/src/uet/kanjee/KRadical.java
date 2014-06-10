package uet.kanjee;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class KRadical {
	private String id;
	private int color;
	private boolean onFocus;
	private boolean onSelect;
	private int numStrokes;
	private String text;
	public KRadical() {
		// TODO Auto-generated constructor stub
		setId("-1");
		setNumStrokes(-1);
		onSelect=false;
		onFocus=true;
		setText("");
	}
	
	public KRadical(String id, int color) {
		this.id = id;
		this.color = color;
		onSelect=false;
		onFocus=true;
		
	}
	public KRadical(String id, int numStrokes, String text){
		onFocus=true;
		this.setId(id);
		this.setNumStrokes(numStrokes);
		this.setText(text);
		
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the numStrokes
	 */
	public int getNumStrokes() {
		return numStrokes;
	}
	/**
	 * @param numStrokes the numStrokes to set
	 */
	public void setNumStrokes(int numStrokes) {
		this.numStrokes = numStrokes;
	}
	
	
	
	/**
	 * @return the image
	 */
	

	public boolean isOnSelect() {
		return onSelect;
	}

	public void setOnSelect(boolean onSelect) {
		this.onSelect = onSelect;
	}

	

	public boolean isOnFocus() {
		return onFocus;
	}

	public void setOnFocus(boolean onFocus) {
		this.onFocus = onFocus;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	

}
