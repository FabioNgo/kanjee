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
	private boolean isHeader;
	private boolean isFilled;

	public KRadical() {
		// TODO Auto-generated constructor stub
		setId("-1");
		setNumStrokes(-1);
		onSelect = false;
		onFocus = true;
		setText("");
	}

	public KRadical(String id, int color) {
		this.id = id;
		this.color = color;
		onSelect = false;
		onFocus = true;

	}

	public KRadical(String id, int numStrokes, String text) {
		onFocus = true;
		this.setId(id);
		this.setNumStrokes(numStrokes);
		this.setText(text);
		this.isHeader = false;
		this.isFilled = false;

	}

	public KRadical(KRadical radical, boolean isHeader, boolean isFilled) {
		onFocus = true;
		if (isHeader || isFilled) {
			id = "";
		} else {
			this.setId(radical.getId());
		}
		this.setNumStrokes(radical.getNumStrokes());
		if (isHeader) {
			this.setText(String.valueOf(radical.getNumStrokes()));
		}
		if(isFilled){
			this.setText("");
		}
		this.isFilled = isFilled;
		this.isHeader = isHeader;

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param numStrokes
	 *            the numStrokes to set
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
		if (!(this.isFilled || this.isHeader)) {
			this.onSelect = onSelect;
		}
	}

	public boolean isOnFocus() {
		return onFocus;
	}

	public void setOnFocus(boolean onFocus) {
		if (!this.isHeader) {
			this.onFocus = onFocus;
		}
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the isRealText
	 */
	public boolean isHeader() {
		return isHeader;
	}

	/**
	 * @return the isFilled
	 */
	public boolean isFilled() {
		return isFilled;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof KRadical))
			return false;
		return this.getText().equals(((KRadical) other).getText());

	}

}
