package pl.marekk.game.rps.domain

import pl.marekk.game.RoundResult
import spock.lang.Specification

import static pl.marekk.game.RoundResult.*
import static pl.marekk.game.rps.domain.RpsInput.*

class RpsInputSpec extends Specification {
    def "ROCK #expectedResult with : #param "() {
        when:
            RoundResult result = ROCK.playWith(param)
        then:
            result == expectedResult
        where:
           param   || expectedResult
           ROCK    || DRAW
           SCISSOR || WIN
           PAPER   || LOST
    }

    def "SCISSOR #expectedResult with : #param "() {
        when:
            RoundResult result = SCISSOR.playWith(param)
        then:
            result == expectedResult
        where:
            param   || expectedResult
            ROCK    || LOST
            SCISSOR || DRAW
            PAPER   || WIN
    }

    def "PAPER #expectedResult with : #param "() {
        when:
            RoundResult result = PAPER.playWith(param)
        then:
            result == expectedResult
        where:
            param   || expectedResult
            ROCK    || WIN
            SCISSOR || LOST
            PAPER   || DRAW
    }
}
