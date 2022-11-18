package com.sergey.webquizengine.businesslayer;

import com.sergey.webquizengine.databaselayer.Quiz;
import com.sergey.webquizengine.databaselayer.QuizAnswer;
import com.sergey.webquizengine.dto.AnswerDto;
import com.sergey.webquizengine.dto.AnswerQuizDto;
import com.sergey.webquizengine.dto.QuizNoAnswerDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

public interface QuizService {
    QuizNoAnswerDto getQuiz(Integer id);

    AnswerDto answerQuestion(Integer id, AnswerQuizDto answer, UserDetails userDetails);

    QuizNoAnswerDto addNewQuiz(Quiz quiz, UserDetails userDetails);

    Page<Quiz> getAllQuizzes(int page);

    void deleteQuiz(Integer id, UserDetails userDetails);

    Page<QuizAnswer> getCompletedQuizzes(UserDetails userDetails, Integer page);
}
