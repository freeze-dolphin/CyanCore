package io.freeze_dolphin.cyan_core.objects;

import java.util.LinkedList;
import java.util.List;

import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.freeze_dolphin.cyan_core.utils.LangUtils;

public class CategoryIcon extends ItemStack {

	public CategoryIcon(Material material, short data, String displayName, String... lores) {
		super(new ItemStack(material, 1, data));
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(LangUtils.colorize(displayName));
		List<String> ls = new LinkedList<>();
		if (lores.length > 0) {
			ls.add("");

			for (String s : lores) {
				ls.add(LangUtils.colorize("&f" + s + "&r"));
			}
		}
		ls.add("");
		ls.add(LangUtils.colorize("&a> 点击打开"));
		im.setLore(ls);
		this.setItemMeta(im);
	}

	public CategoryIcon(Material material, String displayName, String... lores) {
		super(new ItemStack(material, 1, (short) 0));
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(LangUtils.colorize(displayName));
		List<String> ls = new LinkedList<>();
		if (lores.length > 0) {
			ls.add("");

			for (String s : lores) {
				ls.add(LangUtils.colorize("&f" + s + "&r"));
			}
		}
		ls.add("");
		ls.add(LangUtils.colorize("&a> 点击打开"));
		im.setLore(ls);
		this.setItemMeta(im);
	}

	public CategoryIcon(String skullCode, String displayName, String... lores)
			throws IllegalArgumentException, Exception {
		super(CustomSkull.getItem(skullCode));
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(LangUtils.colorize(displayName));
		List<String> ls = new LinkedList<>();
		if (lores.length > 0) {
			ls.add("");

			for (String s : lores) {
				ls.add(LangUtils.colorize("&f" + s + "&r"));
			}
		}
		ls.add("");
		ls.add(LangUtils.colorize("&a> 点击打开"));
		im.setLore(ls);
		this.setItemMeta(im);
	}

}
