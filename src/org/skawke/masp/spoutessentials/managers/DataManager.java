package org.skawke.masp.spoutessentials.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.skawke.masp.spoutessentials.SpoutEssentials;
import org.skawke.masp.spoutessentials.config.configs.Config;
import org.skawke.masp.spoutessentials.data.Saveable;

public class DataManager {
	
	private File dataFolder;
	
	public DataManager(File file) {
		this.setDataFolder(file);
	}
	
	public void save(Saveable[] saves) {
		for (Saveable save : saves) {
			
		}
	}
	
	public void save() {
		
	}
	
	public void store(String value, File file, String path) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.set(value, path);
		try {
			config.save(file);
		} catch (IOException e) {
			SpoutEssentials.getInstance().getLogger().warning("An error has occurred in saving data " + file.getPath() + " data "  + path);
			e.printStackTrace();
		}
	}

	public File getDataFolder() {
		return dataFolder;
	}

	public void setDataFolder(File dataFolder) {
		this.dataFolder = dataFolder;
	}
	
	
	
}
