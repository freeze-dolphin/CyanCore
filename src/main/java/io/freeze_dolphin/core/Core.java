package io.freeze_dolphin.core;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

	public static Plugin plug;
	public static FileConfiguration cfg;

	public static File cfgDir;

	@Override
	public void onEnable() {

		plug = this;

		if (!(new File(plug.getDataFolder().getPath() + File.separator + "config.yml").exists())) {
			this.saveDefaultConfig();
		}

		cfg = plug.getConfig();

		cfgDir = new File(plug.getDataFolder().getPath() + File.separator + "config");
		if (!cfgDir.exists())
			cfgDir.mkdir();

	}

}
