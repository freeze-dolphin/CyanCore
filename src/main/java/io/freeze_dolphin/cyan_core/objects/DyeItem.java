package io.freeze_dolphin.cyan_core.objects;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.freeze_dolphin.cyan_core.utils.LangUtils;

public class DyeItem extends ItemStack {

	public enum DyeColor {

		BLACK(0), RED(1), GREEN(2), BROWN(3), BLUE(4), PURPLE(5), CYAN(6), LIGHT_GRAY(7), GRAY(8), PINK(9), LIME(10), YELLOW(11), LIGHT_BLUE(12), MAGENTA(13), ORANGE(14), WHITE(15);

		private final short damage;

		private DyeColor(int damage) {
			this.damage = (short) damage;
		}

		public short getDamage() {
			return damage;
		}

	}

	public DyeItem(DyeColor color, String displayName, String... lore) {
		super(new ItemStack(Material.INK_SACK));
		this.setDurability(color.getDamage());
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(LangUtils.colorize(displayName + "&r"));
		List<String> ls = new LinkedList<>();
		for (String s : lore) {
			ls.add(LangUtils.colorize(s));
		}
		im.setLore(ls);

	}

}
