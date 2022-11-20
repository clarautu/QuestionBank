package QuestionBank;

import spock.lang.Specification;

class MultipleAnswerChoiceTest extends Specification {
    MultipleAnswerChoice question;
    int ID;
    String questionPrompt;
    LinkedList choices;
    LinkedList correctOptions;

    def setup() {
        choices = new LinkedList(List.of("wrong1","wrong2","wrong3","Right1!","Right2!"));
        correctOptions = new LinkedList(List.of("Right1!","Right2!"));
        ID = 562;
        questionPrompt = "select the right answer:"
        question = new MultipleAnswerChoice(ID,questionPrompt,correctOptions,choices);
    }

    def "check ID"(){
        when: true
        then:  question.GetIdNumber() == ID;
    }

    def "check question"(){
        when: true
        then:  question.GetQuestion() == questionPrompt;
    }

    def "check right answer"(){
        when: true
        then:  question.GetCorrectAnswer() == correctOptions;
    }

    def "check possible answers"() {
        when: true
        then:  question.GetPossibleAnswers() == choices;
    }
}
