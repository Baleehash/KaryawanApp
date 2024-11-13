package config;

import java.io.InputStream;
import java.util.Properties;

@SuppressWarnings("CallToPrintStackTrace")
public class ConfigLoader {
    public static Properties loadConfig() {
        Properties properties = new Properties();

        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("File config.properties tidak ditemukan di resources.");
                return null;
            }
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }
}

