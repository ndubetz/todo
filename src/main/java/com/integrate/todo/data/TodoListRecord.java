package com.integrate.todo.data;


import com.integrate.todo.rest.v1.list.TodoItem;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "todo_list" )
public class TodoListRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    private final Instant timeStamp;

    private String title;

    @ElementCollection
    private List<TodoItemRecord> todoItems = new LinkedList<>();


    public TodoListRecord(String title) {
        this.title = title;
        this.timeStamp = Instant.now();
    }

    public TodoListRecord(){
        this("");
    }


    public Integer getUserID() {
        return userID;
    }

    public TodoListRecord setUserID(Integer userID) {
        this.userID = userID;
        return this;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public String getTitle() {
        return title;
    }

    public List<TodoItemRecord> getTodoItems() {
        return todoItems;
    }

    public TodoListRecord setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = new LinkedList<>();
        for( int index = 0; index < todoItems.size(); index++ ) {
            this.todoItems.add( TodoItem.toRecord( todoItems.get( index ) ) );
        }
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoListRecord that = (TodoListRecord) o;
        return Objects.equals(userID, that.userID) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userID, timeStamp, title);
    }
}
