package Components;

public enum PlayerNumber {
    FIRST,
    SECOND;

    @Override
    public String toString() {
        if (this == FIRST) return "First";
        if (this == SECOND) return "Second";
        return null;
    }
}
