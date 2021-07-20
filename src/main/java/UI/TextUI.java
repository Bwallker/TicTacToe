package UI;

import AI.AI;
import AI.Difficulty;
import Components.*;
import Game.Game;
import Game.MultiPlayerGame;
import Game.SinglePlayerGame;

import java.util.Locale;
import java.util.Scanner;

public class TextUI extends UI {
    private Game gamemode;
    private Scanner reader;

    public TextUI() {
        this.reader = new Scanner(System.in);
    }

    @Override
    public void startGame() {
        System.out.println("TicTacToe!");
        System.out.println();
        System.out.println();
        System.out.println("Would like to play Singleplayer or Multiplayer?");
        System.out.println("Type SP or Singleplayer to choose Singleplayer");
        System.out.println("Type MP or Multiplayer to choose Multiplayer");
        System.out.println("It's case insensitive, so mP or mulTIPLayer or SINglePLayer works aswell");
        String gamemode;
        while (true) {
            System.out.println();
            System.out.print("I want to play: ");
            gamemode = this.reader.nextLine();
            gamemode = gamemode.strip().toLowerCase();
            if (gamemode.equals("mp") || gamemode.equals("multiplayer")) {
                this.gamemode = new MultiPlayerGame(this);
                break;
            }
            if (gamemode.equals("sp") || gamemode.equals("singleplayer")) {
                this.gamemode = new SinglePlayerGame(this);
                break;
            }
            System.out.println("Invalid input!");
            System.out.println("Try again!");
        }
        System.out.println();
        System.out.println("You have chosen gamemode " + this.gamemode + "!");
        System.out.println();
        this.gamemode.startGame();

    }


    @Override
    public PlayerTypes getPlayerTypes() {
        PlayerType firstPlayerType;
        PlayerType secondPlayerType;
        String first;
        System.out.println("Should the human or the AI start?");
        while (true) {
            System.out.println();
            System.out.print("Should the human start (y/n)? ");
            first = this.reader.nextLine();
            first = first.strip().toLowerCase();
            if (first.equals("y") || first.equals("yes") || first.equals("true") || first.equals("t")) {
                firstPlayerType = PlayerType.HUMAN;
                secondPlayerType = PlayerType.AI;
                break;
            }
            if (first.equals("n") || first.equals("no") || first.equals("false") || first.equals("f")) {
                firstPlayerType = PlayerType.AI;
                secondPlayerType = PlayerType.HUMAN;
                break;
            }
            System.out.println("Invalid input!");
            System.out.println("Try again!");
        }
        System.out.println();
        System.out.println(firstPlayerType + " goes first!");
        System.out.println();
        return new PlayerTypes(firstPlayerType, secondPlayerType);
    }

    @Override
    public Symbols getPlayerSymbols() {
        System.out.println("What symbol should the first player use? Cross or Circle?");
        System.out.println("Type cross or x to choose cross as first player symbol");
        System.out.println("Type circle or dot or o to choose circle as first player symbol");
        String symbol;
        Symbol firstPlayerSymbol;
        Symbol secondPlayerSymbol;
        while (true) {
            System.out.println();
            System.out.print("X or O? ");
            symbol = this.reader.nextLine();
            symbol = symbol.strip().toLowerCase();
            if (symbol.equals("cross") || symbol.equals("x")) {
                firstPlayerSymbol = Symbol.CROSS;
                secondPlayerSymbol = Symbol.CIRCLE;
                break;
            }
            if (symbol.equals("circle") || symbol.equals("dot") || symbol.equals("o")) {
                firstPlayerSymbol = Symbol.CIRCLE;
                secondPlayerSymbol = Symbol.CROSS;
                break;
            }
            System.out.println("Invalid input!");
            System.out.println("Try again!");
        }
        System.out.println();
        System.out.println("You have chosen " + firstPlayerSymbol + " as the first players symbol!");
        System.out.println();
        return new Symbols(firstPlayerSymbol, secondPlayerSymbol);
    }

