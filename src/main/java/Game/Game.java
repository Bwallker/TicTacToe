package Game;

import AI.AI;
import Components.*;
import UI.UI;

public abstract class Game {
    Board board;
    Players players;
    UI ui;

    public Game(UI ui) {
        this.ui = ui;
        this.board = new Board();
        this.players = null;
    }
    /**
     * <p>
     * This method should get called right after the UI has started
     *
     * </p>
     *

     *
     *
     * <p>
     * This method should generally get all the needed game parameters from the UI and then exit
     * </p>
     *
     */
    public abstract void startGame();
    /**
     * <p>
     *     The default implementation of this should be good enough for most use cases
     * </p>
     * <p>
     *     This method represents a full turn
     * </p>
     * <p>
     *     Should generally call the playerTurn method for both players and check if the game ended after each player turn
     * </p>
     *
     */
    public void turn()
    {
        boolean hasEnded;
        while (true) {
            hasEnded = this.playerTurn(this.players.firstPlayer());
            if (hasEnded) break;
            hasEnded = this.playerTurn(this.players.secondPlayer());
            if (hasEnded) break;
        }
    }
    /**
     * <p>
     *     This method represents the turn of a player
     * </p>
     * <p>
     *     Should generally ask either the UI or the AI to generaate a move
     * </p>
     *
     */
    public abstract boolean playerTurn(Player player);


    /**
     * <p>
     *     This method should get called at the end of the {@link Game#playerTurn(Player)} method
     * </p>
     * <p>
     *     Calls the {@link UI#updateBoard(Board)} method, then the {@link UI#playerEndTurn(Player)} and finally returns the result from {@link UI#handleWinner(Winner)} using the result from {@link Board#checkWinner()} as the argument
     * </p>
     *
     */
    public boolean endPlayerTurn(Player player) {
        this.ui.updateBoard(board);
        this.ui.playerEndTurn(player);
        return this.ui.handleWinner(this.board.checkWinner());
    }
    /**
     * <p>
     *     Should return the name of the gamemode
     * </p>
     * <p>
     *     For example, the SinglePlayerGame class returns "Singleplayer" and the MultiPlayerGame class returns "Multiplayer"
     * </p>
     */
    public abstract String toString();

    /**
     * <p>
     *     Returns the player with the matching symbol
     * </p>
     */
    public Player getPlayer(Symbol symbol) {
        if (this.players.firstPlayer().symbol() == symbol) return this.players.firstPlayer();
        if (this.players.secondPlayer().symbol() == symbol) return this.players.secondPlayer();
        return null;
    }
}
