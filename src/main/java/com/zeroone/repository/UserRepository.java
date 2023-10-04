package com.zeroone.repository;


import com.zeroone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User findUserById(Long id);

    boolean existsById(Long id);
}
