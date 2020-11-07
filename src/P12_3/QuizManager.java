package P12_3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuizManager {
    private final QuizReader reader = new QuizReader();
    private ArrayList<String> answers = new ArrayList<>();
    private ArrayList<Question> quizQuestions = reader.returnQuiz();
    private final double AMOUNT_OF_QUESTIONS = quizQuestions.size();

    /**
     * Constructor for Quiz Manager
     * @throws FileNotFoundException
     */
    public QuizManager() throws FileNotFoundException {
        menu();
    }

    /**
     * Main menu method
     */
    public void menu() {
        for (Question aQuestion : quizQuestions) {

            System.out.println(displayMessage(aQuestion.returnQuestionType()));
            System.out.println(aQuestion.returnTheQuestion());

            if (aQuestion.returnQuestionType().equals("M") || aQuestion.returnQuestionType().equals("S")) {

                int counter = 0;
                for (String answer : aQuestion.returnAnswers()) {
                    System.out.println(convertInteger(counter) + "). " + answer);
                    counter++;
                }
                answers.add(getExactAnswer(aQuestion.returnQuestionType()));

            } else {
                String answer = getExactAnswer(aQuestion.returnQuestionType());

                String components[] = answer.split(" ");

                String spacesRemoved = "";

                for (String a : components) {
                    spacesRemoved += a;
                }
                answer = spacesRemoved;

                answers.add(answer);
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("You scored " + processScore() * 100 + "%.");
    }

    /**
     * Calculates scores using answers
     * @return - score for user
     */
    public double processScore() {
        double score = 0;
        for (int i = 0; i < AMOUNT_OF_QUESTIONS; i++) {
            switch (quizQuestions.get(i).returnQuestionType()) {
                case "T", "N" -> {
                    String response = answers.get(i);
                    response = response.toLowerCase();

                    String correctAnswer = quizQuestions.get(i).returnAnswers().get(0);
                    correctAnswer = correctAnswer.toLowerCase();

                    if (response.equals(correctAnswer)) {
                        score += 1;
                    }
                }
                case "S" -> {
                    String response = answers.get(i);
                    if (response.length() == 1) {
                        switch (response) {
                            case "A":
                                if (quizQuestions.get(i).returnAnswerKey().get(0).equals(true)) {
                                    score += 1;
                                }
                                break;
                            case "B":
                                if (quizQuestions.get(i).returnAnswerKey().get(1).equals(true)) {
                                    score += 1;
                                }
                                break;
                            case "C":
                                if (quizQuestions.get(i).returnAnswerKey().get(2).equals(true)) {
                                    score += 1;
                                }
                                break;
                            case "D":
                                if (quizQuestions.get(i).returnAnswerKey().get(3).equals(true)) {
                                    score += 1;
                                }
                                break;
                        }
                    }
                }
                case "M" -> {
                    String response = answers.get(i);
                    ArrayList<String> answerParts = answerComponents(response);
                    ArrayList<String> answerKey = theAnswerKey(quizQuestions.get(i));
                    double scoreForEachRightAnswer = (double)1 / (double)answerKey.size();

                    for (String answerPart : answerParts) {


                        switch (answerPart) {
                            case "A":
                                if (answerKey.contains(answerPart)) {
                                    score += scoreForEachRightAnswer ;
                                    answerKey.remove("A");
                                }
                                break;
                            case "B":
                                if (answerKey.contains(answerPart)) {
                                    score += scoreForEachRightAnswer ;
                                    answerKey.remove("B");


                                }
                                break;
                            case "C":
                                if (answerKey.contains(answerPart)) {
                                    score += scoreForEachRightAnswer ;
                                    answerKey.remove("C");


                                }
                                break;
                            case "D":
                                if (answerKey.contains(answerPart)) {
                                    score += scoreForEachRightAnswer ;
                                    answerKey.remove("D");


                                }
                                break;
                            case "E":
                                if (answerKey.contains(answerPart)) {
                                    score += scoreForEachRightAnswer ;
                                    answerKey.remove("E");

                                }
                                break;
                        }

                    }
                }
            }

        }
        return score / AMOUNT_OF_QUESTIONS;
    }


    /**
     * Gets user answer
     * @param questionType - how answer is accepted is based on question type
     * @return - user answer
     */
    public String getExactAnswer(String questionType) {
        System.out.print("Enter your answer: ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        switch (questionType) {
            case "S":

                while ((!answer.toUpperCase().equals("A") &&
                        !answer.toUpperCase().equals("B") &&
                        !answer.toUpperCase().equals("C") &&
                        !answer.toUpperCase().equals("D")
                )) {
                    answer = scanner.nextLine();
                    answer = answer.toUpperCase();
                }
                break;
            case "N":
                try {
                    double aDouble = Double.parseDouble(answer);
                } catch (NumberFormatException e) {
                    System.out.println("Something was wrong with your original response. Please re-enter a different response.");
                    answer = scanner.nextLine();

                }
                break;
            case "M":
                if (answer.length() != 5) {
                    while (answer.length() != 5) {
                        answer += "@";
                    }

                }
                break;
        }

        return answer;
    }


    /**
     * Displays user what type of question is being asked
     * @param questionType  - type of question being asked
     * @return - the message that is being displayed
     */
    public String displayMessage(String questionType) {
        return switch (questionType) {
            case "T" -> "Text question";
            case "N" -> "Number question";
            case "S" -> "Choice question with single answer";
            case "M" -> "Choice question with multiple answers";
            default -> "";
        };
    }

    /**
     * returns the answer key in the format of ABCDE or any key containing at least A-E
     * @param quizQ - quiz instance used to generate answer key
     * @return - ArrayList of String
     */
    public ArrayList<String> theAnswerKey(Question quizQ) {
        int count = 0;
        ArrayList<String> answerKey = new ArrayList<>();
        for (boolean a : quizQ.returnAnswerKey()) {
            if (a) {
                if (count <= 4 && count >= 0)
                    answerKey.add(convertInteger(count));
            }
            count++;
        }
        return answerKey;
    }

    /**
     * Separates user answer for M type questions letter by letter
     * @param answer - user answer
     * @return - ArrayList of String
     */
    public ArrayList<String> answerComponents(String answer) {
        ArrayList<String> answerParts = new ArrayList<>();
        for (int i = 0; i < answer.length(); i++) {
            answerParts.add(String.valueOf(answer.charAt(i)));
        }
        return answerParts;
    }

    /**
     * Converts an integer to a corresponding character
     * @param number - number used for conversion
     * @return  - corresponding String
     */
    public String convertInteger(int number) {
        String convert = "";
        if (number == 0)
            convert = "A";
        else if (number == 1)
            convert = "B";
        else if (number == 2)
            convert = "C";
        else if (number == 3)
            convert = "D";
        else if (number == 4)
            convert = "E";
        return convert;
    }

    public static void main(String[] args) throws FileNotFoundException {
        QuizManager newManager = new QuizManager();
    }

}
