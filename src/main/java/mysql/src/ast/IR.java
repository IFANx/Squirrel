package mysql.src.ast;

import static mysql.src.Utils.trimString;
import static mysql.src.ast.DataType.kDataWhatever;
import static mysql.src.ast.ast.gen_id_name;

public class IR {
    public int scope;
    public DataFlag data_flag;

    public DataType dataType;

    public IRType type;

    public String name;

    public IROperator op;

    public IR left;

    public IR right;

    public int operand_num;

    public int mutated_times;

    public int intVal;

    public long longVal;

    public float floatVal;

    public boolean boolVal;
    public String strVal;

    public IR() {
    }

    public IR(IRType type, String s) {
        this.type = type;
        this.strVal = s;
    }

    public IR(IRType type, IROperator irOperator, IR tmp1) {
        this.type = type;
        this.op = irOperator;
        this.left = tmp1;
    }

    //start
    public IR(IRType type, IROperator irOperator, IR left, IR right) {//done
        this.type = type;
        this.op = irOperator;
        this.left = left;
        this.right = right;
        this.dataType = kDataWhatever;
        this.name = gen_id_name();
    }

    public IR(IRType type, String str_val, DataType data_type, int scope, DataFlag flag) {
        this.type = type;
        this.strVal = str_val;
        this.op = null;
        this.left = null;
        this.right = null;
        this.operand_num = 0;
        this.dataType = kDataWhatever;
        this.scope = -1;
        this.data_flag = DataFlag.kUse;
        this.name = gen_id_name();
    }

    public IR(IRType type, Boolean b_val, DataType data_type, int scope, DataFlag flag) {
        this.type = type;
        this.boolVal = b_val;
        this.left = null;
        this.op = null;
        this.right = null;
        this.operand_num = 0;
        this.dataType = kDataWhatever;
        this.scope = -1;
        this.data_flag = DataFlag.kUse;
        this.name = gen_id_name();
    }

    public IR(IRType type, long long_val, DataType data_type, int scope, DataFlag flag) {
        this.type = type;
        this.longVal = long_val;
        this.left = null;
        this.op = null;
        this.right = null;
        this.operand_num = 0;
        this.dataType = kDataWhatever;
        this.scope = -1;
        this.data_flag = DataFlag.kUse;
        this.name = gen_id_name();
    }

    public IR(IRType type, int int_val, DataType data_type, int scope, DataFlag flag) {
        this.type = type;
        this.intVal = int_val;
        this.left = null;
        this.op = null;
        this.right = null;
        this.operand_num = 0;
        this.dataType = kDataWhatever;
        this.scope = -1;
        this.data_flag = DataFlag.kUse;
        this.name = gen_id_name();
    }

    public IR(IRType type, float f_val, DataType data_type, int scope, DataFlag flag) {
        this.type = type;
        this.floatVal = f_val;
        this.left = null;
        this.op = null;
        this.right = null;
        this.operand_num = 0;
        this.dataType = kDataWhatever;
        this.scope = -1;
        this.data_flag = DataFlag.kUse;
        this.name = gen_id_name();
    }

    public IR(IRType type, IROperator op, IR left, IR right, float floatVal, String str_val, String name, int mutated_times, int scope, DataFlag flag) {
        this.type = type;
        this.left = left;
        this.op = op;
        this.right = right;
        this.operand_num = (right == null || left == null) ? 0 : 1;
        this.name = name;
        this.strVal = str_val;
        this.floatVal = floatVal;
        this.mutated_times = mutated_times;
        this.dataType = kDataWhatever;
        this.scope = scope;
        this.data_flag = flag;
    }

    public IR(IR ir, IR left, IR right) {
        this.type = ir.type;
        if (ir.op != null)
            this.op = new IROperator(ir.op.prefix, ir.op.middle, ir.op.suffix);
        else {
            this.op = new IROperator();
        }
        this.left = left;
        this.right = right;
        this.strVal = ir.strVal;
        this.longVal = ir.longVal;
        this.dataType = ir.dataType;
        this.scope = ir.scope;
        this.data_flag = ir.data_flag;
        this.name = ir.name;
        this.operand_num = ir.operand_num;
        this.mutated_times = ir.mutated_times;
    }


    @Override
    public String toString() {
        String res = toStringCore();
        res = trimString(res.toCharArray());
        return res;
    }

    public String toStringCore() {
        switch (type) {
            case kIntLiteral:
                return String.valueOf(intVal);
            case kFloatLiteral:
                return String.valueOf(floatVal);
            case kIdentifier:
                return strVal;
            case kStringLiteral:
                return strVal;
        }
        StringBuilder res = new StringBuilder();
        if (this.op != null) {
            res.append(this.op.prefix).append(" ");
        }
        if (this.left != null) {
            res.append(this.left.toStringCore());
        }
        if (this.right != null) {
            res.append(this.right.toStringCore());
        }
        if (this.op != null) {
            res.append(this.op.suffix);
        }
        return res.toString();
    }


}
