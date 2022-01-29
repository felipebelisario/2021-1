package simulatedAnnealing.model;

public abstract class NQueen {

    public int boardSize;
    public State currentState, nextState;
    public int tollerenceCost;

    public NQueen(int boardSize, int tollrence) {
        this.boardSize = boardSize;
        this.tollerenceCost = tollrence;
    }

    abstract public void solveNQueens();

    public void show() {
        System.out.println("Custo total de " + currentState.getCost());

        Queen q[] = currentState.getQueens();
        boolean queen = false;
        System.out.println();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                for (int k = 0; k < boardSize; k++) {
                    if (i == q[k].getIndexOfX() && j == q[k].getIndexOfY()) {
                        queen = true;
                        break;
                    }
                }

                if (queen) {
                    System.out.print("Q\t");
                    queen = false;
                } else {
                    System.out.print("*\t");
                }
            }

            System.out.println();
        }
    }

    protected boolean isSolvedPossition(State s) {
        if (s.getCost() <= tollerenceCost) {
            return true;
        }
        return false;
    }
}