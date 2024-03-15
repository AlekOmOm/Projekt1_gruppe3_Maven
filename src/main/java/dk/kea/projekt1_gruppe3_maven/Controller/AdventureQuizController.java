package dk.kea.projekt1_gruppe3_maven.Controller;

import dk.kea.projekt1_gruppe3_maven.Model.Question;
import dk.kea.projekt1_gruppe3_maven.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

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

        model.addAttribute("questionTitle", question.getQuestionTitle());
        model.addAttribute("option1", question.getOption(0));
        model.addAttribute("option2", question.getOption(1));
        model.addAttribute("option3", question.getOption(2));
        model.addAttribute("questionNumber", questionNumber);
        model.addAttribute("nrOfCorrectAnswers", nrOfCorrectAnswers); //added
        model.addAttribute("imageName", questionService.addRandomImageToModel(model));

        return "AdventureQuiz/question";
    }

    @PostMapping("/question")
    public String processAnswer(@RequestParam("questionNumber") int questionNumber,
                                @RequestParam("userChoice") String userChoice,
                                @RequestParam("nrOfCorrectAnswers") int nrOfCorrectAnswers,
                                Model model) {

        this.question = questionService.getQuestionByNumber(questionNumber);

        if (questionNumber==11) {
            return "redirect:/result?nrOfCorrectAnswers=" + nrOfCorrectAnswers;
        }

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
        int restartQuiz = 0;
        int goHome = 0;
        model.addAttribute("restartQuiz", restartQuiz);
        model.addAttribute("goHome", goHome);
        model.addAttribute("questionNumber", questionNumber);
        model.addAttribute("nextQuestionNumber", nextQuestionNumber);
        model.addAttribute("nrOfCorrectAnswers", nrOfCorrectAnswers);

        return "AdventureQuiz/answer";
    }

    @PostMapping("/answer")
    public String postAnswer(@RequestParam("questionNumber") int questionNumber,
                             @RequestParam("nrOfCorrectAnswers") int nrOfCorrectAnswers,
                                @RequestParam("nextQuestionNumber") int nextQuestionNumber,
                                @RequestParam("restartQuiz") int restartQuiz,
                               @RequestParam("goHome") int goHome,
                               Model model) {

        System.out.println();
        System.out.println("DEBUG: postAnswer()");
        System.out.println("questionNumber: " + questionNumber);
        System.out.println("restartQuiz: " + restartQuiz);
        System.out.println("goHome: " + goHome);


        model.addAttribute("nextQuestionNumber", nextQuestionNumber);

        if (nextQuestionNumber==11) {
            return "redirect:/result?questionNumber=" + questionNumber + "&nrOfCorrectAnswers=" + nrOfCorrectAnswers;
        } else if (restartQuiz==1) {
            System.out.println("if (restartQuiz==1) accessed");
            return "redirect:/question?questionNumber=1" + "&nrOfCorrectAnswers=0";
        } else if (goHome==1) {
            System.out.println("if (goHome==1) accessed");
            return "redirect:/home";
        } else {
            System.out.println("default accessed");
            return "redirect:/question?questionNumber=" + nextQuestionNumber + "&nrOfCorrectAnswers=" + nrOfCorrectAnswers;
        }
    }

    @GetMapping("/result")
    public String getResult(@RequestParam("questionNumber") int questionNumber,
                             @RequestParam("nrOfCorrectAnswers") int nrOfCorrectAnswers,
                             Model model) {

        System.out.println();
        System.out.println("DEBUG: getResult()");
        System.out.println("questionNumber: " + questionNumber);
        System.out.println("nrOfCorrectAnswers: " + nrOfCorrectAnswers);

        int percentageOfCorrectAnswers = (int) ((nrOfCorrectAnswers / 10.0) * 100);

        model.addAttribute("nrOfCorrectAnswers", nrOfCorrectAnswers);
        model.addAttribute("percentageOfCorrectAnswers", percentageOfCorrectAnswers);
        model.addAttribute("questionNumber", questionNumber);


        int restartQuiz = 0;
        int goHome = 0;
        model.addAttribute("restartQuiz", restartQuiz);
        model.addAttribute("goHome", goHome);

        return "AdventureQuiz/result";
    }

    @PostMapping("/result")
    public String restartQuiz(@RequestParam("restartQuiz") int restartQuiz,
                              @RequestParam("goHome") int goHome,
                              Model model) {

        System.out.println();
        System.out.println("DEBUG: restartQuiz()");
        System.out.println("restartQuiz: " + restartQuiz);
        System.out.println("goHome: " + goHome);

        if (restartQuiz==1) {
            System.out.println("if (restartQuiz==1) accessed");
            return "redirect:question?questionNumber=1" + "&nrOfCorrectAnswers=0";
        } else if (goHome==1) {
            System.out.println("if (goHome==1) accessed");
            // templates/Home.html
            return "redirect:home";
        } else {
            System.out.println("AdventureQuiz/AdventureQuiz accessed");
            // templates/AdventureQuiz/AdventureQuiz.html
            return "redirect:AdventureQuiz";

        }
    }

}
