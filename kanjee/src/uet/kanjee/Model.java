package uet.kanjee;

import java.util.ArrayList;

public class Model {
	
	private int color;
	private String name;
	private int resourceImageId;
	private String nghia;
	private String hanNhat="ナイ, ダイ";
	private String amNhat;
	private static boolean ACTIVE=true;
	
	public ArrayList<Model> cacchulienquan=new ArrayList<Model>();
	
	public Model(String name, int color){
		this.color = color;
		this.name = name;
	}
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getResourceImageId() {
		return resourceImageId;
	}

	public void setResourceImageId(int resourceImageId) {
		this.resourceImageId = resourceImageId;
	}

	public static boolean isACTIVE() {
		return ACTIVE;
	}

	public static void setACTIVE(boolean aCTIVE) {
		ACTIVE = aCTIVE;
	}

}
