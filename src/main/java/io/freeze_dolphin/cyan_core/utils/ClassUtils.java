package io.freeze_dolphin.cyan_core.utils;

import java.io.InputStream;

public class ClassUtils {

	public static InputStream getResource(Class<?> clazz, String path) {
		return clazz.getClassLoader().getResourceAsStream(path);
	}

}
