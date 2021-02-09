package io.freeze_dolphin.cyan_core.objects;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import io.freeze_dolphin.cyan_core.Core;
import io.freeze_dolphin.cyan_core.utils.ClassUtils;

public class VersionAdapter {

	private static final int version;

	static {
		version = Integer.parseInt(Core.plug.getDescription().getVersion());
	}

	private final YamlConfiguration yaml;
	private final Plugin plug;

	public VersionAdapter(Plugin plug) throws ClassNotFoundException {
		Class<?> clazz = Class.forName(plug.getDescription().getMain());
		InputStream is = ClassUtils.getResource(clazz, "plugin.yml");
		this.yaml = YamlConfiguration.loadConfiguration(new InputStreamReader(is));
		this.plug = plug;
		if (!yaml.contains("supported-cyan-core-version")) {
			throw new IllegalArgumentException("The description file of plugin " + plug.getName() + " contains no necessary item 'supported-cyan-core-version'");
		}
	}

	public int getSupportedCoreVersion() {
		return yaml.getInt("supported-cyan-core-version");
	}

	public boolean isVersionSupported() {
		try {
			boolean result = version >= getSupportedCoreVersion();
			return result;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public void checkVersion() {
		if (isVersionSupported()) {
			plug.getLogger().info("Detected supported CyanCore version: " + version);
		} else {
			plug.getLogger().warning("Self-disabling because of the unsupported CyanCore version: " + version);
			Bukkit.getServer().getPluginManager().disablePlugin(plug);
		}
	}

}
