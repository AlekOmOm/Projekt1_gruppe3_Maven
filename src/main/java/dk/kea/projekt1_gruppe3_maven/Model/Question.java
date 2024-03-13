package dk.kea.projekt1_gruppe3_maven.Model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

public class Question {
    private final int questionNumber;
    private final String questionTitle;
    private final List<String> options;
    private final String answer;

    public Question(int questionNumber, String questionTitle, List<String> options, String answer) {
        this.questionNumber = questionNumber;
        this.questionTitle = questionTitle;
        this.options = options;
        this.answer = answer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getOption(int index) {
        return options.get(index);
    }

    public String getAnswer() {
        return answer;
    }




}
