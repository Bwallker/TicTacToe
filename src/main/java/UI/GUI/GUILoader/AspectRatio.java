package UI.GUI.GUILoader;

import java.util.Objects;

public record AspectRatio(int widthRatio, int heightRatio) {

    @Override
    public String toString() {
        /*
        String output = "";
        if (Integer.toString(this.widthRatio()).length() == 2) {
            output += "        ";
        } else {
            output += "         ";
        }
        output += this.widthRatio() + ":" + this.heightRatio();
        if (Integer.toString(this.heightRatio()).length() == 2) {
            output += "        ";
        } else {
            output += "         ";
        }
        return output;
         */
        return this.widthRatio + ":" + this.heightRatio;
    }
    public int toInt() {
        return this.widthRatio() / this.heightRatio();
    }

    public static AspectRatio fromString(String stringRepresentation) {
        String[] split = stringRepresentation.split(":");
        int widthRatio = Integer.parseInt(split[0]);
        int heightRatio = Integer.parseInt(split[1]);
        return new AspectRatio(widthRatio, heightRatio);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AspectRatio that = (AspectRatio) o;
        return widthRatio == that.widthRatio && heightRatio == that.heightRatio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(widthRatio, heightRatio);
    }
}
