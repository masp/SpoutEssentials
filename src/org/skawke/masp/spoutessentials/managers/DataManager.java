package org.skawke.masp.spoutessentials.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.skawke.masp.spoutessentials.SpoutEssentials;
import org.skawke.masp.spoutessentials.Utilities;
import org.skawke.masp.spoutessentials.Exceptions.SPEPathFormatException;
import org.skawke.masp.spoutessentials.data.Saveable;
import org.skawke.masp.spoutessentials.data.storage.DataNode;

public class DataManager {
	
	private File dataFolder;
	
	public static final String pathFormat = "(i?)\\w+([\\.\\w]+)\\w+;\\w+([\\.\\w]+)\\w+"; 
	
	public DataManager(File file) {
		this.setDataFolder(file);
	}
	
	public void save(Saveable[] saves) {
		for (Saveable save : saves) {
			this.initializeFiles(save.getData());
			for (DataNode node : save.getData()) {
				try {
					this.store(node.getValue(), new File(Utilities.getFilePath(node.getPath())), Utilities.getDataPath(node.getPath()));
				} catch (NullPointerException e) {
					e.printStackTrace();
				} catch (SPEPathFormatException e) {
					SpoutEssentials.getInstance().getLogger().warning("Invalid format for path " + e.getPath());
					e.printStackTrace();
				}
			}
		}
	}
	
	public void save(Saveable save) {
		Saveable[] saves = new Saveable[1];
		saves[0] = save;
		this.save(saves);
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
	
	public void initializeFiles(DataNode[] nodes) {
		DataManager dManager = SpoutEssentials.getInstance().getDataManager();
		for (DataNode dNode : nodes) {
			String path = dNode.getPath();
			try {
				if (!path.matches(pathFormat)) {
					String files = path.split(";")[0];
					String paths = path.split(";")[1];
					if (files.split(".").length == 1) {
						File file = new File(dataFolder, files.split(".")[0]);
						if (!file.exists()) {
							file.createNewFile();
							dManager.store(dNode.getDefaultValue(), file, paths);
						}
					} else {
						for (int i = 0; i < files.split(".").length - 2; i++) {
							File dir = new File(dataFolder, files.split(".")[i]);
							if (!dir.exists()) {
								dir.mkdirs();
							}
						}
						File file = new File(dataFolder, files.split(".")[files.split(".").length - 1]);
						if (!file.exists()) {
							file.createNewFile();
							dManager.store(dNode.getDefaultValue(), file, paths);
						}
					}
				}
			} catch (IOException ex) {
				SpoutEssentials.getInstance().getLogger().warning("An error has occurred in storing " + path);
				ex.printStackTrace();
			}
		}
	}

	public File getDataFolder() {
		return dataFolder;
	}

	public void setDataFolder(File dataFolder) {
		this.dataFolder = dataFolder;
	}
	
	
	
}
