package pl.marekk.game.application;

import javaslang.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import pl.marekk.game.infrastracture.ConsoleSupport;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Menu {

    static Menu create() {
        log.info("{}", getCommands());
        return new Menu();
    }

    boolean play() {
        MenuCommand command = Try.of(() -> MenuCommand.from(ConsoleSupport.takeChar()))
                .getOrElse(MenuCommand.UNKNOWN);
        return command.action.get();
    }

    private static List<String> getCommands() {
        return Arrays.stream(MenuCommand.values())
                .filter(command -> !command.equals(MenuCommand.UNKNOWN))
                .map(MenuCommand::toString)
                .collect(toList());
    }

    private enum MenuCommand {
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
                    .orElseThrow(() -> new IllegalStateException("command not found"));
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
