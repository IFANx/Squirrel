package mysql.src.mutate;


import mysql.src.ast.*;
import mysql.src.ast.node.Node;
import mysql.src.ast.node.astNode;

import java.io.*;
import java.util.*;

import static mysql.src.Utils.*;
import static mysql.src.ast.DataFlag.*;
import static mysql.src.ast.DataType.*;
import static mysql.src.ast.IRType.*;
import static mysql.src.ast.IRType.kStmtlist;
import static mysql.src.ast.ast.*;


public class Mutate extends Mutator {
    private static final boolean GRAPHLOG = false;
    static int counter = 0;

    @Override
    public IR deepCopyWithRecord(IR root, IR record) {
        IR left = null;
        IR right = null;
        IR copyRes;

        if (root.left != null) {
            left = deepCopyWithRecord(root.left, record);
        }
        if (root.right != null) {
            right = deepCopyWithRecord(root.right, record);
        }
        if (root.op != null) {
            copyRes = new IR(root.type, new IROperator(root.op.prefix, root.op.middle, root.op.suffix), left, right,
                    root.floatVal, root.strVal, root.name, root.mutated_times, root.scope, root.data_flag);
        } else {
            copyRes = new IR(root.type, null, left, right, root.floatVal, root.strVal, root.name, root.mutated_times, root.scope, root.data_flag);
        }

        copyRes.dataType = root.dataType;
        if (root == record && record != null) {
            this.record_ = copyRes;
        }
        return copyRes;
    }

    @Override
    public long hash(IR root) {
        String tmp_str = root.toString();
        return this.hash(tmp_str);
    }

    @Override
    public long hash(String sql) {
        return DuckingHash(sql, sql.length());
    }

    @Override
    public IR irRandomGenerator(List<IR> vIrCollector) {
        return null;
    }

    @Override
    public List<IR> mutateAll(List<IR> vIrCollector) {
        List<IR> res = new ArrayList<>();
        IR root = vIrCollector.get(vIrCollector.size() - 1);
        this.mutated_root_ = root;

        for (IR ir : vIrCollector) {
            if (not_mutatable_types_.contains(ir.type)) {
                continue;
            }
            List<IR> v_mutates_ir = mutate(ir);
            for (IR i : v_mutates_ir) {
                IR new_ir_tree = deepCopyWithRecord(root, ir);
                replace(new_ir_tree, this.record_, i);

                extract_struct(new_ir_tree);

                String tmp = new_ir_tree.toString();

                long tmp_hash = hash(tmp);
                if (this.global_hash_.contains(tmp_hash)) {
                    //deep delete 是c++的释放内存操作
                    deepDelete(new_ir_tree);
                }
                this.global_hash_.add(tmp_hash);
                res.add(new_ir_tree);
            }
        }
        return res;
    }

    private void deepDelete(IR newIrTree) {
    }

    @Override
    public List<IR> mutate(IR input) {
        List<IR> res = new ArrayList<>();

        if (!luckyEnoughToBeMutated(input.mutated_times)) {
            return res;
        }

        IR tmp = strategyDelete(input);
        if (tmp != null) {
            res.add(tmp);
        }

        tmp = strategyInsert(input);
        if (tmp != null) {
            res.add(tmp);
        }

        tmp = strategyReplace(input);
        if (tmp != null) {
            res.add(tmp);
        }
        input.mutated_times += res.size();

        for (IR i : res) {
            i.mutated_times = input.mutated_times;
        }
        return res;
    }

    @Override
    public IR strategyDelete(IR cur) {
        assert cur != null;
        IR res = null;
        int randint = random.nextInt() % 3;
        switch (randint) {
            case 0:
                res = deep_copy(cur);
                if (res.left != null) {
                    deepDelete(res.left);
                }
                res.left = null;
                break;
            case 1:
                res = deep_copy(cur);
                if (res.right != null) {
                    deepDelete(res.right);
                }
                res.right = null;
                break;
            case 2:
                res = deep_copy(cur);
                if (res.left != null) {
                    deepDelete(res.left);
                }
                if (res.right != null) {
                    deepDelete(res.right);
                }
                res.left = null;
                res.right = null;
                break;
        }
        return res;
    }

    @Override
    public IR strategyInsert(IR cur) {
        assert cur != null;

        IR res = deep_copy(cur);
        IRType parent_type = cur.type;

        if (res.right == null && res.left != null) {
            IRType lefttype = res.left.type;
            for (int i = 0; i < 4; i++) {
                IR fetch_ir = get_ir_from_library(parent_type);
                if (fetch_ir.left != null && fetch_ir.left.type == lefttype && fetch_ir.right != null) {
                    res.right = deep_copy(fetch_ir.right);
                    return res;
                }
            }
        } else if (res.right != null && res.left == null) {
            IRType righttype = res.right.type;
            for (int k = 0; k < 4; k++) {
                IR fetch_ir = get_ir_from_library(parent_type);
                if (fetch_ir.right != null && fetch_ir.right.type == righttype && fetch_ir.left != null) {
                    res.left = deep_copy(fetch_ir.left);
                    return res;
                }
            }
        } else if (res.left == null && res.right == null) {
            for (int i = 0; i < 4; i++) {
                IR fetch_ir = get_ir_from_library(parent_type);
                if (fetch_ir.right != null && fetch_ir.left != null) {
                    res.left = deep_copy(fetch_ir.left);
                    res.right = deep_copy(fetch_ir.right);
                    return res;
                }
            }
        }

        return res;
    }

