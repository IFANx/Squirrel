package mysql.src.ast;

public enum DataFlag {
    kUse(0x8),

    kMapToClosestOne(0x10),

    kNoSplit(0x100),

    kGlobal(0x4),

    kReplace(0x40),

    kUndefine(0x2),

    kAlias(0x80),

    kMapToAll(0x20),

    kDefine(0x1);

    int value;

    DataFlag(int value) {
        this.value = value;
    }
}
