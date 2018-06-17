package pl.marekk.game.infrastracture;

import lombok.NoArgsConstructor;

import java.util.Scanner;

import static pl.marekk.game.infrastracture.Exceptions.illegalState;

@NoArgsConstructor
public class ConsoleSupport {

    public char takeChar() {
        Scanner input = new Scanner(System.in);
        if (input.hasNext()) {
            return input.next().charAt(0);
        }
        input.close();
        throw illegalState("some issue with console").get();

    }
}
