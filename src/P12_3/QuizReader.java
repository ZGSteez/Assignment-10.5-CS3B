package P12_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class QuizReader {
    private ArrayList<Question> quiz = new ArrayList<>();

    public ArrayList<Question> returnQuiz() {
        return quiz;
    }

    public QuizReader() throws FileNotFoundException {
        Random random = new Random();
        int randomNum = random.nextInt(1000);

        if (randomNum <= 800){
            readFile("sampleQuiz");

        }

        else{
            readFile("sampleQuiz2");
        }

    }

    public void readFile(String fileName) throws FileNotFoundException {
        File inputFile = new File("src/P12_3/" + fileName);

        if (!inputFile.exists())
            throw new FileNotFoundException("Error: file not found");

        Scanner input = new Scanner(inputFile);

        String questionType = "";
        String theQuestion = "";
        ArrayList<String> answers = new ArrayList<>();
        ArrayList<Boolean> answerKey = new ArrayList<>();
        boolean multiAnswerFlag = false;
        boolean singleAnswerFlag = false;
        boolean questionFlag = false;
        boolean ranBeforeFlag = false;
        String theAnswer = "";
        while (input.hasNextLine()) {
            String line = input.nextLine();

            if ((line.equals("T") || line.equals("N") || line.equals("S") || line.equals("M"))) {
                if (ranBeforeFlag) {
                    quiz.add(new Question(questionType, theQuestion, answers, answerKey));
                    questionType = "";
                    theQuestion = "";
                    answers = new ArrayList<>();
                    answerKey = new ArrayList<>();
                }
                questionType = line;
            } else if (line.contains("?")) {
                theQuestion = line;
            } else if (line.contains("+") || line.contains("-")) {


                String values[] = line.split(" ");

                String answer = "";

                for (int i = 1; i < values.length;i++){
                    if (i != values.length - 1){
                        answer += values[i] + " ";
                    }

                    else{
                        answer += values[i];
                    }
                }
                answers.add(answer);




                if (line.contains("+")) {
                    answerKey.add(true);
                } else if (line.contains("-")) {
                    answerKey.add(false);
                }
            } else if (!line.contains("+") && !line.contains("-")) {
                answers.add(line);
                answerKey.add(true);
            }

           if (!(input.hasNextLine())){
                quiz.add(new Question(questionType, theQuestion, answers, answerKey));

            }

            if (ranBeforeFlag == false) {
                ranBeforeFlag = true;
            }


        }
        input.close();
    }


    public void show() {
        for (Question aQuestion : quiz) {
            System.out.print(aQuestion.returnQuestionType());
            System.out.print(" , ");
            System.out.print(aQuestion.returnTheQuestion());
            System.out.print(" , ");
            System.out.print(aQuestion.returnAnswers());
            System.out.print(" , ");
            System.out.print(aQuestion.returnAnswerKey());
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        QuizReader reader = new QuizReader();
      //  reader.readFile("sampleQuiz");
        reader.show();

    }
}
