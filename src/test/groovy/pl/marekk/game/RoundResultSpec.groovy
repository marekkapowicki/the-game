package pl.marekk.game

import pl.marekk.game.infrastracture.Exceptions
import spock.lang.Specification

import static pl.marekk.game.RoundResult.*

class RoundResultSpec extends Specification {
    def "convert #input to GameResult.#expectedResult"() {

        when:
            GameResult result = input.toGameResult()
        then:
           result == expectedResult
        where:
          input   || expectedResult
          LOST    || GameResult.LOST
          WIN     || GameResult.WIN
    }

    def "throw exception during conversion of DRAW"() {
        when:
            DRAW.toGameResult()
        then:
            RuntimeException ex = thrown()
            ex.class == Exceptions.illegalState('').get().class
    }
}
