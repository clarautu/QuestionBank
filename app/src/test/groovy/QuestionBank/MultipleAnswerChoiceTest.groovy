package QuestionBank;

import spock.lang.Specification;

public class MultipleAnswerChoiceTest extends Specification {
    MultipleAnswerChoice question;
    int ID;
    String questionPrompt;
    LinkedList choices;
    LinkedList correctoptions;

    def setup() {
        choices = new LinkedList();
        choices.add("wrong1");
        choices.add("wrong2");
        choices.add("wrong3");
        choices.add("Right1!");
        choices.add("Right2!");
        correctoptions = new LinkedList();
        correctoptions.add("Right1!");
        correctoptions.add("Right2!");
        ID = 562;
        questionPrompt = "select the right answer:"
        question = new MultipleAnswerChoice(ID,questionPrompt,correctoptions,choices);
    }

    def "check ID"(){
        assert question.GetIdNumber() == ID;
    }

    def "check question"(){
        assert question.GetQuestion() == questionPrompt;
    }

    def "check right answer"(){
        assert question.GetCorrectAnswer() == correctoptions;
    }

    def "check possible answers"() {
        assert question.GetPossibleAnswers() == choices;
    }
}
