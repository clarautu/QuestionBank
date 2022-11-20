package QuestionBank

import spock.lang.Specification

class ShortAnswerTest extends Specification {

    //getPrompt
    def "get short answer prompt"() {
        given:
        String statement = "some prompt"
        def shortAnswer = new ShortAnswer(1, statement)

        when:
        def result = shortAnswer.getPrompt()

        then:
        result == statement
    }

    //setPrompt(String Prompt)
    def "set short answer prompt"() {
        given:
        String statement = "some prompt"
        def shortAnswer = new ShortAnswer(1, statement)

        when:
        String newStatement = "new prompt"
        shortAnswer.setPrompt(newStatement)

        then:
        shortAnswer.getPrompt() == newStatement

    }

}
