package uet.kanjee;

import java.util.ArrayList;

public class KCharacter {
	private int id;
	private int numStrokes;
	private String text;
	private String meaning;
	
	//bo cai nay	
	private String pinyin;
	
	//can them nhung cai nay
//	private String hanNhat;
//	private String amNhat;
//	private String trinhDo;
//	private String duongDanImage;
	private String other;
	private String amNhat;
	private String hanNhat;
	private ArrayList<KRadical> relatedRadicals;
	public KCharacter(){
		setId(-1);
		setNumStrokes(-1);
		setText("");
		setPinyin("");
		setMeaning("");
		setOther("");
		setRelatedRadicals(new ArrayList<KRadical>());
		setAmNhat("");
		setHanNhat("");
	}
	public KCharacter(String text){
		setText(text);
	}
	public KCharacter(int id, int numStrokes, String text, String pinyin, String meaning, String other, String amNhat, String hanNhat){
		this.setId(id);
		this.setNumStrokes(numStrokes);
		this.setText(text);
		this.setPinyin(pinyin);
		this.setMeaning(meaning);
		this.setOther(other);
		this.setAmNhat(amNhat);
		this.setHanNhat(hanNhat);
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
	/**
	 * @return the pinyin
	 */
	public String getPinyin() {
		return pinyin;
	}
	/**
	 * @param pinyin the pinyin to set
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	/**
	 * @return the meaning
	 */
	public String getMeaning() {
		return meaning;
	}
	/**
	 * @param meaning the meaning to set
	 */
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}
	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}
	/**
	 * @return the relatedRadicals
	 */
	public ArrayList<KRadical> getRelatedRadicals() {
		return relatedRadicals;
	}
	/**
	 * @param relatedRadicals the relatedRadicals to set
	 */
	public void setRelatedRadicals(ArrayList<KRadical> relatedRadicals) {
		this.relatedRadicals = relatedRadicals;
	}
	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof KCharacter))
			return false;
		return this.getText().equals(((KCharacter) other).getText());

	}
	public String getAmNhat() {
		return amNhat;
	}
	public void setAmNhat(String amNhat) {
		this.amNhat = amNhat;
	}
	public String getHanNhat() {
		return hanNhat;
	}
	public void setHanNhat(String hanNhat) {
		this.hanNhat = hanNhat;
	}
}
