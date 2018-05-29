package com.integrate.todo.data;


import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "todo_list" )
public class TodoListRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    private final Instant timeStamp;
    private String title;

    public TodoListRecord(String title) {
        this.title = title;
        this.timeStamp = Instant.now();
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
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
