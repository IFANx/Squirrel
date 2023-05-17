package utils;


public abstract class DataBase {

    public boolean initialize() {
        return true;
    }

    public int mutate(String s) {
        return 0;
    }

    public String get_next_mutated_query() {
        return null;
    }

    public boolean has_mutated_test_cases() {
        return true;
    }

    public boolean save_interesting_query(String query) {
        return true;
    }

    public boolean clean_up() {
        return true;
    }

    private DataBase create_database() {
        return null;
    }
}
