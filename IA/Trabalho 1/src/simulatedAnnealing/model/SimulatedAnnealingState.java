package simulatedAnnealing.model;

import java.util.Random;

public class SimulatedAnnealingState extends State {

    Random randomGenerator = new Random();

    public SimulatedAnnealingState(int boardSize) {
        super(boardSize);
        for (int i = 0; i < boardSize; i++) {
            q[i] = new Queen(i, randomGenerator.nextInt(boardSize));
        }
    }

    public SimulatedAnnealingState(int boardSize, Queen q[]) {
        super(boardSize);
        this.q = q;
        cost = 0;
    }


    @Override
    public State getNextState() {
        int i;
        Queen nextStateQueen[] = new Queen[boardSize];

        int rand = randomGenerator.nextInt(boardSize);

        for (i = 0; i < boardSize; i++) {
            nextStateQueen[i] = new Queen(q[i].getIndexOfX(),
                    q[i].getIndexOfY());

            if (rand == i) {
                int temp = randomGenerator.nextInt(boardSize);

                while (temp == q[i].getIndexOfY()) {
                    temp = randomGenerator.nextInt(boardSize);
                }
                nextStateQueen[i] = new Queen(q[i].getIndexOfX(), temp);
            }
        }

        return new SimulatedAnnealingState(boardSize, nextStateQueen);
    }
}
