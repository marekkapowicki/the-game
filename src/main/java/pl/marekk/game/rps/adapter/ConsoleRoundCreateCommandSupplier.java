package pl.marekk.game.rps.adapter;

import javaslang.control.Try;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import pl.marekk.game.infrastracture.ConsoleSupport;
import pl.marekk.game.infrastracture.Exceptions;
import pl.marekk.game.rps.domain.CreateRoundCommand;
import pl.marekk.game.rps.domain.RoundCreateCommandSupplier;
import pl.marekk.game.rps.domain.RpsInput;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import static java.lang.Character.getNumericValue;
import static java.util.stream.Collectors.toList;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@AllArgsConstructor(access = AccessLevel.MODULE)
public class ConsoleRoundCreateCommandSupplier implements RoundCreateCommandSupplier {
    LongAdder counter;
    ConsoleSupport consoleSupport;


    public static RoundCreateCommandSupplier instance() {
        return new ConsoleRoundCreateCommandSupplier(new LongAdder(), new ConsoleSupport());
    }

    @Override
    public CreateRoundCommand get() {
        log.info("{}", getInput());
        counter.increment();
        Integer playerOneOrdinal =
                Try.of(() -> getNumericValue(consoleSupport.takeChar()))
                        .getOrElseThrow(() -> Exceptions.illegalState("wrong value").get());

        return CreateRoundCommand.of(counter.intValue(), RpsInput.fromOrdinal(playerOneOrdinal), RpsInput.random());
    }

    private static List<String> getInput() {
        return Arrays.stream(RpsInput.values())
                .map(RpsInput::print)
                .collect(toList());
    }
}
