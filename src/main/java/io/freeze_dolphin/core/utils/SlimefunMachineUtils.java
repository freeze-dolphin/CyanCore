package io.freeze_dolphin.core.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import io.freeze_dolphin.core.objects.SlimeMachineItem;
import io.freeze_dolphin.core.objects.SlimeMachineItem.GeneratorTier;
import io.freeze_dolphin.core.objects.SlimeMachineItem.MachineTier;

public class SlimefunMachineUtils {

	public static String getDisplayName(String name, int level) {
		String levelS = "";
		if (level > 0) {
			levelS = "&7(&e" + MathUtils.ArabicToRoman(level) + "&7)";
		}
		return LangUtils.colorize(name + levelS + "&r");
	}

	public static String getSpeed(int speed) {
		return LangUtils.colorize("&8\u21E8 &7速度: " + speed + "x" + "&r");
	}

	public static String getCache(int jour) {
		return LangUtils.colorize("&8\u21E8 &e\u26A1 &7" + jour + " J 缓存" + "&r");
	}

	public static String getConsumptionPerSecond(int jour) {
		return getConsumption(jour, "s");
	}

	public static String getConsumption(int jour, String unit) {
		return LangUtils.colorize("&8\u21E8 &e\u26A1 &7" + jour + " J/" + unit + "&r");
	}

	public static String getCoolingDown(int second) {
		return LangUtils.colorize("&8\u21E8 &7冷却: &b" + second + "s" + "&r");
	}

	public static String getWastage(double wastage) {
		return LangUtils
				.colorize("&8\u21E8 &e\u26A1 &7能量损耗: &c" + MathUtils.getPercentFormat(wastage, 2, 2) + "%" + "&r");
	}

	public static String getCapacity(int capacity) {
		// "&a黑钻电容器", "", "&4终极电容", "&8\u21E8 &e\u26A1 &765536 J 容量"
		return LangUtils.colorize("&8\u21E8 &e\u26A1 &7" + capacity + "J 容量");
	}

	public static String[] getDescription(String... desc) {
		List<String> ls = new LinkedList<>();
		ls.add("");
		for (String s : desc) {
			ls.add(LangUtils.colorize("&r" + s + "&r"));
		}
		ls.add("");
		return ls.toArray(new String[ls.size()]);
	}

	public static String[] getRequirement(String[] r) {
		List<String> ls = new LinkedList<>();
		ls.add("");
		for (String s : r) {
			ls.add(LangUtils.colorize("&8\u21E8 " + s + "&r"));
		}
		return ls.toArray(new String[ls.size()]);
	}

	public static String[] getAttention(String[] a) {
		List<String> ls = new LinkedList<>();
		for (String s : a) {
			ls.add(LangUtils.colorize("&c&l! &c" + s + "&r"));
		}
		return ls.toArray(new String[ls.size()]);
	}

	/**
	 * Automatically get the lore of the machine according to the arguments
	 * 
	 * @param tier                 The tier of machine
	 * @param speed                The working speed of machine
	 * @param cache                The energy cache of machine
	 * @param consumptionPerSecond The cps of the machine
	 * @param cooling              The cooling time of the machine
	 * @param wastage              The wastage of the machine
	 * @param requirements         The requirements for the machine
	 * @param attention            Attention
	 * @param desc                 Description
	 * @return The machine lore
	 * @deprecated Use SlimeMachineItem instead {@link SlimeMachineItem}
	 */
	@Deprecated
	public static String[] assembleMachine(MachineTier tier, int speed, int cache, int consumptionPerSecond,
			int cooling, double wastage, String[] requirements, String[] attention, String... desc) {
		List<String> ls = new LinkedList<>();
		ls.add(tier.getName());
		if (speed > 0)
			ls.add(getSpeed(speed));
		if (cache > 0 || cache == -1)
			ls.add(cache > 0 ? getCache(cache) : getCache(tier.getDefaultCache()));
		if (consumptionPerSecond > 0 || consumptionPerSecond == -1)
			ls.add(consumptionPerSecond > 0 ? getConsumptionPerSecond(consumptionPerSecond)
					: getConsumptionPerSecond(tier.getDefaultConsumption()));
		if (cooling > 0)
			ls.add(getCoolingDown(cooling));
		if (wastage > 0 && wastage < 1)
			ls.add(getWastage(wastage));
		Object[] lore = ArrayUtils.addAll(getDescription(desc), ls.toArray(new String[ls.size()]));
		Object[] reqAndAnt = ArrayUtils.addAll(getRequirement(requirements), getAttention(attention));
		Object[] complex = ArrayUtils.addAll(lore, reqAndAnt);
		return Arrays.copyOf(complex, complex.length, String[].class);
	}

	/**
	 * Automatically get the lore of the generator according to the arguments
	 * 
	 * @param tier                The tier of the generator
	 * @param cache               The energy cache of the generator
	 * @param generationPerSecond The gps of the generator
	 * @param requirements        The requirements for the generator
	 * @param attention           Attention
	 * @param desc                Description
	 * @return The lore of the generator
	 * @deprecated Use SlimeMachineItem instead {@link SlimeMachineItem}
	 */
	@Deprecated
	public static String[] assembleGenerator(GeneratorTier tier, int cache, int generationPerSecond,
			String[] requirements, String[] attention, String... desc) {
		List<String> ls = new LinkedList<>();
		ls.add(tier.getName());
		if (cache > 0 || cache == -1)
			ls.add(cache > 0 ? getCache(cache) : getCache(tier.getDefaultCache()));
		if (generationPerSecond > 0 || generationPerSecond == -1)
			ls.add(generationPerSecond > 0 ? getConsumptionPerSecond(generationPerSecond)
					: getConsumptionPerSecond(tier.getDefaultGeneration()));
		Object[] lore = ArrayUtils.addAll(getDescription(desc), ls.toArray(new String[ls.size()]));
		Object[] reqAndAnt = ArrayUtils.addAll(getRequirement(requirements), getAttention(attention));
		Object[] complex = ArrayUtils.addAll(lore, reqAndAnt);
		return Arrays.copyOf(complex, complex.length, String[].class);
	}

}
