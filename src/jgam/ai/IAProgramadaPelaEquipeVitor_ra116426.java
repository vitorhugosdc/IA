package jgam.ai;

import jgam.game.*;
import java.util.*;

public class IAProgramadaPelaEquipeVitor_ra116426 implements AI {

    public void init() throws Exception {
    }

    public void dispose() {
    }

    public String getName() {
        return "IAProgramadaPelaEquipeVitor ra116426";
    }

    public String getDescription() {
        return "IAProgramadaPelaEquipeVitor ra116426";
    }

    private double heuristic(BoardSetup bs) {
        double evaluation = 0.0;

        int player = bs.getPlayerAtMove();
        int opponent = 3 - player;

        for (int i = 1; i < 25; i++) {
            int numCheckers = bs.getPoint(player, i);
            if (numCheckers == 1) {
                evaluation -= 50.0;
            } else if (numCheckers > 1) {
                evaluation += 10.0 * numCheckers;
            }
        }

        for (int i = 1; i < 25; i++) {
            int numCheckers = bs.getPoint(player, i);
            evaluation += i * numCheckers;
        }

        for (int i = 1; i < 25; i++) {
            int opponentCheckers = bs.getPoint(opponent, i);
            if (opponentCheckers == 1) {
                evaluation += 75.0;
            }
        }

        int baseCheckers = bs.getPoint(player, 0);
        evaluation -= baseCheckers * 50.0;

        int offBoardCheckers = bs.getPoint(player, 25);
        evaluation += offBoardCheckers * 25.0;

        return evaluation;
    }

    public SingleMove[] makeMoves(BoardSetup bs) throws CannotDecideException {
        double evaluation = Double.NEGATIVE_INFINITY;
        int decision = -1;

        PossibleMoves pm = new PossibleMoves(bs);
        List moveList = pm.getPossbibleNextSetups();

        int i = 0;
        for (Iterator iter = moveList.iterator(); iter.hasNext(); i++) {
            BoardSetup boardSetup = (BoardSetup) iter.next();
            double thisEvaluation = heuristic(boardSetup);
            if (thisEvaluation > evaluation) {
                evaluation = thisEvaluation;
                decision = i;
            }
        }

        if (decision == -1)
            return new SingleMove[0];
        else
            return pm.getMoveChain(decision);
    }

    public int rollOrDouble(BoardSetup boardSetup) throws CannotDecideException {
        return ROLL;
    }

    public int takeOrDrop(BoardSetup boardSetup) throws CannotDecideException {
        return TAKE;
    }
}
