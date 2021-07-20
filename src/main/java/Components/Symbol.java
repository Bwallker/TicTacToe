package Components;

public enum Symbol {
    CROSS,
    CIRCLE,
    UNINITIALIZED;

    @Override
    public String toString() {
        if (this == CROSS) return "X";
        if (this == CIRCLE) return "O";
        if (this == UNINITIALIZED) return "-";
        return "null";
    }
}
