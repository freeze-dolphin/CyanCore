package io.freeze_dolphin.cyan_core.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.md_5.bungee.api.chat.TranslatableComponent;

import org.bukkit.potion.PotionEffectType;

public class SlimefunItemUtils {

	/**
	 * @see {@link ItemEnergy}
	 */
	public static String getPowerDesc(float full) {
		return LangUtils.colorize("&c&o&8\u21E8 &e\u26A1 &70 / " + full + " J");
	}

	public static String getArmorPotionEffectDesc(PotionEffectType type, int amplifier) {
		TranslatableComponent effect = new TranslatableComponent("effect." + PotionUtils.getPotionTranslateName(type));
		switch (PotionUtils.getPotionType(type)) {
		case BENIFITFUL:
			return LangUtils.colorize(" &a+ " + effect + MathUtils.ArabicToRoman(amplifier));
		case HARMFUL:
			return LangUtils.colorize(" &c- " + effect + MathUtils.ArabicToRoman(amplifier));
		case NORMAL:
			return LangUtils.colorize(" &f· " + effect + MathUtils.ArabicToRoman(amplifier));
		case UNKNOWN:
			return LangUtils.colorize(" &7· " + effect + MathUtils.ArabicToRoman(amplifier));
		}
		return LangUtils.colorize(" &7· " + type.getName() + MathUtils.ArabicToRoman(amplifier));
	}

	public static String[] getArmorPotionEffectDesc(HashMap<PotionEffectType, Integer> types) {
		List<String> l = new LinkedList<>();
		for (PotionEffectType type : types.keySet()) {
			l.add(getArmorPotionEffectDesc(type, types.get(type)));
		}
		return l.toArray(new String[l.size()]);
	}

}
