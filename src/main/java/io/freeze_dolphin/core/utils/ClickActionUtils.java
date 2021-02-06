package io.freeze_dolphin.core.utils;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;

public class ClickActionUtils {

	public static boolean isLeftClickWithoutShift(ClickAction action) {
		return (!action.isRightClicked() && !action.isShiftClicked());
	}

	public static boolean isRightClickWithoutShift(ClickAction action) {
		return (action.isRightClicked() && !action.isShiftClicked());
	}

	public static boolean isLeftClickWithShift(ClickAction action) {
		return (!action.isRightClicked() && action.isShiftClicked());
	}

	public static boolean isRightClickWithShift(ClickAction action) {
		return (action.isRightClicked() && action.isShiftClicked());
	}

}