    @Override
    public IR strategyReplace(IR cur) {
        assert cur != null;
        IR res = null;
        int randint = random.nextInt() % (3);
        switch (randint) {
            case 0:
                if (cur.left != null) {
                    res = deep_copy(cur);
                    IR new_node = get_ir_from_library(res.left.type);
                    new_node.dataType = res.left.dataType;
                    deepDelete(res.left);
                    res.left = deep_copy(new_node);
                }
                break;
            case 1:
                if (cur.right != null) {
                    res = deep_copy(cur);

                    IR new_node = get_ir_from_library(res.right.type);
                    new_node.dataType = res.right.dataType;
                    deepDelete(res.right);
                    res.right = deep_copy(new_node);
                }
                break;
            case 2:
                if (cur.left != null && cur.right != null) {
                    res = deep_copy(cur);

                    IR new_left = get_ir_from_library(res.left.type);
                    IR new_right = get_ir_from_library(res.right.type);
                    new_left.dataType = res.left.dataType;
                    new_right.dataType = res.right.dataType;
                    deepDelete(res.right);
                    res.right = deep_copy(new_right);

                    deepDelete(res.left);
                    res.left = deep_copy(new_left);
                }
                break;
        }
        return res;
    }

    @Override
    public boolean luckyEnoughToBeMutated(int mutatedTimes) {
        if (random.nextInt(mutatedTimes + 1) < LUCKY_NUMBER) {
            return true;
        }
        return false;
    }

    @Override
    public boolean replace(IR root, IR oldIr, IR newIr) {
        IR parent_ir = locateParent(root, oldIr);
        if (parent_ir == null) {
            return false;
        }
        if (parent_ir.left == oldIr) {
            deepDelete(oldIr);
            parent_ir.left = newIr;
            return true;
        } else if (parent_ir.right == oldIr) {
            deepDelete(oldIr);
            parent_ir.right = newIr;
            return true;
        }
        return false;
    }

    @Override
    public IR locateParent(IR root, IR oldIr) {
        if (root.left == oldIr || root.right == oldIr) {
            return root;
        }
        if (root.left != null) {
            locateParent(root.left, oldIr);
        }
        if (root.right != null) {
            locateParent(root.right, oldIr);
        }
        return null;
    }

