package pl.marekk.game.application

import pl.marekk.game.infrastracture.ConsoleSupport
import spock.lang.Specification
import spock.lang.Subject

class MenuSpec extends Specification {
    private ConsoleSupport consoleSupport = Mock()

    @Subject
    private Menu menu = new Menu(consoleSupport)

    def "create valid command #expected from character = #character"() {
        given:
            consoleSupport.takeChar() >> character
        when:
            Menu.MenuCommand result = Menu.MenuCommand.from(character as Character)
        then:
            result == expected
        where:
            character || expected
            's'       || Menu.MenuCommand.START
            'q'       || Menu.MenuCommand.QUIT
            'z'       || Menu.MenuCommand.UNKNOWN
    }
}