package mysql.src.ast.node;

import mysql.src.ast.IR;
import mysql.src.ast.IROperator;

import java.util.List;
import java.util.Random;

import static mysql.src.ast.IRType.*;
import static mysql.src.ast.IRType.kStmt;

//TODO：IR method implement
public interface astNode {
    Random random = new Random();

    class Program extends Node {
        StmtList stmtList;

        @Override
        public IR translate(List<IR> vIrCollector) {
            /**
             * TRANSLATESTART
             *
             * 		auto tmp1= SAFETRANSLATE(stmtlist_);  == (assert(a != NULL), a->translate(v_ir_collector))
             * 		res = new IR(kProgram, OP3("","",""), tmp1);
             *
             * 	TRANSLATEEND
             * */

            IR res;
            IR tmp1 = null;
            if (this.stmtList != null) {
                tmp1 = this.stmtList.translate(vIrCollector);
            }
            res = new IR(kProgram, new IROperator("", "", ""), tmp1);

            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = (int) (Math.random() % 1);
            this.stmtList = new StmtList();
            this.stmtList.generate();
        }

        @Override
        public void deepDelete() {
            this.stmtList = null;
        }
    }

    class StmtList extends Node {
        Random rand = new Random();

        Stmt stmt;

        StmtList stmtList;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1 = null;
            IR tmp2 = null;
            switch (this.caseIdx) {
                case 0:

                    if (this.stmt != null) {
                        tmp1 = this.stmt.translate(vIrCollector);
                    }
                    if (this.stmtList != null) {
                        tmp2 = this.stmtList.translate(vIrCollector);
                    }
                    res = new IR(kStmtlist, new IROperator("", ";", ""), tmp1, tmp2);
                    break;
                case 1:
                    if (this.stmt != null) {
                        tmp1 = this.stmt.translate(vIrCollector);
                    }
                    res = new IR(kStmtlist, new IROperator("", ";", ""), tmp1);
                    break;
                default:
                    throw new AssertionError();
            }
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = rand.nextInt() % 200;
            switch (this.caseIdx) {
                case 0:
                    this.stmt = new Stmt();
                    this.stmt.generate();
                    this.stmtList = new StmtList();
                    this.stmtList.generate();
                    break;
                case 1:
                    this.stmt = new Stmt();
                    this.stmt.generate();
                    break;
                default:
                    int tmpCaseIdx = rand.nextInt() % 1;
                    switch (tmpCaseIdx) {
                        case 0:
                            this.stmt = new Stmt();
                            this.stmt.generate();
                            this.caseIdx = 1;
                            break;
                    }
            }
        }

