package pl.marekk.game.rps.domain

import pl.marekk.game.GameResult
import spock.lang.Specification
import spock.lang.Subject

import static pl.marekk.game.rps.domain.CreateRoundCommand.of
import static pl.marekk.game.rps.domain.RpsInput.PAPER
import static pl.marekk.game.rps.domain.RpsInput.ROCK

class RpsGameSpec extends Specification {

    private RoundCreateCommandSupplier supplier = Stub() {
        get() >>> [of(1, ROCK, ROCK), of(2, ROCK, PAPER)]
    }
    @Subject
    private RpsGame game = new RpsGame(supplier)

    def "call suplier as long as draw is not a result"() {
        when:
            GameResult result = game.play()
        then:
            result == GameResult.LOST
    }
}
