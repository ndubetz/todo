package com.integrate.todo.data;


import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "todo_list" )
public class TodoListRecord {


    private String title;
    private Instant timestamp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    public TodoListRecord() {
        this.timestamp = Instant.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getUserID() {
        return userID;
    }
}
