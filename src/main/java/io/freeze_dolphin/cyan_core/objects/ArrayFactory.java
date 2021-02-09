package io.freeze_dolphin.cyan_core.objects;

import java.util.ArrayList;
import java.util.List;

public class ArrayFactory<T> {

	public ArrayFactory() {
	}

	@SuppressWarnings("unchecked")
	public List<T> produce(T... obj) {
		List<T> al = new ArrayList<>();
		for (T t : obj) {
			al.add(t);
		}
		return al;
	}

}
