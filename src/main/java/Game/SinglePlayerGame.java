package Game;

import AI.AI;
import Components.*;
import UI.UI;

public class SinglePlayerGame extends Game {
    private AI ai;
    public SinglePlayerGame(UI ui) {
        super(ui);
        this.ai = null;
    }
    @Override
    public void startGame() {


        PlayerTypes types = this.ui.getPlayerTypes();
        Symbols symbols = this.ui.getPlayerSymbols();
        Player firstPlayer = new Player(PlayerNumber.FIRST, types.firstPlayerType(), symbols.firstPlayerSymbol());
        Player secondPlayer = new Player(PlayerNumber.SECOND, types.secondPlayerType(), symbols.secondPlayerSymbol());
        this.players = new Players(firstPlayer, secondPlayer);

        Player AIPlayer;
        Player otherPlayer;
        if (firstPlayer.type() == PlayerType.AI) {
            AIPlayer = firstPlayer;
            otherPlayer = secondPlayer;
        } else {
            AIPlayer = secondPlayer;
            otherPlayer = firstPlayer;
        }
        try {
            this.ai = this.ui.getAI(AIPlayer, otherPlayer);
        } catch (Exception e) {
            System.out.println(e);
        }

        this.ui.showSettingsSummary(firstPlayer, secondPlayer, this.ai, null);
        this.turn();
    }

    @Override
    public boolean playerTurn(Player player) {
        this.ui.playerStartTurn(player);
        if (player.type() == PlayerType.HUMAN) {
            this.board = this.ui.getMove(player, this.board);
        } else {
            this.ui.AIMove();
            this.board = this.ai.makeMove(player, this.board);
        }
        return this.endPlayerTurn(player);
    }

    @Override
    public String toString() {
        return "Singleplayer";
    }

}
