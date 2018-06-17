package pl.marekk.game.rps.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import pl.marekk.game.RoundResult;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class Round  {
    int roundNumber;
    RpsInput playerOne;
    RpsInput playerTwo;

    public static Round rpsRound(CreateRoundCommand createRoundCommand) {
        return new Round(createRoundCommand.getRoundNumber(), createRoundCommand.getPlayerOne(), createRoundCommand.getPlayerTwo());
    }

    public RoundResult play() {
        RoundResult roundResult = playerOne.playWith(playerTwo);
        log.info("round {}: {} vs {} - player1: {}", roundNumber, playerOne, playerTwo, roundResult);
        return roundResult;
    }
}
