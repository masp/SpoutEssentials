package org.skawke.masp.spoutessentials.config.configs.configfiles;

import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import org.bukkit.configuration.ConfigurationSection;
import org.skawke.masp.spoutessentials.config.configs.Config;
import org.skawke.masp.spoutessentials.config.configs.ConfigMember;
import org.skawke.masp.spoutessentials.data.DataCodes;

public class DataConfig extends Config {
	
	private HashMap<String, Stack<String>> previousMusic = new HashMap<String, Stack<String>>();
	
	private static HashMap<DataCodes, HashMap<String, String>> data = new HashMap<DataCodes, HashMap<String, String>>();
	
	public DataConfig(File dir) {
		super(new File(dir, "data.yml"));
		this.addMember(new ConfigMember("data." + DataCodes.image.getName() + ".defaultsplash", "www.images.com/splash1.png"));
		this.addMember(new ConfigMember("data." + DataCodes.image.getName() + ".defautlregionimage", "www.images.com/splash1.png"));
		
		this.addMember(new ConfigMember("data." + DataCodes.music.getName() + ".joinmusic", "http://www.music.com/welcome.ogg"));
		this.addMember(new ConfigMember("data." + DataCodes.music.getName() + ".defualtambient", "http://www.music.org/ambient.ogg"));
		this.addMember(new ConfigMember("data." + DataCodes.music.getName() + ".regionjoinmusic", "http://www.music.com/welcome.ogg"));
		
		this.addMember("data." + DataCodes.playlist.getName() + ".dungeon_playlist.gloomy_track", "http://www.music.com/dungeon_track_1.ogg");
		this.addMember("data." + DataCodes.playlist.getName() + ".dungeon_playlist.freedom_track", "http://www.music.com/dungeon_track_2.ogg");
		
		this.addMember(new ConfigMember("data." + DataCodes.cape.getName() + ".regioncape", "www.capes.com/mojang.png"));
		this.addMember(new ConfigMember("data." + DataCodes.cape.getName() + ".admincape", "www.capes.com/admin.png"));
		
		this.addMember(new ConfigMember("data." + DataCodes.skin.getName() + ".regionskin", "www.skins.com/adventurer.png"));
		this.addMember(new ConfigMember("data." + DataCodes.skin.getName() + ".adminskin", "www.skins.com/admin.png"));
		
		this.addMember(new ConfigMember("data." + DataCodes.texturepack.getName() + ".reqtpack", "www.texturepacks.com/awesome.zip"));
		this.addMember(new ConfigMember("data." + DataCodes.texturepack.getName() + ".regiontpack", "www.texturepacks.com/region.zip"));
		
		this.acceptAdditions(true);
		
		this.addComment("This file stores all the data that you can access both in the config, and in game. Just use the format DataCode(i.e i for image or s for skin):name");
		this.addComment("Example of this would be, if you wanted to display an image to all players, you could do /spe display all i:defaultsplash, which would display the default splash to everyone.");
		this.addComment("To get a playlist, you would use p:playlistname:trackname, i.e in the default config you could get the gloomy track in the dungeon playlist by doing p:dungeon_playlist:gloomy_track");
	}
	
	public void addDataValue(DataCodes code, String name, String value) {
		HashMap<String, String> dataHash = new HashMap<String, String>();
		dataHash.put(name, value);
		data.put(code, dataHash);
	}
	
	
	// Called when we want to read and store our own values, also when I want users to be able to add their own values.
	@Override
	public void loadValues() {
		for (DataCodes code : DataCodes.getDataCodes()) {
			for (String section : this.getConfig().getConfigurationSection("data." + code.getName()).getKeys(false)) {
				this.addDataValue(code, section, this.getConfig().getString("data." + code.getName() + "." + section));
			}
		}
	}
	
	
	public String getValue(DataCodes c, String name, boolean direct) {
		if (data.get(c) != null && data.get(c).get(name) != null && !direct) {
			String dataValue = data.get(c).get(name);
			return dataValue;
		} else {
			if (!((String) this.getConfig().get("data." + c.getName() + "." + name, "")).equals("")) {
				return (String) this.getConfig().get("data." + c.getName() + "." + name);
			}
		}
		return "";
	}
	
	// The current method I use for telling if I should replay a song or not doesn't check anything more than the first song. If I find it necessary to increase this
	// I will. Hence my use of Stack rather than just a singular object.
	
	public String getMusicPlaylistRandom(String playlist) {
		int i = 0;
		Random r = new Random();
		ConfigurationSection section = this.getConfig().getConfigurationSection("data." + DataCodes.playlist.getName() + "." + playlist);
		int rNum = r.nextInt(section.getKeys(false).size() - 1);
		for (String sSection : section.getKeys(false)) {
			if (i == rNum) {
				// Check to see if 
				if (this.previousMusic.get(playlist) == null) this.previousMusic.put(playlist, new Stack<String>());
				Stack<String> playStack = this.previousMusic.get(playlist);
				if (section.getKeys(false).size() <= 1 || playStack.peek() != sSection) {
					playStack.pop();
					playStack.push(sSection);
					return this.getMusicPlaylist(playlist, sSection);
				} else {
					if (section.getKeys(false).size() == 2) {
						return this.getMusicPlaylist(playlist, "data." + DataCodes.playlist.getName() + "." + playlist + "." + section.getKeys(false).toArray()[rNum == 1 ? 0 : 1]);
					}
					// If we didn't get a result, we keep trying, recursivity might be a little slow if there are 3 members, but it shouldn't be too bad.
					return this.getMusicPlaylistRandom(playlist);
				}
			}
			i++;
		}
		return "";
	}
	
	public String getMusicPlaylist(String playlist, String name) {
		return this.getConfig().getString("data." + DataCodes.playlist.getName() + "." + playlist + "." + name);
	}
	
	public String getMusic(String name) {
		return this.getConfig().getString("data." + DataCodes.music.getName() + "." + name);
	}
	
	public String getSkin(String name) {
		return this.getConfig().getString("data." + DataCodes.skin.getName() + "." + name);
	}
	
	public String getTexturePack(String name) {
		return this.getConfig().getString("data." + DataCodes.texturepack.getName() + "." + name);
	}
	
	public String getCape(String name) {
		return this.getConfig().getString("data." + DataCodes.cape.getName() + "." + name);
	}
	
	public String getImage(String name) {
		return this.getConfig().getString("data." + DataCodes.image.getName() + "." + name);
	}
	
}
