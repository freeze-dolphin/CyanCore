package io.freeze_dolphin.cyan_core.utils;

import java.text.NumberFormat;
import java.util.Random;

public class MathUtils {

	public static int getRandom(int lowest, int highest) {
		return new Random().nextInt(highest - lowest + 1) + lowest;
	}

	public static String getPercentFormat(double d, int IntegerDigits, int FractionDigits) {
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMaximumIntegerDigits(IntegerDigits);
		nf.setMinimumFractionDigits(FractionDigits);
		String str = nf.format(d);
		return str;
	}

	public static int RomanToArabic(String m) {
		int graph[] = new int[400];
		graph['I'] = 1;
		graph['V'] = 5;
		graph['X'] = 10;
		graph['L'] = 50;
		graph['C'] = 100;
		graph['D'] = 500;
		graph['M'] = 1000;
		char[] n = m.toCharArray();
		int sum = graph[n[0]];
		for (int i = 0; i < n.length - 1; i++) {
			if (graph[n[i]] >= graph[n[i + 1]]) {
				sum += graph[n[i + 1]];
			} else {
				sum = sum + graph[n[i + 1]] - 2 * graph[n[i]];
			}
		}
		return sum;
	}

	public static String ArabicToRoman(int nber) {
		String rnber = "";
		int[] aArray = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] rArray = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		if (nber < 1 || nber > 3999) {
			rnber = "-1";
		} else {
			for (int i = 0; i < aArray.length; i++) {
				while (nber >= aArray[i]) {
					rnber += rArray[i];
					nber -= aArray[i];
				}
			}
		}
		return rnber;
	}

	public static int[] DicimalToFraction(double n) {
		int count = 0;
		int base = 10;
		while (n != Math.floor(n)) {
			n *= base;
			count++;
		}
		base = (int) Math.pow(base, count);
		int nor = (int) n;
		int gcd = getGCD(nor, base);
		return new int[] { (nor / gcd), (base / gcd) };
	}

	public static int getGCD(int a, int b) {
		if (a == 0) {
			return b;
		}
		return getGCD(b % a, a);
	}

	public static int getFirstDigit(int integer) {
		int m = 1;
		for (int i = 0; i < (int) Math.log10(integer); i++) {
			m *= 10;
		}
		return (integer / m);
	}

}
