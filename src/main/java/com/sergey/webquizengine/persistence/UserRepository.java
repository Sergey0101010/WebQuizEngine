package com.sergey.webquizengine.persistence;

import com.sergey.webquizengine.databaselayer.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findUserByEmail(String email);

    boolean existsByEmailIgnoreCase(String email);


}
