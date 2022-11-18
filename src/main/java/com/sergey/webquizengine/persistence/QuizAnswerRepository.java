package com.sergey.webquizengine.persistence;

import com.sergey.webquizengine.databaselayer.QuizAnswer;
import com.sergey.webquizengine.databaselayer.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizAnswerRepository
        extends PagingAndSortingRepository<QuizAnswer, Integer> {
    Page<QuizAnswer> findQuizAnswerByUser(User user, Pageable pageable);
}
