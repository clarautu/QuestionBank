package QuestionBank

import org.codehaus.groovy.ast.VariableScope
import spock.lang.Specification

class StateManagerTest extends Specification {

    // Variables
    def stateManager;

    // Setup
    def setup() {
        stateManager = Mock(StateManager)
    }

    // Tests

    def "saveDataTest"() {
        setup:
        int a = 1;

        when:
        a += 1;

        then:
        a == 2;
    }

    def "createFileTest"() {

    }

    def "loadDataTest"() {

    }
}
