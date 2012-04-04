package org.skawke.masp.spoutessentials.data;

import java.util.ArrayList;


/*
 * Represents the data codes for names that reference the data config file.
 * i = image, m = music, t = texturepack, s = skin, c = cape. format for adding it is t:name
 */
public enum DataCodes {
	image("i", "image"),music("m", "music"),texturepack("t", "texturepack"),skin("s", "skin"),cape("c", "cape"),
	playlist("p", "playlist");
	
	private static ArrayList<DataCodes> dataCodes = new ArrayList<DataCodes>();
	
	private String name;
	private String abbrv;
	
	private DataCodes(String abbrv, String name) {
		this.name = name;
		this.abbrv = abbrv;
		addDataCode(this);
	}
	
	public static void addDataCode(DataCodes code) {
		dataCodes.add(code);
	}
	
	public static ArrayList<DataCodes> getDataCodes() {
		return dataCodes;
	}
	
	public static DataCodes getDataCode(String abbrv) {
		for (DataCodes code : dataCodes) {
			if (code.getName().equalsIgnoreCase(abbrv)) {
				return code;
			}
		}
		return null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAbbrv() {
		return this.abbrv;
	}
}