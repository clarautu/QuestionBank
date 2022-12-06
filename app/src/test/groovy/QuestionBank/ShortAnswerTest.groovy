package QuestionBank

import spock.lang.Specification

class ShortAnswerTest extends Specification {

    //Get Id number
    def "check ID"(){
        when:
        def ID = 1
        def question = new ShortAnswer(ID, "prompt")

        then:
        question.GetIdNumber() == ID;
    }
    def "check ID with different number"() {
        when:
        def ID = 11
        def question = new ShortAnswer(ID, "prompt")
        then:
        question.GetIdNumber() == ID
    }
    def "check ID with a big number"() {
        when:
        def ID = 12345678
        def question = new ShortAnswer(ID, "prompt")
        then:
        question.GetIdNumber() == ID
    }

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
    def "get prompt after it has been changed"() {
        setup:
        def question = new ShortAnswer(1, "prompt")

        when:
        def newPrompt = "This is a different prompt"
        question.setPrompt(newPrompt)

        then:
        question.GetQuestion() == newPrompt
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
    def "check question prompt with short prompt"() {
        setup:
        def question = new ShortAnswer(1, "prompt")
        when:
        def newPrompt = "Short"
        question.SetQuestion(newPrompt)
        then:
        question.GetQuestion() == newPrompt
    }
    def "check question prompt with long prompt"() {
        setup:
        def question = new ShortAnswer(1, "prompt")
        when:
        def newPrompt = "This is a very long question description. Really, this description is far" +
                " too long to really be practical, but we must check methods from all angles. Do you know " +
                "how long is too long for a question prompt? The answer may surprise you, but may also not." +
                "The answer is this prompt. It is too long and makes no sense."
        question.SetQuestion(newPrompt)
        then:
        question.GetQuestion() == newPrompt
    }

}
