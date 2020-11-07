package P12_3;

import java.util.*;
public class Quiz {

    private ArrayList<Question> quizQuestions = new ArrayList<>();

    /**
     * A quiz consists of several questions of varying typesf (T,N,S,M)
     * @param quizQuestions - arrayList of questions
     */
    public Quiz(ArrayList<Question> quizQuestions){
        this.quizQuestions = quizQuestions;
    }

    public Quiz() {

    }
}
