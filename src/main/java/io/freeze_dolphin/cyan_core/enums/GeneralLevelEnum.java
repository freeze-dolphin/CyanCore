package io.freeze_dolphin.cyan_core.enums;

public enum GeneralLevelEnum {

	LOW("低", null, null), MEDIUM("中", "中级", null), HIGH("高", null, null), EXTREMELY_HIGH("极高", null, null),

	@Deprecated
	COMMON(null, null, "普通"), 
	@Deprecated
	RARE(null, null, "稀有"), 
	@Deprecated
	EPIC(null, null, "史诗"), 
	@Deprecated
	LEGENDARY(null, null, "传奇");

	private final String degree, level, rarity;

	private GeneralLevelEnum(String degree, String level, String rarity) {
		this.degree = degree;
		this.level = level;
		this.rarity = rarity;
	}

	public String getDegree() {
		return degree;
	}

	public String getLevel() {
		return level;
	}

	public String getRarity() {
		return rarity;
	}

}
