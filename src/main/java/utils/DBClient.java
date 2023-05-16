package utils;

public abstract class DBClient {
    public int database_id = 0;

    public String host;

    public String user_name;

    public String password;

    public int port;

    public String sock_path;
    public String db_prefix;

    public void initialize() {
    }


    public boolean check_alive() {
        return false;
    }

    public void prepare_env() {

    }

    public ExecutionStatus execute(String query, long size) {
        return ExecutionStatus.kNormal;
    }

    public void clean_up_env() {

    }


}
