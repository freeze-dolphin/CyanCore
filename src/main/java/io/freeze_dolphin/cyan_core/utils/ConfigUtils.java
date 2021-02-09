package io.freeze_dolphin.cyan_core.utils;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtils {

	public static String getMessage(YamlConfiguration yaml, String reason) {
		return LangUtils.colorize(yaml.getString("messages." + reason));
	}

	public static Object getMachineData(YamlConfiguration yaml, String machineID, String data) {
		return yaml.get("machines." + convertID(machineID) + "." + data);
	}

	public static String convertID(String id) {
		return id.toLowerCase().replaceAll("_", "-");
	}

}
