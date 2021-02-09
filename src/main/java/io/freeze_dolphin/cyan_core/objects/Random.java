package io.freeze_dolphin.cyan_core.objects;

import io.freeze_dolphin.cyan_core.utils.MathUtils;

public class Random {

	private int lowest, highest;

	public Random(int lowest, int highest) {
		this.setLowest(lowest);
		this.setHighest(highest);
	}

	public int getLowest() {
		return lowest;
	}

	public void setLowest(int lowest) {
		this.lowest = lowest;
	}

	public int getHighest() {
		return highest;
	}

	public void setHighest(int highest) {
		this.highest = highest;
	}

	public int next() {
		if (lowest == highest)
			return lowest;
		return MathUtils.getRandom(lowest, highest);
	}

}
