package hillClimbing.model;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private int board[][];
    private int safeQueenPairs; // number of non-attacking pair of queens

    public Board(int[][] b) {
        this.board = b;
        ev();
    }

    public Board(int N) {
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }
        generateRandomQueens(N);
        ev();
    }

    public void generateRandomQueens(int N) {
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            board[i][rand.nextInt(N - 1)] = 1;
        }
    }

    public int ev() {
        safeQueenPairs = 0;
        ArrayList<Integer> pos = getQueenPositions();

        for (int i = 0; i < board.length - 1; i++) {
            safeQueenPairs += countSafe(pos, i);
        }

        return safeQueenPairs;
    }

    public int countSafe(ArrayList<Integer> pos, int row) {
        int i, safePairQ = 0, count = 0;

        for (i = row; i < board.length - 1; i++) {
            count = 0;
            if (pos.get(row) == pos.get(i + 1)) { // check if same column
                count++;
            }

            if ((pos.get(row) + row) == (pos.get(i + 1) + (i + 1))) { // check diagonal
                count++;
            }

            if ((pos.get(row) - row) == (pos.get(i + 1) - (i + 1))) { // check diagonal
                count++;
            }

            if (count <= 0) {
                safePairQ++;
            }
        }
        return safePairQ;

    }

    public ArrayList<Integer> getQueenPositions() {
        ArrayList<Integer> pos = new ArrayList<Integer>();
        for (int i = 0; i < board.length; i++) {
            pos.add(getColumn(i));
        }
        return pos;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getSafeQueenPairs() {
        return safeQueenPairs;
    }

    public void setSafeQueenPairs(int safeQueenPairs) {
        this.safeQueenPairs = safeQueenPairs;
    }

    public int getColumn(int row) {
        int index = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == 1) {
                index = i;
            }
        }

        return index;
    }

    @Override
    public String toString() {
        String b = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    b += " * ";
                } else {
                    b += " Q ";
                }

            }
            b += "\n";
        }
        return b;
    }

}