package utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ConfigValidate {

    public enum kSupportedDB {
        SQLITE("sqlite"),
        MYSQL("mysql"),
        POSTGRESQL("postgresql"),
        MARIADB("mariadb");

        final String name;

        kSupportedDB(String name) {
            this.name = name;
        }
    }

    boolean validate_db_config(String filePath) {
        HashMap<String, Object> config;
        try {
            InputStream inputStream = new FileInputStream(filePath);
            Yaml yaml = new Yaml();
            config = yaml.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new AssertionError(e);
        }

        if (!config.containsKey("required") || !config.containsKey("db")) {
            System.err.println("You should set the `required` field and `db` for validity checks.");
        }
        if (!(config.get("required") instanceof ArrayList)) {
            System.err.println("The `required` field should be a list.");
        }
        if (config.containsKey("should_exist")) {
            if (!(config.get("should_exist") instanceof ArrayList)) {
                return false;
            }

            for (int i = 0; i < ((ArrayList<?>) config.get("should_exist")).size(); i++) {
                String key_should_exist = (String) ((ArrayList<?>) config.get("should_exist")).get(i);
                if (!config.containsKey(key_should_exist)) {
                    System.err.println(key_should_exist + " is set in `should_exist` but is not a valid key!");
                }
                String file_should_exist = (String) config.get(key_should_exist);
                if (!path_exist(file_should_exist)) {
                    System.err.println(file_should_exist + " doesn't exist!");
                    return false;
                }
            }
        }

        for (int i = 0; i < ((ArrayList<?>) config.get("required")).size(); i++) {
            String required_key =
                    (String) ((ArrayList<?>) config.get("required")).get(i);
            if (!config.containsKey(required_key)) {
                System.err.println("The required field %s is not set.\n" + required_key);
                return false;
            }
        }

        boolean supported = false;
        for (kSupportedDB db : kSupportedDB.values()) {
            if (config.get("db") == db.name) {
                supported = true;
                break;
            }
        }

        if (!supported) {
            System.err.println(config.get("db") + " is not supported by Squirrel yet.");
        }
        return supported;
    }

    boolean path_exist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static void main(String[] args) {
        new ConfigValidate().validate_db_config("src/main/resources/config_mysql.yml");
    }
}
