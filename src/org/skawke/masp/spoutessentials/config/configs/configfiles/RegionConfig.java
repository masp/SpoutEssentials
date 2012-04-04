package org.skawke.masp.spoutessentials.config.configs.configfiles;

import java.io.File;

import org.skawke.masp.spoutessentials.SpoutEssentials;
import org.skawke.masp.spoutessentials.Exceptions.PermissionsNotEnabledException;
import org.skawke.masp.spoutessentials.config.configs.Config;

public class RegionConfig extends Config {
	
	public RegionConfig(File dir) {
		super(new File(dir, "region.yml"));
		
		this.addGroupRegionMember("regions", "achievement.enter_message", "Welcome to region %region%!");
		this.addGroupRegionMember("regions", "achievement.enter_sub_message", "Enjoy your stay here!");
		this.addGroupRegionMember("regions", "achievement.enter_notification_icon", "GOLDEN_APPLE");
		
		this.addGroupRegionMember("regions", "achievement.exit_message", "Goodbye from region %region%!");
		this.addGroupRegionMember("regions", "achievement.exit_sub_message", "Have a good day!");
		this.addGroupRegionMember("regions", "achievement.exit_notification_icon", "APPLE");
		
		this.addGroupRegionMember("regions", "notification.enter_message", "%region%");
		this.addGroupRegionMember("regions", "notification.enter_sub_message", "");
		this.addGroupRegionMember("regions", "notification.remain_text", "%region%");
		this.addGroupRegionMember("regions", "notification.time_before_move", "2000");
		
		this.addGroupRegionMember("regions", "splash.join_splash", "i:defaultregionimage");
		this.addGroupRegionMember("regions", "splash.exit_splash", "i:defaultregionimage");
		
		this.addGroupRegionMember("regions", "message.enter_message", "Welcome to %region%! <n> Please enjoy yourself!");
		this.addGroupRegionMember("regions", "message.exit_message", "See ya'! <n> Please come back soon!");
		
		this.addGroupRegionMember("regions", "texture.join_texturepack_set", "t:regiontpack");
		this.addGroupRegionMember("regions", "texture.reset_on_leave", "true");
		
		this.addGroupRegionMember("regions", "fog.level", "LOW");
		this.addGroupRegionMember("regions", "fog.reset_on_leave", "true");
		
		this.addGroupRegionMember("regions", "player_data.player_speed", "1.0");
		this.addGroupRegionMember("regions", "player_data.player_gravity", "1.0");
		this.addGroupRegionMember("regions", "player_data.player_jumping_distance", "1.0");
		this.addGroupRegionMember("regions", "player_data.reset_speed", "true");
		this.addGroupRegionMember("regions", "player_data.reset_gravity", "true");
		this.addGroupRegionMember("regions", "player_data.reset.jumping_distance", "true");
		
		this.addGroupRegionMember("regions", "music.join", "m:regionjoinmusic");
		this.addGroupRegionMember("regions", "music.join.repeat", "true");
		this.addGroupRegionMember("regions", "music.join.once_per_online", "false");
		this.addGroupRegionMember("regions", "music.join.once_per_existance", "false");
		
		this.addGroupRegionMember("regions", "music.exit", "p:dungeon_playlist:freedom_track");
		this.addGroupRegionMember("regions", "music.exit.once_per_online", "false");
		this.addGroupRegionMember("regions", "music.exit.once_per_existance", "false");
		this.addGroupRegionMember("regions", "music.exit.halt_music", "true");
		
		this.addComment("This file is probably the most valuable to you. It contains both the default region(Everywhere but a region) along with regions. You must have WorldGuard for this file to work besides default.");
		this.addComment("Each region has nodes for each group to allow as much customization as possible. If a person is in multiple groups with nodes, it will take the last group that the person is in.");
		this.addComment("If a person is in multiple regions, it will take the highest priority region. There are a few tags you can use to specify dynamic values, such as player names, or group/region names.");
		this.addComment("These are %region%, %player%, %group%, and <n> which specifies that a new line should present.");
		
	}
	
	public void addGroupRegionMember(String path, String member, String value) {
		this.addGroupMember(path + ".default", member, "");
		this.addGroupMember(path + ".regionname", member, value);
	}
	
	public Object getGroupRegionValue(String path, String group, String region, String member) throws PermissionsNotEnabledException {
		if (SpoutEssentials.getInstance().isPermsEnabled()) {
			if (SpoutEssentials.getInstance().isWorldGuardEnabled()) {
				return this.getGroupValue(path + "." + region, member, group);
			} else {
				return this.getGroupValue(path + ".default", member, group);
			}
		} else {
			throw new PermissionsNotEnabledException();
		}
	}
	
}
