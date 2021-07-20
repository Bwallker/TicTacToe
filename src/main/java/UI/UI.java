package UI;

import AI.AI;
import Components.*;
import Game.Game;
public abstract class UI {
    /**
     * This method uses user input to execute the move that the human player wants to make
     */
    public abstract Board getMove(Player player, Board board);

    /**
     * <p>
     * This method tells the human players what the AI is doing as it is making its move
     *
     * </p>
     * <p>
     *
     * Generally printing a simple "Thinking..." is enough
     * </p>
     *
     */
    public abstract void AIMove();

    /**
     * <p>
     * This method creates the {@link Game} object that will be used for the rest of the game.
     *
     * </p>
     *
     *
     * <p>
     * It uses user input to determine what type of game object is to be used
     *
     * </p>
     *
     *
     * <p>
     * This method should call the {@link Game#startGame()} method before returning
     * </p>
     *
     */
    public abstract void startGame();

    /**
     * <p>
     *     Uses player input to determine what type each player is
     * </p>
     * <p>
     *     At this point the gamemode has already been chosen, so all this method really does is figure out if the AI player goes first or second
     * </p>
     *
     */
    public abstract PlayerTypes getPlayerTypes();
    /**
     * <p>
     *     Uses player input to determine whether crosses or circles starts
     * </p>
     */
    public abstract Symbols getPlayerSymbols();

    /**
     * <p>
     *     Checks to see if the game has ended
     * </p>
     * <p>
     *     If it has, it informs the humans of the terminal gamestate, generally by printing stuff
     * </p>
     */
    public abstract boolean handleWinner(Winner winner);
    /**
     * <p>
     *     Determines how difficult the AI should be based on user input
     * </p>
     */
    public abstract AI getAI(Player AIPlayer, Player otherPlayer) throws IllegalAccessException, IllegalArgumentException;
    /**
     * <p>
     *     Informs the humans of the latest board state
     * </p>
     */
    public abstract void updateBoard(Board board);
    /**
     * <p>
     *     Informs the humans that a players turn has started
     * </p>
     * <p>
     *     Also tells them which player it is, and what type they are
     * </p>
     */
    public abstract void playerStartTurn(Player player);
    /**
     * <p>
     *     Informs the humans that a players turn has ended
     * </p>
     * <p>
     *     Also tells them which player it was, and what type they were
     * </p>
     */
    public abstract void playerEndTurn(Player player);
    /**
     * <p>
     *     Gets called right after the game has been fully setup
     * </p>
     * <p>
     *     Tells the humans all the settings that they have chosen
     * </p>
     */
    public abstract void showSettingsSummary(Player player1, Player player2, AI ai1, AI ai2);
}
