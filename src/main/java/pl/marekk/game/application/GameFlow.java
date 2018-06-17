package pl.marekk.game.application;

import javaslang.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.marekk.game.infrastracture.ConsoleSupport;
import pl.marekk.game.rps.adapter.GameInvoker;
import pl.marekk.game.rps.domain.RpsInput;

import java.util.Arrays;
import java.util.List;

import static java.lang.Character.getNumericValue;
import static java.util.stream.Collectors.toList;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class GameFlow {
    static String play() {
        log.info("{}", getInput());
        Try.of(() -> getNumericValue(ConsoleSupport.takeChar()))
                .onSuccess(GameInvoker::play)
                .onFailure(GameFlow::wrongCommand);
        return "";
    }

    static void wrongCommand(Throwable e) {
        log.error("wrong command", e);
        GameFlow.end();
    }

    static String end() {
        log.info("game is over");
        System.exit(1);
        return "";
    }

    private static List<String> getInput() {
        return Arrays.stream(RpsInput.values())
                .map(RpsInput::print)
                .collect(toList());
    }
}
