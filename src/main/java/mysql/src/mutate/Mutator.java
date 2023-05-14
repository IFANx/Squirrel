package mysql.src.mutate;

import mysql.src.ast.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public abstract class Mutator {
    final int LUCKY_NUMBER = 500;

    IR deepCopyWithRecord(IR root, IR record) {
        return null;
    }

    long hash(IR ir) {
        return 0;
    }

    long hash(String s) {
        return 0;
    }

    IR irRandomGenerator(List<IR> vIrCollector) {
        return null;
    }

    List<IR> mutateAll(List<IR> vIrCollector) {
        return null;
    }

    List<IR> mutate(IR input) {
        return null;
    }

    IR strategyDelete(IR cur) {
        return null;
    }

    IR strategyInsert(IR cur) {
        return null;
    }

    IR strategyReplace(IR cur) {
        return null;
    }

    boolean luckyEnoughToBeMutated(int mutatedTimes) {
        return false;
    }

    boolean replace(IR root, IR oldIr, IR newIr) {
        return false;
    }

    IR locateParent(IR root, IR oldIr) {
        return null;
    }

    void init(String fTestcase, String fCommonString, String file2d, String file1d, String fGenType) {

    }

    void initIrLibrary(String filename) {

    }

    void initValueLibrary() {

    }

    void initCommonString(String filename) {

    }

    void initDataLibrary(String filename) {

    }

    void initDataLibrary2d(String filename) {

    }

    void initNotMutateAbleType(String filename) {

    }

    void initSafeGenerateType(String filename) {

    }

    void addIrToLibrary(IR ir) {

    }

    String get_a_String() {
        return null;
    }

    long get_a_val() {
        return 0;
    }

    IR get_ir_from_library(IRType type) {
        return null;
    }

    IR generate_ir_by_type(IRType type) {
        return null;
    }

    String get_data_by_type(DataType dataType) {
        return null;
    }

    HashMap<String, String> get_data_2d_by_type(DataType dataType1, DataType dataType2) {
        return null;
    }

    void reset_data_library() {
    }

    String parse_data(String s) {
        return null;
    }

    void extract_struct(IR ir) {
    }

    void extract_struct2(IR ir) {

    }

    boolean fix(IR root)//done
    {
        return false;
    }

    List<IR> split_to_stmt(IR root, HashMap<IR, IR> m_save, HashSet<IRType> split_set)//done
    {
        return null;
    }

    boolean connect_back(HashMap<IR, IR> m_save) //done
    {
        return false;
    }

    boolean fix_one(IR stmt_root, HashMap<Integer, HashMap<DataType, List<IR>>> scope_library)//done
    {
        return false;
    }

    void analyze_scope(IR stmt_root) {
    }

    HashMap<IR, List<IR>> build_graph(IR stmt_root, HashMap<Integer, HashMap<DataType, List<IR>>> scope_library) {
        return null;
    }

    boolean fill_stmt_graph(HashMap<IR, List<IR>> graph) //done
    {
        return false;
    }

    void clear_scope_library(boolean clear_define)// done
    {

    }

    IR find_closest_node(IR stmt_root, IR node, DataType type) //done
    {
        return null;
    }

    boolean fill_one(IR parent) //done
    {
        return false;
    }

    boolean fill_one_pair(IR parent, IR child) //done
    {
        return false;
    }

    boolean fill_stmt_graph_one(HashMap<IR, List<IR>> graph, IR ir)//done
    {
        return false;
    }

    boolean validate(IR root) //done
    {
        return false;
    }

    int calc_node(IR root) {
        return 0;
    }

    boolean replace_one_value_from_datalibray_2d(DataType p_datatype, DataType c_data_type, String p_key, String old_c_value, String new_c_value) {
        return false;
    }

    boolean remove_one_pair_from_datalibrary_2d(DataType p_datatype, DataType c_data_type, String p_key) {
        return false;
    }

    boolean replace_one_from_datalibrary(DataType datatype, String old_str, String new_str) {
        return false;
    }

    boolean remove_one_from_datalibrary(DataType datatype, String key) {
        return false;
    }


    void debug(IR root) {

    }

    int try_fix(String buf, int len, String new_buf, int new_len) {
        return 0;
    }


    void add_ir_to_library_no_deepcopy(IR ir)//DONE
    {

    }

    public IR record_ = null;
    public IR mutated_root_ = null;
    public HashMap<IRType, List<IR>> ir_library_ = new HashMap<>();
    public HashMap<IRType, HashSet<Long>> ir_library_hash_ = new HashMap<>();

    public List<String> string_library_ = new ArrayList<>();
    HashSet<Long> string_library_hash_ = new HashSet<>();
    List<Long> value_library_ = new ArrayList<>();

    HashMap<DataType, HashMap<DataType, RelationType>> relationmap_ = new HashMap<>();

    List<String> common_string_library_ = new ArrayList<>();
    HashSet<IRType> not_mutatable_types_ = new HashSet<>();
    HashSet<IRType> string_types_ = new HashSet<>();
    HashSet<IRType> int_types_ = new HashSet<>();
    HashSet<IRType> float_types_ = new HashSet<>();

    HashSet<NodeType> safe_generate_type_ = new HashSet<>();
    HashSet<IRType> split_stmt_types_ = new HashSet<>();
    HashSet<IRType> split_substmt_types_ = new HashSet<>();

    HashMap<DataType, List<String>> data_library_ = new HashMap<>();
    HashMap<DataType, HashMap<String, HashMap<DataType, List<String>>>> data_library_2d_ = new HashMap<>();

    HashMap<DataType, List<String>> g_data_library_ = new HashMap<>();
    HashMap<DataType, HashSet<Long>> g_data_library_hash_ = new HashMap<>();
    HashMap<DataType, HashMap<String, HashMap<DataType, List<String>>>> g_data_library_2d_ = new HashMap<>();
    HashMap<DataType, HashMap<String, HashMap<DataType, List<String>>>> g_data_library_2d_hash_ = new HashMap<>();


    HashMap<Integer, HashMap<DataType, List<IR>>> scope_library_ = new HashMap<>();

    HashSet<Long> global_hash_ = new HashSet<>();
}