    @Override
    public void init(String fTestcase, String fCommonString, String file2d, String file1d, String fGenType) {
        if (!fTestcase.isEmpty()) initIrLibrary(fTestcase);

        //init value_library_
        initValueLibrary();

        // init common_string_library
        if (!fCommonString.isEmpty()) initCommonString(fCommonString);

        // init data_library_2d
        if (!file2d.isEmpty()) initDataLibrary2d(file2d);

        if (!file1d.isEmpty()) initDataLibrary(file1d);
        if (!fGenType.isEmpty()) initSafeGenerateType(fGenType);

        this.float_types_.add(IRType.kFloatLiteral);
        this.int_types_.add(IRType.kIntLiteral);
        this.string_types_.add(IRType.kStringLiteral);


        //  relationmap_[kDataColumnName][kDataTableName] = kRelationSubtype;
        //  relationmap_[kDataPragmaValue][kDataPragmaKey] = kRelationSubtype;
        //  relationmap_[kDataTableName][kDataTableName] = kRelationElement;
        //  relationmap_[kDataColumnName][kDataColumnName] = kRelationElement;
        HashMap<DataType, RelationType> map = new HashMap<>();
        map.put(kDataColumnName, RelationType.kRelationSubtype);
        this.relationmap_.put(kDataColumnName, map);

        HashMap<DataType, RelationType> map2 = new HashMap<>();
        map2.put(kDataPragmaKey, RelationType.kRelationSubtype);
        this.relationmap_.put(kDataPragmaValue, map2);

        HashMap<DataType, RelationType> map3 = new HashMap<>();
        map3.put(kDataTableName, RelationType.kRelationElement);
        this.relationmap_.put(kDataTableName, map3);

        HashMap<DataType, RelationType> map4 = new HashMap<>();
        map4.put(kDataColumnName, RelationType.kRelationElement);
        this.relationmap_.put(kDataColumnName, map4);

        this.split_stmt_types_.add(IRType.kStmt);
        this.split_substmt_types_.add(IRType.kStmt);
        this.split_substmt_types_.add(IRType.kSelectClause);
        this.split_substmt_types_.add(IRType.kSelectStmt);

        //TODO need add
//        #define MYSQLFUZZ
//#ifdef MYSQLFUZZ
//  not_mutatable_types_.insert(
//      {kProgram, kStmtlist, kStmt, kCreateStmt, kDropStmt, kCreateTableStmt,
//       kCreateIndexStmt, kCreateTriggerStmt, kCreateViewStmt, kDropIndexStmt,
//       kDropTableStmt, kDropTriggerStmt, kDropViewStmt, kSelectStmt,
//       kUpdateStmt, kInsertStmt, kAlterStmt});
//#else
//  not_mutatable_types_.insert({kProgram, kStmtlist, kStmt, kCreateStmt,
//                               kDropStmt, kCreateTableStmt, kCreateIndexStmt,
//                               kCreateViewStmt, kDropIndexStmt, kDropTableStmt,
//                               kDropViewStmt, kSelectStmt, kUpdateStmt,
//                               kInsertStmt, kAlterStmt, kReindexStmt});
//#endif
        //TODO:add option
        boolean MySQLFUZZ = true;
        if (MySQLFUZZ == true) {
            this.not_mutatable_types_.add(kProgram);
            this.not_mutatable_types_.add(kStmtlist);
            this.not_mutatable_types_.add(kStmt);
            this.not_mutatable_types_.add(kCreateStmt);
            this.not_mutatable_types_.add(kDropStmt);
            this.not_mutatable_types_.add(kCreateTableStmt);
            this.not_mutatable_types_.add(kCreateIndexStmt);
            this.not_mutatable_types_.add(kCreateTriggerStmt);
            this.not_mutatable_types_.add(kCreateViewStmt);
            this.not_mutatable_types_.add(kDropIndexStmt);
            this.not_mutatable_types_.add(kDropTableStmt);
            this.not_mutatable_types_.add(kDropTriggerStmt);
            this.not_mutatable_types_.add(kDropViewStmt);
            this.not_mutatable_types_.add(kSelectStmt);
            this.not_mutatable_types_.add(kUpdateStmt);
            this.not_mutatable_types_.add(kInsertStmt);
            this.not_mutatable_types_.add(kAlterStmt);
        } else {
            this.not_mutatable_types_.add(kProgram);
            this.not_mutatable_types_.add(kStmtlist);
            this.not_mutatable_types_.add(kStmt);
            this.not_mutatable_types_.add(kCreateStmt);
            this.not_mutatable_types_.add(kDropStmt);
            this.not_mutatable_types_.add(kCreateTableStmt);
            this.not_mutatable_types_.add(kCreateIndexStmt);
            this.not_mutatable_types_.add(kCreateViewStmt);
            this.not_mutatable_types_.add(kDropIndexStmt);
            this.not_mutatable_types_.add(kDropTableStmt);
            this.not_mutatable_types_.add(kDropViewStmt);
            this.not_mutatable_types_.add(kSelectStmt);
            this.not_mutatable_types_.add(kUpdateStmt);
            this.not_mutatable_types_.add(kInsertStmt);
            this.not_mutatable_types_.add(kAlterStmt);
        }
    }

