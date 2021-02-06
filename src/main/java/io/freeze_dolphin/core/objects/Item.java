package io.freeze_dolphin.core.objects;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class Item extends ItemStack {

	@SuppressWarnings("deprecation")
	public Item(Material material, short data, int amount, String displayName, String... lores) {
		super(new CustomItem(new MaterialData(material, (byte) data), displayName + "&r", lores));
		this.setAmount(amount);
	}

	public Item(Material material, int amount, String displayName, String... lores) {
		super(new Item(material, (byte) 0, amount, displayName, lores));
	}

	public Item(Material material, short data, String displayName, String... lores) {
		super(new Item(material, data, 1, displayName, lores));
	}

	public Item(Material material, String displayName, String... lores) {
		super(new Item(material, (short) 0, 1, displayName, lores));
	}

	public Item(String displayName, String[] lores, String skullCode) throws IllegalArgumentException, Exception {
		super(new CustomItem(CustomSkull.getItem(skullCode), displayName + "&r", lores));
	}

	public Item(String displayName, String skullCode) throws IllegalArgumentException, Exception {
		super(new CustomItem(CustomSkull.getItem(skullCode), displayName + "&r"));
	}

	public Item(Material material, short data) {
		super();
		this.setType(material);
		this.setDurability((short) data);
	}

}
