package simulatedAnnealing;

import simulatedAnnealing.model.NQueen;
import simulatedAnnealing.model.SimulatedAnnealingState;

import java.util.Scanner;

public class NQueenProblem extends NQueen {

    double temperature;

    public static void main(String[] args) throws Exception{
        long time;

        int tollerence = 0;

        Scanner sc = new Scanner(System.in);

        System.out.print("Informe a quantidade de rainhas: ");
        int N = Integer.parseInt(sc.nextLine());
        sc.close();

        NQueen nQueensProblem = new NQueenProblem(N, tollerence,1000);
        nQueensProblem.solveNQueens();
        nQueensProblem.show();
    }

    public NQueenProblem(int boardSize, int tollerence, double temperature) {
        super(boardSize, tollerence);
        this.temperature = temperature;
        currentState = new SimulatedAnnealingState(boardSize);
    }

    @Override
    public void solveNQueens() {
        while (!isSolvedPossition(currentState)) {
            double temperature;
            double delta;
            double probability;
            double rand;


            for (temperature = this.temperature; (temperature > 0) && (currentState.getCost() != 0); temperature--) {
                nextState = currentState.getNextState();
                delta = currentState.getCost() - nextState.getCost();
                probability = Math.exp(delta / temperature);
                rand = Math.random();

                if (delta > 0) {
                    currentState = nextState;
                } else if (rand <= probability) {
                    currentState = nextState;
                }
            }
        }
    }
}