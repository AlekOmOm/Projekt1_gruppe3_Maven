package dk.kea.projekt1_gruppe3_maven.Service.AdventureQuizServices;

import dk.kea.projekt1_gruppe3_maven.Model.Question;
import org.springframework.stereotype.Service;

@Service
public class AdventureQuizService {



    public static boolean isCorrect(Question question, int userAnswer) {

        return question.getAnswer().equals(question.getOptions().get(userAnswer));
    }

}
