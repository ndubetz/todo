package com.integrate.todo.rest.v1.list.toBeDeleted;

import com.integrate.todo.data.TodoItemRecord;
import com.integrate.todo.data.TodoItemRepository;
import com.integrate.todo.rest.v1.list.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoItemService {

  private TodoItemRepository repository;


  @Autowired
  public TodoItemService( TodoItemRepository repository ) { this.repository = repository; }


  public TodoItem createTodoItem(TodoItem todoItem, Integer expectedListID) {

    TodoItemRecord record = repository.save(
      new TodoItemRecord().setContent( todoItem.getContent() )
    );
    return TodoItem.fromRecord( record );
  }

}
