package com.snakestore.database;

import com.snakestore.artifacts.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Long deleteByUsername(String username);
}
