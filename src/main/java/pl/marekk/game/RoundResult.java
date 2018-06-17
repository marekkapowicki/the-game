package pl.marekk.game;

import pl.marekk.game.infrastracture.Exceptions;

public enum RoundResult {
    WIN {
        @Override
        public GameResult toGameResult() {
            return GameResult.WIN;
        }
    }, LOST {
        @Override
        public GameResult toGameResult() {
            return GameResult.LOST;
        }
    }, DRAW {
        @Override
        public GameResult toGameResult() {
            throw Exceptions.illegalState("draw is unmapped").get();
        }
    };

    public abstract GameResult toGameResult();
}
