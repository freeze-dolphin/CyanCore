package io.freeze_dolphin.core.enums;

@Deprecated
public enum ArmorAttributes {

	ANTI_RADIATION("抗辐射"), ANTI_ZOMBIE("抗尸变"), ANTI_WITHER("抗凋零"),
	HIGH_HEAT_RESISTANCE("抗高温"), CORROSION_RESISTANCE("抗腐蚀"), DEFORMATION_RESISTANCE("抗形变");

	private final String name;

	private ArmorAttributes(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
