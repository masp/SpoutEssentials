package org.skawke.masp.spoutessentials;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.getspout.spoutapi.plugin.SpoutPlugin;
import org.skawke.masp.spoutessentials.managers.DataManager;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class SpoutEssentials extends SpoutPlugin {
	
	// Stored instance so we can have a singleton class
	private static SpoutEssentials instance;
	
	private Economy econ = null;
	private Permission perms = null;
	private Chat chat = null;
	private WorldGuardPlugin wGuard = null;
	
	// Manager classes (singletons)
	DataManager dataManager = new DataManager(this.getDataFolder());
	
	
	public void onEnable() {
		instance = this;
		setupEconomy();
		setupChat();
		setupPermissions();
		setupWorldGuard();
	}
	
	public void onDisable() {
		
	}
	
	public static SpoutEssentials getInstance() {
		return instance;
	}
	
	private boolean setupEconomy() {
		if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = this.getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
	
	private boolean setupChat() {
		RegisteredServiceProvider<Chat> rsp = this.getServer().getServicesManager().getRegistration(Chat.class);
		chat = rsp.getProvider();
		return chat != null;
	}
	
	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = this.getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}
	
	private boolean setupWorldGuard() {
		Plugin plugin = this.getServer().getPluginManager().getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return false;
		}
		wGuard = (WorldGuardPlugin) plugin;
		return true;
	}
	
	public boolean isChatEnabled() {
		return chat != null;
	}
	
	public boolean isPermsEnabled() {
		return perms != null;
	}
	
	public boolean isEconEnabled() {
		return econ != null;
	}
	
	public boolean isWorldGuardEnabled() {
		return wGuard != null;
	}
	
	public WorldGuardPlugin getWorldGuard() {
		return this.wGuard;
	}
	
	public Chat getChat() {
		return this.chat;
	}
	
	public Permission getPermissions() {
		return this.perms;
	}
	
	public Economy getEcon() {
		return this.econ;
	}
	
	/*
	 * Managers!
	 */
	
	public DataManager getDataManager() {
		return this.dataManager;
	}
	
}
