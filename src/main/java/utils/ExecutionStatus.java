package utils;

public enum ExecutionStatus {
    kConnectFailed,
    kExecuteError,
    kServerCrash,
    kNormal,
    kTimeout,
    kSyntaxError,
    kSemanticError
}
