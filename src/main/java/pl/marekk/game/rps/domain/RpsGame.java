package pl.marekk.game.rps.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import pl.marekk.game.Game;
import pl.marekk.game.GameResult;
import pl.marekk.game.RoundResult;

import static pl.marekk.game.RoundResult.DRAW;
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor(staticName = "of")
public class RpsGame implements Game {

    RoundCreateCommandSupplier roundSupplier;

    @Override
    public GameResult play() {
        RoundResult roundResult = null;
        while(roundResult == null ||  DRAW == roundResult) {
            roundResult= Round.rpsRound(roundSupplier.get()).play();
        }
        return roundResult.toGameResult();
    }
}
