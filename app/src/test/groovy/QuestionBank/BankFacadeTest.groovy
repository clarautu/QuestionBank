package QuestionBank

import spock.lang.Specification

class BankFacadeTest extends Specification{


    // Variables
    def facade


    // Fixtures
    def setup() {
        facade = BankFacade.GetInstance()
    }

    def cleanup() {
        facade = null
    }

    def addBasicQuestion() {
        int typeChoice = 1
        String questionDescription = "test"
        LinkedList<String> correctAnswers = new LinkedList<String>()
        correctAnswers.add("test_correct_answer")
        LinkedList<String> possibleAnswers = new LinkedList<String>()
        possibleAnswers.add("test_possible_answer")
        facade.QuestionBank.CreateQuestion(typeChoice, questionDescription, correctAnswers, possibleAnswers)
    }

    def addBasicTag(int tagID, String tagName) {
        facade.QuestionBank.AddTagToQuestion(tagID, tagName)
    }

    // Tests

    // Checks that there cannot be more than one instance of BankFacade
    def "test_singleton"() {
        setup:
        def facadeOne = facade.GetInstance()

        and:
        def facadeCopy = facade.GetInstance()

        when:
        def before = facade.hashCode()
        def after = facadeCopy.hashCode()

        then:
        before == after
    }


    def "GetQuestions check"() {
        when:
        addBasicQuestion()

        then:
        facade.GetQuestion(0) != null
    }

    def "Check GetQuestions input question has good values"() {
        setup:
        int typeChoice = 1
        String questionDescription = "test"
        LinkedList<String> correctAnswers = new LinkedList<String>()
        correctAnswers.add("test_correct_answer")
        LinkedList<String> possibleAnswers = new LinkedList<String>()
        possibleAnswers.add("test_possible_answer")

        when:
        facade.QuestionBank.CreateQuestion(typeChoice, questionDescription, correctAnswers, possibleAnswers)

        then:
        facade.GetQuestion(0).GetIdNumber() == 0
        facade.GetQuestion(0).GetQuestion() == questionDescription
        facade.GetQuestion(0).GetCorrectAnswer() == correctAnswers
        facade.GetQuestion(0).GetPossibleAnswers() == possibleAnswers
    }

    def "GetTagsList_Test"() {
        setup:
        addBasicQuestion()

        when:
        addBasicTag(0, "test")
        LinkedList<Tag> taglist = facade.GetTagsList()
        LinkedList<Tag> listOtags = facade.QuestionBank.TagsLists

        then:
        taglist == listOtags
    }

    def "CreateQuestion_Test"() { // Cannot directly add a question, so this is sorta pointless
        setup:
        def facade = Stub(BankFacade)

        and:
        facade.GetUserQuestionType() >> 1
        facade.GetUserQuestionDescription() >> "test"
        facade.GetUserCorrectAnswers() >> "test"
        facade.GetUserCorrectAnswers() >> "test"

        when:
        facade.CreateQuestion()

        then:
        facade.GetQuestion(0) != null

    }

    def "RemoveQuestion_Test"() {
        setup:
        addBasicQuestion()

        when:
        facade.RemoveQuestion(0)

        then:
        facade.GetQuestion(0) == null
    }

    def "GetTaggedQuestions_Test"() {
        when:
        addBasicQuestion()
        String tagName = "test"
        LinkedList<Questions> questions = facade.GetTaggedQuestions(tagName)

        then:
        questions != null
    }

//    def "GetUserQuestionType"() {
//        setup:
////        def facade = Stub(BankFacade.class)
//        String userInput = String.format("1")
//        ByteArrayInputStream bais = new ByteArrayInputStream(getBytes("hello"))
//        System.setIn(bais)
//
//        when:
//        int result = facade.GetUserQuestionType()
//
//        then:
//        result == 1
//    }

    def "GetUserQuestionDescription"() {

    }

    def "GetUserCorrectAnswers"() {

    }

    def "GetUserPossibleAnswers"() {

    }

//    def "save"() {
//        setup:
//
//        when:
//        facade = new BankFacade()
//        int typeChoice = 1
//        String questionDescription = "test"
//        LinkedList<String> correctAnswers = new LinkedList<String>()
//        correctAnswers.add("test_correct_answer")
//        LinkedList<String> possibleAnswers = new LinkedList<String>()
//        possibleAnswers.add("test_possible_answer")
//        facade.QuestionBank.CreateQuestion(typeChoice, questionDescription, correctAnswers, possibleAnswers)
//        String pwd = System.getProperty("user.dir")
//        File directory = new File(pwd)
//        String file_name = "test_file.txt"
//        facade.save(directory, file_name)
//        String saved_file = pwd + "\\" + file_name
//
//        then:
//        File check = new File(saved_file)
//        check.exists() == true
//        if (check.exists() == true) {
//            check.delete()
//        }
//    }

//    def "load"() {
//
//        setup:
//
//        when:
//        facade = new BankFacade()
//        int typeChoice = 1
//        String questionDescription = "test"
//        LinkedList<String> correctAnswers = new LinkedList<String>()
//        correctAnswers.add("test_correct_answer")
//        LinkedList<String> possibleAnswers = new LinkedList<String>()
//        possibleAnswers.add("test_possible_answer")
//        facade.QuestionBank.CreateQuestion(typeChoice, questionDescription, correctAnswers, possibleAnswers)
//        String pwd = System.getProperty("user.dir")
//        File directory = new File(pwd)
//        String file_name = "test_file.txt"
//        facade.save(directory, file_name)
//        String saved_file = pwd + "\\" + file_name
//        File file_to_load = new File(saved_file)
//
//        BankFacade facade2 = new BankFacade()
//        facade2.load(file_to_load)
//        System.out.println("hello2")
//
//        then:
//        facade2.GetQuestion(0).GetIdNumber() == 0
//        File check = new File(saved_file)
//        if (check.exists()) {
//            check.delete()
//        }
//    }
}

