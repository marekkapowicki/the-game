package pl.marekk.game.rps.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
public class CreateRoundCommand {
    int roundNumber;
    RpsInput playerOne;
    RpsInput playerTwo;
}
