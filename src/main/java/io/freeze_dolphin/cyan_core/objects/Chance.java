package io.freeze_dolphin.cyan_core.objects;

import io.freeze_dolphin.cyan_core.utils.MathUtils;

public class Chance {

	private int chance, total;

	public Chance(int chance, int total) {
		this.chance = chance;
		this.total = total;
	}

	public Chance(double chance) {
		int[] i = MathUtils.DicimalToFraction(chance);
		this.chance = i[0];
		this.total = i[1];
	}

	public int getChance() {
		return this.chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setDicimalChance(double dicimal) {
		int[] i = MathUtils.DicimalToFraction(chance);
		setChance(i[0]);
		setTotal(i[1]);
	}

	public boolean next() {
		return MathUtils.getRandom(1, total) <= chance;
	}

}
