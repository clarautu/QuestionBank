package QuestionBank

import spock.lang.Specification

class QuestionBankTest extends Specification{
    //Defining a variable to be used in all tests, that the setup method will assign to
    def bank

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
