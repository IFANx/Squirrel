package mysql.src.ast.node;

import mysql.src.ast.DataFlag;
import mysql.src.ast.DataType;
import mysql.src.ast.IR;
import mysql.src.ast.NodeType;

import java.util.List;

public abstract class Node {
    public void setSubType(int i) {
        caseIdx = i;
    }

    NodeType type;
    DataType dataType;
    DataFlag dataFlag;
    int scope;
    public int caseIdx;

    public abstract IR translate(List<IR> vIrCollector);

    public abstract void generate();

    public abstract void deepDelete();

    public Node() {
    }
}
