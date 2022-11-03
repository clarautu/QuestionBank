package QuestionBank

import spock.lang.Specification

class TagTest extends Specification {

    //GetTagName
    def "get the current tag name"() {
        given:
        String TagName = "tag"
        def Tag = new Tag(TagName)

        when:
        def result = Tag.GetTagName()

        then:
        result == TagName

    }

    //GetQuestionsOfTagType
    def "get list of tags"() {
        given:
        LinkedList<Integer> Questions = [1,2,3,4,5]

        def list = new Tag(Questions)

        when:
        def result = list.GetQuestionsOfTagType()

        then:
        result.size() == 5
    }

    //setTagName(String TagName)
    def "set tag name"() {
        given:
        String name = "old tag"
        def Tag = new Tag(name)

        when:
        String newName = "new tag"
        Tag.setTagName(newName)

        then:
        Tag.GetTagName() == newName
    }

    //setQuestionsOfTagType(LinkedList<Integer> questionsOfTagType)
    def "set list of tags"() {
        given:
        LinkedList<Integer> questions = [1,2,3]
        def Tag = new Tag(questions)

        when:
        LinkedList<Integer> questions2 = [3,2,1,0]
        Tag.setQuestionsOfTagType(questions2)

        then:
        Tag.GetQuestionsOfTagType() == questions2
    }

    //AddQuestion(int id)
    def "add question id to list of Questions with this tag"() {
        given:
        int id = 1
        LinkedList<Integer> questions = [2,3,4]
        def Tag = new Tag(questions)

        when:
        def result = Tag.AddQuestion(id)

        then:
        result
    }

}
