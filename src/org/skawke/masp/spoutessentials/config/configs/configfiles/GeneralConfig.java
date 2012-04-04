package org.skawke.masp.spoutessentials.config.configs.configfiles;

import java.io.File;

import org.skawke.masp.spoutessentials.config.configs.Config;
import org.skawke.masp.spoutessentials.config.configs.ConfigMember;

public class GeneralConfig extends Config {
	
	public GeneralConfig(File dir) {
		super(new File(dir, "server_config.yml"));
		
		this.addGroupMember("server", "join_message", "Welcome to my server!");
		this.addGroupMember("server","join_message_subtext", "Have a good time!");
		this.addGroupMember("server", "poked_image", "DIAMOND_ORE");
		this.addGroupMember("server", "login_notification_icon", "GOLDEN_APPLE");
		this.addGroupMember("server", "splash_image", "i:defaultsplash");
		this.addGroupMember("server", "join_music", "m:joinmusic");
		this.addMember(new ConfigMember("server.command_menu_key", "~"));
		this.addMember(new ConfigMember("server.use_spoutcraft_message", "Don't have Spoutcraft? Go check it out and enjoy tons of new features!"));
		
		this.addComment("This config is for general purpose configurations that apply in all sectors/regions. Check default region for region specific functionalities");
		
	}
	
}
