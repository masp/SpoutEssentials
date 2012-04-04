package org.skawke.masp.spoutessentials.data;

public enum Fog {
	
	SHORT("SHORT"),TINY("TINY"),FAR("FAR"),MEDIUM("MEDIUM");
	
	String name;
	
	private Fog(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}
