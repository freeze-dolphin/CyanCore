package io.freeze_dolphin.core.interfaces;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandRunnable {

	/**
	 * Called when the command executed successfully
	 * 
	 * @author freeze-dolphin
	 */
	@FunctionalInterface
	public interface NormalRunnable {
		boolean run(CommandSender sender, Command cmd, String label, String[] funcArgs, String[] multiArgs);
	}

	/**
	 * Called when the command handler cannot match the arguments
	 * 
	 * @author freeze-dolphin
	 */
	@FunctionalInterface
	public interface ArgumentNotMatchRunnable {
		boolean run(CommandSender sender);
	}

	/**
	 * Called when a command sender execute the command without right permissions
	 * 
	 * @author freeze-dolphin
	 */
	@FunctionalInterface
	public interface NoPermissionRunnable {
		boolean run(CommandSender sender);
	}

	/**
	 * Called when a command with no arguments executed (e.g. /explosion,
	 * /force-kick)
	 * 
	 * @author freeze-dolphin
	 */
	@FunctionalInterface
	public interface EmptyArgumentRunnable {
		boolean run(CommandSender sender, Command cmd, String label);
	}

}