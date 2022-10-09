package QuestionBank

import spock.lang.Specification

public class BankFacadeTest extends Specification{

    // Variables
    def facade;

    // Setup
    def setup() {
        facade = Mock(BankFacade)
    }

    // Tests
    // Im holding off on doing these for the moment
    def "poop"() {
        setup:
        def myValue = 1

        when:
        myValue += 1

        then:
        myValue == 2
    }

}
