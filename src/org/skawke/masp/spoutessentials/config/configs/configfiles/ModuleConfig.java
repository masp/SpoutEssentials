package org.skawke.masp.spoutessentials.config.configs.configfiles;

import java.io.File;

import org.skawke.masp.spoutessentials.config.configs.Config;

public class ModuleConfig extends Config {
	
	public ModuleConfig(File dir) {
		super(new File(dir, "modules.yml"));
		
		this.addMember("regions.splashes", "false");
		this.addMember("regions.music", "false");
		this.addMember("regions.fog", "false");
		this.addMember("regions.player_mods", "false");
		this.addMember("regions.notifications", "false");
		this.addMember("regions.achievement_notify", "false");
		this.addMember("regions.message", "false");
		this.addMember("general.splashes", "false");
		this.addMember("general.music", "false");
		this.addMember("general.notification", "false");
		this.addMember("general.usespoutcraft", "false");
		this.addMember("general.command_menu", "false");
		
		this.addComment("Everything is disabled by default. You can add features as you please using this configuration.");
		
	}
	
	public boolean regionSplashesEnabled() {
		return this.getConfig().getBoolean("regions.splashes", false);
	}
	
	public boolean regionMusicEnabled() {
		return this.getConfig().getBoolean("regions.music", false);
	}
	
	public boolean regionFogEnabled() {
		return this.getConfig().getBoolean("regions.fog", false);
	}
	
	public boolean regionPlayerModsEnabled() {
		return this.getConfig().getBoolean("regions.player_mods", false);
	}
	
	public boolean regionNotificationsEnabled() {
		return this.getConfig().getBoolean("regions.notifications", false);
	}
	
	public boolean regionAchievementsEnabled() {
		return this.getConfig().getBoolean("regions.achievement_notify", false);
	}
	
	public boolean regionMessagesEnabled() {
		return this.getConfig().getBoolean("regions.message", false);
	}
	
	public boolean globalSplashesEnabled() {
		return this.getConfig().getBoolean("general.splashes", false);
	}
	
	public boolean globalMusicEnabled() {
		return this.getConfig().getBoolean("general.music", false);
	}
	
	public boolean globalNotificationsEnabled() {
		return this.getConfig().getBoolean("general.notification", false);
	}
	
	public boolean globalUseSpoutcraftEnabled() {
		return this.getConfig().getBoolean("general.usespoutcraft", false);
	}
	
	public boolean globalCommandMenuEnabled() {
		return this.getConfig().getBoolean("general.command_menu", false);
	}
	
	
}
