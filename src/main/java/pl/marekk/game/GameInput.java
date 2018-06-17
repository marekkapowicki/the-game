package pl.marekk.game;

@FunctionalInterface
public interface GameInput {
    RoundResult playWith(GameInput parameter);
}
