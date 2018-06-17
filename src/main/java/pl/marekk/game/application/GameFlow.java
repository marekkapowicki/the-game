package pl.marekk.game.application;

import javaslang.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.marekk.game.rps.adapter.ConsoleRoundCreateCommandSupplier;
import pl.marekk.game.rps.domain.RpsGame;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class GameFlow {
    static boolean play() {
        Try.of(RpsGame.of(ConsoleRoundCreateCommandSupplier.instance())::play)
                .onFailure(GameFlow::error);
        return true;
    }

    static boolean wrongCommand() {
        log.error("wrong command");
        return true;
    }

    static boolean end() {
        log.info("game is over");
        System.exit(1);
        return false;
    }

    private static boolean error(Throwable e) {
        log.error("some bug: {}", e.getMessage());
        return true;
    }


}
