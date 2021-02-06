package io.freeze_dolphin.core.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.freeze_dolphin.core.constants.StringConstants;
import io.freeze_dolphin.core.objects.StainedBlock;
import io.freeze_dolphin.core.objects.StainedBlock.BlockColor;
import io.freeze_dolphin.core.objects.StainedBlock.StainedBlockType;

public class ItemUtils {

	public static List<String> getLores(ItemStack item) {
		return (item.hasItemMeta() ? (item.getItemMeta().hasLore() ? item.getItemMeta().getLore() : null) : null);
	}

	/**
	 * Index starts from ZERO
	 * 
	 * @param index
	 */
	public static boolean setLoreIndex(ItemStack item, int index, String content) {
		List<String> lores = getLores(item);
		if (lores == null)
			return false;
		lores.set(index, content);
		item.getItemMeta().setLore(lores);
		return true;
	}

	public static boolean setLore(ItemStack item, List<String> lore) {
		if (!item.hasItemMeta())
			return false;
		ItemMeta im = item.getItemMeta();
		im.setLore(lore);
		item.setItemMeta(im);
		return true;
	}

	public static boolean setLastLore(ItemStack item, int lastIndex, String content) {
		List<String> lores = getLores(item);
		if (lores == null)
			return false;
		lores.set(lores.size() - lastIndex, content);
		item.getItemMeta().setLore(lores);
		return true;
	}

	public static ItemStack type(Material material) {
		return new ItemStack(material);
	}

	public static ItemStack amount(ItemStack item, int amount) {
		if (amount <= 0) {
			return null;
		} else {
			ItemStack rt = item.clone();
			rt.setAmount(amount);
			return rt;
		}
	}

	public static void setDisplayName(ItemStack item, String displayName) {
		if (!item.hasItemMeta())
			return;
		if (!item.getItemMeta().hasDisplayName())
			return;

		ItemMeta im = item.getItemMeta();
		im.setDisplayName(displayName);
	}

	public static ItemStack getChestMenuBorderItem() {
		return new StainedBlock(BlockColor.GRAY, StainedBlockType.GLASS_PANE, " ", StringConstants.EMPTY_STRING_ARRAY);
	}

}
