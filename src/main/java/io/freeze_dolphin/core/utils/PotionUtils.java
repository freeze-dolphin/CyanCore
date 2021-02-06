package io.freeze_dolphin.core.utils;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionUtils {

	/**
	 * The format of PotionCode is:
	 * <PotionEffectType'name[S]>-<duration[I]>-<amplifier[I]>-<ambient[B]>-<particles[B]>
	 * REMOVE the '<' & '>' when calling!
	 * 
	 * @param PotionCode
	 * @return A translated PotionEffect
	 */
	public static PotionEffect craftPotionEffect(String potioncode) {
		String[] splited = potioncode.split("-");
		return new PotionEffect(PotionEffectType.getByName(splited[0]), Integer.valueOf(splited[1]).intValue(),
				Integer.valueOf(splited[2]).intValue(), Boolean.parseBoolean(splited[3]),
				Boolean.parseBoolean(splited[4]));
	}

	/**
	 * You must insert the symbol '_' between two PotionCodes to split them!
	 * 
	 * @param potioncodes
	 * @return An array of translated PotionEffect
	 */
	public static PotionEffect[] craftPotionEffects(String potioncodes) {
		List<PotionEffect> pel = new LinkedList<>();
		for (String s : potioncodes.split("_")) {
			pel.add(craftPotionEffect(s));
		}
		return pel.toArray(new PotionEffect[pel.size()]);
	}

	private static final PotionEffectType[] HARMFUL = new PotionEffectType[] { PotionEffectType.BLINDNESS,
			PotionEffectType.CONFUSION, PotionEffectType.HARM, PotionEffectType.HUNGER, PotionEffectType.POISON,
			PotionEffectType.SLOW, PotionEffectType.SLOW_DIGGING, PotionEffectType.UNLUCK, PotionEffectType.WEAKNESS,
			PotionEffectType.WITHER };

	private static final PotionEffectType[] BENIFITFUL = new PotionEffectType[] { PotionEffectType.ABSORPTION,
			PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.FAST_DIGGING, PotionEffectType.FIRE_RESISTANCE,
			PotionEffectType.HEAL, PotionEffectType.HEALTH_BOOST, PotionEffectType.INCREASE_DAMAGE,
			PotionEffectType.JUMP, PotionEffectType.LUCK, PotionEffectType.NIGHT_VISION, PotionEffectType.REGENERATION,
			PotionEffectType.SATURATION, PotionEffectType.SPEED, PotionEffectType.WATER_BREATHING };

	private static final PotionEffectType[] NORMAL = new PotionEffectType[] { PotionEffectType.GLOWING,
			PotionEffectType.INVISIBILITY, PotionEffectType.LEVITATION, };

	public static PotionType getPotionType(PotionEffectType type) {
		for (PotionEffectType h : HARMFUL) {
			if (type.equals(h)) {
				return PotionType.HARMFUL;
			}
		}
		for (PotionEffectType b : BENIFITFUL) {
			if (type.equals(b)) {
				return PotionType.BENIFITFUL;
			}
		}
		for (PotionEffectType n : NORMAL) {
			if (type.equals(n)) {
				return PotionType.NORMAL;
			}
		}
		return PotionType.UNKNOWN;
	}

	enum PotionType {
		HARMFUL, BENIFITFUL, NORMAL, UNKNOWN
	}

	public static String getPotionTranslateName(PotionEffectType type) {
		switch (type.getName()) {
		case "SPEED":
			return "moveSpeed";
		case "SLOW":
			return "moveSlowdown";
		case "FAST_DIGGING":
			return "digSpeed";
		case "SLOW_DIGGING":
			return "digSlowdown";
		case "INCREASE_DAMAGE":
			return "damageBoost";
		case "HEAL":
			return "heal";
		case "HARM":
			return "harm";
		case "JUMP":
			return "jumpBoost";
		case "CONFUSION":
			return "confusion";
		case "REGENERATION":
			return "regeneration";
		case "DAMAGE_RESISTANCE":
			return "resistance";
		case "FIRE_RESISTANCE":
			return "fireResistance";
		case "WATER_BREATHING":
			return "waterBreathing";
		case "INVISIBILITY":
			return "invisibility";
		case "BLINDNESS":
			return "blindness";
		case "NIGHT_VISION":
			return "nightVision";
		case "HUNGER":
			return "hunger";
		case "WEAKNESS":
			return "weakness";
		case "POISON":
			return "poison";
		case "WITHER":
			return "wither";
		case "HEALTH_BOOST":
			return "healthBoost";
		case "ABSORPTION":
			return "absorption";
		case "SATURATION":
			return "saturation";
		case "GLOWING":
			return "glowing";
		case "LUCK":
			return "luck";
		case "UNLUCK":
			return "unluck";
		case "LEVITATION":
			return "levitation";
		default:
			return null;
		}
	}

	public static boolean hasSpecialNightVision(LivingEntity ety) {
		if (!ety.hasPotionEffect(PotionEffectType.NIGHT_VISION))
			return false;
		PotionEffect pe = ety.getPotionEffect(PotionEffectType.NIGHT_VISION);
		if (!pe.hasParticles() && !pe.isAmbient() && pe.getAmplifier() == 255) {
			return true;
		} else
			return false;
	}

}
