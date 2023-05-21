package mysql.src;

import mysql.src.ast.node.astNode;

import java.util.List;
import java.util.Random;

public class Utils {
    public static Random random = new Random();

    public static String trimString(char[] res) {
        int count = 0;
        int idx = 0;
        boolean expectSpace = false;
        for (int i = 0; i < res.length; i++) {
            if (res[i] == ';' && i != res.length - 1) {
                res[i + 1] = '\n';
            }
            if (res[i] == ' ') {
                if (expectSpace == false) {
                    continue;
                } else {
                    expectSpace = false;
                    res[idx++] = res[i];
                    count++;
                }
            } else {
                expectSpace = true;
                res[idx++] = res[i];
                count++;
            }
        }
//        res.resize(count);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public static String gen_string() {
        return "x";
    }

    public static double gen_float() {
        return 1.2;
    }

    public static long gen_long() {
        return 1;
    }

    public static int gen_int() {
        return 1;
    }

    public static astNode.Program parser(String sql) {
        //TODO 使用到了flex_lexer.h中的内容
        astNode.Program p = new astNode.Program();
        System.out.println(p.caseIdx);
//        yyscan_t scanner;
//        YY_BUFFER_STATE state;
//
//        if (ff_lex_init(&scanner)) {
//            return NULL;
//        }
//        state = ff__scan_string(sql.c_str(), scanner);
//
//        int ret = ff_parse(p, scanner);
//
//        ff__delete_buffer(state, scanner);
//        ff_lex_destroy(scanner);
//        if(ret != 0){
//            p->deep_delete();
//            return NULL;
//        }


        return p;
    }

    //TODO need check
    public static long DuckingHash(String key, int len) {
        final long m = 0xc6a4a7935bd1e995L;
        final int r = 47;
        long h = 0xdeadbeefdeadbeefL ^ (len * m);

        long data = Long.parseLong(key);
        final long end = data + (len / 8);

        while (data != end) {
            long k = data++;

            k *= m;
            k ^= k >> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        final long[] data2 = new long[]{data};

        switch (len & 7) {
            case 7:
                h ^= (data2[6]) << 48;
            case 6:
                h ^= (data2[5]) << 40;
            case 5:
                h ^= (data2[4]) << 32;
            case 4:
                h ^= (data2[3]) << 24;
            case 3:
                h ^= (data2[2]) << 16;
            case 2:
                h ^= (data2[1]) << 8;
            case 1:
                h ^= (data2[0]);
                h *= m;
        } ;

        h ^= h >> r;
        h *= m;
        h ^= h >> r;

        return h;
    }

    List<String> get_all_files_in_dir(String dir_name) {
        return null;
    }


}
