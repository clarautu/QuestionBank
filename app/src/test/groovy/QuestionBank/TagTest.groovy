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
        LinkedList<Integer> QuestionIDs = [1,2,3,4,5]

        def list = new Tag("tagName")
        list.setQuestionsOfTagType(QuestionIDs)

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
    def "set list of questions that have this tags with a short list"() {
        given:
        def Tag = new Tag("tagName")

        when:
        LinkedList<Integer> questionIDs = [3,2,1,0]
        Tag.setQuestionsOfTagType(questionIDs)

        then:
        Tag.GetQuestionsOfTagType() == questionIDs
    }
    def "set list of questions that have this tag with a long list"() {
        given:
        def Tag = new Tag("tagName")

        when:
        LinkedList<Integer> questionIDs = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]
        Tag.setQuestionsOfTagType(questionIDs)

        then:
        Tag.GetQuestionsOfTagType() == questionIDs
    }

    //AddQuestion(int id)
    def "add question id to list of Questions with this tag"() {
        given:
        int id = 1
        def Tag = new Tag("tagName")

        when:
        def result = Tag.AddQuestion(id)

        then:
        result
    }
    def "add question id to list of Questions with this tag 2"() {
        given:
        int id = 1
        def Tag = new Tag("tagName")

        when:
        Tag.AddQuestion(id)
        def questionID = Tag.GetQuestionsOfTagType().pop()

        then:
        questionID == id
    }

}
