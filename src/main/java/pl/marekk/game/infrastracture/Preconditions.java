package pl.marekk.game.infrastracture;

public final class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(boolean expression, RuntimeException exception) {
        if (!expression) {
            throw exception;
        }
    }
}
