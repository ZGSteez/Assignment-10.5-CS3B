package P12_3;

import java.util.*;
public class Question {

    private String questionType;
    private String theQuestion;
    public ArrayList<String> answers = new ArrayList<>();
    private ArrayList<Boolean> answerKey = new ArrayList<>();

    /**
     * Constructor for class Question,
     * @param questionType - the type of question being asked (T,N,S,M)
     * @param theQuestion - the question being asked
     * @param answers - list of answers (must be at least one, but can be up to 5)
     */
    public Question(String questionType, String theQuestion, ArrayList<String> answers, ArrayList<Boolean> answerKey){
        this.questionType = questionType;
        this.theQuestion = theQuestion;
        this.answers = answers;
        this.answerKey = answerKey;
    }


    public String returnQuestionType(){
        return questionType;
    }

    public String returnTheQuestion(){
        return theQuestion;
    }

    public ArrayList<String> returnAnswers(){
        return answers;
    }

    public ArrayList<Boolean> returnAnswerKey(){
        return answerKey;
    }
}
