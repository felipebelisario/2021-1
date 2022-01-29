package profundidadeIterativa;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class NQueensProblem {

    public static int N;
    public static int maxDepth;

    public Map<Integer,Integer> queensMap = new HashMap<Integer,Integer>();

    public static void main(String args[]) throws Exception {
        Scanner sc= new Scanner(System.in);

        System.out.print("Informe a quantidade de rainhas: ");

        NQueensProblem queenProblem = new NQueensProblem();
        queenProblem.N = sc.nextInt();
        queenProblem.maxDepth = 0;

        int board[][] = new int[N][N];

        for (int i = 0; i < N; i++) {
            int col = getRandomNumber(0, N);
            board[i][col] = 1;

            queenProblem.queensMap.put(i, col);
        }

        while( ! queenProblem.solveNQueens(board)) {
            queenProblem.maxDepth++;
        }

    }

    public boolean solveNQueens(int[][] board) {

        int currentDepth = 0;
        if (solveNQueensRecursion(board, 0, currentDepth) == false) {
            System.out.println("---------------------------------");
            System.out.println("Limite de altura: " + maxDepth);
            System.out.println("\nNão foi encontrada uma solução!\n");
            return false;
        }

        printSolution(board);
        return true;
    }

    public static int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    boolean solveNQueensRecursion(int board[][], int row, int currentDepth) {

        if(isValidBoard(board)){
            return true;
        }

        if (row >= N) {
            return false;
        }

        if(currentDepth >= maxDepth){
            return false;
        }

        int colQueen = queensMap.get(row);
        board[row][colQueen] = 0;

        for (int col = 0; col < N; col++) {

            board[row][col] = 1;

            if (solveNQueensRecursion(board, row + 1, currentDepth + 1) == true)
                return true;

            board[row][col] = 0;

        }

        board[row][colQueen] = 1;

        return false;
    }

    void printSolution(int board[][]) {
        System.out.println("---------------------------------");
        System.out.println("Limite de altura: " + maxDepth);
        System.out.println("\nSolução encontrada! \nTabuleiro:\n");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j]
                        + " ");
            System.out.println();
        }
    }

    boolean isValidPosition(int board[][], int row, int col)
    {
        int i, j;

        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    boolean isValidBoard(int board[][]) {

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {

                if(board[row][col] == 1) {

                    int i, j;

                    for (i = 0; i < N; i++)
                        if (board[row][i] == 1 && col != i)
                            return false;

                    for (i = 0; i < N; i++)
                        if (board[i][col] == 1 && row != i)
                            return false;

                    for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
                        if (board[i][j] == 1 && row != i && col != j)
                            return false;

                    for (i = row, j = col; i >= 0 && j < N; i--, j++)
                        if (board[i][j] == 1 && row != i && col != j)
                            return false;

                    for (i = row, j = col; i < N && j < N; i++, j++)
                        if (board[i][j] == 1 && row != i && col != j)
                            return false;

                    for (i = row, j = col; j >= 0 && i < N; i++, j--)
                        if (board[i][j] == 1 && row != i && col != j)
                            return false;

                }
            }
        }

        return true;
    }

}