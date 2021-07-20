package UI.GUI.GUILoader;

public record Resolution(int width, int height) implements Comparable<Resolution> {
    @Override
    public String toString() {
        return this.width() + "x" + this.height();
    }

    public int toInt() {
        return this.width() / this.height();
    }

    @Override
    public int compareTo(Resolution other) {
        return Integer.compare(this.width(), other.width());
    }
}
