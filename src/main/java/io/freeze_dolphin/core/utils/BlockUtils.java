package io.freeze_dolphin.core.utils;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class BlockUtils {

	public static Location getBlockMiddleLocation(Block b, boolean affectYAxis) {
		return new Location(b.getWorld(), b.getX() + 0.5, b.getY() + (affectYAxis ? 0.5 : 0), b.getZ() + 0.5);
	}

	public static Location getBlockMiddleLocation(Block b) {
		return getBlockMiddleLocation(b, true);
	}

	public static Location getHighestLocation(Location l) {
		try {
			return new Location(l.getWorld(), l.getX(),
					Double.valueOf((String) ServerUtils.getServerProperties().get("max-build-height")).doubleValue(),
					l.getZ());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
			return l;
		}
	}

}
