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
            vIrCollector.add(res);
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
            vIrCollector.add(res);
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
            vIrCollector.add(res);
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
            vIrCollector.add(res);
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
            vIrCollector.add(res);
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
            vIrCollector.add(res);
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
            vIrCollector.add(res);
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
            vIrCollector.add(res);
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
            IR res;
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
            vIrCollector.add(res);
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
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.from_clause_ != null);
                    res = new IR(kOptFromClause, new IROperator("", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptFromClause, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.from_clause_ = new FromClause();
                    this.from_clause_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.from_clause_ = null;
        }
    }

    class SelectTarget extends Node {
        ExprList expr_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            assert (this.expr_list_ != null);
            IR tmp1 = this.expr_list_.translate(vIrCollector);
            res = new IR(kSelectTarget, new IROperator("", "", ""), tmp1);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = 0;
            this.expr_list_ = new ExprList();
            this.expr_list_.generate();
        }

        @Override
        public void deepDelete() {
            this.expr_list_ = null;
        }
    }

    class OptWindowClause extends Node {
        WindowClause window_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.window_clause_ != null);
                    IR tmp1 = this.window_clause_.translate(vIrCollector);
                    res = new IR(kOptWindowClause, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptWindowClause, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.window_clause_ = new WindowClause();
                    this.window_clause_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.window_clause_ = null;
        }
    }

    class WindowClause extends Node {
        WindowDefList window_def_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;

            assert (this.window_def_list_ != null);
            IR tmp1 = this.window_def_list_.translate(vIrCollector);
            res = new IR(kWindowClause, new IROperator("WINDOW", "", ""), tmp1);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.window_def_list_ = new WindowDefList();
            this.window_def_list_.generate();
        }

        @Override
        public void deepDelete() {
            this.window_def_list_ = null;
        }
    }

    class WindowDefList extends Node {
        WindowDef window_def_;
        WindowDefList window_def_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.window_def_ != null);
                    tmp1 = this.window_def_.translate(vIrCollector);
                    res = new IR(kWindowDef, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.window_def_ != null);
                    tmp1 = this.window_def_.translate(vIrCollector);
                    assert (this.window_def_list_ != null);
                    IR tmp2 = this.window_def_list_.translate(vIrCollector);
                    res = new IR(kWindowDefList, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 200;
            switch (this.caseIdx) {
                case 0:
                    this.window_def_ = new WindowDef();
                    this.window_def_.generate();
                    break;
                case 1:
                    this.window_def_ = new WindowDef();
                    this.window_def_.generate();
                    this.window_def_list_ = new WindowDefList();
                    this.window_def_list_.generate();
                    break;
                default:
                    this.caseIdx = 0;
                    this.window_def_ = new WindowDef();
                    this.window_def_.generate();
            }
        }

        @Override
        public void deepDelete() {
            this.window_def_ = null;
            this.window_def_list_ = null;
        }
    }

    class WindowDef extends Node {
        Window window_;
        WindowName window_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            assert (this.window_ != null);
            IR tmp1 = this.window_.translate(vIrCollector);
            assert (this.window_name_ != null);
            IR tmp2 = this.window_name_.translate(vIrCollector);
            res = new IR(kWindowDef, new IROperator("", "AS(", ")"), tmp1, tmp2);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.window_ = new Window();
            this.window_.generate();
            this.window_name_ = new WindowName();
            this.window_name_.generate();
        }

        @Override
        public void deepDelete() {
            this.window_ = null;
            this.window_name_ = null;
        }
    }

    class WindowName extends Node {
        Identifier identifier_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            assert (this.identifier_ != null);
            IR tmp1 = this.identifier_.translate(vIrCollector);
            res = new IR(kWindowName, new IROperator("", "", ""), tmp1);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.identifier_ = new Identifier();
            this.identifier_.generate();
        }

        @Override
        public void deepDelete() {
            this.identifier_ = null;
        }
    }

    class Window extends Node {
        OptExistWindowName opt_exist_window_name_;
        OptFrameClause opt_frame_clause_;
        OptPartition opt_partition_;
        OptOrderClause opt_order_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            assert (this.opt_exist_window_name_ != null);
            IR tmp1 = this.opt_exist_window_name_.translate(vIrCollector);
            assert (this.opt_partition_ != null);
            IR tmp2 = this.opt_partition_.translate(vIrCollector);
            assert (this.opt_order_clause_ != null);
            IR tmp3 = this.opt_order_clause_.translate(vIrCollector);
            assert (this.opt_frame_clause_ != null);
            IR tmp4 = this.opt_frame_clause_.translate(vIrCollector);
            IR tmp5 = new IR(kUnknown, new IROperator("", "", ""), tmp1, tmp2);
            vIrCollector.add(tmp5);
            IR tmp6 = new IR(kUnknown, new IROperator("", "", ""), tmp5, tmp3);
            vIrCollector.add(tmp6);
            res = new IR(kWindow, new IROperator("", "", ""), tmp6, tmp4);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.opt_exist_window_name_ = new OptExistWindowName();
            this.opt_exist_window_name_.generate();
            this.opt_partition_ = new OptPartition();
            this.opt_partition_.generate();
            this.opt_order_clause_ = new OptOrderClause();
            this.opt_order_clause_.generate();
            this.opt_frame_clause_ = new OptFrameClause();
            this.opt_frame_clause_.generate();
        }

        @Override
        public void deepDelete() {
            this.opt_exist_window_name_ = null;
            this.opt_partition_ = null;
            this.opt_order_clause_ = null;
            this.opt_frame_clause_ = null;
        }
    }

    class OptPartition extends Node {
        ExprList expr_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.expr_list_ != null);
                    IR tmp1 = this.expr_list_.translate(vIrCollector);
                    res = new IR(kOptPartition, new IROperator("PARTITION BY", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptPartition, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.expr_list_ = new ExprList();
                    this.expr_list_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.expr_list_ = null;
        }
    }

    class OptFrameClause extends Node {
        FrameBoundStart frame_bound_start_;
        RangeOrRows range_or_rows_;
        FrameBoundEnd frame_bound_end_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.range_or_rows_ != null);
                    tmp1 = this.range_or_rows_.translate(vIrCollector);
                    assert (this.frame_bound_start_ != null);
                    tmp2 = this.frame_bound_start_.translate(vIrCollector);
                    res = new IR(kOptFrameClause, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                case 1:
                    assert (this.range_or_rows_ != null);
                    tmp1 = this.range_or_rows_.translate(vIrCollector);
                    assert (this.frame_bound_start_ != null);
                    tmp2 = this.frame_bound_start_.translate(vIrCollector);
                    assert (this.frame_bound_end_ != null);
                    tmp3 = this.frame_bound_end_.translate(vIrCollector);
                    IR tmp4 = new IR(kUnknown, new IROperator("", "BETWEEN", "AND"), tmp1, tmp2);
                    vIrCollector.add(tmp4);
                    res = new IR(kOptFrameClause, new IROperator("", "", ""), tmp4, tmp3);
                    break;
                case 2:
                    res = new IR(kOptFrameClause, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.range_or_rows_ = new RangeOrRows();
                    this.range_or_rows_.generate();
                    this.frame_bound_start_ = new FrameBoundStart();
                    this.frame_bound_start_.generate();
                    break;
                case 1:
                    this.range_or_rows_ = new RangeOrRows();
                    this.range_or_rows_.generate();
                    this.frame_bound_start_ = new FrameBoundStart();
                    this.frame_bound_start_.generate();
                    this.frame_bound_end_ = new FrameBoundEnd();
                    this.frame_bound_end_.generate();
                    break;
                case 2:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.frame_bound_start_ = null;
            this.range_or_rows_ = null;
            this.frame_bound_end_ = null;
        }
    }

    class RangeOrRows extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kRangeOrRows, new IROperator("RANGE", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kRangeOrRows, new IROperator("ROWS", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kRangeOrRows, new IROperator("GROUPS", "", ""), null, null);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
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

    class FrameBoundStart extends Node {
        FrameBound frame_bound_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.frame_bound_ != null);
                    IR tmp1 = this.frame_bound_.translate(vIrCollector);
                    res = new IR(kFrameBoundStart, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kFrameBoundStart, new IROperator("UNBOUNDED PRECEDING", "", ""), null, null);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.frame_bound_ = new FrameBound();
                    this.frame_bound_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.frame_bound_ = null;
        }
    }

    class FrameBoundEnd extends Node {
        FrameBound frame_bound_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.frame_bound_ != null);
                    IR tmp1 = this.frame_bound_.translate(vIrCollector);
                    res = new IR(kFrameBoundEnd, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kFrameBoundEnd, new IROperator("UNBOUNDED FOLLOWING", "", ""), null, null);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.frame_bound_ = new FrameBound();
                    this.frame_bound_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.frame_bound_ = null;
        }
    }

    class FrameBound extends Node {
        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.expr_ != null);
                    tmp1 = this.expr_.translate(vIrCollector);
                    res = new IR(kFrameBound, new IROperator("", "PRECEDING", ""), tmp1);
                    break;
                case 1:
                    tmp1 = this.expr_.translate(vIrCollector);
                    res = new IR(kFrameBound, new IROperator("", "FOLLOWING", ""), tmp1);
                    break;
                case 2:
                    res = new IR(kFrameBound, new IROperator("", "CURRENT ROW", ""), null, null);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.expr_ = new Expr();
                    this.expr_.generate();
                    break;
                case 1:
                    this.expr_ = new Expr();
                    this.expr_.generate();
                    break;
                case 2:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.expr_ = null;
        }
    }

    class OptExistWindowName extends Node {
        Identifier identifier_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.identifier_ != null);
                    tmp1 = this.identifier_.translate(vIrCollector);
                    res = new IR(kOptExistWindowName, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptExistWindowName, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.identifier_ = new Identifier();
                    this.identifier_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.identifier_ = null;
        }
    }

    class OptGroupClause extends Node {
        ExprList expr_list_;
        OptHavingClause opt_having_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.expr_list_ != null);
                    tmp1 = this.expr_list_.translate(vIrCollector);
                    assert (this.opt_having_clause_ != null);
                    tmp2 = this.opt_having_clause_.translate(vIrCollector);
                    res = new IR(kOptGroupClause, new IROperator("GROUP BY", "", ""), tmp1, tmp2);
                    break;
                case 1:
                    res = new IR(kOptGroupClause, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.expr_list_ = new ExprList();
                    this.expr_list_.generate();
                    this.opt_having_clause_ = new OptHavingClause();
                    this.opt_having_clause_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.expr_list_ = null;
            this.opt_having_clause_ = null;
        }
    }

    class OptHavingClause extends Node {
        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.expr_ != null);
                    IR tmp1 = this.expr_.translate(vIrCollector);
                    res = new IR(kOptHavingClause, new IROperator("HAVING", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptHavingClause, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.expr_ = new Expr();
                    this.expr_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.expr_ = null;
        }
    }

    class OptWhereClause extends Node {
        WhereClause where_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.where_clause_ != null);
                    IR tmp1 = this.where_clause_.translate(vIrCollector);
                    res = new IR(kOptWhereClause, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptWhereClause, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.where_clause_ = new WhereClause();
                    this.where_clause_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.where_clause_ = null;
        }
    }

    class WhereClause extends Node {
        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            assert (this.expr_ != null);
            IR tmp1 = this.expr_.translate(vIrCollector);
            res = new IR(kWhereClause, new IROperator("WHERE", "", ""), tmp1);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.expr_ = new Expr();
            this.expr_.generate();
        }

        @Override
        public void deepDelete() {
            this.expr_ = null;
        }
    }

    class FromClause extends Node {
        TableRef table_ref_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            assert (this.table_ref_ != null);
            IR tmp1 = this.table_ref_.translate(vIrCollector);
            res = new IR(kWhereClause, new IROperator("FROM", "", ""), tmp1);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.table_ref_ = new TableRef();
            this.table_ref_.generate();
        }

        @Override
        public void deepDelete() {
            this.table_ref_ = null;
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
            IR res = null;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;
            IR tmp5;
            IR tmp6;
            IR tmp7;
            IR tmp8;
            IR tmp9;
            IR tmp10;
            switch (this.caseIdx) {
                case 0:
                    assert (this.opt_table_prefix_ != null);
                    tmp1 = this.opt_table_prefix_.translate(vIrCollector);
                    assert (this.table_name_ != null);
                    tmp2 = this.table_name_.translate(vIrCollector);
                    assert (this.opt_as_alias_ != null);
                    tmp3 = this.opt_as_alias_.translate(vIrCollector);
                    assert (this.opt_index_ != null);
                    tmp4 = this.opt_index_.translate(vIrCollector);
                    assert (this.opt_on_ != null);
                    tmp5 = this.opt_on_.translate(vIrCollector);
                    assert (this.opt_using_ != null);
                    tmp6 = this.opt_using_.translate(vIrCollector);

                    tmp7 = new IR(kUnknown, new IROperator("", "", ""), tmp1, tmp2);
                    vIrCollector.add(tmp7);
                    tmp8 = new IR(kUnknown, new IROperator("", "", ""), tmp7, tmp3);
                    vIrCollector.add(tmp8);
                    tmp9 = new IR(kUnknown, new IROperator("", "", ""), tmp8, tmp4);
                    vIrCollector.add(tmp9);
                    tmp10 = new IR(kUnknown, new IROperator("", "", ""), tmp9, tmp5);
                    vIrCollector.add(tmp10);
                    res = new IR(kTableRef, new IROperator("", "", ""), tmp10, tmp6);
                    break;
                case 1:
                    assert (this.opt_table_prefix_ != null);
                    tmp1 = this.opt_table_prefix_.translate(vIrCollector);
                    assert (this.function_name_ != null);
                    tmp2 = this.function_name_.translate(vIrCollector);
                    assert (this.expr_list_ != null);
                    tmp3 = this.expr_list_.translate(vIrCollector);
                    assert (this.opt_as_alias_ != null);
                    tmp4 = this.opt_as_alias_.translate(vIrCollector);
                    assert (this.opt_on_ != null);
                    tmp5 = this.opt_on_.translate(vIrCollector);
                    assert (this.opt_using_ != null);
                    tmp6 = this.opt_using_.translate(vIrCollector);

                    tmp7 = new IR(kUnknown, new IROperator("", "", ""), tmp1, tmp2);
                    vIrCollector.add(tmp7);
                    tmp8 = new IR(kUnknown, new IROperator("", "", ""), tmp7, tmp3);
                    vIrCollector.add(tmp8);
                    tmp9 = new IR(kUnknown, new IROperator("", "", ""), tmp8, tmp4);
                    vIrCollector.add(tmp9);
                    tmp10 = new IR(kUnknown, new IROperator("", "", ""), tmp9, tmp5);
                    vIrCollector.add(tmp10);
                    res = new IR(kTableRef, new IROperator("", "", ""), tmp10, tmp6);
                    break;
                case 2:
                    assert (this.opt_table_prefix_ != null);
                    tmp1 = this.opt_table_prefix_.translate(vIrCollector);
                    assert (this.select_no_parens_ != null);
                    tmp2 = this.select_no_parens_.translate(vIrCollector);
                    assert (this.opt_as_alias_ != null);
                    tmp3 = this.opt_as_alias_.translate(vIrCollector);
                    assert (this.opt_on_ != null);
                    tmp4 = this.opt_on_.translate(vIrCollector);
                    assert (this.opt_using_ != null);
                    tmp5 = this.opt_using_.translate(vIrCollector);

                    tmp6 = new IR(kUnknown, new IROperator("", "", ""), tmp1, tmp2);
                    vIrCollector.add(tmp6);
                    tmp7 = new IR(kUnknown, new IROperator("", "", ""), tmp6, tmp3);
                    vIrCollector.add(tmp7);
                    tmp8 = new IR(kUnknown, new IROperator("", "", ""), tmp7, tmp4);
                    vIrCollector.add(tmp8);
                    res = new IR(kTableRef, new IROperator("", "", ""), tmp8, tmp5);
                    break;
                case 3:
                    assert (this.opt_table_prefix_ != null);
                    tmp1 = this.opt_table_prefix_.translate(vIrCollector);
                    assert (this.table_ref_ != null);
                    tmp2 = this.table_ref_.translate(vIrCollector);
                    assert (this.opt_as_alias_ != null);
                    tmp3 = this.opt_as_alias_.translate(vIrCollector);
                    assert (this.opt_on_ != null);
                    tmp4 = this.opt_on_.translate(vIrCollector);
                    assert (this.opt_using_ != null);
                    tmp5 = this.opt_using_.translate(vIrCollector);

                    tmp6 = new IR(kUnknown, new IROperator("", "", ""), tmp1, tmp2);
                    vIrCollector.add(tmp6);
                    tmp7 = new IR(kUnknown, new IROperator("", "", ""), tmp6, tmp3);
                    vIrCollector.add(tmp7);
                    tmp8 = new IR(kUnknown, new IROperator("", "", ""), tmp7, tmp4);
                    vIrCollector.add(tmp8);
                    res = new IR(kTableRef, new IROperator("", "", ""), tmp8, tmp5);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.opt_table_prefix_ = new OptTablePrefix();
                    this.opt_table_prefix_.generate();
                    this.table_name_ = new TableName();
                    this.table_name_.generate();
                    this.opt_as_alias_ = new OptAsAlias();
                    this.opt_as_alias_.generate();
                    this.opt_index_ = new OptIndex();
                    this.opt_index_.generate();
                    this.opt_on_ = new OptOn();
                    this.opt_on_.generate();
                    this.opt_using_ = new OptUsing();
                    this.opt_using_.generate();
                    break;
                case 1:
                    this.opt_table_prefix_ = new OptTablePrefix();
                    this.opt_table_prefix_.generate();
                    this.function_name_ = new FunctionName();
                    this.function_name_.generate();
                    this.opt_as_alias_ = new OptAsAlias();
                    this.opt_as_alias_.generate();
                    this.opt_index_ = new OptIndex();
                    this.opt_index_.generate();
                    this.opt_on_ = new OptOn();
                    this.opt_on_.generate();
                    this.opt_using_ = new OptUsing();
                    this.opt_using_.generate();
                    break;
                case 2:
                    this.opt_table_prefix_ = new OptTablePrefix();
                    this.opt_table_prefix_.generate();
                    this.select_no_parens_ = new SelectNoParens();
                    this.select_no_parens_.generate();
                    this.opt_as_alias_ = new OptAsAlias();
                    this.opt_as_alias_.generate();
                    this.opt_on_ = new OptOn();
                    this.opt_on_.generate();
                    this.opt_using_ = new OptUsing();
                    this.opt_using_.generate();
                    break;
                case 3:
                    this.opt_table_prefix_ = new OptTablePrefix();
                    this.opt_table_prefix_.generate();
                    this.table_ref_ = new TableRef();
                    this.table_ref_.generate();
                    this.opt_as_alias_ = new OptAsAlias();
                    this.opt_as_alias_.generate();
                    this.opt_on_ = new OptOn();
                    this.opt_on_.generate();
                    this.opt_using_ = new OptUsing();
                    this.opt_using_.generate();
                    break;
                default:
                    int tmp_case_idx = random.nextInt() % 3;
                    switch (tmp_case_idx) {
                        case 0:
                            this.opt_table_prefix_ = new OptTablePrefix();
                            this.opt_table_prefix_.generate();
                            this.table_name_ = new TableName();
                            this.table_name_.generate();
                            this.opt_as_alias_ = new OptAsAlias();
                            this.opt_as_alias_.generate();
                            this.opt_index_ = new OptIndex();
                            this.opt_index_.generate();
                            this.opt_on_ = new OptOn();
                            this.opt_on_.generate();
                            this.opt_using_ = new OptUsing();
                            this.opt_using_.generate();
                            this.caseIdx = 0;
                            break;
                        case 1:
                            this.opt_table_prefix_ = new OptTablePrefix();
                            this.opt_table_prefix_.generate();
                            this.function_name_ = new FunctionName();
                            this.function_name_.generate();
                            this.opt_as_alias_ = new OptAsAlias();
                            this.opt_as_alias_.generate();
                            this.opt_index_ = new OptIndex();
                            this.opt_index_.generate();
                            this.opt_on_ = new OptOn();
                            this.opt_on_.generate();
                            this.opt_using_ = new OptUsing();
                            this.opt_using_.generate();
                            this.caseIdx = 1;
                            break;
                        case 2:
                            this.opt_table_prefix_ = new OptTablePrefix();
                            this.opt_table_prefix_.generate();
                            this.select_no_parens_ = new SelectNoParens();
                            this.select_no_parens_.generate();
                            this.opt_as_alias_ = new OptAsAlias();
                            this.opt_as_alias_.generate();
                            this.opt_on_ = new OptOn();
                            this.opt_on_.generate();
                            this.opt_using_ = new OptUsing();
                            this.opt_using_.generate();
                            this.caseIdx = 2;
                            break;
                    }
            }
        }

        @Override
        public void deepDelete() {
            this.opt_as_alias_ = null;
            this.opt_table_prefix_ = null;
            this.expr_list_ = null;
            this.opt_index_ = null;
            this.opt_on_ = null;
            this.select_no_parens_ = null;
            this.opt_using_ = null;
            this.table_name_ = null;
            this.table_ref_ = null;
            this.function_name_ = null;
        }
    }

    class OptIndex extends Node {
        ColumnName column_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.column_name_ != null);
                    IR tmp1 = this.column_name_.translate(vIrCollector);
                    res = new IR(kOptIndex, new IROperator("INDEXED BY", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptIndex, new IROperator("NOT INDEXED", "", ""), null);
                    break;
                case 2:
                    res = new IR(kOptIndex, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
            switch (this.caseIdx) {
                case 0:
                    this.column_name_ = new ColumnName();
                    this.column_name_.generate();
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
            this.column_name_ = null;
        }
    }

    class OptOn extends Node {
        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.expr_ != null);
                    IR tmp1 = this.expr_.translate(vIrCollector);
                    res = new IR(kOptOn, new IROperator("ON", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptOn, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.expr_ = new Expr();
                    this.expr_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.expr_ = null;
        }
    }

    class OptUsing extends Node {
        ColumnNameList column_name_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.column_name_list_ != null);
                    IR tmp1 = this.column_name_list_.translate(vIrCollector);
                    res = new IR(kOptUsing, new IROperator("USING", "(", ")"), tmp1);
                    break;
                case 1:
                    res = new IR(kOptUsing, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.column_name_list_ = new ColumnNameList();
                    this.column_name_list_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.column_name_list_ = null;
        }
    }

    class ColumnNameList extends Node {
        ColumnNameList column_name_list_;
        ColumnName column_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.column_name_ != null);
                    tmp1 = this.column_name_.translate(vIrCollector);
                    res = new IR(kColumnNameList, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.column_name_ != null);
                    tmp1 = this.column_name_.translate(vIrCollector);
                    assert (this.column_name_list_ != null);
                    tmp2 = this.column_name_list_.translate(vIrCollector);
                    res = new IR(kColumnNameList, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.column_name_ = new ColumnName();
                    this.column_name_.generate();
                    break;
                case 1:
                    this.column_name_ = new ColumnName();
                    this.column_name_.generate();
                    this.column_name_list_ = new ColumnNameList();
                    this.column_name_list_.generate();
                    break;
                default:
                    int tmp_case_idx = 0;
                    this.column_name_ = new ColumnName();
                    this.column_name_.generate();
                    this.caseIdx = 0;
                    break;
            }
        }

        @Override
        public void deepDelete() {
            this.column_name_list_ = null;
            this.column_name_ = null;
        }
    }

    class OptTablePrefix extends Node {
        JoinOp join_op_;
        TableRef table_ref_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.table_ref_ != null);
                    IR tmp1 = this.table_ref_.translate(vIrCollector);
                    assert (this.join_op_ != null);
                    IR tmp2 = this.join_op_.translate(vIrCollector);
                    res = new IR(kOptTablePrefix, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                case 1:
                    res = new IR(kOptTablePrefix, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.table_ref_ = new TableRef();
                    this.table_ref_.generate();
                    this.join_op_ = new JoinOp();
                    this.join_op_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.table_ref_ = null;
            this.join_op_ = null;
        }
    }

    class JoinOp extends Node {
        OptJoinType opt_join_type_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kJoinOp, new IROperator("", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kJoinOp, new IROperator("JOIN", "", ""), null, null);
                    break;
                case 2:
                    assert (this.opt_join_type_ != null);
                    IR tmp1 = this.opt_join_type_.translate(vIrCollector);
                    res = new IR(kJoinOp, new IROperator("NATURAL", "JOIN", ""), tmp1);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
            switch (this.caseIdx) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    this.opt_join_type_ = new OptJoinType();
                    this.opt_join_type_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.opt_join_type_ = null;
        }
    }

    class OptJoinType extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptJoinType, new IROperator("LEFT", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptJoinType, new IROperator("LEFT OUTER", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kOptJoinType, new IROperator("INNER", "", ""), null, null);
                    break;
                case 3:
                    res = new IR(kOptJoinType, new IROperator("CROSS", "", ""), null, null);
                    break;
                case 4:
                    res = new IR(kOptJoinType, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 5;
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
            IR res;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;
            switch (this.caseIdx) {
                case 0:
                    assert (this.expr_ != null);
                    tmp1 = this.expr_.translate(vIrCollector);
                    assert (this.opt_as_alias_ != null);
                    tmp2 = this.opt_as_alias_.translate(vIrCollector);
                    assert (this.expr_list_ != null);
                    tmp3 = this.expr_list_.translate(vIrCollector);

                    tmp4 = new IR(kExprList, new IROperator("", "", ""), tmp1, tmp2);
                    vIrCollector.add(tmp4);
                    res = new IR(kExprList, new IROperator("", "", ""), tmp4, tmp3);
                    break;
                case 1:
                    assert (this.expr_ != null);
                    tmp1 = this.expr_.translate(vIrCollector);
                    assert (this.opt_as_alias_ != null);
                    tmp2 = this.opt_as_alias_.translate(vIrCollector);
                    res = new IR(kExprList, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 200;
            switch (this.caseIdx) {
                case 0:
                    this.expr_ = new Expr();
                    this.expr_.generate();
                    this.opt_as_alias_ = new OptAsAlias();
                    this.opt_as_alias_.generate();
                    this.expr_list_ = new ExprList();
                    this.expr_list_.generate();
                    break;
                case 1:
                    this.expr_ = new Expr();
                    this.expr_.generate();
                    this.opt_as_alias_ = new OptAsAlias();
                    this.opt_as_alias_.generate();
                    break;
                default:
                    this.expr_ = new Expr();
                    this.expr_.generate();
                    this.opt_as_alias_ = new OptAsAlias();
                    this.opt_as_alias_.generate();
                    this.caseIdx = 1;
                    break;
            }
        }

        @Override
        public void deepDelete() {
            this.expr_ = null;
            this.opt_as_alias_ = null;
            this.expr_list_ = null;
        }
    }

    class OptLimitClause extends Node {
        LimitClause limit_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.limit_clause_ != null);
                    IR tmp1 = this.limit_clause_.translate(vIrCollector);
                    res = new IR(kOptLimitClause, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptLimitClause, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.limit_clause_ = new LimitClause();
                    this.limit_clause_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.limit_clause_ = null;
        }
    }

    class LimitClause extends Node {
        Expr expr_1_;
        Expr expr_2_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.expr_1_ != null);
                    tmp1 = this.expr_1_.translate(vIrCollector);
                    res = new IR(kOptLimitClause, new IROperator("LIMIT", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.expr_1_ != null);
                    tmp1 = this.expr_1_.translate(vIrCollector);
                    assert (this.expr_2_ != null);
                    tmp2 = this.expr_2_.translate(vIrCollector);
                    res = new IR(kOptLimitClause, new IROperator("LIMIT", "OFFSET", ""), tmp1, tmp2);
                    break;
                case 2:
                    assert (this.expr_1_ != null);
                    tmp1 = this.expr_1_.translate(vIrCollector);
                    assert (this.expr_2_ != null);
                    tmp2 = this.expr_2_.translate(vIrCollector);
                    res = new IR(kOptLimitClause, new IROperator("LIMIT", "", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
            switch (this.caseIdx) {
                case 0:
                    this.expr_1_ = new Expr();
                    this.expr_1_.generate();
                    break;
                case 1:
                case 2:
                    this.expr_1_ = new Expr();
                    this.expr_1_.generate();
                    this.expr_2_ = new Expr();
                    this.expr_2_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.expr_1_ = null;
            this.expr_2_ = null;
        }
    }

    class OptLimitRowCount extends Node {
        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.expr_ != null);
                    IR tmp1 = this.expr_.translate(vIrCollector);
                    res = new IR(kOptLimitRowCount, new IROperator("LIMIT", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptLimitRowCount, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.expr_ = new Expr();
                    this.expr_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.expr_ = null;
        }
    }

    class OptOrderClause extends Node {


        OrderItemList order_item_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.order_item_list_ != null);
                    IR tmp1 = this.order_item_list_.translate(vIrCollector);
                    res = new IR(kOptOrderClause, new IROperator("ORDER BY", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptOrderClause, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.order_item_list_ = new OrderItemList();
                    this.order_item_list_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.order_item_list_ = null;
        }
    }

    class OptOrderNulls extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptOrderNulls, new IROperator("NULLS FIRST", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptOrderNulls, new IROperator("NULLS LAST", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kOptOrderNulls, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
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
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.order_item_ != null);
                    tmp1 = this.order_item_.translate(vIrCollector);
                    res = new IR(kOrderItemList, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.order_item_ != null);
                    tmp1 = this.order_item_.translate(vIrCollector);
                    assert (this.order_item_list_ != null);
                    tmp2 = this.order_item_list_.translate(vIrCollector);
                    res = new IR(kOrderItemList, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.order_item_ = new OrderItem();
                    this.order_item_.generate();
                    break;
                case 1:
                    this.order_item_ = new OrderItem();
                    this.order_item_.generate();
                    this.order_item_list_ = new OrderItemList();
                    this.order_item_list_.generate();
                    break;
                default:
                    this.order_item_ = new OrderItem();
                    this.order_item_.generate();
                    this.caseIdx = 0;
                    break;
            }
        }

        @Override
        public void deepDelete() {
            this.order_item_ = null;
            this.order_item_list_ = null;
        }
    }

    class OrderItem extends Node {
        Expr expr_;
        OptOrderNulls opt_order_nulls_;
        OptOrderBehavior opt_order_behavior_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;

            assert (this.expr_ != null);
            tmp1 = this.expr_.translate(vIrCollector);
            assert (this.opt_order_behavior_ != null);
            tmp2 = this.opt_order_behavior_.translate(vIrCollector);
            assert (this.opt_order_nulls_ != null);
            tmp3 = this.opt_order_nulls_.translate(vIrCollector);
            tmp4 = new IR(kUnknown, new IROperator("", "", ""), tmp1, tmp2);
            vIrCollector.add(tmp4);
            res = new IR(kOrderItem, new IROperator("", "", ""), tmp4, tmp3);

            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.expr_ = new Expr();
            this.expr_.generate();
            this.opt_order_behavior_ = new OptOrderBehavior();
            this.opt_order_behavior_.generate();
            this.opt_order_nulls_ = new OptOrderNulls();
            this.opt_order_nulls_.generate();
        }

        @Override
        public void deepDelete() {
            this.expr_ = null;
            this.opt_order_behavior_ = null;
            this.opt_order_nulls_ = null;
        }
    }

    class OptOrderBehavior extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptOrderBehavior, new IROperator("ASE", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptOrderBehavior, new IROperator("DESC", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kOptOrderBehavior, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
        }

        @Override
        public void deepDelete() {

        }
    }

    class OptWithClause extends Node {
        CteTableList cte_table_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.cte_table_list_ != null);
                    tmp1 = this.cte_table_list_.translate(vIrCollector);
                    res = new IR(kOptWithClause, new IROperator("WITH", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.cte_table_list_ != null);
                    tmp1 = this.cte_table_list_.translate(vIrCollector);
                    res = new IR(kOptWithClause, new IROperator("WITH RECURSIVE", "", ""), tmp1);
                    break;
                case 2:
                    res = new IR(kOptWithClause, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
            switch (this.caseIdx) {
                case 0:
                case 1:
                    this.cte_table_list_ = new CteTableList();
                    this.cte_table_list_.generate();
                    break;
                case 2:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.cte_table_list_ = null;
        }
    }

    class CteTableList extends Node {
        CteTableList cte_table_list_;
        CteTable cte_table_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.cte_table_ != null);
                    tmp1 = this.cte_table_.translate(vIrCollector);
                    res = new IR(kCteTable, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.cte_table_ != null);
                    tmp1 = this.cte_table_.translate(vIrCollector);
                    assert (this.cte_table_list_ != null);
                    tmp2 = this.cte_table_list_.translate(vIrCollector);
                    res = new IR(kCteTable, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 200;
            switch (this.caseIdx) {
                case 0:
                    this.cte_table_ = new CteTable();
                    this.cte_table_.generate();
                case 1:
                    this.cte_table_ = new CteTable();
                    this.cte_table_.generate();
                    this.cte_table_list_ = new CteTableList();
                    this.cte_table_list_.generate();
                    break;
                default:
                    this.caseIdx = 0;
                    this.cte_table_ = new CteTable();
                    this.cte_table_.generate();
                    break;
            }
        }

        @Override
        public void deepDelete() {
            this.cte_table_ = null;
            this.cte_table_list_ = null;
        }
    }

    class CteTable extends Node {
        CteTableName cte_table_name_;
        SelectStmt select_stmt_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            assert (this.cte_table_name_ != null);
            IR tmp1 = this.cte_table_name_.translate(vIrCollector);
            assert (this.select_stmt_ != null);
            IR tmp2 = this.select_stmt_.translate(vIrCollector);
            IR res = new IR(kCteTable, new IROperator("", "AS(", ")"), tmp1, tmp2);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.cte_table_name_ = new CteTableName();
            this.cte_table_name_.generate();
            this.select_stmt_ = new SelectStmt();
            this.select_stmt_.generate();
        }

        @Override
        public void deepDelete() {
            this.cte_table_name_ = null;
            this.select_stmt_ = null;
        }
    }

    class CteTableName extends Node {
        OptColumnNameListP opt_column_name_list_p_;
        TableName table_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            assert (this.table_name_ != null);
            IR tmp1 = this.table_name_.translate(vIrCollector);
            assert (this.opt_column_name_list_p_ != null);
            IR tmp2 = this.opt_column_name_list_p_.translate(vIrCollector);
            IR res = new IR(kCteTableName, new IROperator("", "", ""), tmp1, tmp2);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.opt_column_name_list_p_ = new OptColumnNameListP();
            this.opt_column_name_list_p_.generate();
            this.table_name_ = new TableName();
            this.table_name_.generate();
        }

        @Override
        public void deepDelete() {
            this.opt_column_name_list_p_ = null;
            this.table_name_ = null;
        }
    }

    class OptAllOrDistinct extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptAllOrDistinct, new IROperator("ALL", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptAllOrDistinct, new IROperator("DISTINCT", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kOptAllOrDistinct, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
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
            IR res;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;
            IR tmp5;
            IR tmp6;
            IR tmp7;
            IR tmp8;
            IR tmp9;
            IR tmp10;
            switch (this.caseIdx) {
                case 0:
                    assert (this.opt_temp_ != null);
                    tmp1 = this.opt_temp_.translate(vIrCollector);
                    assert (this.opt_if_not_exist_ != null);
                    tmp2 = this.opt_if_not_exist_.translate(vIrCollector);
                    assert (this.table_name_ != null);
                    tmp3 = this.table_name_.translate(vIrCollector);
                    assert (this.opt_table_constraint_list_ != null);
                    tmp4 = this.opt_table_constraint_list_.translate(vIrCollector);
                    assert (this.opt_ignore_or_replace_ != null);
                    tmp5 = this.opt_ignore_or_replace_.translate(vIrCollector);
                    assert (this.select_stmt_ != null);
                    tmp6 = this.select_stmt_.translate(vIrCollector);

                    tmp7 = new IR(kUnknown, new IROperator("CREATE", "TABLE", ""), tmp1, tmp2);
                    vIrCollector.add(tmp7);
                    tmp8 = new IR(kUnknown, new IROperator("", "", ""), tmp7, tmp3);
                    vIrCollector.add(tmp8);
                    tmp9 = new IR(kUnknown, new IROperator("", "", ""), tmp8, tmp4);
                    vIrCollector.add(tmp9);
                    tmp10 = new IR(kUnknown, new IROperator("", "", "AS"), tmp9, tmp5);
                    vIrCollector.add(tmp10);
                    res = new IR(kCreateTableStmt, new IROperator("", "", ""), tmp10, tmp6);
                    break;
                case 1:
                    assert (this.opt_temp_ != null);
                    tmp1 = this.opt_temp_.translate(vIrCollector);
                    assert (this.opt_if_not_exist_ != null);
                    tmp2 = this.opt_if_not_exist_.translate(vIrCollector);
                    assert (this.table_name_ != null);
                    tmp3 = this.table_name_.translate(vIrCollector);
                    assert (this.column_def_list_ != null);
                    tmp4 = this.column_def_list_.translate(vIrCollector);
                    assert (this.opt_table_constraint_list_ != null);
                    tmp5 = this.opt_table_constraint_list_.translate(vIrCollector);
                    assert (this.opt_table_option_list_ != null);
                    tmp6 = this.opt_table_option_list_.translate(vIrCollector);

                    tmp7 = new IR(kUnknown, new IROperator("CREATE", "TABLE", ""), tmp1, tmp2);
                    vIrCollector.add(tmp7);
                    tmp8 = new IR(kUnknown, new IROperator("", "", "("), tmp7, tmp3);
                    vIrCollector.add(tmp8);
                    tmp9 = new IR(kUnknown, new IROperator("", "", ""), tmp8, tmp4);
                    vIrCollector.add(tmp9);
                    tmp10 = new IR(kUnknown, new IROperator("", "", ")"), tmp9, tmp5);
                    vIrCollector.add(tmp10);
                    res = new IR(kCreateTableStmt, new IROperator("", "", ""), tmp10, tmp6);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.opt_temp_ = new OptTemp();
                    this.opt_temp_.generate();
                    this.opt_if_not_exist_ = new OptIfNotExist();
                    this.opt_if_not_exist_.generate();
                    this.table_name_ = new TableName();
                    this.table_name_.generate();
                    this.opt_table_option_list_ = new OptTableOptionList();
                    this.opt_table_option_list_.generate();
                    this.opt_ignore_or_replace_ = new OptIgnoreOrReplace();
                    this.opt_ignore_or_replace_.generate();
                    this.select_stmt_ = new SelectStmt();
                    this.select_stmt_.generate();
                    break;
                case 1:
                    this.opt_temp_ = new OptTemp();
                    this.opt_temp_.generate();
                    this.opt_if_not_exist_ = new OptIfNotExist();
                    this.opt_if_not_exist_.generate();
                    this.table_name_ = new TableName();
                    this.table_name_.generate();
                    this.column_def_list_ = new ColumnDefList();
                    this.column_def_list_.generate();
                    this.opt_table_constraint_list_ = new OptTableConstraintList();
                    this.opt_table_constraint_list_.generate();
                    this.opt_table_option_list_ = new OptTableOptionList();
                    this.opt_table_option_list_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.opt_table_option_list_ = null;
            this.opt_table_constraint_list_ = null;
            this.opt_temp_ = null;
            this.select_stmt_ = null;
            this.table_name_ = null;
            this.column_def_list_ = null;
            this.opt_ignore_or_replace_ = null;
            this.opt_if_not_exist_ = null;
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
            IR res;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;
            IR tmp5;
            IR tmp6;
            IR tmp7;
            IR tmp8;
            IR tmp9;
            IR tmp10;
            assert (this.opt_index_keyword_ != null);
            tmp1 = this.opt_index_keyword_.translate(vIrCollector);
            assert (this.table_name_1_ != null);
            tmp2 = this.table_name_1_.translate(vIrCollector);
            assert (this.table_name_2_ != null);
            tmp3 = this.table_name_2_.translate(vIrCollector);
            assert (this.indexed_column_list_ != null);
            tmp4 = this.indexed_column_list_.translate(vIrCollector);
            assert (this.opt_index_option_ != null);
            tmp5 = this.opt_index_option_.translate(vIrCollector);
            assert (this.opt_extra_option_ != null);
            tmp6 = this.opt_extra_option_.translate(vIrCollector);

            tmp7 = new IR(kUnknown, new IROperator("CREATE", "INDEX", "ON"), tmp1, tmp2);
            vIrCollector.add(tmp7);
            tmp8 = new IR(kUnknown, new IROperator("", "", "("), tmp7, tmp3);
            vIrCollector.add(tmp8);
            tmp9 = new IR(kUnknown, new IROperator("", "", ")"), tmp8, tmp4);
            vIrCollector.add(tmp9);
            tmp10 = new IR(kUnknown, new IROperator("", "", "AS"), tmp9, tmp5);
            vIrCollector.add(tmp10);
            res = new IR(kCreateIndexStmt, new IROperator("", "", ""), tmp10, tmp6);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.table_name_1_ = new TableName();
            this.table_name_1_.generate();
            this.table_name_2_ = new TableName();
            this.table_name_2_.generate();
            this.opt_index_option_ = new OptIndexOption();
            this.opt_index_option_.generate();
            this.opt_index_keyword_ = new OptIndexKeyword();
            this.opt_index_keyword_.generate();
            this.indexed_column_list_ = new IndexedColumnList();
            this.indexed_column_list_.generate();
            this.opt_extra_option_ = new OptExtraOption();
            this.opt_extra_option_.generate();
        }

        @Override
        public void deepDelete() {
            this.table_name_1_ = null;
            this.table_name_2_ = null;
            this.opt_index_option_ = null;
            this.opt_index_keyword_ = null;
            this.indexed_column_list_ = null;
            this.opt_extra_option_ = null;
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
            IR res;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;
            IR tmp5;
            IR tmp6;
            IR tmp7;
            IR tmp8;
            assert (this.trigger_name_ != null);
            tmp1 = this.trigger_name_.translate(vIrCollector);
            assert (this.trigger_action_time_ != null);
            tmp2 = this.trigger_action_time_.translate(vIrCollector);
            assert (this.trigger_events_ != null);
            tmp3 = this.trigger_events_.translate(vIrCollector);
            assert (this.table_name_ != null);
            tmp4 = this.table_name_.translate(vIrCollector);
            assert (this.trigger_body_ != null);
            tmp5 = this.trigger_body_.translate(vIrCollector);

            tmp6 = new IR(kUnknown, new IROperator("CREATE TRIGGER", "", ""), tmp1, tmp2);
            vIrCollector.add(tmp6);
            tmp7 = new IR(kUnknown, new IROperator("", "", "ON"), tmp6, tmp3);
            vIrCollector.add(tmp7);
            tmp8 = new IR(kUnknown, new IROperator("", "", "FOR"), tmp7, tmp4);
            vIrCollector.add(tmp8);
            res = new IR(kCreateTriggerStmt, new IROperator("", "EACH ROW", ""), tmp8, tmp5);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.trigger_action_time_ = new TriggerActionTime();
            this.trigger_action_time_.generate();
            this.trigger_name_ = new TriggerName();
            this.trigger_name_.generate();
            this.table_name_ = new TableName();
            this.table_name_.generate();
            this.trigger_events_ = new TriggerEvents();
            this.trigger_events_.generate();
            this.trigger_body_ = new TriggerBody();
            this.trigger_body_.generate();
        }

        @Override
        public void deepDelete() {
            this.trigger_action_time_ = null;
            this.trigger_name_ = null;
            this.table_name_ = null;
            this.trigger_events_ = null;
            this.trigger_body_ = null;
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
            IR res = null;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;
            IR tmp5;
            IR tmp6;
            IR tmp7;
            IR tmp8;
            IR tmp9;
            IR tmp10;
            switch (this.caseIdx) {
                case 0:
                    assert (this.opt_view_algorithm_ != null);
                    tmp1 = this.opt_view_algorithm_.translate(vIrCollector);
                    assert (this.opt_sql_security_ != null);
                    tmp2 = this.opt_sql_security_.translate(vIrCollector);
                    assert (this.view_name_ != null);
                    tmp3 = this.view_name_.translate(vIrCollector);
                    assert (this.opt_column_name_list_p_ != null);
                    tmp4 = this.opt_column_name_list_p_.translate(vIrCollector);
                    assert (this.select_stmt_ != null);
                    tmp5 = this.select_stmt_.translate(vIrCollector);
                    assert (this.opt_check_option_ != null);
                    tmp6 = this.opt_check_option_.translate(vIrCollector);

                    tmp7 = new IR(kUnknown, new IROperator("CREATE", "VIEW", ""), tmp1, tmp2);
                    vIrCollector.add(tmp7);
                    tmp8 = new IR(kUnknown, new IROperator("", "", ""), tmp7, tmp3);
                    vIrCollector.add(tmp8);
                    tmp9 = new IR(kUnknown, new IROperator("", "", "AS"), tmp8, tmp4);
                    vIrCollector.add(tmp9);
                    tmp10 = new IR(kUnknown, new IROperator("", "", ""), tmp9, tmp5);
                    vIrCollector.add(tmp10);
                    res = new IR(kCreateViewStmt, new IROperator("", "", ""), tmp10, tmp6);
                    break;
                case 1:
                    assert (this.opt_view_algorithm_ != null);
                    tmp1 = this.opt_view_algorithm_.translate(vIrCollector);
                    assert (this.opt_sql_security_ != null);
                    tmp2 = this.opt_sql_security_.translate(vIrCollector);
                    assert (this.view_name_ != null);
                    tmp3 = this.view_name_.translate(vIrCollector);
                    assert (this.opt_column_name_list_p_ != null);
                    tmp4 = this.opt_column_name_list_p_.translate(vIrCollector);
                    assert (this.select_stmt_ != null);
                    tmp5 = this.select_stmt_.translate(vIrCollector);
                    assert (this.opt_check_option_ != null);
                    tmp6 = this.opt_check_option_.translate(vIrCollector);

                    tmp7 = new IR(kUnknown, new IROperator("CREATE OR REPLACE", "VIEW", ""), tmp1, tmp2);
                    vIrCollector.add(tmp7);
                    tmp8 = new IR(kUnknown, new IROperator("", "", ""), tmp7, tmp3);
                    vIrCollector.add(tmp8);
                    tmp9 = new IR(kUnknown, new IROperator("", "", "AS"), tmp8, tmp4);
                    vIrCollector.add(tmp9);
                    tmp10 = new IR(kUnknown, new IROperator("", "", ""), tmp9, tmp5);
                    vIrCollector.add(tmp10);
                    res = new IR(kCreateViewStmt, new IROperator("", "", ""), tmp10, tmp6);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                case 1:
                    this.opt_view_algorithm_ = new OptViewAlgorithm();
                    this.opt_view_algorithm_.generate();
                    this.opt_sql_security_ = new OptSqlSecurity();
                    this.opt_sql_security_.generate();
                    this.view_name_ = new ViewName();
                    this.view_name_.generate();
                    this.opt_column_name_list_p_ = new OptColumnNameListP();
                    this.opt_column_name_list_p_.generate();
                    this.select_stmt_ = new SelectStmt();
                    this.select_stmt_.generate();
                    this.opt_check_option_ = new OptCheckOption();
                    this.opt_check_option_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.opt_view_algorithm_ = null;
            this.opt_sql_security_ = null;
            this.view_name_ = null;
            this.opt_column_name_list_p_ = null;
            this.select_stmt_ = null;
            this.opt_check_option_ = null;
        }
    }

    class OptTableOptionList extends Node {
        TableOptionList table_option_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.table_option_list_ != null);
                    IR tmp1 = this.table_option_list_.translate(vIrCollector);
                    res = new IR(kOptTableOptionList, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptTableOptionList, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.table_option_list_ = new TableOptionList();
                    this.table_option_list_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.table_option_list_ = null;
        }
    }

    class TableOptionList extends Node {
        TableOptionList table_option_list_;
        OptOpComma opt_op_comma_;
        TableOption table_option_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;
            switch (this.caseIdx) {
                case 0:
                    assert (this.table_option_ != null);
                    tmp1 = this.table_option_.translate(vIrCollector);
                    res = new IR(kOptTableOptionList, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.table_option_ != null);
                    tmp1 = this.table_option_.translate(vIrCollector);
                    assert (this.opt_op_comma_ != null);
                    tmp2 = this.opt_op_comma_.translate(vIrCollector);
                    assert (this.table_option_list_ != null);
                    tmp3 = this.table_option_list_.translate(vIrCollector);
                    tmp4 = new IR(kUnknown, new IROperator("", "", ""), tmp1, tmp2);
                    vIrCollector.add(tmp4);
                    res = new IR(kOptTableOptionList, new IROperator("", "", ""), tmp4, tmp3);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 200;
            switch (this.caseIdx) {
                case 0:
                    this.table_option_ = new TableOption();
                    this.table_option_.generate();
                    break;
                case 1:
                    this.table_option_ = new TableOption();
                    this.table_option_.generate();
                    this.opt_op_comma_ = new OptOpComma();
                    this.opt_op_comma_.generate();
                    this.table_option_list_ = new TableOptionList();
                    this.table_option_list_.generate();
                    break;
                default:
                    this.caseIdx = 0;
                    this.table_option_ = new TableOption();
                    this.table_option_.generate();
                    break;
            }
        }

        @Override
        public void deepDelete() {
            this.table_option_list_ = null;
            this.opt_op_comma_ = null;
            this.table_option_ = null;
        }
    }

    class TableOption extends Node {
        OptOpEqual opt_op_equal_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kTableOption, new IROperator("INSERT_METHOD", "NO", ""), tmp1);
                    break;
                case 1:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kTableOption, new IROperator("INSERT_METHOD", "FIRST", ""), tmp1);
                    break;
                case 2:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kTableOption, new IROperator("INSERT_METHOD", "LAST", ""), tmp1);
                    break;
                case 3:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kTableOption, new IROperator("ROW_FORMAT", "DEFAULT", ""), tmp1);
                    break;
                case 4:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kTableOption, new IROperator("ROW_FORMAT", "DYNAMIC", ""), tmp1);
                    break;
                case 5:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kTableOption, new IROperator("ROW_FORMAT", "FIXED", ""), tmp1);
                    break;
                case 6:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kTableOption, new IROperator("ROW_FORMAT", "COMPRESSED", ""), tmp1);
                    break;
                case 7:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kTableOption, new IROperator("ROW_FORMAT", "REDUNDANT", ""), tmp1);
                    break;
                case 8:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kTableOption, new IROperator("ROW_FORMAT", "COMPACT", ""), tmp1);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 9;
            switch (this.caseIdx) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    this.opt_op_equal_ = new OptOpEqual();
                    this.opt_op_equal_.generate();
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.opt_op_equal_ = null;
        }
    }

    class OptOpComma extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptOpComma, new IROperator(",", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptOpComma, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
        }

        @Override
        public void deepDelete() {

        }
    }

    class OptIgnoreOrReplace extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptIgnoreOrReplace, new IROperator("IGNORE", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptIgnoreOrReplace, new IROperator("REPLACE", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kOptIgnoreOrReplace, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
        }

        @Override
        public void deepDelete() {

        }
    }

    class OptViewAlgorithm extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptViewAlgorithm, new IROperator("ALGORITHM = UNDEFINED", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptViewAlgorithm, new IROperator("ALGORITHM = MERGE", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kOptViewAlgorithm, new IROperator("ALGORITHM = TEMPTABLE", "", ""), null, null);
                    break;
                case 3:
                    res = new IR(kOptViewAlgorithm, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 4;
        }

        @Override
        public void deepDelete() {

        }
    }

    class OptSqlSecurity extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptSqlSecurity, new IROperator("SQL SECURITY DEFINER", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptSqlSecurity, new IROperator("SQL SECURITY INVOKER", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kOptSqlSecurity, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
        }

        @Override
        public void deepDelete() {

        }
    }

    class OptIndexOption extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptIndexOption, new IROperator("USING BTREE", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptIndexOption, new IROperator("USING HASH", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kOptIndexOption, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
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
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.index_algorithm_option_ != null);
                    tmp1 = this.index_algorithm_option_.translate(vIrCollector);
                    res = new IR(kOptExtraOption, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.lock_option_ != null);
                    tmp1 = this.lock_option_.translate(vIrCollector);
                    res = new IR(kOptExtraOption, new IROperator("", "", ""), tmp1);
                    break;
                case 2:
                    res = new IR(kOptExtraOption, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
            switch (this.caseIdx) {
                case 0:
                    this.index_algorithm_option_ = new IndexAlgorithmOption();
                    this.index_algorithm_option_.generate();
                    break;
                case 1:
                    this.lock_option_ = new LockOption();
                    this.lock_option_.generate();
                    break;
                case 2:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.lock_option_ = null;
            this.index_algorithm_option_ = null;
        }
    }

    class IndexAlgorithmOption extends Node {
        OptOpEqual opt_op_equal_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kIndexAlgorithmOption, new IROperator("ALGORITHM", "DEFAULT", ""), tmp1);
                    break;
                case 1:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kIndexAlgorithmOption, new IROperator("ALGORITHM", "INPLACE", ""), tmp1);
                    break;
                case 2:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kIndexAlgorithmOption, new IROperator("ALGORITHM", "COPY", ""), tmp1);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
            this.opt_op_equal_ = new OptOpEqual();
            this.opt_op_equal_.generate();
        }

        @Override
        public void deepDelete() {
            this.opt_op_equal_ = null;
        }
    }

    class LockOption extends Node {
        OptOpEqual opt_op_equal_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kLockOption, new IROperator("LOCK", "DEFAULT", ""), tmp1);
                    break;
                case 1:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kLockOption, new IROperator("LOCK", "NONE", ""), tmp1);
                    break;
                case 2:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kLockOption, new IROperator("LOCK", "SHARED", ""), tmp1);
                    break;
                case 3:
                    assert (this.opt_op_equal_ != null);
                    tmp1 = this.opt_op_equal_.translate(vIrCollector);
                    res = new IR(kLockOption, new IROperator("LOCK", "EXCLUSIVE", ""), tmp1);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 4;
            this.opt_op_equal_ = new OptOpEqual();
            this.opt_op_equal_.generate();
        }

        @Override
        public void deepDelete() {
            this.opt_op_equal_ = null;
        }
    }

    class OptOpEqual extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptOpEqual, new IROperator("=", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptOpEqual, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
        }

        @Override
        public void deepDelete() {

        }
    }

    class TriggerEvents extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kTriggerEvents, new IROperator("DELETE", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kTriggerEvents, new IROperator("INSERT", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kTriggerEvents, new IROperator("UPDATE", "", ""), null, null);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
        }

        @Override
        public void deepDelete() {

        }
    }

    class TriggerName extends Node {


        Identifier identifier_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            assert (this.identifier_ != null);
            IR tmp1 = this.identifier_.translate(vIrCollector);
            IR res = new IR(kTriggerName, new IROperator("", "", ""), tmp1);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.identifier_ = new Identifier();
            this.identifier_.generate();
        }

        @Override
        public void deepDelete() {
            this.identifier_ = null;
        }
    }

    class TriggerActionTime extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kTriggerActionTime, new IROperator("BEFORE", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kTriggerActionTime, new IROperator("AFTER", "", ""), null, null);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
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
            assert (this.table_name_ != null);
            IR tmp1 = this.table_name_.translate(vIrCollector);
            assert (this.opt_extra_option_ != null);
            IR tmp2 = this.opt_extra_option_.translate(vIrCollector);
            IR res = new IR(kDropIndexStmt, new IROperator("DROP INDEX", "", ""), tmp1, tmp2);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.table_name_ = new TableName();
            this.table_name_.generate();
            this.opt_extra_option_ = new OptExtraOption();
            this.opt_extra_option_.generate();
        }

        @Override
        public void deepDelete() {
            this.table_name_ = null;
            this.opt_extra_option_ = null;
        }
    }

    class DropTableStmt extends Node {
        OptTemp opt_temp_;
        TableName table_name_;
        OptRestrictOrCascade opt_restrict_or_cascade_;
        OptIfExist opt_if_exist_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            assert (this.opt_temp_ != null);
            IR tmp1 = this.opt_temp_.translate(vIrCollector);
            assert (this.opt_if_exist_ != null);
            IR tmp2 = this.opt_if_exist_.translate(vIrCollector);
            assert (this.table_name_ != null);
            IR tmp3 = this.table_name_.translate(vIrCollector);
            assert (this.opt_restrict_or_cascade_ != null);
            IR tmp4 = this.opt_restrict_or_cascade_.translate(vIrCollector);
            IR tmp5 = new IR(kUnknown, new IROperator("DROP", "TABLE", ""), tmp1, tmp2);
            vIrCollector.add(tmp5);
            IR tmp6 = new IR(kUnknown, new IROperator("", "", ""), tmp5, tmp3);
            vIrCollector.add(tmp6);
            IR res = new IR(kDropTableStmt, new IROperator("", "", ""), tmp6, tmp4);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = 0;
            this.opt_temp_ = new OptTemp();
            this.opt_temp_.generate();
            this.opt_if_exist_ = new OptIfExist();
            this.opt_if_exist_.generate();
            this.table_name_ = new TableName();
            this.table_name_.generate();
            this.opt_restrict_or_cascade_ = new OptRestrictOrCascade();
            this.opt_restrict_or_cascade_.generate();
        }

        @Override
        public void deepDelete() {
            this.opt_temp_ = null;
            this.opt_if_exist_ = null;
            this.table_name_ = null;
            this.opt_restrict_or_cascade_ = null;
        }
    }

    class OptRestrictOrCascade extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptRestrictOrCascade, new IROperator("RESTRICT", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptRestrictOrCascade, new IROperator("CASCADE", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kOptRestrictOrCascade, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
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
            assert (this.opt_if_exist_ != null);
            IR tmp1 = this.opt_if_exist_.translate(vIrCollector);
            assert (this.trigger_name_ != null);
            IR tmp2 = this.trigger_name_.translate(vIrCollector);
            IR res = new IR(kOptHavingClause, new IROperator("DRIP TRIGGER", "", ""), tmp1, tmp2);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = 0;
            this.opt_if_exist_ = new OptIfExist();
            this.opt_if_exist_.generate();
            this.trigger_name_ = new TriggerName();
            this.trigger_name_.generate();
        }

        @Override
        public void deepDelete() {
            this.opt_if_exist_ = null;
            this.trigger_name_ = null;
        }
    }

    class DropViewStmt extends Node {
        ViewName view_name_;
        OptRestrictOrCascade opt_restrict_or_cascade_;
        OptIfExist opt_if_exist_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            assert (this.opt_if_exist_ != null);
            IR tmp1 = this.opt_if_exist_.translate(vIrCollector);
            assert (this.view_name_ != null);
            IR tmp2 = this.view_name_.translate(vIrCollector);
            assert (this.opt_restrict_or_cascade_ != null);
            IR tmp3 = this.opt_restrict_or_cascade_.translate(vIrCollector);
            IR tmp4 = new IR(kUnknown, new IROperator("DRIP VIEW", "", ""), tmp1, tmp2);
            vIrCollector.add(tmp4);
            IR res = new IR(kDropViewStmt, new IROperator("", "", ""), tmp4, tmp3);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = 0;
            this.opt_if_exist_ = new OptIfExist();
            this.opt_if_exist_.generate();
            this.view_name_ = new ViewName();
            this.view_name_.generate();
            this.opt_restrict_or_cascade_ = new OptRestrictOrCascade();
            this.opt_restrict_or_cascade_.generate();
        }

        @Override
        public void deepDelete() {
            this.opt_if_exist_ = null;
            this.view_name_ = null;
            this.opt_restrict_or_cascade_ = null;
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
            assert (this.opt_with_clause_ != null);
            IR tmp1 = this.opt_with_clause_.translate(vIrCollector);
            assert (this.table_name_ != null);
            IR tmp2 = this.table_name_.translate(vIrCollector);
            assert (this.opt_as_alias_ != null);
            IR tmp3 = this.opt_as_alias_.translate(vIrCollector);
            assert (this.insert_rest_ != null);
            IR tmp4 = this.insert_rest_.translate(vIrCollector);
            assert (this.opt_on_conflict_ != null);
            IR tmp5 = this.opt_on_conflict_.translate(vIrCollector);
            IR tmp6 = new IR(kUnknown, new IROperator("", "INSERT INTO", ""), tmp1, tmp2);
            vIrCollector.add(tmp6);
            IR tmp7 = new IR(kUnknown, new IROperator("", "", ""), tmp6, tmp3);
            vIrCollector.add(tmp7);
            IR tmp8 = new IR(kUnknown, new IROperator("", "", ""), tmp7, tmp4);
            vIrCollector.add(tmp8);
            IR res = new IR(kInsertStmt, new IROperator("", "", ""), tmp8, tmp5);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = 0;
            this.insert_rest_ = new InsertRest();
            this.insert_rest_.generate();
            this.opt_as_alias_ = new OptAsAlias();
            this.opt_as_alias_.generate();
            this.table_name_ = new TableName();
            this.table_name_.generate();
            this.opt_on_conflict_ = new OptOnConflict();
            this.opt_on_conflict_.generate();
            this.opt_with_clause_ = new OptWithClause();
            this.opt_with_clause_.generate();
        }

        @Override
        public void deepDelete() {
            this.insert_rest_ = null;
            this.opt_as_alias_ = null;
            this.table_name_ = null;
            this.opt_on_conflict_ = null;
            this.opt_with_clause_ = null;
        }
    }

    class InsertRest extends Node {
        OptColumnNameListP opt_column_name_list_p_;
        SuperValuesList super_values_list_;
        SelectNoParens select_no_parens_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.opt_column_name_list_p_ != null);
                    tmp1 = this.opt_column_name_list_p_.translate(vIrCollector);
                    assert (this.select_no_parens_ != null);
                    tmp2 = this.select_no_parens_.translate(vIrCollector);
                    res = new IR(kInsertRest, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                case 1:
                    assert (this.opt_column_name_list_p_ != null);
                    tmp1 = this.opt_column_name_list_p_.translate(vIrCollector);
                    res = new IR(kInsertRest, new IROperator("DEFAULT HAVING", "", ""), tmp1);
                    break;
                case 2:
                    assert (this.opt_column_name_list_p_ != null);
                    tmp1 = this.opt_column_name_list_p_.translate(vIrCollector);
                    assert (this.super_values_list_ != null);
                    tmp2 = this.super_values_list_.translate(vIrCollector);
                    res = new IR(kInsertRest, new IROperator("", "VALUES", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
            switch (this.caseIdx) {
                case 0:
                    this.opt_column_name_list_p_ = new OptColumnNameListP();
                    this.opt_column_name_list_p_.generate();
                    this.select_no_parens_ = new SelectNoParens();
                    this.select_no_parens_.generate();
                    break;
                case 1:
                    this.opt_column_name_list_p_ = new OptColumnNameListP();
                    this.opt_column_name_list_p_.generate();
                    break;
                case 2:
                    this.opt_column_name_list_p_ = new OptColumnNameListP();
                    this.opt_column_name_list_p_.generate();
                    this.super_values_list_ = new SuperValuesList();
                    this.super_values_list_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.opt_column_name_list_p_ = null;
            this.select_no_parens_ = null;
            this.super_values_list_ = null;
        }
    }

    class SuperValuesList extends Node {


        ValuesList values_list_;
        SuperValuesList super_values_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.values_list_ != null);
                    tmp1 = this.values_list_.translate(vIrCollector);
                    res = new IR(kSuperValuesList, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.values_list_ != null);
                    tmp1 = this.values_list_.translate(vIrCollector);
                    assert (this.super_values_list_ != null);
                    tmp2 = this.super_values_list_.translate(vIrCollector);
                    res = new IR(kSuperValuesList, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 200;
            switch (this.caseIdx) {
                case 0:
                    this.values_list_ = new ValuesList();
                    this.values_list_.generate();
                    break;
                case 1:
                    this.values_list_ = new ValuesList();
                    this.values_list_.generate();
                    this.super_values_list_ = new SuperValuesList();
                    this.super_values_list_.generate();
                    break;
                default:
                    this.caseIdx = 0;
                    this.values_list_ = new ValuesList();
                    this.values_list_.generate();
            }
        }

        @Override
        public void deepDelete() {
            this.values_list_ = null;
            this.super_values_list_ = null;
        }
    }

    class ValuesList extends Node {
        ExprList expr_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            assert (this.expr_list_ != null);
            IR tmp1 = this.expr_list_.translate(vIrCollector);
            res = new IR(kValuesList, new IROperator("(", ")", ""), tmp1);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = 0;
            this.expr_list_ = new ExprList();
            this.expr_list_.generate();
        }

        @Override
        public void deepDelete() {
            this.expr_list_ = null;
        }
    }

    class OptOnConflict extends Node {
        OptConflictExpr opt_conflict_expr_;
        SetClauseList set_clause_list_;
        WhereClause where_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;
            switch (this.caseIdx) {
                case 0:
                    assert (this.opt_conflict_expr_ != null);
                    tmp1 = this.opt_conflict_expr_.translate(vIrCollector);
                    res = new IR(kOptOnConflict, new IROperator("ON CONFLICT", "DO NOTHING", ""), tmp1);
                    break;
                case 1:
                    assert (this.opt_conflict_expr_ != null);
                    tmp1 = this.opt_conflict_expr_.translate(vIrCollector);
                    assert (this.set_clause_list_ != null);
                    tmp2 = this.set_clause_list_.translate(vIrCollector);
                    assert (this.where_clause_ != null);
                    tmp3 = this.where_clause_.translate(vIrCollector);
                    tmp4 = new IR(kUnknown, new IROperator("ON CONFLICT", "DO UPDATE", ""), tmp1, tmp2);
                    vIrCollector.add(tmp4);
                    res = new IR(kOptOnConflict, new IROperator("", "", ""), tmp4, tmp3);
                    break;
                case 2:
                    res = new IR(kOptOnConflict, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
            switch (this.caseIdx) {
                case 0:
                    this.opt_conflict_expr_ = new OptConflictExpr();
                    this.opt_conflict_expr_.generate();
                    break;
                case 1:
                    this.opt_conflict_expr_ = new OptConflictExpr();
                    this.opt_conflict_expr_.generate();
                    this.set_clause_list_ = new SetClauseList();
                    this.set_clause_list_.generate();
                    this.where_clause_ = new WhereClause();
                    this.where_clause_.generate();
                    break;
                case 2:
                    break;
            }
        }

        @Override
        public void deepDelete() {
            this.opt_conflict_expr_ = null;
            this.set_clause_list_ = null;
            this.where_clause_ = null;
        }
    }

    class OptConflictExpr extends Node {
        IndexedColumnList indexed_column_list_;
        WhereClause where_clause_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.indexed_column_list_ != null);
                    IR tmp1 = this.indexed_column_list_.translate(vIrCollector);
                    assert (this.where_clause_ != null);
                    IR tmp2 = this.where_clause_.translate(vIrCollector);
                    res = new IR(kOptConflictExpr, new IROperator("(", ")", ""), tmp1, tmp2);
                    break;
                case 1:
                    res = new IR(kOptConflictExpr, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.indexed_column_list_ = new IndexedColumnList();
                    this.indexed_column_list_.generate();
                    this.where_clause_ = new WhereClause();
                    this.where_clause_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.indexed_column_list_ = null;
            this.where_clause_ = null;
        }
    }

    class IndexedColumnList extends Node {
        IndexedColumn indexed_column_;
        IndexedColumnList indexed_column_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.indexed_column_ != null);
                    tmp1 = this.indexed_column_.translate(vIrCollector);

                    res = new IR(kIndexedColumnList, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.indexed_column_ != null);
                    tmp1 = this.indexed_column_.translate(vIrCollector);
                    assert (this.indexed_column_list_ != null);
                    tmp2 = this.indexed_column_list_.translate(vIrCollector);
                    res = new IR(kIndexedColumnList, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 200;
            switch (this.caseIdx) {
                case 0:
                    this.indexed_column_ = new IndexedColumn();
                    this.indexed_column_.generate();
                    break;
                case 1:
                    this.indexed_column_ = new IndexedColumn();
                    this.indexed_column_.generate();
                    this.indexed_column_list_ = new IndexedColumnList();
                    this.indexed_column_list_.generate();
                    break;
                default:
                    this.indexed_column_ = new IndexedColumn();
                    this.indexed_column_.generate();
                    this.caseIdx = 0;
            }
        }

        @Override
        public void deepDelete() {
            this.indexed_column_ = null;
            this.indexed_column_list_ = null;
        }
    }

    class IndexedColumn extends Node {
        Expr expr_;
        OptOrderBehavior opt_order_behavior_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;

            assert (this.expr_ != null);
            IR tmp1 = this.expr_.translate(vIrCollector);
            assert (this.opt_order_behavior_ != null);
            IR tmp2 = this.opt_order_behavior_.translate(vIrCollector);
            res = new IR(kIndexedColumn, new IROperator("", "", ""), tmp1, tmp2);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = 0;
            this.expr_ = new Expr();
            this.expr_.generate();
            this.opt_order_behavior_ = new OptOrderBehavior();
            this.opt_order_behavior_.generate();
        }

        @Override
        public void deepDelete() {
            this.expr_ = null;
            this.opt_order_behavior_ = null;
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
            IR res = null;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;
            IR tmp5;
            IR tmp6;
            IR tmp7;
            IR tmp8;
            IR tmp9;
            IR tmp10;
            switch (this.caseIdx) {
                case 0:
                    assert (this.table_name_ != null);
                    tmp1 = this.table_name_.translate(vIrCollector);
                    assert (this.opt_as_alias_ != null);
                    tmp2 = this.opt_as_alias_.translate(vIrCollector);
                    assert (this.set_clause_list_ != null);
                    tmp3 = this.set_clause_list_.translate(vIrCollector);
                    assert (this.opt_where_clause_ != null);
                    tmp4 = this.opt_where_clause_.translate(vIrCollector);
                    assert (this.opt_order_clause_ != null);
                    tmp5 = this.opt_order_clause_.translate(vIrCollector);
                    assert (this.opt_limit_row_count_ != null);
                    tmp6 = this.opt_limit_row_count_.translate(vIrCollector);

                    tmp7 = new IR(kUnknown, new IROperator("UPDATE IGNORE", "", "SET"), tmp1, tmp2);
                    vIrCollector.add(tmp7);
                    tmp8 = new IR(kUnknown, new IROperator("", "", ""), tmp7, tmp3);
                    vIrCollector.add(tmp8);
                    tmp9 = new IR(kUnknown, new IROperator("", "", ""), tmp8, tmp4);
                    vIrCollector.add(tmp9);
                    tmp10 = new IR(kUnknown, new IROperator("", "", ""), tmp9, tmp5);
                    vIrCollector.add(tmp10);
                    res = new IR(kUpdateStmt, new IROperator("", "", ""), tmp10, tmp6);
                    break;
                case 1:
                    assert (this.table_name_ != null);
                    tmp1 = this.table_name_.translate(vIrCollector);
                    assert (this.opt_as_alias_ != null);
                    tmp2 = this.opt_as_alias_.translate(vIrCollector);
                    assert (this.set_clause_list_ != null);
                    tmp3 = this.set_clause_list_.translate(vIrCollector);
                    assert (this.opt_where_clause_ != null);
                    tmp4 = this.opt_where_clause_.translate(vIrCollector);
                    assert (this.opt_order_clause_ != null);
                    tmp5 = this.opt_order_clause_.translate(vIrCollector);
                    assert (this.opt_limit_row_count_ != null);
                    tmp6 = this.opt_limit_row_count_.translate(vIrCollector);

                    tmp7 = new IR(kUnknown, new IROperator("UPDATE", "", "SET"), tmp1, tmp2);
                    vIrCollector.add(tmp7);
                    tmp8 = new IR(kUnknown, new IROperator("", "", ""), tmp7, tmp3);
                    vIrCollector.add(tmp8);
                    tmp9 = new IR(kUnknown, new IROperator("", "", ""), tmp8, tmp4);
                    vIrCollector.add(tmp9);
                    tmp10 = new IR(kUnknown, new IROperator("", "", ""), tmp9, tmp5);
                    vIrCollector.add(tmp10);
                    res = new IR(kUpdateStmt, new IROperator("", "", ""), tmp10, tmp6);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            this.table_name_ = new TableName();
            this.table_name_.generate();
            this.opt_as_alias_ = new OptAsAlias();
            this.opt_as_alias_.generate();
            this.set_clause_list_ = new SetClauseList();
            this.set_clause_list_.generate();
            this.opt_where_clause_ = new OptWhereClause();
            this.opt_where_clause_.generate();
            this.opt_order_clause_ = new OptOrderClause();
            this.opt_order_clause_.generate();
            this.opt_limit_row_count_ = new OptLimitRowCount();
            this.opt_limit_row_count_.generate();
        }

        @Override
        public void deepDelete() {
            this.table_name_ = null;
            this.opt_as_alias_ = null;
            this.set_clause_list_ = null;
            this.opt_where_clause_ = null;
            this.opt_order_clause_ = null;
            this.opt_limit_row_count_ = null;
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
            IR res;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;
            switch (this.caseIdx) {
                case 0:
                    assert (this.table_name_ != null);
                    tmp1 = this.table_name_.translate(vIrCollector);
                    res = new IR(kAlterAction, new IROperator("RENAME TO", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.opt_column_ != null);
                    tmp1 = this.opt_column_.translate(vIrCollector);
                    assert (this.column_name_1_ != null);
                    tmp2 = this.column_name_1_.translate(vIrCollector);
                    assert (this.column_name_2_ != null);
                    tmp3 = this.column_name_2_.translate(vIrCollector);
                    tmp4 = new IR(kUnknown, new IROperator("RENAME", "TO", ""), tmp1, tmp2);
                    vIrCollector.add(tmp4);
                    res = new IR(kAlterAction, new IROperator("", "", ""), tmp4, tmp3);
                    break;
                case 2:
                    assert (this.opt_column_ != null);
                    tmp1 = this.opt_column_.translate(vIrCollector);
                    assert (this.column_def_ != null);
                    tmp2 = this.column_def_.translate(vIrCollector);
                    res = new IR(kAlterAction, new IROperator("ADD", "", ""), tmp1, tmp2);
                    break;
                case 3:
                    assert (this.opt_column_ != null);
                    tmp1 = this.opt_column_.translate(vIrCollector);
                    assert (this.column_name_1_ != null);
                    tmp2 = this.column_name_1_.translate(vIrCollector);
                    res = new IR(kAlterAction, new IROperator("DROP", "", ""), tmp1, tmp2);
                    break;
                case 4:
                    assert (this.alter_constant_action_ != null);
                    tmp1 = this.alter_constant_action_.translate(vIrCollector);
                    res = new IR(kAlterAction, new IROperator("", "", ""), tmp1);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 5;
            switch (this.caseIdx) {
                case 0:
                    this.table_name_ = new TableName();
                    this.table_name_.generate();
                    break;
                case 1:
                    this.opt_column_ = new OptColumn();
                    this.opt_column_.generate();
                    this.column_name_1_ = new ColumnName();
                    this.column_name_1_.generate();
                    this.column_name_2_ = new ColumnName();
                    this.column_name_2_.generate();
                    break;
                case 2:
                    this.opt_column_ = new OptColumn();
                    this.opt_column_.generate();
                    this.column_def_ = new ColumnDef();
                    this.column_def_.generate();
                    break;
                case 3:
                    this.opt_column_ = new OptColumn();
                    this.opt_column_.generate();
                    this.column_name_1_ = new ColumnName();
                    this.column_name_1_.generate();
                    break;
                case 4:
                    this.alter_constant_action_ = new AlterConstantAction();
                    this.alter_constant_action_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.column_def_ = null;
            this.alter_constant_action_ = null;
            this.opt_column_ = null;
            this.table_name_ = null;
            this.column_name_1_ = null;
            this.column_name_2_ = null;
        }
    }

    class AlterConstantAction extends Node {
        LockOption lock_option_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kAlterConstantAction, new IROperator("DROP PRIMARY KEY", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kAlterConstantAction, new IROperator("FORCE", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kAlterConstantAction, new IROperator("DISABLE KEYS", "", ""), null, null);
                    break;
                case 3:
                    res = new IR(kAlterConstantAction, new IROperator("ENABLE KEYS", "", ""), null, null);
                    break;
                case 4:
                    assert (this.lock_option_ != null);
                    IR tmp1 = this.lock_option_.translate(vIrCollector);
                    res = new IR(kAlterConstantAction, new IROperator("", "", ""), tmp1);
                    break;
                case 5:
                    res = new IR(kAlterConstantAction, new IROperator("WITH VALIDATION", "", ""), null, null);
                    break;
                case 6:
                    res = new IR(kAlterConstantAction, new IROperator("WITHOUT VALIDATION", "", ""), null, null);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 7;
            switch (this.caseIdx) {
                case 0:
                case 1:
                case 2:
                case 3:
                    break;
                case 4:
                    this.lock_option_ = new LockOption();
                    this.lock_option_.generate();
                    break;
                case 5:
                case 6:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.lock_option_ = null;
        }
    }

    class ColumnDefList extends Node {


        ColumnDef column_def_;
        ColumnDefList column_def_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.column_def_ != null);
                    tmp1 = this.column_def_.translate(vIrCollector);
                    res = new IR(kColumnDefList, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.column_def_ != null);
                    tmp1 = this.column_def_.translate(vIrCollector);
                    assert (this.column_def_list_ != null);
                    tmp2 = this.column_def_list_.translate(vIrCollector);
                    res = new IR(kColumnDefList, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 200;
            switch (this.caseIdx) {
                case 0:
                    this.column_def_ = new ColumnDef();
                    this.column_def_.generate();
                    break;
                case 1:
                    this.column_def_ = new ColumnDef();
                    this.column_def_.generate();
                    this.column_def_list_ = new ColumnDefList();
                    this.column_def_list_.generate();
                    break;
                default:
                    this.caseIdx = 0;
                    this.column_def_ = new ColumnDef();
                    this.column_def_.generate();
            }
        }

        @Override
        public void deepDelete() {
            this.column_def_ = null;
            this.column_def_list_ = null;
        }
    }

    class ColumnDef extends Node {
        TypeName type_name_;
        Identifier identifier_;
        OptColumnConstraintList opt_column_constraint_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            assert (this.identifier_ != null);
            IR tmp1 = this.identifier_.translate(vIrCollector);
            assert (this.type_name_ != null);
            IR tmp2 = this.type_name_.translate(vIrCollector);
            assert (this.opt_column_constraint_list_ != null);
            IR tmp3 = this.opt_column_constraint_list_.translate(vIrCollector);
            IR tmp4 = new IR(kUnknown, new IROperator("", "", ""), tmp1, tmp2);
            vIrCollector.add(tmp4);
            res = new IR(kUnknown, new IROperator("", "", ""), tmp4, tmp3);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = 0;
            this.identifier_ = new Identifier();
            this.identifier_.generate();
            this.type_name_ = new TypeName();
            this.type_name_.generate();
            this.opt_column_constraint_list_ = new OptColumnConstraintList();
            this.opt_column_constraint_list_.generate();
        }

        @Override
        public void deepDelete() {
            this.identifier_ = null;
            this.type_name_ = null;
            this.opt_column_constraint_list_ = null;
        }
    }

    class OptColumnConstraintList extends Node {
        ColumnConstraintList column_constraint_list_;
        OptReferenceClause opt_reference_clause_;
        OptCheck opt_check_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;
            switch (this.caseIdx) {
                case 0:
                    assert (this.column_constraint_list_ != null);
                    tmp1 = this.column_constraint_list_.translate(vIrCollector);
                    assert (this.opt_check_ != null);
                    tmp2 = this.opt_check_.translate(vIrCollector);
                    assert (this.opt_reference_clause_ != null);
                    tmp3 = this.opt_reference_clause_.translate(vIrCollector);
                    tmp4 = new IR(kUnknown, new IROperator("", "", ""), tmp1, tmp2);
                    vIrCollector.add(tmp4);
                    res = new IR(kOptColumnConstraintList, new IROperator("", "", ""), tmp4, tmp3);
                    break;
                case 1:
                    res = new IR(kOptColumnConstraintList, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.column_constraint_list_ = new ColumnConstraintList();
                    this.column_constraint_list_.generate();
                    this.opt_check_ = new OptCheck();
                    this.opt_check_.generate();
                    this.opt_reference_clause_ = new OptReferenceClause();
                    this.opt_reference_clause_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.column_constraint_list_ = null;
            this.opt_reference_clause_ = null;
            this.opt_check_ = null;
        }
    }

    class ColumnConstraintList extends Node {
        ColumnConstraintList column_constraint_list_;
        ColumnConstraint column_constraint_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.column_constraint_ != null);
                    tmp1 = this.column_constraint_.translate(vIrCollector);
                    res = new IR(kColumnConstraintList, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.column_constraint_ != null);
                    tmp1 = this.column_constraint_.translate(vIrCollector);
                    assert (this.column_constraint_list_ != null);
                    tmp2 = this.column_constraint_list_.translate(vIrCollector);
                    res = new IR(kColumnConstraintList, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.column_constraint_ = new ColumnConstraint();
                    this.column_constraint_.generate();
                    break;
                case 1:
                    this.column_constraint_ = new ColumnConstraint();
                    this.column_constraint_.generate();
                    this.column_constraint_list_ = new ColumnConstraintList();
                    this.column_constraint_list_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.column_constraint_ = null;
            this.column_constraint_list_ = null;
        }
    }

    class ColumnConstraint extends Node {
        ConstraintType constraint_type_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;

            assert (this.constraint_type_ != null);
            IR tmp1 = this.constraint_type_.translate(vIrCollector);
            res = new IR(kColumnConstraint, new IROperator("", "", ""), tmp1);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = 0;
            this.constraint_type_ = new ConstraintType();
            this.constraint_type_.generate();
        }

        @Override
        public void deepDelete() {
            this.constraint_type_ = null;
        }
    }

    class OptReferenceClause extends Node {
        ReferenceClause reference_clause_;
        OptForeignKey opt_foreign_key_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.opt_foreign_key_ != null);
                    tmp1 = this.opt_foreign_key_.translate(vIrCollector);
                    assert (this.reference_clause_ != null);
                    tmp2 = this.reference_clause_.translate(vIrCollector);
                    res = new IR(kOptReferenceClause, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                case 1:
                    res = new IR(kOptReferenceClause, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.opt_foreign_key_ = new OptForeignKey();
                    this.opt_foreign_key_.generate();
                    this.reference_clause_ = new ReferenceClause();
                    this.reference_clause_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.opt_foreign_key_ = null;
            this.reference_clause_ = null;
        }
    }

    class OptCheck extends Node {
        OptEnforced opt_enforced_;
        Expr expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.expr_ != null);
                    tmp1 = this.expr_.translate(vIrCollector);
                    assert (this.opt_enforced_ != null);
                    tmp2 = this.opt_enforced_.translate(vIrCollector);
                    res = new IR(kOptCheck, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                case 1:
                    res = new IR(kOptCheck, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.expr_ = new Expr();
                    this.expr_.generate();
                    this.opt_enforced_ = new OptEnforced();
                    this.opt_enforced_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.expr_ = null;
            this.opt_enforced_ = null;
        }
    }

    class ConstraintType extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kConstraintType, new IROperator("PRIMARY KEY", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kConstraintType, new IROperator("NOT NULL", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kConstraintType, new IROperator("UNIQUE", "", ""), null, null);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
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
            IR res;

            assert (this.table_name_ != null);
            IR tmp1 = this.table_name_.translate(vIrCollector);
            assert (this.opt_column_name_list_p_ != null);
            IR tmp2 = this.opt_column_name_list_p_.translate(vIrCollector);
            assert (this.opt_foreign_key_actions_ != null);
            IR tmp3 = this.opt_foreign_key_actions_.translate(vIrCollector);
            assert (this.opt_constraint_attribute_spec_ != null);
            IR tmp4 = this.opt_constraint_attribute_spec_.translate(vIrCollector);
            IR tmp5 = new IR(kUnknown, new IROperator("REFERENCES", "", ""), tmp1, tmp2);
            vIrCollector.add(tmp5);
            IR tmp6 = new IR(kUnknown, new IROperator("", "", ""), tmp5, tmp3);
            vIrCollector.add(tmp6);
            res = new IR(kReferenceClause, new IROperator("", "", ""), tmp6, tmp4);

            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = 0;
            this.table_name_ = new TableName();
            this.table_name_.generate();
            this.opt_column_name_list_p_ = new OptColumnNameListP();
            this.opt_column_name_list_p_.generate();
            this.opt_constraint_attribute_spec_ = new OptConstraintAttributeSpec();
            this.opt_constraint_attribute_spec_.generate();
            this.opt_foreign_key_actions_ = new OptForeignKeyActions();
            this.opt_foreign_key_actions_.generate();
        }

        @Override
        public void deepDelete() {
            this.table_name_ = null;
            this.opt_column_name_list_p_ = null;
            this.opt_constraint_attribute_spec_ = null;
            this.opt_foreign_key_actions_ = null;
        }
    }

    class OptForeignKey extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptForeignKey, new IROperator("FOREIGN", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptForeignKey, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
        }

        @Override
        public void deepDelete() {

        }
    }

    class OptForeignKeyActions extends Node {
        ForeignKeyActions foreign_key_actions_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.foreign_key_actions_ != null);
                    IR tmp1 = this.foreign_key_actions_.translate(vIrCollector);
                    res = new IR(kOptForeignKeyActions, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptForeignKeyActions, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.foreign_key_actions_ = new ForeignKeyActions();
                    this.foreign_key_actions_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.foreign_key_actions_ = null;
        }
    }

    class ForeignKeyActions extends Node {
        KeyActions key_actions_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kForeignKeyActions, new IROperator("MATCH FULL", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kForeignKeyActions, new IROperator("MATCH PARTIAL", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kForeignKeyActions, new IROperator("MATCH SIMPLE", "", ""), null, null);
                    break;
                case 3:
                    assert (this.key_actions_ != null);
                    IR tmp1 = this.key_actions_.translate(vIrCollector);
                    res = new IR(kForeignKeyActions, new IROperator("ON UPDATE", "", ""), tmp1);
                    break;
                case 4:
                    assert (this.key_actions_ != null);
                    IR tmp2 = this.key_actions_.translate(vIrCollector);
                    res = new IR(kForeignKeyActions, new IROperator("ON DELETE", "", ""), tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                case 1:
                case 2:
                    break;
                case 3:
                case 4:
                    this.key_actions_ = new KeyActions();
                    this.key_actions_.generate();
                    break;
                default:
                    throw new AssertionError();
            }

        }

        @Override
        public void deepDelete() {
            this.key_actions_ = null;
        }
    }

    class KeyActions extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kKeyActions, new IROperator("SET NULL", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kKeyActions, new IROperator("SET DEFAULT", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kKeyActions, new IROperator("CASCADE", "", ""), null, null);
                    break;
                case 3:
                    res = new IR(kKeyActions, new IROperator("RESTRICT", "", ""), null, null);
                    break;
                case 4:
                    res = new IR(kKeyActions, new IROperator("NO ACTION", "", ""), null, null);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 5;
        }

        @Override
        public void deepDelete() {

        }
    }

    class OptConstraintAttributeSpec extends Node {
        OptInitialTime opt_initial_time_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.opt_initial_time_ != null);
                    tmp1 = this.opt_initial_time_.translate(vIrCollector);
                    res = new IR(kOptConstraintAttributeSpec, new IROperator("DEFFERRABLE", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.opt_initial_time_ != null);
                    tmp1 = this.opt_initial_time_.translate(vIrCollector);
                    res = new IR(kOptConstraintAttributeSpec, new IROperator("NOT DEFFERRABLE", "", ""), tmp1);
                    break;
                case 2:
                    res = new IR(kOptConstraintAttributeSpec, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
            switch (this.caseIdx) {
                case 0:
                case 1:
                    this.opt_initial_time_ = new OptInitialTime();
                    this.opt_initial_time_.generate();
                    break;
                case 2:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.opt_initial_time_ = null;
        }
    }

    class OptInitialTime extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptInitialTime, new IROperator("INITIALLY DEFERRED", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptInitialTime, new IROperator("INITIALLY IMMEDIATE", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kOptInitialTime, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
        }

        @Override
        public void deepDelete() {

        }
    }

    class ConstraintName extends Node {


        Name name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            assert (this.name_ != null);
            IR tmp1 = this.name_.translate(vIrCollector);
            res = new IR(kConstraintName, new IROperator("CONSTRAINT", "", ""), tmp1);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.name_ = new Name();
            this.name_.generate();
        }

        @Override
        public void deepDelete() {
            this.name_ = null;
        }
    }

    class OptTemp extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptTemp, new IROperator("TEMPORARY", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptTemp, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
        }

        @Override
        public void deepDelete() {

        }
    }

    class OptCheckOption extends Node {
        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    res = new IR(kOptCheckOption, new IROperator("WITH CHECK OPTION", "", ""), null, null);
                    break;
                case 1:
                    res = new IR(kOptCheckOption, new IROperator("WITH CASCADED CHECK OPTION", "", ""), null, null);
                    break;
                case 2:
                    res = new IR(kOptCheckOption, new IROperator("WITH LOCAL CHECK OPTION", "", ""), null, null);
                    break;
                case 3:
                    res = new IR(kOptCheckOption, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 4;
        }

        @Override
        public void deepDelete() {

        }
    }

    class OptColumnNameListP extends Node {
        ColumnNameList column_name_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.column_name_list_ != null);
                    IR tmp1 = this.column_name_list_.translate(vIrCollector);
                    res = new IR(kOptColumnNameListP, new IROperator("(", ")", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptColumnNameListP, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.column_name_list_ = new ColumnNameList();
                    this.column_name_list_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.column_name_list_ = null;
        }
    }

    class SetClauseList extends Node {
        SetClause set_clause_;
        SetClauseList set_clause_list_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.set_clause_ != null);
                    tmp1 = this.set_clause_.translate(vIrCollector);
                    res = new IR(kSetClauseList, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.set_clause_ != null);
                    tmp1 = this.set_clause_.translate(vIrCollector);
                    assert (this.set_clause_list_ != null);
                    tmp2 = this.set_clause_list_.translate(vIrCollector);
                    res = new IR(kSetClauseList, new IROperator("", "", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 200;
            switch (this.caseIdx) {
                case 0:
                    this.set_clause_ = new SetClause();
                    this.set_clause_.generate();
                    break;
                case 1:
                    this.set_clause_ = new SetClause();
                    this.set_clause_.generate();
                    this.set_clause_list_ = new SetClauseList();
                    this.set_clause_list_.generate();
                    break;
                default:
                    this.caseIdx = 0;
                    this.set_clause_ = new SetClause();
                    this.set_clause_.generate();
            }
        }

        @Override
        public void deepDelete() {
            this.set_clause_ = null;
            this.set_clause_list_ = null;
        }
    }

    class SetClause extends Node {
        Expr expr_;
        ColumnNameList column_name_list_;
        ColumnName column_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.column_name_ != null);
                    tmp1 = this.column_name_.translate(vIrCollector);
                    assert (this.expr_ != null);
                    tmp2 = this.expr_.translate(vIrCollector);
                    res = new IR(kSetClause, new IROperator("", "=", ""), tmp1, tmp2);
                    break;
                case 1:
                    assert (this.column_name_list_ != null);
                    tmp1 = this.column_name_list_.translate(vIrCollector);
                    assert (this.expr_ != null);
                    tmp2 = this.expr_.translate(vIrCollector);
                    res = new IR(kSetClause, new IROperator("(", ") =", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.column_name_ = new ColumnName();
                    this.column_name_.generate();
                    this.expr_ = new Expr();
                    this.expr_.generate();
                    break;
                case 1:
                    this.column_name_list_ = new ColumnNameList();
                    this.column_name_list_.generate();
                    this.expr_ = new Expr();
                    this.expr_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.column_name_list_ = null;
            this.expr_ = null;
            this.column_name_ = null;
        }
    }

    class OptAsAlias extends Node {
        AsAlias as_alias_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.as_alias_ != null);
                    IR tmp1 = this.as_alias_.translate(vIrCollector);
                    res = new IR(kOptAsAlias, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    res = new IR(kOptAsAlias, "");
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.as_alias_ = new AsAlias();
                    this.as_alias_.generate();
                    break;
                case 1:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.as_alias_ = null;
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
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.operand_ != null);
                    tmp1 = this.operand_.translate(vIrCollector);
                    res = new IR(kExpr, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.between_expr_ != null);
                    tmp1 = this.between_expr_.translate(vIrCollector);
                    res = new IR(kExpr, new IROperator("", "", ""), tmp1);
                    break;
                case 2:
                    assert (this.exists_expr_ != null);
                    tmp1 = this.exists_expr_.translate(vIrCollector);
                    res = new IR(kExpr, new IROperator("", "", ""), tmp1);
                    break;
                case 3:
                    assert (this.in_expr_ != null);
                    tmp1 = this.in_expr_.translate(vIrCollector);
                    res = new IR(kExpr, new IROperator("", "", ""), tmp1);
                    break;
                case 4:
                    assert (this.cast_expr_ != null);
                    tmp1 = this.cast_expr_.translate(vIrCollector);
                    res = new IR(kExpr, new IROperator("", "", ""), tmp1);
                    break;
                case 5:
                    assert (this.logic_expr_ != null);
                    tmp1 = this.logic_expr_.translate(vIrCollector);
                    res = new IR(kExpr, new IROperator("", "", ""), tmp1);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 6;
            switch (this.caseIdx) {
                case 0:
                    this.operand_ = new Operand();
                    this.operand_.generate();
                    break;
                case 1:
                    this.between_expr_ = new BetweenExpr();
                    this.between_expr_.generate();
                    break;
                case 2:
                    this.exists_expr_ = new ExistsExpr();
                    this.exists_expr_.generate();
                    break;
                case 3:
                    this.in_expr_ = new InExpr();
                    this.in_expr_.generate();
                    break;
                case 4:
                    this.cast_expr_ = new CastExpr();
                    this.cast_expr_.generate();
                    break;
                case 5:
                    this.logic_expr_ = new LogicExpr();
                    this.logic_expr_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.cast_expr_ = null;
            this.in_expr_ = null;
            this.between_expr_ = null;
            this.operand_ = null;
            this.exists_expr_ = null;
            this.logic_expr_ = null;
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
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.expr_list_ != null);
                    tmp1 = this.expr_list_.translate(vIrCollector);
                    res = new IR(kOperand, new IROperator("(", ")", ""), tmp1);
                    break;
                case 1:
                    assert (this.array_index_ != null);
                    tmp1 = this.array_index_.translate(vIrCollector);
                    res = new IR(kOperand, new IROperator("", "", ""), tmp1);
                    break;
                case 2:
                    assert (this.scalar_expr_ != null);
                    tmp1 = this.scalar_expr_.translate(vIrCollector);
                    res = new IR(kOperand, new IROperator("", "", ""), tmp1);
                    break;
                case 3:
                    assert (this.unary_expr_ != null);
                    tmp1 = this.unary_expr_.translate(vIrCollector);
                    res = new IR(kOperand, new IROperator("", "", ""), tmp1);
                    break;
                case 4:
                    assert (this.binary_expr_ != null);
                    tmp1 = this.binary_expr_.translate(vIrCollector);
                    res = new IR(kOperand, new IROperator("", "", ""), tmp1);
                    break;
                case 5:
                    assert (this.case_expr_ != null);
                    tmp1 = this.case_expr_.translate(vIrCollector);
                    res = new IR(kOperand, new IROperator("", "", ""), tmp1);
                    break;
                case 6:
                    assert (this.extract_expr_ != null);
                    tmp1 = this.extract_expr_.translate(vIrCollector);
                    res = new IR(kOperand, new IROperator("", "", ""), tmp1);
                    break;
                case 7:
                    assert (this.array_expr_ != null);
                    tmp1 = this.array_expr_.translate(vIrCollector);
                    res = new IR(kOperand, new IROperator("", "", ""), tmp1);
                    break;
                case 8:
                    assert (this.function_expr_ != null);
                    tmp1 = this.function_expr_.translate(vIrCollector);
                    res = new IR(kOperand, new IROperator("", "", ""), tmp1);
                    break;
                case 9:
                    assert (this.select_no_parens_ != null);
                    tmp1 = this.select_no_parens_.translate(vIrCollector);
                    res = new IR(kOperand, new IROperator("(", ")", ""), tmp1);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 10;
            switch (this.caseIdx) {
                case 0:
                    this.expr_list_ = new ExprList();
                    this.expr_list_.generate();
                    break;
                case 1:
                    this.array_index_ = new ArrayIndex();
                    this.array_index_.generate();
                    break;
                case 2:
                    this.scalar_expr_ = new ScalarExpr();
                    this.scalar_expr_.generate();
                    break;
                case 3:
                    this.unary_expr_ = new UnaryExpr();
                    this.unary_expr_.generate();
                    break;
                case 4:
                    this.binary_expr_ = new BinaryExpr();
                    this.binary_expr_.generate();
                    break;
                case 5:
                    this.case_expr_ = new CaseExpr();
                    this.case_expr_.generate();
                    break;
                case 6:
                    this.extract_expr_ = new ExtractExpr();
                    this.extract_expr_.generate();
                    break;
                case 7:
                    this.array_expr_ = new ArrayExpr();
                    this.array_expr_.generate();
                    break;
                case 8:
                    this.function_expr_ = new FunctionExpr();
                    this.function_expr_.generate();
                    break;
                case 9:
                    this.select_no_parens_ = new SelectNoParens();
                    this.select_no_parens_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.expr_list_ = null;
            this.array_index_ = null;
            this.scalar_expr_ = null;
            this.unary_expr_ = null;
            this.binary_expr_ = null;
            this.case_expr_ = null;
            this.extract_expr_ = null;
            this.array_expr_ = null;
            this.function_expr_ = null;
            this.select_no_parens_ = null;
        }
    }

    class CastExpr extends Node {
        Expr expr_;
        TypeName type_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;

            assert (this.expr_ != null);
            IR tmp1 = this.expr_.translate(vIrCollector);
            assert (this.type_name_ != null);
            IR tmp2 = this.type_name_.translate(vIrCollector);
            res = new IR(kCastExpr, new IROperator("CAST (", "AS", ")"), tmp1, tmp2);
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = 0;
            this.expr_ = new Expr();
            this.expr_.generate();
            this.type_name_ = new TypeName();
            this.type_name_.generate();
        }

        @Override
        public void deepDelete() {
            this.expr_ = null;
            this.type_name_ = null;
        }
    }

    class ScalarExpr extends Node {
        Literal literal_;
        ColumnName column_name_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            switch (this.caseIdx) {
                case 0:
                    assert (this.column_name_ != null);
                    IR tmp1 = this.column_name_.translate(vIrCollector);
                    res = new IR(kScalarExpr, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.literal_ != null);
                    IR tmp2 = this.literal_.translate(vIrCollector);
                    res = new IR(kScalarExpr, new IROperator("", "", ""), tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                    this.column_name_ = new ColumnName();
                    this.column_name_.generate();
                    break;
                case 1:
                    this.literal_ = new Literal();
                    this.literal_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.column_name_ = null;
            this.literal_ = null;
        }
    }

    class UnaryExpr extends Node {
        Operand operand_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            switch (this.caseIdx) {
                case 0:
                    assert (this.operand_ != null);
                    tmp1 = this.operand_.translate(vIrCollector);
                    res = new IR(kUnaryExpr, new IROperator("-", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.operand_ != null);
                    tmp1 = this.operand_.translate(vIrCollector);
                    res = new IR(kUnaryExpr, new IROperator("NOT", "", ""), tmp1);
                    break;
                case 2:
                    assert (this.operand_ != null);
                    tmp1 = this.operand_.translate(vIrCollector);
                    res = new IR(kUnaryExpr, new IROperator("", "ISNULL", ""), tmp1);
                    break;
                case 3:
                    assert (this.operand_ != null);
                    tmp1 = this.operand_.translate(vIrCollector);
                    res = new IR(kUnaryExpr, new IROperator("", "IS NULL", ""), tmp1);
                    break;
                case 4:
                    assert (this.operand_ != null);
                    tmp1 = this.operand_.translate(vIrCollector);
                    res = new IR(kUnaryExpr, new IROperator("", "IS NOT NULL", ""), tmp1);
                    break;
                case 5:
                    res = new IR(kUnaryExpr, new IROperator("NULL", "", ""), null);
                    break;
                case 6:
                    res = new IR(kUnaryExpr, new IROperator("*", "", ""), null);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 7;
            switch (this.caseIdx) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    this.operand_ = new Operand();
                    this.operand_.generate();
                    break;
                case 5:
                case 6:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.operand_ = null;
        }
    }

    class BinaryExpr extends Node {
        Operand operand_1_;
        Operand operand_2_;
        BinaryOp binary_op_;
        CompExpr comp_expr_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;
            switch (this.caseIdx) {
                case 0:
                    assert (this.comp_expr_ != null);
                    tmp1 = this.comp_expr_.translate(vIrCollector);
                    res = new IR(kBinaryExpr, new IROperator("", "", ""), tmp1);
                    break;
                case 1:
                    assert (this.operand_1_ != null);
                    tmp1 = this.operand_1_.translate(vIrCollector);
                    assert (this.binary_op_ != null);
                    tmp2 = this.binary_op_.translate(vIrCollector);
                    assert (this.operand_2_ != null);
                    tmp3 = this.operand_2_.translate(vIrCollector);
                    tmp4 = new IR(kBinaryExpr, new IROperator("", "", ""), tmp1, tmp2);
                    vIrCollector.add(tmp4);
                    res = new IR(kBinaryExpr, new IROperator("", "", ""), tmp4, tmp3);
                    break;
                case 2:
                    assert (this.operand_1_ != null);
                    tmp1 = this.operand_1_.translate(vIrCollector);
                    assert (this.operand_2_ != null);
                    tmp2 = this.operand_2_.translate(vIrCollector);
                    res = new IR(kBinaryExpr, new IROperator("", "LIKE", ""), tmp1, tmp2);
                    break;
                case 3:
                    assert (this.operand_1_ != null);
                    tmp1 = this.operand_1_.translate(vIrCollector);
                    assert (this.operand_2_ != null);
                    tmp2 = this.operand_2_.translate(vIrCollector);
                    res = new IR(kBinaryExpr, new IROperator("", "NOT LIKE", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 4;
            switch (this.caseIdx) {
                case 0:
                    this.comp_expr_ = new CompExpr();
                    this.comp_expr_.generate();
                    break;
                case 1:
                    this.operand_1_ = new Operand();
                    this.operand_1_.generate();
                    this.binary_op_ = new BinaryOp();
                    this.binary_op_.generate();
                    this.operand_2_ = new Operand();
                    this.operand_2_.generate();
                    break;
                case 2:
                    this.operand_1_ = new Operand();
                    this.operand_1_.generate();
                    this.operand_2_ = new Operand();
                    this.operand_2_.generate();
                    break;
                case 3:
                    this.operand_1_ = new Operand();
                    this.operand_1_.generate();
                    this.operand_2_ = new Operand();
                    this.operand_2_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.operand_1_ = null;
            this.operand_2_ = null;
            this.binary_op_ = null;
            this.comp_expr_ = null;
        }
    }

    class LogicExpr extends Node {
        Expr expr_1_;
        Expr expr_2_;

        @Override
        public IR translate(List<IR> vIrCollector) {
            IR res;
            IR tmp1;
            IR tmp2;
            switch (this.caseIdx) {
                case 0:
                    assert (this.expr_1_ != null);
                    tmp1 = this.expr_1_.translate(vIrCollector);
                    assert (this.expr_2_ != null);
                    tmp2 = this.expr_2_.translate(vIrCollector);
                    res = new IR(kLogicExpr, new IROperator("", "AND", ""), tmp1, tmp2);
                    break;
                case 1:
                    assert (this.expr_1_ != null);
                    tmp1 = this.expr_1_.translate(vIrCollector);
                    assert (this.expr_2_ != null);
                    tmp2 = this.expr_2_.translate(vIrCollector);
                    res = new IR(kLogicExpr, new IROperator("", "OR", ""), tmp1, tmp2);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 2;
            switch (this.caseIdx) {
                case 0:
                case 1:
                    this.expr_1_ = new Expr();
                    this.expr_1_.generate();
                    this.expr_2_ = new Expr();
                    this.expr_2_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.expr_1_ = null;
            this.expr_2_ = null;
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
            IR res;
            IR tmp1;
            IR tmp2;
            IR tmp3;
            IR tmp4;
            switch (this.caseIdx) {
                case 0:
                    assert (this.operand_ != null);
                    tmp1 = this.operand_.translate(vIrCollector);
                    assert (this.opt_not_ != null);
                    tmp2 = this.opt_not_.translate(vIrCollector);
                    assert (this.select_no_parens_ != null);
                    tmp3 = this.select_no_parens_.translate(vIrCollector);
                    tmp4 = new IR(kUnknown, new IROperator("", "", "IN"), tmp1, tmp2);
                    vIrCollector.add(tmp4);
                    res = new IR(kInExpr, new IROperator("", "(", ")"), tmp4, tmp3);
                    break;
                case 1:
                    assert (this.operand_ != null);
                    tmp1 = this.operand_.translate(vIrCollector);
                    assert (this.opt_not_ != null);
                    tmp2 = this.opt_not_.translate(vIrCollector);
                    assert (this.expr_list_ != null);
                    tmp3 = this.expr_list_.translate(vIrCollector);
                    tmp4 = new IR(kUnknown, new IROperator("", "", "IN"), tmp1, tmp2);
                    vIrCollector.add(tmp4);
                    res = new IR(kInExpr, new IROperator("", "(", ")"), tmp4, tmp3);
                    break;
                case 2:
                    assert (this.operand_ != null);
                    tmp1 = this.operand_.translate(vIrCollector);
                    assert (this.opt_not_ != null);
                    tmp2 = this.opt_not_.translate(vIrCollector);
                    assert (this.table_name_ != null);
                    tmp3 = this.table_name_.translate(vIrCollector);
                    tmp4 = new IR(kUnknown, new IROperator("", "", "IN"), tmp1, tmp2);
                    vIrCollector.add(tmp4);
                    res = new IR(kInExpr, new IROperator("", "(", ")"), tmp4, tmp3);
                    break;
                default:
                    throw new AssertionError();
            }
            vIrCollector.add(res);
            return res;
        }

        @Override
        public void generate() {
            this.caseIdx = random.nextInt() % 3;
            switch (this.caseIdx) {
                case 0:
                    this.operand_ = new Operand();
                    this.operand_.generate();
                    this.opt_not_ = new OptNot();
                    this.opt_not_.generate();
                    this.select_no_parens_ = new SelectNoParens();
                    this.select_no_parens_.generate();
                    break;
                case 1:
                    this.operand_ = new Operand();
                    this.operand_.generate();
                    this.opt_not_ = new OptNot();
                    this.opt_not_.generate();
                    this.expr_list_ = new ExprList();
                    this.expr_list_.generate();
                    break;
                case 2:
                    this.operand_ = new Operand();
                    this.operand_.generate();
                    this.opt_not_ = new OptNot();
                    this.opt_not_.generate();
                    this.table_name_ = new TableName();
                    this.table_name_.generate();
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public void deepDelete() {
            this.operand_ = null;
            this.expr_list_ = null;
            this.opt_not_ = null;
            this.table_name_ = null;
            this.select_no_parens_ = null;
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
