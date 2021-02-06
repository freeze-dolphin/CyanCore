package io.freeze_dolphin.core.utils;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerUtils {

	public static void setSufferringFromRadioactivity(Player p, int tick) {
		if (tick > 0) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, tick, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, tick, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, tick, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, tick, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, tick, 1));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, tick, 1));
			p.setFireTicks(tick);
		} else {
			p.removePotionEffect(PotionEffectType.WITHER);
			p.removePotionEffect(PotionEffectType.BLINDNESS);
			p.removePotionEffect(PotionEffectType.CONFUSION);
			p.removePotionEffect(PotionEffectType.WEAKNESS);
			p.removePotionEffect(PotionEffectType.SLOW);
			p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
			p.setFireTicks(0);
		}

	}

}
