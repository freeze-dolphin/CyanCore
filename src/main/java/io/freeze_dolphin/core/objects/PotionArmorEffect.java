package io.freeze_dolphin.core.objects;

import java.util.HashMap;

import org.bukkit.potion.PotionEffectType;

public class PotionArmorEffect extends HashMap<PotionEffectType, Integer> {

	private static final long serialVersionUID = 1L;

	public PotionArmorEffect() {
		super(new HashMap<PotionEffectType, Integer>());
	}

	public void addEffect(PotionEffectType type, int amplifier) {
		this.put(type, amplifier);
	}

}
