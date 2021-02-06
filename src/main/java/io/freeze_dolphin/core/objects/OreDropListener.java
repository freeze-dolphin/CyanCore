package io.freeze_dolphin.core.objects;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import io.freeze_dolphin.core.utils.BlockUtils;

public class OreDropListener implements Listener {

	private List<Pair<Chance, ItemStack>> dropList;
	private BlockValidator blockValidator;
	private boolean checkDrop;
	private Random exp;
	private boolean fortune;

	public OreDropListener(List<Pair<Chance, ItemStack>> dropList, BlockValidator blockValidator, Random exp,
			boolean allowFortune, boolean checkDrop) {
		this.dropList = dropList;
		this.blockValidator = blockValidator;
		this.checkDrop = checkDrop;
		this.exp = exp;
		this.fortune = allowFortune;
	}

	public void register(Plugin plug) {
		Bukkit.getServer().getPluginManager().registerEvents(this, plug);
	}

	@EventHandler(ignoreCancelled = true)
	private void onMine(BlockBreakEvent e) {
		if (e.isCancelled())
			return;
		if (checkDrop) {
			if (!e.isDropItems())
				return;
		}
		if (blockValidator.check(e.getBlock())) {
			e.setExpToDrop(exp.next());
			for (Pair<Chance, ItemStack> i : dropList) {
				Chance c = i.getLeft();
				ItemStack drop = i.getRight();
				if (c.next()) {
					e.setDropItems(false);
					e.getBlock().getWorld().dropItemNaturally(BlockUtils.getBlockMiddleLocation(e.getBlock(), true),
							drop);
					if (fortune) {
						int fortuneLevel = e.getPlayer().getInventory().getItemInMainHand()
								.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
						Chance chance = new Chance(1.0 / fortuneLevel + 2);
						for (int j = 0; j < fortuneLevel; j++) {
							if (chance.next()) {
								e.getBlock().getWorld()
										.dropItemNaturally(BlockUtils.getBlockMiddleLocation(e.getBlock(), true), drop);
							}
						}
					}
				}
			}
		}
	}

	public interface BlockValidator {
		public boolean check(Block b);
	}

}
