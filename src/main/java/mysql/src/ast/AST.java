package mysql.src.ast;

import mysql.src.ast.node.Node;
import mysql.src.ast.node.*;


import static mysql.src.ast.DataType.*;
import static mysql.src.ast.NodeType.*;

public class AST {
    public static long g_id_counter;

    public static String gen_id_name() {
        return "v" + g_id_counter++;
    }

    public static void reset_id_counter() {
        g_id_counter = 0;
    }

    public static Node generate_ast_node_by_type(IRType type) {
        if (type == IRType.kProgram)
            return new astNode.Program();
        if (type == IRType.kStmtlist)
            return new astNode.StmtList();
        if (type == IRType.kStmt)
            return new astNode.Stmt();
        if (type == IRType.kCreateStmt)
            return new astNode.CreateStmt();
        if (type == IRType.kDropStmt)
            return new astNode.DropStmt();
        if (type == IRType.kAlterStmt)
            return new astNode.AlterStmt();
        if (type == IRType.kSelectStmt)
            return new astNode.SelectStmt();
        if (type == IRType.kSelectWithParens)
            return new astNode.SelectWithParens();
        if (type == IRType.kSelectNoParens)
            return new astNode.SelectNoParens();
        if (type == IRType.kSelectClauseList)
            return new astNode.SelectClauseList();
        if (type == IRType.kSelectClause)
            return new astNode.SelectClause();
        if (type == IRType.kCombineClause)
            return new astNode.CombineClause();
        if (type == IRType.kOptFromClause)
            return new astNode.OptFromClause();
        if (type == IRType.kSelectTarget)
            return new astNode.SelectTarget();
        if (type == IRType.kOptWindowClause)
            return new astNode.OptWindowClause();
        if (type == IRType.kWindowClause)
            return new astNode.WindowClause();
        if (type == IRType.kWindowDefList)
            return new astNode.WindowDefList();
        if (type == IRType.kWindowDef)
            return new astNode.WindowDef();
        if (type == IRType.kWindowName)
            return new astNode.WindowName();
        if (type == IRType.kWindow)
            return new astNode.Window();
        if (type == IRType.kOptPartition)
            return new astNode.OptPartition();
        if (type == IRType.kOptFrameClause)
            return new astNode.OptFrameClause();
        if (type == IRType.kRangeOrRows)
            return new astNode.RangeOrRows();
        if (type == IRType.kFrameBoundStart)
            return new astNode.FrameBoundStart();
        if (type == IRType.kFrameBoundEnd)
            return new astNode.FrameBoundEnd();
        if (type == IRType.kFrameBound)
            return new astNode.FrameBound();
        if (type == IRType.kOptExistWindowName)
            return new astNode.OptExistWindowName();
        if (type == IRType.kOptGroupClause)
            return new astNode.OptGroupClause();
        if (type == IRType.kOptHavingClause)
            return new astNode.OptHavingClause();
        if (type == IRType.kOptWhereClause)
            return new astNode.OptWhereClause();
        if (type == IRType.kWhereClause)
            return new astNode.WhereClause();
        if (type == IRType.kFromClause)
            return new astNode.FromClause();
        if (type == IRType.kTableRef)
            return new astNode.TableRef();
        if (type == IRType.kOptIndex)
            return new astNode.OptIndex();
        if (type == IRType.kOptOn)
            return new astNode.OptOn();
        if (type == IRType.kOptUsing)
            return new astNode.OptUsing();
        if (type == IRType.kColumnNameList)
            return new astNode.ColumnNameList();
        if (type == IRType.kOptTablePrefix)
            return new astNode.OptTablePrefix();
        if (type == IRType.kJoinOp)
            return new astNode.JoinOp();
        if (type == IRType.kOptJoinType)
            return new astNode.OptJoinType();
        if (type == IRType.kExprList)
            return new astNode.ExprList();
        if (type == IRType.kOptLimitClause)
            return new astNode.OptLimitClause();
        if (type == IRType.kLimitClause)
            return new astNode.LimitClause();
        if (type == IRType.kOptLimitRowCount)
            return new astNode.OptLimitRowCount();
        if (type == IRType.kOptOrderClause)
            return new astNode.OptOrderClause();
        if (type == IRType.kOptOrderNulls)
            return new astNode.OptOrderNulls();
        if (type == IRType.kOrderItemList)
            return new astNode.OrderItemList();
        if (type == IRType.kOrderItem)
            return new astNode.OrderItem();
        if (type == IRType.kOptOrderBehavior)
            return new astNode.OptOrderBehavior();
        if (type == IRType.kOptWithClause)
            return new astNode.OptWithClause();
        if (type == IRType.kCteTableList)
            return new astNode.CteTableList();
        if (type == IRType.kCteTable)
            return new astNode.CteTable();
        if (type == IRType.kCteTableName)
            return new astNode.CteTableName();
        if (type == IRType.kOptAllOrDistinct)
            return new astNode.OptAllOrDistinct();
        if (type == IRType.kCreateTableStmt)
            return new astNode.CreateTableStmt();
        if (type == IRType.kCreateIndexStmt)
            return new astNode.CreateIndexStmt();
        if (type == IRType.kCreateTriggerStmt)
            return new astNode.CreateTriggerStmt();
        if (type == IRType.kCreateViewStmt)
            return new astNode.CreateViewStmt();
        if (type == IRType.kOptTableOptionList)
            return new astNode.OptTableOptionList();
        if (type == IRType.kTableOption)
            return new astNode.TableOption();
        if (type == IRType.kOptOpComma)
            return new astNode.OptOpComma();
        if (type == IRType.kOptIgnoreOrReplace)
            return new astNode.OptIgnoreOrReplace();
        if (type == IRType.kOptViewAlgorithm)
            return new astNode.OptViewAlgorithm();
        if (type == IRType.kOptSqlSecurity)
            return new astNode.OptSqlSecurity();
        if (type == IRType.kOptIndexOption)
            return new astNode.OptIndexOption();
        if (type == IRType.kOptExtraOption)
            return new astNode.OptExtraOption();
        if (type == IRType.kIndexAlgorithmOption)
            return new astNode.IndexAlgorithmOption();
        if (type == IRType.kLockOption)
            return new astNode.LockOption();
        if (type == IRType.kOptOpEqual)
            return new astNode.OptOpEqual();
        if (type == IRType.kTriggerEvents)
            return new astNode.TriggerEvents();
        if (type == IRType.kTriggerName)
            return new astNode.TriggerName();
        if (type == IRType.kTriggerActionTime)
            return new astNode.TriggerActionTime();
        if (type == IRType.kDropIndexStmt)
            return new astNode.DropIndexStmt();
        if (type == IRType.kDropTableStmt)
            return new astNode.DropTableStmt();
        if (type == IRType.kOptRestrictOrCascade)
            return new astNode.OptRestrictOrCascade();
        if (type == IRType.kDropTriggerStmt)
            return new astNode.DropTriggerStmt();
        if (type == IRType.kDropViewStmt)
            return new astNode.DropViewStmt();
        if (type == IRType.kInsertStmt)
            return new astNode.InsertStmt();
        if (type == IRType.kInsertRest)
            return new astNode.InsertRest();
        if (type == IRType.kSuperValuesList)
            return new astNode.SuperValuesList();
        if (type == IRType.kValuesList)
            return new astNode.ValuesList();
        if (type == IRType.kOptOnConflict)
            return new astNode.OptOnConflict();
        if (type == IRType.kOptConflictExpr)
            return new astNode.OptConflictExpr();
        if (type == IRType.kIndexedColumnList)
            return new astNode.IndexedColumnList();
        if (type == IRType.kIndexedColumn)
            return new astNode.IndexedColumn();
        if (type == IRType.kUpdateStmt)
            return new astNode.UpdateStmt();
        if (type == IRType.kAlterAction)
            return new astNode.AlterAction();
        if (type == IRType.kAlterConstantAction)
            return new astNode.AlterConstantAction();
        if (type == IRType.kColumnDefList)
            return new astNode.ColumnDefList();
        if (type == IRType.kColumnDef)
            return new astNode.ColumnDef();
        if (type == IRType.kOptColumnConstraintList)
            return new astNode.OptColumnConstraintList();
        if (type == IRType.kColumnConstraintList)
            return new astNode.ColumnConstraintList();
        if (type == IRType.kColumnConstraint)
            return new astNode.ColumnConstraint();
        if (type == IRType.kOptReferenceClause)
            return new astNode.OptReferenceClause();
        if (type == IRType.kOptCheck)
            return new astNode.OptCheck();
        if (type == IRType.kConstraintType)
            return new astNode.ConstraintType();
        if (type == IRType.kReferenceClause)
            return new astNode.ReferenceClause();
        if (type == IRType.kOptForeignKey)
            return new astNode.OptForeignKey();
        if (type == IRType.kOptForeignKeyActions)
            return new astNode.OptForeignKeyActions();
        if (type == IRType.kForeignKeyActions)
            return new astNode.ForeignKeyActions();
        if (type == IRType.kKeyActions)
            return new astNode.KeyActions();
        if (type == IRType.kOptConstraintAttributeSpec)
            return new astNode.OptConstraintAttributeSpec();
        if (type == IRType.kOptInitialTime)
            return new astNode.OptInitialTime();
        if (type == IRType.kConstraintName)
            return new astNode.ConstraintName();
        if (type == IRType.kOptTemp)
            return new astNode.OptTemp();
        if (type == IRType.kOptCheckOption)
            return new astNode.OptCheckOption();
        if (type == IRType.kOptColumnNameListP)
            return new astNode.OptColumnNameListP();
        if (type == IRType.kSetClauseList)
            return new astNode.SetClauseList();
        if (type == IRType.kSetClause)
            return new astNode.SetClause();
        if (type == IRType.kOptAsAlias)
            return new astNode.OptAsAlias();
        if (type == IRType.kExpr)
            return new astNode.Expr();
        if (type == IRType.kOperand)
            return new astNode.Operand();
        if (type == IRType.kCastExpr)
            return new astNode.CastExpr();
        if (type == IRType.kScalarExpr)
            return new astNode.ScalarExpr();
        if (type == IRType.kUnaryExpr)
            return new astNode.UnaryExpr();
        if (type == IRType.kBinaryExpr)
            return new astNode.BinaryExpr();
        if (type == IRType.kLogicExpr)
            return new astNode.LogicExpr();
        if (type == IRType.kInExpr)
            return new astNode.InExpr();
        if (type == IRType.kCaseExpr)
            return new astNode.CaseExpr();
        if (type == IRType.kBetweenExpr)
            return new astNode.BetweenExpr();
        if (type == IRType.kExistsExpr)
            return new astNode.ExistsExpr();
        if (type == IRType.kFunctionExpr)
            return new astNode.FunctionExpr();
        if (type == IRType.kOptDistinct)
            return new astNode.OptDistinct();
        if (type == IRType.kOptFilterClause)
            return new astNode.OptFilterClause();
        if (type == IRType.kOptOverClause)
            return new astNode.OptOverClause();
        if (type == IRType.kCaseList)
            return new astNode.CaseList();
        if (type == IRType.kCaseClause)
            return new astNode.CaseClause();
        if (type == IRType.kCompExpr)
            return new astNode.CompExpr();
        if (type == IRType.kExtractExpr)
            return new astNode.ExtractExpr();
        if (type == IRType.kDatetimeField)
            return new astNode.DatetimeField();
        if (type == IRType.kArrayExpr)
            return new astNode.ArrayExpr();
        if (type == IRType.kArrayIndex)
            return new astNode.ArrayIndex();
        if (type == IRType.kLiteral)
            return new astNode.Literal();
        if (type == IRType.kStringLiteral)
            return new astNode.StringLiteral();
        if (type == IRType.kBoolLiteral)
            return new astNode.BoolLiteral();
        if (type == IRType.kNumLiteral)
            return new astNode.NumLiteral();
        if (type == IRType.kIntLiteral)
            return new astNode.IntLiteral();
        if (type == IRType.kFloatLiteral)
            return new astNode.FloatLiteral();
        if (type == IRType.kOptColumn)
            return new astNode.OptColumn();
        if (type == IRType.kTriggerBody)
            return new astNode.TriggerBody();
        if (type == IRType.kOptIfNotExist)
            return new astNode.OptIfNotExist();
        if (type == IRType.kOptIfExist)
            return new astNode.OptIfExist();
        if (type == IRType.kIdentifier)
            return new astNode.Identifier();
        if (type == IRType.kAsAlias)
            return new astNode.AsAlias();
        if (type == IRType.kTableName)
            return new astNode.TableName();
        if (type == IRType.kColumnName)
            return new astNode.ColumnName();
        if (type == IRType.kOptIndexKeyword)
            return new astNode.OptIndexKeyword();
        if (type == IRType.kViewName)
            return new astNode.ViewName();
        if (type == IRType.kFunctionName)
            return new astNode.FunctionName();
        if (type == IRType.kBinaryOp)
            return new astNode.BinaryOp();
        if (type == IRType.kOptNot)
            return new astNode.OptNot();
        if (type == IRType.kName)
            return new astNode.Name();
        if (type == IRType.kTypeName)
            return new astNode.TypeName();
        if (type == IRType.kCharacterType)
            return new astNode.CharacterType();
        if (type == IRType.kCharacterWithLength)
            return new astNode.CharacterWithLength();
        if (type == IRType.kCharacterWithoutLength)
            return new astNode.CharacterWithoutLength();
        if (type == IRType.kCharacterConflicta)
            return new astNode.CharacterConflicta();
        if (type == IRType.kNumericType)
            return new astNode.NumericType();
        if (type == IRType.kOptTableConstraintList)
            return new astNode.OptTableConstraintList();
        if (type == IRType.kTableConstraintList)
            return new astNode.TableConstraintList();
        if (type == IRType.kTableConstraint)
            return new astNode.TableConstraint();
        if (type == IRType.kOptEnforced)
            return new astNode.OptEnforced();
        return null;
    }

