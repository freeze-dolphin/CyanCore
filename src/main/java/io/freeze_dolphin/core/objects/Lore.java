package io.freeze_dolphin.core.objects;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import io.freeze_dolphin.core.utils.LangUtils;

public class Lore extends LinkedList<String> {

	private static final long serialVersionUID = 6764125867114224771L;

	public Lore(String... lore) {
		super(Arrays.asList(lore));
	}

	public Lore(List<String> lore) {
		super(lore);
	}

	public void addLine(String lore) {
		this.add(LangUtils.colorize(lore));
	}

	public void setLine(int index, String lore) {
		this.set(index, LangUtils.colorize(lore));
	}

}
