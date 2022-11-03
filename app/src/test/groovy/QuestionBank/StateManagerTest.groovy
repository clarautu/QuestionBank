package QuestionBank

import org.codehaus.groovy.ast.VariableScope
import spock.lang.Specification

class StateManagerTest extends Specification {

    // Variables
    def stateManager;

    // Setup
    def setup() {
        stateManager = new StateManager()
    }

    def cleanup() {
        stateManager = null
    }

    // Tests

//    def "saveDataTest"() {
//        setup:
//        QuestionBank questionBank = QuestionBank.GetInstance()
//        String pwd = System.getProperty("user.dir")
//        File directory = new File(pwd)
//        String file_name = "test_file.txt"
//        String saved_file = pwd + "\\" + file_name
//
//        when:
//        stateManager.saveData(directory, file_name, questionBank)
//
//        then:
//        File check = new File(saved_file)
//        check.exists() == true
//        if (check.exists() == true) {
//            check.delete()
//        }
//    }
//
//    def "createFileTest"() {
//        setup:
//        String pwd = System.getProperty("user.dir")
//        File directory = new File(pwd)
//        String file_name = "test_file.txt"
//        String saved_file = pwd + "\\" + file_name
//
//        when:
//        stateManager.createFile(saved_file)
//
//        then:
//        File check = new File(saved_file)
//        check.exists()
//        if (check.exists()) {
//            check.delete()
//        }
//    }
//
//    def "loadDataTest"() {
//        setup:
//        QuestionBank questionBank = QuestionBank.GetInstance()
//        String pwd = System.getProperty("user.dir")
//        File directory = new File(pwd)
//        String file_name = "test_file.txt"
//        String saved_file = pwd + "\\" + file_name
//        File load_from_this = new File(saved_file)
//        stateManager.saveData(directory, file_name, questionBank)
//
//        when:
//        QuestionBank questionBankLoad
//        questionBankLoad = stateManager.loadData(load_from_this)
//
//        then:
//        if (load_from_this.exists()) {
//            load_from_this.delete()
//        }
//        questionBank.Questions == questionBankLoad.Questions
//        questionBank.TagsLists == questionBankLoad.TagsLists
//    }
}
