package Components;

public class Board {
    private Symbol[][] board;
    private Symbol[][] allThrees;

    public Board() {
        this.board = new Symbol[3][3];
        for (int row = 0; row < this.board.length; row++) {
            for (int column = 0; column < this.board[0].length; column++) {
                this.board[row][column] = Symbol.UNINITIALIZED;
            }
        }
        this.allThrees = null;

    }

    @Override
    public String toString() {
        String output = "";
        for (Symbol[] row : this.board) {
            output += "\n";
            output += "|";
            for (Symbol cell : row) {
                output += " ";
                output += cell;
                output += " |";
            }
        }
        output += "\n";
        return output;
    }

    public boolean isFull() {
        for (Symbol[] row : this.board) {
            for (Symbol cell : row) {
                if (cell == Symbol.UNINITIALIZED) return false;
            }
        }
        return true;
    }

    public void updateAllThrees() {
        Symbol[] horizontal1 = {this.board[0][0], this.board[1][1], this.board[2][2]};
        Symbol[] horizontal2 = {this.board[2][0], this.board[1][1], this.board[0][2]};
        Symbol[] column1 = {this.board[0][0], this.board[0][1], this.board[0][2]};
        Symbol[] column2 = {this.board[1][0], this.board[1][1], this.board[1][2]};
        Symbol[] column3 = {this.board[2][0], this.board[2][1], this.board[2][2]};
        Symbol[] row1 = {this.board[0][0], this.board[1][0], this.board[2][0]};
        Symbol[] row2 = {this.board[0][1], this.board[1][1], this.board[2][1]};
        Symbol[] row3 = {this.board[0][2], this.board[1][2], this.board[2][2]};


        this.allThrees = new Symbol[][]{horizontal1, horizontal2, column1, column2, column3, row1, row2, row3};
    }


    public boolean makeMove(int y, int x, Symbol symbol) {
        if (this.board[y][x] == Symbol.UNINITIALIZED) {
            this.board[y][x] = symbol;
            this.updateAllThrees();
            return true;
        }
        return false;
    }

    public Symbol[][] getBoard() {
        return this.board;
    }

    public void undoMove(int y, int x) {
        this.board[y][x] = Symbol.UNINITIALIZED;
    }

    public boolean movePossible(int y, int x) {
        return this.board[y][x] == Symbol.UNINITIALIZED;
    }


    public Winner checkWinner() {
        int circleSymbols;
        int crossSymbols;
        for (Symbol[] threeCells : this.allThrees) {
            circleSymbols = 0;
            crossSymbols = 0;
            for (Symbol cell : threeCells) {
                if (cell == Symbol.CIRCLE) {
                    circleSymbols++;
                } else if (cell == Symbol.CROSS) {
                    crossSymbols++;
                }
            }
            if (circleSymbols == 3) return Winner.CIRCLE;
            if (crossSymbols == 3) return Winner.CROSS;
        }
        if (this.isFull()) return Winner.DRAW;
        return Winner.UNDECIDED;
    }
}
