package mysql;

import org.yaml.snakeyaml.Yaml;
import utils.DBClient;
import utils.ExecutionStatus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class MySQLClient extends DBClient {
    @Override
    public void initialize() {
        HashMap<String, Object> config;
        try {
            config = loadConfig();
        } catch (FileNotFoundException e) {
            throw new AssertionError(e);
        }
        this.host = (String) config.get("host");
        this.user_name = (String) config.get("user_name");
        this.password = (String) config.get("password");
        this.port = (Integer) config.get("port");
        this.sock_path = (String) config.get("sock_path");
        this.db_prefix = (String) config.get("db_prefix");
    }

    @Override
    public boolean check_alive() {
        try {
            Connection connection = create_connection("");
            if (connection == null || connection.isClosed()) {
                return false;
            }
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public void prepare_env() {
        this.database_id++;
        String databaseName = this.db_prefix + this.database_id;
        try {
            if (!create_database(databaseName)) {
                System.err.println("Failed to create database.");
            }
        } catch (SQLException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public ExecutionStatus execute(String query, long size) {
        String dataBaseName = this.db_prefix + this.database_id;
        Connection con = create_connection(dataBaseName);
        if (con == null) {
            System.err.println("Cannot create connection at execute ");
            return ExecutionStatus.kServerCrash;
        }

        try {
            Statement s = con.createStatement();
            boolean server_response = s.execute(query);
            if (!server_response) {
                System.err.println("Cannot mySQL_QUERY ");
                return ExecutionStatus.kServerCrash;
            }
        } catch (SQLException e) {
            throw new AssertionError(e);
        }
        ExecutionStatus server_status;
        try {
            server_status = clean_up_connection(con);
            con.close();
        } catch (SQLException e) {
            throw new AssertionError(e);
        }
        return server_status;
    }

    @Override
    public void clean_up_env() {
        String dataBaseName = this.db_prefix + this.database_id;

        String reset_query = "DROP DATABASE IF EXISTS " + dataBaseName + ";";

        Connection connection = create_connection(dataBaseName);
        try {
            final var statement = connection.createStatement();
            statement.execute(reset_query);
            connection.close();
        } catch (SQLException e) {
            throw new AssertionError(e);
        }


    }

    public static HashMap<String, Object> loadConfig() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("src/main/resources/config_mysql.yml");
        Yaml yaml = new Yaml();
        return yaml.load(inputStream);
    }

    private boolean create_database(String databaseName) throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
                this.host, this.port);
        Connection con = DriverManager.getConnection(url, this.user_name, password);
        try {
            Statement s = con.createStatement();
            s.execute("DROP DATABASE IF EXISTS " + databaseName);
            s.execute("CREATE DATABASE " + databaseName);
            s.execute("USE " + databaseName);
            con.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    private Connection create_connection(String databaseName) {
        String url = String.format("jdbc:mysql://%s:%d?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
                this.host, this.port);
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, this.user_name, password);
            Statement statement = con.createStatement();
            databaseName = databaseName.equals("") ? "test" : databaseName;
            statement.execute("USE " + databaseName);
        } catch (SQLException e) {
            System.err.println("Create mysql connection failed.");
        }
        return con;
    }

    //TODO : 这里可能返回crash，syntax error以及 semantic error和normal，不明白具体实现
    private ExecutionStatus clean_up_connection(Connection connection) {
        ExecutionStatus res = ExecutionStatus.kNormal;
        return res;

    }
}
