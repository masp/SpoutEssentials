package org.skawke.masp.spoutessentials.data.storage;

import java.io.File;
import java.io.IOException;

import org.skawke.masp.spoutessentials.SpoutEssentials;

public class StorageRunnable implements Runnable {
	
	private static final String pathFormat = "(i?)\\w+([\\.\\w]+)\\w+;\\w+([\\.\\w]+)\\w+"; 
	private final DataNode[] nodes;
	private final File dataFolder;
	
	public StorageRunnable(final DataNode[] nodes) {
		this.nodes = nodes;
		this.dataFolder = SpoutEssentials.getInstance().getDataFolder();
	}
	
	@Override
	public void run() {
		initializeFiles(nodes);
	}
	
	public void initializeFiles(DataNode[] nodes) {
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
							SpoutEssentials.getInstance().getDataManager().store(dNode.getDefaultValue(), file, paths);
						}
					} else {
						for (int i = files.split(".").length - 2; i >= 0; i++) {
							
						}
					}
				}
			} catch (IOException ex) {
				SpoutEssentials.getInstance().getLogger().warning("An error has occurred in storing " + path);
				ex.printStackTrace();
			}
		}
	}
	
}
