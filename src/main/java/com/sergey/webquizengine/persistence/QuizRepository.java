package com.sergey.webquizengine.persistence;

import com.sergey.webquizengine.databaselayer.Quiz;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {


}
