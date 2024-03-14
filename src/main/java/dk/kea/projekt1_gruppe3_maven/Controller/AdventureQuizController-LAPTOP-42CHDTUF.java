package dk.kea.projekt1_gruppe3_maven.Controller;

import dk.kea.projekt1_gruppe3_maven.Model.Question;
import dk.kea.projekt1_gruppe3_maven.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;


@Controller
public class AdventureQuizController {




    private Question question;

    @Autowired
    private QuestionService questionService;


    @GetMapping("/AdventureQuiz")
    public String adventureQuiz() {
        return "AdventureQuiz/AdventureQuiz"; // This is the name of the html file to be returned
    }

    @GetMapping("/question")
    public String showQuestion(@RequestParam("quizType") String QUIZ_TYPE,
                                @RequestParam("questionNumber") String questionNumber,
                               @RequestParam("answer") String userChoice,
                               Model model) {


        question = questionService.getQuestionByNumber(questionNumber, QUIZ_TYPE); // fetch the question
        if (question == null) {
            model.addAttribute("error", "The question with number " + questionNumber + " does not exist.");
            model.addAttribute("questionId", questionNumber);
            return "errorQuestionNotFound"; // return an error page or some error handling strategy.
        }

        // Add the required attributes to the model.
        // They are used to populate the fields in question.html
        model.addAttribute("questionTitle", question.getQuestionTitle());
        model.addAttribute("option1", question.getOption(0));
        model.addAttribute("option2", question.getOption(1));
        model.addAttribute("option3", question.getOption(2));
        model.addAttribute("questionNumber", questionNumber);

        return "AdventureQuiz/question";
    }


    @PostMapping("/question")
    public String processAnswer(@RequestParam("quizType") String QUIZ_TYPE,
                                    @RequestParam("questionNumber") String questionNumber,
                                @RequestParam("answer") String userChoice,
                                Model model) {

        this.question = questionService.getQuestionByNumber(questionNumber, QUIZ_TYPE);


        return "redirect:/answer?questionNumber=" + questionNumber + "&userChoice=" + userChoice;
    }

    @GetMapping("/answer")
    public String showAnswer(@RequestParam("quizType") String QUIZ_TYPE,
                             @RequestParam("questionNumber") String questionNumber,
                             @RequestParam("answer") String userChoice,
                             Model model) {
        if (question == null) {
            model.addAttribute("error", "The question with number " + questionNumber + " does not exist.");
            model.addAttribute("questionId", questionNumber);
            return "errorQuestionNotFound"; // return an error page or some error handling strategy.
        }

        // attributes for model: questionNumber, questionTitle, answer, userChoice, isCorrect

        String isCorrect = questionService.isCorrect(question, Integer.parseInt(userChoice)) ? "Correct" : "Incorrect";


        model.addAttribute("questionNumber", questionNumber);
        model.addAttribute("questionTitle", question.getQuestionTitle());
        model.addAttribute("answer", question.getAnswer());
        model.addAttribute("userChoice", userChoice);
        model.addAttribute("isCorrect", isCorrect);

        return "AdventureQuiz/answer";
    }


    @PostMapping("/answer")
    public String processAnswer1(@RequestParam("questionNumber") String questionNumber, Model model) {
        // Handle answer here

        return "AdventureQuiz/answer";
    }


    @RequestMapping("/answer")
    public String showAnswer1(Model model) {


        return "answer";
    }



}
