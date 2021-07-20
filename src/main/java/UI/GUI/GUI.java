package UI.GUI;
import AI.AI;
import Components.*;
import UI.UI;
public class GUI extends UI {
    public GUI() {

    }

    @Override
    public Board getMove(Player player, Board board) {
        return null;
    }

    @Override
    public void AIMove() {

    }

    @Override
    public void startGame() {

    }

    @Override
    public PlayerTypes getPlayerTypes() {
        return null;
    }

    @Override
    public Symbols getPlayerSymbols() {
        return null;
    }

    @Override
    public boolean handleWinner(Winner winner) {
        return false;
    }

    @Override
    public AI getAI(Player AIPlayer, Player otherPlayer) throws IllegalAccessException, IllegalArgumentException {
        return null;
    }

    @Override
    public void updateBoard(Board board) {

    }

    @Override
    public void playerStartTurn(Player player) {

    }

    @Override
    public void playerEndTurn(Player player) {

    }

    @Override
    public void showSettingsSummary(Player player1, Player player2, AI ai1, AI ai2) {

    }
}