    @Override
    public AI getAI(Player AIPlayer, Player otherPlayer) throws IllegalArgumentException, IllegalAccessException {
        System.out.println("How good should the AI be?");
        System.out.println("Available difficulties are easy, medium, hard, expert and custom");
        System.out.println("Type easy to choose easy difficulty");
        System.out.println("Type medium to choose medium difficulty");
        System.out.println("Type hard to choose hard difficulty");
        System.out.println("Type expert to choose expert difficulty");
        System.out.println("Type custom to choose custom difficulty");
        System.out.println();
        String input;
        Difficulty difficulty;
        while (true) {
            System.out.println();
            System.out.print("Choose a difficulty: ");
            input = this.reader.nextLine();
            input = input.strip().toLowerCase();
            if (input.equals("easy")) {
                difficulty = Difficulty.EASY;
                break;
            }
            if (input.equals("medium")) {
                difficulty = Difficulty.MEDIUM;
                break;
            }
            if (input.equals("hard")) {
                difficulty = Difficulty.HARD;
                break;
            }
            if (input.equals("expert")) {
                difficulty = Difficulty.EXPERT;
                break;
            }
            if (input.equals("custom")) {
                difficulty = Difficulty.CUSTOM;
                break;
            }

            System.out.println("Invalid input!");
            System.out.println("Try again!");
        }
        difficulty = handleCustomDifficulty(difficulty);
        System.out.println();

        AI ai = new AI(difficulty, this, AIPlayer, otherPlayer);
        return ai;
    }

    private Difficulty handleCustomDifficulty(Difficulty difficulty) throws IllegalArgumentException, IllegalAccessException {

        if (difficulty != Difficulty.CUSTOM) return difficulty;
        System.out.println();
        System.out.println("You have chosen the custom difficulty!");
        double randomMoveChance = this.getRandomMoveChance();
        int lookAheadLimit = this.getLookAheadLimit();
        boolean randomFirstMove = this.getRandomFirstMove();
        difficulty.setLookAheadLimit(lookAheadLimit);
        difficulty.setRandomMoveChange(randomMoveChance);
        difficulty.setRandomFirstMove(randomFirstMove);
        return difficulty;
    }


    private double getRandomMoveChance() {
        String randomMoveChanceString;
        int percentage;
        System.out.println();
        System.out.println("Choose the random move chance for your custom difficulty");
        System.out.println("The random move chance is the chance that the AI will make a random move each turn");
        System.out.println("So if the random move chance is 50%, there is a 50% chance that the AI will make a random move each turn");
        System.out.println("Type your random move chance as a percentage");
        System.out.println("So if you want the random move chance to be 50%, type 50");
        System.out.println("Percentage has to be between 0 and 100");
        while (true) {
            System.out.print("My custom random move chance is: ");
            randomMoveChanceString = this.reader.nextLine();
            randomMoveChanceString = randomMoveChanceString.strip().toLowerCase();
            try {
                percentage = Integer.parseInt(randomMoveChanceString);
            } catch (NumberFormatException exception) {
                System.out.println("The numbered you entered was not correctly formatted");
                System.out.println("If you want the random move percentage to be 50%, type 50");
                System.out.println("If you want it to be 30%, type 30");
                System.out.println("If you want it to be 0%, type 0");
                System.out.println("etc");
                System.out.println();
                continue;
            }
            if (percentage > 100) {
                System.out.println("The percentage you entered is greater than 100");
                System.out.println("Your percentage must be between 0 and 100");
                System.out.println();
                continue;
            }
            if (percentage < 0) {
                System.out.println("The percentage you entered is less than 100");
                System.out.println("Your percentage must be between 0 and 100");
                System.out.println();
                continue;
            }
            double randomMoveChance = Double.valueOf(percentage);
            return randomMoveChance / 100;

        }
    }

    private int getLookAheadLimit() {
        String lookAheadLimitString;
        int lookAheadLimit;
        System.out.println();
        System.out.println("Choose the look ahead limit for your custom difficulty");
        System.out.println("The look ahead limit is how far into the future the AI is allowed to look when it decides what to do");
        System.out.println("So if the look ahead limit is 2, the AI is allowed to look 2 turns into the future");
        System.out.println("Type your look ahead limit as a number");
        System.out.println("So if you want the look ahead limit to be 5, type 5");
        System.out.println("The look ahead limit has to be greater than 0");
        while (true) {
            System.out.print("My custom look ahead limit is: ");
            lookAheadLimitString = this.reader.nextLine();
            lookAheadLimitString = lookAheadLimitString.strip().toLowerCase();
            try {
                lookAheadLimit = Integer.parseInt(lookAheadLimitString);
            } catch (NumberFormatException exception) {
                System.out.println("The numbered you entered was not correctly formatted");
                System.out.println("You have to type your look ahead limit as a number");
                System.out.println("If you want it to be 10, type 10");
                System.out.println("If you want it to be 1, type 1");
                System.out.println("etc");
                System.out.println();
                continue;
            }
            if (lookAheadLimit < 1) {
                System.out.println("The look ahead limit you entered is less than 1");
                System.out.println("Your look ahead limit has to be greater than zero");
                System.out.println();
                continue;
            }
            return lookAheadLimit;

        }
    }

