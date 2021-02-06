package io.freeze_dolphin.core.objects;

import java.util.LinkedList;
import java.util.List;

import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.freeze_dolphin.core.utils.LangUtils;

public class SlimeItem extends ItemStack {

	public SlimeItem(Material type, int damage, String displayName, boolean radioactivity, String... desc) {
		super(type, 1, (short) damage);
		List<String> ls = new LinkedList<>();
		if (desc.length > 0) {
			ls.add("");
			for (String s : desc) {
				ls.add(LangUtils.colorize("&r" + s));
			}
		}
		if (radioactivity) {
			ls.add("");
			ls.add(LangUtils.colorize("&2辐射等级: 高"));
			ls.add(LangUtils.colorize("&4需要穿着防护服使用"));
		}
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(LangUtils.colorize(displayName + "&r"));
		im.setLore(ls);
	}

	public SlimeItem(Material type, String displayName, boolean radioactivity, String... desc) {
		super(type, 1, (short) 0);
		List<String> ls = new LinkedList<>();
		if (desc.length > 0) {
			ls.add("");
			for (String s : desc) {
				ls.add(LangUtils.colorize("&r" + s));
			}
		}
		if (radioactivity) {
			ls.add("");
			ls.add(LangUtils.colorize("&2辐射等级: 高"));
			ls.add(LangUtils.colorize("&4需要穿着防护服使用"));
		}
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(LangUtils.colorize(displayName + "&r"));
		im.setLore(ls);
	}

	public SlimeItem(Material type, String displayName, boolean radioactivity) {
		super(type, 1, (short) 0);
		List<String> ls = new LinkedList<>();
		if (radioactivity) {
			ls.add("");
			ls.add(LangUtils.colorize("&2辐射等级: 高"));
			ls.add(LangUtils.colorize("&4需要穿着防护服使用"));
		}
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(LangUtils.colorize(displayName + "&r"));
		im.setLore(ls);
	}

	public SlimeItem(String skullCode, String displayName, boolean radioactivity, String... desc)
			throws IllegalArgumentException, Exception {
		super(CustomSkull.getItem(skullCode));
		List<String> ls = new LinkedList<>();
		if (desc.length > 0) {
			ls.add("");
			for (String s : desc) {
				ls.add(LangUtils.colorize("&r" + s));
			}
		}
		if (radioactivity) {
			ls.add("");
			ls.add(LangUtils.colorize("&2辐射等级: 高"));
			ls.add(LangUtils.colorize("&4需要穿着防护服使用"));
		}
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(LangUtils.colorize(displayName + "&r"));
		im.setLore(ls);
	}

}
