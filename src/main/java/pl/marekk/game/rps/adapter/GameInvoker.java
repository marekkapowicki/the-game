package pl.marekk.game.rps.adapter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.marekk.game.GameResult;
import pl.marekk.game.rps.domain.RpsInput;

import static pl.marekk.game.rps.domain.RpsInput.random;
import static pl.marekk.game.rps.domain.TwoPlayersGame.rpsGame;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class GameInvoker {
    public static String play(int number) {
        RpsInput playerOne = RpsInput.fromOrdinal(number);
        GameResult result = play(playerOne, random());
        log.info("{}: {} ", playerOne, result);
        return result.toString();
    }

    private static GameResult play(RpsInput player1, RpsInput player2) {
        return rpsGame(player1, player2).play();
    }
}
