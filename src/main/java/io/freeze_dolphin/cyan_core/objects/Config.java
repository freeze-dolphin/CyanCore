package io.freeze_dolphin.cyan_core.objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.base.Charsets;

import io.freeze_dolphin.cyan_core.Core;

public class Config {

	private static List<Config> configs = new ArrayList<>();
	private YamlConfiguration yaml;
	private final File file;
	private final String name;

	public Config(String name, int version, InputStream defaultYaml) throws IOException {

		if (name == null || "".equals(name)) {
			throw new IllegalStateException("The config must own a name!");
		}

		String suffix = "";
		if (!name.matches("(.*)\\.yml")) {
			suffix = ".yml";
		}
		File file = new File(Core.cfgDir.getPath() + File.separator + name + suffix);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			FileOutputStream fos = new FileOutputStream(file);
			byte[] b = new byte[defaultYaml.available()];
			while (defaultYaml.read(b) != -1) {
				fos.write(b);
			}
			// defaultYaml.close(); This code will cause a configuration-loading error
			fos.close();
		}
		this.name = name.replaceAll("\\.yml", "");
		this.file = file;
		this.yaml = YamlConfiguration.loadConfiguration(file);
		this.yaml.set("version", version);
		this.yaml.save(this.file);
		this.yaml = YamlConfiguration.loadConfiguration(file);
		configs.add(this);
	}

	public YamlConfiguration getYaml() {
		return this.yaml;
	}

	public String getName() {
		return this.name;
	}

	public void reloadYaml() throws FileNotFoundException {
		this.yaml = YamlConfiguration.loadConfiguration(this.file);
		InputStream defConfigStream = new FileInputStream(new File(file.getAbsolutePath()));
		this.yaml.setDefaults(
				YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
	}

	public void saveYaml() throws IOException {
		this.yaml.save(this.file);
	}

	public static void reloadAll() throws FileNotFoundException {
		for (Config c : configs) {
			c.reloadYaml();
		}
	}

	public static void reload(String name) throws FileNotFoundException {
		for (Config c : configs) {
			if (name.equals(c.getName())) {
				c.reloadYaml();
			}
		}
	}

	public static Config forName(String name) {
		for (Config c : configs) {
			if (name.equals(c.getName())) {
				return c;
			}
		}
		return null;
	}

}
