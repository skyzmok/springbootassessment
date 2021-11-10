package com.leslie.SpringBootAssessment.service;

import com.leslie.SpringBootAssessment.model.ToDo;
import com.leslie.SpringBootAssessment.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImp implements ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;


    @Override
    public List<ToDo> getTodosByUser(String user) {
        return toDoRepository.findByUserName(user);
    }

    @Override
    public Optional<ToDo> getTodoById(long id) {
        return toDoRepository.findById(id);
    }

    @Override
    public void updateTodo(ToDo todo) {
        toDoRepository.save(todo);
    }

    @Override
    public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
        toDoRepository.save(new ToDo(name, desc, targetDate, isDone));
    }

    @Override
    public void deleteTodo(long id) {
        Optional<ToDo> todo = toDoRepository.findById(id);
        if (todo.isPresent()) {
            toDoRepository.delete(todo.get());
        }
    }

    @Override
    public void saveTodo(ToDo todo) {
        toDoRepository.save(todo);
    }

}
