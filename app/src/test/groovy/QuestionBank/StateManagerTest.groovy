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
    ///////////////////////////////////////////////////////////////////////////////////
    //  Save and Load Tests cause problems with Github actions due to file creation  //
    ///////////////////////////////////////////////////////////////////////////////////
    //  Uncomment to use

//    def "saveDataTest"() {
//        setup:
//        StateObject stateObject = new StateObject()
//        String pwd = System.getProperty("user.dir")
//        File directory = new File(pwd)
//        String file_name = "test_file_for_saveDataTest.txt"
//        String saved_file = pwd + "\\" + file_name
//
//        when:
//        stateManager.saveData(directory, file_name, stateObject)
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
//        String file_name = "test_file_for_createFileTest.txt"
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
//        StateObject stateObject = new StateObject()
//        String pwd = System.getProperty("user.dir")
//        File directory = new File(pwd)
//        String file_name = "test_file_for_loadDataTest.txt"
//        String saved_file = pwd + "\\" + file_name
//        File load_from_this = new File(saved_file)
//        stateManager.saveData(directory, file_name, stateObject)
//        StateObject savedStateObject = stateObject
//
//        when:
//        stateManager.loadData(load_from_this, stateObject)
//        StateObject loadedStateObject = stateObject
//
//        then:
//        if (load_from_this.exists()) {
//            load_from_this.delete()
//        }
//        savedStateObject.getQuestions() == loadedStateObject.getQuestions()
//        savedStateObject.getTagsLists() == loadedStateObject.getTagsLists()
//    }
}
