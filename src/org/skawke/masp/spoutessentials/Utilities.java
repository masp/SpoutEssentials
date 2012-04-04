package org.skawke.masp.spoutessentials;

import java.io.File;

import org.skawke.masp.spoutessentials.Exceptions.SPEPathFormatException;
import org.skawke.masp.spoutessentials.managers.DataManager;

public class Utilities {
	
	private Utilities() { }
	
	public static String getFilePath(String nodePath) throws SPEPathFormatException, NullPointerException {
		String path = "";
		if (nodePath.matches(DataManager.pathFormat)) {
			String[] pathSection = nodePath.split(";");
			StringBuilder sb = new StringBuilder();
			sb.append(SpoutEssentials.getInstance().getDataManager().getDataFolder().getPath());
			for (String file : pathSection) {
				sb.append(File.pathSeparatorChar + file);
			}
			sb.append(".yml");
			path = sb.toString();
		} else {
			throw new SPEPathFormatException(nodePath);
		}
		if (path == "") {
			// Almost impossible to reach here, but oh well.
			throw new NullPointerException("Null path for the node path.");
		}
		return path;
	}
	
	public static String getDataPath(String nodePath) throws SPEPathFormatException, NullPointerException {
		String path = "";
		if (nodePath.matches(DataManager.pathFormat)) {
			path = nodePath.split(";")[1];
		} else {
			throw new SPEPathFormatException(nodePath);
		}
		if (path == "") {
			throw new NullPointerException("Null path for the data path.");
		}
		return path;
	}
	
}
