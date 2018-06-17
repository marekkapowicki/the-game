package pl.marekk.game.rps.domain

import pl.marekk.game.RoundResult
import spock.lang.Specification
import spock.lang.Subject

import static pl.marekk.game.RoundResult.WIN

class RoundSpec extends Specification {
    private RpsInput player1 = RpsInput.ROCK
    private RpsInput player2 = RpsInput.SCISSOR
    @Subject
    private Round round = new Round(1, player1, player2)

    def "simple proxy to input params"() {
        when:
            RoundResult result = round.play()
        then:
            result == WIN

    }
}
