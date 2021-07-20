package Components;

public record Player (PlayerNumber number, PlayerType type, Symbol symbol) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (!(object instanceof Player)) return false;
        Player player = (Player) object;
        if (player.type() != this.type()) return false;
        if (player.number() != this.number()) return false;
        if (player.symbol() != this.symbol()) return false;
        return true;

    }


    @Override
    public String toString() {
        String output = "";
        output += this.number() + " player:\n";
        if (type() == PlayerType.AI) {
            output += "Player is an ";
        } else {
            output += "Player is a ";
        }
        output += this.type() + " player\n";
        output += "Player symbol is " + this.symbol() + "\n";
        return output;
    }
}
