package dk.kea.projekt1_gruppe3_maven.Service;

import dk.kea.projekt1_gruppe3_maven.Model.Question;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

import static dk.kea.projekt1_gruppe3_maven.Service.DataTypeConversion.stringToInt;


@Service
public class QuestionService {

    // hard code the AdventureQuestionsFile.txt file data here for loading Question objects

    List <Question> questions = new ArrayList<>();

    Map<Integer, Question> adventureQuestionMap = new HashMap<Integer, Question>() {
        {
        put(1, new Question(1, "How much food gets wasted globally each year?", Arrays.asList("1.3 billion tonnes", "5 billion tonnes", "300 million tonnes"), "1.3 billion tonnes"));
        put(2, new Question(2, "What is composting?", Arrays.asList("Throwing food away", "A natural process that turns organic material into a nutrient-rich soil conditioner", "Collective term for fruits and vegetables"), "A natural process that turns organic material into a nutrient-rich soil conditioner"));
        put(3, new Question(3, "What's the best way to reduce food waste?", Arrays.asList("Buy less food", "Ignore Use-By Dates", "Always finish your meals"), "Buy less food"));
        put(4, new Question(4, "Where does most food waste occur?", Arrays.asList("Retail stores", "Homes", "Farms"), "Homes"));
        put(5, new Question(5, "How much food do average American households throw away each year?", Arrays.asList("$500 worth", "$50 worth", "$1500 worth"), "$1500 worth"));
        put(6, new Question(6, "What is the most common type of food that gets wasted?", Arrays.asList("Meat", "Dairy", "Fruits and Vegetables"), "Fruits and Vegetables"));
        put(7, new Question(7, "What is the environmental impact of food waste?", Arrays.asList("Minimal impact", "If food waste were a country, it would be the third largest greenhouse gas emitter", "Food waste doesn't contribute to greenhouse gases"), "If food waste were a country, it would be the third largest greenhouse gas emitter"));
        put(8, new Question(8, "What is an efficient way to avoid shopping for more food than you need?", Arrays.asList("Taking inventory of refrigerator, freezer, and pantry before going to store", "Buy in bulk to get discounts", "Always eat out"), "Taking inventory of refrigerator, freezer, and pantry before going to store"));
        put(9, new Question(9, "What is the monetary cost of food waste worldwide per year?", Arrays.asList("$100 billion", "$50 billion", "$1 trillion"), "$1 trillion"));
        put(10, new Question(10, "What can be made from recycled food waste?", Arrays.asList("Renewable energy and fertilizer", "Plastic", "Clothing"), "Renewable energy and fertilizer"));
        }
    };

    Map<Integer, Question> quizQuestionMap = new HashMap<Integer, Question>() {
        {
            put(1, new Question(1, "How much food gets wasted globally each year?", Arrays.asList("1.3 billion tonnes", "5 billion tonnes", "300 million tonnes"), "1.3 billion tonnes"));
        }
    };

    public List<String> imageNames = Arrays.asList("backpackDora.jpg", "DoraReadyToStart.jpg", "jumpingMonkeyDora.jpg", "runningDora.jpg",
            "sittingMonkeyDora.jpg", "TylerDora.jpg", "riseAndDownFallOfDora.jpg");

    public Question getQuestionByNumber(int questionNumber) {

        return adventureQuestionMap.get(questionNumber);

    }

    public Model processAnswer(int questionNumber, String userChoice, int nrOfCorrectAnswers, Model model) {

        int uChoice = stringToInt(userChoice, 3);
        Question question = getQuestionByNumber(questionNumber);
        boolean isCorrect = isCorrect(question, uChoice);


        model.addAttribute("questionNumber", questionNumber);
        model.addAttribute("questionTitle", question.getQuestionTitle());
        model.addAttribute("answer", question.getAnswer());
        model.addAttribute("userChoice", userChoice);
        model.addAttribute("isCorrect", isCorrect ? "Correct" : "Incorrect");
        model.addAttribute("nrOfCorrectAnswers", isCorrect ? nrOfCorrectAnswers + 1 : nrOfCorrectAnswers);
        return model;
    }

    public boolean isCorrect(Question question, int userChoice) {
        userChoice -= 1;
        return question.getAnswer().equals(question.getOptions().get(userChoice));
    }

    public String addRandomImageToModel(Model model) {

        Random random = new Random();
        return imageNames.get(random.nextInt(imageNames.size()));
    }
}


