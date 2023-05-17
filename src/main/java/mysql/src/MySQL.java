package mysql.src;

import mysql.src.ast.AST;
import mysql.src.ast.IR;
import mysql.src.ast.node.astNode;
import mysql.src.mutate.Mutator;
import utils.DataBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import static mysql.MySQLClient.loadConfig;
import static mysql.src.Utils.parser;

public class MySQL extends DataBase {
    IR ir;

    private Mutator mutator;

    private Stack<String> validate_test_cases;

    MySQL() {

    }

    private int validate_all(List<IR> ir_set) {
        for (IR ir : ir_set) {
            boolean result = this.mutator.validate(ir);
            if (!result) {
                continue;
            }
            String validated_ir = ir.toString();
            this.validate_test_cases.add(validated_ir);
        }
        return this.validate_test_cases.size();
    }

    @Override
    public boolean initialize() {
        HashMap<String, Object> config;
        try {
            config = loadConfig();
        } catch (FileNotFoundException e) {
            throw new AssertionError(e);
        }
        String init_lib_path = (String) config.get("init_lib");
        String data_lib = (String) config.get("data_lib");

        //获取init-lib目录中的所有文件
        File folder = new File(init_lib_path);
        File[] files = folder.listFiles();

        for (File file : files) {
            this.mutator.init(String.format("%s/%s", init_lib_path, file.getName()), "", "", "", "");
        }

        this.mutator.initDataLibrary(data_lib);

        return true;
    }

    @Override
    public int mutate(String query) {
        List<IR> ir_set = new ArrayList<>();
        List<IR> mutated_tree = new ArrayList<>();

        astNode.Program program_root = parser(query);
        if (program_root == null) {
            return 0;
        }

        try {
            program_root.translate(ir_set);
        } catch (Throwable throwable) {
            ir_set.clear();
            program_root.deepDelete();
        }

        mutated_tree = this.mutator.mutateAll(ir_set);
        AST.deepDelete(ir_set.get(ir_set.size() - 1));

        int validated_ir_size = validate_all(mutated_tree);
        for (IR ir : mutated_tree) {
            AST.deepDelete(ir);
        }
        return validated_ir_size;
    }

    @Override
    public String get_next_mutated_query() {
        assert has_mutated_test_cases();
        String result = this.validate_test_cases.peek();
        this.validate_test_cases.pop();
        return result;
    }

    @Override
    public boolean has_mutated_test_cases() {
        return !this.validate_test_cases.isEmpty();
    }

    @Override
    public boolean save_interesting_query(String query) {
        astNode.Program program = parser(query);
        if (program != null) {
            List<IR> ir_set = new ArrayList<>();
            IR ir = program.translate(ir_set);
            ir_set.clear();
            this.mutator.extract_struct(ir);
            String strip_sql = ir.toString();
            astNode.Program p_strip_sql = parser(strip_sql);
            if (program != null) {
                IR root_ir = p_strip_sql.translate(ir_set);
                p_strip_sql.deepDelete();
                this.mutator.add_ir_to_library(root_ir);
                AST.deepDelete(root_ir);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean clean_up() {
        return super.clean_up();
    }
}
