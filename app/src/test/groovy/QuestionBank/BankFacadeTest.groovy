package QuestionBank

import spock.lang.Specification

class BankFacadeTest extends Specification{

    // Variables
    def facade;
    def questionBank;


    // Setup
    def setup() {
        facade = Mock(BankFacade)
//        questionBank = Mock(QuestionBank)
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

    def "GetQuestions"() {
        setup:

        when:
        facade = new BankFacade()
        int typeChoice = 1
        String questionDescription = "test"
        LinkedList<String> correctAnswers = new LinkedList<String>()
        correctAnswers.add("test_correct_answer")
        LinkedList<String> possibleAnswers = new LinkedList<String>()
        possibleAnswers.add("test_possible_answer")
        facade.QuestionBank.CreateQuestion(typeChoice, questionDescription, correctAnswers, possibleAnswers)

        then:
        facade.GetQuestion(0).GetIdNumber() == 0
        facade.GetQuestion(0).GetQuestion() == questionDescription
        facade.GetQuestion(0).GetCorrectAnswer() == correctAnswers
        facade.GetQuestion(0).GetPossibleAnswers() == possibleAnswers
    }

    def "GetTagsList"() {
        setup:

        when:
        facade = new BankFacade()

        then:
        facade.GetTagsList() == []
    }

    def "CreateQuestion"() { // Cannot directly add a question, so this is sorta pointless
        setup:

        when:
        facade = new BankFacade()
        int typeChoice = 1
        String questionDescription = "test"
        LinkedList<String> correctAnswers = new LinkedList<String>()
        correctAnswers.add("test_correct_answer")
        LinkedList<String> possibleAnswers = new LinkedList<String>()
        possibleAnswers.add("test_possible_answer")
        facade.QuestionBank.CreateQuestion(typeChoice, questionDescription, correctAnswers, possibleAnswers)

        then:
        facade.GetQuestion(0).GetIdNumber() == 0
        facade.GetQuestion(0).GetQuestion() == questionDescription
        facade.GetQuestion(0).GetCorrectAnswer() == correctAnswers
        facade.GetQuestion(0).GetPossibleAnswers() == possibleAnswers
    }

    def "RemoveQuestion"() {
        setup:

        when:
        facade = new BankFacade()
        int typeChoice = 1
        String questionDescription = "test"
        LinkedList<String> correctAnswers = new LinkedList<String>()
        correctAnswers.add("test_correct_answer")
        LinkedList<String> possibleAnswers = new LinkedList<String>()
        possibleAnswers.add("test_possible_answer")
        facade.QuestionBank.CreateQuestion(typeChoice, questionDescription, correctAnswers, possibleAnswers)
        facade.RemoveQuestion(0)

        then:
        facade.GetQuestion(0) == null
    }

    def "GetTaggedQuestions"() { // q is null?
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
//        facade.QuestionBank.AddTagToQuestion(0, "test")
//
//        then:
//        facade.GetTaggedQuestions("test") == 0
    }

    def "GetUserQuestionType"() {
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
//
//        then:
//        facade.GetUserQuestionType() == 1
    }

    def "GetUserQuestionDescription"() {

    }

    def "GetUserCorrectAnswers"() {

    }

    def "GetUserPossibleAnswers"() {

    }

    def "save"() {
        setup:

        when:
        facade = new BankFacade()
        int typeChoice = 1
        String questionDescription = "test"
        LinkedList<String> correctAnswers = new LinkedList<String>()
        correctAnswers.add("test_correct_answer")
        LinkedList<String> possibleAnswers = new LinkedList<String>()
        possibleAnswers.add("test_possible_answer")
        facade.QuestionBank.CreateQuestion(typeChoice, questionDescription, correctAnswers, possibleAnswers)
        String pwd = System.getProperty("user.dir")
        File directory = new File(pwd)
        String file_name = "test_file.txt"
        facade.save(directory, file_name)
        String saved_file = pwd + "\\" + file_name

        then:
        File check = new File(saved_file)
        check.exists() == true
        if (check.exists() == true) {
            check.delete()
        }
    }

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

