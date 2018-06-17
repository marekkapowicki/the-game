package pl.marekk.game.rps.domain;

import pl.marekk.game.GameInput;
import pl.marekk.game.GameResult;

import java.util.Random;

import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;
import static javaslang.Predicates.is;
import static pl.marekk.game.infrastracture.Exceptions.illegalState;
import static pl.marekk.game.infrastracture.Preconditions.checkArgument;
import static pl.marekk.game.rps.domain.TwoPlayersGameResult.DRAW;
import static pl.marekk.game.rps.domain.TwoPlayersGameResult.LOST;
import static pl.marekk.game.rps.domain.TwoPlayersGameResult.WIN;

public enum RpsInput implements GameInput {
    ROCK {
        @Override
        public GameResult playWith(GameInput parameter) {
            return Match(parameter).of(
                    Case($(is(SCISSOR)), WIN),
                    Case($(is(PAPER)), LOST),
                    Case($(is(ROCK)), DRAW));
        }
    }, PAPER {
        @Override
        public GameResult playWith(GameInput parameter) {
            return Match(parameter).of(
                    Case($(is(SCISSOR)), LOST),
                    Case($(is(PAPER)), DRAW),
                    Case($(is(ROCK)), WIN));
        }
    }, SCISSOR {
        @Override
        public GameResult playWith(GameInput parameter) {
            return Match(parameter).of(
                    Case($(is(SCISSOR)), DRAW),
                    Case($(is(PAPER)), WIN),
                    Case($(is(ROCK)), LOST));
        }
    };

    public static RpsInput random() {
        int number = new Random().nextInt(values().length);
        return values()[number];
    }

    public static RpsInput fromOrdinal(int ordinal) {
        checkArgument(ordinal <= values().length, illegalState("wrong number"));
        return values()[ordinal];
    }

    public String print() {
        return ordinal() + "-" + name();
    }
}
