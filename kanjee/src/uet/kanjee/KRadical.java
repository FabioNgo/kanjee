package uet.kanjee;

import java.util.ArrayList;

public class KRadical {
	private int id;
	private int numStrokes;
	private String imagePath;
	private ArrayList<KCharacter> relatedCharacters;
	private ArrayList<KRadical> relatedRadicals;
	public KRadical() {
		// TODO Auto-generated constructor stub
		setId(-1);
		setNumStrokes(-1);
		setImagePath("");
		setRelatedCharacters(new ArrayList<KCharacter>());
		setRelatedRadicals(new ArrayList<KRadical>());
	}
	public KRadical(int id, int numStrokes, String imagePath){
		this.setId(id);
		this.setNumStrokes(numStrokes);
		this.setImagePath(imagePath);
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
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
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
	

}
