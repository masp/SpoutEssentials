package org.skawke.masp.spoutessentials.config.configs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class Config {
	
	private FileConfiguration config;
	
	private File file;
	
	private ArrayList<ConfigMember> members = new ArrayList<ConfigMember>();
	private ArrayList<String> comments = new ArrayList<String>();
	
	private boolean acceptsAdditions = false;
	
	public Config() throws IllegalStateException {
		throw new IllegalStateException("You may not initialize the Config class without specifying a file, extend it!");
	}
	
	public Config(File file) {
		this.file = file;
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void addComment(String comment) {
		this.comments.add(comment);
	}
	
	public ArrayList<String> getComments() {
		return this.comments;
	}
	
	public void acceptAdditions(boolean accepts) {
		this.acceptsAdditions = accepts;
	}
	
	public boolean acceptsAdditions() {
		return this.acceptsAdditions;
	}
	
	public FileConfiguration getConfig() {
		return this.config;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public void addMember(String path, String name) {
		this.members.add(new ConfigMember(path, name));
	}
	
	public void addMember(ConfigMember member) {
		this.members.add(member);
	}
	
	public ConfigMember getMember(String path) {
		for (ConfigMember member : members) {
			if (member.getPath().equalsIgnoreCase(path)) {
				return member;
			}
		}
		return null;
	}
	
	public void initializeConfig() throws IOException, NullPointerException {
		
		if (file == null) throw new NullPointerException("You need to specify a config file before initialization!");
		
		if (config == null) config = YamlConfiguration.loadConfiguration(file); 
		
		File parentFile = file.getParentFile();
		
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		
		if (!file.exists()) {
			file.createNewFile();
			for (ConfigMember member : members) {
				if (member.getDefaultValue() != null) {
					config.addDefault(member.getPath(), member.getDefaultValue());
				}
			}
			config.options().copyDefaults(true);
			config.save(file);
		}
		if (!this.acceptsAdditions) { 
			for (ConfigMember member : members) {
				member.setValue(config.get(member.getPath()));
			}
		} else {
			this.loadValues();
		}
	}
	
	public Object getValue(String path) {
		for (ConfigMember member : this.members) {
			if (member.getPath().equalsIgnoreCase(path)) {
				return config.get(member.getPath(), member.getDefaultValue());
			}
		}
		return "";
	}
	
	public void addGroupMember(String path, String member, String value) {
		this.addMember(new ConfigMember(path + ".default." + member, value));
		this.addMember(new ConfigMember(path + ".group_name" + member, value));
	}
	
	public Object getGroupValue(String path, String value, String group) {
		return this.getValue(path + "." + group + "." + value);
	}
	
	public void initializeConfig(ArrayList<String> comments) throws IOException, NullPointerException {
		String finalComment = "";
		for (String comment : comments) {
			if (comment != "") {
				finalComment += comments + "\n";
			}
		}
		this.writeComment(finalComment);
		this.initializeConfig();
	}
	
	public void writeComment(String comment) throws IOException {
		String fileText = "";
		String lineText = "";
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((lineText = br.readLine()) != null) {
			fileText += lineText + "\n";
		}
		br.close();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		fileText += comment;
		bw.write(fileText);
		bw.close();
		
	}
	
	public void loadValues() {}

}
