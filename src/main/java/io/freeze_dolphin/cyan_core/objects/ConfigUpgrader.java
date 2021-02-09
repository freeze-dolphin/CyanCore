package io.freeze_dolphin.cyan_core.objects;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUpgrader {

	private final Config cfg;

	public ConfigUpgrader(String name) {
		Config c = Config.forName(name);
		if (c == null)
			throw new IllegalArgumentException("Configuration '" + name + "' is not available!");
		this.cfg = c;
		if (!check(cfg)) {
			throw new IllegalArgumentException(
					"Configuration " + cfg.getName() + " cannot pass the check of ConfigUpgrader");
		}
	}

	public int getVersion() {
		return cfg.getYaml().getInt("version");
	}

	public boolean checkVersion(int currentVersion, boolean downwardCompatible) {
		return (downwardCompatible ? (currentVersion <= getVersion()) : (currentVersion == getVersion()));
	}

	public void upgrade(int currentVersion, boolean downwardCompatible, Runnable runnable) throws IOException {
		if (!checkVersion(currentVersion, downwardCompatible)) {
			runnable.run(cfg, getVersion());
			cfg.saveYaml();
		}
	}

	public void upgrade(int currentVersion, boolean downwardCompatible, Config inPluginConfig) throws IOException {
		if (!checkVersion(currentVersion, downwardCompatible)) {
			YamlConfiguration newYaml = inPluginConfig.getYaml();
			YamlConfiguration yaml = cfg.getYaml();
			for (String key : newYaml.getKeys(true)) {
				if (!yaml.contains(key)) {
					yaml.set(key, newYaml.get(key));
				}
			}
			cfg.saveYaml();
		}
	}

	public void upgrade(int currentVersion, boolean downwardCompatible, Config inPluginConfig, Runnable runnable)
			throws IOException {
		if (!checkVersion(currentVersion, downwardCompatible)) {
			YamlConfiguration newYaml = inPluginConfig.getYaml();
			YamlConfiguration yaml = cfg.getYaml();
			for (String key : newYaml.getKeys(true)) {
				if (!yaml.contains(key)) {
					yaml.set(key, newYaml.get(key));
				}
			}
			runnable.run(cfg, getVersion());
			cfg.saveYaml();
		}
	}

	private static boolean check(Config cfg) {
		YamlConfiguration yaml = cfg.getYaml();
		return yaml.contains("version") && yaml.isInt("version");
	}

	@FunctionalInterface
	public interface Runnable {

		/**
		 * @param cfg     The local configuration (NOT the in-plugin 'config.yml')
		 * @param version The version of the local configuration (NOT the in-plugin
		 *                'config.yml')
		 * @author freeze-dolphin
		 */
		public void run(Config cfg, int version);

	}

}
