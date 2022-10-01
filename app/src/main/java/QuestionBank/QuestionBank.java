package QuestionBank;

import java.util.LinkedList;

public class QuestionBank {
    private LinkedList<Questions> Questions;
    private LinkedList<Tag> TagsLists;

    /**
     * Method that returns the list of all questions
     * @return A list containing all questions
     */
    public LinkedList GetQuestions(){
        return this.Questions;
    }

    /**
     * Method that returns a list of all tags
     * @return A list containing all tags
     */
    public LinkedList GetTagsList(){
        return this.TagsLists;
    }

    /**
     * Method that creates a question, based on user input
     * @return True if the question was created successfully; False otherwise
     */
    public Boolean CreateQuestion(){
        int lastUsedId; // Variable to hold last used id number for a question
        if (this.Questions.peekLast() == null) { // Check if any questions have been added
            // If no questions yet, set lastUsedId to -1
            lastUsedId = -1;
        } else { // There is at least one question
            // Set lastUsedId equal to the last question's id number
            lastUsedId = this.Questions.peekLast().GetIdNumber();
        }
        int currentId = lastUsedId + 1;

        // Create question here
        // will need to prompt user for what kind of question to be created
        // and what tag to list it as
        // If no tag specified, have a default tag?
        // Once done, add new question to this.Questions

        return null;
    }

    /**
     * Method that finds and removes a Question with a specified ID number
     * @param ID The ID number of the question being searched for
     * @return True if the question was successfully removed; False if question not found
     */
    public Boolean RemoveQuestion(int ID){
        for (Questions q : this.Questions) {
            if (q.GetIdNumber() == ID) {
                return Questions.remove(q);
            }
        }
        return false;
    }

    /**
     * Method that finds and returns a Question with a specified ID number
     * @param ID The ID number of the question being searched for
     * @return The question with the specified ID number; Null if question not found
     */
    public Questions GetQuestion(int ID){
        for (Questions q : this.Questions) {
            if (q.GetIdNumber() == ID) {
                return q;
            }
        }
        return null;
    }

    /**
     * Method that finds and returns all questions of a specified tag
     * @param tagname The name of the tag to search for questions of
     * @return A list of all questions with the specified tag; Null if tag not found
     */
    public LinkedList GetTaggedQuestions(String tagname) {
        LinkedList<Questions> taggedQuestions = new LinkedList<Questions>();

        // Search through this.TagsList to find the specified tag
        for (Tag t : this.TagsLists) {
            if (t.GetTagName() == tagname) { // Found right tag
                // List of id numbers for questions that have the specified tag
                LinkedList<Integer> ids = t.GetQuestionsOfTagType();
                // Search for questions with id numbers in ids, then add questions to taggedQuestions
                for (int id : ids) {
                    for (Questions q : this.Questions) {
                        if (q.GetIdNumber() == id) {
                            taggedQuestions.add(q);
                            // Break out of first for loop, as the question with the id number has been found
                            break;
                        }
                    }
                }
                return taggedQuestions;
            }
        }
        // Tag not found, return null
        return null;
    }
}