        //C++的delete方法，释放内存
        @Override
        public void deepDelete() {
            this.stmtList = null;
            this.stmt = null;
        }
    }

    class Stmt extends Node {

        InsertStmt insert_stmt_;
        DropStmt drop_stmt_;
        CreateStmt create_stmt_;
        SelectStmt select_stmt_;
        AlterStmt alter_stmt_;
        UpdateStmt update_stmt_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1 = null;
            switch (this.caseIdx) {
                case 0:
                    if (this.create_stmt_ != null) {
                        tmp1 = this.create_stmt_.translate(vIrCollector);
                    }
                    res = new IR(kStmt, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    if (this.drop_stmt_ != null) {
                        tmp1 = this.drop_stmt_.translate(vIrCollector);
                    }
                    res = new IR(kStmt, new IROperator("", "", ""), tmp1);
                    break;
                case 2:
                    if (this.select_stmt_ != null) {
                        tmp1 = this.select_stmt_.translate(vIrCollector);
                    }
                    res = new IR(kStmt, new IROperator("", "", ""), tmp1);
                    break;
                case 3:
                    if (this.update_stmt_ != null) {
                        tmp1 = this.update_stmt_.translate(vIrCollector);
                    }
                    res = new IR(kStmt, new IROperator("", "", ""), tmp1);
                    break;
                case 4:
                    if (this.insert_stmt_ != null) {
                        tmp1 = this.insert_stmt_.translate(vIrCollector);
                    }
                    res = new IR(kStmt, new IROperator("", "", ""), tmp1);
                    break;
                case 5:
                    if (this.alter_stmt_ != null) {
                        tmp1 = this.alter_stmt_.translate(vIrCollector);
                    }
                    res = new IR(kStmt, new IROperator("", "", ""), tmp1);
                    break;
                default:
                    throw new AssertionError();
            }

            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 6;
            switch (this.caseIdx) {
                case 0:
                    if (this.create_stmt_ != null) {
                        this.create_stmt_ = new CreateStmt();
                        create_stmt_.generate();
                    }
                    break;
                case 1:
                    if (this.drop_stmt_ != null) {
                        this.drop_stmt_ = new DropStmt();
                        this.drop_stmt_.generate();
                    }
                    break;
                case 2:
                    if (this.select_stmt_ != null) {
                        this.select_stmt_ = new SelectStmt();
                        this.select_stmt_.generate();
                    }
                    break;
                case 3:
                    if (this.update_stmt_ != null) {
                        this.update_stmt_ = new UpdateStmt();
                        this.update_stmt_.generate();
                    }
                    break;
                case 4:
                    if (this.insert_stmt_ != null) {
                        this.insert_stmt_ = new InsertStmt();
                        this.insert_stmt_.generate();
                    }
                    break;
                case 5:
                    if (this.alter_stmt_ != null) {
                        this.alter_stmt_ = new AlterStmt();
                        this.alter_stmt_.generate();
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.select_stmt_ = null;
            this.drop_stmt_ = null;
            this.create_stmt_ = null;
            this.alter_stmt_ = null;
            this.insert_stmt_ = null;
            this.update_stmt_ = null;
        }

    }

    class CreateStmt extends Node {
        CreateTriggerStmt create_trigger_stmt_;
        CreateIndexStmt create_index_stmt_;
        CreateViewStmt create_view_stmt_;
        CreateTableStmt create_table_stmt_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.create_table_stmt_ != null);
                    tmp1 = this.create_table_stmt_.translate(vIrCollector);
                    res = new IR(kCreateStmt, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.create_index_stmt_ != null);
                    tmp1 = this.create_index_stmt_.translate(vIrCollector);
                    res = new IR(kCreateStmt, new IROperator("", "", ""), tmp1);
                    break;
                case 2:
                    assert (this.create_trigger_stmt_ != null);
                    tmp1 = this.create_trigger_stmt_.translate(vIrCollector);
                    res = new IR(kCreateStmt, new IROperator("", "", ""), tmp1);
                    break;
                case 3:
                    assert (this.create_view_stmt_ != null);
                    tmp1 = this.create_view_stmt_.translate(vIrCollector);
                    res = new IR(kCreateStmt, new IROperator("", "", ""), tmp1);
                    break;
                default:
                    throw new AssertionError();
            }
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 4;

            switch (this.caseIdx) {
                case 0:
                    this.create_table_stmt_ = new CreateTableStmt();
                    this.create_table_stmt_.generate();
                    break;
                case 1:
                    this.create_index_stmt_ = new CreateIndexStmt();
                    this.create_index_stmt_.generate();
                    break;
                case 2:
                    this.create_trigger_stmt_ = new CreateTriggerStmt();
                    this.create_trigger_stmt_.generate();
                    break;
                case 3:
                    this.create_view_stmt_ = new CreateViewStmt();
                    this.create_view_stmt_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.create_index_stmt_ = null;
            this.create_table_stmt_ = null;
            this.create_trigger_stmt_ = null;
            this.create_view_stmt_ = null;
        }
    }

    class DropStmt extends Node {
        DropTableStmt drop_table_stmt_;
        DropViewStmt drop_view_stmt_;
        DropIndexStmt drop_index_stmt_;
        DropTriggerStmt drop_trigger_stmt_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.drop_index_stmt_ != null);
                    tmp1 = this.drop_index_stmt_.translate(vIrCollector);
                    res = new IR(kCreateStmt, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.drop_table_stmt_ != null);
                    tmp1 = this.drop_table_stmt_.translate(vIrCollector);
                    res = new IR(kCreateStmt, new IROperator("", "", ""), tmp1);
                    break;
                case 2:
                    assert (this.drop_trigger_stmt_ != null);
                    tmp1 = this.drop_trigger_stmt_.translate(vIrCollector);
                    res = new IR(kCreateStmt, new IROperator("", "", ""), tmp1);
                    break;
                case 3:
                    assert (this.drop_view_stmt_ != null);
                    tmp1 = this.drop_view_stmt_.translate(vIrCollector);
                    res = new IR(kCreateStmt, new IROperator("", "", ""), tmp1);
                    break;
                default:
                    throw new AssertionError();
            }
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 4;

            switch (this.caseIdx) {
                case 0:
                    this.drop_index_stmt_ = new DropIndexStmt();
                    this.drop_index_stmt_.generate();
                    break;
                case 1:
                    this.drop_table_stmt_ = new DropTableStmt();
                    this.drop_table_stmt_.generate();
                    break;
                case 2:
                    this.drop_trigger_stmt_ = new DropTriggerStmt();
                    this.drop_trigger_stmt_.generate();
                    break;
                case 3:
                    this.drop_view_stmt_ = new DropViewStmt();
                    this.drop_view_stmt_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.drop_table_stmt_ = null;
            this.drop_view_stmt_ = null;
            this.drop_index_stmt_ = null;
            this.drop_trigger_stmt_ = null;
        }
    }

    class AlterStmt extends Node {
        AlterAction alter_action_;
        TableName table_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            assert (this.table_name_ != null);
            IR tmp1 = this.table_name_.translate(vIrCollector);
            assert (this.alter_action_ != null);
            IR tmp2 = this.alter_action_.translate(vIrCollector);

            res = new IR(kAlterStmt, new IROperator("ALTER TABLE", "", ""), tmp1, tmp2);
            return res;
        }

        @Override
        public void generate() {
            this.table_name_ = new TableName();
            this.table_name_.generate();
            this.alter_action_ = new AlterAction();
            this.alter_action_.generate();
        }

        @Override
        public void deepDelete() {
            this.alter_action_ = null;
            this.table_name_ = null;
        }
    }

    class SelectStmt extends Node {
        SelectNoParens select_no_parens_;
        SelectWithParens select_with_parens_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.select_no_parens_ != null);
                    tmp1 = this.select_no_parens_.translate(vIrCollector);
                    res = new IR(kSelectStmt, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.select_with_parens_ != null);
                    tmp1 = this.select_with_parens_.translate(vIrCollector);
                    res = new IR(kSelectStmt, new IROperator("", "", ""), tmp1);
                    break;
                default:
                    throw new AssertionError();
            }
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.select_no_parens_ = new SelectNoParens();
                    this.select_no_parens_.generate();
                    break;
                case 1:
                    this.select_with_parens_ = new SelectWithParens();
                    this.select_with_parens_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.select_no_parens_ = null;
            this.select_with_parens_ = null;
        }
    }

    class SelectWithParens extends Node {

        SelectNoParens select_no_parens_;
        SelectWithParens select_with_parens_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.select_no_parens_ != null);
                    tmp1 = this.select_no_parens_.translate(vIrCollector);
                    res = new IR(kSelectWithParens, new IROperator("(", ")", ""), tmp1);
                    break;
                case 1:
                    assert (this.select_with_parens_ != null);
                    tmp1 = this.select_with_parens_.translate(vIrCollector);
                    res = new IR(kSelectWithParens, new IROperator("(", ")", ""), tmp1);
                    break;
                default:
                    throw new AssertionError();
            }
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 200;
            switch (this.caseIdx) {
                case 0:
                    this.select_no_parens_ = new SelectNoParens();
                    this.select_no_parens_.generate();
                    break;
                case 1:
                    this.select_with_parens_ = new SelectWithParens();
                    this.select_with_parens_.generate();
                    break;
                default:
                    this.select_no_parens_ = new SelectNoParens();
                    this.select_no_parens_.generate();
                    this.caseIdx = 0;
                    break;
            }
        }

        @Override
        public void deepDelete() {
            this.select_no_parens_ = null;
            this.select_with_parens_ = null;
        }
    }

    class SelectNoParens extends Node {
        SelectClauseList select_clause_list_;
        OptWithClause opt_with_clause_;
        OptOrderClause opt_order_clause_;
        OptLimitClause opt_limit_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            assert this.opt_with_clause_ != null;
            IR tmp1 = this.opt_with_clause_.translate(vIrCollector);
            assert this.select_clause_list_ != null;
            IR tmp2 = this.select_clause_list_.translate(vIrCollector);
            assert this.opt_order_clause_ != null;
            IR tmp3 = this.opt_order_clause_.translate(vIrCollector);
            assert this.opt_limit_clause_ != null;
            IR tmp4 = this.opt_limit_clause_.translate(vIrCollector);

            IR tmp5 = new IR(kUnknown, new IROperator("", "", ""), tmp1, tmp2);
            vIrCollector.add(tmp5);
            IR tmp6 = new IR(kUnknown, new IROperator("", "", ""), tmp5, tmp3);
            vIrCollector.add(tmp6);
            IR res = new IR(kSelectNoParens, new IROperator("", "", ""), tmp6, tmp4);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.opt_with_clause_ = new OptWithClause();
            this.opt_with_clause_.generate();
            this.select_clause_list_ = new SelectClauseList();
            this.select_clause_list_.generate();
            this.opt_order_clause_ = new OptOrderClause();
            this.opt_order_clause_.generate();
            this.opt_limit_clause_ = new OptLimitClause();
            this.opt_limit_clause_.generate();
        }

        @Override
        public void deepDelete() {
            this.select_clause_list_ = null;
            this.opt_with_clause_ = null;
            this.opt_order_clause_ = null;
            this.opt_limit_clause_ = null;
        }
    }

    class SelectClauseList extends Node {
        SelectClauseList select_clause_list_;
        CombineClause combine_clause_;
        SelectClause select_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res = null;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.select_clause_ != null);
                    tmp1 = this.select_clause_.translate(vIrCollector);
                    res = new IR(kSelectClause, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.select_clause_ != null);
                    tmp1 = this.select_clause_.translate(vIrCollector);
                    assert (this.combine_clause_ != null);
                    IR tmp2 = this.combine_clause_.translate(vIrCollector);
                    assert (this.select_clause_list_ != null);
                    IR tmp3 = this.select_clause_list_.translate(vIrCollector);
                    IR tmp4 = new IR(kSelectClause, new IROperator("", "", ""), tmp1, tmp2);
                    vIrCollector.add(tmp4);
                    res = new IR(kSelectClauseList, new IROperator("", "", ""), tmp4, tmp3);
                    break;
                default:
                    throw new AssertionError();
            }
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 200;
            switch (this.caseIdx) {
                case 0:
                    this.select_clause_ = new SelectClause();
                    this.select_clause_.generate();
                    break;
                case 1:
                    this.select_clause_ = new SelectClause();
                    this.select_clause_.generate();
                    this.combine_clause_ = new CombineClause();
                    this.combine_clause_.generate();
                    this.select_clause_list_ = new SelectClauseList();
                    this.select_clause_list_.generate();
                    break;
                default:
                    this.select_clause_ = new SelectClause();
                    this.select_clause_.generate();
                    this.caseIdx = 0;
                    break;
            }
        }

        @Override
        public void deepDelete() {
            this.select_clause_list_ = null;
            this.combine_clause_ = null;
            this.select_clause_ = null;
        }
    }

    class SelectClause extends Node {
        OptGroupClause opt_group_clause_;
        OptAllOrDistinct opt_all_or_distinct_;
        OptFromClause opt_from_clause_;
        OptWindowClause opt_window_clause_;
        SelectTarget select_target_;
        OptWhereClause opt_where_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            assert (this.opt_all_or_distinct_ != null);
            IR tmp1 = this.opt_all_or_distinct_.translate(vIrCollector);
            assert (this.select_target_ != null);
            IR tmp2 = this.select_target_.translate(vIrCollector);
            assert (this.opt_from_clause_ != null);
            IR tmp3 = this.opt_from_clause_.translate(vIrCollector);
            assert (this.opt_where_clause_ != null);
            IR tmp4 = this.opt_where_clause_.translate(vIrCollector);
            assert (this.opt_group_clause_ != null);
            IR tmp5 = this.opt_group_clause_.translate(vIrCollector);
            assert (this.opt_window_clause_ != null);
            IR tmp6 = this.opt_window_clause_.translate(vIrCollector);

            IR tmp7 = new IR(kUnknown, new IROperator("SELECT", "", ""), tmp1, tmp2);
            vIrCollector.add(tmp7);
            IR tmp8 = new IR(kUnknown, new IROperator("", "", ""), tmp7, tmp3);
            vIrCollector.add(tmp8);
            IR tmp9 = new IR(kUnknown, new IROperator("", "", ""), tmp8, tmp4);
            vIrCollector.add(tmp9);
            IR tmp10 = new IR(kUnknown, new IROperator("", "", ""), tmp9, tmp5);
            vIrCollector.add(tmp10);
            IR res = new IR(kSelectClause, new IROperator("", "", ""), tmp10, tmp6);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.opt_group_clause_ = new OptGroupClause();
            this.opt_group_clause_.generate();
            this.opt_all_or_distinct_ = new OptAllOrDistinct();
            this.opt_all_or_distinct_.generate();
            this.opt_from_clause_ = new OptFromClause();
            this.opt_from_clause_.generate();
            this.opt_window_clause_ = new OptWindowClause();
            this.opt_window_clause_.generate();
            this.select_target_ = new SelectTarget();
            this.select_target_.generate();
            this.opt_where_clause_ = new OptWhereClause();
            this.opt_where_clause_.generate();
        }

        @Override
        public void deepDelete() {
            this.opt_group_clause_ = null;
            this.opt_all_or_distinct_ = null;
            this.opt_from_clause_ = null;
            this.opt_window_clause_ = null;
            this.select_target_ = null;
            this.opt_where_clause_ = null;
        }
    }

    class CombineClause extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res = null;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kCombineClause, new IROperator("UNION", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kCombineClause, new IROperator("INTERSECT", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kCombineClause, new IROperator("EXCEPT", "", ""), null, null);
                    break;
                default:
                    throw new AssertionError();
            }
            return res;
        }

        @Override
        public void generate() {
            //TODO:这里代码获取存在问题
            this.caseIdx = random.nextInt() % 3;
            switch (this.caseIdx) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
        }
    }

    class OptFromClause extends Node {


        FromClause from_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class SelectTarget extends Node {


        ExprList expr_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptWindowClause extends Node {


        WindowClause window_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class WindowClause extends Node {


        WindowDefList window_def_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class WindowDefList extends Node {


        WindowDef window_def_;
        WindowDefList window_def_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class WindowDef extends Node {


        Window window_;
        WindowName window_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class WindowName extends Node {


        Identifier identifier_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class Window extends Node {


        OptExistWindowName opt_exist_window_name_;
        OptFrameClause opt_frame_clause_;
        OptPartition opt_partition_;
        OptOrderClause opt_order_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptPartition extends Node {


        ExprList expr_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptFrameClause extends Node {


        FrameBoundStart frame_bound_start_;
        RangeOrRows range_or_rows_;
        FrameBoundEnd frame_bound_end_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class RangeOrRows extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class FrameBoundStart extends Node {


        FrameBound frame_bound_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class FrameBoundEnd extends Node {


        FrameBound frame_bound_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class FrameBound extends Node {


        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptExistWindowName extends Node {


        Identifier identifier_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptGroupClause extends Node {


        ExprList expr_list_;
        OptHavingClause opt_having_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptHavingClause extends Node {


        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptWhereClause extends Node {


        WhereClause where_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class WhereClause extends Node {


        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class FromClause extends Node {


        TableRef table_ref_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class TableRef extends Node {


        OptAsAlias opt_as_alias_;
        OptTablePrefix opt_table_prefix_;
        ExprList expr_list_;
        OptIndex opt_index_;
        OptOn opt_on_;
        SelectNoParens select_no_parens_;
        OptUsing opt_using_;
        TableName table_name_;
        TableRef table_ref_;
        FunctionName function_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptIndex extends Node {


        ColumnName column_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptOn extends Node {


        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptUsing extends Node {


        ColumnNameList column_name_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ColumnNameList extends Node {


        ColumnNameList column_name_list_;
        ColumnName column_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptTablePrefix extends Node {


        JoinOp join_op_;
        TableRef table_ref_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class JoinOp extends Node {


        OptJoinType opt_join_type_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptJoinType extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ExprList extends Node {


        Expr expr_;
        OptAsAlias opt_as_alias_;
        ExprList expr_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptLimitClause extends Node {


        LimitClause limit_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class LimitClause extends Node {


        Expr expr_1_;
        Expr expr_2_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptLimitRowCount extends Node {


        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptOrderClause extends Node {


        OrderItemList order_item_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptOrderNulls extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OrderItemList extends Node {


        OrderItem order_item_;
        OrderItemList order_item_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OrderItem extends Node {


        Expr expr_;
        OptOrderNulls opt_order_nulls_;
        OptOrderBehavior opt_order_behavior_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptOrderBehavior extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptWithClause extends Node {


        CteTableList cte_table_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CteTableList extends Node {


        CteTableList cte_table_list_;
        CteTable cte_table_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CteTable extends Node {


        CteTableName cte_table_name_;
        SelectStmt select_stmt_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CteTableName extends Node {


        OptColumnNameListP opt_column_name_list_p_;
        TableName table_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptAllOrDistinct extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CreateTableStmt extends Node {


        OptTableOptionList opt_table_option_list_;
        OptTableConstraintList opt_table_constraint_list_;
        OptTemp opt_temp_;
        SelectStmt select_stmt_;
        TableName table_name_;
        ColumnDefList column_def_list_;
        OptIgnoreOrReplace opt_ignore_or_replace_;
        OptIfNotExist opt_if_not_exist_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CreateIndexStmt extends Node {


        TableName table_name_1_;
        TableName table_name_2_;
        OptIndexOption opt_index_option_;
        OptIndexKeyword opt_index_keyword_;
        IndexedColumnList indexed_column_list_;
        OptExtraOption opt_extra_option_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CreateTriggerStmt extends Node {


        TriggerActionTime trigger_action_time_;
        TriggerName trigger_name_;
        TableName table_name_;
        TriggerEvents trigger_events_;
        TriggerBody trigger_body_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CreateViewStmt extends Node {


        OptViewAlgorithm opt_view_algorithm_;
        OptCheckOption opt_check_option_;
        OptSqlSecurity opt_sql_security_;
        ViewName view_name_;
        SelectStmt select_stmt_;
        OptColumnNameListP opt_column_name_list_p_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptTableOptionList extends Node {


        TableOptionList table_option_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class TableOptionList extends Node {


        TableOptionList table_option_list_;
        OptOpComma opt_op_comma_;
        TableOption table_option_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class TableOption extends Node {


        OptOpEqual opt_op_equal_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptOpComma extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptIgnoreOrReplace extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptViewAlgorithm extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptSqlSecurity extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptIndexOption extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptExtraOption extends Node {


        LockOption lock_option_;
        IndexAlgorithmOption index_algorithm_option_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class IndexAlgorithmOption extends Node {


        OptOpEqual opt_op_equal_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class LockOption extends Node {


        OptOpEqual opt_op_equal_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptOpEqual extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class TriggerEvents extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class TriggerName extends Node {


        Identifier identifier_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class TriggerActionTime extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class DropIndexStmt extends Node {


        TableName table_name_;
        OptExtraOption opt_extra_option_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class DropTableStmt extends Node {


        OptTemp opt_temp_;
        TableName table_name_;
        OptRestrictOrCascade opt_restrict_or_cascade_;
        OptIfExist opt_if_exist_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptRestrictOrCascade extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class DropTriggerStmt extends Node {


        TriggerName trigger_name_;
        OptIfExist opt_if_exist_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class DropViewStmt extends Node {


        ViewName view_name_;
        OptRestrictOrCascade opt_restrict_or_cascade_;
        OptIfExist opt_if_exist_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class InsertStmt extends Node {


        InsertRest insert_rest_;
        OptAsAlias opt_as_alias_;
        TableName table_name_;
        OptOnConflict opt_on_conflict_;
        OptWithClause opt_with_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class InsertRest extends Node {


        OptColumnNameListP opt_column_name_list_p_;
        SuperValuesList super_values_list_;
        SelectNoParens select_no_parens_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class SuperValuesList extends Node {


        ValuesList values_list_;
        SuperValuesList super_values_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ValuesList extends Node {


        ExprList expr_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptOnConflict extends Node {


        OptConflictExpr opt_conflict_expr_;
        SetClauseList set_clause_list_;
        WhereClause where_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptConflictExpr extends Node {


        IndexedColumnList indexed_column_list_;
        WhereClause where_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class IndexedColumnList extends Node {


        IndexedColumn indexed_column_;
        IndexedColumnList indexed_column_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class IndexedColumn extends Node {


        Expr expr_;
        OptOrderBehavior opt_order_behavior_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class UpdateStmt extends Node {


        OptAsAlias opt_as_alias_;
        OptWhereClause opt_where_clause_;
        TableName table_name_;
        SetClauseList set_clause_list_;
        OptLimitRowCount opt_limit_row_count_;
        OptOrderClause opt_order_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class AlterAction extends Node {


        ColumnDef column_def_;
        AlterConstantAction alter_constant_action_;
        OptColumn opt_column_;
        TableName table_name_;
        ColumnName column_name_1_;
        ColumnName column_name_2_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class AlterConstantAction extends Node {


        LockOption lock_option_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ColumnDefList extends Node {


        ColumnDef column_def_;
        ColumnDefList column_def_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ColumnDef extends Node {


        TypeName type_name_;
        Identifier identifier_;
        OptColumnConstraintList opt_column_constraint_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptColumnConstraintList extends Node {


        ColumnConstraintList column_constraint_list_;
        OptReferenceClause opt_reference_clause_;
        OptCheck opt_check_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ColumnConstraintList extends Node {


        ColumnConstraintList column_constraint_list_;
        ColumnConstraint column_constraint_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ColumnConstraint extends Node {


        ConstraintType constraint_type_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptReferenceClause extends Node {


        ReferenceClause reference_clause_;
        OptForeignKey opt_foreign_key_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptCheck extends Node {


        OptEnforced opt_enforced_;
        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ConstraintType extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ReferenceClause extends Node {


        OptConstraintAttributeSpec opt_constraint_attribute_spec_;
        OptColumnNameListP opt_column_name_list_p_;
        TableName table_name_;
        OptForeignKeyActions opt_foreign_key_actions_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptForeignKey extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptForeignKeyActions extends Node {


        ForeignKeyActions foreign_key_actions_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ForeignKeyActions extends Node {


        KeyActions key_actions_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class KeyActions extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptConstraintAttributeSpec extends Node {


        OptInitialTime opt_initial_time_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptInitialTime extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ConstraintName extends Node {


        Name name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptTemp extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptCheckOption extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptColumnNameListP extends Node {


        ColumnNameList column_name_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class SetClauseList extends Node {


        SetClause set_clause_;
        SetClauseList set_clause_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class SetClause extends Node {


        Expr expr_;
        ColumnNameList column_name_list_;
        ColumnName column_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptAsAlias extends Node {


        AsAlias as_alias_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class Expr extends Node {


        CastExpr cast_expr_;
        InExpr in_expr_;
        BetweenExpr between_expr_;
        Operand operand_;
        ExistsExpr exists_expr_;
        LogicExpr logic_expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class Operand extends Node {


        FunctionExpr function_expr_;
        UnaryExpr unary_expr_;
        ExprList expr_list_;
        CaseExpr case_expr_;
        ArrayExpr array_expr_;
        SelectNoParens select_no_parens_;
        ExtractExpr extract_expr_;
        ArrayIndex array_index_;
        BinaryExpr binary_expr_;
        ScalarExpr scalar_expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CastExpr extends Node {


        Expr expr_;
        TypeName type_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ScalarExpr extends Node {


        Literal literal_;
        ColumnName column_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class UnaryExpr extends Node {


        Operand operand_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class BinaryExpr extends Node {


        Operand operand_1_;
        Operand operand_2_;
        BinaryOp binary_op_;
        CompExpr comp_expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class LogicExpr extends Node {


        Expr expr_1_;
        Expr expr_2_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class InExpr extends Node {


        Operand operand_;
        ExprList expr_list_;
        OptNot opt_not_;
        TableName table_name_;
        SelectNoParens select_no_parens_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CaseExpr extends Node {


        Expr expr_1_;
        Expr expr_2_;
        CaseList case_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class BetweenExpr extends Node {


        Operand operand_1_;
        Operand operand_2_;
        Operand operand_3_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ExistsExpr extends Node {


        OptNot opt_not_;
        SelectNoParens select_no_parens_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class FunctionExpr extends Node {


        ExprList expr_list_;
        FunctionName function_name_;
        OptFilterClause opt_filter_clause_;
        OptDistinct opt_distinct_;
        OptOverClause opt_over_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptDistinct extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptFilterClause extends Node {


        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptOverClause extends Node {


        Window window_;
        WindowName window_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CaseList extends Node {


        CaseList case_list_;
        CaseClause case_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CaseClause extends Node {


        Expr expr_1_;
        Expr expr_2_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CompExpr extends Node {


        Operand operand_1_;
        Operand operand_2_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ExtractExpr extends Node {


        DatetimeField datetime_field_;
        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class DatetimeField extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ArrayExpr extends Node {


        ExprList expr_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ArrayIndex extends Node {


        Operand operand_;
        IntLiteral int_literal_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class Literal extends Node {


        BoolLiteral bool_literal_;
        StringLiteral string_literal_;
        NumLiteral num_literal_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class StringLiteral extends Node {


        String string_val_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class BoolLiteral extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class NumLiteral extends Node {


        IntLiteral int_literal_;
        FloatLiteral float_literal_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class IntLiteral extends Node {


        int int_val_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class FloatLiteral extends Node {


        float float_val_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptColumn extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class TriggerBody extends Node {


        AlterStmt alter_stmt_;
        DropStmt drop_stmt_;
        InsertStmt insert_stmt_;
        UpdateStmt update_stmt_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptIfNotExist extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptIfExist extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class Identifier extends Node {


        String string_val_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class AsAlias extends Node {


        Identifier identifier_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class TableName extends Node {


        Identifier identifier_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ColumnName extends Node {


        Identifier identifier_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptIndexKeyword extends Node {


        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class ViewName extends Node {


        Identifier identifier_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class FunctionName extends Node {


        Identifier identifier_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class BinaryOp extends Node {

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptNot extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class Name extends Node {
        Identifier identifier_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class TypeName extends Node {
        NumericType numeric_type_;
        CharacterType character_type_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CharacterType extends Node {
        CharacterWithLength character_with_length_;
        CharacterWithoutLength character_without_length_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CharacterWithLength extends Node {
        CharacterConflicta character_conflicta_;
        IntLiteral int_literal_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CharacterWithoutLength extends Node {
        CharacterConflicta character_conflicta_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class CharacterConflicta extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class NumericType extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptTableConstraintList extends Node {
        TableConstraintList table_constraint_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class TableConstraintList extends Node {
        TableConstraint table_constraint_;
        TableConstraintList table_constraint_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class TableConstraint extends Node {
        IndexedColumnList indexed_column_list_;
        ColumnNameList column_name_list_;
        Expr expr_;
        ConstraintName constraint_name_;
        OptEnforced opt_enforced_;
        ReferenceClause reference_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }

    class OptEnforced extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            return null;
        }

        @Override
        public void generate() {

        }

        @Override
        public void deepDelete() {

        }
    }
}
