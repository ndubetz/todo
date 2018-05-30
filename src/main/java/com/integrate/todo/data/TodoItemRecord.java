package com.integrate.todo.data;


import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "todo_item")
public class TodoItemRecord {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Integer id;

  private Instant timeStamp;

  private String content;


  public TodoItemRecord() { }


  public int getId() { return this.id; }

  public TodoItemRecord setId( Integer id ) {
    this.id = id;
    return this;
  }

  public Instant getTimestamp() { return this.timeStamp; }

  public TodoItemRecord setTimestamp( Instant time ) {
    this.timeStamp = time;
    return this;
  }

  public String getContent() { return this.content; }

  public TodoItemRecord setContent( String content ) {
    this.content = content;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TodoItemRecord that = (TodoItemRecord) o;
    return Objects.equals(id, that.id) &&
      Objects.equals(content, that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, timeStamp, content);
  }
}
