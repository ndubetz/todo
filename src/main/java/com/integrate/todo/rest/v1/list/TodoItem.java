package com.integrate.todo.rest.v1.list;

import com.integrate.todo.data.TodoItemRecord;

import java.util.Objects;

public class TodoItem {

  private String content;


  public TodoItem() { }


  public String getContent() { return this.content; }

  public TodoItem setContent( String content ) {
    this.content = content;
    return this;
  }

  public static TodoItem fromRecord( TodoItemRecord record ) {
    return new TodoItem()
      .setContent( record.getContent() );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TodoItem todoItem = (TodoItem) o;
    return Objects.equals(content, todoItem.content);
  }

  @Override
  public int hashCode() {

    return Objects.hash(content);
  }
}
