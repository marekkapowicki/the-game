package pl.marekk.game.application;

import javaslang.control.Try;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import pl.marekk.game.infrastracture.ConsoleSupport;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class Menu {

    ConsoleSupport consoleSupport;
    static Menu create() {
        return new Menu(new ConsoleSupport());
    }

    boolean play() {
        log.info("{}", getCommands());
        MenuCommand command = Try.of(() -> MenuCommand.from(consoleSupport.takeChar()))
                .getOrElse(MenuCommand.UNKNOWN);
        return command.action.get();
    }

    private static List<String> getCommands() {
        return Arrays.stream(MenuCommand.values())
                .filter(command -> !command.equals(MenuCommand.UNKNOWN))
                .map(MenuCommand::toString)
                .collect(toList());
    }

    enum MenuCommand {
        START('s', GameFlow::play), QUIT('q', GameFlow::end), UNKNOWN('u', GameFlow::wrongCommand);

        private char value;
        private Supplier<Boolean> action;

        MenuCommand(char value, Supplier<Boolean> action) {
            this.value = value;
            this.action = action;
        }

        static MenuCommand from(char key) {
            return Arrays.stream(MenuCommand.values())
                    .filter(it -> key == it.getValue())
                    .findFirst()
                    .orElseGet(() -> UNKNOWN);
        }

        public char getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value + "-" + name();
        }

    }
}
