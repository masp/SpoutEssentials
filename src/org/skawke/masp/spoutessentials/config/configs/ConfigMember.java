package org.skawke.masp.spoutessentials.config.configs;

public class ConfigMember {
	
	private String path;
	
	private Object value;
	
	private Object defaultValue;
	
	public ConfigMember(String path, Object defaultValue) {
		this.value = defaultValue;
		this.defaultValue = defaultValue;
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public Object getValue() {
		return this.value;
	}
	
	public Object getDefaultValue() {
		return this.defaultValue;
	}
	
	public void setValue(Object val) {
		this.value = val;
	}
	
	public void setDefaultValue(Object val) {
		this.defaultValue = val;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
}
