package hillClimbing;

import hillClimbing.model.Board;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NQueensProblem {

    public static int N;

    public static void main(String[] args) {
        NQueensProblem nQueensProblem = new NQueensProblem();
        nQueensProblem.solveNQueens();
    }

    public void solveNQueens() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Informe a quantidade de rainhas: ");
        N = Integer.parseInt(sc.nextLine());
        sc.close();

        Board startBoard = new Board(N);

        System.out.println("------------Especificação do tabuleiro------------\n" + "Tamanho do tabuleiro: "
                + N + " x " + N + "\nFunção objetivo: " + startBoard.getSafeQueenPairs()
                + "\nTabuleiro Inicial:\n\n" + startBoard.toString());

        int count = 1;
        while(! hillClimbing(startBoard)){
            System.out.println("**********************************************************");

            count++;
            startBoard = new Board(N);

            System.out.println("------------Especificação do tabuleiro------------\n" + "Tamanho do tabuleiro: "
                    + N + " x " + N + "\nFunção objetivo: " + startBoard.getSafeQueenPairs()
                    + "\nTabuleiro Inicial:\n\n" + startBoard.toString());
        }

        System.out.println("Quantidade de vezes executado: " + count);

    }

    public boolean hillClimbing(Board board) {
        boolean isLocalMax = false, continueSearch = true;
        Board currentBoard = new Board(board.getBoard());
        int iterations = 0;
        int globalMax = getGoalValue(currentBoard.getBoard().length);

        while (true) {
            if (currentBoard.getSafeQueenPairs() == globalMax) { // funcao de avaliacao atual alcancou o maximo global calculado, logo temos uma solucao global
                System.out.println("------------Solução encontrada------------" + "\nQuantidade de iterações: "
                        + iterations + "\nFunção objetivo: " + currentBoard.getSafeQueenPairs()
                        + "\nTabuleiro Resultante:\n" + currentBoard.toString());
                return true;
            } else {
                for (int i = 0; i < currentBoard.getBoard().length; i++) {
                    Board bestSuccessor = generateSuccessor(currentBoard, i);
                    if (bestSuccessor.getSafeQueenPairs() > currentBoard.getSafeQueenPairs()) {
                        currentBoard = bestSuccessor;
                        iterations++;
                        isLocalMax = false;
                    } else {
                        // Não achou um sucessor com menos conflitos que o tabuleiro atual, logo chegou em seu maximo local
                        isLocalMax = true;
                    }
                }

                if (isLocalMax) {
                    System.out.println(
                            "------------Máximo Local Encontrado------------" + "\nQuantidade de iterações: "
                                    + iterations + "\nFunção objetivo: " + currentBoard.getSafeQueenPairs()
                                    + "\nTabuleiro Resultante:\n\n" + currentBoard.toString());
                    return false;
                }
            }
        }
    }

    public Board generateSuccessor(Board board, int row) {
        ArrayList<Board> candidates = new ArrayList<Board>();
        Board bestCandidate;

        for (int col = 0; col < board.getBoard().length; col++) {

            if (board.getBoard()[row][col] != 1) { // nao eh uma rainha
                int successorCandidate[][] = new int[board.getBoard().length][board.getBoard().length];
                successorCandidate[row][col] = 1;

                // Copiando restante do tabuleiro para o novo voluntario
                for (int i = 0; i < successorCandidate.length; i++) {
                    if (i != row) {
                        successorCandidate[i] = board.getBoard()[i];
                    }
                }
                candidates.add(new Board(successorCandidate)); // adiciona o novo tabuleiro numa lista para ser avaliado

            }
        }

        bestCandidate = candidates.get(0); // primeiro candidato ao melhor sucessor

        ArrayList<Board> bestCandidates = new ArrayList<Board>();
        for (int i = 1; i < candidates.size(); i++) {
            int bestEvaluation = bestCandidate.getSafeQueenPairs();
            int nextEvaluation = candidates.get(i).getSafeQueenPairs();

            if (nextEvaluation > bestEvaluation) {
                bestCandidate = candidates.get(i);
            } else if (nextEvaluation == bestEvaluation) {
                bestCandidates.add(candidates.get(i));
            }
        }

        if(bestCandidate == null) {
            Random rand = new Random();
            int choose = (int) (rand.nextInt(bestCandidates.size()));
            bestCandidate = bestCandidates.get(choose);
        }

        return bestCandidate;

    }

    public int getGoalValue(int size) {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += i;
        }
        return sum;
    }

}

