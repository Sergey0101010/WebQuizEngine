package com.sergey.webquizengine.businesslayer;

import com.sergey.webquizengine.databaselayer.Quiz;
import com.sergey.webquizengine.databaselayer.QuizAnswer;
import com.sergey.webquizengine.databaselayer.User;
import com.sergey.webquizengine.dto.AnswerDto;
import com.sergey.webquizengine.dto.AnswerQuizDto;
import com.sergey.webquizengine.dto.QuizNoAnswerDto;
import com.sergey.webquizengine.exceptions.NoSuchQuizException;
import com.sergey.webquizengine.persistence.QuizAnswerRepository;
import com.sergey.webquizengine.persistence.QuizRepository;
import com.sergey.webquizengine.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final static int QUIZZES_IN_PAGE = 10;

    private final static String SORT_BY = "completedAt";

    private final QuizRepository quizRepository;

    private final UserRepository userRepository;

    private final QuizAnswerRepository quizAnswerRepository;

    @Override
    public QuizNoAnswerDto getQuiz(Integer id) {
        Quiz quizById = getQuizById(id);
        return quizNoAnswerFromQuiz(quizById);

    }


    @Override
    public AnswerDto answerQuestion(Integer id, AnswerQuizDto answerNumber, UserDetails userDetails) {

        Quiz quiz = getQuizById(id);
        AnswerDto answer = new AnswerDto();
        List<Integer> requestAnswers = answerNumber.getAnswer();
        User answeredUser = userRepository.findUserByEmail(userDetails.getUsername());
        if (requestAnswers.equals(quiz.getAnswer())) {
            answer.setSuccess(true);
            answer.setFeedback("Cool!");
            QuizAnswer quizAnswer = getQuizAnswer(id, quiz, answeredUser);
            quizAnswerRepository.save(quizAnswer);
        } else {
            answer.setSuccess(false);
            answer.setFeedback("Try again");
        }
        return answer;

    }

    private QuizAnswer getQuizAnswer(Integer id, Quiz quiz, User answeredUser) {
        QuizAnswer quizAnswer = new QuizAnswer();
        quizAnswer.setCompletedAt(LocalDateTime.now());
        quizAnswer.setUser(answeredUser);
        quizAnswer.setId(id);
        quizAnswer.setQuiz(quiz);
        return quizAnswer;
    }

    private Quiz getQuizById(Integer id) {
        Optional<Quiz> quizOptionalById = quizRepository.findById(id);
        return quizOptionalById
                .orElseThrow(() -> new NoSuchQuizException("No such quiz"));
    }

    @Override
    public QuizNoAnswerDto addNewQuiz(Quiz quiz, UserDetails userDetails) {
        User userThatAddedQuiz = userRepository.findUserByEmail(userDetails.getUsername());
        quiz.setUser(userThatAddedQuiz);
        quizRepository.save(quiz);
        return quizNoAnswerFromQuiz(quiz);
    }

    @Override
    public Page<Quiz> getAllQuizzes(int page) {
        PageRequest pageRequest = PageRequest.of(page, QUIZZES_IN_PAGE);
        Page<Quiz> quizzesPageFromRepo = quizRepository.findAll(pageRequest);
        return quizzesPageFromRepo;
    }

    @Override
    public void deleteQuiz(Integer id, UserDetails userDetails) {
        Quiz requestQuiz = getQuizById(id);
        String emailUserAddedQuiz = requestQuiz.getUser().getEmail();
        if (!userDetails.getUsername().equals(emailUserAddedQuiz)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            quizRepository.deleteById(id);
        }
    }

    @Override
    public Page<QuizAnswer> getCompletedQuizzes(UserDetails userDetails, Integer page) {
        User requestByUser = userRepository.findUserByEmail(userDetails.getUsername());
        PageRequest pageRequest = PageRequest.of(page, QUIZZES_IN_PAGE,
                Sort.Direction.DESC, SORT_BY);
        Page<QuizAnswer> quizAnswerByUser = quizAnswerRepository.findQuizAnswerByUser(requestByUser, pageRequest);
        log.info(quizAnswerByUser.getTotalElements() + " total user's answers");
        return quizAnswerByUser;

    }

    private QuizNoAnswerDto quizNoAnswerFromQuiz(Quiz quiz) {
        QuizNoAnswerDto quizNoAnswerDto = new QuizNoAnswerDto();
        quizNoAnswerDto.setId(quiz.getId());
        quizNoAnswerDto.setTitle(quiz.getTitle());
        quizNoAnswerDto.setText(quiz.getText());
        quizNoAnswerDto.setOptions(quiz.getOptions());
        return quizNoAnswerDto;
    }


}
