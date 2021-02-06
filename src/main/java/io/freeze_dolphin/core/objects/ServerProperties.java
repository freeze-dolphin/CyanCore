package io.freeze_dolphin.core.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import io.freeze_dolphin.core.Core;

@Deprecated
public class ServerProperties {

	private Properties prop;
	private final File file;

	public ServerProperties(String name, HashMap<String, Object> defaultProperties) throws IOException {

		if (name == null || "".equals(name)) {
			throw new IllegalStateException("The config must own a name!");
		}

		String suffix = "";
		if (!name.matches("(.*).properties")) {
			suffix = ".properties";
		}
		File file = new File(Core.cfgDir.getPath() + File.separator + name + suffix);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		Properties p = new Properties();
		if (!file.exists()) {
			file.createNewFile();
			p.load(new FileReader(file));
			for (String s : defaultProperties.keySet()) {
				p.put(s, defaultProperties.get(s));
			}
			p.store(new FileOutputStream(file), "Initialize default config");
		}
		this.file = file;
		prop = p;
	}

	public Properties getProperties() {
		return prop;
	}

	public void reloadProp() throws FileNotFoundException, IOException {
		this.prop.load(new FileReader(this.file));
	}

	public void saveProp(String comment) throws FileNotFoundException, IOException {
		this.prop.store(new FileOutputStream(this.file), comment);
	}

}
