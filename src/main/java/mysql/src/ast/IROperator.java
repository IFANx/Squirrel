package mysql.src.ast;

public class IROperator {
    public String prefix;
    public String middle;
    public String suffix;

    public IROperator(String prefix, String middle, String suffix) {
        this.prefix = prefix;
        this.middle = middle;
        this.suffix = suffix;
    }

    public IROperator() {

    }
}