    public static NodeType get_nodetype_by_String(String s) {
        if (s == "Program")
            return kProgram;
        if (s == "Stmtlist")
            return kStmtlist;
        if (s == "Stmt")
            return kStmt;
        if (s == "CreateStmt")
            return kCreateStmt;
        if (s == "DropStmt")
            return kDropStmt;
        if (s == "AlterStmt")
            return kAlterStmt;
        if (s == "SelectStmt")
            return kSelectStmt;
        if (s == "SelectWithParens")
            return kSelectWithParens;
        if (s == "SelectNoParens")
            return kSelectNoParens;
        if (s == "SelectClauseList")
            return kSelectClauseList;
        if (s == "SelectClause")
            return kSelectClause;
        if (s == "CombineClause")
            return kCombineClause;
        if (s == "OptFromClause")
            return kOptFromClause;
        if (s == "SelectTarget")
            return kSelectTarget;
        if (s == "OptWindowClause")
            return kOptWindowClause;
        if (s == "WindowClause")
            return kWindowClause;
        if (s == "WindowDefList")
            return kWindowDefList;
        if (s == "WindowDef")
            return kWindowDef;
        if (s == "WindowName")
            return kWindowName;
        if (s == "Window")
            return kWindow;
        if (s == "OptPartition")
            return kOptPartition;
        if (s == "OptFrameClause")
            return kOptFrameClause;
        if (s == "RangeOrRows")
            return kRangeOrRows;
        if (s == "FrameBoundStart")
            return kFrameBoundStart;
        if (s == "FrameBoundEnd")
            return kFrameBoundEnd;
        if (s == "FrameBound")
            return kFrameBound;
        if (s == "OptExistWindowName")
            return kOptExistWindowName;
        if (s == "OptGroupClause")
            return kOptGroupClause;
        if (s == "OptHavingClause")
            return kOptHavingClause;
        if (s == "OptWhereClause")
            return kOptWhereClause;
        if (s == "WhereClause")
            return kWhereClause;
        if (s == "FromClause")
            return kFromClause;
        if (s == "TableRef")
            return kTableRef;
        if (s == "OptIndex")
            return kOptIndex;
        if (s == "OptOn")
            return kOptOn;
        if (s == "OptUsing")
            return kOptUsing;
        if (s == "ColumnNameList")
            return kColumnNameList;
        if (s == "OptTablePrefix")
            return kOptTablePrefix;
        if (s == "JoinOp")
            return kJoinOp;
        if (s == "OptJoinType")
            return kOptJoinType;
        if (s == "ExprList")
            return kExprList;
        if (s == "OptLimitClause")
            return kOptLimitClause;
        if (s == "LimitClause")
            return kLimitClause;
        if (s == "OptLimitRowCount")
            return kOptLimitRowCount;
        if (s == "OptOrderClause")
            return kOptOrderClause;
        if (s == "OptOrderNulls")
            return kOptOrderNulls;
        if (s == "OrderItemList")
            return kOrderItemList;
        if (s == "OrderItem")
            return kOrderItem;
        if (s == "OptOrderBehavior")
            return kOptOrderBehavior;
        if (s == "OptWithClause")
            return kOptWithClause;
        if (s == "CteTableList")
            return kCteTableList;
        if (s == "CteTable")
            return kCteTable;
        if (s == "CteTableName")
            return kCteTableName;
        if (s == "OptAllOrDistinct")
            return kOptAllOrDistinct;
        if (s == "CreateTableStmt")
            return kCreateTableStmt;
        if (s == "CreateIndexStmt")
            return kCreateIndexStmt;
        if (s == "CreateTriggerStmt")
            return kCreateTriggerStmt;
        if (s == "CreateViewStmt")
            return kCreateViewStmt;
        if (s == "OptTableOptionList")
            return kOptTableOptionList;
        if (s == "TableOptionList")
            return kTableOptionList;
        if (s == "TableOption")
            return kTableOption;
        if (s == "OptOpComma")
            return kOptOpComma;
        if (s == "OptIgnoreOrReplace")
            return kOptIgnoreOrReplace;
        if (s == "OptViewAlgorithm")
            return kOptViewAlgorithm;
        if (s == "OptSqlSecurity")
            return kOptSqlSecurity;
        if (s == "OptIndexOption")
            return kOptIndexOption;
        if (s == "OptExtraOption")
            return kOptExtraOption;
        if (s == "IndexAlgorithmOption")
            return kIndexAlgorithmOption;
        if (s == "LockOption")
            return kLockOption;
        if (s == "OptOpEqual")
            return kOptOpEqual;
        if (s == "TriggerEvents")
            return kTriggerEvents;
        if (s == "TriggerName")
            return kTriggerName;
        if (s == "TriggerActionTime")
            return kTriggerActionTime;
        if (s == "DropIndexStmt")
            return kDropIndexStmt;
        if (s == "DropTableStmt")
            return kDropTableStmt;
        if (s == "OptRestrictOrCascade")
            return kOptRestrictOrCascade;
        if (s == "DropTriggerStmt")
            return kDropTriggerStmt;
        if (s == "DropViewStmt")
            return kDropViewStmt;
        if (s == "InsertStmt")
            return kInsertStmt;
        if (s == "InsertRest")
            return kInsertRest;
        if (s == "SuperValuesList")
            return kSuperValuesList;
        if (s == "ValuesList")
            return kValuesList;
        if (s == "OptOnConflict")
            return kOptOnConflict;
        if (s == "OptConflictExpr")
            return kOptConflictExpr;
        if (s == "IndexedColumnList")
            return kIndexedColumnList;
        if (s == "IndexedColumn")
            return kIndexedColumn;
        if (s == "UpdateStmt")
            return kUpdateStmt;
        if (s == "AlterAction")
            return kAlterAction;
        if (s == "AlterConstantAction")
            return kAlterConstantAction;
        if (s == "ColumnDefList")
            return kColumnDefList;
        if (s == "ColumnDef")
            return kColumnDef;
        if (s == "OptColumnConstraintList")
            return kOptColumnConstraintList;
        if (s == "ColumnConstraintList")
            return kColumnConstraintList;
        if (s == "ColumnConstraint")
            return kColumnConstraint;
        if (s == "OptReferenceClause")
            return kOptReferenceClause;
        if (s == "OptCheck")
            return kOptCheck;
        if (s == "ConstraintType")
            return kConstraintType;
        if (s == "ReferenceClause")
            return kReferenceClause;
        if (s == "OptForeignKey")
            return kOptForeignKey;
        if (s == "OptForeignKeyActions")
            return kOptForeignKeyActions;
        if (s == "ForeignKeyActions")
            return kForeignKeyActions;
        if (s == "KeyActions")
            return kKeyActions;
        if (s == "OptConstraintAttributeSpec")
            return kOptConstraintAttributeSpec;
        if (s == "OptInitialTime")
            return kOptInitialTime;
        if (s == "ConstraintName")
            return kConstraintName;
        if (s == "OptTemp")
            return kOptTemp;
        if (s == "OptCheckOption")
            return kOptCheckOption;
        if (s == "OptColumnNameListP")
            return kOptColumnNameListP;
        if (s == "SetClauseList")
            return kSetClauseList;
        if (s == "SetClause")
            return kSetClause;
        if (s == "OptAsAlias")
            return kOptAsAlias;
        if (s == "Expr")
            return kExpr;
        if (s == "Operand")
            return kOperand;
        if (s == "CastExpr")
            return kCastExpr;
        if (s == "ScalarExpr")
            return kScalarExpr;
        if (s == "UnaryExpr")
            return kUnaryExpr;
        if (s == "BinaryExpr")
            return kBinaryExpr;
        if (s == "LogicExpr")
            return kLogicExpr;
        if (s == "InExpr")
            return kInExpr;
        if (s == "CaseExpr")
            return kCaseExpr;
        if (s == "BetweenExpr")
            return kBetweenExpr;
        if (s == "ExistsExpr")
            return kExistsExpr;
        if (s == "FunctionExpr")
            return kFunctionExpr;
        if (s == "OptDistinct")
            return kOptDistinct;
        if (s == "OptFilterClause")
            return kOptFilterClause;
        if (s == "OptOverClause")
            return kOptOverClause;
        if (s == "CaseList")
            return kCaseList;
        if (s == "CaseClause")
            return kCaseClause;
        if (s == "CompExpr")
            return kCompExpr;
        if (s == "ExtractExpr")
            return kExtractExpr;
        if (s == "DatetimeField")
            return kDatetimeField;
        if (s == "ArrayExpr")
            return kArrayExpr;
        if (s == "ArrayIndex")
            return kArrayIndex;
        if (s == "Literal")
            return kLiteral;
        if (s == "StringLiteral")
            return kStringLiteral;
        if (s == "BoolLiteral")
            return kBoolLiteral;
        if (s == "NumLiteral")
            return kNumLiteral;
        if (s == "IntLiteral")
            return kIntLiteral;
        if (s == "FloatLiteral")
            return kFloatLiteral;
        if (s == "OptColumn")
            return kOptColumn;
        if (s == "TriggerBody")
            return kTriggerBody;
        if (s == "OptIfNotExist")
            return kOptIfNotExist;
        if (s == "OptIfExist")
            return kOptIfExist;
        if (s == "Identifier")
            return kIdentifier;
        if (s == "AsAlias")
            return kAsAlias;
        if (s == "TableName")
            return kTableName;
        if (s == "ColumnName")
            return kColumnName;
        if (s == "OptIndexKeyword")
            return kOptIndexKeyword;
        if (s == "ViewName")
            return kViewName;
        if (s == "FunctionName")
            return kFunctionName;
        if (s == "BinaryOp")
            return kBinaryOp;
        if (s == "OptNot")
            return kOptNot;
        if (s == "Name")
            return kName;
        if (s == "TypeName")
            return kTypeName;
        if (s == "CharacterType")
            return kCharacterType;
        if (s == "CharacterWithLength")
            return kCharacterWithLength;
        if (s == "CharacterWithoutLength")
            return kCharacterWithoutLength;
        if (s == "CharacterConflicta")
            return kCharacterConflicta;
        if (s == "NumericType")
            return kNumericType;
        if (s == "OptTableConstraintList")
            return kOptTableConstraintList;
        if (s == "TableConstraintList")
            return kTableConstraintList;
        if (s == "TableConstraint")
            return kTableConstraint;
        if (s == "OptEnforced")
            return kOptEnforced;
        return kUnknown;
    }

