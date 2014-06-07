package uet.kanjee;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class KRadical {
	private int id;
	private int numStrokes;
	private Bitmap image;
	private ArrayList<KCharacter> relatedCharacters;
	private ArrayList<KRadical> relatedRadicals;
	public KRadical() {
		// TODO Auto-generated constructor stub
		setId(-1);
		setNumStrokes(-1);
		setImage(null);
		setRelatedCharacters(new ArrayList<KCharacter>());
		setRelatedRadicals(new ArrayList<KRadical>());
	}
	public KRadical(int id, int numStrokes, byte[] image){
		this.setId(id);
		this.setNumStrokes(numStrokes);
		
		this.setImage(BitmapFactory.decodeByteArray(image, 0, image.length)); 
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
	 * @return the relatedCharacters
	 */
	public ArrayList<KCharacter> getRelatedCharacters() {
		return relatedCharacters;
	}
	/**
	 * @param relatedCharacters the relatedCharacters to set
	 */
	public void setRelatedCharacters(ArrayList<KCharacter> relatedCharacters) {
		this.relatedCharacters = relatedCharacters;
	}
	public ArrayList<KRadical> getRelatedRadicals() {
		return relatedRadicals;
	}
	public void setRelatedRadicals(ArrayList<KRadical> relatedRadicals) {
		this.relatedRadicals = relatedRadicals;
	}
	/**
	 * @return the image
	 */
	public Bitmap getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(Bitmap image) {
		this.image = image;
	}
	public void scaleBitmap(int percent){
		image = Bitmap.createScaledBitmap(
				image,
				(int)(image.getHeight()*percent/100.0),
				(int)(image.getWidth()*percent/100.0),
				true);
	}
	

}
