package io.freeze_dolphin.cyan_core.utils;

import io.freeze_dolphin.cyan_core.Core;

public class SystemUtils {

	public static void debug(String debugInfo) {
		if (Core.cfg.getBoolean("debug")) {
			Core.plug.getServer().getConsoleSender().sendMessage(LangUtils.colorize("&c[Debug] &f" + debugInfo));
		}
	}

}
