package com.snakestore.database;

import com.snakestore.artifacts.LoginPassEntity;
import org.springframework.data.repository.CrudRepository;

public interface LoginPassRepository extends CrudRepository<LoginPassEntity, String> {
    Iterable<LoginPassEntity> findAllByUserName(String userName);
}
