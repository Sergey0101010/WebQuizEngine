package com.sergey.webquizengine.controllers;

import com.sergey.webquizengine.businesslayer.QuizService;
import com.sergey.webquizengine.databaselayer.Quiz;
import com.sergey.webquizengine.databaselayer.QuizAnswer;
import com.sergey.webquizengine.dto.AnswerDto;
import com.sergey.webquizengine.dto.AnswerQuizDto;
import com.sergey.webquizengine.dto.QuizNoAnswerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("api/quizzes")
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/{id}")
    public QuizNoAnswerDto getQuiz(@PathVariable Integer id) {
        return quizService.getQuiz(id);
    }

    @GetMapping()
    public Page<Quiz> getAllQuizzes(
            @RequestParam int page) {
        return quizService.getAllQuizzes(page);
    }

    @GetMapping("/completed")
    public Page<QuizAnswer> getCompletedQuizzes(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        return quizService.getCompletedQuizzes(userDetails, page);
    }

    @PostMapping()
    public QuizNoAnswerDto addNewQuiz(@Valid @RequestBody Quiz quiz,
                                      @AuthenticationPrincipal UserDetails userDetails) {
        return quizService.addNewQuiz(quiz, userDetails);
    }

    @PostMapping("/{id}/solve")
    public AnswerDto checkAnswer(@PathVariable Integer id,
                                 @RequestBody AnswerQuizDto answer,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        return quizService.answerQuestion(id, answer, userDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable Integer id,
                           @AuthenticationPrincipal UserDetails userDetails) {
        quizService.deleteQuiz(id, userDetails);
    }


}
