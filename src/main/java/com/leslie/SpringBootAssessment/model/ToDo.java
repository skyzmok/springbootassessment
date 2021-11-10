package com.leslie.SpringBootAssessment.model;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name="todo")
@Getter
@Setter
@ToString
public class ToDo {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    @Size(min = 10, message = "Enter at least 10 Characters...")
    private String description;
    private Date targetDate;

    public ToDo() {
        super();
    }

    public ToDo(String user, String desc, Date targetDate, boolean isDone) {
        super();
        this.userName = user;
        this.description = desc;
        this.targetDate = targetDate;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ToDo toDo = (ToDo) o;
        return id != null && Objects.equals(id, toDo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
