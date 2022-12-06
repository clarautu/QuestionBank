package QuestionBank

import spock.lang.Specification

class QuestionBankTest extends Specification{

    //Setup method - run before every test
    def setup() {
        QuestionBank.GetInstance().ResetBank()
    }

    //GetQuestions
    def "get all questions"() {
        setup:
        def bank = QuestionBank.GetInstance()
        bank.CreateQuestion(1,"Test question",new LinkedList<String>(List.of("answer1","answer2")),
                new LinkedList<String>(List.of("possible1","possible2")))
        bank.CreateQuestion(2,"Test question 2",new LinkedList<String>(List.of("answer1","answer2")),
                new LinkedList<String>(List.of("possible1","possible2")))
        bank.CreateQuestion(3,"Test question 3",new LinkedList<String>(List.of("answer1","answer2")),
                new LinkedList<String>(List.of("possible1","possible2")))

        when:
        def result = bank.GetQuestions()

        then:
        result.size() == 3
    }

    //GetTagsList
    def "get all tags"() {
        setup:
        def bank = QuestionBank.GetInstance()
        bank.CreateQuestion(1,"Test question",new LinkedList<String>(List.of("answer1","answer2")),
                new LinkedList<String>(List.of("possible1","possible2")))

        def q = bank.GetQuestions().peekFirst()
        bank.AddTagToQuestion(q.GetIdNumber(), "TagName")

        when:
        def result = bank.GetTagsList().size()

        then:
        result == 1
    }

    //CreateQuestion(int typeChoice, String questionDescription, LinkedList<String> correctAnswers, LinkedList<String> possibleAnswers)
    def "create a multi answer question"() {
        setup:
        def bank = QuestionBank.GetInstance()
        when:
        def result = bank.CreateQuestion(1,"Test question",new LinkedList<String>(List.of("answer1","answer2")),
                new LinkedList<String>(List.of("possible1","possible2")))
        then:
        result ==  true
    }
    def "create a single answer question"() {
        setup:
        def bank = QuestionBank.GetInstance()
        when:
        def result = bank.CreateQuestion(2,"Test question",new LinkedList<String>(List.of("answer")),
                new LinkedList<String>(List.of("possible1","possible2")))
        then:
        result ==  true
    }
    def "create a short answer question"() {
        setup:
        def bank = QuestionBank.GetInstance()
        when:
        def result = bank.CreateQuestion(3,"Test question",null,null)
        then:
        result ==  true
    }
    def "create a short answer question and give it lists of answers"() {
        setup:
        def bank = QuestionBank.GetInstance()
        when:
        def result = bank.CreateQuestion(3,"Test question",new LinkedList<String>(List.of("answer")),
                new LinkedList<String>(List.of("possible1","possible2")))
        then:
        result ==  true
    }

    //RemoveQuestion(id)
    def "remove a question"() {
        setup:
        def bank = QuestionBank.GetInstance()
        bank.CreateQuestion(2,"Test question",new LinkedList<String>(List.of("answer")),
                new LinkedList<String>(List.of("possible1","possible2")))
        when:
        bank.RemoveQuestion(0)
        def result = bank.GetQuestions()

        then:
        result.size() == 0
    }

    //GetQuestion(id)
    def "get a specified question"() {
        setup:
        def bank = QuestionBank.GetInstance()
        bank.CreateQuestion(2,"Test question 1",new LinkedList<String>(List.of("answer")),
                new LinkedList<String>(List.of("possible1","possible2")))
        bank.CreateQuestion(2,"Test question 2",new LinkedList<String>(List.of("answer")),
                new LinkedList<String>(List.of("possible1","possible2")))

        when:
        def result = bank.GetQuestion(1)

        then:
        result.GetIdNumber() == 1
    }

    //AddTagToQuestion(id, tagName)
    def "add a tag to a question"() {
        setup:
        def bank = QuestionBank.GetInstance()
        bank.CreateQuestion(2,"Test question 1",new LinkedList<String>(List.of("answer")),
                new LinkedList<String>(List.of("possible1","possible2")))
        bank.AddTagToQuestion(0, "TestQuestion")

        when:
        def result = bank.GetQuestion(0).GetTags()

        then:
        result.getFirst().equals("TestQuestion")
    }

    //GetTaggedQuestions(tagName)
    def "get all questions with a particular tag"() {
        setup:
        def bank = QuestionBank.GetInstance()
        bank.CreateQuestion(2,"Test question 1",new LinkedList<String>(List.of("answer")),
                new LinkedList<String>(List.of("possible1","possible2")))
        bank.CreateQuestion(2,"Test question 2",new LinkedList<String>(List.of("answer")),
                new LinkedList<String>(List.of("possible1","possible2")))
        bank.AddTagToQuestion(0, "TestQuestion")
        bank.AddTagToQuestion(1, "TestQuestion")

        when:
        def result = bank.GetTaggedQuestions("TestQuestion")

        then:
        result.size() == 2
    }

    //RemoveTag(tagName)
    def "remove a specified tag"() {
        setup:
        def bank = QuestionBank.GetInstance()
        bank.CreateQuestion(2,"Test question 1",new LinkedList<String>(List.of("answer")),
                new LinkedList<String>(List.of("possible1","possible2")))
        bank.AddTagToQuestion(0, "TestQuestion")

        when:
        def result1 = bank.GetTagsList().size()
        bank.RemoveTag("TestQuestion")
        def result2 = bank.GetTagsList().size()

        then:
        result1 != result2
    }

    //ResetBank()
    def "reset the bank"() {
        setup:
        def bank = QuestionBank.GetInstance()
        bank.CreateQuestion(1,"Test question",new LinkedList<String>(List.of("answer1","answer2")),
                new LinkedList<String>(List.of("possible1","possible2")))

        when:
        bank.ResetBank()

        then:
        bank.GetQuestions().size() == 0
    }
}