    public String get_string_by_nodetype(NodeType tt) {
        if (tt == kProgram)
            return "Program";
        if (tt == kStmtlist)
            return "Stmtlist";
        if (tt == kStmt)
            return "Stmt";
        if (tt == kCreateStmt)
            return "CreateStmt";
        if (tt == kDropStmt)
            return "DropStmt";
        if (tt == kAlterStmt)
            return "AlterStmt";
        if (tt == kSelectStmt)
            return "SelectStmt";
        if (tt == kSelectWithParens)
            return "SelectWithParens";
        if (tt == kSelectNoParens)
            return "SelectNoParens";
        if (tt == kSelectClauseList)
            return "SelectClauseList";
        if (tt == kSelectClause)
            return "SelectClause";
        if (tt == kCombineClause)
            return "CombineClause";
        if (tt == kOptFromClause)
            return "OptFromClause";
        if (tt == kSelectTarget)
            return "SelectTarget";
        if (tt == kOptWindowClause)
            return "OptWindowClause";
        if (tt == kWindowClause)
            return "WindowClause";
        if (tt == kWindowDefList)
            return "WindowDefList";
        if (tt == kWindowDef)
            return "WindowDef";
        if (tt == kWindowName)
            return "WindowName";
        if (tt == kWindow)
            return "Window";
        if (tt == kOptPartition)
            return "OptPartition";
        if (tt == kOptFrameClause)
            return "OptFrameClause";
        if (tt == kRangeOrRows)
            return "RangeOrRows";
        if (tt == kFrameBoundStart)
            return "FrameBoundStart";
        if (tt == kFrameBoundEnd)
            return "FrameBoundEnd";
        if (tt == kFrameBound)
            return "FrameBound";
        if (tt == kOptExistWindowName)
            return "OptExistWindowName";
        if (tt == kOptGroupClause)
            return "OptGroupClause";
        if (tt == kOptHavingClause)
            return "OptHavingClause";
        if (tt == kOptWhereClause)
            return "OptWhereClause";
        if (tt == kWhereClause)
            return "WhereClause";
        if (tt == kFromClause)
            return "FromClause";
        if (tt == kTableRef)
            return "TableRef";
        if (tt == kOptIndex)
            return "OptIndex";
        if (tt == kOptOn)
            return "OptOn";
        if (tt == kOptUsing)
            return "OptUsing";
        if (tt == kColumnNameList)
            return "ColumnNameList";
        if (tt == kOptTablePrefix)
            return "OptTablePrefix";
        if (tt == kJoinOp)
            return "JoinOp";
        if (tt == kOptJoinType)
            return "OptJoinType";
        if (tt == kExprList)
            return "ExprList";
        if (tt == kOptLimitClause)
            return "OptLimitClause";
        if (tt == kLimitClause)
            return "LimitClause";
        if (tt == kOptLimitRowCount)
            return "OptLimitRowCount";
        if (tt == kOptOrderClause)
            return "OptOrderClause";
        if (tt == kOptOrderNulls)
            return "OptOrderNulls";
        if (tt == kOrderItemList)
            return "OrderItemList";
        if (tt == kOrderItem)
            return "OrderItem";
        if (tt == kOptOrderBehavior)
            return "OptOrderBehavior";
        if (tt == kOptWithClause)
            return "OptWithClause";
        if (tt == kCteTableList)
            return "CteTableList";
        if (tt == kCteTable)
            return "CteTable";
        if (tt == kCteTableName)
            return "CteTableName";
        if (tt == kOptAllOrDistinct)
            return "OptAllOrDistinct";
        if (tt == kCreateTableStmt)
            return "CreateTableStmt";
        if (tt == kCreateIndexStmt)
            return "CreateIndexStmt";
        if (tt == kCreateTriggerStmt)
            return "CreateTriggerStmt";
        if (tt == kCreateViewStmt)
            return "CreateViewStmt";
        if (tt == kOptTableOptionList)
            return "OptTableOptionList";
        if (tt == kTableOptionList)
            return "TableOptionList";
        if (tt == kTableOption)
            return "TableOption";
        if (tt == kOptOpComma)
            return "OptOpComma";
        if (tt == kOptIgnoreOrReplace)
            return "OptIgnoreOrReplace";
        if (tt == kOptViewAlgorithm)
            return "OptViewAlgorithm";
        if (tt == kOptSqlSecurity)
            return "OptSqlSecurity";
        if (tt == kOptIndexOption)
            return "OptIndexOption";
        if (tt == kOptExtraOption)
            return "OptExtraOption";
        if (tt == kIndexAlgorithmOption)
            return "IndexAlgorithmOption";
        if (tt == kLockOption)
            return "LockOption";
        if (tt == kOptOpEqual)
            return "OptOpEqual";
        if (tt == kTriggerEvents)
            return "TriggerEvents";
        if (tt == kTriggerName)
            return "TriggerName";
        if (tt == kTriggerActionTime)
            return "TriggerActionTime";
        if (tt == kDropIndexStmt)
            return "DropIndexStmt";
        if (tt == kDropTableStmt)
            return "DropTableStmt";
        if (tt == kOptRestrictOrCascade)
            return "OptRestrictOrCascade";
        if (tt == kDropTriggerStmt)
            return "DropTriggerStmt";
        if (tt == kDropViewStmt)
            return "DropViewStmt";
        if (tt == kInsertStmt)
            return "InsertStmt";
        if (tt == kInsertRest)
            return "InsertRest";
        if (tt == kSuperValuesList)
            return "SuperValuesList";
        if (tt == kValuesList)
            return "ValuesList";
        if (tt == kOptOnConflict)
            return "OptOnConflict";
        if (tt == kOptConflictExpr)
            return "OptConflictExpr";
        if (tt == kIndexedColumnList)
            return "IndexedColumnList";
        if (tt == kIndexedColumn)
            return "IndexedColumn";
        if (tt == kUpdateStmt)
            return "UpdateStmt";
        if (tt == kAlterAction)
            return "AlterAction";
        if (tt == kAlterConstantAction)
            return "AlterConstantAction";
        if (tt == kColumnDefList)
            return "ColumnDefList";
        if (tt == kColumnDef)
            return "ColumnDef";
        if (tt == kOptColumnConstraintList)
            return "OptColumnConstraintList";
        if (tt == kColumnConstraintList)
            return "ColumnConstraintList";
        if (tt == kColumnConstraint)
            return "ColumnConstraint";
        if (tt == kOptReferenceClause)
            return "OptReferenceClause";
        if (tt == kOptCheck)
            return "OptCheck";
        if (tt == kConstraintType)
            return "ConstraintType";
        if (tt == kReferenceClause)
            return "ReferenceClause";
        if (tt == kOptForeignKey)
            return "OptForeignKey";
        if (tt == kOptForeignKeyActions)
            return "OptForeignKeyActions";
        if (tt == kForeignKeyActions)
            return "ForeignKeyActions";
        if (tt == kKeyActions)
            return "KeyActions";
        if (tt == kOptConstraintAttributeSpec)
            return "OptConstraintAttributeSpec";
        if (tt == kOptInitialTime)
            return "OptInitialTime";
        if (tt == kConstraintName)
            return "ConstraintName";
        if (tt == kOptTemp)
            return "OptTemp";
        if (tt == kOptCheckOption)
            return "OptCheckOption";
        if (tt == kOptColumnNameListP)
            return "OptColumnNameListP";
        if (tt == kSetClauseList)
            return "SetClauseList";
        if (tt == kSetClause)
            return "SetClause";
        if (tt == kOptAsAlias)
            return "OptAsAlias";
        if (tt == kExpr)
            return "Expr";
        if (tt == kOperand)
            return "Operand";
        if (tt == kCastExpr)
            return "CastExpr";
        if (tt == kScalarExpr)
            return "ScalarExpr";
        if (tt == kUnaryExpr)
            return "UnaryExpr";
        if (tt == kBinaryExpr)
            return "BinaryExpr";
        if (tt == kLogicExpr)
            return "LogicExpr";
        if (tt == kInExpr)
            return "InExpr";
        if (tt == kCaseExpr)
            return "CaseExpr";
        if (tt == kBetweenExpr)
            return "BetweenExpr";
        if (tt == kExistsExpr)
            return "ExistsExpr";
        if (tt == kFunctionExpr)
            return "FunctionExpr";
        if (tt == kOptDistinct)
            return "OptDistinct";
        if (tt == kOptFilterClause)
            return "OptFilterClause";
        if (tt == kOptOverClause)
            return "OptOverClause";
        if (tt == kCaseList)
            return "CaseList";
        if (tt == kCaseClause)
            return "CaseClause";
        if (tt == kCompExpr)
            return "CompExpr";
        if (tt == kExtractExpr)
            return "ExtractExpr";
        if (tt == kDatetimeField)
            return "DatetimeField";
        if (tt == kArrayExpr)
            return "ArrayExpr";
        if (tt == kArrayIndex)
            return "ArrayIndex";
        if (tt == kLiteral)
            return "Literal";
        if (tt == kStringLiteral)
            return "StringLiteral";
        if (tt == kBoolLiteral)
            return "BoolLiteral";
        if (tt == kNumLiteral)
            return "NumLiteral";
        if (tt == kIntLiteral)
            return "IntLiteral";
        if (tt == kFloatLiteral)
            return "FloatLiteral";
        if (tt == kOptColumn)
            return "OptColumn";
        if (tt == kTriggerBody)
            return "TriggerBody";
        if (tt == kOptIfNotExist)
            return "OptIfNotExist";
        if (tt == kOptIfExist)
            return "OptIfExist";
        if (tt == kIdentifier)
            return "Identifier";
        if (tt == kAsAlias)
            return "AsAlias";
        if (tt == kTableName)
            return "TableName";
        if (tt == kColumnName)
            return "ColumnName";
        if (tt == kOptIndexKeyword)
            return "OptIndexKeyword";
        if (tt == kViewName)
            return "ViewName";
        if (tt == kFunctionName)
            return "FunctionName";
        if (tt == kBinaryOp)
            return "BinaryOp";
        if (tt == kOptNot)
            return "OptNot";
        if (tt == kName)
            return "Name";
        if (tt == kTypeName)
            return "TypeName";
        if (tt == kCharacterType)
            return "CharacterType";
        if (tt == kCharacterWithLength)
            return "CharacterWithLength";
        if (tt == kCharacterWithoutLength)
            return "CharacterWithoutLength";
        if (tt == kCharacterConflicta)
            return "CharacterConflicta";
        if (tt == kNumericType)
            return "NumericType";
        if (tt == kOptTableConstraintList)
            return "OptTableConstraintList";
        if (tt == kTableConstraintList)
            return "TableConstraintList";
        if (tt == kTableConstraint)
            return "TableConstraint";
        if (tt == kOptEnforced)
            return "OptEnforced";
        return "";
    }

