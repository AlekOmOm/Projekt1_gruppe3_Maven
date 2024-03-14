package dk.kea.projekt1_gruppe3_maven.Controller;

import dk.kea.projekt1_gruppe3_maven.Model.Question;
import dk.kea.projekt1_gruppe3_maven.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdventureQuizController {


    private Question question;

    @Autowired
    private QuestionService questionService;


    @GetMapping("/AdventureQuiz")
    public String quizStart() {
        return "AdventureQuiz/AdventureQuiz";
    }

    @PostMapping("/AdventureQuiz")
    public String processQuizChoice(Model model) {

        return "redirect:/question?questionNumber=1" + "&nrOfCorrectAnswers=0";
    }


    @GetMapping("/question")
    public String showQuestion(@RequestParam("questionNumber") int questionNumber,
                               @RequestParam("nrOfCorrectAnswers") int nrOfCorrectAnswers,
                               Model model) {


        question = questionService.getQuestionByNumber(questionNumber); // fetch the question
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
        model.addAttribute("nrOfCorrectAnswers", nrOfCorrectAnswers); //added

        return "AdventureQuiz/question";
    }


    @PostMapping("/question")
    public String processAnswer(@RequestParam("questionNumber") int questionNumber,
                                @RequestParam("userChoice") String userChoice,
                                @RequestParam("nrOfCorrectAnswers") int nrOfCorrectAnswers,
                                Model model) {

        this.question = questionService.getQuestionByNumber(questionNumber);


        return "redirect:/answer?questionNumber=" + questionNumber + "&userChoice=" + userChoice + "&nrOfCorrectAnswers=" + nrOfCorrectAnswers;
    }


    @GetMapping("/answer")
    public String showAnswer(@RequestParam("questionNumber") int questionNumber,
                             @RequestParam("userChoice") String userChoice,
                             @RequestParam("nrOfCorrectAnswers") int nrOfCorrectAnswers,
                             Model model) {

        // logic processing
        model = questionService.processAnswer(questionNumber, userChoice, nrOfCorrectAnswers, model);
        int nextQuestionNumber = questionNumber + 1;
        model.addAttribute("questionNumber", questionNumber);
        model.addAttribute("nextQuestionNumber", nextQuestionNumber);

        return "AdventureQuiz/answer";
    }

    @PostMapping("/answer")
    public String postAnswer(@RequestParam("questionNumber") int questionNumber,
                             @RequestParam("nrOfCorrectAnswers") int nrOfCorrectAnswers,
                                @RequestParam("nextQuestionNumber") int nextQuestionNumber,
                               Model model) {

        model.addAttribute("nextQuestionNumber", nextQuestionNumber);

        System.out.println("DEBUG, questionNumber: "+questionNumber);
        System.out.println("DEBUG, nrOfCorrectAnswers: "+nrOfCorrectAnswers);
        System.out.println("DEBUG, nextQuestionNumber: "+nextQuestionNumber);
        if (questionNumber==0) {
            return "redirect:/AdventureQuiz";
        }


        return "redirect:/question?questionNumber=" + nextQuestionNumber + "&nrOfCorrectAnswers=" + nrOfCorrectAnswers;
    }




}
