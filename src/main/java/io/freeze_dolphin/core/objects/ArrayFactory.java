package io.freeze_dolphin.core.objects;

import java.util.ArrayList;
import java.util.List;

public class ArrayFactory<T> {

	public ArrayFactory() {
	}

	public List<T> produce(@SuppressWarnings("unchecked") T... obj) {
		List<T> al = new ArrayList<>();
		for (T t : obj) {
			al.add(t);
		}
		return al;
	}

}
