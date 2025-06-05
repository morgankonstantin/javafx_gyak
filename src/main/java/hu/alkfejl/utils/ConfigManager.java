package hu.alkfejl.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties props = new Properties();

    static {
        try {
            props.load(ConfigManager.class.getResourceAsStream("/application/properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }
}
