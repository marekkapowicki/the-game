package pl.marekk.game.application;

import javaslang.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.marekk.game.rps.adapter.ConsoleRoundCommandSupplier;
import pl.marekk.game.rps.domain.RpsGame;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class GameFlow {
    static boolean play() {
        Try.of(RpsGame.of(new ConsoleRoundCommandSupplier())::play)
                .onFailure(GameFlow::error);
        return false;
    }

    static boolean wrongCommand() {
        log.error("wrong command");
        return false;
    }

    static boolean error(Throwable e) {
        log.error("some bug", e.getMessage());
        return false;
    }

    static boolean end() {
        log.info("game is over");
        System.exit(1);
        return true;
    }


}
