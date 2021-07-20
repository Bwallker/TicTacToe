package Components;

public enum PlayerType {
    HUMAN,
    AI;

    @Override
    public String toString() {
        if (this == HUMAN) return "Human";
        if (this == AI) return "AI";
        return "null";
    }
}
