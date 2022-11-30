package QuestionBank

import QuestionBank.BankFacade
import QuestionBank.MultipleAnswerChoice
import QuestionBank.QuestionBank
import QuestionBank.Questions
import QuestionBank.Tag
import org.codehaus.groovy.control.io.InputStreamReaderSource
import spock.lang.Specification

import java.nio.charset.StandardCharsets


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
        facade.CreateQuestion(3, "Test question", null, null)

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
        setup:
        String tagName = "test"
        int tagID = 1
        addBasicQuestion()
        addBasicTag(tagID, tagName)

        when:
        LinkedList<Questions> questions = facade.GetTaggedQuestions(tagName)
        Questions question1 = questions.get(0)
        LinkedList<Tag> tags = question1.GetTags()
        String tag = tags.get(0)

        then:
        tag == tagName
    }

//    def "GetUserQuestionType"() {
//        setup:
//        def mockScanner = GroovyStub(Scanner.class) {
//            nextLine() >> "1\n"
//        }
//        facade.scanner = mockScanner
//
//        when:
//        int gotType = facade.GetUserQuestionType()
//
//        then:
//        gotType == 1
//    }

    def "GetUserQuestionDescription"() {

    }

    def "GetUserCorrectAnswers"() {

    }

    def "GetUserPossibleAnswers"() {

    }


    ///////////////////////////////////////////////////////////////////////////////////
    //  Save and Load Tests cause problems with Github actions due to file creation  //
    ///////////////////////////////////////////////////////////////////////////////////
    //  Uncomment to use

//    def "save"() {
//        setup:
//        String pwd = System.getProperty("user.dir")
//        File directory = new File(pwd)
//        String file_name = "test_file.txt"
//        String saved_file = pwd + "\\" + file_name
//        File made_file = new File(saved_file)
//        when:
//        Boolean saved = facade.save(directory, file_name)
//
//        then:
//        if (made_file.exists()){
//            made_file.delete()
//        }
//        saved == true
//
//    }
//
//    def "load"() {
//        setup:
//        String pwd = System.getProperty("user.dir")
//        File directory = new File(pwd)
//        String file_name = "test_file.txt"
//        String saved_file = pwd + "\\" + file_name
//        File made_file = new File(saved_file)
//
//        when:
//        StateObject saved_state = facade.stateObject
//        facade.save(directory, file_name)
//        facade.load(made_file)
//        StateObject loaded_state = facade.stateObject
//
//        then:
//        if (made_file.exists()){
//            made_file.delete()
//        }
//        saved_state == loaded_state
//    }

}

