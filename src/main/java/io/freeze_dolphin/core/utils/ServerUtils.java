package io.freeze_dolphin.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ServerUtils {

	public static Properties getServerProperties() throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader(new File("server.properties")));
		return p;
	}

}
