package org.skawke.masp.spoutessentials.Exceptions;

public class SPEPathFormatException extends Exception {

	private static final long serialVersionUID = -7313752913069033193L;
	
	private String path;
	
	
	public SPEPathFormatException(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
	
}
