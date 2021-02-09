package io.freeze_dolphin.cyan_core.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.ChatColor;

import io.freeze_dolphin.cyan_core.interfaces.CommandRunnable.NormalRunnable;

public class LangUtils {

	public static String colorize(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}

	public static int getLength(String str) {
		return str.length();
	}

	public static String merge(String... str) {
		StringBuilder bder = new StringBuilder();
		for (String s : str) {
			bder.append(s);
		}
		return bder.toString();
	}

	public static String[] arrayMerge(String[] array1, String[] array2) {
		String[] array12 = new String[array1.length + array2.length];
		System.arraycopy(array1, 0, array12, 0, array1.length);
		System.arraycopy(array2, 0, array12, array1.length, array2.length);
		return array12;
	}

	public static String[] arrayAdd(String array1, String[] array2) {
		String[] array22 = new String[array2.length + 1];
		System.arraycopy(array2, 0, array22, 0, array2.length);
		array22[array2.length] = array1;
		return array22;
	}

	public static StringBuilder deleteLastChar(StringBuilder sb) {
		return sb.deleteCharAt(sb.length() - 1);
	}

	/**
	 * Divide String list
	 * 
	 * @param dividend The bigger String Array
	 * @param divisor  The small String Array
	 * @return A copy of 'dividend' array which does not contain all the item in the
	 *         'divisor'
	 */
	public static String[] divideBy(List<String> dividend, List<String> divisor) {

		List<String> l = new LinkedList<>();
		int index = 0;
		for (String s : dividend) {
			if (index > divisor.size() - 1) {
				l.add(s);
			}
			index++;
		}

		return l.toArray(new String[l.size()]);
	}

	/**
	 * The same as {@link StringUtils.divideBy(List<String>, List<String>)}
	 */
	public static String[] divideBy(String[] dividend, String[] divisor) {

		List<String> l = new LinkedList<>();
		int index = 0;
		for (String s : dividend) {
			if (index > divisor.length - 1) {
				l.add(s);
			}
			index++;
		}

		return l.toArray(new String[l.size()]);
	}

	public static String arrayToString(String[] array) {

		if (array.length == 0)
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (String s : array) {
			sb.append(s).append(", ");
		}
		deleteLastChar(sb);
		deleteLastChar(sb);
		sb.append("]");
		return sb.toString();
	}

	public static String mapToString(Map<String, Pair<String[], NormalRunnable>> storage) {

		if (storage.isEmpty())
			return "{}";
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (String s : storage.keySet()) {
			sb.append(s).append(", ").append(arrayToString(storage.get(s).getLeft())).append(", ")
					.append(storage.get(s).getRight().toString()).append(", ");
		}
		deleteLastChar(sb);
		deleteLastChar(sb);
		sb.append("}");
		return sb.toString();
	}

}
