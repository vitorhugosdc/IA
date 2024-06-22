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
            int opponentCheckers = bs.getPoint(opponent, i);

            if (numCheckers == 1) {
                evaluation -= 100.0;
            } else if (numCheckers >= 2) {
                evaluation += 50.0 * numCheckers;
            }

            if (opponentCheckers == 1) {
                evaluation += 80.0;
            }
        }

        for (int i = 1; i < 25; i++) {
            int numCheckers = bs.getPoint(player, i);
            evaluation += i * numCheckers;
        }

        int baseCheckers = bs.getPoint(player, 0);
        evaluation -= baseCheckers * 40.0;

        int offBoardCheckers = bs.getPoint(player, 25);
        evaluation += offBoardCheckers * 100.0;

        for (int i = 19; i < 25; i++) {
            int numCheckers = bs.getPoint(player, i);
            evaluation += 100.0 * numCheckers;
        }

        // Verificar peças adversárias capturadas
        int opponentCaptured = bs.getPoint(opponent, 0);
        if (opponentCaptured > 0) {
            evaluation += 150.0 * opponentCaptured;
        }

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
        double eval = heuristic(boardSetup);
        if (boardSetup.mayDouble(boardSetup.getPlayerAtMove())) {
            if (eval >= 200.0)
                return DOUBLE;
        }
        return ROLL;
    }

    public int takeOrDrop(BoardSetup boardSetup) throws CannotDecideException {
        double eval = heuristic(boardSetup);
        if (eval > -100.0)
            return TAKE;
        else
            return DROP;
    }
}