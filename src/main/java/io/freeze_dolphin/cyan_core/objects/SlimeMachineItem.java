package io.freeze_dolphin.cyan_core.objects;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.freeze_dolphin.cyan_core.utils.ItemUtils;
import io.freeze_dolphin.cyan_core.utils.LangUtils;
import io.freeze_dolphin.cyan_core.utils.SlimefunMachineUtils;

public class SlimeMachineItem extends ItemStack {

	public enum MachineType {

		MACHINE, GENERATOR, CAPACITOR

	}

	public enum GeneratorTier {

		BASIC_GENERATOR("&e基础发电机", 64, 4), MEDIUM_GENERATOR("&a中级发电机", 128, 8), ADVANCED_GENERATOR("&6高级发电机", 256, 16),
		ULTIMATE_GENERATOR("&4终极发电机", 512, 32), QUANTUM_GENERATOR("&d量子发电机", 1024, 64),

		GROUP_GENERATOR("&6发电机组", 128, 8), PORTABLE_GENERATOR("&b便携式发电机", 32, 2);

		private final String name;
		private final int defaultCache, defaultGeneration;

		private GeneratorTier(String name, int defaultCache, int defaultGeneration) {
			this.name = name;
			this.defaultCache = defaultCache;
			this.defaultGeneration = defaultGeneration;
		}

		public String getName() {
			return LangUtils.colorize(name);
		}

		public int getDefaultCache() {
			return defaultCache;
		}

		public int getDefaultGeneration() {
			return defaultGeneration;
		}

	}

	public enum MachineTier {

		BASIC_MACHINE("&e基础机器", 64, 4), MEDIUM_MACHINE("&a中级机器", 128, 8), ADVANCED_MACHINE("&6高级机器", 256, 16),
		ULTIMATE_MACHINE("&4终极机器", 512, 32), QUANTUM_MACHINE("&d量子机器", 1024, 64);

		private final String name;
		private final int defaultCache, defaultConsumption;

		private MachineTier(String name, int defaultCache, int defaultConsumption) {
			this.name = name;
			this.defaultCache = defaultCache;
			this.defaultConsumption = defaultConsumption;
		}

		public String getName() {
			return LangUtils.colorize(name);
		}

		public int getDefaultCache() {
			return defaultCache;
		}

		public int getDefaultConsumption() {
			return defaultConsumption;
		}

	}

	public enum CapacitorTier {

		SMALL_CAPACITOR("&e基础电容", 128), MEDIUM_CAPACITOR("&6中型电容", 512), BIG_CAPACITOR("&a大型电容", 1024),
		LARGE_CAPACITOR("&2超级电容", 8192), ULTIMATE_CAPACITOR("&4终极电容", 65536), QUANTUM_CAPACITOR("&d量子电容", 524288),
		ENDLESS_CAPACITOR("&8无尽电容", 8388608);

		private final String name;
		private final int defaultCapacity;

		private CapacitorTier(String name, int defaultCapacity) {
			this.name = name;
			this.defaultCapacity = defaultCapacity;
		}

		public String getName() {
			return LangUtils.colorize(name);
		}

		public int getDefaultCapacity() {
			return defaultCapacity;
		}

	}

	public static class Shell extends ItemStack {

		public Shell(Material material, short damage, String displayName, int level) {
			super(new Item(material, damage, SlimefunMachineUtils.getDisplayName(displayName, level)));
		}

		public Shell(Material material, String displayName, int level) {
			super(new Item(material, SlimefunMachineUtils.getDisplayName(displayName, level)));
		}

		public Shell(String skullCode, String displayName, int level) throws IllegalArgumentException, Exception {
			super(new Item(SlimefunMachineUtils.getDisplayName(displayName, level), skullCode));
		}

	}

	public SlimeMachineItem(ItemStack item, MachineTier tier, int speed, int cache, int eps, int cooling,
			double wastage, String[] requirements, String[] attention, String... desc) {
		super(item);
		List<String> ls = new LinkedList<>();
		ls.add(tier.getName());
		if (speed > 0)
			ls.add(SlimefunMachineUtils.getSpeed(speed));
		if (cache > 0 || cache == -1)
			ls.add(cache > 0 ? SlimefunMachineUtils.getCache(cache)
					: SlimefunMachineUtils.getCache(tier.getDefaultCache()));
		if (eps > 0 || eps == -1)
			ls.add(eps > 0 ? SlimefunMachineUtils.getConsumptionPerSecond(eps)
					: SlimefunMachineUtils.getConsumptionPerSecond(tier.getDefaultConsumption()));
		if (cooling > 0)
			ls.add(SlimefunMachineUtils.getCoolingDown(cooling));
		if (wastage > 0 && wastage < 1)
			ls.add(SlimefunMachineUtils.getWastage(wastage));
		Object[] lore = ArrayUtils.addAll(SlimefunMachineUtils.getDescription(desc), ls.toArray(new String[ls.size()]));
		Object[] reqAndAnt = ArrayUtils.addAll(SlimefunMachineUtils.getRequirement(requirements),
				SlimefunMachineUtils.getAttention(attention));
		Object[] complex = ArrayUtils.addAll(lore, reqAndAnt);
		ItemUtils.setLore(this, Arrays.asList(Arrays.copyOf(complex, complex.length, String[].class)));
	}

	public SlimeMachineItem(ItemStack item, GeneratorTier tier, int cache, int generationPerSecond,
			String[] requirements, String[] attention, String... desc) {
		super(item);
		List<String> ls = new LinkedList<>();
		ls.add(tier.getName());
		if (cache > 0 || cache == -1)
			ls.add(cache > 0 ? SlimefunMachineUtils.getCache(cache)
					: SlimefunMachineUtils.getCache(tier.getDefaultCache()));
		if (generationPerSecond > 0 || generationPerSecond == -1)
			ls.add(generationPerSecond > 0 ? SlimefunMachineUtils.getConsumptionPerSecond(generationPerSecond)
					: SlimefunMachineUtils.getConsumptionPerSecond(tier.getDefaultGeneration()));
		Object[] lore = ArrayUtils.addAll(SlimefunMachineUtils.getDescription(desc), ls.toArray(new String[ls.size()]));
		Object[] reqAndAnt = ArrayUtils.addAll(SlimefunMachineUtils.getRequirement(requirements),
				SlimefunMachineUtils.getAttention(attention));
		Object[] complex = ArrayUtils.addAll(lore, reqAndAnt);
		ItemUtils.setLore(this, Arrays.asList(Arrays.copyOf(complex, complex.length, String[].class)));
	}

	public SlimeMachineItem(ItemStack item, CapacitorTier tier, int capacity, String... desc) {
		super(item);
		List<String> ls = new LinkedList<>();
		ls.add(tier.getName());
		ls.add(SlimefunMachineUtils.getCapacity(capacity));
		Object[] lore = ArrayUtils.addAll(SlimefunMachineUtils.getDescription(desc), ls.toArray(new String[ls.size()]));
		ItemUtils.setLore(this, Arrays.asList(Arrays.copyOf(lore, lore.length, String[].class)));
	}

}
