package QuestionBank

import spock.lang.Specification

class BankFacadeTest extends Specification{

    // Variables
    def facade;

    // Setup
    def setup() {
        facade = Mock(BankFacade)
    }

    // Tests

    // Checks that there cannot be more than one instance of BankFacade
    def "test_singleton"() {
        setup:
        def facadeOne = facade.GetInstance()
        def before = facadeOne.hashCode()

        when:
        def facadeTwo = facade.GetInstance()
        def after = facadeTwo.hashCode()

        then:
        before == after
    }

}
