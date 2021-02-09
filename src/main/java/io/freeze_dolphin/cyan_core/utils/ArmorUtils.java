package io.freeze_dolphin.cyan_core.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import io.freeze_dolphin.cyan_core.enums.ArmorAttributes;

@Deprecated
public class ArmorUtils {

	/*

	The following lores must be added to the last of the item !

	Locked (ALL):
	&7&o[抗辐射]&r &7&o[抗尸变]&r &7&o[抗凋零]&r
	&7&o[抗高温]&r &7&o[抗腐蚀]&r &7&o[抗形变]&r

	Unlocked (ALL):
	&6[抗辐射]&r &a[抗尸变]&r &8[抗凋零]&r
	&c[抗高温]&r &d[抗腐蚀]&r &e[抗形变]&r

	 */

	public static List<ArmorAttributes> getArmorAttributes(ItemStack item) {
		if (!item.hasItemMeta()) return null;
		if (!item.getItemMeta().hasLore()) return null;
		List<String> lores = ItemUtils.getLores(item);
		if (lores.size() < 2) return null;
		String last1 = lores.get(lores.size() - 1);
		String last2 = lores.get(lores.size() - 2);
		if (matchLores(last1, last2)) {

			String[] a = last2.split(LangUtils.colorize("&r "));
			String[] b = last1.split(LangUtils.colorize("&r "));
			String a1 = a[0];
			String a2 = a[1];
			String a3 = a[2];
			String b1 = b[0];
			String b2 = b[1];
			String b3 = b[2];

			List<ArmorAttributes> returns = new LinkedList<>();
			if (!a1.matches("(.*)" + "(.*)")) returns.add(ArmorAttributes.ANTI_RADIATION);
			if (!a2.matches("(.*)" + "(.*)")) returns.add(ArmorAttributes.ANTI_ZOMBIE);
			if (!a3.matches("(.*)" + "(.*)")) returns.add(ArmorAttributes.ANTI_WITHER);

			if (!b1.matches("(.*)" + "(.*)")) returns.add(ArmorAttributes.HIGH_HEAT_RESISTANCE);
			if (!b2.matches("(.*)" + "(.*)")) returns.add(ArmorAttributes.CORROSION_RESISTANCE);
			if (!b3.matches("(.*)" + "(.*)")) returns.add(ArmorAttributes.DEFORMATION_RESISTANCE);

			return returns;

		} else {
			return null;
		}

	}

	public static boolean hasAttributes(ItemStack item, ArmorAttributes attribute) {
		List<ArmorAttributes> laa = new ArrayList<>();
		laa = getArmorAttributes(item);
		if (laa == null) { return false; }
		if (laa.contains(attribute)) { return true; }
		return false;
	}

	public static boolean setAttributes(ItemStack item, ArmorAttributes attribute, boolean setting) {
		List<String> lores = ItemUtils.getLores(item);
		if (lores.size() < 2) return false;
		String last1 = lores.get(lores.size() - 1);
		String last2 = lores.get(lores.size() - 2);

		if (matchLores(last1, last2)) {
			String reset = LangUtils.colorize("&r ");
			
			String[] a = last2.split(reset);
			String[] b = last1.split(reset);
			String a1 = a[0];
			String a2 = a[1];
			String a3 = a[2];
			String b1 = b[0];
			String b2 = b[1];
			String b3 = b[2];

			String tag = attribute.getName();

			outer:
				if (true) {
					if (a1.equals("(.*)" + tag)) { a1 = (setting ? (LangUtils.colorize("&6") + tag) : (LangUtils.colorize("&7&o") + tag)); break outer; }
					if (a2.equals("(.*)" + tag)) { a2 = (setting ? (LangUtils.colorize("&a") + tag) : (LangUtils.colorize("&7&o") + tag)); break outer; }
					if (a3.equals("(.*)" + tag)) { a3 = (setting ? (LangUtils.colorize("&8") + tag) : (LangUtils.colorize("&7&o") + tag)); break outer; }

					if (b1.equals("(.*)" + tag)) { b1 = (setting ? (LangUtils.colorize("&c") + tag) : (LangUtils.colorize("&7&o") + tag)); break outer; }
					if (b2.equals("(.*)" + tag)) { b2 = (setting ? (LangUtils.colorize("&d") + tag) : (LangUtils.colorize("&7&o") + tag)); break outer; }
					if (b3.equals("(.*)" + tag)) { b3 = (setting ? (LangUtils.colorize("&b") + tag) : (LangUtils.colorize("&7&o") + tag)); break outer; }
				}
			
			StringBuilder sa = new StringBuilder("");
			sa.append(a1).append(reset).append(a2).append(reset).append(a3).append(reset);
			last2 = sa.toString();
			
			StringBuilder sb = new StringBuilder("");
			sb.append(b1).append(reset).append(b2).append(reset).append(b3).append(reset);
			last1 = sb.toString();
			
			ItemUtils.setLastLore(item, 1, last1);
			ItemUtils.setLastLore(item, 2, last2);
			
			return true;
			
		} else {
			return false;
		}



	}

	private static boolean matchLores(String last1, String last2) {
		if (last2.matches("抗辐射(.*)抗尸变(.*)抗凋零") && last1.matches("抗高温(.*)抗腐蚀(.*)抗形变")) return true;
		return false;
	}

}
