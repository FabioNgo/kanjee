package uet.kanjee;

import java.util.ArrayList;

public class KRadical {
	private String id;
	private int color;
	private boolean onFocus;
	private int numStrokes;
	private int imagePath;
	private ArrayList<KCharacter> relatedCharacters;
	private ArrayList<KRadical> relatedRadicals;
	
	public KRadical() {
		// TODO Auto-generated constructor stub
		setId("-1");
		setNumStrokes(-1);
		onFocus=true;
		setImagePath(0);
		setRelatedCharacters(new ArrayList<KCharacter>());
		setRelatedRadicals(new ArrayList<KRadical>());
	}
	
	public KRadical(String id, int color) {
		this.id = id;
		this.color = color;
		onFocus=true;
		setRelatedCharacters(new ArrayList<KCharacter>());
		setRelatedRadicals(new ArrayList<KRadical>());
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
	 * @return the imagePath
	 */
	public int getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(int imagePath) {
		this.imagePath = imagePath;
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
	public boolean isOnFocus() {
		return onFocus;
	}
	public void setOnFocus(boolean onFocus) {
		this.onFocus = onFocus;
	}
	

}
