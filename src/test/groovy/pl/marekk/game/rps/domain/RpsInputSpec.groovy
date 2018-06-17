package pl.marekk.game.rps.domain

import pl.marekk.game.RoundResult
import pl.marekk.game.infrastracture.Exceptions
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

    def "create instance from valid number: #number"() {
        when:
            RpsInput result = fromOrdinal(number)
        then:
            noExceptionThrown()
        and:
            result
        where:
            number << [0, 1, 2]
    }

    def "throw exception during creation instance from wrong number: #number"() {
        when:
            RpsInput result = fromOrdinal(number)
        then:
            RuntimeException ex = thrown()
            ex.class == Exceptions.illegalState('').get().class
        where:
            number << [ 10, 4, -1]
    }
}

