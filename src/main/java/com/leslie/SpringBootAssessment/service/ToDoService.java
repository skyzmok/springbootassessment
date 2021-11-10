package com.leslie.SpringBootAssessment.service;

import com.leslie.SpringBootAssessment.model.ToDo;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ToDoService {

    List<ToDo> getTodosByUser(String user);

    Optional < ToDo > getTodoById(long id);

    void updateTodo(ToDo todo);

    void deleteTodo(long id);

    void saveTodo(ToDo todo);

    void addTodo(String name, String desc, Date targetDate, boolean isDone);
}
