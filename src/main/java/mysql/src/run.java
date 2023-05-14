package mysql.src;

import mysql.src.ast.node.astNode;

public class run {
    public static void main(String[] args) {
        astNode.Program a = new astNode.Program();
        a.setSubType(2);
        System.out.println(a.toString());
    }
}