/*
nr: 1
q: How much food gets wasted globally each year?
option1: 1.3 billion tonnes
option2: 5 billion tonnes
option3: 300 million tonnes
A: 1.3 billion tonnes
        ;;

nr: 2
q: What is composting?
option1: Throwing food away
option2: A natural process that turns organic material into a nutrient-rich soil conditioner
option3: Collective term for fruits and vegetables
A: A natural process that turns organic material into a nutrient-rich soil conditioner
;;

nr: 3
q: What's the best way to reduce food waste?
option1: Buy less food
option2: Ignore Use-By Dates
option3: Always finish your meals
A: Buy less food
;;

nr: 4
q: Where does most food waste occur?
option1: Retail stores
option2: Homes
option3: Farms
A: Homes
;;

nr: 5
q: How much food do average American households throw away each year?
option1: $500 worth
option2: $50 worth
option3: $1500 worth
A: $1500 worth
        ;;

nr: 6
q: What is the most common type of food that gets wasted?
option1: Meat
option2: Dairy
option3: Fruits and Vegetables
A: Fruits and Vegetables
;;

nr: 7
q: What is the environmental impact of food waste?
option1: Minimal impact
option2: If food waste were a country, it would be the third largest greenhouse gas emitter
option3: Food waste doesn't contribute to greenhouse gases
A: If food waste were a country, it would be the third largest greenhouse gas emitter
        ;;

nr: 8
q: What is an efficient way to avoid shopping for more food than you need?
option1: Taking inventory of refrigerator, freezer, and pantry before going to store
option2: Buy in bulk to get discounts
option3: Always eat out
A: Taking inventory of refrigerator, freezer, and pantry before going to store
;;

nr: 9
q: What is the monetary cost of food waste worldwide per year?
option1: $100 billion
option2: $50 billion
option3: $1 trillion
A: $1 trillion
        ;;

nr: 10
q: What can be made from recycled food waste?
option1: Renewable energy and fertilizer
option2: Plastic
option3: Clothing
A: Renewable energy and fertilizer
        ;;
        */

/*
    private static final String QUESTION_DELIMITER = ";;";
    private static final String CONTENT_DELIMITER = ":";

    public Question getQuestionByNumber(int questionNumber, int quizType) throws FileNotFoundException {

        File file = null;

        switch (quizType) {
            case 1:
                file = new File("src/main/java/dk/kea/projekt1_gruppe3_maven/AdventureQuestionsFile.txt");
                break;

        }

        // logic for getting the question by the question number
        return getQuestionFromFileBy(questionNumber, file);

    }

    private static Question getQuestionFromFileBy(int questionNumber, File file) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            String qTitle = "";
            List<String> listOfQOptions = new ArrayList<>();
            String qAnswer = "";

            String str = br.readLine();
            while (str != null || !str.equals(QUESTION_DELIMITER)) {

                if (str.equals("nr: " + questionNumber)) {
                    str = br.readLine();
                    int lineNr = 1;

                    while (!str.equals(QUESTION_DELIMITER)) {

                        String[] strSplit = str.split(CONTENT_DELIMITER);
                        switch (lineNr) {
                            case 1:
                                qTitle = strSplit[1];
                                break;
                            case 2:
                                listOfQOptions.add(strSplit[1]);
                                break;
                            case 3:
                                listOfQOptions.add(strSplit[1]);
                                break;
                            case 4:
                                listOfQOptions.add(strSplit[1]);
                                break;
                            case 5:
                                qAnswer = strSplit[1];
                                break;
                        }

                        str = br.readLine();
                        lineNr++;
                    }
                    return new Question(questionNumber, qTitle, listOfQOptions, qAnswer);
                }
                str = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}


package dk.kea.projekt1_gruppe3_maven.Service;

import dk.kea.projekt1_gruppe3_maven.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.*;

@Service
public class QuestionService {
    @Autowired
    private ResourceLoader resourceLoader;
    
    
    private File file;
    private static final String QUESTION_DELIMITER = ";;";
    private static final String CONTENT_DELIMITER = ":";

    public Question getQuestionByNumber(int questionNumber, int quizType) {
        
        Resource file = null;

        switch (quizType) {
            case 1:
                file = resourceLoader.getResource("classpath:AdventureQuestionsFile.txt");
                break;
            case 2:
                file = resourceLoader.getResource("classpath:QuizQuestionsFile.txt");
                break;
        }

        // logic for getting the question by the question number
        return getQuestionFromFileBy(questionNumber, file);
    }

    private static Question getQuestionFromFileBy(int questionNumber, Resource file) {
        InputStream is = null;
        BufferedReader br = null;

        try {
            is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String qTitle = "";
            List<String> listOfQOptions = new ArrayList<>();
            String qAnswer = "";

            String str = br.readLine();
            while (str != null || !str.equals(QUESTION_DELIMITER)) {

                if (str.equals("nr: " + questionNumber)) {
                    str = br.readLine();
                    int lineNr = 1;

                    while (!str.equals(QUESTION_DELIMITER)) {

                        String[] strSplit = str.split(CONTENT_DELIMITER);
                        switch (lineNr) {
                            case 1:
                                qTitle = strSplit[1];
                                break;
                            case 2:
                                listOfQOptions.add(strSplit[1]);
                                break;
                            case 3:
                                listOfQOptions.add(strSplit[1]);
                                break;
                            case 4:
                                listOfQOptions.add(strSplit[1]);
                                break;
                            case 5:
                                qAnswer = strSplit[1];
                                break;
                        }

                        str = br.readLine();
                        lineNr++;
                    }
                    return new Question(questionNumber, qTitle, listOfQOptions, qAnswer);
                }
                str = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


}
 */