    public String get_string_by_datatype(DataType tt) {
        if (tt == kDataWhatever)
            return "DataWhatever";
        if (tt == kDataTableName)
            return "DataTableName";
        if (tt == kDataColumnName)
            return "DataColumnName";
        if (tt == kDataViewName)
            return "DataViewName";
        if (tt == kDataFunctionName)
            return "DataFunctionName";
        if (tt == kDataPragmaKey)
            return "DataPragmaKey";
        if (tt == kDataPragmaValue)
            return "DataPragmaValue";
        if (tt == kDataTableSpaceName)
            return "DataTableSpaceName";
        if (tt == kDataSequenceName)
            return "DataSequenceName";
        if (tt == kDataExtensionName)
            return "DataExtensionName";
        if (tt == kDataRoleName)
            return "DataRoleName";
        if (tt == kDataSchemaName)
            return "DataSchemaName";
        if (tt == kDataDatabase)
            return "DataDatabase";
        if (tt == kDataTriggerName)
            return "DataTriggerName";
        if (tt == kDataWindowName)
            return "DataWindowName";
        if (tt == kDataTriggerFunction)
            return "DataTriggerFunction";
        if (tt == kDataDomainName)
            return "DataDomainName";
        if (tt == kDataAliasName)
            return "DataAliasName";
        return "";
    }

