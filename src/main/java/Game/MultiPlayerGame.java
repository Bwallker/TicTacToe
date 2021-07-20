package Game;

import Components.*;
import UI.UI;

public class MultiPlayerGame extends Game {

    public MultiPlayerGame(UI ui) {
        super(ui);
    }
    @Override
    public void startGame() {


        Symbols symbols = this.ui.getPlayerSymbols();
        Player firstPlayer = new Player(PlayerNumber.FIRST, PlayerType.HUMAN, symbols.firstPlayerSymbol());
        Player secondPlayer = new Player(PlayerNumber.SECOND, PlayerType.HUMAN, symbols.secondPlayerSymbol());
        this.players = new Players(firstPlayer, secondPlayer);

        this.ui.showSettingsSummary(firstPlayer, secondPlayer, null, null);
        this.turn();

    }

    @Override
    public boolean playerTurn(Player player) {
        this.ui.playerStartTurn(player);
        this.board = ui.getMove(player, this.board);
        return this.endPlayerTurn(player);
    }


    @Override
    public String toString() {
        return "Multiplayer";
    }

}