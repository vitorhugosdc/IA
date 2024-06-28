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
            int opponentNumCheckers = bs.getPoint(opponent, i);

            if (opponentNumCheckers == 1) {
                evaluation += 75.0;
            }

            if (numCheckers == 2) {
                evaluation += 100.0;
            } else if (numCheckers == 1) {
                evaluation -= 75.0;
            } else {
                evaluation -= 25.0 * numCheckers;
            }

            evaluation += i * 1.5 * numCheckers;
        }

        int playerHomeCheckers = bs.getPoint(player, 25);
        evaluation += 50.0 * playerHomeCheckers;

        return evaluation;
    }

    public SingleMove[] makeMoves(BoardSetup bs) throws CannotDecideException {
        double evaluation = Double.NEGATIVE_INFINITY;
        int decision = -1;

        PossibleMoves pm = new PossibleMoves(bs);
        List<BoardSetup> moveList = pm.getPossbibleNextSetups();

        int i = 0;
        for (Iterator<BoardSetup> iter = moveList.iterator(); iter.hasNext(); i++) {
            BoardSetup boardSetup = iter.next();
            double thisEvaluation = heuristic(boardSetup);
            if (thisEvaluation > evaluation) {
                evaluation = thisEvaluation;
                decision = i;
            }
        }

        if (decision == -1) {
            return new SingleMove[0];
        } else {
            return pm.getMoveChain(decision);
        }
    }

    public int rollOrDouble(BoardSetup boardSetup) throws CannotDecideException {
        if (boardSetup.getPoint(3 - boardSetup.getPlayerAtMove(), 25) < boardSetup.getPoint(boardSetup.getPlayerAtMove(), 25) / 2) {
            return DOUBLE;
        } else {
            return ROLL;
        }
    }

    public int takeOrDrop(BoardSetup boardSetup) throws CannotDecideException {
        if (boardSetup.getPoint(3 - boardSetup.getPlayerAtMove(), 25) > boardSetup.getPoint(boardSetup.getPlayerAtMove(), 25) * 2) {
            return DROP;
        } else {
            return TAKE;
        }
    }
}