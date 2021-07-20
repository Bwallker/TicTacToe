package AI;

import Components.*;
import UI.UI;

import java.util.Random;
public class AI {
    private double randomMoveChance;
    private int lookAheadLimit;
    private Random random;
    private UI ui;
    private Player thisPlayer;
    private Player otherPlayer;
    private boolean randomFirstMove;
    private String difficultyName;

    public AI(Difficulty difficulty, UI ui, Player thisPlayer, Player otherPlayer) throws IllegalAccessException {
        this.randomMoveChance = difficulty.getRandomMoveChance();
        this.lookAheadLimit = difficulty.getLookAheadLimit();
        this.randomFirstMove = difficulty.getRandomFirstMove();
        this.random = new Random();
        this.ui = ui;
        this.thisPlayer = thisPlayer;
        this.otherPlayer = otherPlayer;
        this.difficultyName = difficulty.toString();
    }


    public Player otherPlayer(Player player) {
        if (player.equals(this.thisPlayer)) return this.otherPlayer;
        return this.thisPlayer;
    }

    public Board makeMove(Player player, Board board) {
        //Always makes a random move as their first move, because that makes things more exciting
        if (this.randomFirstMove) {
            this.randomFirstMove = false;
            return this.randomMove(player, board);
        }
        Double randomDouble = this.random.nextDouble();
        if (randomDouble < this.randomMoveChance) {
            return this.randomMove(player, board);
        }
        return this.bestMove(player, board);


    }

    private Board randomMove(Player player, Board board) {
        int x, y;
        while (true) {
            x = random.nextInt(3);
            y = random.nextInt(3);
            if (board.makeMove(x, y, player.symbol())) break;
        }
        return board;
    }

    private Coordinate randomCoordinate(Player player, Board board) {
        int x, y;
        while (true) {
            x = random.nextInt(3);
            y = random.nextInt(3);
            if (board.movePossible(x, y)) {
                return new Coordinate(x, y);
            }
        }
    }

    private Coordinate bestMoveForPlayer(Player player, Board board) {
        Symbol[][] boardOfSymbol = board.getBoard();


        int bestScore = Integer.MIN_VALUE;
        Coordinate bestMove = randomCoordinate(player, board);
        int score;
        for (int column = 0; column < boardOfSymbol.length; column++) {
            for (int row = 0; row < boardOfSymbol[0].length; row++) {
                if (boardOfSymbol[column][row] == Symbol.UNINITIALIZED) {
                    board.makeMove(column, row, player.symbol());
                    score = minimax(0, this.otherPlayer(player), board);
                    board.undoMove(column, row);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new Coordinate(column, row);
                    }
                }
            }
        }
        return bestMove;
    }

    private Board bestMove(Player player, Board board) {


        Coordinate bestMove = this.bestMoveForPlayer(player, board);
        board.makeMove(bestMove.x(), bestMove.y(), player.symbol());
        return board;
    }

    private int minimax(int depth, Player player, Board board) {
        //WORKS!
        int winnerScore = this.calculateWinnerScore(player, board);
        if (winnerScore != 2) return winnerScore;


        return calculateScore(depth, board, player);

    }

    public int calculateWinnerScore(Player player, Board board) {
        Winner winner = board.checkWinner();
        if (winner == Winner.UNDECIDED) {
            return 2;
        }
        final boolean isThisPlayer = player.equals(this.thisPlayer);
        System.out.println(player);
        System.out.println(isThisPlayer);
        //defaults to Winner.DRAW. No need to check it separately
        int result = 0;
        if (winner == Winner.CROSS) {
            if (player.symbol() == Symbol.CROSS) {
                result = 1;
            } else {
                result = -1;
            }
        }
        else if (winner == Winner.CIRCLE) {
            if (player.symbol() == Symbol.CIRCLE) {
                result = 1;;
            } else {
                result = -1;;
            }
        }
        if (isThisPlayer) return result;
        return result * -1;

    }


    public int calculateScore(int depth, Board board, Player player) {
        final boolean isThisPlayer = player.equals(this.thisPlayer);
        int best = isThisPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Symbol[][] boardOfSymbols = board.getBoard();
        Symbol[] firstRow = boardOfSymbols[0];
        final int columnLength = boardOfSymbols.length;
        final int rowLength = firstRow.length;

        for (int column = 0; column < rowLength; column++) {
            for (int row = 0; row < columnLength; row++) {
                if (!board.makeMove(column, row, player.symbol())) {
                    continue;
                }
                if (depth > this.lookAheadLimit) {
                    board.undoMove(column, row);
                    return best;
                }
                int score = minimax(depth + 1, this.otherPlayer(player), board);
                board.undoMove(column, row);
                best = isThisPlayer ? Math.max(best, score) : Math.min(best, score);
                if (isThisPlayer) {
                    best = Math.max(best, score);
                } else {
                    best = Math.min(best, score);
                }
            }
        }
        return best;
    }



    @Override
    public String toString() {
        String output = "";
        output += "Difficulty is: " + this.difficultyName + "\n";
        output += "Random move chance is: " + this.randomMoveChance + "\n";
        output += "Look ahead limit is: " + this.lookAheadLimit + "\n";
        output += "Random first move is: " + Difficulty.randomFirstMoveToString(this.randomFirstMove) + "\n";
        return output;

    }
}