    private boolean getRandomFirstMove() {
        String randomFirstMoveString;
        System.out.println();
        System.out.println("Should the AI make a random first move?");
        System.out.println("If random first move is on, the first move the AI makes will always be random");
        System.out.println("Otherwise it will follow the normal rules (This means it might still be random if your random move chance is greater than 0");
        System.out.println("Type on or true if you want the AI to make a random first move");
        System.out.println("Type off or false if you do not want the AI to make a random first move");
        while (true) {
            System.out.print("Random first move should be: ");
            randomFirstMoveString = this.reader.nextLine();
            randomFirstMoveString = randomFirstMoveString.strip().toLowerCase();
            if (randomFirstMoveString.equals("on") || randomFirstMoveString.equals("true") || randomFirstMoveString.equals("t") || randomFirstMoveString.equals("y") || randomFirstMoveString.equals("yes")) {
                System.out.println("You have turned random first move on!");
                return true;
            }
            if (randomFirstMoveString.equals("off") || randomFirstMoveString.equals("false") || randomFirstMoveString.equals("f") || randomFirstMoveString.equals("n") || randomFirstMoveString.equals("no")) {
                System.out.println("You have turned random first move off!");
                return false;
            }
            System.out.println("Invalid input!");
            System.out.println("Try again!");
            System.out.println();
        }
    }

    @Override
    public void playerStartTurn(Player player) {
        System.out.println();
        System.out.println(player.number() + " " + player.type() + " players turn!");
        System.out.println();
    }

    @Override
    public void playerEndTurn(Player player) {
        System.out.println();
        System.out.println(player.number() + " " + player.type() + " player has finished their turn!");
    }

    @Override
    public Board getMove(Player player, Board board) {

        System.out.println();
        System.out.println("Make a move!");
        System.out.println("Use format x, y when choosing your move");
        System.out.println("Where x is the x coordinate and y is the y coordinate of your move");
        System.out.println("So if you want to place your symbol in the cell in the second row and first column, type 2, 1");
        System.out.println("The coordinates you choose must no be occupied, and they must be within the grid");
        String move;
        while (true) {
            System.out.print("My move is: ");
            move = this.reader.nextLine();
            move = move.strip().toLowerCase();
            String[] split = move.split(",");
            if (split.length != 2) {
                System.out.println("The coordinates you entered either contains more or less than 1 comma, or it only contains one coordinate");
                System.out.println("Try again!");
                continue;
            }
            String x = split[0];
            x = x.strip();

            if (!x.matches("[1-3]")) {
                System.out.println("The x coordinate you enter must be a number between 1 and 3");
                System.out.println("Try again!");
                continue;
            }
            String y = split[1];
            y = y.strip();
            if (!y.matches("[1-3]")) {
                System.out.println("The y coordinate you enter must be a number between 1 and 3");
                System.out.println("Try again!");
                continue;
            }
            int intX = Integer.parseInt(x) - 1;
            int intY = Integer.parseInt(y) - 1;

            boolean successful = board.makeMove(intY, intX, player.symbol());
            if (successful) {
                System.out.println("Move successful!");
                return board;
            } else {
                System.out.println("The coordinates you entered are already in use");
            }
        }
    }

    @Override
    public void updateBoard(Board board) {
        System.out.println(board);
    }

    @Override
    public boolean handleWinner(Winner winner) {
        if (winner == Winner.UNDECIDED) return false;
        System.out.println();
        System.out.println();
        if (winner == Winner.DRAW) {
            System.out.println("DRAW!");
            System.out.println("No player has won and there are no cells left to played");
            return true;
        }
        Player player;
        if (winner == Winner.CROSS) {
            player = this.gamemode.getPlayer(Symbol.CROSS);;
        } else {
            player = this.gamemode.getPlayer(Symbol.CIRCLE);
        }
        System.out.println(player.number().toString().toUpperCase() + " PLAYER HAS WON!");
        return true;
    }

    @Override
    public void AIMove() {
        System.out.println("Starting AI turn!");
        System.out.println("Thinking...");
    }


    @Override
    public void showSettingsSummary(Player player1, Player player2, AI ai1, AI ai2) {
        System.out.println("Game initialization done!");
        System.out.println("Printing a summary of game settings!");
        System.out.println("--------------------------");
        System.out.println(player1);
        System.out.println("--------------------------");
        System.out.println(player2);
        if (ai1 == null) return;
        System.out.println("--------------------------");
        System.out.println("Printing first AI!");
        System.out.println(ai1);
        if (ai2 == null) return;
        System.out.println("--------------------------");
        System.out.println("Printing second AI!");
        System.out.println(ai2);
    }
}