    @Override
    public void initIrLibrary(String filename) {
        System.out.println(String.format("[*] init ir_library: %s", filename));
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                if (line.isEmpty()) {
                    continue;
                }
                astNode.Program p = parser(line);
                if (p == null) {
                    continue;
                }
                List<IR> v_ir = new ArrayList<>();
                IR res = p.translate(v_ir);
                p.deepDelete();
                p = null;

                addIrToLibrary(res);
                deepDelete(res);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initValueLibrary() {
        List<Long> value_lib_init = new ArrayList<>();
        value_lib_init.add(9223372036854775807L);
        value_lib_init.add(9223372036854775807L * 2 + 1);
        value_lib_init.add(8L);
        value_lib_init.add(-127 - 1L);
        value_lib_init.add(127L);
        value_lib_init.add(127 * 2 + 1L);
        value_lib_init.add(6L);
        value_lib_init.add(-2147483647 - 1L);
        value_lib_init.add(2147483647L);
        value_lib_init.add(2147483647 * 2 + 1L);
        value_lib_init.add((long) 3.40282347e+38F);
        value_lib_init.add((long) 1.7976931348623157e+308);
        value_lib_init.add((long) 1.17549435e-38F);
        value_lib_init.add((long) 2.2250738585072014e-308);
        value_lib_init.add((long) 2.2250738585072014e-308);
    }

    @Override
    public void initCommonString(String filename) {
        this.common_string_library_.add("DO_NOT_BE_EMPTY");
        if (!Objects.equals(filename, "")) {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(filename));
                String line = reader.readLine();
                while (line != null) {
                    this.common_string_library_.add(line);
                    // read next line
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initDataLibrary(String filename) {
        System.out.println(String.format("[*] init data_library: %s", filename));
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                int pos = line.indexOf(" ");
                //    if (pos == string::npos) continue;
                if (pos == 0) {
                    continue;
                }
                DataType dataType = get_datatype_by_string(line.substring(0, pos));
                String v = line.substring(pos + 1, line.length() - pos - 1);
                final var strings = this.g_data_library_.get(dataType);
                strings.add(v);

                this.g_data_library_.put(dataType, strings);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initDataLibrary2d(String filename) {
        System.out.println(String.format("[*] init data_library_2d: %s", filename));
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                List<String> v_strbuf = new ArrayList<>();
                int prev_pos = -1;
                for (int i = 0; i < 3; i++) {
                    int pos = line.indexOf(" ", prev_pos + 1);
                    v_strbuf.add(line.substring(prev_pos + 1, pos - prev_pos - 1));
                    prev_pos = pos;
                }
                v_strbuf.add(line.substring(prev_pos + 1, line.length() - prev_pos - 1));


                DataType data_type1 = get_datatype_by_string(v_strbuf.get(0));
                DataType data_type2 = get_datatype_by_string(v_strbuf.get(2));

                //g_data_library_2d_[data_type1][v_strbuf[1]][data_type2].push_back(v_strbuf[3]);
                List<String> strings = this.g_data_library_2d_.get(data_type1).get(v_strbuf.get(1)).get(data_type2);
                strings.add(v_strbuf.get(3));
                final var dataTypeListHashMap = this.g_data_library_2d_.get(data_type1).get(v_strbuf.get(1));
                dataTypeListHashMap.put(DataType.valueOf(v_strbuf.get(1)), strings);
                final var stringHashMapHashMap = this.g_data_library_2d_.get(data_type1);
                stringHashMapHashMap.put(v_strbuf.get(1), dataTypeListHashMap);
                this.g_data_library_2d_.put(data_type1, stringHashMapHashMap);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initNotMutateAbleType(String filename) {

    }

    @Override
    public void initSafeGenerateType(String filename) {
        System.out.println(String.format("[*] init safe generate type: %s", filename));
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                if (line.isEmpty()) {
                    continue;
                }
                NodeType node_type = get_nodetype_by_String("k" + line);
                this.safe_generate_type_.add(node_type);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addIrToLibrary(IR cur) {
        extract_struct(cur);
        cur = deep_copy(cur);
        add_ir_to_library_no_deepcopy(cur);
    }

    @Override
    public String get_a_String() {
        int com_size = this.common_string_library_.size();
        int lib_size = this.string_library_.size();
        int double_lib_size = lib_size * 2;
        int rand_int = random.nextInt(double_lib_size + com_size);
        if (rand_int < double_lib_size) {
            return this.string_library_.get(rand_int >> 1);
        } else {
            rand_int -= double_lib_size;
            return this.common_string_library_.get(rand_int);
        }
    }

    @Override
    public long get_a_val() {
        assert this.value_library_.size() != 0;

        return this.value_library_.get(random.nextInt() % (this.value_library_.size()));
    }

    @Override
    public IR get_ir_from_library(IRType type) {
        final int generate_prop = 1;
        final int threshold = 0;
        IR empty_ir = new IR(kStringLiteral, "");

        //#ifdef USEGENERATE
        //  if (ir_library_[type].empty() == true ||
        //      (get_rand_int(400) == 0 && type != kUnknown)) {
        //    auto ir = generate_ir_by_type(type);
        //    add_ir_to_library_no_deepcopy(ir);
        //    return ir;
        //  }
        //#endif
        //TODO add option
        boolean USEGENERATE = true;
        if (USEGENERATE) {
            if (this.ir_library_.get(type).isEmpty() || (random.nextInt(400) == 0 && type != kUnknown)) {
                IR ir = generate_ir_by_type(type);
                add_ir_to_library_no_deepcopy(ir);
                return ir;
            }
        }
        if (this.ir_library_.get(type).isEmpty()) return empty_ir;
        return this.ir_library_.get(type).get(random.nextInt() % this.ir_library_.get(type).size());
    }

    @Override
    public IR generate_ir_by_type(IRType type) {
        Node ast_node = generate_ast_node_by_type(type);
        ast_node.generate();
        List<IR> tmp_vector = new ArrayList<>();
        ast_node.translate(tmp_vector);
        assert tmp_vector.size() != 0;

        return tmp_vector.get(tmp_vector.size() - 1);
    }

    @Override
    public String get_data_by_type(DataType dataType) {
        return null;
    }

    @Override
    public HashMap<String, String> get_data_2d_by_type(DataType type1, DataType type2) {
        HashMap<String, String> res = new HashMap<>();
        res.put("", "");
        int size = this.data_library_2d_.get(type1).size();

        if (size == 0) {
            return res;
        }
        int rint = random.nextInt() % size;

        int counter = 0;
        for (Map.Entry<String, HashMap<DataType, List<String>>> i : this.data_library_2d_.get(type1).entrySet()) {
            if (counter++ == rint) {
                //return std::make_pair(i.first, vector_rand_ele(i.second[type2]));
                HashMap<String, String> objectObjectHashMap = new HashMap<String, String>();
                objectObjectHashMap.put(i.getKey(), i.getValue().get(type2).get(random.nextInt() % (i.getValue().get(type2).size())));
            }
        }
        return res;
    }

    @Override
    public void reset_data_library() {
        this.data_library_.clear();
        this.data_library_2d_.clear();
    }

    @Override
    public String parse_data(String input) {
        String res = null;
        //TODO:need check
        if (input.equals("_int_")) {
            res = String.valueOf(get_a_val());
        } else if (input.equals("_empty_")) {
            res = "";
        } else if (input.equals("_boolean_")) {
            if (random.nextInt() % 2 == 0)
                res = "false";
            else
                res = "true";
        } else if (input.equals("_string_")) {
            res = get_a_String();
        } else {
            res = input;
        }
        return res;
    }

    @Override
    public void extract_struct(IR root) {
        IRType type = root.type;
        if (root.left != null) {
            extract_struct(root.left);
        }
        if (root.right != null) {
            extract_struct(root.right);
        }
        if (root.left != null || root.right != null) {
            return;
        }
        if (this.string_types_.contains(type)) {
            root.strVal = "'x'";
        } else if (this.int_types_.contains(type)) {
            root.intVal = 1;
        } else if (this.float_types_.contains(type)) {
            root.floatVal = 1.0F;
        }
    }

    @Override
    public void extract_struct2(IR root) {
        IRType type = root.type;
        if (root.left != null) {
            extract_struct2(root.left);
        }
        if (root.right != null) {
            extract_struct2(root.right);
        }
        if (root.left != null || root.right != null) {
            return;
        }
        if (root.dataType != kDataWhatever) {
            root.strVal = "x" + counter++;
            return;
        }
        if (this.string_types_.contains(type)) {
            root.strVal = "'x'";
        } else if (this.int_types_.contains(type)) {
            root.intVal = 1;
        } else if (this.float_types_.contains(type)) {
            root.floatVal = 1.0F;
        }
    }

    @Override
    public boolean fix(IR root) {
        HashMap<IR, IR> m_save = new HashMap<>();
        boolean res = true;

        List<IR> stmts = split_to_stmt(root, m_save, this.split_stmt_types_);
        if (stmts.size() > 8) {
            connect_back(m_save);
            return false;
        }
        clear_scope_library(true);
        for (IR stmt : stmts) {
            HashMap<IR, IR> m_substmt_save = new HashMap<>();
            List<IR> substmts = split_to_stmt(stmt, m_substmt_save, split_substmt_types_);

            int stmt_num = substmts.size();
            if (stmt_num > 4) {
                connect_back(m_save);
                connect_back(m_substmt_save);
                return false;
            }
            for (IR substmt : substmts) {
                clear_scope_library(false);
                int tmp_node_num = calc_node(substmt);
                if (stmt_num == 1 && tmp_node_num > 150 || tmp_node_num > 120) {
                    connect_back(m_save);
                    connect_back(m_substmt_save);
                    return false;
                }
                res = fix_one(substmt, this.scope_library_) && res;

                if (res == false) {
                    connect_back(m_save);
                    connect_back(m_substmt_save);
                    return false;
                }
            }
            res = connect_back(m_substmt_save) && res;
        }
        res = connect_back(m_save) && res;

        return res;
    }

    @Override
    public List<IR> split_to_stmt(IR root, HashMap<IR, IR> m_save, HashSet<IRType> split_set) {
        List<IR> res = new ArrayList<>();
        Deque<IR> bfs = new ArrayDeque<>((Collection) root);
        while (!bfs.isEmpty()) {
            IR node = bfs.pop();
            if (node != null && node.left != null) {
                bfs.add(node.left);
            }
            if (node != null && node.right != null) {
                bfs.add(node.right);
            }

            if (node.left != null && split_set.contains(node.left.type)) {
                res.add(node.left);
                m_save.put(node.left, node.left);
                node.left = null;
            }
            if (node.right != null && split_set.contains(node.right.type)) {
                res.add(node.right);
                m_save.put(node.right, node.right);
                node.right = null;
            }

            if (split_set.contains(root.type)) {
                res.add(root);
            }
        }
        return res;
    }

    @Override
    public boolean connect_back(HashMap<IR, IR> m_save) {
        // for(auto &iter: m_save){
        //        *(iter.first) = iter.second;
        //    }
        //    return true;
        //TODO:add

        return true;
    }

    public static HashSet<IR> visited;

    @Override
    public boolean fix_one(IR stmt_root, HashMap<Integer, HashMap<DataType, List<IR>>> scope_library) {
        visited.clear();
        analyze_scope(stmt_root);

        HashMap<IR, List<IR>> graph = build_graph(stmt_root, scope_library);

        /**
         * c++ 中 出现条件编译
         * #ifdef GRAPHLOG
         *     for(auto &iter: graph){
         *         cout << "Node: " << iter.first.to_string() << " connected with:" << endl;
         *         for(auto &k: iter.second){
         *             cout << k.to_string() << endl;
         *         }
         *         cout << "--------" <<endl;
         *     }
         *     cout << "OUTPUT END" << endl;
         * #endif
         * */

        return fill_stmt_graph(graph);
    }

    @Override
    public void analyze_scope(IR stmt_root) {
        if (stmt_root.left != null) {
            analyze_scope(stmt_root.left);
        }
        if (stmt_root.right != null) {
            analyze_scope(stmt_root.right);
        }

        DataType dataType = stmt_root.dataType;
        if (dataType == kDataWhatever) {
            return;
        }
        final var irList = this.scope_library_.get(stmt_root.scope).get(dataType);
        irList.add(stmt_root);
        this.scope_library_.get(stmt_root.scope).put(dataType, irList);
    }

    @Override
    public HashMap<IR, List<IR>> build_graph(IR
                                                     stmt_root, HashMap<Integer, HashMap<DataType, List<IR>>> scope_library) {
        HashMap<IR, List<IR>> res = new HashMap<>();
        Deque<IR> bfs = new ArrayDeque<>((Collection) stmt_root);

        while (!bfs.isEmpty()) {
            IR node = bfs.pop();

            int cur_scope = node.scope;
            DataFlag cur_data_flag = node.data_flag;
            DataType cur_data_type = node.dataType;

            if (int_types_.contains(node.type)) {
                if (random.nextInt(100) > 50)
                    node.intVal = Math.toIntExact(this.value_library_.get(random.nextInt() % (this.value_library_.size())));
                else
                    node.intVal = random.nextInt() % (100);
            } else if (float_types_.contains(node.type)) {
                node.floatVal = (random.nextInt() % (100000000));
            }

            if (node.left != null) {
                bfs.add(node.left);
            }
            if (node.right != null) {
                bfs.add(node.right);
            }
            if (cur_data_type == kDataWhatever) continue;

            // res[node];
            //        cur_scope--;

            cur_scope--;
            if (this.relationmap_.containsKey(cur_data_type)) {
                HashMap<DataType, RelationType> target_data_typemap = this.relationmap_.get(cur_data_type);
                for (Map.Entry<DataType, RelationType> entry : target_data_typemap.entrySet()) {
                    IR pick_node = null;
                    if (((cur_data_flag.ordinal()) & kMapToClosestOne.ordinal()) == 1) {
                        pick_node = find_closest_node(stmt_root, node, entry.getKey());
                        if (pick_node != null && pick_node.scope != cur_scope) {
                            pick_node = null;
                        }
                    } else {
                        if (node.strVal == null) {

                        }
                        if (((cur_data_flag.ordinal()) & kDefine.ordinal()) == 1 || this.relationmap_.get(cur_data_type).get(entry.getKey()) != RelationType.kRelationElement) {
                            if (!scope_library.get(cur_scope).get(entry.getKey()).isEmpty()) {
                                pick_node = (scope_library.get(cur_scope).get(entry.getKey()).get(random.nextInt() % scope_library.get(cur_scope).get(entry.getKey()).size()));
                            }
                        }
                    }
                    if (pick_node != null) {
                        res.get(pick_node).add(node);
                        res.put(pick_node, res.get(pick_node));
                    }
                }
            }
        }
        return res;
    }

    @Override
    public boolean fill_stmt_graph(HashMap<IR, List<IR>> graph) {
        boolean res = true;
        HashMap<IR, Boolean> zero_indegrees = new HashMap<>();
        for (Map.Entry<IR, List<IR>> entry : graph.entrySet()) {
            if (!zero_indegrees.containsKey(entry.getKey())) {
                zero_indegrees.put(entry.getKey(), true);
            }
            for (IR ir : entry.getValue()) {
                zero_indegrees.put(ir, false);
            }
        }

        for (Map.Entry<IR, List<IR>> entry : graph.entrySet()) {
            DataType type1 = entry.getKey().dataType;
            IR beg = entry.getKey();
            if (zero_indegrees.get(beg) == false || visited.contains(beg)) {
                continue;
            }
            res &= fill_one(entry.getKey());
            res &= fill_stmt_graph_one(graph, entry.getKey());
        }
        return res;
    }

    @Override
    public void clear_scope_library(boolean clear_define) {
        int level = clear_define ? 0 : 1;
        int sz = this.scope_library_.size();
        this.scope_library_.clear();
    }

    @Override
    public IR find_closest_node(IR stmt_root, IR node, DataType type) {
        IR cur = node;
        while (true) {
            IR parent = locateParent(stmt_root, cur);
            if (parent == null) {
                break;
            }
            boolean flag = false;
            while (parent.left == null || parent.right == null) {
                cur = parent;
                parent = locateParent(stmt_root, cur);
                if (parent == null) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                return null;
            }

            IR search_root = parent.left == cur ? parent.right : parent.left;
            IR res = search_mapped_ir(search_root, type);
            if (res != null) {
                return res;
            }
            cur = parent;
        }
        return null;
    }

    public static IR search_mapped_ir(IR ir, DataType type) {
        List<IR> to_search = new ArrayList<>();
        List<IR> backup = new ArrayList<>();
        to_search.add(ir);
        while (!to_search.isEmpty()) {
            for (IR i : to_search) {
                if (i.dataType == type) {
                    return i;
                }
                if (i.left != null) {
                    backup.add(i.left);
                }
                if (i.right != null) {
                    backup.add(i.right);
                }
            }
            to_search = backup;
            backup.clear();
        }
        return null;
    }

    @Override
    public boolean fill_one(IR ir) {
        DataType type = ir.dataType;
        visited.add(ir);
        if (((ir.data_flag.ordinal()) & kDefine.ordinal()) == 1) {
            String new_name = gen_id_name();
            List<String> strings = this.data_library_.get(type);
            strings.add(new_name);
            this.data_library_.put(type, strings);


            for (Map.Entry<DataType, HashMap<DataType, RelationType>> iter : this.relationmap_.entrySet()) {
                for (Map.Entry<DataType, RelationType> iter2 : iter.getValue().entrySet()) {
                    if (iter2.getKey() == type && iter2.getValue() == RelationType.kRelationSubtype) {
                        //data_library_2d_[type][new_name];
                        //TODO
//                        this.data_library_2d_.put(type, new_name);
                    }
                }
            }
            return true;
        } else if (((ir.data_flag.ordinal()) & kAlias.ordinal()) == 1) {
            String alias_target;
            if (this.data_library_.get(type).size() != 0) {
                alias_target = this.data_library_.get(type).get(random.nextInt() % this.data_library_.get(type).size());
            } else {
                alias_target = random.nextInt() % 2 == 0 ? "v0" : "v1";
            }

            String new_name = gen_id_name();
            List<String> strings = this.data_library_.get(type);
            strings.add(new_name);
            this.data_library_.put(type, strings);

            if (this.data_library_2d_.containsKey(type)) {
                if (this.data_library_2d_.get(type).containsKey(alias_target)) {
                    HashMap<DataType, List<String>> dataTypeListHashMap = this.data_library_2d_.get(type).get(alias_target);
                    HashMap<String, HashMap<DataType, List<String>>> stringHashMapHashMap = this.data_library_2d_.get(type);
                    stringHashMapHashMap.put(alias_target, dataTypeListHashMap);
                    this.data_library_2d_.put(type, stringHashMapHashMap);
                }
            }
            return true;
        } else if (this.data_library_.containsKey(type)) {
            if (this.data_library_.get(type).isEmpty()) {
                ir.strVal = "v0";
                return false;
            }
            ir.strVal = this.data_library_.get(type).get(random.nextInt() % this.data_library_.get(type).size());
            if (((ir.data_flag.ordinal()) & kUndefine.ordinal()) == 1) {
                remove_one_from_datalibrary(ir.dataType, ir.strVal);
                if (this.data_library_2d_.containsKey(type) && this.data_library_2d_.get(type).containsKey(ir.strVal)) {
                    for (Map.Entry<DataType, List<String>> entry : this.data_library_2d_.get(type).get(ir.strVal).entrySet()) {
                        //TODO:check
                        for (String s : entry.getValue()) {
                            remove_one_pair_from_datalibrary_2d(type, entry.getKey(), ir.strVal);
                        }
                        if (!this.data_library_2d_.get(type).containsKey(ir.strVal))
                            break;

                    }
                }
            }
            return true;
        } else if (this.g_data_library_.containsKey(type)) {
            if (this.g_data_library_.get(type).isEmpty()) {
                return false;
            }
            ir.strVal = this.g_data_library_.get(type).get(random.nextInt() % this.g_data_library_.get(type).size());
            return true;
        } else if (this.g_data_library_2d_.containsKey(type)) {
            //TODO to implement
            /**
             *    int choice = get_rand_int(g_data_library_2d_[type].size());
             *         auto iter = g_data_library_2d_[type].begin();
             *         while(choice > 0){
             *             iter ++;
             *             choice --;
             *         }
             *         ir.str_val_ = iter.first;
             * */
            int choice = random.nextInt() % (this.g_data_library_2d_.get(type).size());


            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean fill_one_pair(IR parent, IR child) {
        visited.add(child);

        //((child.data_flag_) & kDefine)
        boolean is_define = (child.data_flag.ordinal() & kDefine.ordinal()) == 1;
        boolean is_replace = (child.data_flag.ordinal() & kReplace.ordinal()) == 1;
        boolean is_undefine = (child.data_flag.ordinal() & kUndefine.ordinal()) == 1;
        boolean is_alias = (child.data_flag.ordinal() & kAlias.ordinal()) == 1;

        String new_name = "";

        if (is_define || is_replace || is_alias) {
            new_name = gen_id_name();
        }

        DataType p_type = parent.dataType;
        DataType c_type = child.dataType;
        String p_str = parent.strVal;

        RelationType r_type = this.relationmap_.get(c_type).get(p_type);
        switch (r_type) {
            case kRelationElement:
                if (is_replace) {
                    child.strVal = new_name;
                    replace_one_from_datalibrary(c_type, p_str, new_name);

                    if (this.data_library_2d_.containsKey(p_type)) {
                        if (this.data_library_2d_.get(p_type).containsKey(p_str)) {
                            //TODO:c++版本未声明extract方法
//                           var tmp = this.data_library_2d_.get(p_type).extract(p_str);
                        }
                    }
                }
        }

        return false;
    }

    @Override
    public boolean fill_stmt_graph_one(HashMap<IR, List<IR>> graph, IR ir) {
        if (!graph.containsKey(ir)) {
            return true;
        }
        boolean res = true;
        DataType type = ir.dataType;
        List<IR> vec = graph.get(ir);

        if (!vec.isEmpty()) {
            for (IR d : vec) {
                res = res & fill_one_pair(ir, d);
                res = res & fill_stmt_graph_one(graph, d);
            }
        }
        return res;
    }

    public static boolean replace_in_vector(String old_str, String new_str, List<String> victim) {
        for (int i = 0; i < victim.size(); i++) {
            if (Objects.equals(victim.get(i), old_str)) {
                victim.set(i, new_str);
                return true;
            }
        }
        return false;
    }

    public static boolean remove_in_vector(String str_to_remove, List<String> victim) {
        for (String iter : victim) {
            if (Objects.equals(iter, str_to_remove)) {
                victim.remove(iter);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validate(IR root) {
        reset_data_library();
        String sql = root.toString();
        //parser使用到了flex.h中的方法，flex/bison为另一个工具
        astNode.Program ast = parser(sql);
        if (ast == null) {
            return false;
        }
        deepDelete(root);
        root = null;

        List<IR> irVector = new ArrayList<>();
        ast.translate(irVector);
        ast.deepDelete();

        root = irVector.get(irVector.size() - 1);
        reset_id_counter();

        if (fix(root) == false) {
            return false;
        }

        return true;
    }

    @Override
    public int calc_node(IR root) {
        int res = 0;
        if (root.left != null) {
            res += calc_node(root.left);
        }
        if (root.right != null) {
            res += calc_node(root.right);
        }
        return res + 1;
    }

    @Override
    public boolean replace_one_value_from_datalibray_2d(DataType p_datatype, DataType c_data_type, String
            p_key, String old_c_value, String new_c_value) {
        replace_one_from_datalibrary(c_data_type, old_c_value, new_c_value);
        replace_in_vector(old_c_value, new_c_value, this.data_library_2d_.get(p_datatype).get(p_key).get(c_data_type));
        return true;
    }

    @Override
    public boolean remove_one_pair_from_datalibrary_2d(DataType p_datatype, DataType c_data_type, String p_key) {
        for (String value : this.data_library_2d_.get(p_datatype).get(p_key).get(c_data_type)) {
            remove_one_from_datalibrary(c_data_type, value);
        }

        HashMap<DataType, List<String>> dataTypeListHashMap = this.data_library_2d_.get(p_datatype).get(p_key);
        dataTypeListHashMap.remove(c_data_type);
        this.data_library_2d_.get(p_datatype).put(p_key, dataTypeListHashMap);

        if (!this.data_library_2d_.get(p_datatype).get(p_key).isEmpty()) {
            remove_one_from_datalibrary(p_datatype, p_key);
            HashMap<String, HashMap<DataType, List<String>>> stringHashMapHashMap = this.data_library_2d_.get(p_datatype);
            this.data_library_2d_.put(p_datatype, stringHashMapHashMap);
        }
        return true;
    }

    @Override
    public boolean replace_one_from_datalibrary(DataType datatype, String old_str, String new_str) {

        return replace_in_vector(old_str, new_str, data_library_.get(datatype));
    }

    @Override
    public boolean remove_one_from_datalibrary(DataType datatype, String key) {
        return remove_in_vector(key, data_library_.get(datatype));
    }

    @Override
    public void debug(IR root) {
        for (String i : this.data_library_.get(kDataFunctionName)) {
            System.out.println(i);
        }
    }

    @Override
    public int try_fix(String buf, int len, String new_buf, int new_len) {
        String sql = buf;

        astNode.Program ast = parser(sql);

        new_buf = buf;
        new_len = len;
        if (ast == null)
            return 0;

        List<IR> v_ir = new ArrayList<>();
        IR ir_root = ast.translate(v_ir);
        ast.deepDelete();

        if (ir_root == null) {
            return 0;
        }
        boolean fixed_result = validate(ir_root);
        String fixed = null;
        if (fixed_result != false) {
            fixed = ir_root.toString();
        }
        deepDelete(ir_root);

        if (fixed.isEmpty()) {
            return 0;
        }
        char[] sfixed = new char[fixed.length() + 1];
        sfixed = fixed.toCharArray();
        sfixed[fixed.length()] = 0;

        new_buf = Arrays.toString(sfixed);
        new_len = fixed.length();
        return 1;
    }

    @Override
    public void add_ir_to_library_no_deepcopy(IR cur) {
        if (cur.left != null) {
            add_ir_to_library_no_deepcopy(cur.left);
        }
        if (cur.right != null) {
            add_ir_to_library_no_deepcopy(cur.right);
        }

        IRType type = cur.type;
        long h = hash(cur);

        if (this.ir_library_hash_.containsKey(h)) {
            return;
        }

        HashSet<Long> longs = this.ir_library_hash_.get(type);
        longs.add(h);
        this.ir_library_hash_.put(type, longs);

        List<IR> irList = this.ir_library_.get(type);
        irList.add(cur);
        this.ir_library_.put(type, irList);
    }
}
