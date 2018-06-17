package pl.marekk.game;

@FunctionalInterface
public interface GameInput {
    GameResult playWith(GameInput parameter);
}
