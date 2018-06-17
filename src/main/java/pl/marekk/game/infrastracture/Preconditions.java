package pl.marekk.game.infrastracture;

import java.util.function.Supplier;

public final class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(boolean expression, Supplier<RuntimeException> exception) {
        if (!expression) {
            throw exception.get();
        }
    }
}
