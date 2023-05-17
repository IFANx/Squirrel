import mysql.src.mutate.Mutator;
import utils.DataBase;

import java.io.*;
import java.util.HashMap;

import static mysql.MySQLClient.loadConfig;

public class CustomMutator {
    //自定义mutator


    public static class SquirrelMutator {
        public DataBase database;
        public String current_input;

        public SquirrelMutator(DataBase database) {
            this.database = database;
        }
    }


    /**
     * TODO：使用到了AFL中的一些afl_state_t 结构体，暂时不清除Squirrel是如何结合AFL使用的除此以外，Squirrel工具中定义的这些方法并没有被调用
     * afl没有被使用，seed也没有被使用
     * void *afl_custom_init(afl_state_t *afl, unsigned int seed) {
     *   const char *config_file_path = getenv(kConfigEnv);
     *   if (!config_file_path) {
     *     std::cerr << "You should set the enviroment variable " << kConfigEnv
     *               << " to the path of your config file." << std::endl;
     *     exit(-1);
     *   }
     *   std::string config_file(config_file_path);
     *   std::cerr << "Config file: " << config_file << std::endl;
     *   YAML::Node config = YAML::LoadFile(config_file);
     *   if (!utils::validate_db_config(config)) {
     *     std::cerr << "Invalid config!" << std::endl;
     *   }
     *   auto *mutator = create_database(config);
     *   return new SquirrelMutator(mutator);
     * }
     */
    public SquirrelMutator afl_custom_init(String filePath) {
        File configFile = new File(filePath);
        if (!configFile.exists()) {
            System.out.println("You should set the environment variable to the path of your config file.");
            System.exit(1);
        }
        HashMap<String, Object> config;
        try {
            config = loadConfig();
        } catch (FileNotFoundException e) {
            throw new AssertionError(e);
        }
        DataBase mutator = DBFactory.create_database(config);
        return new SquirrelMutator(mutator);
    }

    public void afl_custom_deInit(SquirrelMutator data) {
    }


    public int afl_custom_queue_new_entry(SquirrelMutator mutator,
                                          String filename_new_queue,
                                          String filename_orig_queue) throws FileNotFoundException {
        // read a file by file name
        StringBuilder content = new StringBuilder();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename_new_queue));
            String line = reader.readLine();
            while (line != null) {
                content.append(line).append("\n");
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mutator.database.save_interesting_query(content.toString());
        return 0;
    }

    public int afl_custom_fuzz_count(SquirrelMutator mutator,
                                     String buf, int buf_size) {
//        std::string sql((const char *)buf, buf_size);
        //TODO:need check
        String sql = buf.substring(0, buf_size);
        return mutator.database.mutate(sql);
    }

    /**
     * size_t afl_custom_fuzz(SquirrelMutator *mutator, uint8_t *buf, size_t buf_size,
     *                        u8 **out_buf, uint8_t *add_buf,
     *                        size_t add_buf_size,  // add_buf can be NULL
     *                        size_t max_size) {
     * */
    public int afl_custom_fuzz(SquirrelMutator mutator, int buf, int buf_size,
                               int out_buf, int add_buf,
                               int add_buf_size,  // add_buf can be NULL
                               int max_size) {
        DataBase db = mutator.database;
        assert (db.has_mutated_test_cases());
        mutator.current_input = db.get_next_mutated_query();
        out_buf = Integer.parseInt(mutator.current_input);
        return mutator.current_input.length();
    }
}
