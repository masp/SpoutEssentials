package org.skawke.masp.spoutessentials.data;

import java.io.File;

import org.bukkit.entity.Player;
import org.skawke.masp.spoutessentials.data.storage.DataNode;

public class PlayerData implements Saveable {
	
	private String name;
	private Fog fog = Fog.MEDIUM;
	private String world;
	private String previousTexturePack;
	private float previousJump;
	private float previousGravity;
	private float previousSpeed;
	
	public PlayerData(String name, String world) {
		this.name = name;
		this.world = world;
	}
	
	public void setFog(Fog fog) {
		this.fog = fog;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrevTextPack(String url) {
		this.previousTexturePack = url;
	}
	
	public String getPrevTextPack() {
		return this.previousTexturePack;
	}
	
	public Fog getFog() {
		return this.fog;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getWorld() {
		return this.world;
	}
	
	public float getPreviousJump() {
		return previousJump;
	}

	public void setPreviousJump(float previousJump) {
		this.previousJump = previousJump;
	}

	public float getPreviousGravity() {
		return previousGravity;
	}

	public void setPreviousGravity(float previousGravity) {
		this.previousGravity = previousGravity;
	}

	public float getPreviousSpeed() {
		return previousSpeed;
	}

	public void setPreviousSpeed(float previousSpeed) {
		this.previousSpeed = previousSpeed;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Player) {
			Player player = (Player) o;
			return player.getName().equalsIgnoreCase(this.getName()) && player.getWorld().equals(player.getWorld());
		} else if (o instanceof PlayerData) {
			PlayerData data = (PlayerData) o;
			return data.getName().equalsIgnoreCase(this.getName()) && data.getWorld().equalsIgnoreCase(this.getWorld());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.getName().hashCode() + this.getWorld().hashCode();
	}

	@Override
	public DataNode[] getData() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
