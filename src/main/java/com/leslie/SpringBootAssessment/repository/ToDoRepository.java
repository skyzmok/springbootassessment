package com.leslie.SpringBootAssessment.repository;

import com.leslie.SpringBootAssessment.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findByUserName(String user);
}
