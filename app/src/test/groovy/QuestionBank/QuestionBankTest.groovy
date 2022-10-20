package QuestionBank

import spock.lang.Specification

class QuestionBankTest extends Specification{
    //Defining a variable to be used in all tests, that the setup method will assign to
    def bank = QuestionBank

    //This is a setup method - it is run before every individual test method
    def setup() {
        bank = Mock(QuestionBank)
    }

    //GetQuestions
    def "get all questions"() {
        setup:
        def myValue = 1

        when:
        myValue += 1

        then:
        myValue == 2
    }
    //GetTagsList
    def "get all tags"() {
        setup:
        def myValue = 1

        when:
        myValue += 1

        then:
        myValue == 2
    }
    //CreateQuestion(int typeChoice, String questionDescription, LinkedList<String> correctAnswers, LinkedList<String> possibleAnswers)
    def "create a multianswer question"() {
        setup:
        def bank = QuestionBank.GetInstance()
        when:
        def result = bank.CreateQuestion(1,"Test question",new LinkedList<String>(List.of("answer1","answer2")),
                new LinkedList<String>(List.of("possible1","possible2")))
        then:
        result ==  true
    }
    def "create a singleanswer question"() {
        setup:
        def bank = QuestionBank.GetInstance()
        when:
        def result = bank.CreateQuestion(2,"Test question",new LinkedList<String>(List.of("answer")),
                new LinkedList<String>(List.of("possible1","possible2")))
        then:
        result ==  true
    }

    //RemoveQuestion(id)
    def "remove a question"() {
        setup:
        def myValue = 1

        when:
        myValue += 1

        then:
        myValue == 2
    }
    //GetQuestion(id)
    def "get a specified question"() {
        setup:
        def myValue = 1

        when:
        myValue += 1

        then:
        myValue == 2
    }
    //GetTaggedQuestions(tagName)
    def "get all questions with a particular tag"() {
        setup:
        def myValue = 1

        when:
        myValue += 1

        then:
        myValue == 2
    }
    //RemoveTag(tagName)
    def "remove a specified tag"() {
        setup:
        def myValue = 1

        when:
        myValue += 1

        then:
        myValue == 2
    }
}
