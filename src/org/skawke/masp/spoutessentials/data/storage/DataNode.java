package org.skawke.masp.spoutessentials.data.storage;

public final class DataNode {
	
	private final String path;
	private final String defValue;
	private final String val;
	
	public DataNode(String path, String defValue, String val) {
		this.path = path;
		this.defValue = defValue;
		this.val = val;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public String getValue() {
		return val;
	}
	
	public String getDefaultValue() {
		return defValue;
	}
	
}
