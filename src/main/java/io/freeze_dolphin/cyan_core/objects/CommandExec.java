package io.freeze_dolphin.cyan_core.objects;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Triple;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.freeze_dolphin.cyan_core.interfaces.CommandRunnable;
import io.freeze_dolphin.cyan_core.utils.LangUtils;
import io.freeze_dolphin.cyan_core.utils.SystemUtils;

public class CommandExec implements CommandExecutor {

	private Map<String, Triple<String, CommandRunnable.NoPermissionRunnable, CommandRunnable.NormalRunnable>> storage = new HashMap<>();
	private final DefaultNode defaultNode;
	private final CommandRunnable.ArgumentNotMatchRunnable illegal;
	private final boolean defaultReturn;

	public static class DefaultNode {

		private final Triple<String, CommandRunnable.NoPermissionRunnable, CommandRunnable.EmptyArgumentRunnable> empty;

		public DefaultNode(String permissionNode, CommandRunnable.NoPermissionRunnable noPermissionWarning,
				CommandRunnable.EmptyArgumentRunnable behavior) {
			this.empty = Triple.of(permissionNode, noPermissionWarning, behavior);
		}

	}

	/**
	 * Add a node
	 * 
	 * @param commandNode         This must be in this format: arg1.arg2.arg3. ...
	 *                            .<*> e.g.: command "/skywars start
	 *                            FloatingIslandMap Player1 Player2 ... PlayerN"
	 *                            should be "start.FloatingIslandMap.<*>" Please
	 *                            note that using dot('.') as the split symbol means
	 *                            that you cannot use the dot in the arguments
	 * @param permissionNode      The permission to run this command
	 * @param noPermissionWarning This message will be sent while the CommandSender
	 *                            do not have the permission above
	 * @param behavior            What to do when perform the command successfully
	 */
	public void addNode(String commandNode, String permissionNode,
			CommandRunnable.NoPermissionRunnable noPermissionWarning, CommandRunnable.NormalRunnable behavior) {
		this.storage.put(commandNode, Triple.of(permissionNode, noPermissionWarning, behavior));
	}

	public CommandExec(DefaultNode defaultNode, CommandRunnable.ArgumentNotMatchRunnable executeWhenArgumentNotMatch,
			boolean defaultReturn) {
		this.defaultNode = defaultNode;
		this.illegal = executeWhenArgumentNotMatch;
		this.defaultReturn = defaultReturn;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			String permission = defaultNode.empty.getLeft();
			CommandRunnable.NoPermissionRunnable noPermission = defaultNode.empty.getMiddle();
			CommandRunnable.EmptyArgumentRunnable emptyB = defaultNode.empty.getRight();

			if (permission.equalsIgnoreCase("op") ? sender.isOp()
					: (permission.equals("") ? true : sender.hasPermission(permission))) {
				return emptyB.run(sender, cmd, label);
			} else {
				return noPermission.run(sender);
			}
		}

		boolean superMatch = false;
		for (String codeNode : storage.keySet()) {

			StringBuilder sb = new StringBuilder();
			for (String ss : args) {
				sb.append(ss).append(".");
			}
			LangUtils.deleteLastChar(sb);
			String userNode = sb.toString();

			if (userNode.equals(codeNode)) {

				superMatch = true;

				String permission = storage.get(codeNode).getLeft();
				CommandRunnable.NoPermissionRunnable noPermission = storage.get(codeNode).getMiddle();
				CommandRunnable.NormalRunnable behavior = storage.get(codeNode).getRight();

				if (permission.equalsIgnoreCase("op") ? sender.isOp()
						: (permission.equals("") ? true : sender.hasPermission(permission))) {
					return behavior.run(sender, cmd, label, args, args);
				} else {
					return noPermission.run(sender);
				}

			} else if (codeNode.matches(".*\\.<\\*>")) {

				String[] codeNodeFunctionalPartArray = codeNode.replaceAll("\\.<\\*>", "").split("\\.");
				String[] userNodeArray = userNode.split("\\.");

				int index = 0;
				boolean match = true;
				try {

					while (index < codeNodeFunctionalPartArray.length) {

						if (!codeNodeFunctionalPartArray[index].equals(userNodeArray[index])) {
							match = false;
							break;
						}
						index++;

					}

				} catch (ArrayIndexOutOfBoundsException aiofbe) {
					return this.illegal.run(sender);
				}

				if (match) {

					superMatch = true;
					// SuperMatch switch ON!
					String permission = storage.get(codeNode).getLeft();
					CommandRunnable.NoPermissionRunnable noPermission = storage.get(codeNode).getMiddle();
					CommandRunnable.NormalRunnable behavior = storage.get(codeNode).getRight();

					if (permission.equalsIgnoreCase("op") ? sender.isOp()
							: (permission.equals("") ? true : sender.hasPermission(permission))) {
						return behavior.run(sender, cmd, label, codeNodeFunctionalPartArray,
								LangUtils.divideBy(args, codeNodeFunctionalPartArray));
					} else {
						return noPermission.run(sender);
					}

				} else {
					continue;
				}

			} else {
				continue;
			}

		}

		if (!superMatch) {
			SystemUtils.debug("superMatch");
			return illegal.run(sender);
		}
		return this.defaultReturn;
	}

}
