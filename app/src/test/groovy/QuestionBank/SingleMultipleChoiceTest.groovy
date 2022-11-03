package QuestionBank

import spock.lang.Specification;

class SingleMultipleChoiceTest extends Specification {
    SingleMultipleChoice question;
    int ID;
    String questionPrompt;
    LinkedList choices;

    def setup() {
        choices = new LinkedList();
        choices.add("wrong1");
        choices.add("wrong2");
        choices.add("wrong3");
        choices.add("Right!");
        LinkedList rightanswer = new LinkedList();
        rightanswer.add("Right!");
        ID = 562;
        questionPrompt = "select the right answer:"
        question = new SingleMultipleChoice(ID,questionPrompt,rightanswer,choices);
    }

    def "check ID"(){
        when: true
        then:  question.GetIdNumber() == ID;
    }

    def "check question"(){
        when: true
        then: question.GetQuestion() == questionPrompt
    }

    def "check right answer"(){
        when: true
        then:  question.GetCorrectAnswer()[0] == "Right!";
    }

    def "check possible answers"(){
        when: true
        then:  question.GetPossibleAnswers() == choices;
    }


}
