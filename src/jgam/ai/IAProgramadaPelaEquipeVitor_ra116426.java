package jgam.ai;

import jgam.game.BoardSetup;
import jgam.game.PossibleMoves;
import jgam.game.SingleMove;

import java.util.Iterator;
import java.util.List;

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

        int consecutiveBlocks = 0;

        for (int i = 1; i < 25; i++) {
            int numCheckers = bs.getPoint(player, i);
            int opponentNumCheckers = bs.getPoint(opponent, i);

            /*Ofensiva: Alvo peças sozinhas do oponente*/
            if (opponentNumCheckers == 1) {
                evaluation += 75.0;
            }

            /*Defensiva: Assegurar posições com duas ou mais peças*/
            if (numCheckers >= 2) {
                evaluation += 100.0;
                consecutiveBlocks++;
            } else {
                consecutiveBlocks = 0;
            }

            /*Bônus adicional para cada bloco consecutivo*/
            if (consecutiveBlocks > 1) {
                evaluation += 50.0 * consecutiveBlocks;
            }

            /*Aplica penalidade para peças sozinhas*/
            if (numCheckers == 1) {
                evaluation -= 50.0;
            }

            /*Aplica penalidade para mais que 3 peças sozinhas em um único ponto*/
            if (numCheckers > 3) {
                evaluation -= 10.0 * (numCheckers - 3);
            }

            /*Bônus por posições avançadas*/
            evaluation += i * 1.0 * numCheckers;
        }
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