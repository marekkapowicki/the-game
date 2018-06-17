package pl.marekk.game.infrastracture;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Exceptions {
    public static RuntimeException illegalState(String message) {
        log.error("game is now in illegal state ={}", message);
        return new IllegalStateException(message);
    }
}