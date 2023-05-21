import mysql.src.ast.node.astNode;

import static mysql.src.Utils.parser;

public class Run {
    public static void main(String[] args) {
//        astNode.Program parser = parser("");
        astNode.Program program = new astNode.Program();
        program.generate();
        System.out.println(program.caseIdx);
    }
}
