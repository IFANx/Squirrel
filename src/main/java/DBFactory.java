import mysql.src.MySQL;
import utils.DataBase;

import java.util.HashMap;

public class DBFactory {
    public static DataBase create_database(HashMap<String, Object> config) {
        String db_name = (String) config.get("db");
        DataBase result = null;

        if (db_name.equals("sqlite")) {
//            result = create_sqlite();
        } else if (db_name.equals("mysql")) {
            result = MySQL.create_mysql();
        } else if (db_name.equals("postgresql")) {
//            result = create_postgresql();
        } else {
            throw new AssertionError("Unreachable");
        }
        assert result != null;
        result.initialize();
        return result;
    }
}
