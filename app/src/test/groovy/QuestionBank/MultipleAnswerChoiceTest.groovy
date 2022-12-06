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
    def "check ID with different number"() {
        when:
        def newID = 11
        question = new MultipleAnswerChoice(newID, questionPrompt, correctOptions, choices)
        then:
        question.GetIdNumber() == newID
    }
    def "check ID with a big number"() {
        when:
        def newID = 12345678
        question = new MultipleAnswerChoice(newID, questionPrompt, correctOptions, choices)
        then:
        question.GetIdNumber() == newID
    }

    def "check question"(){
        when: true
        then:  question.GetQuestion() == questionPrompt;
    }
    def "check question prompt with short prompt"() {
        when:
        def newPrompt = "Short"
        question.SetQuestion(newPrompt)
        then:
        question.GetQuestion() == newPrompt
    }
    def "check question prompt with long prompt"() {
        when:
        def newPrompt = "This is a very long question description. Really, this description is far" +
                " too long to really be practical, but we must check methods from all angles. Do you know " +
                "how long is too long for a question prompt? The answer may surprise you, but may also not." +
                "The answer is this prompt. It is too long and makes no sense."
        question.SetQuestion(newPrompt)
        then:
        question.GetQuestion() == newPrompt
    }

    def "check right answer"(){
        when: true
        then:  question.GetCorrectAnswer() == correctOptions;
    }
    def "check right answers when a new answer is added"() {
        when:
        question.AddCorrectAnswer("Answer3")
        correctOptions.add("Answer3")
        then:
        question.GetCorrectAnswer() == correctOptions
    }
    def "check right answers when several new answers are added"() {
        when:
        question.AddCorrectAnswer("Answer3")
        question.AddCorrectAnswer("Answer4")
        question.AddCorrectAnswer("Answer5")
        correctOptions.add("Answer3")
        correctOptions.add("Answer4")
        correctOptions.add("Answer5")
        then:
        question.GetCorrectAnswer() == correctOptions
    }
    def "check right answers when new answers are added in different orders"() {
        when:
        question.AddCorrectAnswer("Answer3")
        question.AddCorrectAnswer("Answer4")
        question.AddCorrectAnswer("Answer5")
        correctOptions.add("Answer3")
        correctOptions.add("Answer5")
        correctOptions.add("Answer4")
        then:
        question.GetCorrectAnswer() == correctOptions
    }

    def "check possible answers"() {
        when: true
        then:  question.GetPossibleAnswers() == choices;
    }
    def "check possible answers when a new possible answer is added"() {
        when:
        question.AddPossibleAnswer("Possible3")
        choices.add("Possible3")
        then:
        question.GetPossibleAnswers() == choices
    }
    def "check possible answers when several new possible answers are added"() {
        when:
        question.AddPossibleAnswer("Possible3")
        question.AddPossibleAnswer("Possible4")
        question.AddPossibleAnswer("Possible5")
        choices.add("Possible3")
        choices.add("Possible4")
        choices.add("Possible5")
        then:
        question.GetPossibleAnswers() == choices
    }
    def "check possible answers when new possibles answers are added in different orders"() {
        when:
        question.AddPossibleAnswer("Possible3")
        question.AddPossibleAnswer("Possible4")
        question.AddPossibleAnswer("Possible5")
        choices.add("Possible3")
        choices.add("Possible5")
        choices.add("Possible4")
        then:
        question.GetPossibleAnswers() == choices
    }
}
