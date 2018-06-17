package pl.marekk.game.rps.domain;

import pl.marekk.game.GameInput;
import pl.marekk.game.RoundResult;

import java.util.Random;

import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;
import static javaslang.Predicates.is;
import static pl.marekk.game.RoundResult.DRAW;
import static pl.marekk.game.RoundResult.LOST;
import static pl.marekk.game.RoundResult.WIN;
import static pl.marekk.game.infrastracture.Exceptions.illegalState;
import static pl.marekk.game.infrastracture.Preconditions.checkArgument;
public enum RpsInput implements GameInput {
    ROCK {
        public RoundResult playWith(GameInput parameter) {
            return Match(parameter).of(
                    Case($(is(SCISSOR)), WIN),
                    Case($(is(PAPER)), LOST),
                    Case($(is(ROCK)), DRAW));
        }
    }, PAPER {
        public RoundResult playWith(GameInput parameter) {
            return Match(parameter).of(
                    Case($(is(SCISSOR)), LOST),
                    Case($(is(PAPER)), DRAW),
                    Case($(is(ROCK)), WIN));
        }
    }, SCISSOR {
        public RoundResult playWith(GameInput parameter) {
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
