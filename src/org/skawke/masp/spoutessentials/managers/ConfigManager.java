package org.skawke.masp.spoutessentials.managers;

import java.io.IOException;
import java.util.ArrayList;

import org.skawke.masp.spoutessentials.SpoutEssentials;
import org.skawke.masp.spoutessentials.config.configs.Config;
import org.skawke.masp.spoutessentials.config.configs.configfiles.DataConfig;
import org.skawke.masp.spoutessentials.config.configs.configfiles.GeneralConfig;
import org.skawke.masp.spoutessentials.config.configs.configfiles.RegionConfig;

public class ConfigManager {
	
	private ArrayList<Config> configs = new ArrayList<Config>();
	
	public ConfigManager() {
		SpoutEssentials plugin = SpoutEssentials.getInstance();
		Config dataConfig = new DataConfig(plugin.getDataFolder());
		Config genConfig = new GeneralConfig(plugin.getDataFolder());
		Config regionConfig = new RegionConfig(plugin.getDataFolder());
		this.addConfig(dataConfig);
		this.addConfig(genConfig);
		this.addConfig(regionConfig);
	}
	
	public void addConfig(Config config) {
		this.configs.add(config);
	}
	
	public void removeConfig(Config config) {
		this.configs.remove(config);
	}
	
	public void initializeConfigs() throws NullPointerException, IOException {
		for (Config config : this.configs) {
			config.initializeConfig(config.getComments());
		}
	}
	
}
