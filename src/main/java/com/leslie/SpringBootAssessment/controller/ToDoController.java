package com.leslie.SpringBootAssessment.controller;


import com.leslie.SpringBootAssessment.model.ToDo;
import com.leslie.SpringBootAssessment.service.ToDoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class ToDoController {

    @Autowired
    public ToDoServiceImp toDoServiceImp;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    @GetMapping(value = "/list-todos")
    public String showTodos(ModelMap model) {
        String name = getLoggedInUserName(model);
        model.put("todos", toDoServiceImp.getTodosByUser(name));
        // model.put("todos", service.retrieveTodos(name));
        return "list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddTodoPage(ModelMap model) {
        model.addAttribute("todo", new ToDo());
        return "todo";
    }


    @GetMapping("/{id}")
    public Optional<ToDo> read(@PathVariable Long id) {
        return toDoServiceImp.getTodoById(id);
    }

    @GetMapping(value = "/update-todo")
    public String showUpdateTodoPage(@RequestParam long id, ModelMap model) {
        ToDo todo = toDoServiceImp.getTodoById(id).get();
        model.put("todo", todo);
        return "todo";
    }

    @GetMapping(value = "/delete-todo")
    public String deleteTodo(@RequestParam long id) {
        toDoServiceImp.deleteTodo(id);
        // service.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @PostMapping(value = "/update-todo")
    public String updateTodo(ModelMap model, @Valid ToDo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }
        todo.setUserName(getLoggedInUserName(model));
        toDoServiceImp.updateTodo(todo);
        return "redirect:/list-todos";
    }

    @PostMapping(value = "/add-todo")
    public String addTodo(ModelMap model, @Valid ToDo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        todo.setUserName(getLoggedInUserName(model));
        toDoServiceImp.saveTodo(todo);
        return "redirect:/list-todos";
    }
}
