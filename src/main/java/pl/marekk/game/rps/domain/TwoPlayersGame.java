package pl.marekk.game.rps.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import pl.marekk.game.Game;
import pl.marekk.game.GameInput;
import pl.marekk.game.GameResult;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public abstract class TwoPlayersGame<I extends GameInput> implements Game {
    I playerOne;
    I playerTwo;

    public static TwoPlayersGame<RpsInput> rpsGame(RpsInput playerOne, RpsInput playerTwo) {
        return new RpsGame(playerOne, playerTwo);
    }

    @Override
    public GameResult play() {
        log.info("{} vs {}", playerOne, playerTwo);
        return playerOne.playWith(playerTwo);
    }
}