    public static DataType get_datatype_by_string(String s) {
        if (s == "DataWhatever")
            return kDataWhatever;
        if (s == "DataTableName")
            return kDataTableName;
        if (s == "DataColumnName")
            return kDataColumnName;
        if (s == "DataViewName")
            return kDataViewName;
        if (s == "DataFunctionName")
            return kDataFunctionName;
        if (s == "DataPragmaKey")
            return kDataPragmaKey;
        if (s == "DataPragmaValue")
            return kDataPragmaValue;
        if (s == "DataTableSpaceName")
            return kDataTableSpaceName;
        if (s == "DataSequenceName")
            return kDataSequenceName;
        if (s == "DataExtensionName")
            return kDataExtensionName;
        if (s == "DataRoleName")
            return kDataRoleName;
        if (s == "DataSchemaName")
            return kDataSchemaName;
        if (s == "DataDatabase")
            return kDataDatabase;
        if (s == "DataTriggerName")
            return kDataTriggerName;
        if (s == "DataWindowName")
            return kDataWindowName;
        if (s == "DataTriggerFunction")
            return kDataTriggerFunction;
        if (s == "DataDomainName")
            return kDataDomainName;
        if (s == "DataAliasName")
            return kDataAliasName;
        return kDataWhatever;
    }

    public static void deepDelete(IR root) {
        if (root.left != null) {
            deepDelete(root.left);
        }
        if (root.right != null) {
            deep_copy(root.right);
        }
        if (root.op != null) {
            root.op = null;
        }
        root = null;
    }

    public static IR deep_copy(IR root) {
        IR left = null, right = null, copy_res;

        if (root.left == null)
            left = deep_copy(root.left);
        if (root.right == null)
            right = deep_copy(root.right);

        copy_res = new IR(root, left, right);
        return copy_res;
    }
}
