package pl.marekk.game.rps.adapter

import pl.marekk.game.infrastracture.ConsoleSupport
import pl.marekk.game.infrastracture.Exceptions
import pl.marekk.game.rps.domain.CreateRoundCommand
import pl.marekk.game.rps.domain.RpsInput
import spock.lang.Specification
import spock.lang.Subject

import java.util.concurrent.atomic.LongAdder

class ConsoleRoundCreateCommandSupplierSpec extends Specification {

    private LongAdder adder = Mock() {
        intValue() >> 1
    }
    private ConsoleSupport consoleSupport = Mock()

    @Subject
    ConsoleRoundCreateCommandSupplier supplier = new ConsoleRoundCreateCommandSupplier(adder, consoleSupport)

    def "create proper request for #number"() {
        given:
           consoleSupport.takeChar() >> number
        when:
            CreateRoundCommand command = supplier.get()
        then:
            with(command) {
                roundNumber == 1
                playerOne == RpsInput.fromOrdinal(number.toInteger())
                playerTwo
            }
        where:
            number << ['0' , '1', '2']
    }

    def "throw exception during creating request from invalid character #character"() {
        given:
            consoleSupport.takeChar() >> character
        when:
            supplier.get()
        then:
            RuntimeException ex = thrown()
            ex.class == Exceptions.illegalState('').get().class
        where:
            character << ['7' , 'z', '-3']
    }
}
