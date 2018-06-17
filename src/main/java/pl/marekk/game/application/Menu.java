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

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Menu {

    static Menu create() {
        log.info("{}", getCommands());
        return new Menu();
    }

    void play() {
        Try.of(() -> MenuCommand.from(ConsoleSupport.takeChar()))
                .onSuccess(command -> command.action.get())
                .onFailure(GameFlow::wrongCommand);
    }

    private static List<String> getCommands() {
        return Arrays.stream(MenuCommand.values())
                .map(MenuCommand::toString)
                .collect(toList());
    }

    private enum MenuCommand {
        START('s', GameFlow::play), QUIT('q', GameFlow::end);

        private char value;
        private Supplier<String> action;

        MenuCommand(char value, Supplier<String> action) {
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
